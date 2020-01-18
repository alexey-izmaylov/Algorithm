package io.example.sort

import java.io.BufferedReader
import java.nio.file.Files
import java.nio.file.Path
import java.util.LinkedList
import java.util.Optional
import java.util.Queue
import java.util.UUID
import java.util.stream.Stream
import kotlin.random.Random

const val dir = "D:/tmp/"

fun main(args: Array<String>) {
    Files.createDirectories(Path.of(dir))

    val path = LinesGenerator(10_000_000, 20)
        .asFile(dir + "input")
        .toAbsolutePath()
        .also { println("file is generated in: $it") }

    SortEachLineOfFile(path, Path.of(dir + "result"))
        .sort()
        .also { println("file is sorted in: $it") }
}

class SortEachLineOfFile(
    private val from: Path,
    private val to: Path
) {

    private val buffer = 1024 * 1024

    fun sort(): Path {
        val fromFile = from.toFile()
        if (fromFile.isFile) {
            Files.deleteIfExists(to)
            Files.createFile(to)
                .toFile()
                .printWriter()
                .use { writer ->
                    val lineParts = mutableMapOf<Long, List<Path>>()
                    fromFile
                        .bufferedReader()
                        .use { reader ->
                            var line = 1L
                            var symbolNumber = 0
                            var i: Int
                            val stringBuilder = StringBuilder()
                            while (reader.read().also { i = it } != -1) {
                                val char = i.toChar()
                                if (char == '\n') {
                                    val path = writeSortedPart(stringBuilder.toString())
                                    stringBuilder.clear()
                                    lineParts.merge(line, mutableListOf(path)) { old, _ -> old + listOf(path) }
                                    symbolNumber = 0

                                    ++line
                                } else if (char != '\t' && char != '\r') {
                                    stringBuilder.append(char)
                                    ++symbolNumber
                                    if (symbolNumber >= buffer) {
                                        val path = writeSortedPart(stringBuilder.toString())
                                        stringBuilder.clear()
                                        lineParts.merge(line, mutableListOf(path)) { old, _ -> old + listOf(path) }
                                        symbolNumber = 0
                                    }
                                }
                            }
                        }
                    println("sorted line parts:")
                    lineParts.forEach {
                        println("line: ${it.key} " + it.value.map { path -> path.toAbsolutePath().toString() })
                    }

                    val sortedLines = mutableMapOf<Long, Path>()
                    for (i in 1L..lineParts.size) {
                        val parts = lineParts[i].orEmpty()
                        if (parts.isNotEmpty()) {
                            sortedLines[i] = mergeParts(LinkedList(parts))
                        }
                    }

                    for (i in 1L..sortedLines.size) {
                        sortedLines[i]?.let {
                            it.toFile()
                                .bufferedReader()
                                .use { reader ->
                                    reader.transferTo(writer)
                                    writer.println()
                                }
                        }
                    }
                }
            return to
        } else throw RuntimeException("file $from does not exist")
    }

    private fun writeSortedPart(string: String): Path {
        val charArray = string.toCharArray()
        charArray.sort()
        val path = Path.of(dir + UUID.randomUUID().toString())
        Files.createFile(path)
            .toFile()
            .bufferedWriter()
            .use { it.write(charArray) }
        return path
    }

    private fun mergeParts(parts: Queue<Path>): Path {
        return if (parts.size == 1) {
            parts.poll()
        } else {
            val left = parts.poll()
            val right = parts.poll()
            parts.add(merge(left, right))
            mergeParts(parts)
        }
    }

    private fun merge(left: Path, right: Path): Path {
        val to = Path.of(dir + UUID.randomUUID().toString())
        Files
            .createFile(to)
            .toFile()
            .bufferedWriter()
            .use { writer ->
                left
                    .toFile()
                    .bufferedReader()
                    .use { left ->
                        val leftReader = CharBuffer(left)
                        right
                            .toFile()
                            .bufferedReader()
                            .use { right ->
                                val rightReader = CharBuffer(right)

                                var optional: Optional<Char>
                                while (minimal(leftReader, rightReader).also { optional = it }.isPresent) {
                                    writer.append(optional.get())
                                }
                            }
                    }
            }
        return to
    }

    private fun minimal(left: CharBuffer, right: CharBuffer): Optional<Char> {
        return if (left.isEmpty() && right.isEmpty()) Optional.empty()
        else if (left.isEmpty()) Optional.of(right.poll())
        else if (right.isEmpty()) Optional.of(left.poll())
        else if (left.peek() < right.peek()) Optional.of(left.poll())
        else Optional.of(right.poll())
    }

    internal class CharBuffer(private val reader: BufferedReader) {
        private var buffer: Int = -1

        fun isEmpty(): Boolean {
            return if (buffer == -1) {
                buffer = reader.read()
                buffer == -1
            } else false
        }

        fun peek(): Char {
            isEmpty()
            return buffer.toChar()
        }

        fun poll(): Char {
            isEmpty()
            val result = buffer.toChar()
            buffer = -1
            return result
        }
    }
}

class LinesGenerator(
    private val lineMaxSize: Int,
    private val count: Long
) {

    fun asFile(path: String = UUID.randomUUID().toString()): Path {
        val filePath = Path.of(path)
        Files.deleteIfExists(filePath)
        Files.createFile(filePath)
            .toFile()
            .printWriter()
            .use { writer ->
                asStream().forEach {
                    writer.println(it)
                }
            }
        return filePath
    }

    fun asStream(): Stream<String> = Stream
        .generate { generateLine() }
        .limit(count)

    private fun generateLine(): String = List(Random.nextInt(lineMaxSize / 2, lineMaxSize)) {
        ('A'..'z').random()
    }.joinToString("")
}