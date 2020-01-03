package io.example.sort

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import kotlin.test.Test
import kotlin.test.assertNotNull

@TestInstance(PER_CLASS)
class JdkSortTest {

    @Test
    fun sort() {
        val sorted = JdkSort<Int>().sort(listOf(2, 1, 3, 2))
        assertNotNull(sorted)
        assertThat(sorted, contains(1, 2, 2, 3))
    }
}