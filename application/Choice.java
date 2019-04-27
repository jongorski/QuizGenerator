///////////////////////////////////////////////////////////////////////////////
//
// Title:           Choice
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

/**
 * This class represents an individual choice in regards to multiple choices for a Question
 * 
 * @author mterracina
 *
 */
public class Choice {

  //************************** FIELDS ******************************************//
  public boolean isCorrect;
  public String choice;

  //************************** CONSTRUCTORS **************************************//
  Choice(boolean isCorrect, String choice) {
    this.isCorrect = isCorrect;
    this.choice = choice;
  }

  //************************** METHODS ********************************************//
  /**
   * Returns String representation of this Choice
   * 
   * @return the String representation of this Choice for the Question
   */
  public String getChoice() {
    return this.choice;
  }

  /**
   * Returns true/false depending on whether or not this Choice is the correct Choice for the
   * Question
   * 
   * @return true if this Choice is the correct Choice, false otherwise
   */
  public boolean getIsCorrect() {
    return this.isCorrect;
  }
} // END CLASS
