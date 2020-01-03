package io.example.search;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinarySearch<T extends Comparable<T>> implements Search<T> {

  private static final Logger LOGGER = LoggerFactory.getLogger(BinarySearch.class);

  @Override
  public int search(List<T> list, T value) {
    return search(list, value, 0, list.size() - 1);
  }

  int search(List<T> list, T value, int left, int right) {
    if (left > right) {
      return -1;
    }
    final int middle = (right + left) / 2;
    LOGGER.debug("left={} middle={} right={}", left, middle, right);
    if (list.get(middle).compareTo(value) == 0) {
      return middle;
    } else if (value.compareTo(list.get(middle)) > 0) {
      return search(list, value, middle + 1, right);
    } else {
      return search(list, value, left, middle - 1);
    }
  }
}