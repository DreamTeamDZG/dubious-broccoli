import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AXEWOOD here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AXEWOOD extends AXE
{
    GreenfootImage icon;
    public AXEWOOD(){
        icon = new GreenfootImage("tools/axe_wood.png");
    }
    
    public double get_speed_multiplier(){
        return 1;
    }
}