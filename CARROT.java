import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BROCCOLI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CARROT extends BLOCK
{
    private GreenfootImage icon;
    private GreenfootImage image;
    public CARROT(){
        //super("blocks/plants/carrot/",5);
        icon = new GreenfootImage("inventory/icons/carrot/carrot_icon.png");
        image = icon;
        setImage(image);
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
        return "carrot";
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
