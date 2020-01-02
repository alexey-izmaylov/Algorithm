package io.example;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(PER_CLASS)
class QuickSortTest {

  @Test
  void sort() {
    List<Integer> sorted = new QuickSort<Integer>().sort(List.of(2, 1, 3, 2));
    assertNotNull(sorted);
    assertThat(sorted, contains(1, 2, 2, 3));
  }

  @Test
  void matchWithJdkSort() {
    final int capacity = 100;
    ArrayList<Integer> list = new ArrayList<>(capacity);
    SecureRandom random = new SecureRandom();
    for (int i = 0; i < capacity; i++) {
      list.add(random.nextInt(10));
    }
    assertThat(new QuickSort<Integer>().sort(list), contains(new JdkSort<Integer>().sort(list).toArray()));
  }
}