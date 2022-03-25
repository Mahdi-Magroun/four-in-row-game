package view;

import control.Control;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.Joueur;

public class View1 {
	private Control controller;
	private GridPane maquette= new GridPane() ;
	
	public View1(Control controller) {
		this.controller=controller;
	}
	public BorderPane init(int[][] maquette) {
	//	this.maquette.minHeight(150);
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
		
		BorderPane bp=new BorderPane();
		bp.setCenter(this.maquette);
		Joueur j[]=this.controller.getJoueurs();
		FlowPane flow =new FlowPane();
		FlowPane flow2 =new FlowPane();
		Text j1= new Text(j[1].getName());
		Text j2= new Text(j[2].getName());
		Text score= new Text(j[1].getScore()+"-"+j[2].getScore());
		flow.getChildren().add(j1);
		flow.getChildren().add(j2);
		
		bp.setLeft(flow);
		bp.setRight(flow2);
		bp.setTop(score);
		return bp;

		
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
}
