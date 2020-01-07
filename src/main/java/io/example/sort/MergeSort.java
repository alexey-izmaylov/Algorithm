package io.example.sort;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MergeSort<T extends Comparable<T>> implements Sort<T> {

  private static final Logger LOGGER = LoggerFactory.getLogger(MergeSort.class);

  @Override
  public List<T> sort(List<T> list) {
    return sort(new ArrayList<>(list), 0);
  }

  private List<T> sort(final List<T> input, final int level) {
    final int group = (int) Math.pow(2, level);
    if (group < input.size()) {
      LOGGER.debug("level={} group={} input={}", level, group, input);

      final List<T> list = new LinkedList<>();
      for (int from = 0; from < input.size(); from += 2 * group) {

        final int middle = min(from + group, input.size());
        final List<T> left = input.subList(from, middle);
        final int to = min(from + 2 * group, input.size());

        if (middle == to) {
          LOGGER.debug("  sorted group ({}..{}): {}", from, middle - 1, left);
          list.addAll(left);
        } else {
          List<T> merged = merge(
              new LinkedList<>(left),
              new LinkedList<>(input.subList(middle, to))
          );
          LOGGER.debug("  merged sorted left ({}..{}) with right ({}..{}): {}", from, middle - 1, middle, to - 1, merged);
          list.addAll(merged);
        }
      }
      return sort(list, level + 1);
    } else {
      return input;
    }
  }

  private List<T> merge(final Queue<T> left, final Queue<T> right) {
    final List<T> result = new LinkedList<>();
    Optional<T> optional;
    while ((optional = minimal(left, right)).isPresent()) {
      result.add(optional.get());
    }
    return result;
  }

  private Optional<T> minimal(final Queue<T> left, final Queue<T> right) {
    if (left.isEmpty() && right.isEmpty()) return Optional.empty();
    if (left.isEmpty()) return Optional.of(right.poll());
    if (right.isEmpty()) return Optional.of(left.poll());
    if (left.peek().compareTo(right.peek()) < 0) return Optional.of(left.poll());
    return Optional.of(right.poll());
  }
}
