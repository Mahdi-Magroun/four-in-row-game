package control;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import model.Jeux;
import model.Joueur;
import view.View1;

public class Control {
private Jeux game ;
	private View1 maquetteView = new View1(this);
	
	
	public BorderPane intialiserMaquette() {
		int lengthMaquette=this.maquetteView.getLgMaquette();
		this.game=new Jeux(this,lengthMaquette,lengthMaquette,new Joueur(1,"player1"),new Joueur(2,"player2"));
		int[][] maquette = this.game.initialiserMaquette();
		return this.maquetteView.initGame(maquette);
	}
	
	
	public void blockButton() {
		this.maquetteView.blockButton();
	}
	
	
	public void  play(int col,int row) {
		int row2=this.game.identifierRowWhenColumIsValid(col);
		System.out.println(row2);
		int player=this.game.getWhoPlay();
		int status=this.game.play(col);
		if(status==1) {
			this.maquetteView.changeButton(col,row2,player);
			Joueur[] gg=this.game.getUser();
			this.score(gg[player]);
			this.showWinner(gg[player].getName());
		}
		else if(status==2) {
			this.maquetteView.changeButton(col,row2,player);
			this.egaliter();
		}
		else {
			this.maquetteView.changeButton(col,row2,player);
		}
		
		
	}
	
	
	public Joueur[] getJoueurs() {
		return this.game.getUser();
	}
	
	public void restart() {
		this.game.restart();
		this.maquetteView.initGame(this.game.getMaquette());
	}
	
	
	
	public void showWinner(String name) {
		this.maquetteView.alert(name);
	}
	
	
	public void egaliter() {
		this.maquetteView.showEgaliter();
		
	}
	
	public void score(Joueur j) {
		j.setScore(j.getScore()+1);
		this.maquetteView.setScore();
		
	}
	
}
