import greenfoot.*;
/**
 * Write a description of class WILDGARLIC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WILDGARLIC extends BLOCK 
{
    // instance variables - replace the example below with your own
    private GreenfootImage icon;
    private GreenfootImage image;
    /**
     * Constructor for objects of class WILDGARLIC
     */
    public WILDGARLIC(){
        icon = new GreenfootImage("blocks/wildgarlic/wildgarlic_plant.png");
        image = icon;
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
        return "wildgarlic";
    }
    
    public int will_drop_item(TOOL tool){
        return 1;
    }
    
    public boolean is_placeable(){
        return false;
    }
}
