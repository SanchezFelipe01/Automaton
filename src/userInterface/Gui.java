package userInterface;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import model.Automaton;
import model.State;

public class Gui {

	@FXML
	private RadioButton mealyOption;

	@FXML
	private RadioButton mooreOption;

	@FXML
	private TextField states_TextField;

	@FXML
	private TextField input_TextField;

	@FXML
	private TextField output_TextField;

	@FXML
	private StackPane centralPane;

	@FXML
	private GridPane grid;

	@FXML
	private Button showButtonId;

	@FXML
	private Button saveButtonId;

	@FXML
	private Button connectedButtonId;

	@FXML
	private Button ReducedButtonId;


	private Automaton auto;
	private String[][] matrix;

	public void initialize() {

		ToggleGroup group = new ToggleGroup();
		mealyOption.setToggleGroup(group);
		mooreOption.setToggleGroup(group);

	}

	@FXML
	void new_Button(ActionEvent event) {

	}

	@FXML
	void connected_Button(ActionEvent event) {

	}

	@FXML
	void reduced_button(ActionEvent event) {

	}

	@FXML
	void save_Button(ActionEvent event) {

		readStates();

	}

	private void readStates() {
		
		System.out.println(matrix.length);
		System.out.println(matrix[0].length);
		
		for (Node node : grid.getChildren()) {
			
			int col = GridPane.getColumnIndex(node);
			int row = GridPane.getRowIndex(node);
			
			TextField tf = (TextField)node;
			String s = tf.getText();
			matrix[row][col] = s;
			
		}
		
		for (int i = 0; i < matrix.length; i++) {
			
			for (int j = 0; j < matrix[i].length; j++) {
				
				System.out.print(matrix[i][j] + " ");
				
			}
			
			System.out.println();
		}

	}
	
	
	@FXML
	void show_Button(ActionEvent event) {

		String line = states_TextField.getText();
		String[] states = line.split(",");

		String line2 = input_TextField.getText();
		String[] inputs = line2.split(",");

		mooreOption.setDisable(true);
		mealyOption.setDisable(true);

		states_TextField.setEditable(false);
		input_TextField.setEditable(false);
		output_TextField.setEditable(false);

		saveButtonId.setDisable(false);
		
		if (mooreOption.isSelected()) {
			matrix = new String[states.length+1][inputs.length+2];
		}else {
			matrix = new String[states.length+1][inputs.length+1];
		}
		
		
		showTemplate(states, inputs);

	}

	private void showTemplate(String[] states, String[] inputs) {

		centralPane.getChildren().clear();
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		TextField tf0 = new TextField("-");
		tf0.setDisable(true);
		tf0.setPrefWidth(40);
		grid.add(tf0, 0, 0);

		for (int i = 0; i < states.length; i++) {
			TextField tf = new TextField(states[i]);
			tf.setDisable(true);
			tf.setPrefWidth(40);
			//Label l = new Label(states[i]);
			grid.add(tf, 0, i+1);
		}

		for (int i = 0; i < inputs.length; i++) {
			//Label l = new Label(inputs[i]);
			//l.setPrefWidth(40);
			//l.setAlignment(Pos.CENTER);
			
			TextField tf = new TextField(inputs[i]);
			tf.setDisable(true);
			tf.setPrefWidth(40);
			
			grid.add(tf, i+1, 0);
		}

		for (int i = 0; i < states.length; i++) {
			for (int j = 0; j < inputs.length; j++) {

				TextField tf = new TextField();

				tf.setPrefWidth(40);
				tf.setEditable(true);

				grid.add(tf, j+1, i+1);

			}
		}

		if (mooreOption.isSelected()) {
			int j = inputs.length + 1;
			TextField tfm = new TextField("-");
			tf0.setDisable(true);
			tf0.setPrefWidth(40);
			grid.add(tfm, j, 0);
			for (int i = 0; i < states.length; i++) {

				TextField tf = new TextField();
				

				tf.setPrefWidth(40);
				tf.setEditable(true);

				grid.add(tf, j, i+1);

			}
			
			
		}
		
		centralPane.getChildren().add(grid);

	}

}












