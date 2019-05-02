package application;

import java.util.ArrayList;
import java.util.List;

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
	
	public QuizBank qb;

	/*
	 * constructor method for AddQuestionFormNode
	 */
	public AddQuestionFormNode(QuizBank quizbank) {
	    this.qb = quizbank;
	}
	
	/*
	 * Helper function to submit form 
	 * 
	 * topic: javafx TextField where user enters question topic
	 * text: javafx TextArea where user enters question text
	 * ans: array of javafx TextField where user enters question answer options
	 * choice: javafx TextArea where user enters correct answer choice
	 * img_path: javafx TextArea where user enters path to assoc image
	 */
	public void getSubmittedQuestion(TextField[] choices, TextField answer, TextField meta,
			TextField topic, TextArea text, TextField img_path) {
		String q_meta = meta.getText();      //string metadata
		String q_topic = topic.getText();    //string topic
		String q_text = text.getText();      //string text
		String q_img = img_path.getText();   //string image path
		String q_answer = answer.getText();  //string answer idx
		//Take only non-null answer choices
		List<Choice> q_choices = new ArrayList<Choice>();
		for (int i=0; i<choices.length; i++) {
			if (i == Integer.parseInt(q_answer)) {
				q_choices.add(new Choice("T", q_text));  //mark correct choice as T
			}
			else {
				q_choices.add(new Choice("F", q_text));
			}
		}
		//create new question to add to quizbank
		Question question = new Question(q_topic, q_text,
				 q_choices, q_meta, q_img);
		this.qb.addQuestionToQuiz(question);
		System.out.println(q_topic);
		System.out.println(this.qb.topics);
	}
			
	
	/*
	 * Set up main stage for add question page
	 * return stage to be displayed when adding question
	 */
	public Stage AddQuestion() {
		Stage currentStage = new Stage();
		HBox hbox = new HBox();
		Scene add_q_scene = new Scene(hbox, 800, 600);
		
		VBox leftPane = new VBox();
		leftPane.setAlignment(Pos.CENTER);
		
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
		
		leftPane.getChildren().add(topic);
		leftPane.getChildren().add(text);
		
		VBox midPane = new VBox();
		midPane.setAlignment(Pos.CENTER);
		
		//Answer choice 1
		TextField ans1 = new TextField();
		ans1.setPromptText("Answer choice #1:");
		ans1.setStyle("-fx-font-size:15");
		ans1.setMinSize(200, 50);
		ans1.setMaxSize(200, 50);
		VBox.setMargin(ans1, new Insets(0, 0, 30, 0));
		//Answer choice 2
		TextField ans2 = new TextField();
		ans2.setPromptText("Answer choice #2:");
		ans2.setStyle("-fx-font-size:15");
		ans2.setMinSize(200, 50);
		ans2.setMaxSize(200, 50);
		VBox.setMargin(ans2, new Insets(0, 0, 30, 0));
		//Answer choice 3
		TextField ans3 = new TextField();
		ans3.setPromptText("Answer choice #3:");
		ans3.setStyle("-fx-font-size:15");
		ans3.setMinSize(200, 50);
		ans3.setMaxSize(200, 50);
		VBox.setMargin(ans3, new Insets(0, 0, 30, 0));
		//Answer choice 4
		TextField ans4 = new TextField();
		ans4.setPromptText("Answer choice #4:");
		ans4.setStyle("-fx-font-size:15");
		ans4.setMinSize(200, 50);
		ans4.setMaxSize(200, 50);
		VBox.setMargin(ans4, new Insets(0, 0, 30, 0));
		//Answer choice 5
		TextField ans5 = new TextField();
		ans5.setPromptText("Answer choice #5:");
		ans5.setStyle("-fx-font-size:15");
		ans5.setMinSize(200, 50);
		ans5.setMaxSize(200, 50);
		VBox.setMargin(ans5, new Insets(0, 0, 30, 0));
		
		TextField[] choices = new TextField[] {ans1, ans2, ans3, ans4, ans5};
		
		midPane.getChildren().add(ans1);
		midPane.getChildren().add(ans2);
		midPane.getChildren().add(ans3);
		midPane.getChildren().add(ans4);
		midPane.getChildren().add(ans5);
		
		VBox rightPane = new VBox();
		rightPane.setAlignment(Pos.CENTER);
		
		//Enter answer choice of new question
		TextField ans_choice = new TextField();
		ans_choice.setPromptText("Correct answer");
		ans_choice.setStyle("-fx-font-size:15");
		ans_choice.setMinSize(130, 50);
		ans_choice.setMaxSize(130, 50);
		VBox.setMargin(ans_choice, new Insets(40, 40, 10, 40));
		
		//Enter image file of new question
		TextField img_file = new TextField();
		img_file.setPromptText("Image file");
		img_file.setStyle("-fx-font-size:15");
		img_file.setMinSize(130, 50);
		img_file.setMaxSize(130, 50);
		VBox.setMargin(img_file, new Insets(10, 40, 50, 40));
		
		//Enter metadata of new question
		TextField meta = new TextField();
		meta.setPromptText("Metadata");
		meta.setStyle("-fx-font-size:15");
		meta.setMinSize(130, 50);
		meta.setMaxSize(130, 50);
		VBox.setMargin(meta, new Insets(10, 40, 50, 40));
		
		//Add another question button
		Button another = new Button("Submit & Add another");
		another.setStyle("-fx-font-size:15");
		another.setMinSize(150, 60);
		another.setMaxSize(150, 60);
	    another.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	getSubmittedQuestion(choices, ans_choice, meta, topic, text, img_file);
            	topic.clear();
            	text.clear();
            	for (int i=0; i<choices.length; i++) { choices[i].clear(); }
            	ans_choice.clear();
            	img_file.clear();
            	meta.clear();
            	currentStage.close();
            	currentStage.show();
            }
    	});
		VBox.setMargin(another, new Insets(30, 25, 10, 25));
		
		another.setStyle("-fx-font-size:15");
		another.setMinSize(150, 60);
		another.setMaxSize(150, 60);

		Button submit = new Button("Submit & Close");
		submit.setStyle("-fx-font-size:15");
		submit.setMinSize(150, 60);
		submit.setMaxSize(150, 60);
	    submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
              getSubmittedQuestion(choices, ans_choice, meta, topic, text, img_file);
              currentStage.close();
            }
    	});
		VBox.setMargin(submit, new Insets(10, 25, 90, 25));
		
		rightPane.getChildren().add(ans_choice);
		rightPane.getChildren().add(img_file);
		rightPane.getChildren().add(meta);
		rightPane.getChildren().add(another);
		rightPane.getChildren().add(submit);
		

		
		hbox.getChildren().add(leftPane);
		hbox.getChildren().add(midPane);
		hbox.getChildren().add(rightPane);

		currentStage.setTitle("Add a new question");
		currentStage.setScene(add_q_scene);
		return currentStage;
	}
	
	public QuizBank getQB() { return this.qb; }
	
	public static QuizBank runAddQ(QuizBank qb) {
		AddQuestionFormNode addQ = new AddQuestionFormNode(qb);
		Stage addPopup = addQ.AddQuestion();
		addPopup.show();
		System.out.println(addQ.qb.topics);
		return addQ.qb;
	}
}
