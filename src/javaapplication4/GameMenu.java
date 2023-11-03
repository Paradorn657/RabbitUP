/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication4;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author AVI03
 */
public class GameMenu extends JPanel implements KeyListener {
    private int selectedOption = 0; // 0: Start Game, 1: Options, 2: Exit
    private String[] menuOptions = {"Start Game", "Options", "Exit"};

    public GameMenu() {
        setPreferredSize(new Dimension(400, 300));
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (int i = 0; i < menuOptions.length; i++) {
            if (i == selectedOption) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(menuOptions[i], 150, 100 + i * 30);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            selectedOption = (selectedOption - 1 + menuOptions.length) % menuOptions.length;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            selectedOption = (selectedOption + 1) % menuOptions.length;
        } else if (keyCode == KeyEvent.VK_ENTER) {
            if (selectedOption == 0) {
                // Start the game
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.getContentPane().remove(this);
                frame.add(new Rabbitup()); // Create your game instance
                frame.pack();
                frame.validate();
            } else if (selectedOption == 1) {
                // Handle Options (not implemented in this example)
            } else if (selectedOption == 2) {
                // Exit the game
                System.exit(0);
            }
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Handle key release events if necessary
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Handle key typed events if necessary
    }

    public static void main(String[] args) {
        JFrame w = new JFrame("Game Menu Example");
        w.setResizable(false);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.add(new GameMenu());
        w.pack();
        w.setLocationRelativeTo(null);
        w.setVisible(true);
    }
}


