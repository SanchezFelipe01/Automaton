package userInterface;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

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
	private StackPane centralPane2;

	@FXML
	private GridPane grid;
	
	@FXML
	private GridPane grid2;

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
		
		ArrayList<State> list = auto.getReducedAutomaton();
		
	}

	@FXML
	void save_Button(ActionEvent event) {

		readStates();

		String line2 = input_TextField.getText();
		String[] inputs = line2.split(",");

		String line3 = output_TextField.getText();
		String[] outputs = line3.split(",");

		char[] stimuli = castArray(inputs);
		char[] responses = castArray(outputs);

		String type;
		ArrayList<State> states;

		if (mooreOption.isSelected()) {
			type = Automaton.MOORE;
			states = buildStatesMoore();
		}else {
			type = Automaton.MEALY;
			states = buildStatesMealy(stimuli.length);
		}


		auto = new Automaton(type, states, stimuli, responses);


	}

	private ArrayList<State> buildStatesMealy(int size){

		ArrayList<State> list = new ArrayList<State>();
		HashMap<String, State> map = new HashMap<>();

		for (int i = 1; i < matrix.length; i++) {
			String[] c = new String[size];
			for (int j = 1; j < matrix[i].length; j++) {

				String[] array = matrix[i][j].split(",");
				c[j-1] = array[1];
			}

			char[] cc = castArray(c);

			String name = matrix[i][0];
			State s = new State(name, cc);
			map.put(name, s);
			list.add(s);

		}

		for (int i = 0; i < matrix.length-1; i++) {

			for (int j = 0; j < matrix[i].length-1; j++) {

				String[] array = matrix[i+1][j+1].split(",");
				String name = array[0];
				State s = map.get(name);

				list.get(i).addSuccessor(s);

			}

		}
		String message = "";
		for (int i = 0; i < list.size(); i++) {
			State s = list.get(i);
			message += s.getName() + " = ";
			for (int j = 0; j < s.getSuccessorStates().size(); j++) {
				message += s.getSuccessorStates().get(j).getName() + s.getResponses()[j] + " ";
			}
			message += "\n";
		}

		System.out.println(message);

		return list;
	}

	private ArrayList<State> buildStatesMoore(){

		ArrayList<State> list = new ArrayList<State>();
		HashMap<String, State> map = new HashMap<>();
		
		System.out.println(matrix.length);
		System.out.println(matrix[1].length);
		
		for (int i = 1; i < matrix.length; i++) {
			
			String c = matrix[i][matrix[i].length-1];
			char ch = c.charAt(0);
			
			char[] cc = new char[] {ch};

			String name = matrix[i][0];
			State s = new State(name, cc);
			map.put(name, s);
			list.add(s);

		}

		for (int i = 0; i < matrix.length-1; i++) {
			System.out.print(list.get(i).getName() + " ");
			for (int j = 1; j < matrix[i].length-1; j++) {

				String name = matrix[i+1][j];
				State s = map.get(name);
				System.out.print(name + " ");
				list.get(i).addSuccessor(s);

			}
			
			System.out.println();

		}
		
		String message = "";
		for (int i = 0; i < list.size(); i++) {
			State s = list.get(i);
			
			message += s.getName() + " = ";
			for (int j = 0; j < s.getSuccessorStates().size(); j++) {
				message += s.getSuccessorStates().get(j).getName() + " ";
			}
			message += s.getResponses()[0] + "\n";
		}

		System.out.println(message);

		return list;

	}

	private char[] castArray(String[] array) {

		char[] chars = new char[array.length];

		for (int i = 0; i < array.length; i++) {
			char c = array[i].charAt(0);
			chars[i] = c;
		}

		return chars;

	}

	private void readStates() {

		for (Node node : grid.getChildren()) {

			int col = GridPane.getColumnIndex(node);
			int row = GridPane.getRowIndex(node);

			TextField tf = (TextField)node;
			String s = tf.getText();
			matrix[row][col] = s;	
			
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
			grid.add(tf, 0, i+1);
		}

		for (int i = 0; i < inputs.length; i++) {
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












