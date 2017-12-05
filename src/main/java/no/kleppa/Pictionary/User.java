package no.kleppa.Pictionary;

public class User {
	private final String name;
	private final int score;

	public User(String name) {
		this.name = name;
		this.score = 0;
	}

	public User(String name, int i) {
		this.name=name;
		score=i;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}
	public User updateScoreFirstPlace(){
		return new User(name,score+50);
	}
	public User updateScore(){
		return new User(name,score+25);
	}
}
