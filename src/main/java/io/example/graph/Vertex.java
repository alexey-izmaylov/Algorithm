package io.example.graph;

import java.util.Set;

public class Vertex<T> {

  private final T value;
  private final Set<Vertex<T>> edges;
  private State state = State.WHITE;

  public Vertex(T value, Set<Vertex<T>> edges) {
    this.value = value;
    this.edges = edges;
  }

  public T getValue() {
    return value;
  }

  public Set<Vertex<T>> getEdges() {
    return edges;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }
}