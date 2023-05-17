package com.example.demo.model;

public class Edge {
    private double weight;
    private Node node;

    public Edge(double weight, Node node) {
        this.weight = weight;
        this.node = node;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "weight=" + weight +
                ", node=" + node +
                '}';
    }
}
