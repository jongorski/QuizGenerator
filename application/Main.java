package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Main extends Application {
	
	/*
	 * Create initial GUI scene
	 */
	public Scene InitalSetup(QuizBank qb, Stage curr_stage, Scene next_scene) {
		VBox vbox = new VBox();
	    vbox.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vbox, 900, 700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		//Add question button
		Button add_q = new Button("Add question");
		add_q.setStyle("-fx-font-size:15");
		add_q.setMinSize(150, 60);
		add_q.setPrefSize(150, 60);
		AddQuestionFormNode addQ = new AddQuestionFormNode();
		Stage add_popup_stage = addQ.AddQuestion(qb);
	    add_q.setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	            	add_popup_stage.show();
	            }
	    	});
		VBox.setMargin(add_q, new Insets(0, 0, 30, 0));
		
		//Move on button
		Button next = new Button("Move on!");
		next.setStyle("-fx-font-size:15");
		next.setMinSize(300, 125);
		next.setPrefSize(300, 125);
	    next.setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	            	curr_stage.setScene(next_scene);
	            }
	    	});
		VBox.setMargin(next, new Insets(0, 0, 30, 0));
		
		vbox.getChildren().add(add_q);
		vbox.getChildren().add(next);

		return scene;
	}
	
	/*
	 * Create secondary main GUI scene
	 */
	public Scene SecondSetup() {
		
		VBox vbox = new VBox();
	    vbox.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(vbox, 900, 700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		//Title of main page
		Label title = new Label("QuizGenerator");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		VBox.setMargin(title, new Insets(20, 0, 70, 0));
		
		
		//Start button
		Button start_button = new Button("Start quiz!");
		start_button.setStyle("-fx-font-size:30");
		start_button.setMaxSize(300, 125);
		start_button.setMinSize(300, 125);
		VBox.setMargin(start_button, new Insets(70, 0, 40, 0));
		
		//Topic list
        String topics[] = {"Math", "Comp Sci", "English", 
        		"History", "PE", "Photo" };
        ComboBox<String> topic_box = new ComboBox<String>(FXCollections
        		                    .observableArrayList(topics));
        topic_box.setPromptText("Topics");
		topic_box.setStyle("-fx-font-size:15");
        topic_box.setMinSize(225, 75);
		topic_box.setMaxSize(225, 75);
		topic_box.setPrefSize(225, 75);
		VBox.setMargin(topic_box, new Insets(20, 0, 20, 0));

		
		//Choose number of questions
		TextField num_q = new TextField();
		num_q.setPromptText("Enter # questions");
		num_q.setStyle("-fx-font-size:15");
		num_q.setMinSize(225, 75);
        num_q.setMaxSize(225, 75);
		VBox.setMargin(num_q, new Insets(20, 0, 20, 0));

		vbox.getChildren().add(title);
		vbox.getChildren().add(topic_box);
		vbox.getChildren().add(num_q);
		vbox.getChildren().add(start_button);
        
		return scene;
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			QuizBank qb = new QuizBank();
			Scene scene = SecondSetup();
			
			Scene secondary_scene = SecondSetup();
			Scene initial_scene = InitalSetup(qb, primaryStage, secondary_scene);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(initial_scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
