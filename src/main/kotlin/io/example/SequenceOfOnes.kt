package io.example

/**
B. Последовательно идущие единицы
Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Требуется найти в бинарном векторе самую длинную последовательность единиц и вывести её длину.

Желательно получить решение, работающее за линейное время и при этом проходящее по входному массиву только один раз.
Формат ввода

Первая строка входного файла содержит одно число n, n ≤ 10000. Каждая из следующих n строк содержит ровно одно число — очередной элемент массива.
Формат вывода

Выходной файл должен содержать единственное число — длину самой длинной последовательности единиц во входном массиве.
Пример
Ввод 	Вывод

5
1
0
1
0
1



1
 */
fun main(args: Array<String>) {
    var bestResult = 0
    val n = readLine().orEmpty().let {
        if (it.isEmpty() || it == "0") {
            println(bestResult)
            return
        }
        it.toInt()
    }
    val pattern = "1"
    val anti = "0"
    var result = 0
    for (i in 1..n) {
        val line = readLine().orEmpty()
        if (line == pattern) {
            ++result
            if (bestResult < result) {
                bestResult = result
            }
        } else if (line == anti) {
            result = 0
        } else {
            break
        }
    }
    println(bestResult)
}