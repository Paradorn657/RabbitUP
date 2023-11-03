/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Random;

import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rabbitup extends JPanel implements Runnable, KeyListener {

    final int WIDTH = 400;
    final int HEIGHT = 533;

    boolean isRunning;
    Thread thread;

    BufferedImage view, background, platform, moveplatform, jumpplatform, spikeplatform, jumponceplatform;

    BufferedImage up1, up2, up3,
            fall1, fall2, fall3, Rup1, Rup2, Rup3,
            Rfall1, Rfall2, Rfall3;

    String direction = "up";

    BufferedImage skyBackground, cloud1, cloud2, star, skyBackground_1, cloud1_1, cloud2_1, star_1, skyBackground_2, cloud1_2, cloud2_2, star_2, skyBackground_3, cloud1_3, cloud2_3, star_3, skyBackground_4, cloud1_4, cloud2_4, star_4, skyBackground_5, cloud1_5, cloud2_5, star_5;

    BufferedImage rabImage;
    
    Sound sound = new Sound();
    int spriteCounter = 0;
    int spriteNum = 1;

    int score = 0;

    PlatformPosition[] platformposition;
    ArrayList<MovingPlatform> movingPlatforms = new ArrayList<MovingPlatform>();
    ArrayList<jumpOncePlatform> jumponcePlatforms = new ArrayList<jumpOncePlatform>();

    ArrayList<JumpPlatform> jumpPlatforms = new ArrayList<JumpPlatform>();
    BufferedImage[] jumpPlatformFrames = new BufferedImage[3];

    ArrayList<SpikePlatform> spikePlatforms = new ArrayList<SpikePlatform>();
    BufferedImage[] spikePlatformFrames = new BufferedImage[10];

    ArrayList<BirdObstacle> bird = new ArrayList<BirdObstacle>();
    BufferedImage[] birdFrames = new BufferedImage[9];
    BufferedImage[] RbirdFrames = new BufferedImage[9];

    int x = 100, y = 100, h = 150;

    float dy = 0;
    boolean right, left;

    int amoutofstatic_plat = 11;

    int amountofspike_plat = 0;

    int amountofmoving_plat = 1;

    int amountofjump_plat = 0;

    int amountofbird = 0;

    int amountofjumpOnce_plat = 0;

    int GameState = 11;
    int GameOverState = 6;
    int GamePlayState = 9;
    int GamePauseState = 7;

    int GameTitleState = 11;

    int commandNum = 0;

    int commandTitle = 0;

    public Rabbitup() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);
        getRabbitImage();
    }

    public void getRabbitImage() {
        try {

            up1 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\jump1.png"));
            up2 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\jump2.png"));
            up3 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\jump3.png"));
            fall1 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Fall1.png"));
            fall2 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Fall2.png"));
            fall3 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Fall3.png"));

            Rup1 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Rjump1.png"));
            Rup2 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Rjump2.png"));
            Rup3 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Rjump3.png"));
            Rfall1 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\RFall1.png"));
            Rfall2 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\RFall2.png"));
            Rfall3 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\RFall3.png"));
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame w = new JFrame("Rabbit Up");
        w.setResizable(false);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.add(new Rabbitup());
        w.pack();
        w.setLocationRelativeTo(null);
        w.setVisible(true);
    }

    @Override
    public void addNotify() {
        super.addNotify();

        if (thread == null) {
            thread = new Thread(this);
            isRunning = true;

            thread.start();

        }

    }

    public void start() {
        try {

            view = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            rabImage = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\jump2.png"));

            File plat_path = new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\platform.png");
            File moveplat_path = new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\moveplatform.png");

            skyBackground = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 1\\1.png"));
            cloud1 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 1\\2.png"));
            cloud2 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 1\\3.png"));
            star = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 1\\4.png"));

            skyBackground_1 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 2\\1.png"));
            cloud1_1 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 2\\2.png"));
            cloud2_1 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 2\\3.png"));
            star_1 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 2\\4.png"));

            skyBackground_2 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 5\\1.png"));
            cloud1_2 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 5\\2.png"));
            cloud2_2 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 5\\3.png"));
            star_2 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 5\\4.png"));

            skyBackground_3 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 8\\1.png"));
            cloud1_3 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 8\\2.png"));
            cloud2_3 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 8\\3.png"));
            star_3 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 8\\4.png"));

            skyBackground_4 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 4\\1.png"));
            cloud1_4 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 4\\2.png"));
            cloud2_4 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 4\\3.png"));
            star_4 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 4\\4.png"));

            skyBackground_5 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 3\\1.png"));
            cloud1_5 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 3\\2.png"));
            cloud2_5 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 3\\3.png"));
            star_5 = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Clouds\\Clouds 3\\4.png"));

            platform = ImageIO.read(plat_path);
            moveplatform = ImageIO.read(moveplat_path);

//            jumpplatform = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\jumpplatform.png"));
            jumpPlatformFrames[0] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\jumpplatform.png"));
            jumpPlatformFrames[1] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\jumpplatform2.png"));
            jumpPlatformFrames[2] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\jumpplatform3.png"));

//            spikeplatform = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\pngs\\unavoidable_roller\\unavoidable_spikes_just_roller_01.png"));
            spikePlatformFrames[0] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\pngs\\unavoidable_roller\\unavoidable_spikes_just_roller_01.png"));
            spikePlatformFrames[1] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\pngs\\unavoidable_roller\\unavoidable_spikes_just_roller_02.png"));
            spikePlatformFrames[2] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\pngs\\unavoidable_roller\\unavoidable_spikes_just_roller_03.png"));
            spikePlatformFrames[3] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\pngs\\unavoidable_roller\\unavoidable_spikes_just_roller_04.png"));
            spikePlatformFrames[4] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\pngs\\unavoidable_roller\\unavoidable_spikes_just_roller_05.png"));
            spikePlatformFrames[5] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\pngs\\unavoidable_roller\\unavoidable_spikes_just_roller_06.png"));
            spikePlatformFrames[6] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\pngs\\unavoidable_roller\\unavoidable_spikes_just_roller_07.png"));
            spikePlatformFrames[7] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\pngs\\unavoidable_roller\\unavoidable_spikes_just_roller_08.png"));
            spikePlatformFrames[8] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\pngs\\unavoidable_roller\\unavoidable_spikes_just_roller_09.png"));
            spikePlatformFrames[9] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\pngs\\unavoidable_roller\\unavoidable_spikes_just_roller_10.png"));

            birdFrames[0] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\bird1.png"));
            birdFrames[1] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\bird2.png"));
            birdFrames[2] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\bird3.png"));
            birdFrames[3] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\bird4.png"));
            birdFrames[4] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\bird5.png"));
            birdFrames[5] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\bird6.png"));
            birdFrames[6] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\bird7.png"));
            birdFrames[7] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\bird8.png"));
            birdFrames[8] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\bird9.png"));

            RbirdFrames[0] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Rbird1.png"));
            RbirdFrames[1] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Rbird2.png"));
            RbirdFrames[2] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Rbird3.png"));
            RbirdFrames[3] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Rbird4.png"));
            RbirdFrames[4] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Rbird5.png"));
            RbirdFrames[5] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Rbird6.png"));
            RbirdFrames[6] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Rbird7.png"));
            RbirdFrames[7] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Rbird8.png"));
            RbirdFrames[8] = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\Rbird9.png"));

            jumponceplatform = ImageIO.read(new File("C:\\Users\\AVI03\\Desktop\\minigame\\JavaApplication4\\src\\javaapplication4\\image\\jump_once.png"));
            platformposition = new PlatformPosition[20];

            generateBird(amountofbird);
            generateStaticPlatform(amoutofstatic_plat);
            generateMovingPlatform(amountofmoving_plat);
            generateJumpOncePlatform(amountofjumpOnce_plat);
            generateJumpPlatform(amountofjump_plat);
            generateSpikePlatform(amountofspike_plat);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void generateBird(int amount) {
        for (int i = 0; i < amount; i++) {
            BirdObstacle Platform1 = new BirdObstacle(new Random().nextInt(0,2), new Random().nextInt(10), birdFrames, RbirdFrames);
            
            bird.add(Platform1);
            
            playEffect(3);

        }
    }

    public void generateJumpPlatform(int amount) {
        for (int i = 0; i < amount; i++) {
            JumpPlatform jumpPlatform1 = new JumpPlatform(new Random().nextInt(400 - 70), new Random().nextInt(533), jumpPlatformFrames);
            jumpPlatform1.platformUsed = false;
            jumpPlatforms.add(jumpPlatform1);
        }
    }

    public void generateJumpOncePlatform(int amount) {
        for (int i = 0; i < amount; i++) {
            jumpOncePlatform Platform = new jumpOncePlatform(new Random().nextInt(400 - 70), new Random().nextInt(533)); // Example: Moves right at speed 2
            Platform.platformUsed = false;
            jumponcePlatforms.add(Platform);
        }
    }

    public void generateStaticPlatform(int amount) {
        for (int i = 0; i < amount; i++) {
            platformposition[i] = new PlatformPosition();
            platformposition[i].x = new Random().nextInt(400);
            platformposition[i].y = new Random().nextInt(533);
            platformposition[i].platformUsed = false;
        }
    }

    public void generateMovingPlatform(int amount) {
        for (int i = 0; i < amount; i++) {
            MovingPlatform movingPlatform1 = new MovingPlatform(new Random().nextInt(400 - 70), new Random().nextInt(533), new Random().nextInt(-3, 3)); // Example: Moves right at speed 2
            movingPlatform1.platformUsed = false;

            movingPlatforms.add(movingPlatform1);

        }
    }

    public void generateSpikePlatform(int amount) {
        for (int i = 0; i < amount; i++) {
            SpikePlatform spikePlatform1 = new SpikePlatform(new Random().nextInt(400 - 70), new Random().nextInt(533), spikePlatformFrames);
            spikePlatform1.platformUsed = false;

            spikePlatforms.add(spikePlatform1);
        }
    }
    
    public int finalscore = 0;

    public void update() {

        if (y > 500) {
            
            GameState = GameOverState;
            playEffect(4);
            setDefault();
            
            
        } else if (GameState == GamePauseState || GameState == GameTitleState || GameState == GameOverState) {

            //do nothing
        } else {

//        System.out.println(score);
            //check score more 20 create 3 spike plat
            //station 2
            if (score >= 25 && score < 40 && amoutofstatic_plat == 11 && amountofspike_plat == 0 && amountofmoving_plat == 1) {

                System.out.println("station 2");
                movingPlatforms.clear();
                spikePlatforms.clear();

                jumpPlatforms.clear();

                amountofspike_plat = 1;
                amountofmoving_plat = 2;
                amoutofstatic_plat = 9;

                amountofjump_plat = 1;

                generateSpikePlatform(amountofspike_plat);
                generateMovingPlatform(amountofmoving_plat);
                generateJumpPlatform(amountofjump_plat);
            } //station 3
            else if (score >= 40 && score < 60 && amoutofstatic_plat == 9 && amountofspike_plat == 1 && amountofmoving_plat == 2) {
                System.out.println("station 3");
                movingPlatforms.clear();
                spikePlatforms.clear();
                jumpPlatforms.clear();

                generateBird(1);

                amountofspike_plat = 2;
                amountofmoving_plat = 3;

                amountofjumpOnce_plat = 3;

                generateJumpOncePlatform(amountofjumpOnce_plat);

                amoutofstatic_plat = 6;

                generateSpikePlatform(amountofspike_plat);
                generateMovingPlatform(amountofmoving_plat);

            } else if (score >= 60 && score < 80 && amoutofstatic_plat == 6 && amountofspike_plat == 2 && amountofmoving_plat == 3) {
                System.out.println("station 4");
                movingPlatforms.clear();
                spikePlatforms.clear();
                jumpPlatforms.clear();
                jumponcePlatforms.clear();

                generateBird(1);

                amountofspike_plat = 2;
                amountofmoving_plat = 3;

                amountofjumpOnce_plat = 4;

                generateJumpOncePlatform(amountofjumpOnce_plat);

                amoutofstatic_plat = 5;

                generateSpikePlatform(amountofspike_plat);
                generateMovingPlatform(amountofmoving_plat);

            } else if (score >= 80 && score < 100 && amoutofstatic_plat == 5 && amountofspike_plat == 2 && amountofmoving_plat == 3) {

                System.out.println("station 5");
                movingPlatforms.clear();
                spikePlatforms.clear();
                jumpPlatforms.clear();
                jumponcePlatforms.clear();

                generateBird(1);

                amountofspike_plat = 3;
                amountofmoving_plat = 2;

                amoutofstatic_plat = 4;

                amountofjump_plat = 2;

                amountofjumpOnce_plat = 5;

                generateJumpOncePlatform(amountofjumpOnce_plat);

                generateSpikePlatform(amountofspike_plat);
                generateMovingPlatform(amountofmoving_plat);
                generateJumpPlatform(amountofjump_plat);
            } else if (score >= 100 && amoutofstatic_plat == 7 && amountofspike_plat == 3 && amountofmoving_plat == 2) {
                System.out.println("station 6");
                movingPlatforms.clear();
                spikePlatforms.clear();
                jumpPlatforms.clear();
                jumponcePlatforms.clear();

                generateBird(1);

                amountofspike_plat = 3;
                amountofmoving_plat = 2;

                amountofjumpOnce_plat = 7;

                generateJumpOncePlatform(amountofjumpOnce_plat);
                amoutofstatic_plat = 0;

                amountofjump_plat = 2;

                generateSpikePlatform(amountofspike_plat);
                generateMovingPlatform(amountofmoving_plat);
                generateJumpPlatform(amountofjump_plat);

            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;

            }

            //perform annimation suddenly when jump or fall
            if (direction.equals("up") && dy <= 0) {
                // The rabbit is starting to jump
                spriteNum = 1;
            } else if (direction.equals("down") && dy > 0) {
                // The rabbit is starting to fall
                spriteNum = 1;
            }

            if (dy < 0) {
                direction = "up";
            } else {
                direction = "down";
            }

            if (right) {
                direction = "right";
                x = x + 3;
            } else if (left) {
                direction = "left";
                x = x - 3;
            }

            //gravity
            dy = (float) (dy + 0.2);
            y = (int) (y + dy);

            //check fall
            //if rabbit jumpup more 150 reset rabbit =150
            //mean rabbit always in screen when jumpup
            if (y < h) {
                y = h;

                //make hai mun yu kub tee when jump
                for (int i = 0; i < amoutofstatic_plat; i++) {

                    platformposition[i].y = platformposition[i].y - (int) dy;

                    if (platformposition[i].y > 533) {
                        platformposition[i].y = 0;
                        platformposition[i].x = new Random().nextInt(400);

                        platformposition[i].platformUsed = false;
                    }
                }

                for (jumpOncePlatform platform : jumponcePlatforms) {
                    platform.y = platform.y - (int) dy;
                    if (platform.y > 533) {
                        platform.y = 0;
                        platform.x = new Random().nextInt(WIDTH - 70);

                        platform.platformUsed = false;
                    }
                }

                for (MovingPlatform platform : movingPlatforms) {
                    platform.y = platform.y - (int) dy;
                    if (platform.y > 533) {
                        platform.y = 0;
                        platform.x = new Random().nextInt(WIDTH - 70);

                        platform.platformUsed = false;
                    }
                }

                for (JumpPlatform platform : jumpPlatforms) {

                    platform.update();
                    platform.y = platform.y - (int) dy;
                    if (platform.y > 533) {
                        platform.y = 0;
                        platform.x = new Random().nextInt(WIDTH - 70);

                        platform.platformUsed = false;
                        platform.setRabbitHit(false);
                    }
                }

                for (SpikePlatform platform : spikePlatforms) {

                    platform.delayCounter++;

                    if (platform.delayCounter >= 5) {  // Update the frame every 5 * 10 milliseconds
                        platform.update();
                        platform.delayCounter = 0;

                    }
                    platform.y = platform.y - (int) dy;
                    if (platform.y > 533) {
                        platform.y = 0;
                        platform.x = new Random().nextInt(WIDTH - 70);

                        platform.platformUsed = false;
                    }

                }

                for (BirdObstacle platform : bird) {

                    platform.y = platform.y - (int) dy;
                    if (platform.birdspeed > 0) {
                        platform.isMovingRight = true;
                    } else {
                        platform.isMovingRight = false;
                    }

                }

            }
            

            for (BirdObstacle platform : bird) {
                platform.update();
                platform.x += platform.birdspeed;
                

                if (platform.x > WIDTH || platform.x < -20) {
                    platform.birdspeed = -platform.birdspeed;
                    platform.isMovingRight = !platform.isMovingRight;
                }
                 if (platform.birdspeed > 0) {
                        platform.isMovingRight = true;
                    } else {
                        platform.isMovingRight = false;
                    }

                int rabbitRight = x + 40;
                int rabbitLeft = x + 40;
                int rabbitBottom = y + 65;
                int rabbitTop = y;

                int birdRight = platform.x + 65;
                int birdLeft = platform.x;
                int birdBottom = platform.y + 30; // Assuming bird height is 30
                int birdTop = platform.y;

                // Check for overlap between the rabbit and bird bounding boxes
                if (rabbitRight > birdLeft && rabbitLeft < birdRight
                        && rabbitBottom > birdTop && rabbitTop < birdBottom) {
                    // Collision detected, handle it here (e.g., end the game)
                    GameState = GameOverState;
                }

            }

            for (MovingPlatform platform : movingPlatforms) {
                platform.x += platform.speed;
                if (platform.x > WIDTH - 70 || platform.x < 0) {
                    platform.speed = -platform.speed;
                }
                if (platform.y > HEIGHT) {
                    platform.y = 0;
                    platform.x = new Random().nextInt(400);
                }

                if (y + 70 > platform.y && y + 70 < platform.y + 20 && dy > 0) {
                    if (x + 50 > platform.x && x + 20 < platform.x + 70) {
                        // The rabbit can jump on the platform
                        dy = -11;
                        playEffect(0);
                        if (!platform.platformUsed) {

                            score += 2;
                            System.out.println(score);
                            platform.platformUsed = true;

                        }
                    }
                }
            }

            for (jumpOncePlatform platform : jumponcePlatforms) {

                if (y + 70 > platform.y && y + 70 < platform.y + 20 && dy > 0) {
                    if (x + 50 > platform.x && x + 20 < platform.x + 70) {
                        // The rabbit can jump on the platform
                        dy = -11;
                        playEffect(0);
                        if (!platform.platformUsed) {
                            score += 3;
                            platform.x = 600;
                            platform.y = 600;

                            //remove when touch
                            platform.platformUsed = true;

                        }

                    }
                }
            }

            for (JumpPlatform platform : jumpPlatforms) {

                if (y + 70 > platform.y && y + 70 < platform.y + 20 && dy > 0) {
                    if (x + 50 > platform.x && x + 20 < platform.x + 70) {
                        // The rabbit can jump on the platform
                        dy = -20;
                        playEffect(2);
                        platform.setRabbitHit(true);
                        if (!platform.platformUsed) {
                            score += 10;
                            platform.platformUsed = true;

                        }

                    }
                }
            }

            for (SpikePlatform platform : spikePlatforms) {
                platform.delayCounter++;

                if (platform.delayCounter >= 5) {  // Update the frame every 5 * 10 milliseconds
                    platform.update();
                    platform.delayCounter = 0;

                }

                if (y + 70 > platform.y && y + 70 < platform.y + 20 && dy > 0) {
                    if (x + 50 > platform.x && x + 20 < platform.x + 70) {
                        // The rabbit can jump on the platform
                        dy = -5;
                        playEffect(1);
                        if (!platform.platformUsed) {
                            if (score - 6 < 0) {
                                score = 0;
                            } else {
                                score -= 6;

                            }
                            platform.platformUsed = false;
                            //if player keep jump going to decrease forever
                        }

                    }
                }
            }

            // Check collision with static platforms
            for (int i = 0; i < amoutofstatic_plat; i++) {
                if (x + 50 > platformposition[i].x && x + 20 < platformposition[i].x + 68
                        && y + 70 > platformposition[i].y && y + 70 < platformposition[i].y + 14 && dy > 0) {
                    
                    
                    dy = -11;
                    
                    playEffect(0);
                    
                    
                if (!platformposition[i].platformUsed) {

                    score += 1;
                    System.out.println(score);
                    platformposition[i].platformUsed = true;

                }
                
                
                }
            }

        }

    }
    
    public void playEffect(int i){
        sound.setFile(i);
        sound.play();
        sound.setVolume(-30f);
    }
    
    public int getxforCenteredText(String text , Graphics2D g2){
    
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = WIDTH/2 - length/2;
        return x;
    }
    
    
    
    

    public void draw() {

        Graphics2D g2 = (Graphics2D) view.getGraphics();

        if (GameState == GameOverState) {
            
            g2.setColor(new Color(0, 0, 0, 10));
            g2.fillRect(0, 0, WIDTH, HEIGHT);

            int x;
            int y;

            String text;
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));
            text = "Game Over";

            g2.setColor(Color.red);

            x = WIDTH / 3 - 10;
            y = 200;

            g2.drawString(text, x, y);

            g2.setColor(Color.WHITE);
            g2.drawString(text,getxforCenteredText(text, g2), y - 4);
            
            g2.setFont(g2.getFont().deriveFont(25f));
            x = (WIDTH / 3) + 40;
            y += 50;
            g2.drawString("You got : "+score+" score!!", getxforCenteredText("You got : "+score+" score!!", g2), y - 4);

            //retry
            g2.setFont(g2.getFont().deriveFont(20f));
            text = "Retry";
            x = (WIDTH / 3) + 40;
            y += 50;
            g2.drawString(text, getxforCenteredText(text, g2), y);
            

            if (commandNum == 0) {
                g2.drawString(">", x - 40, y);

            }

            //quit
            text = "Back to menu";

            x = (WIDTH / 3);
            y += 50;

            g2.drawString(text, x, y);

            if (commandNum == 1) {
                g2.drawString(">", x - 40, y);

            }

        } else if (GameState == GamePauseState) {

            int x;
            int y;

            String text;
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));
            text = "Game Pause";

            x = (WIDTH / 3) + 45;
            y = HEIGHT / 2;

            g2.drawString(text, x, y);

        } else if (GameState == GameTitleState) {

            g2.drawImage(skyBackground_5, 0, 0, WIDTH, HEIGHT, null);
            g2.drawImage(cloud1_5, 0, 0, cloud1_5.getWidth(), cloud1_5.getHeight(), null);
            g2.drawImage(cloud2_5, 0, 0, cloud2_5.getWidth(), cloud2_5.getHeight(), null);
            g2.drawImage(star_5, 0, 0, star_5.getWidth(), star_5.getHeight(), null);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36f));

            g2.setColor(Color.white);
            int x = 96;
            int y = (HEIGHT / 4) - 34;
            g2.drawString("Rabbit Up", x, y);

            g2.drawImage(rabImage, x + 180, y - 30, 50, 60, null);

            g2.drawImage(platform, x, y + 100, 60, 15, null);
            g2.drawImage(platform, x + 50, y + 120, 60, 15, null);
            g2.drawImage(moveplatform, x + 150, y + 120, 60, 15, null);
            g2.drawImage(jumponceplatform, x + 100, y + 80, 60, 15, null);
            g2.drawImage(jumponceplatform, x - 40, y + 60, 60, 15, null);

            g2.drawImage(spikePlatformFrames[0], x + 20, y + 150, 60, 25, null);
            g2.drawImage(birdFrames[0], x + 200, y + 150, 60, 40, null);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24f));
            g2.drawString("Play Now", x + 50, y + 290);

            if (commandNum == 0) {
                g2.drawString(">", x + 10, y + 290);

            }

            g2.drawString("Quit", x + 80, y + 340);

            if (commandNum == 1) {
                g2.drawString(">", x + 40, y + 340);

            }

        } else {
            

            if (score >= 0 && score < 25) {
                g2.drawImage(skyBackground, 0, 0, WIDTH, HEIGHT, null);
                g2.drawImage(cloud1, 0, 0, cloud1.getWidth(), cloud1.getHeight(), null);
                g2.drawImage(cloud2, 0, 0, cloud2.getWidth(), cloud2.getHeight(), null);
                g2.drawImage(star, 0, 0, star.getWidth(), star.getHeight(), null);

            }
            if (score >= 25 && score < 40) {
                g2.drawImage(skyBackground_1, 0, 0, WIDTH, HEIGHT, null);
                g2.drawImage(cloud1_1, 0, 0, cloud1_1.getWidth(), cloud1_1.getHeight(), null);
                g2.drawImage(cloud2_1, 0, 0, cloud2_1.getWidth(), cloud2_1.getHeight(), null);
                g2.drawImage(star_1, 0, 0, star_1.getWidth(), star_1.getHeight(), null);

            }

            if (score >= 40 && score < 60) {
                g2.drawImage(skyBackground_2, 0, 0, WIDTH, HEIGHT, null);
                g2.drawImage(cloud1_2, 0, 0, cloud1_2.getWidth(), cloud1_2.getHeight(), null);
                g2.drawImage(cloud2_2, 0, 0, cloud2_2.getWidth(), cloud2_2.getHeight(), null);
                g2.drawImage(star_2, 0, 0, star_2.getWidth(), star_2.getHeight(), null);

            }
            if (score >= 60 && score < 80) {
                g2.drawImage(skyBackground_3, 0, 0, WIDTH, HEIGHT, null);
                g2.drawImage(cloud1_3, 0, 0, cloud1_3.getWidth(), cloud1_3.getHeight(), null);
                g2.drawImage(cloud2_3, 0, 0, cloud2_3.getWidth(), cloud2_3.getHeight(), null);
                g2.drawImage(star_3, 0, 0, star_3.getWidth(), star_3.getHeight(), null);

            }
            if (score >= 80 && score < 100) {
                g2.drawImage(skyBackground_4, 0, 0, WIDTH, HEIGHT, null);
                g2.drawImage(cloud1_4, 0, 0, cloud1_4.getWidth(), cloud1_4.getHeight(), null);
                g2.drawImage(cloud2_4, 0, 0, cloud2_4.getWidth(), cloud2_4.getHeight(), null);
                g2.drawImage(star_4, 0, 0, star_4.getWidth(), star_4.getHeight(), null);

            }
            if (score >= 100) {
                g2.drawImage(skyBackground_5, 0, 0, WIDTH, HEIGHT, null);
                g2.drawImage(cloud1_5, 0, 0, cloud1_5.getWidth(), cloud1_5.getHeight(), null);
                g2.drawImage(cloud2_5, 0, 0, cloud2_5.getWidth(), cloud2_5.getHeight(), null);
                g2.drawImage(star_5, 0, 0, star_5.getWidth(), star_5.getHeight(), null);

            }

            BufferedImage image = null;
            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    break;

                case "down":

                    if (spriteNum == 1) {
                        image = fall1;
                    }
                    break;

                case "left":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    if (spriteNum == 3) {
                        image = up3;
                    }
                    break;

                case "right":
                    if (spriteNum == 1) {
                        image = Rup1;
                    }
                    if (spriteNum == 2) {
                        image = Rup2;
                    }
                    if (spriteNum == 3) {
                        image = Rup3;
                    }
                    break;
            }
            g2.drawImage(image, x, y, 50, 60, null);

            for (int i = 0; i < amoutofstatic_plat; i++) {
                g2.drawImage(platform, platformposition[i].x, platformposition[i].y, 70, 20, null);

            }

            for (MovingPlatform platform : movingPlatforms) {
                g2.drawImage(moveplatform, platform.x, platform.y, 70, 20, null);
            }

            for (JumpPlatform platform : jumpPlatforms) {
                g2.drawImage(platform.getCurrentFrame(), platform.x, platform.y, 40, 30, null);
            }

            for (SpikePlatform platform : spikePlatforms) {
                g2.drawImage(platform.getCurrentFrame(), platform.x, platform.y, 60, 30, null);

            }

            for (BirdObstacle platform : bird) {
                if (platform.isMovingRight) {
                    g2.drawImage(platform.getCurrentRFrame(), platform.x, platform.y, 40,30, null);
                } else {
                    g2.drawImage(platform.getCurrentFrame(), platform.x, platform.y, 40, 30, null);
                }
            }

            for (jumpOncePlatform platform : jumponcePlatforms) {

                g2.drawImage(jumponceplatform, platform.x, platform.y, 70, 20, null);
            }
            
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20f));
            g2.drawString("Score: " + score, 20, 20);

        }
        Graphics g = getGraphics();

        g.drawImage(view, 0, 0, WIDTH, HEIGHT, null);
        g.dispose();

    }

    @Override
    public void run() {

        try {
            requestFocus();
            start();

            while (isRunning) {
                update();
                draw();
                thread.sleep(10);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void setDefault() {
        x = 100;
        y = 100;
        dy = 0;

        amoutofstatic_plat = 11;

        amountofspike_plat = 0;

        amountofmoving_plat = 1;

        amountofjump_plat = 0;

        amountofbird = 0;

        amountofjumpOnce_plat = 0;

        movingPlatforms.clear();
        jumpPlatforms.clear();
        spikePlatforms.clear();
        jumponcePlatforms.clear();
        bird.clear();

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (GameState == GameOverState) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                commandNum--;
                if (commandNum < 0) {
                    commandNum = 1;
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_S) {
                commandNum++;
                if (commandNum > 1) {
                    commandNum = 0;
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                if (commandNum == 0) {
                    GameState = GamePlayState;
                    score =0;
                    setDefault();
                    start();

                } else if (commandNum == 1) {
                    System.out.println("Enter");
                    GameState = GameTitleState;
                    System.out.println(GameState);
                    setDefault();
                }
            }

        } 
        
        else if (GameState == GameTitleState) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                commandNum--;
                if (commandNum < 0) {
                    commandNum = 1;
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_S) {
                commandNum++;
                if (commandNum > 1) {
                    commandNum = 0;
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                if (commandNum == 0) {
                    GameState = GamePlayState;
                    setDefault();
                    start();

                } else if (commandNum == 1) {
                    System.exit(0);
                }
            }

        }
        
        else {

            if (e.getKeyCode() == KeyEvent.VK_D) {
                right = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                left = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

                if (GameState == GamePlayState) {
                    System.out.println("pause");
                    GameState = GamePauseState;
                } else if (GameState == GamePauseState) {
                    System.out.println("play");
                    GameState = GamePlayState;
                }
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }

    }

}
