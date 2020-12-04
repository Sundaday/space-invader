package entites;

import java.awt.Graphics;
import java.util.Random;

import jeu.Main;
import ressources.Audio;
import ressources.Chrono;
import ressources.Constantes;

public class GroupeAliens {

/**** VARIABLES ****/	

	// Tableau contenant tous les aliens (50)
	private Alien tabAlien[][] = new Alien [5][10];
	private boolean vaADroite, pos1;
	private int vitesse;

	private int[] tabAlienMort = {-1,-1}; // Emplacement alien mort dans tableau aliens
	
	Random hasard = new Random();
	
	private int nombreAliens = Constantes.NOMBRE_ALIENS;
	
	private int compteurSonAlien = 0;
	
	
/**** CONSTRUCTEUR ****/
	
	public GroupeAliens() {	
		
		this.initTableauAliens();
		this.vaADroite = true;
		this.pos1 =true;
		this.vitesse = Constantes.VITESSE_ALIEN;
	}

	
/**** METHODES ****/
		
	private void initTableauAliens() {
		// M�thode qui remplit le tableau complet des aliens
		for(int colonne=0; colonne<10; colonne++) {
			this.tabAlien[0][colonne] = new Alien(Constantes.X_POS_INIT_ALIEN + (Constantes.LARGEUR_ALIEN + Constantes.ECART_COLONNES_ALIEN) * colonne, 
					Constantes.ALT_INIT_ALIEN, "/images/alienHaut1.png", "/images/alienHaut2.png");
			for(int ligne=1; ligne<3; ligne++) {
				this.tabAlien[ligne][colonne] = new Alien(Constantes.X_POS_INIT_ALIEN + (Constantes.LARGEUR_ALIEN + Constantes.ECART_COLONNES_ALIEN) *
						colonne, Constantes.ALT_INIT_ALIEN + Constantes.ECART_LIGNES_ALIEN * ligne, "/images/alienMilieu1.png", "/images/alienMilieu2.png");
			}
			for(int ligne=3; ligne<5; ligne++) {	
				this.tabAlien[ligne][colonne] = new Alien(Constantes.X_POS_INIT_ALIEN + (Constantes.LARGEUR_ALIEN + Constantes.ECART_COLONNES_ALIEN)
					* colonne, Constantes.ALT_INIT_ALIEN + Constantes.ECART_LIGNES_ALIEN * ligne, "/images/alienBas1.png", "/images/alienBas2.png");
			}	
		}
	}
	
	public void dessinAliens(Graphics g) {
		if(Chrono.compteTours % (100 - 10 * this.vitesse) == 0) {this.deplacementAliens();}
		// Dessin des aliens contenus dans le tableau tabAlien
		for(int colonne=0; colonne<10; colonne++) {
			for(int ligne=0; ligne<5; ligne++) {
				if(this.tabAlien[ligne][colonne] != null) {
					this.tabAlien[ligne][colonne].choixImage(pos1);
					g.drawImage(this.tabAlien[ligne][colonne].getImg(), this.tabAlien[ligne][colonne].getxPos(), this.tabAlien[ligne][colonne].getyPos(),
						null);
				}
			}
		}		
	}
		
	private boolean toucheBordGauche() {
		// M�thode qui d�tecte le bord gauche de la fen�tre
		boolean reponse = false;
		for(int colonne=0; colonne<10; colonne++) {
			for(int ligne=0; ligne<5; ligne++) {
				if(this.tabAlien[ligne][colonne] != null) {
					if(this.tabAlien[ligne][colonne].getxPos() < Constantes.MARGE_FENETRE){
						reponse = true;
						break;
					}
				}
			}
		}
		return reponse;
	}
	
	private boolean toucheBordDroit() {
		// M�thode qui d�tecte le bord droit de la fen�tre
		boolean reponse = false;
		for(int colonne=0; colonne<10; colonne++) {
			for(int ligne=0; ligne<5; ligne++) {
				if(this.tabAlien[ligne][colonne] != null) {
					if(this.tabAlien[ligne][colonne].getxPos() > 
					Constantes.LARGEUR_FENETRE - Constantes.LARGEUR_ALIEN - Constantes.DX_ALIEN - Constantes.MARGE_FENETRE) {
						reponse = true;
						break;
					}
				}
			}
		}
		return reponse;
	}
	
	public void alienTourneEtDescend() {
		// M�thode qui change le sens de d�placement de l'alien et le descend d'un cran
		if(this.toucheBordDroit() == true) {			
			for(int colonne=0; colonne<10; colonne++) {
				for(int ligne=0; ligne<5; ligne++) {
					if(this.tabAlien[ligne][colonne] != null) {
						this.tabAlien[ligne][colonne].setyPos(this.tabAlien[ligne][colonne].getyPos() + Constantes.DY_ALIEN);
					}
				}
			}
			this.vaADroite = false;
			if(this.vitesse < 9) {this.vitesse++;}
		} else {
			if(this.toucheBordGauche() == true) {			
				for(int colonne=0; colonne<10; colonne++) {
					for(int ligne=0; ligne<5; ligne++) {
						if(this.tabAlien[ligne][colonne] != null) {
						this.tabAlien[ligne][colonne].setyPos(
							this.tabAlien[ligne][colonne].getyPos() + Constantes.DY_ALIEN);
						}
					}
				}
				this.vaADroite = true;
				if(this.vitesse < 9) {this.vitesse++;}
			}
		}
	}
	
	public void deplacementAliens() {
		// M�thode qui g�re le d�placement des aliens
		if(this.tabAlienMort[0] != -1) { // Elimination de l'alien mort si n�cessaire
			elimineAlienMort(tabAlienMort);
			tabAlienMort[0] = -1; // R�initialisation de tabAlienMort
		}
		if(this.vaADroite == true) { // D�placement vers la droite
			for(int colonne=0; colonne<10; colonne++) {
				for(int ligne=0; ligne<5; ligne++) {	
					if(this.tabAlien[ligne][colonne] != null) {
						this.tabAlien[ligne][colonne].setxPos(this.tabAlien[ligne][colonne].getxPos() + Constantes.DX_ALIEN);
					}
				}
			}
		}else{ // D�placement vers la gauche
			for(int colonne=0; colonne<10; colonne++) {
				for(int ligne=0; ligne<5; ligne++) {
					if(this.tabAlien[ligne][colonne] != null) {
						this.tabAlien[ligne][colonne].setxPos(this.tabAlien[ligne][colonne].getxPos() - Constantes.DX_ALIEN);
					}
				}
			}
		}
		// les aliens �mettent un son
		this.joueSonAlien();
		// Incr�mentation du compteur de son
		this.compteurSonAlien++;
		// Changement de l'image de l'alien
		if(this.pos1 == true) {this.pos1 = false;} 
		else {this.pos1 = true;}
		// M�j du sens de d�placement si un alien atteint le bord de la fen�tre
		this.alienTourneEtDescend();
	}
	
	public void tirVaisseauToucheAlien(TirVaisseau tirVaisseau) {
		// D�tection contact tirVaisseau avec alien
		for(int colonne=0; colonne<10; colonne++) {
			for(int ligne=0; ligne<5; ligne++) {
				if(this.tabAlien[ligne][colonne] != null) {
					if(tirVaisseau.tueAlien(this.tabAlien[ligne][colonne]) == true) {
						this.tabAlien[ligne][colonne].vivant = false; // On tue l'alien
						tirVaisseau.yPos = -1; // On tue le tir
						// On enregistre la position de l'alien mort dans le tableau
						this.tabAlienMort[0] = ligne;
						this.tabAlienMort[1] = colonne; 
						if(ligne == 0) {
							Main.scene.score = Main.scene.score + Constantes.VALEUR_ALIEN_HAUT;}
						else if(ligne>0 && ligne<3) {
							Main.scene.score = Main.scene.score + Constantes.VALEUR_ALIEN_MILIEU;}
						else {
							Main.scene.score = Main.scene.score + Constantes.VALEUR_ALIEN_BAS;}	
						break;
					}
				}					
			}					
		}
	}

	private void elimineAlienMort(int[] tabAlienMort) {
		// M�thode qui enl�ve l'alien mort du tableau (case � null)
		this.tabAlien[tabAlienMort[0]][tabAlienMort[1]] = null;
		this.nombreAliens--;
	}
	
	public int[] choixAlienQuiTire() {
		// Renvoie la position d'un alien tir� au hasard dans tabAlien en bas de sa 
		// colonne (ligne, colonne)
		int positionAlien[] = {-1,-1};		
		if(this.nombreAliens != 0) { // On v�rifie qu'il reste des aliens vivants
			do {int colonne = hasard.nextInt(10); // On tire au hasard une colonne du 
			// tableau des aliens		
				for(int ligne=4;ligne>=0;ligne--) { // On cherche le 1er alien vivant 
				// en partant du bas
					if(tabAlien[ligne][colonne]!= null) {
						positionAlien[0] = this.tabAlien[ligne][colonne].getxPos();
						positionAlien[1] = this.tabAlien[ligne][colonne].getyPos();
						break;
					}
				}
			} while(positionAlien[0] == -1);
		}	
		return positionAlien;
	}
	
	private void joueSonAlien() { // M�thode qui joue le son de l'alien (4 sons possibles)
		int compteur = this.compteurSonAlien % 4;
		if(compteur==0) {
            audio.playSound("/sons/sonAlien1.wav");}
		else if(compteur==1) {
            audio.playSound("/sons/sonAlien2.wav");}
		else if(compteur==2) {
            audio.playSound("/sons/sonAlien3.wav");}
		else {
            audio.playSound("/sons/sonAlien4.wav");}
	}
	
	public int getNombreAliens() {return nombreAliens;}
	
	public int positionAlienLePlusBas() {
		// Renvoie l'altitude des pieds de l'alien le plus bas
		int posBas = 0, posBasFinal = 0;
		for(int colonne=1; colonne<10;colonne++) {
			for(int ligne=4; ligne>=0;ligne--) {
				if(this.tabAlien[ligne][colonne] != null) {
					posBas = this.tabAlien[ligne][colonne].yPos + this.tabAlien[ligne][colonne].hauteur;
					break;
				}			
			}
			if(posBas > posBasFinal) {posBasFinal = posBas;}
		}
		return posBasFinal;
	}
}




