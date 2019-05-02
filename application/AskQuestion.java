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
import javafx.scene.control.ToggleGroup;
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

	public AskQuestion(Stage curr_stage, QuizBank quizbank, String topic, int requested_size) {
		this.curr_stage = curr_stage;
		this.qb = quizbank;
		this.selected_questions = qb.getRandomN(topic, requested_size);
		this.next_button = new Button("Next Question");
		this.results_button = new Button("Results");
		this.num_taken = 0;
		this.num_correct = 0;
		this.total_num = this.selected_questions.size();
		this.next_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				nextHandler();
			}
		});
		this.results_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				resultsHandler();
			}
		});

		this.is_correct = new Label("");
	}

	private void nextHandler() {
		Scene next_q_scene = null;
		if (this.num_taken != this.total_num - 1) {
			next_q_scene = AskQScene(this.selected_questions.get(this.num_taken), this.next_button);
		} else {
			next_q_scene = AskQScene(this.selected_questions.get(this.num_taken), this.results_button);
		}
		this.num_taken++;
		this.curr_stage.setScene(next_q_scene);
	}

	private HBox midHbox(VBox left, VBox right) {
		HBox hb = new HBox(15);
		hb.setAlignment(Pos.CENTER);
		hb.getChildren().addAll(left, right);
		return hb;
	}

	private VBox rbVbox(List<Choice> choices) {
		VBox vb = new VBox(10);
		List<RadioButton> rbs = new ArrayList<RadioButton>();
		ToggleGroup group = new ToggleGroup();
		for (Choice c : choices) {
			RadioButton rb = new RadioButton(c.choice);
			rb.setToggleGroup(group);
			rb.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
			rb.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					radioButtonHandler(rbs, choices);
				}
			});
			rbs.add(rb);
			VBox.setMargin(rb, new Insets(10, 10, 10, 10));
		} // rbs is now list of N buttons with labels 0,...,N-1
		vb.getChildren().addAll(rbs);
		return vb;
	}

	private VBox detailsVbox(Question q, Button button) {
		VBox vb = new VBox(10);
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
				vb.getChildren().add(imageView);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// quiz completeness info -- x/n
		Label completeness = new Label(this.num_taken + 1 + " / " + this.total_num);
		completeness.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		vb.getChildren().add(completeness);

		this.is_correct.setVisible(false);
		this.is_correct.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		vb.getChildren().add(this.is_correct);

		return vb;
	}

	public Scene AskQScene(Question q, Button button) {
		VBox main_vb = new VBox(15);
		main_vb.setAlignment(Pos.CENTER);

		// title - top of main_vb
		int k = this.num_taken + 1;
		Label title = new Label("Question " + k);
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		// VBox.setMargin(title, new Insets(20, 0, 40, 0));
		main_vb.getChildren().add(title);

		// question text - 2nd to top of main_vb
		Label text = new Label(q.getQuestion());
		text.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		// VBox.setMargin(text, new Insets(20, 0, 20, 0));
		main_vb.getChildren().add(text);

		VBox rb_vb = rbVbox(q.getChoices());
		VBox details = detailsVbox(q, button);
		HBox mid_hb = midHbox(rb_vb, details);

		main_vb.getChildren().add(mid_hb);

		button.setStyle("-fx-font-size:30");
		button.setMaxSize(300, 125);
		button.setMinSize(300, 125);
		main_vb.getChildren().add(button);

		Scene scene = new Scene(main_vb, 800, 700);
		return scene;
	}

	private void resultsHandler() {
		if (this.num_taken >= this.total_num) {
			// DISPLAY FINAL WINDOWS OR CALL TO THAT CLASS, ETC
			QuizComplete quizComplete = new QuizComplete();
			this.curr_stage.setScene(quizComplete.QuizCompleteScene(curr_stage, num_correct, num_taken, this.qb));
		}
	}

	public void beginQuiz() {
		nextHandler();
	}

	/**
	 * If the radio button selected corresponds to the correct choice then it
	 * displays correct, else incorrect.
	 * 
	 * @param rbs
	 * @param choices
	 */
	private void radioButtonHandler(List<RadioButton> rbs, List<Choice> choices) {
		this.is_correct.setVisible(true);
		int k = 0;
		for (int i = 0; i < rbs.size(); i++) {
			if (rbs.get(i).isSelected()) {
				if (choices.get(i).isCorrect.contentEquals("T")) {
					k = 1;
					this.is_correct.setText("Correct!");
					this.num_correct += 1;
				}
			}
		}
		if (k != 1) {
			this.is_correct.setText("Incorrect.");
		}
		this.is_correct.setVisible(true);
	}

}