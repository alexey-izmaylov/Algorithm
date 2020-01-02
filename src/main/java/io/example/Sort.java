package io.example;

import java.util.List;

public interface Sort<T extends Comparable<T>> {

  List<T> sort(Iterable<T> list);
}