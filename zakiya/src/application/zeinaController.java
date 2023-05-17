package application;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;




public class zeinaController {
    Group group = new Group();

	ObservableList<String> palestinecities = FXCollections.observableArrayList("Salfit", "Nablus", "Ramallah", "Jenin",
			"Jerusalem", "Tulkarem", "jericho", "Bethlehem","Yafa", "Acre", "Tubas", "Ramlah",
			"Hebron", "Qalqelya","Safad", "Nazareth",
			"Sabastia", "Haifa","Halhoul" , "Dura");

	ObservableList<String> palestinecities2 = FXCollections.observableArrayList("Salfit", "Nablus", "Ramallah", "Jenin",
			"Jerusalem", "Tulkarem", "jericho", "Bethlehem","Yafa", "Acre", "Tubas", "Ramlah",
			"Hebron",  "Qalqelya","Safad", "Nazareth",
			"Sabastia", "Haifa","Halhoul","Dura");
	
	ObservableList<String> Algorithims = FXCollections.observableArrayList("DFS","Uniform cost","Astar" , "BFS" , "Greedy");
	ObservableList<String> TypeOfHurstic  = FXCollections.observableArrayList("Walking Distance" , "Aerial Distance");
	   @FXML
	    private Pane pane;
	  @FXML
	    private ComboBox<String> hurstic;
	  
	    @FXML
	    private ComboBox<String> algo;

	    @FXML
	    private ComboBox<String> dest_combo;

	    @FXML
	    private TextField dist_text;

	    @FXML
	    private TextField path_text;

	    
	    @FXML
	    private AnchorPane anchar;
	    
	    @FXML
	    private ComboBox<String> source_combo;
	 //   readFile file = new readFile(); 
	 
	  
	    
	 Algorithims algorithms = new Algorithims(); 
	    
	    
	
	    @FXML
		private void initialize() {
	    	
	    	source_combo.setValue("Select");
	    	source_combo.setItems(palestinecities);
	    	algo.setValue("Select");
	    	algo.setItems(Algorithims);
	    	hurstic.setItems(TypeOfHurstic);
	    	hurstic.setValue("Select ");
	    	dest_combo.setValue("Select");
	    	dest_combo.setItems(palestinecities2);
	    	anchar.getChildren().addAll(group); 
	    	
	    	
		}


	     Algorithims test  ;
	     

	    @FXML
	    void run(ActionEvent event) throws FileNotFoundException {
	    	
	        anchar.getChildren().remove(group); 
	        group.getChildren().clear();
	    	path_text.clear();
	    	dist_text.clear();
	    	String filePath = "city.txt";
	        readFile get_data = new readFile(filePath) ;
	        ArrayList <Node>Graph = get_data.read_file() ;
	        HashMap<String,Integer>mp = get_data.getMp() ;
	        HashMap<String,HashMap<String,Double>>des = get_data.getDes() ;
	    //    System.out.println(Graph.get(mp.get("Tulkarem")).getAdj());
	        
	        if (algo.getValue().toString().equals("Astar")) {
	        	if (hurstic.getValue().toString().equals("Walking Distance"))
		        test = new Algorithims(Graph,mp,"Astar" ,source_combo.getValue().toString().toLowerCase() , dest_combo.getValue().toString().toLowerCase(),'w','a',des);
	        	if (hurstic.getValue().toString().equals("Aerial Distance")) {
			        test = new Algorithims(Graph,mp,"Astar" ,source_combo.getValue().toString().toLowerCase() , dest_combo.getValue().toString().toLowerCase(),'s','a',des);

	        	}
	        	
	        }
	        if (algo.getValue().toString().equals("Uniform cost")) {
	        	
		        test = new Algorithims(Graph,mp,"Astar" ,source_combo.getValue().toString().toLowerCase() , dest_combo.getValue().toString().toLowerCase(),'s','u',des);

	 
	        }
    if (algo.getValue().toString().equals("Greedy")) {
	        	
		        test = new Algorithims(Graph,mp,"Astar" ,source_combo.getValue().toString().toLowerCase() , dest_combo.getValue().toString().toLowerCase(),'s','g',des);
	        }
    
    if (algo.getValue().toString().equals("DFS")) {
    	
        test = new Algorithims(Graph,mp,"DFS" ,source_combo.getValue().toString().toLowerCase() , dest_combo.getValue().toString().toLowerCase(),'s','g',des);
    }
    
    
    if (algo.getValue().toString().equals("BFS")) {
    	
        test = new Algorithims(Graph,mp,"BFS" ,source_combo.getValue().toString().toLowerCase() , dest_combo.getValue().toString().toLowerCase(),'s','g',des);
    }
	       
	       ArrayList<Edges>Path = test.Runner();
	        System.out.println("-----------------------------------------------------------------");

	       // test1.Runner();

	        double dest = 0 ;
	        StringBuilder str  = new StringBuilder();
	       for(int i=0 ;i < Path.size()-1;i++)
	       {
	    	   
	    	   Line line = new Line() ;
	    	   line.setStartX(Path.get(i).getNode().getxPlot());
	    	   line.setStartY(Path.get(i).getNode().getyPlot());
	    	   line.setEndX(Path.get(i+1).getNode().getxPlot());
	    	   line.setEndY(Path.get(i+1).getNode().getyPlot());
	    	   if (i%2==0 ) {
	    		    line.setStroke(Color.DARKGOLDENROD);
	    		    line.setStrokeWidth(3);
	    		  
	    	   }
	    	   else {
	    		   line.setStroke(Color.DARKSALMON);
	    		    line.setStrokeWidth(3);
	    		   
	    	   }
	    	   
	    	   
	    	   
	    	   group.getChildren().add(line) ;
		       

	        
	      
	           /*System.out.print(i.getNode().getName()+" "+i.getDestance()+"----->");*/
	       }
	       anchar.getChildren().addAll(group); 
	       
	       for(int i=0 ;i < Path.size();i++)
	       {
	    	   
	    
	           dest+=Path.get(i).getDestance();
	           str.append(Path.get(i).getNode().getName()+"-->");
	      
	           /*System.out.print(i.getNode().getName()+" "+i.getDestance()+"----->");*/
	       }
	       
	     
	       
	       path_text.setText(str.toString());
	       dist_text.setText(Double.toString(dest));
	       // System.out.println("\n"+dest);

	//
	 }
	    	 
	    
	    
	   

}
