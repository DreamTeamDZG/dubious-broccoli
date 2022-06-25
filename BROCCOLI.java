import greenfoot.*;
/**
 * Write a description of class BROCCOLI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BROCCOLI extends PLANT
{
    private GreenfootImage icon;
    private GreenfootImage image;
    public BROCCOLI(){
        super("",0);
    }
    
    
    
    public GreenfootImage get_icon(){
        return icon;
    }
    
    public GreenfootImage get_image(){
        return image;
    }
    
    public boolean is_stackable(){
        return true;
    }
    
    public String get_name(){
        return "Broccoli";
    }
    
    public int will_drop_item(TOOL tool){
        if(tool.get_kind() == TOOLKIND.HOE){
            return 1;
        }
        return -1;
    }
    
    public boolean is_placeable(){
        return true;
    }
}
