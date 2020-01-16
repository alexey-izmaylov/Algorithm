package io.example

import io.example.sort.JdkSort
import io.example.sort.MergeSort
import io.example.sort.QuickSort
import java.util.stream.Collectors.toUnmodifiableList
import java.util.stream.Stream
import kotlin.random.Random
import kotlin.system.measureNanoTime

fun main(args: Array<String>) {
    val list = generateList(100_000)
    jdkSort(list)
    mergeSort(list)
    quickSort(list)

    val list2 = generateList(10_000_000)
    jdkSort(list2)
    quickSort(list2)
}

private fun generateList(limit: Long): List<Long> = Stream
    .generate { Random.nextLong(1000) }
    .limit(limit)
    .collect(toUnmodifiableList())

internal fun <T : Comparable<T>> mergeSort(list: List<T>) {
    val millis = measureNanoTime {
        MergeSort<T>().sort(list)
    }.toDouble() / 1_000_000
    println("mergeSort of ${list.size} elements finished in: $millis milliseconds")
}

internal fun <T : Comparable<T>> quickSort(list: List<T>) {
    val millis = measureNanoTime {
        QuickSort<T>().sort(list)
    }.toDouble() / 1_000_000
    println("quickSort of ${list.size} elements finished in: $millis milliseconds")
}

internal fun <T : Comparable<T>> jdkSort(list: List<T>) {
    val millis = measureNanoTime {
        JdkSort<T>().sort(list)
    }.toDouble() / 1_000_000
    println("jdkSort of ${list.size} elements finished in: $millis milliseconds")
}