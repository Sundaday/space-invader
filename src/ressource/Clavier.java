package ressource;

import com.sundaday.jeu.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Clavier implements KeyListener {
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            Main.scene.vaisseau.setDx(Constantes.DX_VAISSEAU);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            Main.scene.vaisseau.setDx(-Constantes.DX_VAISSEAU);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Main.scene.vaisseau.setDx(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
