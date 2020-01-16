package io.example.graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopologySort {

  private static final Logger LOGGER = LoggerFactory.getLogger(TopologySort.class);

  public <T> Queue<T> sort(final Set<Vertex<T>> graph) {
    return depthFirstSearch(graph, new LinkedList<>());
  }

  private <T> Queue<T> depthFirstSearch(final Set<Vertex<T>> graph, final Deque<T> result) {
    for (Vertex<T> vertex : graph) {
      switch (vertex.getState()) {
        case GRAY: {
          final RuntimeException exception = new RuntimeException("Cycle with vertex: " + vertex.getValue());
          LOGGER.error("", exception);
          throw exception;
        }
        case WHITE: {
          LOGGER.debug("Found white: " + vertex.getValue());
          vertex.setState(State.GRAY);
          depthFirstSearch(vertex.getEdges(), result);
          vertex.setState(State.BLACK);
          result.addFirst(vertex.getValue());
          break;
        }
        case BLACK: {
          LOGGER.debug("Already black: " + vertex.getValue());
        }
      }
    }
    return result;
  }
}