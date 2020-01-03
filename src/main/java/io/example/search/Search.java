package io.example.search;

import java.util.List;

public interface Search<T extends Comparable<T>> {

  int search(List<T> list, T value);
}