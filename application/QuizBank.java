package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class QuizBank {
	
	public HashMap<String, topicBucket> table;
	public int total_num_questions;
	public int curr_q_ID;
	public int num_correct;
	
	class topicBucket {
		int n; 
		List<Question> bucket;
		
		topicBucket() {
			this.n = 0;
			this.bucket = new ArrayList<Question>();
		}
		
		Question get(int idx) {
			return this.bucket.get(idx % this.n);
		}
		
		void addQuestionToBucket(Question q) {
			this.bucket.add(q);
			this.n++;
		}
	}

	public QuizBank() {
		this.table = new HashMap<String, topicBucket>();
		this.total_num_questions = 0;
		this.curr_q_ID = 0;
		this.num_correct = 0;
	}
	
	public void addQuestionToQuiz(Question q) {
		this.total_num_questions += 1;
		if (!this.table.containsKey(q.getTopic())) {
			this.table.put(q.getTopic(), new topicBucket());
		}
		this.table.get(q.getTopic()).addQuestionToBucket(q);

	}
			
}
