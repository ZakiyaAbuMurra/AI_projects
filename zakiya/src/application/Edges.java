package application;

import java.util.Comparator;

public class Edges implements Comparable<Edges> {
    private double destance ;
    private double destance_and_huristic ;
    private double destance1 ;
    Node node;

    public Edges() {
    }

    public Edges(double destance, Node n) {
        this.destance = destance;
        this.node = n;
        destance_and_huristic =Double.MAX_VALUE ;
        destance1 =Double.MAX_VALUE;
    }

    public Edges(Node node) {
        this.node = node;
    }

    public double getDestance() {
        return destance;
    }

    public void setDestance(double destance) {
        this.destance = destance;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }


    public double getDestance_and_huristic() {
        return destance_and_huristic;
    }

    public void setDestance_and_huristic(double destance_and_huristic) {
        this.destance_and_huristic = destance_and_huristic;
    }

    public double getDestance1() {
        return destance1;
    }

    public void setDestance1(double destance1) {
        this.destance1 = destance1;
    }

    @Override
    public String toString() {
        return "Edges{" +
                "destance=" + destance +
                ", destance_and_huristic=" + destance_and_huristic +
                ", node=" + node +
                '}';
    }

    @Override
    public int compareTo(Edges o) {
        if(this.getDestance_and_huristic() == o.getDestance_and_huristic())
            return 0;
        if(this.getDestance_and_huristic() > o.getDestance_and_huristic())
            return 1 ;
        return -1 ;
    }
}
