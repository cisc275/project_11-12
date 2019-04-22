package project;

public class Question {
	String question;
	String ans1;
	String ans2;
	int correctAns;
	
	Question(String q, String a1, String a2, int ans){
		this.question = q;
		this.ans1 = a1;
		this.ans2 = a2;
		this.correctAns = ans;
	}
}