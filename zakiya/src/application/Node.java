package application;

import java.util.ArrayList;
import java.util.Comparator;

public class Node  {
    private String name ;
    private double xReal,yReal,xPlot,yPlot;
    ArrayList<Edges> adj ;

    public Node(String name, double xReal, double yReal , double xPlot , double yPlot) {
        this.name = name;
        this.xReal = xReal;
        this.yReal = yReal;
        this.xPlot = xPlot;
        this.yPlot = yPlot;
        adj = new ArrayList<>() ;
    }

  /*  public Node(double xPlot, double yPlot) {
        this.xPlot = xPlot;
        this.yPlot = yPlot;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getxReal() {
        return xReal;
    }

    public void setxReal(double xReal) {
        this.xReal = xReal;
    }

    public double getyReal() {
        return yReal;
    }

    public void setyReal(double yReal) {
        this.yReal = yReal;
    }

    public double getxPlot() {
        return xPlot;
    }

    public void setxPlot(double xPlot) {
        this.xPlot = xPlot;
    }

    public double getyPlot() {
        return yPlot;
    }

    public void setyPlot(double yPlot) {
        this.yPlot = yPlot;
    }

    public ArrayList<Edges> getAdj() {
        return adj;
    }

    public void setAdj(ArrayList<Edges> adj) {
        this.adj = adj;
    }


    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", xReal=" + xReal +
                ", yReal=" + yReal +
                '}';
    }


}
