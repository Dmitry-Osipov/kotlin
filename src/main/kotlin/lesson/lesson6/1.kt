package lesson.lesson6

import kotlin.concurrent.thread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext

fun main() {
    // Корутина — это не отдельный поток. Её можно рассматривать как задачу или даже просто функцию, которая может
    // выполняться любым потоком, останавливаться и продолжать своё выполнение на любом другом потоке.
    // Зачем нужны корутины?
    // 1. Простота.
    // Корутины пишутся в лёгком императивном стиле, словно код не обладает признаками блокирующего и реактивного кода.
    // Для сравнения как бы один и тот же код получения данных пользователя и его постов выглядел с использованием
    // коллбэков, реактива (RxJava) и корутин.
    callbacks()
    rx()
    coroutines()  // Благодаря корутинам асинхронный код поместился в 4 строки
    // 2. Производительность
    // Представим что нам нужно выполнить какое-то действие 100 тысяч раз. При чём все эти 100 тысяч выполнений
    // должны быть последовательны.
    // Без использования корутин мы бы сразу подумали о создании новых потоков (new Thread()), но есть проблема с
    // переключением потоков на ядре и с тем, что каждый поток занимает очень много памяти (можем вылетить по
    // OutOfMemoryError)
    withThreads()
    withCoroutines()
    // 3. Контроль над выполнением
    // Скажем, мы хотим получить страницу с информацией о пользователе и его постах одним вызовом.
    // Cервис, который отвечает за выдачу постов клиента ответил ошибкой. Чтобы не тратить ресурсы на заведомо
    // негативный ответ, параллельная корутина с выгрузкой данных пользователя отменится автоматически. (Это поведение
    // не всегда может быть таким. Оно контролируемо и зависит от скопа в котором выполняются корутины, а также корутин
    runBlocking {
        first()
        second()
    }
}

// Callbacks
interface Callback<T> {  // Пример интерфейса колллбэка
    fun onSuccess(result: T)
    fun onFailure(error: Throwable)
}

fun callbacks() {
    fetchUser(object : Callback<User> {
        override fun onSuccess(user: User) {
            displayUser(user)  // Запускается на основном потоке
            fetchPosts(user, object : Callback<List<Post>> {
                override fun onSuccess(posts: List<Post>) {
                    displayPosts(posts)  // Запускается на основном потоке
                }

                override fun onFailure(error: Throwable) {
                    handleError(error)  // Обработка ошибки при получении постов
                }
            })
        }

        override fun onFailure(error: Throwable) {
            handleError(error)  // Обработка ошибки при получении пользователя
        }
    })
}

fun fetchUser(callback: Callback<User>) {
    // Имитация асинхронной операции получения пользователя
}

fun fetchPosts(user: User, callback: Callback<User>) {
    // Имитация асинхронной операции получения постов
}

// RxJava
fun rx() {
    fetchUser()
        .observeOn(AndroidSchedulers.mainThread()) // переключение на основной поток для отображения данных
        .flatMap { user ->
            displayUser(user) // отобразить пользователя на основном потоке
            fetchPosts(user) // возвращает Observable<List<Post>>
        }
        .observeOn(AndroidSchedulers.mainThread()) // снова переключаемся на основной поток
        .subscribe(
            { posts ->
                displayPosts(posts) // отобразить посты на основном потоке
            },
            { error ->
                handleError(error) // обработка ошибок
            }
        )
}

fun fetchUser(): Observable<User> {  // Пример функций, возвращающих Observable
    return Observable.create { emmiter ->
        // Логика получения пользователя
        // emmiter.onNext(user)
        // emmiter.onComplete()
        // или emmite.onError(error) в случае ошибки
    }
}

fun fetchPosts(user: User): Observable<List<Post>> {  // Имитация асинхронной операции получения постов
    return Observable.create { emitter ->
        // Логика получения постов
        // emitter.onNext(posts)
        // emitter.onComplete()
        // Или emitter.onError(error) в случае ошибки
    }
}

// Coroutines
fun coroutines() {
    viewModelScope.launch {
        val user = fetchUser()  // suspends coroutine
        displayUser(user)  // run on the main thread
        val posts = fetchPosts(user)  // suspends coroutine
        displayPosts(posts)  // run on the main thread
    }
}

//----------------------------------------------------------------------------------------------------------------------

fun withThreads() {
    repeat(100_000) {
        thread {
            Thread.sleep(1000L)
            print(".")
        }
    }
}

fun withCoroutines(): Unit = runBlocking {
    repeat(100_000) {
        launch {
            delay(1000L)
            print(".")
        }
    }
}

//----------------------------------------------------------------------------------------------------------------------

suspend fun fetchUser(): UserData = coroutineScope {
    // fetchUserDetails is cancelled if fetchPosts fails
    val userDetails = async { api.fetchUserDetails() }
    // fetchPosts is cancelled if fetchUserDetails fails
    val posts = async { api.fetchPosts() }
    UserData(userDetails.await(), posts.await())
}

// Или скажем вы хотите чтобы ресурс использовал только один поток, так как первый не умеет работать в конкурентной
// среде.
class UserDownloader(private val networkService: NetworkService) {
    private val users = mutableListOf<User>()
    private val dispatcher = Dispatchers.IO.limitedParallelism(1)
    // Создаём отдельный диспатчер с одним потоком в управлении

    suspend fun downloaded(): List<User> = withContext(dispatcher) {
        users.toList()
    }

    suspend fun fetchUser(id: Int) = withContext(dispatcher) {
        val newUser = networkService.fetchUser(id)
        users += newUser
    }
}

// Или у нас есть задача асинхронно и параллельно отослать всем клиентам сообщения, но при этом мы не хотим, чтобы наш
// код упал, если при отправке какого-либо сообщения выбросится ошибка.

suspend fun first() {
    val users = listOf("Mark", null, "Robert")
    supervisorScope {
        users.forEach { user ->
            launch {
                if (user != null) {
                    delay(100)
                    println("Hello " + user)
                } else {
                    throw RuntimeException("USER NOT FOUND")
                }
            }
        }
    }
    delay(100)
    println("Task finished.")
}

suspend fun second() {
    val users = listOf("Mark", null, "Robert")
    coroutineScope {
        users.forEach { user ->
            launch {
                if (user != null) {
                    delay(100)
                    println("Hello " + user)
                } else {
                    throw RuntimeException("USER NOT FOUND")
                }
            }
        }
    }
    delay(100)
    println("Task finished.")
}
// Благодаря разным областям, в которых выполняются корутины, мы смогли контролировать ситуации с выбросом ошибок.
// Коротко говоря, при использовании supervisorScope корутины не связаны друг с другом и выполняются независимо.
// В случае использования coroutineScope корутины связаны. Если какая-либо корутина упадёт - она потянет остальные.
