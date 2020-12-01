package com.sundaday.jeu;

import javax.swing.*;

import ressource.Constantes;

public class Main  {

    //Variables
    public static Scene scene;

    //Methodes
    public static void main(String[] args) {
        //Creation de la fenetre de l'application
        JFrame fenetre = new JFrame("Space Invader");
        fenetre.setSize(Constantes.LARGUEUR_FENETRE, Constantes.HAUTEUR_FENETRE);//dimensions
        fenetre.setResizable(false);//Impossible a redimentionner
        fenetre.setLocationRelativeTo(null);//mettre la fenetre au milie de l'ecran
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //cliquer sur croix pour close
        fenetre.setAlwaysOnTop(true); //toujours au top des autres fenetre

        //Association du panneau Scene a la fentre
        scene = new Scene();
        fenetre.setContentPane(scene);//met le panneau scene dans notre fenetre

        fenetre.setVisible(true);// rend ma fenetre visible


    }
}
