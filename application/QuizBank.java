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
	
	public HashMap<String, topicBucket> table;  //map topic --> list of corresp. questions
	public int total_num_questions;  //total questions in this bank
	public int num_correct;  //total number of questions answered correctly so far
	public int num_taken;
	public Set<String> topics;
	
	class topicBucket {
		
		String topic;
		int n; 
		List<Question> bucket;
		
		topicBucket() {
			this.topic = "";
			this.n = 0;
			this.bucket = new ArrayList<Question>();
		}
		
		Question get(int idx) {
			return this.bucket.get(idx);
		}
		
		void addQuestionToBucket(Question q) {
			this.bucket.add(q);
			this.n++;
		}
	}

	public QuizBank() {
		this.table = new HashMap<String, topicBucket>();
		this.total_num_questions = 0;
		this.num_correct = 0;
		this.num_taken = 1;
		this.topics = new HashSet<String>();
	}
	
	public void addQuestionToQuiz(Question q) {
		this.topics.add(q.getTopic());
		this.total_num_questions += 1;
		if (!this.table.containsKey(q.getTopic())) {
			this.table.put(q.getTopic(), new topicBucket());
		}
		this.table.get(q.getTopic()).addQuestionToBucket(q);
	}
	
	public List<Question> getRandomN(String topic, int n) {
		System.out.println("QuizBank getRandomN 1");
		List<Question> selected = new ArrayList<Question>();
		if (n > this.table.get(topic).n) {
			n = this.table.get(topic).n;
		}
		System.out.println("QuizBank getRandomN 2");
		topicBucket question_bucket = this.table.get(topic);
		Random rand = new Random();
		System.out.println("QuizBank getRandomN STARTING LOOP");
		for (int i=0; i<n; i++) {
			System.out.println("    i: " + i);
			Question randomQ = question_bucket.get(rand.nextInt(n));
			if (randomQ.asked) { i--; }
			else {
				randomQ.asked = true;
				selected.add(randomQ);
			}
		}
		System.out.println("QuizBank getRandomN 3");
		return selected;
	}
			
}
