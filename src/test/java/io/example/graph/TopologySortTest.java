package io.example.graph;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TopologySortTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(TopologySortTest.class);

  @Test
  void sort() {
    Vertex<String> e = new Vertex<>("E", Set.of());
    Vertex<String> d = new Vertex<>("D", Set.of(e));
    Vertex<String> b = new Vertex<>("B", Set.of(d));
    Vertex<String> c = new Vertex<>("C", Set.of(d, e));
    Vertex<String> a = new Vertex<>("A", Set.of(b, c, d, e));
    Queue<String> sorted = new TopologySort().sort(Set.of(a));
    LOGGER.info(sorted.toString());
    assertThat(sorted, anyOf(contains("A", "B", "C", "D", "E"), contains("A", "C", "B", "D", "E")));
  }

  @Test
  void cyclic() {
    Vertex<String> e = new Vertex<>("E", new HashSet<>());
    Vertex<String> d = new Vertex<>("D", Set.of(e));
    Vertex<String> b = new Vertex<>("B", Set.of(d));
    e.getEdges().add(b);
    Vertex<String> c = new Vertex<>("C", Set.of(d, e));
    Vertex<String> a = new Vertex<>("A", Set.of(b, c, d, e));
    assertThrows(RuntimeException.class, () -> new TopologySort().sort(Set.of(a)));
  }
}