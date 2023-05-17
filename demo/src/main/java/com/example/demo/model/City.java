package com.example.demo.model;

public class City {
    private String city_name;
    private int x_axis;
    private int y_axis;

    public City(String city_name, int x_axis, int y_axis) {
        this.city_name = city_name;
        this.x_axis = x_axis;
        this.y_axis = y_axis;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public int getX_axis() {
        return x_axis;
    }

    public void setX_axis(int x_axis) {
        this.x_axis = x_axis;
    }

    public int getY_axis() {
        return y_axis;
    }

    public void setY_axis(int y_axis) {
        this.y_axis = y_axis;
    }

    @Override
    public String toString() {
        return "City{" +
                "city_name='" + city_name + '\'' +
                ", x_axis=" + x_axis +
                ", y_axis=" + y_axis +
                '}';
    }
}
