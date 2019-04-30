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
	public Scene AskQuestionScene(Question q, int qNum) {

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

		List<Choice> choicesList = q.getChoices();  //all choices for question
		List<RadioButton> rbs = new ArrayList<RadioButton>();  //radio buttons w labels 0,..,N-1
		
		for (Choice c : choicesList) { 
			rbs.add(new RadioButton(c.choice));
		} 
		
		int count = 0;
		for (RadioButton rb : rbs) {
			if (rb.isSelected()) {
				if (q.getChoices().get(count).isCorrect) {
					System.out.println("USER CORRECT");
				}
				else {
					System.out.println("USER INCORRECT");
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