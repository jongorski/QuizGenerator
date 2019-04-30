/**
 * 
 */
package application;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import application.QuizBank.topicBucket;

/**
 * @author mterracina
 *
 */
class QuizBankTest {

//fields that will be used by multiple tests
 protected QuizBank qb;
// protected Question q1;
// protected Question q2;
// protected Question q3;
// protected Question q4;
// protected Question q5;
// protected Choice ch1a;

 
  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    qb = new QuizBank();
    
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    qb = null;
  }
  
  /***
   * Creates a series of question and answers
   * @return List of Question objects with newly created question/answers
   */
  List<Question> createQuestions() {
    List<Question> questionList = new ArrayList<Question>();
    
    // create topic 1, question 1 //
    String topic = "Topic 1";
    String question = "Question 1";
    String answer = "choice 3";
    Choice ch1 = new Choice(false, "choice 1");
    Choice ch2 = new Choice(false, "choice 2");
    Choice ch3 = new Choice(true, "choice 3");
    Choice ch4 = new Choice(false, "choice 4");
    Choice ch5 = new Choice(false, "choice 5");
    List<Choice> choices = new ArrayList<Choice>();
    choices.add(ch1);
    choices.add(ch2);
    choices.add(ch3);
    choices.add(ch4);

    Question q1 = new Question(topic, question, answer, choices);

    // create topic 1, question 2 //
    question = "Question 2";
    answer = "choice 4";
    ch1 = new Choice(false, "choice 1");
    ch2 = new Choice(false, "choice 2");
    ch3 = new Choice(false, "choice 3");
    ch4 = new Choice(true, "choice 4");
    choices.clear();
    choices.add(ch1);
    choices.add(ch2);
    choices.add(ch3);
    choices.add(ch4);

    Question q2 = new Question(topic, question, answer, choices);

    // create topic 1, question 3 //
    question = "Question 3";
    answer = "choice 1";
    ch1 = new Choice(true, "choice 1");
    ch2 = new Choice(false, "choice 2");
    ch3 = new Choice(false, "choice 3");
    ch4 = new Choice(false, "choice 4");
    choices.clear();
    choices.add(ch1);
    choices.add(ch2);
    choices.add(ch3);
    choices.add(ch4);

    Question q3 = new Question(topic, question, answer, choices);

    // create topic 2, question 1 //
    topic = "Topic 2";
    question = "Question 1";
    answer = "choice 2";
    ch1 = new Choice(false, "choice 1");
    ch2 = new Choice(true, "choice 2");
    ch3 = new Choice(false, "choice 3");
    ch4 = new Choice(false, "choice 4");
    choices.clear();
    choices.add(ch1);
    choices.add(ch2);
    choices.add(ch3);
    choices.add(ch4);

    Question q4 = new Question(topic, question, answer, choices);
    
    // create topic 2, question 3 //
    topic = "Topic 2";
    question = "Question 2";
    answer = "choice 5";
    ch1 = new Choice(false, "choice 1");
    ch2 = new Choice(false, "choice 2");
    ch3 = new Choice(false, "choice 3");
    ch4 = new Choice(false, "choice 4");
    ch5 = new Choice(true, "choice 5");
    choices.clear();
    choices.add(ch1);
    choices.add(ch2);
    choices.add(ch3);
    choices.add(ch4);
    choices.add(ch5);

    Question q5 = new Question(topic, question, answer, choices);

    // create topic 2, question 3 //
    topic = "Topic 3";
    question = "Question 1";
    answer = "choice 3";
    ch1 = new Choice(false, "choice 1");
    ch2 = new Choice(false, "choice 2");
    ch3 = new Choice(true, "choice 3");
    ch4 = new Choice(false, "choice 4");
    ch5 = new Choice(false, "choice 5");
    choices.clear();
    choices.add(ch1);
    choices.add(ch2);
    choices.add(ch3);
    choices.add(ch4);
    choices.add(ch5);

    Question q6 = new Question(topic, question, answer, choices);
    
    questionList.add(q1);
    questionList.add(q2);
    questionList.add(q3);
    questionList.add(q4);
    questionList.add(q5);
    questionList.add(q6);

    return questionList;
  }

  /**
   * Creates a new QuizBank object and adds several topics and questions. Verifies that expected answers are returned
   */
  @Test
  void test000_AddQuestionToQuiz() {

    boolean q_a_found = false;
    String topic;
    String question;
    String answer;
    
    List<Question> questions = createQuestions();
    
    // add the Questions to the QuizBank
    for (Question q : questions) {
      qb.addQuestionToQuiz(q);
    }

    // get all questions from the QuizBank 
    HashMap<String, topicBucket> qTable;
    qTable = qb.getQuestions();

    // get List of Questions for Topic 1 //
    topicBucket tB = qTable.get("Topic 1");
    List<Question> qList = tB.bucket;

    // test - topic 1, question 1 //
    topic = "Topic 1";
    question = "Question 1";
    answer = "choice 3";
    q_a_found = test000_AddQuestionToQuizHelper("Question 1", "choice 3", qList);
    if (q_a_found == false) {
      fail(topic + ", " + question + ", " + answer + " not found");
    }

    // test - topic 1, question 2 //
    question = "Question 2";
    answer = "choice 4";
    q_a_found = test000_AddQuestionToQuizHelper("Question 2", "choice 4", qList);
    if (q_a_found == false) {
      fail(topic + ", " + question + ", " + answer + " not found");
    }

    // test - topic 1, question 3 //
    question = "Question 3";
    answer = "choice 1";
    q_a_found = test000_AddQuestionToQuizHelper("Question 3", "choice 1", qList);
    if (q_a_found == false) {
      fail(topic + ", " + question + ", " + answer + " not found");
    }

    // get List of Questions for Topic 2 //
    topic = "Topic 2";
    tB = qTable.get(topic);
    qList = tB.bucket;
    
    // test - topic 2, question 1 //
    question = "Question 1";
    answer = "choice 2";
    q_a_found = test000_AddQuestionToQuizHelper("Question 1", "choice 2", qList);
    if (q_a_found == false) {
      fail(topic + ", " + question + ", " + answer + " not found");
    }
    
    // test - topic 2, question 2 //
    question = "Question 2";
    answer = "choice 5";
    q_a_found = test000_AddQuestionToQuizHelper("Question 2", "choice 5", qList);
    if (q_a_found == false) {
      fail(topic + ", " + question + ", " + answer + " not found");
    }
    
    // get List of Questions for Topic 2 //
    topic = "Topic 3";
    tB = qTable.get(topic);
    qList = tB.bucket;
    
    // test - topic 3, question 1 //
    question = "Question 1";
    answer = "choice 3";
    q_a_found = test000_AddQuestionToQuizHelper("Question 1", "choice 3", qList);
    if (q_a_found == false) {
      fail(topic + ", " + question + ", " + answer + " not found");
    }             
  }

  /**
   * Helper method that returns true/false if expected question/answer combination is found in the passed List of Question objects
   * @param question - question to search for within given List
   * @param answer - answer expected for the given question
   * @param qList - List of Question objects to search for the question/answer combination
   * @return true if the question/answer combo is found, otherwise false
   */
  boolean test000_AddQuestionToQuizHelper(String question, String answer, List<Question> qList) {
    for (Question q : qList) {
      if (q.getQuestion().equals(question)) {
        if (q.getAnswer().equals(answer)) {
          return true;
        }
      }
    }
    return false;
  }
  /**
   * Creates a new QuizBank object and adds several topics and questions. Verifies that expected number of Questions per topic is returned
   */
  @Test
  void test001_GetLen() {
    
    String topic;
    
    List<Question> questions = createQuestions();
    
    // add the Questions to the QuizBank
    for (Question q : questions) {
      qb.addQuestionToQuiz(q);
    }

    // get all questions from the QuizBank 
    HashMap<String, topicBucket> qTable;
    qTable = qb.getQuestions();

    // get the number of Questions for Topic 1 //
    topic = "Topic 1";
    topicBucket tB = qTable.get(topic);
    if (tB.len != 3) {
      fail("length of topicBucket " + topic + " did not return the expected value: " + tB.len);
    }
    
    // get the number of Questions for Topic 2 //
    topic = "Topic 2";
    tB = qTable.get(topic);
    if (tB.len != 2) {
      fail("length of topicBucket " + topic + " did not return the expected value: " + tB.len);
    }
    
    // get the number of Questions for Topic 2 //
    topic = "Topic 3";
    tB = qTable.get(topic);
    if (tB.len != 1) {
      fail("length of topicBucket " + topic + " did not return the expected value: " + tB.len);
    }
  }

  /**
   * Test method for {@link application.QuizBank#getQuestions()}.
   */
  @Test
  void testGetQuestions() {
    fail("Not yet implemented");
  }

}
