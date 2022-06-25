import greenfoot.GreenfootImage;

/**
 * Write a description of class PICKAXE here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class PICKAXE extends TOOL 
{
    protected GreenfootImage icon;
    public TOOLKIND get_kind(){
        return TOOLKIND.PICKAXE;
    }
    
    public GreenfootImage get_icon(){
        return icon;
    }
    public String get_name(){
        return "HOE";
    }
}
