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
	public Set<String> topics;
	
	class topicBucket {
		
		String topic;
		int n; 
		List<Question> bucket;
		
		topicBucket(String topic) {
			this.topic = topic;
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
		this.topics = new HashSet<String>();
	}
	
	public void addQuestionToQuiz(Question q) {
		this.topics.add(q.getTopic());
		System.out.println(this.topics);
		this.total_num_questions += 1;
		if (!this.table.containsKey(q.getTopic())) {
			this.table.put(q.getTopic(), new topicBucket(q.getTopic()));
		}
		this.table.get(q.getTopic()).addQuestionToBucket(q);
	}
	
	public List<Question> getRandomN(String topic, int n) {
		List<Question> selected = new ArrayList<Question>();
		if (n > this.table.get(topic).n) {
			n = this.table.get(topic).n;
		}
		topicBucket question_bucket = this.table.get(topic);
		Random rand = new Random();
		for (int i=0; i<n; i++) {
			Question randomQ = question_bucket.get(rand.nextInt(n));
			if (randomQ.asked) { i--; }
			else {
				randomQ.asked = true;
				selected.add(randomQ);
			}
		}
		return selected;
	}
			
}
