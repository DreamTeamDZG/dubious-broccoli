import greenfoot.GreenfootImage;

/**
 * Write a description of class AXE here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class AXE extends TOOL 
{
    protected GreenfootImage icon;
    
    public TOOLKIND get_kind(){
        return TOOLKIND.AXE;
    }
    
    public GreenfootImage get_icon(){
        return icon;
    }
    
    public String get_name(){
        return "HOE";
    }
}
