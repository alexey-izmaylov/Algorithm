package io.example.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JdkSort<T extends Comparable<T>> implements Sort<T> {

  @Override
  public List<T> sort(List<T> list) {
    ArrayList<T> result = new ArrayList<>(list);
    result.sort(Comparator.naturalOrder());
    return result;
  }
}