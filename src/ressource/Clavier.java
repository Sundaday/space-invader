package ressource;

import com.sundaday.jeu.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Clavier implements KeyListener {
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                //System.out.println("L");
                Main.scene.vaisseau.setDx(-Constantes.DX_VAISSEAU);
                break;
            case KeyEvent.VK_RIGHT:
                //System.out.println("R");
                Main.scene.vaisseau.setDx(Constantes.DX_VAISSEAU);
                break;
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
