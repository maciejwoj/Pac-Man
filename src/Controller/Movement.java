package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement implements KeyListener {

    public boolean up, down, left, right, backToMenu;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        if(k == KeyEvent.VK_W){
            up = true;
        }
        if(k == KeyEvent.VK_S){
            down = true;
        }
        if(k == KeyEvent.VK_A){
            left = true;
        }
        if(k == KeyEvent.VK_D){
            right = true;
        }
        if (e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_Q) {
            backToMenu = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        if(k == KeyEvent.VK_W){
            up = false;
        }
        if(k == KeyEvent.VK_S){
            down = false;
        }
        if(k == KeyEvent.VK_A){
            left = false;
        }
        if(k == KeyEvent.VK_D){
            right = false;
        }
        if (e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_Q) {
            backToMenu = false;
        }

    }
}
