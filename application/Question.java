/**
 * 
 */
package application;

import java.util.List;

/**
 * @author mterracina
 *
 */
public class Question {

  // ************************** FIELDS ******************************************//
  private String metadata; // miscellaneous information about the question
  private String question;
  private String topic;
  private String image;
  private List<Choice> choices;
  private String answer;

  // ************************** CONSTRUCTORS **************************************//
  Question(String metadata, String question, String topic, String image, String answer,
      List<Choice> choices) {
    this.metadata = metadata;
    this.question = question;
    this.topic = topic;
    this.image = image;
    this.answer = answer;
    this.choices = choices; // max number of choices = 5
  }

  // ************************** METHODS ********************************************//
  public String getQuestion() {
    return question;
  }

  public List<Choice> getChoices() {
    return choices;
  }

  public String getAnswer() {
    return answer;
  }

  public String getMetadator() {
    return metadata;
  }

  public String getTopic() {
    return topic;
  }

  public String getImage() {
    return image;
  }
} // END CLASS