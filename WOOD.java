import greenfoot.*;
/**
 * Write a description of class TREE here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WOOD extends PLANT
{
    private GreenfootImage icon;
    private GreenfootImage image;
    public WOOD(){
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
        return "Wood";
    }
    
    public int will_drop_item(TOOL tool){
        if(tool.get_kind() == TOOLKIND.AXE){
            return 100;
        }
        return -1;
    }
    
    public boolean is_placeable(){
        return true;
    }
}
