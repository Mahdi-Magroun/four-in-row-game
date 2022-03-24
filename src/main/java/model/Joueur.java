package model;

public class Joueur {
	private int id;
	private String Name;
	public Joueur(int id,String name) {
		this.id=id;
		this.Name=name;
	}
	
	public String getName() {
		return this.Name;
	}

}
