package io.example

import java.util.LinkedList
import java.util.Queue
import java.util.stream.Collectors.toList

/**
F. Слияние k сортированных списков
Язык 	Ограничение времени 	Ограничение памяти 	Ввод 	Вывод
Все языки 	1 секунда 	10Mb 	стандартный ввод или input.txt 	стандартный вывод или output.txt
Oracle Java 7 	1 секунда 	20Mb
Node JS 8.9.4 	1 секунда 	20Mb
Oracle Java 8 	1 секунда 	20Mb
Kotlin 	1 секунда 	20Mb

Даны k отсортированных в порядке неубывания массивов неотрицательных целых чисел, каждое из которых не превосходит 100. Требуется построить результат их слияния: отсортированный в порядке неубывания массив, содержащий все элементы исходных k массивов.

Длина каждого массива не превосходит 10 ⋅ k.

Постарайтесь, чтобы решение работало за время k ⋅ log(k) ⋅ n, если считать, что входные массивы имеют длину n.
Формат ввода

Первая строка входного файла содержит единственное число k, k ≤ 1024.

Каждая из следующих k строк описывает по одному массиву. Первое число каждой строки равняется длине соответствующего массива, оставшиеся числа этой строки описывают значения элементов этого же массива. Элементы массивов являются неотрицательными целыми числами и не превосходят 100.
Формат вывода

Выходной файл должен содержать отсортированный в порядке неубывания массив, содержащий все элементы исходных массивов.
Пример
Ввод 	Вывод

4
6 2 26 64 88 96 96
4 8 20 65 86
7 1 4 16 42 58 61 69
1 84



1 2 4 8 16 20 26 42
58 61 64 65 69 84 86
88 96 96
 */
fun main(args: Array<String>) {
    val n = readLine().orEmpty().let {
        if (it.isEmpty() || it == "0") return
        it.toInt()
    }
    val lists = ArrayList<Queue<Byte>>()
    for (i in 1..n) {
        val list = readLine().orEmpty()
            .split(" ")
            .stream()
            .skip(1)
            .map { it.toByte() }
            .collect(toList())
        lists.add(LinkedList(list))
    }
    while (true) {
        var empty = true
        var minValue: Byte = -1
        var minIndex = 0
        for (i in 0 until lists.size) {
            if (lists[i].isNotEmpty()) {
                empty = false
                if (minValue == (-1).toByte()) {
                    minValue = lists[i].peek()
                    minIndex = i
                } else {
                    if (minValue > lists[i].peek()) {
                        minValue = lists[i].peek()
                        minIndex = i
                    }
                }
            }
        }
        lists[minIndex].poll()
        if (empty) break
        print("$minValue ")
    }
}