/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication4;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author AVI03
 */
public class BirdObstacle extends PlatformPosition {

    int birdspeed = 1;
    private int currentFrame;

    private BufferedImage[] frames;
    private BufferedImage[] Rframes;
    boolean isMovingRight = true;

    public BirdObstacle(double x, int y, BufferedImage[] frames,BufferedImage[] Rframes) {
        
        System.out.println(x);
        if(x==0){
            this.x = 400;
            
        }
        else if(x==1){
            this.x = -20;
        }
        
        this.y = y;
        this.frames = frames;
        this.Rframes = Rframes;
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
    
    public BufferedImage getCurrentRFrame() {
        return Rframes[currentFrame];

    }

}
