package lesson.lesson2

import java.time.LocalDateTime

// В Kotlin нет необходимости явно прописывать getters и setters, они генерируются автоматически для свойств val и var.
// Конструктор может быть объявлен прямо в заголовке класса.
class Person(val firstName: String, var age: Int)

//}

//public final class Person {  // Так же выглядит аналогичный класс в Java
//    private final String firstName;
//    private int age;
//
//    public Person(String firstName, int age) {
//        this.firstName = name;
//        this.age = age;
//    }
//
//    // getters and setters

// Kotlin поддерживает основной конструктор (primary constructor) и вторичные конструкторы (secondary constructors).
// Основной конструктор объявляется прямо в заголовке класса, а если требуется дополнительные конструкторы, они
// создаются с помощью ключевого слова constructor
class Car(val brand: String, val model: String) {
    constructor(brand: String) : this(brand, "Unknown")  // Если требуется добавить какую-то логику в конструктор,
    // он должен сначала вызвать primary конструктор, а затем открывать скобки {}
}

//public final class Car {  // Тот же самый класс в Java
//    private final String brand;
//    private final String model;
//
//    public Car(String brand, String model) {
//        this.brand = brand;
//        this.model = model;
//    }
//
//    public Car(String brand) {
//        this(brand, "Unknown");
//    }
//
//    // + getters and setters
//}

// В Kotlin классы по умолчанию final, наследование возможно только при явном указании open.
open class Animal
class Dog : Animal()

// Для создания классов POJO в Kotlin имеется отдельный тип класса - data class (аналог record в Java), из-за чего
// отпадает нужда в Lombok:
data class User(val username: String, val age: Int)

// Так обычно объявляют data классы в проектах:
// - нет фигурных скобок
// - все поля в столбик
// - отступ в 4 пробела
// - trailing comma (завершающая запятая)
data class NotificationEntity(
    val id: String,
    val chatId: String,
    val eventDate: LocalDateTime,
    val notificationDates: List<LocalDateTime> = emptyList(),  // Можно указать значение по умолчанию
    val isWeekly: Boolean = false,
    val text: String,
    val owner: String,
    val creationDate: LocalDateTime,  // Оставляем завершающую запятую на случай изменений
)

// Kotlin автоматически генерирует геттеры и сеттеры для val и var. Но при необходимости их можно переопределить
class Usr(val name: String, var age: Int) {
    val nameUppercase: String
        get() = name.uppercase()  // Кастомный геттер для свойства
}

// Если свойство (в Kotlin свойство - это поле и методы доступа (геттеры и сеттеры)) может быть инициализировано в
// момент создания объекта, используется lateinit для изменяемых свойств. Это аналог отложенной инициализации объектов
// в Java через null и проверку на null
class Example {
    lateinit var description: String
}

// Kotlin поддерживает инициализаторы через блоки init, которые выполняются при создании объекта
class Employee(val name: String) {
    var position: String = "Unknown"

    init {  // Инициализация может быть выполнена в теле класса или с помощью лямбда-выражений
        println("Employee created: $name")
    }
}

// Методы в Kotlin объявляются аналогично Java
class Calculator {
    fun add(a: Int, b: Int): Int {
        return a + b
    }
}

// Kotlin использует четыре основных модификатора доступа:
// - public — доступен везде (по умолчанию).
// - private — доступен только внутри класса.
// - protected — доступен в классе и его подклассах.
// - internal — доступен внутри одного модуля.
open class Exmple {
    private val secret = "hidden"
    protected val subclassVisible = "visible in subclass"
    internal val moduleWide = "visible in module"
    public val everyoneCanSee = "visible everywhere"  // public по умолчанию можно не указывать
}
// Отличия от Java:
// - В Kotlin по умолчанию все классы и члены являются public, тогда как в Java классы и их члены без модификаторов
// видимости имеют package-private доступ.
// - Модификатор internal в Kotlin аналогичен package-private в Java, но действует на уровне модуля, а не пакета.

// В Kotlin классы по умолчанию final, т.е. они не могут быть унаследованы, если явно не указано иное с помощью
// ключевого слова open. Это отличие от Java, где классы по умолчанию могут быть унаследованы, если не объявлены как
// final.
open class Animl {
    open fun sound() {
        println("Some generic animal sound")
    }
}

class DogAnimal : Animl() {
    override fun sound() {
        println("Bark")
    }
}
// Особенности наследования:
// - Чтобы класс был наследуемым, нужно объявить его с open.
// - Методы, которые можно переопределять, также нужно пометить как open.
// - Переопределенные методы помечаются ключевым словом override. В отличие от Java, где используется аннотация
// @Override, в Kotlin это обязательная часть синтаксиса.
// - Если подкласс имеет основной конструктор, в нем необходимо явно вызвать основной конструктор родительского класса,
// если тот не является пустым:
open class Person1(open val name: String)
class Employee1(override val name: String, var age: Int) : Person1(name)
// - Если же родительский класс имеет вторичные конструкторы, можно их использовать через ключевое слово super:
open class Vehicle(val brand: String) {
    constructor(brand: String, year: Int) : this(brand) {
        println("Vehicle brand: $brand, year: $year")
    }
}
class Car1(brand: String, year: Int) : Vehicle(brand, year)

// Ключевое слово abstract используется как в Java, чтобы создать абстрактные классы и методы. Абстрактные методы
// должны быть реализованы в подклассах, как в Java.
abstract class Shape {
    abstract fun draw()
    fun describe() {
        println("This is a shape")
    }
}
class Circle : Shape() {
    override fun draw() {
        println("Drawing a circle")
    }
}

// Kotlin поддерживает интерфейсы, как и Java, но с рядом особенностей:
// - В интерфейсах Kotlin могут быть методы с реализацией (подобно default методам в Java)
// - Интерфейсы не содержат состояния (не могут иметь полей).
// - Если класс реализует несколько интерфейсов с одинаковыми методами, необходимо явно указать, какой метод
// использовать (или предоставить свою реализацию).
interface Movable {
    fun move() {
        println("Moving...")
    }
    fun stop()
}

class Car2 : Movable {
    override fun stop() {
        println("Car stopped")
    }
}

// пример конфликта интерфейсов:
interface A {
    fun hello() = println("Hello from A")
}

interface B {
    fun hello() = println("Hello from B")
}

class C : A, B {
    override fun hello() {
        // Разрешение конфликта интерфейсов
        super<A>.hello()
        super<B>.hello()
    }
}

//----------------------------------------------------------------------------------------------------------------------

// Типы классов в Kotlin:
// - Обычные классы (Regular Classes) - это стандартные классы, которые вы используете для создания объектов, содержащих
// состояние и поведение. Они аналогичны классам в Java и используются для общей ОО-разработки
class Person2(val name: String, var age: Int) {
    fun introduce() {
        println("Hi, my name is $name, and I am $age years old.")
    }
}
// - Data-классы (Data Classes) - автоматически генерируют полезные методы, такие как equals(), hashCode(), toString()
// и copy(). Эти классы идеальны для хранения данных, где важна структура и равенство объектов:
data class User1(
    val name: String,
    val age: Int,
)
// - Классы перечисления (Enum Classes) - используются для представления фиксированных наборов констант.
enum class Direction {
    NORTH, SOUTH, EAST, WEST
}
enum class Color(private val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF);

    fun containsRed() = (this.rgb and RED.rgb != 0)
}
// - Запечатанные классы (Sealed Classes) - ограничивают иерархию подклассов. Они используются, когда набор возможных
// типов объектов известен и ограничен во время компиляции. Это особенно полезно в моделировании состояний (например,
// для управления состояния UI или обработки результата операций). Все подклассы sealed класса должны быть определены в
// том же файле, что и сам класс. Это гарантирует, что все возможные варианты известны на этапе компиляции
sealed class Result
class Success(val data: String) : Result()
class Error(val message: String) : Result()
fun handleResult(result: Result) {
    when (result) {
        is Success -> println("Success: ${result.data}")
        is Error -> println("Error: ${result.message}")
    }
}
// - Вложенные и внутренние классы (Nested and Inner Classes) - Вложенный класс - аналог статических вложенных классов
// в Java, он не имеет доступа к переменным внешнего класса. Чтобы вложенный класс имел доступ к переменным внешнего
// класса, его нужно объявить с помощью ключевого слова inner - тогда он станет внутренним классом
class Outer(val greeting: String) {
    class Nested {
        fun greet() = "Hello from Nested class"
    }

    inner class Inner {
        fun greet() = "Hello from Inner class, greeting: $greeting"
    }
}
// - Объекты (Object Declarations) - реализация паттерна Singleton. Объявление объекта создаёт единственный экземпляр
// класса, который можно использовать напрямую, без необходимости создания нового экземпляра. Чаще всего такие классы
// используют как утилитные
object Singleton {
    val name = "Singleton"

    fun show() = println("This is a singleton object")
}
fun callSingleton() {
    Singleton.show()
}
// - Объекты компаньоны (Companion Object) - Kotlin не имеет статических членов, как в Java, но предлагает companion
// object, который может содержать элементы, доступные через класс, как если бы они были статическими. Это аналог
// статических методов или полей в Java. Чаще всего его используют для хранения констант - аналог static final полей
// в Java
class MyClass {
    companion object {
        fun create(): MyClass = MyClass()  // Вот это просто статика, как была бы в Java, не более
    }
}
fun createCompanion() {
    val instance = MyClass.create()  // Создание через статичную фабрику
    val classicInstance = MyClass()  // Создание по-старинке
}

//----------------------------------------------------------------------------------------------------------------------

// Kotlin полностью совместим с Java благодаря работе на JVM. Это позволяет использовать классы Kotlin в Java и
// наоборот. Однако при взаимодействии могут возникать нюансы, связанные с отличиями в синтаксисе и особенностями
// языка. Для улучшения совместимости Kotlin предлагает несколько аннотаций: @JvmField, @JvmStatic, и @JvmOverloads.
// Рассмотрим их использование и влияние на совместимость.
class User4J(val name: String, var age: Int) {  // Пример вызова этого класса доступен в методе testUser класса Main
    fun greet() {
        println("Hello, my name is $name, and I am $age years old.")
    }
}

// Аннотации для улучшения совместимости
// - @JvmField - в Kotlin все свойства val и var по умолчанию имеют приватные поля с автоматическими геттерами и
// сеттерами. Если требуется непосредственный доступ к полю без использования методов доступа, можно использовать эту
// аннотацию
class Person4J {  // Пример вызова этого класса доступен в методе testPerson класса Main
    @JvmField
    var age: Int = 25
}
// - @JvmStatic - для улучшения взаимодействия между Kotlin и Java можно использовать эту аннотацию, чтобы метод или
// свойство можно было вызвать как статический в Java. Особенно полезно в companion object, т.к. Kotlin урезан
// по статике
class Utils4J {  // Пример вызова этого класса доступен в методе testUtils класса Main
    companion object {
        @JvmStatic  // Без @JvmStatic выглядел бы так: Utils4J.Companion.printMessage()
        fun printMessage() {
            println("This is a static method in Kotlin")
        }
    }
}
// - @JvmOverloads - Kotlin поддерживает аргументы по умолчанию, что позволяет не перегружать методы с разным
// количеством аргументов. Однако Java не поддерживает аргументы по умолчанию, и чтобы упростить использование методов
// Kotlin из Java, можно применить эту аннотацию, которая генерирует перегруженные версии метода
class Calculator4J {  // Пример вызова этого класса доступен в методе testCalculator класса Main
    @JvmOverloads
    fun add(a: Int, b: Int = 0): Int = a + b
}

// Взаимодействие с data class
data class UserDTO4J(  // Пример вызова этого класса доступен в методе testDataClass класса Main
    val name: String,
    val age: Int,
)

// Взаимодействие с sealed class
sealed class Result4J {   // Пример вызова этого класса доступен в методе testResult класса Main
    class Success(val data: String) : Result4J()
    class Error(val message: String) : Result4J()
}
