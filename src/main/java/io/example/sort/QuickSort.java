package io.example.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuickSort<T extends Comparable<T>> implements Sort<T> {

  private static final Logger LOGGER = LoggerFactory.getLogger(QuickSort.class);
  private final AtomicLong counter = new AtomicLong(0);

  @Override
  public List<T> sort(List<T> list) {
    LOGGER.debug("Input: {}", list);
    List<T> sorted = sort(new ArrayList<>(list), 0, list.size() - 1);
    LOGGER.debug("Iterations: {}", counter.get());
    return sorted;
  }

  List<T> sort(List<T> list, int low, int high) {
    int partition = partition(list, low, high);
    if (low < partition - 1) {
      sort(list, low, partition - 1);
    }
    if (high > partition) {
      sort(list, partition, high);
    }
    return list;
  }

  private int partition(List<T> list, int low, int high) {
    T pivot = list.get((low + high) / 2);
    while (low <= high) {
      while (pivot.compareTo(list.get(low)) > 0) {
        ++low;
        metric();
      }
      while (pivot.compareTo(list.get(high)) < 0) {
        --high;
        metric();
      }
      if (low <= high) {
        if (list.get(low).compareTo(list.get(high)) != 0) {
          T swap = list.get(low);
          list.set(low, list.get(high));
          list.set(high, swap);
        }
        ++low;
        --high;
      }
      metric();
    }
    return low;
  }

  private void metric() {
    counter.incrementAndGet();
  }
}
