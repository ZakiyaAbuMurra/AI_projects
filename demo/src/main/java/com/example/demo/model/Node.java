package com.example.demo.model;

import java.util.ArrayList;

public class Node implements Comparable<Node>{
    private City city;
    private Node parent;
    private double cost;
    private double distance_from_first_node;
    private double distance_to_target = Double.MAX_VALUE;
    private ArrayList<Edge> adjacent = new ArrayList<>();

    public Node(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getDistance_from_first_node() {
        return distance_from_first_node;
    }

    public void setDistance_from_first_node(double distance_from_first_node) {
        this.distance_from_first_node = distance_from_first_node;
    }

    public double getDistance_to_target() {
        return distance_to_target;
    }

    public void setDistance_to_target(double distance_to_target) {
        this.distance_to_target = distance_to_target;
    }

    public ArrayList<Edge> getAdjacent() {
        return adjacent;
    }

    public void setAdjacent(ArrayList<Edge> adjacent) {
        this.adjacent = adjacent;
    }

    @Override
    public String toString() {
        return "Node{" +
                "city=" + city +
                ", parent=" + parent +
                ", cost=" + cost +

                '}';
    }


    public double calculateDistanceToTarget(Node target) {
        if(distance_to_target!=Double.MAX_VALUE){
            return distance_to_target;
        }
        double node_xaxis = this.getCity().getX_axis();
        double target_xaxis = target.getCity().getX_axis();
        double node_yaxis = this.getCity().getY_axis();
        double target_yaxis = target.getCity().getY_axis();
        return Math.sqrt(Math.pow(target_xaxis-node_xaxis,2) + Math.pow(target_yaxis-node_yaxis,2));

    }

    @Override
    public int compareTo(Node o) {return Double.compare(o.cost,this.cost);
    }
}