package practice.lesson1

import kotlin.random.Random

fun main() {
    printSortedWords("Kotlin is a modern programming language")
    println("-------------------------------------------------------------")
    println(findShortestAndLongestWords("Kotlin is a modern statically typed programming language"))
    println(findShortestAndLongestWords("This is my long"))
    println(findShortestAndLongestWords("some text"))
    println(findShortestAndLongestWords("one"))
    println("-------------------------------------------------------------")
    printPalindrome("Анна узнала как построить шалаш своими руками")
    println("-------------------------------------------------------------")
    println(getSumInRange(2))
    println(getSumInRange(10))
    println("-------------------------------------------------------------")
    println(findSumOfPalindromeNumbers(10))
    println(findSumOfPalindromeNumbers(100))
    println("-------------------------------------------------------------")
    println(getNumberProperties(2).toString())
    println(getNumberProperties(13).toString())
    println(getNumberProperties(25).toString())
    println("-------------------------------------------------------------")
    println(reverseString("abc"))
    println("-------------------------------------------------------------")
    println(getMinAndMaxNumbers())
    println(getMinAndMaxNumbers())
    println(getMinAndMaxNumbers())
    println("-------------------------------------------------------------")
    println(convertCelsiusToFahrenheit(10))
    println(convertCelsiusToFahrenheit(25))
    println(convertCelsiusToFahrenheit(32))
    println("-------------------------------------------------------------")
    println(sumNumberDigits(123))
    println(sumNumberDigits(1021))
}

// Создать статический метод, который принимает строку (предложение), подсчитывает количество слов (разделитель -
// пробел, без знаков препинания) и выводит их в отсортированном алфавитном порядке.
fun printSortedWords(sentence: String) {
    sentence
        .split(" ")
        .sortedWith { t, t2 -> t.lowercase().compareTo(t2.lowercase()) }
        .forEach { println(it) }
}

// Написать статический метод, который принимает строку (предложение), разбивает её на слова по пробелам, и находит
// самое длинное и самое короткое слово. Вернуть найденные слова. Если несколько слов имеют одинаковую длину,
// вернуть первое из них.
fun findShortestAndLongestWords(sentence: String): List<String> {
    val words = sentence.split(" ")
    if (words.size < 2) {
        return words
    }

    var shortestWord = words[0]
    var shortestLength = shortestWord.length
    var longestWord = words[0]
    var longestLength = longestWord.length
    for (i in 1 until words.size) {
        val length = words[i].length
        if (length < shortestLength) {
            shortestWord = words[i]
            shortestLength = length
        } else if (length > longestLength) {
            longestWord = words[i]
            longestLength = length
        }
    }

    return if (shortestLength == longestLength) {
        listOf(longestWord)
    } else {
        listOf(shortestWord, longestWord)
    }
}

// Написать статический метод, который принимает строку (предложение), разбивает её на слова по пробелам и выводит все
// слова, являющиеся палиндромами.
fun printPalindrome(sentence: String) {
    val words = sentence.split(" ")
    for (word in words) {
        val current = word.lowercase()
        val reversed = current.reversed()
        if (current == reversed) {
            println(word)
        }
    }
}

// Написать метод, который принимает число N и возвращает сумму всех четных чисел от 0 до N.
fun getSumInRange(num: Int): Int {
    if (num < 2) {
        return 0
    }

    var sum = 0
    for (i in 2..<num step 2) {
        sum += i
    }

    return sum
}

// Написать метод, который считает количество чисел-палиндромов в диапазоне целых чисел от 0 до N.
fun findSumOfPalindromeNumbers(num: Int): Int {
    var count = 0
    for (i in 0..<num) {
        val str = i.toString()
        if (str == str.reversed()) {
            count++
        }
    }

    return count
}

// Написать метод, который принимает число N и определяет, является ли оно четным/нечетным, простым/составным.
fun getNumberProperties(num: Int): Pair<String, String> {
    val parity = if (num % 2 == 0) "Even" else "Odd"
    val primality = if (isPrime(num)) "Prime" else "Composite"
    return Pair(parity, primality)
}

fun isPrime(number: Int, divisor: Int = 2): Boolean {
    if (number < 2) {
        return false
    }

    if (number == 2) {
        return true
    }

    if (number % divisor == 0) {
        return false
    }

    if (divisor.times(2) > number) {
        return true
    }

    return isPrime(number, divisor + 1)
}

// Написать метод, который переворачивает строку (например, "abc" -> "cba").
fun reverseString(str: String): String {
    return str.reversed()
}

// Написать 2 метода: метод, который находит минимальное и максимальное значение в массиве целых чисел, который
// сгенерирвал другой метод
fun getMinAndMaxNumbers(): Pair<Int, Int> {
    val arr = generateIntArray()
    val min = arr.min()
    val max = arr.max()
    return Pair(min, max)
}

fun generateIntArray(): IntArray {
    return IntArray(10) { Random.nextInt(-100_000, 100_000) }
}

// Написать метод, который принимает температуру в градусах Цельсия и переводит её в градусы Фаренгейта.
fun convertCelsiusToFahrenheit(degrees: Int): Double {
    return degrees * 1.8 + 32
}

// Написать метод, который принимает число и возвращает сумму его цифр (например, 123 -> 6).
fun sumNumberDigits(num: Int): Int {
    var sum = 0
    val str = num.toString()
    for (c in str) {
        sum += c.digitToInt()
    }

    return sum
}
