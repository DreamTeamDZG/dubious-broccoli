import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AXESTONE here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AXESTONE extends AXE
{
    GreenfootImage icon;
    public AXESTONE(){
        icon = new GreenfootImage("tools/axe_stone.png");
    }
    
    public double get_speed_multiplier(){
        return 1.5;
    }
}
