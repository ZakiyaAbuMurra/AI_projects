package application;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

//import java.net.URL;
//import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
//import javafx.fxml.Initializable;

public class InterfaceController {

	ObservableList<String> palestinecities = FXCollections.observableArrayList("Salfit", "Nablus", "Ramallah", "Jenin",
			"Jerusalem", "Tulkarm", "Jerico", "Bethlehem", "jaffa", "Acre", "Tubas", "Ramla", "Ashkelon", "Gaza",
			"Hebron", "Rafah", "Khan Younes", "Beersheba", "Qalqilya","Safed", "Galilee", "Nazareth",
			"Tiberius", "Haifa", "Besan");

	ObservableList<String> palestinecities2 = FXCollections.observableArrayList("Salfit", "Nablus", "Ramallah", "Jenin",
			"Jerusalem", "Tulkarm", "Jerico", "Bethlehem", "jaffa", "Acre", "Tubas", "Ramla", "Ashkelon", "Gaza",
			"Hebron", "Rafah", "Khan Younes", "Beersheba", "Qalqilya","Safed", "Galilee", "Nazareth",
			"Tiberius", "Haifa", "Besan");

	@FXML
	private ComboBox<String> FromBox;

	@FXML
	private ComboBox<String> ToBox;

	@FXML
	private AnchorPane main_pane;

	@FXML
	private Label lable_num1;

	@FXML
	private Label lable_num2;
	  
	@FXML
	  private Button start_btn;
	
	 @FXML
	    private Label dis_label;
	 
	  @FXML
	    private Label path_label;


	@FXML
	private void initialize() {
		FromBox.setValue("Select");
		FromBox.setItems(palestinecities);

		ToBox.setValue("Select");
		ToBox.setItems(palestinecities2);
	}

}
