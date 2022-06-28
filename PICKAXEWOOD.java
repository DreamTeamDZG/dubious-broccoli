import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PICKAXEWOOD here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PICKAXEWOOD extends PICKAXE
{
    private static final BLOCK[] ingredients = new BLOCK[]{new WOOD(), new WOOD(), new WOOD(), new WOOD()};
    public double get_speed_multiplier(){
        return 1;
    }
    
    public static BLOCK[] get_ingredients(){
        
        return ingredients;
    }
    public double get_speed_multiplier(){
        return 1;
    }
}
