/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication4;

import java.awt.image.BufferedImage;

/**
 *
 * @author AVI03
 */
public class SpikePlatform extends PlatformPosition {
    
    int delayCounter;

    private BufferedImage[] frames;
    private int currentFrame;

    public SpikePlatform(int x, int y, BufferedImage[] frames) {
        super(x, y);
        this.frames = frames;
        this.currentFrame = 0;

    }

    public void update() {
        currentFrame++;
        if (currentFrame >= frames.length) {
            currentFrame = 0;  // Wrap around to the first frame
        }
    }
    
    public BufferedImage getCurrentFrame() {
            return frames[currentFrame];
        
    } 
    
    
  }

