import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PICKAXESTONE here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PICKAXESTONE extends PICKAXE
{
    private static final BLOCK[] ingredients = new BLOCK[] {new STONE(),new STONE(), new WOOD()};
    
    public double get_speed_multiplier(){
        return 1.5;
    }
    
    public static BLOCK[] get_ingredients(){
        
        return ingredients;
    }
}
