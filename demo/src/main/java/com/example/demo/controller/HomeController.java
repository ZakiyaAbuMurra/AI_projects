package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.model.Edge;
import com.example.demo.model.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HomeController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextArea ans_area;

    @FXML
    private TextField dest_id;

    @FXML
    private Button find_btn;

    @FXML
    private TextField src_id;

    private ArrayList<Node> arr_towns;

    Group line_group = new Group();

    public void initialize() throws FileNotFoundException {
        arr_towns = new ArrayList<>();
        readFileCity();
        readFileEdges();

    }

    private void readFileEdges() throws FileNotFoundException {
        File file = new File("./src/main/resources/com/example/demo/edges.csv");
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(",");
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            addNodeEdge(line);
        }
    }

    private void addNodeEdge(String line) {
        StringTokenizer stringTokenizer = new StringTokenizer(line,",");
        String town_name = stringTokenizer.nextToken();
        String town_edge = stringTokenizer.nextToken();
        double weight = Double.parseDouble(stringTokenizer.nextToken());

        for (Node node : arr_towns){
            if (node.getCity().getCity_name().equalsIgnoreCase(town_name)){
                for (Node edge : arr_towns){
                    if (edge.getCity().getCity_name().equalsIgnoreCase(town_edge)){
                        node.getAdjacent().add(new Edge(weight,edge));
                    }
                }
            }
        }

    }

    private void readFileCity() throws FileNotFoundException {

        File file = new File("./src/main/resources/com/example/demo/ramallah_city_coord.csv");
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(",");
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            City city = getCityCoordinates(line);
            arr_towns.add(new Node(city));
            drawCityCoordinates(city);
        }
    }

    private void drawCityCoordinates(City city) {

        for (Node node : arr_towns){
            ///Creating the buttons
            Button button = new Button("");
            setButtonStyle(button);

            ///Drawing
            button.setLayoutX(city.getX_axis() - 3);
            button.setLayoutY(city.getY_axis() - 5);

            button.setOnMouseClicked(e->{
                if (src_id.getText().equalsIgnoreCase("")){
                    src_id.setText(node.getCity().getCity_name());
                }
                else{
                    dest_id.setText(node.getCity().getCity_name());
                }
            });

            anchorPane.getChildren().add(button);
        }


    }

    private void setButtonStyle(Button button) {
        button.setStyle(
                "-fx-background-radius: 10em; " +
                        "-fx-min-width: 7px; " +
                        "-fx-min-height: 7px; " +
                        "-fx-max-width: 7px; " +
                        "-fx-max-height: 7px;" +
                        "-fx-background-color: red"
        );
    }

    private City getCityCoordinates(String line) {
        StringTokenizer stringTokenizer = new StringTokenizer(line,",");
        String name = stringTokenizer.nextToken();
        int x_axis = Integer.parseInt(stringTokenizer.nextToken());
        int y_axis = Integer.parseInt(stringTokenizer.nextToken());
        return new City(name,x_axis,y_axis);
    }

    @FXML
    private void findShortestPath(ActionEvent event) {
        anchorPane.getChildren().remove(line_group);
        line_group.getChildren().clear();
        String source_town = src_id.getText();
        String destination_town = dest_id.getText();
        clearAllNodes(arr_towns);
        ans_area.setText(aStar(findCityNode(source_town),findCityNode(destination_town)));
        src_id.setText("");
        dest_id.setText("");
    }

    private void clearAllNodes(ArrayList<Node> arr_towns) {
        for (int i = 0; i < arr_towns.size(); i++){
            arr_towns.get(i).setDistance_from_first_node(0);
            arr_towns.get(i).setCost(0);
            arr_towns.get(i).setParent(null);
        }
    }

    private Node findCityNode(String town) {
        for (Node node : arr_towns){
            if (node.getCity().getCity_name().equalsIgnoreCase(town)){
                return node;
            }
        }
        return null;
    }

    private String aStar(Node source, Node destination) {
        PriorityQueue<Node> openQueue = new PriorityQueue<>();
        PriorityQueue<Node> finishedQueue = new PriorityQueue<>();

        source.setCost(0);
        openQueue.add(source);
        while (!openQueue.isEmpty()){
            Node current = openQueue.poll();
            if (current.getCity().getCity_name().equalsIgnoreCase(destination.getCity().getCity_name())){
                return create_path(current);
            }
            else{
                for (int i = 0; i < current.getAdjacent().size(); i++){
                    Node adjacent = current.getAdjacent().get(i).getNode();
                    double total_weight = current.getDistance_from_first_node() + current.getAdjacent().get(i).getWeight();
                    if (!openQueue.contains(adjacent) && !finishedQueue.contains(adjacent)){
                        adjacent.setParent(current);
                        adjacent.setDistance_from_first_node(total_weight);
                        adjacent.setCost(adjacent.getDistance_from_first_node() + adjacent.calculateDistanceToTarget(destination));
                        openQueue.add(adjacent);
                    }
                    else{
                        if (adjacent.getDistance_from_first_node() > total_weight){
                            adjacent.setParent(current);
                            adjacent.setDistance_from_first_node(total_weight);
                            adjacent.setCost(adjacent.getDistance_from_first_node() + adjacent.calculateDistanceToTarget(destination));
                            if (finishedQueue.contains(adjacent)){
                                finishedQueue.remove(adjacent);
                                openQueue.add(adjacent);
                            }
                        }
                    }
                }
            }
            finishedQueue.add(current);
        }
        return create_path(null);
    }

    private String create_path(Node final_node) {
        if (final_node == null){
            return "No Path Available";
        }
        double distance_travelled = final_node.getDistance_from_first_node();
        ArrayList<Node> arr_result_city = new ArrayList<>();

        Node temp = final_node;
        while (temp.getParent() != null){
            arr_result_city.add(temp);
            line_group.getChildren().addAll(draw_line_path(temp,temp.getParent()));
            temp = temp.getParent();
        }
        arr_result_city.add(temp);
        Collections.reverse(arr_result_city);

        anchorPane.getChildren().addAll(line_group);
        ans_area.setText(String.valueOf(distance_travelled));


        return cities_result(arr_result_city);
    }

    private Line draw_line_path(Node temp, Node parent) {

        //draw
        Line line=new Line(temp.getCity().getX_axis(),temp.getCity().getY_axis(),parent.getCity().getX_axis(),parent.getCity().getY_axis());
        return line;
    }

    private String cities_result(ArrayList<Node> arr_result_city) {
        String s = "Start\n";
        for (Node node: arr_result_city){
            s = s + "City: " +node.getCity().getCity_name() + " \nDistance: " + node.getDistance_from_first_node() + "\n |\nv " + "\n******************\n";
        }
        s = s + "END ";
        return s;
    }
}
