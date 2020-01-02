package io.example;

import java.util.ArrayList;
import java.util.List;

public class MergeSort<T extends Comparable<T>> implements Sort<T> {

  @Override
  public List<T> sort(Iterable<T> list) {
    ArrayList<T> arrayList = new ArrayList<>();
    list.forEach(arrayList::add);
    arrayList.sort(null);
    return arrayList;
  }
}
