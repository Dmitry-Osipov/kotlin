package lesson.lesson5

fun main() {
    // Так как в котлине всё типизировано (даже те примитивы, которые мы знаем из Java), встаёт вопрос: а как работают
    // операции сложения, удаления, сравнения и тд
    // Рассмотрим пример сложения чисел:
    val o = 1 + 2
    // На этапе компиляции это не что иное как вызов функции у одного из объектов:
    // val a: Int = 1
    // val b: Int = 2
    // val o = a.plus(b)
    // Разумеется, для такого варианта компилятор выполнит оптимизацию (заранее посчитает и поставит 3), но для более
    // сложных кейсов, как следующий, запись будет именно такой.

    // Рассмотрим ещё один пример. Представим, что мы пишем приложение “кошелёк” и что нам нужно к имеющемуся балансу
    // добавить определённую сумму:
    data class Amount(
        val value: Long,
        val currency: String,
    ) {
        operator fun plus(amount: Amount): Amount = Amount(value + amount.value, currency)
    }

    class Account {
        var balance: Amount = Amount(100, "BYN")
    }

    val account = Account()
    println(account.balance)
    account.balance += Amount(50, "BYN")
    println(account.balance)
}
