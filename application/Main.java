///////////////////////////////////////////////////////////////////////////////
//
// Title: Quiz Generator
// Course: CS400 Spring 2019
// Project: Team Project
//
// Author: Mikel Terracina, Danielle Hart, Jon Gorski, Jack Wolf
// Email: mterracina@wisc.edu, dahart2@wisc.edu, 
//        jongorski2@wisc.edu, jwolf22@wisc.edu
//
// Lecturer's Name: Andrew Kuemmel & Deb Deppler
// Lecture Number: 004 & 001
//
// Due Date: 2019-05-02
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
// Online Sources: none
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
package application;

import java.util.ArrayList;
import java.util.List;

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

/**
 * Main class of quiz generator
 * @author daniellehart
 *
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			QuizStart.startQuiz(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}