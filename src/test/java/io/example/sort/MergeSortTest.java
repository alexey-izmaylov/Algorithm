package io.example.sort;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MergeSortTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(MergeSortTest.class);

  @Test
  void sort() {
    List<Integer> sorted = new MergeSort<Integer>().sort(List.of(2, 1, 3, 4, 2));
    assertNotNull(sorted);
    assertThat(sorted, contains(1, 2, 2, 3, 4));
  }

  @Test
  void sort0() {
    assertThat(new MergeSort<Integer>().sort(List.of()), empty());
  }

  @Test
  void sort1000() {
    sortN(1000);
  }

  @Test
  void sortSizesFrom1To20() {
    for (int i = 1; i < 20; i++) {
      LOGGER.info("sort {} elements", i);
      sortN(i);
    }
  }

  private void sortN(int capacity) {
    ArrayList<Integer> list = new ArrayList<>(capacity);
    Random random = new Random();
    for (int i = 0; i < capacity; i++) {
      list.add(random.nextInt(10));
    }
    assertThat(new MergeSort<Integer>().sort(list), contains(new JdkSort<Integer>().sort(list).toArray()));
  }
}