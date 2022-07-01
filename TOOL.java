import greenfoot.*;
/**
 * Write a description of class TOOL here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class TOOL extends BLOCK
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class TOOL
     */
    public TOOL(){
    }
    public abstract TOOLKIND get_kind();
    
    public boolean is_placeable(){
        return false;
    }
    
    public GreenfootImage get_image(){
        return null;
    }
    
    public boolean is_stackable(BLOCK block){
        return false;
    }

    public int will_drop_item(TOOL tool){
        return -1;
    }
    
    public abstract double get_speed_multiplier();
    
    public static BLOCK[] get_ingredients(){
        return null;
    };
}
