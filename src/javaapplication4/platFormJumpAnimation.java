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
public class platFormJumpAnimation {
    private BufferedImage[] frames;
    private int frameCount;
    private int currentFrame;
    private int frameDelay;
    private int delayCount;

    public platFormJumpAnimation(BufferedImage[] frames, int frameDelay) {
        this.frames = frames;
        this.frameCount = frames.length;
        this.currentFrame = 0;
        this.frameDelay = frameDelay;
        this.delayCount = 0;
    }

    public BufferedImage getCurrentFrame() {
        return frames[currentFrame];
    }

    public void update() {
        delayCount++;
        if (delayCount >= frameDelay) {
            currentFrame = (currentFrame + 1) % frameCount;
            delayCount = 0;
        }
    }
}
