import greenfoot.*;
/**
 * Write a description of class STONE here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class STONE extends NEEDSBACKGROUND 
{
    // instance variables - replace the example below with your own
    private GreenfootImage icon;
    /**
     * Constructor for objects of class STONE
     */
    public STONE(){
        icon = new GreenfootImage("blocks/stone/stone_small_1.png");
        
    }
    
    public GreenfootImage get_icon(){
        return icon;
    }
    
    public GreenfootImage get_image(){
        return icon;
    }
    
    public boolean is_stackable(BLOCK block){
        return true;
    }
    
    public String get_name(){
        return "Stone";
    }
    
    public int will_drop_item(TOOL tool){
        return 1;
    }
    
    public boolean is_placeable(){
        return true;
    }
}
