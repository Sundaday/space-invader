package ressource;

import com.sundaday.jeu.Main;

public class Chrono implements Runnable {
    //Variables
    private final int PAUSE = 5;// temps d'attente entre de 2 tours de boucle = 5millisecondes
    public static int compteTours = 0;

    //Methodes

    public void run() {
        while (true){
            Main.scene.repaint();//Appel de la méthode PaintComponent de l'objet Scene
            try {
                Thread.sleep(PAUSE);//Temps de pause 5msec
            } catch (InterruptedException e){}
        }
    }
}
