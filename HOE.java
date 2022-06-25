import greenfoot.GreenfootImage;

/**
 * Write a description of class HOE here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class HOE extends TOOL 
{
    protected GreenfootImage icon;

    /**
     * Constructor for objects of class HOE
     */
    public HOE()
    {
        icon = new GreenfootImage("tools/pickaxe_1_small");
    }

    public TOOLKIND get_kind(){
        return TOOLKIND.HOE;
    }
    
    public GreenfootImage get_icon(){
        return icon;
    }
    
    public String get_name(){
        return "HOE";
    }
}
