import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class STORY here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class STORY extends World
{

    /**
     * Constructor for objects of class STORY.
     * 
     */
    public STORY()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(500, 300, 1); 
    }
    
    public void act(){
         if(Greenfoot.getKey() != null){
                Greenfoot.setWorld(new MAINWORLD());
            }
    }
}
