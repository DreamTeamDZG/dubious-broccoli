import greenfoot.*;
/**
 * Write a description of class ITEM here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class ITEM  
{
    private String name;
    private boolean stackable;
    private GreenfootImage icon;
    
    public abstract String get_name();
    
    public abstract boolean is_stackable();
    
    public abstract GreenfootImage get_icon();
}
