package model;

public class Joueur {
	private int id;
	private String Name;
	private int score=0;
	public Joueur(int id,String name) {
		this.id=id;
		this.Name=name;
	}
	
	public String getName() {
		return this.Name;
	}
	
	public int getScore() {
		return this.score;
	}
	public void setScore(int score) {
		this.score=score;
	}

}
