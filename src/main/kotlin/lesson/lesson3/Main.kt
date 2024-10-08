package lesson.lesson3

import lesson.lesson2.Person

fun main() {
    // Лямбда-функции — это анонимные функции, которые можно определить и использовать без необходимости присваивать
    // им имя. Они предоставляют удобный синтаксис для определения небольших функций прямо "на месте" — там, где
    // это нужно. Пример лямбды в Kotlin:
    val sum = { x: Int, y: Int -> x + y }
    println(sum(3, 4))

    // Лямбды в Kotlin обладают рядом особенностей:
    // - Опускание типов аргументов: если компилятор может вывести тип аргументов лямбды, их можно не указывать явно:
    val sumWithoutTypes = listOf(1, 2, 3, 4, 5, 6).reduce { x, y -> x + y }
    println(sumWithoutTypes)
    // - Использование it для единственного параметра: если лямбда принимает один параметр, его можно опустить,
    // используя ключевое слово it:
    listOf(1, 2, 3).forEach { println(it) }
    // - Возвращаемое значение лямбды: лямбда автоматически возвращает значение последней выраженной строки - нет
    // необходимости явно писать return:
    val multiply: (Int, Int) -> Int = { x, y -> x * y }
    println(multiply(3, 4))
    // Типизировать лямбды можно по-разному: указать типы около объявления лямбды, указать типы внутри лямбды,
    // всё вместе

    // Лямбды используются как во встроенных методах Kotlin, таких как map, filter, reduce, так и в собственных методах
    val numsList = listOf(1, 2, 3, 4)
    val doubledList = numsList
        .map { it * 2 }  // Можно писать как в цепочке вызовов, так и в одну строку
    println(doubledList)

    // Kotlin позволяет использовать синтаксис с вынесением лямбды за круглые скобки, если она является последним или
    // единственным параметром:
    numsList.filter { it % 2 == 0 }  // Метод filter ожидает на вход только лямбду - круглые скобки не пишем
//    logger.error(e) { "Произошла ошибка" }  // Метод логирования принимает как параметр объект исключения, а
    // также лямбду

    //------------------------------------------------------------------------------------------------------------------

    // Функции высшего порядка — это функции, которые принимают другие функции в качестве параметров или возвращают
    // функции.
    // Примеры функций высшего порядка:
    fun <T> applyTwice(x: T, f: (T) -> T): T {  // Принимает лямбду параметром, а затем применяет её дважды к аргументу
        return f(f(x))
    }

    fun repeatAction(times: Int, action: (Int) -> Unit) {  // Принимает лямбду, которая будет вызываться times раз с
        // передачей текущего индекса
        for (i in 0 until times) {
            action(i)
        }
    }

    val applyTwiceResult = applyTwice(3) { it * 2 }  // Можно было передать в скобках, но мы оставили лямбду
    // за скобками
    println(applyTwiceResult)
    repeatAction(3) { println("Action #$it") }

    //------------------------------------------------------------------------------------------------------------------

    // Лямбды с получателем позволяют использовать лямбды в контексте некоторого объекта, к которому можно обращаться
    // через this (либо без него). Это делает код более выразительным и читабельным.
    // Пример лямбды с получателем:
    val stringBuilderResult = buildString {  // buildString() - функция, которая принимает на вход лямбду с
        // получателем, и эта лямбда будет применена к объекту класса StringBuilder, после чего функция buildString и
        // вернёт строку
        append("Hello, ")
        append("World!")
    }
    println(stringBuilderResult)

    // Функции вроде apply, with, run, let, и also широко используют концепцию лямбд с получателем.
    // - apply: Возвращает объект, на котором было вызвано.
    // - run: Возвращает результат выполнения лямбды.
    // - also и let: Используют it как контекст.
    val person = Person("Alice", 25)
        .apply {
            age = 10
        }
    println(person.age)

    // Лямбды могут захватывать переменные из внешнего контекста, даже если они изменяемы. Это поведение
    // называется замыканием:
    var counter = 0
    val increment = { counter++ }
    increment()
    increment()
    println(counter)

    //------------------------------------------------------------------------------------------------------------------

    // Анонимные функции похожи на лямбды, но их синтаксис чуть более традиционный. Основное различие между анонимной
    // функцией и лямбдой заключается в том, как именно они возвращают значения. В анонимной функции необходимо
    // использовать явное return, тогда как в лямбде оно подразумевается последним выражением:
    val anonymousFuncSum = fun(a: Int, b: Int): Int { return a + b }
    val anotherAnonymousFuncSum = fun(a: Int, b: Int): Int = a + b
    println(anonymousFuncSum(3, 4))
    println(anotherAnonymousFuncSum(3, 4))

    //------------------------------------------------------------------------------------------------------------------

    // Ключевое слово inline позволяет оптимизировать функции высшего порядка, которые принимают лямбды, чтобы избежать
    // накладных расходов на создание объектов лямбд и передачу их в функцию. Использование inline уменьшает количество
    // объектов, созданных для лямбд, и улучшает производительность, особенно если лямбды часто используются в
    // высоконагруженных местах.
}

inline fun performAction(action: () -> Unit) {
    action()
}
