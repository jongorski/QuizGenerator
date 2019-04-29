package application;

import java.util.List;

/**
 * Defines the operations required of the Question class.
 * @author Jon Gorski (jgorski2@wisc.edu)
 */
public interface QuestionADT {
	
	/**
	 * This method takes no parameters, and returns the string of the
	 * question's text, which is a field in the Question class.
	 * If String is empty, return null.
	 * @return A string of the question's actual text
	 */
	String getQuestion();
	
	/**
	 * This method takes no parameters, and returns a list containing
	 * Choice objects, which is a field in the Question class. If the list
	 * is null, return null.
	 * @return List of Choice objects
	 */
	List<Choice> getChoices(); 
	
	/**
	 * This method returns the String of the correct choice by traversing
	 * the List of Choice objects and searching for which Choice has a true value
	 * associated with it. Once it finds that Choice object, it returns the string
	 * associated with that Choice.
	 * @return String of the correct Choice
	 */
	String getAnswer();
	
	/**
	 * This method takes no parameters, and returns the String of the Metadata.
	 * String Metadata is a field in the Question class. If the metadata is empty,
	 * return null.
	 * @return String of the metadata
	 */
	String getMetadata();
	
	/**
	 * This method takes no parameters, and returns the String of the topic,
	 * which is a field in the Question class. If the topic if empty, return
	 * null. 
	 * @return String of the Topic
	 */
	String getTopic();
	
	/**
	 * This method takes no parameters, and returns the String of the name 
	 * of the image ("example.jpg"). Image is a field in the Question class.
	 * If there is no image, return null.
	 * @return String name of the image
	 */
	String getImage();
}