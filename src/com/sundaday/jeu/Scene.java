package com.sundaday.jeu;

import entites.Vaisseau;
import ressource.Chrono;
import ressource.Constantes;
import ressource.Clavier;

import javax.swing.*;
import java.awt.*;

public class Scene extends JPanel {
    //Variables
    public Vaisseau vaisseau = new Vaisseau();
    //Constructeur
    public Scene(){
        super();
        //Instanciation de la classe Clavier
        this.setFocusable(true); //permet d'écouter le clavier
        this.requestFocusInWindow(); //rend l'écoute du clavier possible dans la fenetre
        this.addKeyListener(new Clavier()); //creation de l'instance de clavier

        //instanciation de la classe Chrono
        Thread chronoEcran = new Thread(new Chrono()); //thread = flux d'info qui va appeler chrono toutes les 5milliSec
        chronoEcran.start();
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
        g2.drawImage(this.vaisseau.getImg(),this.vaisseau.deplacementVaisseau(),this.vaisseau.getyPos(),null);
    }
}
