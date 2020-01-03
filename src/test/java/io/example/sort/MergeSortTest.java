package io.example.sort;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.Test;

class MergeSortTest {

  @Test
  void sort() {
    List<Integer> sorted = new MergeSort<Integer>().sort(List.of(2, 1, 3, 2));
    assertNotNull(sorted);
    assertThat(sorted, contains(1, 2, 2, 3));
  }
}