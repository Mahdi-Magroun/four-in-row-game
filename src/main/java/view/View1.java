package view;

import control.Control;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class View1 {
	private Control controller;
	private GridPane maquette= new GridPane() ;
	public View1(Control controller) {
		this.controller=controller;
	}
	public BorderPane init(int[][] maquette) {
		
		for(int i=0;i<maquette.length;i++) {
			for(int j=0;j<maquette[i].length;j++) {
				final int ii=i;
				final int jj=j;
				Button btn=new Button("   ");
				this.maquette.add(btn,ii,jj);
					btn.setOnAction(event -> {
						controller.play(ii,jj);
					});
			}
		}
		BorderPane bp=new BorderPane();
		bp.setCenter(this.maquette);
		return bp;

		
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
