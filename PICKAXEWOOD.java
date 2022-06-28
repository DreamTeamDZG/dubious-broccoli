import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PICKAXEWOOD here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PICKAXEWOOD extends PICKAXE
{
    GreenfootImage icon;
    PICKAXEWOOD(){
        icon = new GreenfootImage("tools/pickaxe_wood.png");
    }
    public double get_speed_multiplier(){
        return 1;
    }
}
