package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AskQuestion {

	private Button next_button;

	public AskQuestion() {
		this.next_button = new Button("Next Q");
		this.next_button.setStyle("-fx-font-size:30");
		this.next_button.setMaxSize(200, 75);
		this.next_button.setMinSize(200, 75);
	}

	/*
	 * Create ask question page
	 */
	public Scene AskQuestion(Stage stage, Question q, int qNum) {

		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox, 900, 700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		// Title of ask question page
		Label title = new Label("Question " + qNum + ":");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		VBox.setMargin(title, new Insets(20, 0, 70, 0));

		// question of ask question page
		Label question = new Label(q.getQuestion());
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		VBox.setMargin(title, new Insets(20, 0, 70, 0));

		/*
		// Next button
		Button next_button = new Button("Next");
		next_button.setStyle("-fx-font-size:30");
		next_button.setMaxSize(200, 75);
		next_button.setMinSize(200, 75);
		next_button.setPrefSize(200, 75);
		VBox.setMargin(next_button, new Insets(70, 0, 40, 0));
		*/
		
		// gets list of choices for questions
		List<Choice> choicesList = q.getChoices();

		/*
		// save choice as string for button use
		String buttonA = choicesList.get(0).toString();
		String buttonB = choicesList.get(1).toString();
		String buttonC = choicesList.get(2).toString();
		String buttonD = choicesList.get(3).toString();
		String buttonE = choicesList.get(4).toString();
		*/
		
		/*
		// create radio buttons for multiple choice,
		// if choice null, button is not created 
		RadioButton radioA, radioB, radioC, radioD, radioE; 
		if (!buttonA.equals(null))
			radioA = new RadioButton(buttonA);
			// add to VBox 
		if (!buttonB.equals(null))
			radioB = new RadioButton(buttonB);
		if (!buttonC.equals(null))
			radioC = new RadioButton(buttonC);
		if (!buttonD.equals(null))
			radioD = new RadioButton(buttonD);
		if (!buttonE.equals(null))
			radioE = new RadioButton(buttonE);
		*/
		
		List<RadioButton> rbs = new ArrayList<RadioButton>();
		for (Choice c : choicesList) {
			rbs.add(new RadioButton(c.choice));
		} //rbs is now list of N buttons with labels 0,...,N-1
		
		int count = 0;
		for (RadioButton rb : rbs) {
			if (rb.isSelected()) {
				if (q.getChoices().get(count).isCorrect) {
					//USER ENTERED CORRECT ANSWER
				}
				else {
					//USER ENTERED INCORRECT ANSWER
				}
			}
			count++;
		} 
		
	    this.next_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	
            }
    	});
		
		return scene;
	}
}