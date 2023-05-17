package application;
	
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
//import javafx.collections.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.application.Application;
//import javafx.collections.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.ComboBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		System.out.println("Hellllllll");
		try {
			AnchorPane	 root  = FXMLLoader.load(getClass().getResource("zeina.fxml"));
		     Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Palestine Shortest Path");
			primaryStage.show();
             
			 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		launch(args);
		String filePath = "city.txt";
        readFile get_data = new readFile(filePath) ;
        ArrayList <Node>Graph = get_data.read_file() ;
        HashMap<String,Integer>mp = get_data.getMp() ;
        HashMap<String,HashMap<String,Double>>des = get_data.getDes() ;
        Algorithims test = new Algorithims(Graph,mp,"Astar","Jenin".toLowerCase(),"Ramallah".toLowerCase(),'u','w',des);

        ArrayList<Edges>Path = test.Runner();
        System.out.println("-----------------------------------------------------------------");

       // test1.Runner();

        double dest = 0 ;
       for(Edges i : Path)
       {
           dest+=i.getDestance();
       //    System.out.print(i.getNode().getName()+" "+i.getDestance()+"----->");
       }

       // System.out.println("\n"+dest);

//
 }
	}

