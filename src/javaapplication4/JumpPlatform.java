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
public class JumpPlatform extends PlatformPosition {

    private final BufferedImage[] jumpPlatformFrames;
    private int frameIndex;
    private boolean rabbitHit;

    public JumpPlatform(int x, int y, BufferedImage[] frames) {
        super(x, y);
        this.platformUsed = false;
        this.jumpPlatformFrames = frames;
        this.frameIndex = 0;
        this.rabbitHit = false;
    }

 

    public void update() {
        if (rabbitHit) {
            // Update the animation frames only when rabbit has hit the platform
            frameIndex = (frameIndex + 1) % jumpPlatformFrames.length;
        }
    }

    public void setRabbitHit(boolean rabbitHit) {
        this.rabbitHit = rabbitHit;
    }

    public BufferedImage getCurrentFrame() {
        if (rabbitHit) {
            return jumpPlatformFrames[frameIndex];
        } else {
            return jumpPlatformFrames[0];
        }
    }

}
