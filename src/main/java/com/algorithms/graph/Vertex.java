package com.algorithms.graph;

/**
 * Created by linxiao on 2016/3/17.
 */
public class Vertex {
    private char label;
    private boolean wasVisited;
    public Vertex(char label) {
        this.label = label;
        wasVisited = false;
    }

    public char getLabel() {
        return label;
    }

    public void setLabel(char label) {
        this.label = label;
    }

    public boolean isWasVisited() {
        return wasVisited;
    }

    public void setWasVisited(boolean wasVisited) {
        this.wasVisited = wasVisited;
    }
}
