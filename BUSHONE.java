import greenfoot.*;
/**
 * Write a description of class BUSHONE here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BUSHONE extends BLOCK 
{
    // instance variables - replace the example below with your own
    private GreenfootImage icon;
    private GreenfootImage image;
    /**
     * Constructor for objects of class BUSHONE
     */
    public BUSHONE(){
        icon = new GreenfootImage("blocks/bush/bush_small_1.png");
        image = new GreenfootImage("blocks/bush/bush_small_1.png");
    }
    
    public GreenfootImage get_icon(){
        return icon;
    }
    
    public GreenfootImage get_image(){
        return image;
    }
    
    public boolean is_stackable(BLOCK block){
        return true;
    }
    
    public String get_name(){
        return "bushone";
    }
    
    public int will_drop_item(TOOL tool){
        return 1;
    }
    
    public boolean is_placeable(){
        return false;
    }
}
