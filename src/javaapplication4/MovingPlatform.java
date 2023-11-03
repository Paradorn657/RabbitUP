/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication4;

/**
 *
 * @author AVI03
 */
class MovingPlatform extends PlatformPosition {
    int speed; 
    
    

    public MovingPlatform(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        
        if(speed==0){
         this.speed = 3;
        }else{
        this.speed = speed;
        }
       
    }
    
}


