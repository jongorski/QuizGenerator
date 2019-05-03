///////////////////////////////////////////////////////////////////////////////
//
// Title:           Question
// Course:          CS400 Spring 2019
// Project:         Team Project 
//
// Author:          Mikel Terracina
// Email:           mterracina@wisc.edu
//
// Lecturer's Name: Andrew Kuemmel
// Lecture Number:  004
//
// Due Date:        2019-05-02
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
// Online Sources: none
// 
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
package application;

import java.util.List;

/**
 * This class represents a Question within the Quiz
 * 
 * @author mterracina
 *
 */
public class Question implements QuestionADT {

  // ************************** FIELDS ******************************************//
  private String metadata; // miscellaneous information about the question
  public String question;
  private String topic;
  private String image;
  private List<Choice> choices;
  public boolean asked;

  // ************************** CONSTRUCTORS **************************************//
  Question(String topic, String question, List<Choice> choices) {
    this.question = question;
    this.topic = topic;
    this.choices = choices; // max number of choices = 5
    this.asked = false;
  }

  Question(String topic, String question, List<Choice> choices, String metadata) {
    this.metadata = metadata;
    this.question = question;
    this.topic = topic;
    this.choices = choices; // max number of choices = 5
    this.asked = false;

  }

  Question(String topic, String question, List<Choice> choices, String metadata, String image) {
    this.metadata = metadata;
    this.question = question;
    this.topic = topic;
    this.image = image;
    this.choices = choices; // max number of choices = 5
    this.asked = false;
  }

  // ************************** METHODS ********************************************//
  /**
   * Returns the String representation of the actual question
   * 
   * @return question
   */
  public String getQuestion() {
    return question;
  }

  /**
   * Returns the available choices a user can pick for an answer
   * 
   * @returns List of Choice objects for this Question
   */
  public List<Choice> getChoices() {
    return choices;
  }

  /**
   * Returns the correct answer for this Question. If not found, returns null
   * 
   * @return correct answer in String format
   */
  public String getAnswer() {
    for (Choice choice : choices) {
      if (choice.getIsCorrect().equals("T")) {
        return choice.getChoice();
      }
    }
    return null;
  }

  /**
   * Returns the metadata for this Question (i.e. miscellaneous information about the question)
   * 
   * @return metadata in String format
   */
  public String getMetadata() {
    return metadata;
  }

  /**
   * Returns the topic that this question is associated with
   * 
   * @return topic in String format
   */
  public String getTopic() {
    return topic;
  }

  /**
   * Returns the name of the image file that is associated with this question
   * 
   * @return name of the image file in String format
   */
  public String getImage() {
    return image;
  }
} // END CLASS
