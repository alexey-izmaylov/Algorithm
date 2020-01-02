package io.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class QuickSort<T extends Comparable<T>> implements Sort<T> {

  private final AtomicLong counter = new AtomicLong(0);

  @Override
  public List<T> sort(Iterable<T> iterable) {
    ArrayList<T> list = new ArrayList<>();
    iterable.forEach(list::add);
    System.out.println("Input: " + list);
    List<T> sorted = List.copyOf(
        sort(list, 0, list.size() - 1)
    );
    System.out.println("Iterations: " + counter.get());
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
