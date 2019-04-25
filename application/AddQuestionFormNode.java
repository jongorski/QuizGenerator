package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddQuestionFormNode {
	
	public static VBox leftPane() {
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		
		//Enter topic of new question
		TextField topic = new TextField();
		topic.setPromptText("Topic of new question");
		topic.setStyle("-fx-font-size:15");
		topic.setMinSize(200, 100);
		topic.setMaxSize(200, 100);
		VBox.setMargin(topic, new Insets(0, 50, 40, 50));
		
		//Enter text of new question
		TextArea text = new TextArea();
		text.setPromptText("Text of new question");
		text.setStyle("-fx-font-size:15");
		text.setMinSize(250, 200);
		text.setMaxSize(250, 200);
		VBox.setMargin(text, new Insets(0, 50, 40, 50));
		
		vbox.getChildren().add(topic);
		vbox.getChildren().add(text);
		return vbox;
	}
	
	public static VBox middlePane() {
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		
		//Answer choice 1
		TextField ans1 = new TextField();
		ans1.setPromptText("Answer choice #1:");
		ans1.setStyle("-fx-font-size:15");
		ans1.setMinSize(200, 50);
		ans1.setMaxSize(200, 50);
		ans1.setPrefSize(200, 50);
		VBox.setMargin(ans1, new Insets(0, 0, 40, 0));
		//Answer choice 2
		TextField ans2 = new TextField();
		ans2.setPromptText("Answer choice #2:");
		ans2.setStyle("-fx-font-size:15");
		ans2.setMinSize(200, 50);
		ans2.setMaxSize(200, 50);
		ans2.setPrefSize(200, 50);
		VBox.setMargin(ans2, new Insets(0, 0, 40, 0));
		//Answer choice 3
		TextField ans3 = new TextField();
		ans3.setPromptText("Answer choice #3:");
		ans3.setStyle("-fx-font-size:15");
		ans3.setMinSize(200, 50);
		ans3.setMaxSize(200, 50);
		ans3.setPrefSize(200, 50);
		VBox.setMargin(ans3, new Insets(0, 0, 40, 0));
		//Answer choice 4
		TextField ans4 = new TextField();
		ans4.setPromptText("Answer choice #4:");
		ans4.setStyle("-fx-font-size:15");
		ans4.setMinSize(200, 50);
		ans4.setMaxSize(200, 50);
		ans4.setPrefSize(200, 50);
		VBox.setMargin(ans4, new Insets(0, 0, 40, 0));
		//Answer choice 5
		TextField ans5 = new TextField();
		ans5.setPromptText("Answer choice #5:");
		ans5.setStyle("-fx-font-size:15");
		ans5.setMinSize(200, 50);
		ans5.setMaxSize(200, 50);
		ans5.setPrefSize(200, 50);
		VBox.setMargin(ans5, new Insets(0, 0, 40, 0));
		
		vbox.getChildren().add(ans1);
		vbox.getChildren().add(ans2);
		vbox.getChildren().add(ans3);
		vbox.getChildren().add(ans4);
		vbox.getChildren().add(ans5);

		return vbox;
	}
	
	public static VBox rightPane(Stage currentStage) {
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		
		//Enter topic of new question
		TextField ans_choice = new TextField();
		ans_choice.setPromptText("Correct answer");
		ans_choice.setStyle("-fx-font-size:15");
		ans_choice.setMinSize(130, 50);
		ans_choice.setMaxSize(130, 50);
		VBox.setMargin(ans_choice, new Insets(40, 40, 80, 40));
		
		//Add another question button
		Button another = new Button("Add another");
		another.setStyle("-fx-font-size:15");
		another.setMinSize(150, 60);
		another.setMaxSize(150, 60);
	    another.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	currentStage.close();
            	currentStage.show();
            }
    	});
		VBox.setMargin(another, new Insets(30, 25, 10, 25));
		
		//Submit question button
		Button submit = new Button("Submit question");
		submit.setStyle("-fx-font-size:15");
		submit.setMinSize(150, 60);
		submit.setMaxSize(150, 60);
	    submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	currentStage.close();
            }
    	});
		VBox.setMargin(submit, new Insets(10, 25, 90, 25));
		
		vbox.getChildren().add(ans_choice);
		vbox.getChildren().add(another);
		vbox.getChildren().add(submit);
		
		return vbox;
	}
	
	public static Stage AddQuestion() {
		Stage stage = new Stage();
		HBox hbox = new HBox();
		Scene add_q_scene = new Scene(hbox, 800, 600);
		
		hbox.getChildren().add(leftPane());
		hbox.getChildren().add(middlePane());
		hbox.getChildren().add(rightPane(stage));

		stage.setScene(add_q_scene);
		return stage;
	}

}
