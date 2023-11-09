/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication4;

import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.*;

/**
 *
 * @author AVI03
 */
public class Sound {

    Clip clip;
    URL soundURL[] = new URL[1000];

    public Sound() {

        soundURL[0] = this.getClass().getResource("image/sound/jump1.wav");
        soundURL[1] = this.getClass().getResource("image/sound/ouch.wav");
        soundURL[2] = this.getClass().getResource("image/sound/spring.wav");
        soundURL[3] = this.getClass().getResource("image/sound/hawk.wav");
        soundURL[4] = this.getClass().getResource("image/sound/fall.wav");

    }

    public void setFile(int i) {

        try {
            
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);

            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {
        }

    }

    public void play() {

        if (clip != null) {
            clip.start();
        } else {
            System.err.println("Clip is null. Call setFile() first to initialize the clip.");
        }

    }

    public void setVolume(float volume) {
        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume); // Adjust volume here
        } else {
            System.err.println("Clip is null. Call setFile() first to initialize the clip.");
        }
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public void stop() {
        clip.stop();

    }

}
