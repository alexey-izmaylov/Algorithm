package io.example.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.example.sort.QuickSort;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TestInstance(Lifecycle.PER_CLASS)
class BinarySearchTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(BinarySearchTest.class);

  @Test
  void search() {
    List<String> list = new ArrayList<>();
    for (int i = 1; i <= 1000; i++) list.add(Integer.toString(i));
    List<String> sorted = new QuickSort<String>().sort(list);
    LOGGER.info(sorted.toString());

    String value = Integer.toString(new Random().nextInt(1000) + 1);
    LOGGER.info(value);

    assertEquals(sorted.indexOf(value), new BinarySearch<String>().search(sorted, value));
  }

  @Test
  void searchLeft() {
    List<String> list = new ArrayList<>();
    for (int i = 1; i <= 1000; i++) list.add(Integer.toString(i));
    List<String> sorted = new QuickSort<String>().sort(list);
    LOGGER.info(sorted.toString());

    assertEquals(0, new BinarySearch<String>().search(sorted, "1"));
  }

  @Test
  void searchRight() {
    List<String> list = new ArrayList<>();
    for (int i = 1; i <= 1000; i++) list.add(Integer.toString(i));
    List<String> sorted = new QuickSort<String>().sort(list);
    LOGGER.info(sorted.toString());

    assertEquals(999, new BinarySearch<String>().search(sorted, "999"));
  }

  @Test
  void notFound() {
    List<String> input = new ArrayList<>();
    for (int i = 1; i <= 1000; i++) input.add(Integer.toString(i));
    LOGGER.info(input.toString());

    assertTrue(new BinarySearch<String>().search(input, "0") < 0);
  }
}