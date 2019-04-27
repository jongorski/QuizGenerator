package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class QuizBank {
	
	private HashMap<String, topicBucket> table;
	private int len;
	
	class topicBucket {
		int len; 
		List<Question> bucket;
		
		topicBucket() {
			this.len = 0;
			this.bucket = new ArrayList<Question>();
		}
		
		Question get(int idx) {
			return this.bucket.get(idx % this.len);
		}
		
		void addQuestionToBucket(Question q) {
			this.bucket.add(q);
			this.len++;
		}
	}

	public QuizBank() {
		this.table = new HashMap<String, topicBucket>();
	}
	
	public void addQuestionToQuiz(Question q) {
		if (!this.table.containsKey(q.getTopic())) {
			this.table.put(q.getTopic(), new topicBucket());
		}
		this.table.get(q.getTopic()).addQuestionToBucket(q);

	}
	
	public int getLen() { return this.len; }
	
	public HashMap<String, topicBucket> getQuestions() { return this.table; }
	
}
