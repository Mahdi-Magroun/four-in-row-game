package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import control.Control;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.Joueur;

public class View1 {
	private Control controller;
	private GridPane maquette= new GridPane() ;
	private BorderPane allView=new BorderPane();
	
	public View1(Control controller) {
		this.controller=controller;
	}
	public void init(int[][] maquette) {
	//this.maquette.minHeight(150);
		//this.maquette.minWidth(150);
		for(int i=0;i<maquette.length;i++) {
			for(int j=0;j<maquette[i].length;j++) {
				final int ii=i;
				final int jj=j;
				Button btn=new Button(" ");
				btn.setMinHeight(50);
				btn.setMinWidth(50);
				this.maquette.add(btn,ii,jj);
					btn.setOnAction(event -> {
						controller.play(ii,jj);
					});
			}
		}
	}
	
	public BorderPane initGame(int[][] maquette) {
		this.init(maquette);
		FlowPane[] joueur=this.setJoueurPanel(this.controller.getJoueurs());
		GridPane score = this.getScore();
		this.allView.setCenter(this.maquette);
		this.allView.setTop(score);
		this.allView.setLeft(joueur[0]);
		this.allView.setRight(joueur[1]);
		return this.allView;
		
		
		
		
	}
	
	
	
	
private GridPane getScore() {
		Joueur j[]=this.controller.getJoueurs();
		Text score= new Text(j[1].getScore()+"-"+j[2].getScore());
		GridPane g =new GridPane();
		g.add(score, 0, 0);
		Button btn=new Button("restart");
		btn.setOnAction(event -> {
			this.controller.restart();
		});
		g.add(btn, 0, 2);
		return g;
	}
	
	public  void setScore() {
		this.allView.setTop(this.getScore());
	}
	private FlowPane[] setJoueurPanel(Joueur[] j) {
		FlowPane allFlow[]=new FlowPane[2];
		FlowPane flow =new FlowPane();
		FlowPane flow2 =new FlowPane();
		Text j1= new Text(j[1].getName());
		Text j2= new Text(j[2].getName());
		flow.getChildren().add(j1);
		flow2.getChildren().add(j2);
		allFlow[0]=flow;
		allFlow[1]=flow2;
		
		return allFlow;
		
	}
	public void alert(String name) {
		Alert a=new Alert(AlertType.INFORMATION);
		a.setTitle("Winner");
		a.setContentText(name+" you are the winner !!!");
		a.showAndWait();
	}
	
	
	public void showEgaliter() {
		Alert a=new Alert(AlertType.INFORMATION);
		a.setTitle("egaliter");
		a.setContentText("vous ete fort tout les deux  ");
		a.showAndWait();
	}
	
	
	public void blockButton() {
		   for (Node bt:this.maquette.getChildren()) {
			   bt.setDisable(true);
			   
		   }
	}
	
	public GridPane getMaquette() {
		return this.maquette;
	}
	
	public void changeButton(int col,int row,int player) {
		if(player==1) {
			this.maquette.add(new Button(" X "), col, row);
		}
		if(player==2) {
			this.maquette.add(new Button( " O "), col, row);
		}
		
		
	}
	
	
	public int getLgMaquette() {
		List<String> choices = new ArrayList<>();
		choices.add("6*6");
		choices.add("8*8");
		choices.add("10*10");
		choices.add("16*16");
		choices.add("20*20");

		ChoiceDialog<String> dialog = new ChoiceDialog<>("6*6", choices);
		dialog.setTitle("Choice Dialog");
		dialog.setHeaderText("Look, a Choice Dialog");
		dialog.setContentText("Choose your letter:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			switch(result.get()) {
			case "6*6":
				return 6;
			case "8*8" :
				return 8;
			case "10*10":
				return 10;			
			case "16*16":
				return 16;
			case "20*20":
			return 20;
			
					
		}
					
		}
			
		
		return 6;
	}
	
	
	public String PlayerName(String who) {
		TextInputDialog dialog = new TextInputDialog("");

		dialog.setTitle("enter Name");
		dialog.setHeaderText("Enter your name:");
		dialog.setContentText("");

		Optional<String> result = dialog.showAndWait();
		if(result.isPresent()) {
			return result.get();
		}
		
		
			return who;
		
		
	}
	
	
	
	
	
	
	
}
