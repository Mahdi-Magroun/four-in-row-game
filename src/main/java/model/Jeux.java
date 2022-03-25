package model;

import control.Control;
import view.View1;

public class Jeux {

	private boolean canIPlay=true;
	private int  lignie;
	private int colone;
	private Joueur tabJoueur[]=new Joueur[3];
	private int maquette[][];
	private int whoPlay=1;
	private int numberOfAlignItem=4;
	private boolean MaquetteRemplit=false;
	private Control controller;
	
	
	public Jeux(Control controller,int lignie,int colone,Joueur j1,Joueur j2) {
		this.controller=controller;
		this.lignie=lignie;
		this.colone=colone;
		this.maquette=new int [this.lignie][this.colone];
		this.tabJoueur[1]=j1;
		this.tabJoueur[2]=j2;
		//initialiser la maquette 
		this.initialiserMaquette();
	}
	
	//initialiser la maquette a 0 
	public int[][] initialiserMaquette() {
		for(int lg=0;lg<this.lignie;lg++) {
			for(int col=0;col<this.colone;col++) {
				this.maquette[lg][col]=0;
			}
		}
		return this.maquette;
	}
	public void restart() {
		this.initialiserMaquette();
		this.canIPlay=true;
		this.whoPlay=1;
		this.MaquetteRemplit=false;
		
	}
	public int [][]getMaquette(){
		return this.maquette;
	}
	
	public int  play(int colone) {
		//verifier si la partie n'est pas fini 
		
			if(this.canIPlay==true) {
			int lignie=this.identifierRowWhenColumIsValid(colone);
			this.maquette[lignie][colone]=this.whoPlay;
			boolean result =this.verif(lignie,colone);
			this.affiche();
			if(result==true) {
				System.out.println("player : "+this.tabJoueur[this.whoPlay].getName());// be carful out of range
				this.canIPlay=false;
				this.controller.blockButton();
				return 1;
			}
			else if(this.MaquetteRemplit==true) {
			 // en cas d'egaliter
				System.out.println("BRAVOOO vous ete fort tout les deux ");
				this.canIPlay=false;
				this.controller.blockButton();
				return 2;
			}
			else
			{
				// alternate between two user 
				this.whoPlay=(this.whoPlay==1)? 2 :1;
				return 0;
			}
			
			}
			
			
			else {
				System.out.println("la partie et fini");
				return -1;
	}
			
	}

	
	// identifier la lignie vide pour inserer la valeur de l'utulisateur 
	public int identifierRowWhenColumIsValid(int colone) {
		for(int i=0;i<this.lignie;i++) {
			if(this.maquette[i][colone]!=0) {
				return i-1;
			}
		}
		return this.lignie-1;
	}
	
// les methode de verification du coup 
	public boolean verif(int lignie,int colone) {
		//verifier si la maquette et remplit 
		this.MaquetteRemplit=this.maquetteStatus();
		
		// verif verticale : 
		//this.numberOfAlignItem-1=3
	
		if(lignie+(this.numberOfAlignItem-1)<this.lignie) { // verifier q'on nest pas out of range pour optimiser la recherche : done
		
		if(this.verifVertical(lignie, colone)==this.numberOfAlignItem-1) {
			//System.out.println("im here");
			return true;
		}
	}
	
		
		if(this.verifHorizentale(lignie, colone)>=this.numberOfAlignItem-1) {
			return true;
		}
		
		if(this.verifDiagonaleDroite(lignie, colone)>=this.numberOfAlignItem-1) {
			return true ;
		}
		
		if(this.verifDiagonaleGauche(lignie, colone)>=this.numberOfAlignItem-1) {
			return true ;
		}
		
		
		
		return false;
		
	}

	
	// change while loop to for loop in verif method's
	
	private int verifHorizentaleDroite(int lg,int col) {
		int result=0;
		
		for(int i=col+1;i<this.colone;i++) {
			
			if(this.maquette[lg][i]==this.whoPlay) {
				result++;
			}
			else {
				break;
			}
		}
		
		return result;
	}
	
	
	
	private int verifHorizentaleGauche(int lg,int col) {
		int result=0;
		for(int i=col-1;i>=0;i--) { // cas de 3 succesive
			if(this.maquette[lg][i]==this.whoPlay) {
				result++;
				System.out.println("Result : "+result );
			}
			else break;
		}
		return result;
		}

	
		public int verifHorizentale(int lg,int col) {
			int verifGauche=verifHorizentaleGauche(lg,col);
			int verifDroite=verifHorizentaleDroite(lg,col);
			return verifGauche+verifDroite;
		}
	
	
	
		
		private int verifVertical(int lg,int col) {
		int result=0;
		for(int i=lg+1;i<=lg+this.numberOfAlignItem-1;i++) {
			if(this.maquette[i][col]==this.whoPlay) {
				result++;
			}
		}
		return result;
	}
	
	
	
	private int verifDiagonaleAcendantG(int lignie,int colone) {
		int result=0;
		int col=colone-1;
		int row=lignie-1;
		
		while(row>=0&&col>=0) {
			if(this.maquette[row][col]==this.whoPlay) {
				result++;
			}
			else break;
			
			row--;
			col--;
		}
	
		return result;
	}
	
	
	
	private int verifDiagonaleAcendant(int lignie,int colone) {
		int result=0;
		int col=colone+1;
		int row=lignie-1;
		while(row>=0&&col<this.colone) {
			if(this.maquette[row][col]==this.whoPlay) {
				result++;
			}
			else break;
			
			row--;
			col++;
		}
	
		return result;
	}
	
	
	private int verifDiagonaleDroite(int lg,int col) {
		int a=this.verifDiagonaleAcendant(lg, col);
		int b=this.verifDiagonaleDecendant(lg, col);
		//System.out.println("a+b="+a+b);
		return a+b;
	}
	
	
	private int verifDiagonaleGauche(int lg,int col) {
		int a=this.verifDiagonaleAcendantG(lg, col);
		int b=this.verifDiagonaleDecendantG(lg, col);
		System.out.println("a="+a+b);
		return b+a;
	}
	
	
	
	
	private int verifDiagonaleDecendant(int lignie,int colone) {
		int result=0;
		int col=colone-1;
		int row=lignie+1;
		while(row<this.lignie&&col>=0) {
			if(this.maquette[row][col]==this.whoPlay) {
				result++;
			}
			else break;
			
			col--;
			row++;
		}
	
		return result;
	}
	
	private int verifDiagonaleDecendantG(int lignie,int colone) {
		int result=0;
		int col=colone+1;
		int row=lignie+1;
		while(row<this.lignie&&col<this.colone) {
			if(this.maquette[row][col]==this.whoPlay) {
				result++;
			}
			else break;
			
			col++;
			row++;
		}
	
		return result;
	}

	//verifier si la maquette et pleinne 
	private boolean maquetteStatus() {
	 for(int i=0;i<this.colone;i++) {
		 if(this.maquette[0][i]==0) {
			 return false;
			 
		 }
	 }
	
		return true;
 }

	// getter and setter 
	public boolean getCanIPlay() {
	 return this.canIPlay;
 }
 
	// affichage de matrice 
	public void affiche() {
	 for(int i = 0 ; i < this.lignie; i++ ){  
	     for(int j = 0; j< this.colone; j++){   
	         System.out.print(this.maquette[i][j]+"  "); 
	     } 
	System.out.println(); 
	System.out.println(); 
	}   } 
 
 public int  getWhoPlay() {
	 return this.whoPlay;
 }
 
 
 public Joueur[] getUser() {
		return this.tabJoueur;
	}
 
 
 
 
 
 
 
 
 
 
 
 
 
}