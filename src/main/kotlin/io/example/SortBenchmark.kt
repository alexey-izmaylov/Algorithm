package io.example

import io.example.sort.JdkSort
import io.example.sort.MergeSort
import io.example.sort.QuickSort
import java.util.stream.Collectors.toUnmodifiableList
import java.util.stream.Stream
import kotlin.random.Random
import kotlin.system.measureNanoTime

fun main(args: Array<String>) {
    val list: List<Long> = Stream
        .generate { Random.nextLong(1000) }
        .limit(100_000)
        .collect(toUnmodifiableList())
    jdkSort(list)
    mergeSort(list)
    quickSort(list)
}

internal fun <T : Comparable<T>> mergeSort(list: List<T>) {
    val millis = measureNanoTime {
        MergeSort<T>().sort(list)
    }.toDouble() / 1_000_000
    println("mergeSort finished in: $millis milliseconds");
}

internal fun <T : Comparable<T>> quickSort(list: List<T>) {
    val millis = measureNanoTime {
        QuickSort<T>().sort(list)
    }.toDouble() / 1_000_000
    println("quickSort finished in: $millis milliseconds");
}

internal fun <T : Comparable<T>> jdkSort(list: List<T>) {
    val millis = measureNanoTime {
        JdkSort<T>().sort(list)
    }.toDouble() / 1_000_000
    println("jdkSort finished in: $millis milliseconds");
}