import greenfoot.*;
/**
 * Write a description of class WATER here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WATER extends BLOCK 
{
    // instance variables - replace the example below with your own
    private GreenfootImage icon;
    /**
     * Constructor for objects of class WATER
     */
    public WATER(){
        icon = new GreenfootImage("blocks/water/water.png");
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
        return "Water";
    }
    
    public int will_drop_item(TOOL tool){
        return -1;
    }
    
    public boolean is_placeable(){
        return false;
    }
}
