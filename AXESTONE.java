import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AXESTONE here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AXESTONE extends AXE
{
    private static final BLOCK[] ingredients = new BLOCK[]{new WOOD(),new WOOD(), new STONE()};

    public AXESTONE(){
        //icon = new GreenfootImage();
    }
    
    public double get_speed_multiplier(){
        return 1.5;
    }

    
    public static BLOCK[] get_ingredients(){
        
        return ingredients;
    }

}
