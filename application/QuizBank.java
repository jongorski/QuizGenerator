///////////////////////////////////////////////////////////////////////////////
//
// Title: Quiz Generator
// Course: CS400 Spring 2019
// Project: Team Project
//
// Author: Mikel Terracina, Danielle Hart, Jon Gorski, Jack Wolf
// Email: mterracina@wisc.edu, dahart2@wisc.edu, jongorski2@wisc.edu, jwolf22@wisc.edu
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/*
 * Class defining QuizBank object where user stores questions
 */
public class QuizBank {

  public HashMap<String, topicBucket> table; // map topic --> list of corresp. questions
  public int total_num_questions; // total questions in this bank
  public Set<String> topics;

  /**
   * This class defines topic bucket. Topic buckets are a list of questions of specific topic given.
   * Topic buckets are mapped in the hash table (quiz bank)
   * 
   * @author daniellehart
   *
   */
  class topicBucket {

    String topic;
    int n;
    List<Question> bucket;

    /**
     * Topic bucket constructor.
     * 
     * @param topic
     */
    topicBucket(String topic) {
      this.topic = topic;
      this.n = 0;
      this.bucket = new ArrayList<Question>();
    }

    /**
     * a getter for the element from the list of questions of topic bucket
     * 
     * @param idx
     * @return
     */
    Question get(int idx) {
      return this.bucket.get(idx);
    }

    /**
     * an adder, adds question to topic bucket
     * 
     * @param q : question being added
     */
    void addQuestionToBucket(Question q) {
      this.bucket.add(q);
      this.n++;
    }
  }

  /**
   * This is a quiz bank constructor.
   */
  public QuizBank() {
    this.table = new HashMap<String, topicBucket>();
    this.total_num_questions = 0;
    this.topics = new HashSet<String>();
  }

  /**
   * This method adds a question to the quiz bank. Updates number of questions and topics.
   * 
   * @param q : question to be added to quiz
   */

  public void addQuestionToQuiz(Question q) {
    if (!q.getTopic().equals("")) {
      this.topics.add(q.getTopic());
      this.total_num_questions += 1;
      if (!this.table.containsKey(q.getTopic())) {
        this.table.put(q.getTopic(), new topicBucket(q.getTopic()));
      }
      this.table.get(q.getTopic()).addQuestionToBucket(q);
    }
  }

  /**
   * This method gets 'n' random questions and returns a list
   * 
   * @param topic
   * @param n : number of questions user wants to answer
   * @return list of random questions selected
   */
  public List<Question> getRandomN(String topic, int n) {
    List<Question> selected = new ArrayList<Question>();
    if (n > this.table.get(topic).n) {
      n = this.table.get(topic).n;
    }
    topicBucket question_bucket = this.table.get(topic);
    Random rand = new Random();
    for (int i = 0; i < n; i++) {
      Question randomQ = question_bucket.get(rand.nextInt(n));
      if (randomQ.asked) {
        i--;
      } else {
        randomQ.asked = true;
        selected.add(randomQ);
      }
    }
    return selected;
  }

}