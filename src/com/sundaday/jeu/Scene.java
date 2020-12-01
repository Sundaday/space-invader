package com.sundaday.jeu;

import entites.Vaisseau;
import ressource.Constantes;

import javax.swing.*;
import java.awt.*;

public class Scene extends JPanel {
    //Variables
    public Vaisseau vaisseau = new Vaisseau();
    //Constructeur
    public Scene(){
        super();

    }
    //Methodes
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics g2 = (Graphics2D) g;

        //Dessin du fond d'écran
        g2.setColor(Color.BLACK);//couleur
        g2.fillRect(0,0, Constantes.LARGUEUR_FENETRE,Constantes.HAUTEUR_FENETRE);//dimensions
            //fillRect = rectangle plein
        //Dessin ligne verte en bas de l'écran
        g2.setColor(Color.GREEN);//couleur
        g2.fillRect(30,530,535,5);//dimensions

        //Dessin du vaisseau
        g2.drawImage(this.vaisseau.getImg(),this.vaisseau.getxPos(),this.vaisseau.getyPos(),null);
    }
}
