package io.example.sort;

import static java.lang.Math.min;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MergeSort<T extends Comparable<T>> implements Sort<T> {

  private static final Logger LOGGER = LoggerFactory.getLogger(MergeSort.class);

  @Override
  public List<T> sort(Iterable<T> list) {
    List<T> arrayList = new LinkedList<>();
    list.forEach(arrayList::add);
    return sort(arrayList, 0);
  }

  private List<T> sort(List<T> list, final int level) {
    final int group = (int) Math.pow(2, level);
    LOGGER.debug("level={} group={} list={}", level, group, list);

    if (group < list.size()) {
      List<T> newList = new LinkedList<>();
      for (int from = 0; from < list.size(); from += 2 * group) {

        int middle = min(from + group, list.size());
        List<T> left = list.subList(from, middle);
        int to = min(from + 2 * group, list.size());

        LOGGER.debug("    index from {} to {}", from, to - 1);

        if (middle == to) {
          LOGGER.debug("    already merged group: {}", left);
          newList.addAll(left);
        } else {
          LOGGER.debug("    merge left ({}..{}) with right ({}..{})", from, middle - 1, middle, to - 1);
          newList.addAll(
              merge(
                  new LinkedList<>(left),
                  new LinkedList<>(list.subList(middle, to))
              ));
        }
      }
      return sort(newList, level + 1);
    } else {
      return list;
    }
  }

  private List<T> merge(LinkedList<T> left, LinkedList<T> right) {
    List<T> list = new LinkedList<>();
    Optional<T> optional;
    while ((optional = pollMin(left, right)).isPresent()) {
      list.add(optional.get());
    }
    LOGGER.debug("        sorted merged group: {}", list);
    return list;
  }

  private Optional<T> pollMin(Queue<T> left, Queue<T> right) {
    if (left.isEmpty() && right.isEmpty()) return Optional.empty();
    if (left.isEmpty()) return Optional.of(right.poll());
    if (right.isEmpty()) return Optional.of(left.poll());
    if (left.peek().compareTo(right.peek()) < 0) return Optional.of(left.poll());
    return Optional.of(right.poll());
  }
}
