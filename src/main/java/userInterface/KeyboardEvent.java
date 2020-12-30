package userInterface;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardEvent extends JFrame{

    public char getKeyPressed(){
        final char[] keyPressed = new char[1];
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                keyPressed[0] = keyEvent.getKeyChar();
            }
        });
        return keyPressed[0];
    }

}
