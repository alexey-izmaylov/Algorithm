package io.example

/**
A. Камни и украшения
Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt
Даны две строки строчных латинских символов: строка J и строка S. Символы, входящие в строку J, — «драгоценности», входящие в строку S — «камни». Нужно определить, какое количество символов из S одновременно являются «драгоценностями». Проще говоря, нужно проверить, какое количество символов из S входит в J.

Это разминочная задача, к которой мы размещаем готовые решения. Она очень простая и нужна для того, чтобы вы могли познакомиться с нашей автоматической системой проверки решений. Ввод и вывод осуществляется через файлы, либо через стандартные потоки ввода-вывода, как вам удобнее.

Python: https://pastebin.com/bCKpF9Ru. В качестве языка выбирайте Python 2.7.

C++: https://pastebin.com/e5wMVV1u. Можно использовать GNU c++ 14 4.9.

C#: https://pastebin.com/UZU4iCB0. Язык: Mono C# 5.2.0.

Java: https://pastebin.com/SbLfafuv. Подойдёт, например, язык Java 8.

Kotlin: https://pastebin.com/Ruj3W2Er. Язык: Kotlin.

Swift: https://pastebin.com/Jh5PPwM6. Язык: Swift 4.1.1.

JavaScript: https://pastebin.com/B5YhwWFK. Язык: Node JS 8.9.4.

Формат ввода
На двух первых строках входного файла содержатся две строки строчных латинских символов: строка J и строка S. Длина каждой не превосходит 100 символов.
Формат вывода
Выходной файл должен содержать единственное число — количество камней, являющихся драгоценностями.
Пример
Ввод 	Вывод

ab
aabbccd



4
 */
fun main(args: Array<String>) {
    var result = 0
    val j = readLine().orEmpty()
    readLine().orEmpty()
        .forEach {
            if (j.contains(it)) ++result
        }
    println(result)
}