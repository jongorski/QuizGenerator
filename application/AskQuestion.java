package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import application.QuizBank.topicBucket;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AskQuestion {

	private Stage curr_stage;
	private QuizBank qb;
	private Button next_button;
	private Button results_button;
	private List<Question> selected_questions;
	private int num_taken;
	private int num_correct;
	private int total_num;
	private Label is_correct;
	
	
	/*
	 * constructor method for AskQuestion class
	 * 
	 * @param curr_stage: current stage, used to change scene
	 * @param qb: QuizBank to quiz out of
	 * @param topic: topic of quiz questions to ask
	 * @param num_t0_asl: number of questions to ask
	 */
	public AskQuestion(Stage curr_stage, QuizBank qb, String topic, int requested_size) {
		System.out.println("AskQuestion constr 1");
		this.curr_stage = curr_stage;
		this.qb = qb;
		this.selected_questions = qb.getRandomN(topic, requested_size);
		this.next_button = new Button("Next Question");
		this.results_button = new Button("Results");
		this.num_taken = 0;
		this.num_correct = 0;
		this.total_num = this.selected_questions.size();
		System.out.println("AskQuestion constr 2");
		this.next_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				nextHandler();
			}
		});
		System.out.println("AskQuestion constr 3");
		this.results_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				resultsHandler();
			}
		});
		
		this.is_correct = new Label("");
	}
	
	private void nextHandler() {
		System.out.println("nextHandler 1");
		Scene next_q_scene = null;
		if (this.num_taken != this.total_num-1) {
			System.out.println("nextHandler 2.a");
			next_q_scene = AskQScene(this.selected_questions.get(this.num_taken),
					                 this.next_button);
		}
		else {
			System.out.println("nextHandler 2.b");
			next_q_scene = AskQScene(this.selected_questions.get(this.num_taken),
	                 this.results_button);
		}
		System.out.println("nextHandler 3");
		this.num_taken++;
		this.curr_stage.setScene(next_q_scene);
	}
	

public Scene AskQScene(Question q, Button button) {
		VBox main_vbox = new VBox();
		HBox mid_hbox = new HBox(); // main hbox
		VBox options_vbox = new VBox();
		VBox img_details_button_vbox = new VBox();
		
		this.is_correct.setVisible(false);

		// title - top of main vbox
		Label title = new Label("Question " + this.num_taken + 1 + ":");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		VBox.setMargin(title, new Insets(20, 0, 40, 0));
		main_vbox.getChildren().add(title);

		// text of question - 2nd from top of main vbox
		Label question_text = new Label(q.getQuestion());
		question_text.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		VBox.setMargin(question_text, new Insets(20, 0, 40, 0));
		main_vbox.getChildren().add(question_text);


		// gets list of choices for questions
		List<Choice> choicesList = q.getChoices();

		// create radio buttons for multiple choice
		// add to radio buttons list
		List<RadioButton> rbs = new ArrayList<RadioButton>();
		for (Choice c : choicesList) {
			RadioButton rb = new RadioButton(c.choice);
			rb.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					radioButtonHandler(rbs, choicesList);
				}
			});
			rbs.add(rb);
			VBox.setMargin(rb, new Insets(10, 0, 10, 0));
			options_vbox.getChildren().add(rb);
		} // rbs is now list of N buttons with labels 0,...,N-1


		// image display...
		// https://www.tutorialspoint.com/javafx/javafx_images.htm
		// Loading image from URL if image associated with question (not empty string)
		if (!q.getImage().equals("")) {
			try {
				// Creating an image
				Image image = new Image(new FileInputStream(q.getImage()));
				// Setting the image view
				ImageView imageView = new ImageView(image);
				// Setting the image
				imageView.setImage(image);
				// setting the fit height and width of the image view
				imageView.setFitHeight(250);
				// Setting the preserve ratio of the image view
				imageView.setPreserveRatio(true);
				img_details_button_vbox.getChildren().add(imageView);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// quiz completeness info
		Label details = new Label(this.num_taken + 1 + " / " + this.total_num);
		details.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		VBox.setMargin(details, new Insets(10, 0, 50, 0));
		img_details_button_vbox.getChildren().add(details);
		
		// correct/incorrect label
		this.is_correct.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		VBox.setMargin(this.is_correct, new Insets(10, 0, 50, 0));
		img_details_button_vbox.getChildren().add(this.is_correct);
		
		mid_hbox.getChildren().add(options_vbox);
		mid_hbox.getChildren().add(img_details_button_vbox);

		main_vbox.getChildren().add(mid_hbox);
		
		// next/results button
		button.setStyle("-fx-font-size:30");
		button.setMaxSize(300, 125);
		button.setMinSize(300, 125);
		
		Scene scene = new Scene(main_vbox, 800, 700);
		return scene;
	}

	
	private void resultsHandler() {
		if (this.num_taken > this.total_num) {
			//DISPLAY FINAL WINDOWS OR CALL TO THAT CLASS, ETC
		}
	}
	
	public void beginQuiz() {
		System.out.println("AskQuestion beginQuiz");
		nextHandler();
	}
	
	private void radioButtonHandler(List<RadioButton> rbs, List<Choice> choices) {
		int k = 0;
		for (int i = 0; i < rbs.size(); i++) {
			if (rbs.get(i).isSelected()) {
				if (choices.get(i).isCorrect.contentEquals("T")) {
					k = 1;
					this.is_correct.setText("Correct!");
				}
			}
		}
		if (k != 1) {
			this.is_correct.setText("Incorrect.");
		}
		this.is_correct.setVisible(true);
	}
	
}