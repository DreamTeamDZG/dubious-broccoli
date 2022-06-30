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
    private int dubious_lvl;
    public BROCCOLI(){
        super("blocks/plants/broccoli/",5);
        image = new GreenfootImage("blocks/plants/broccoli/broccoli_dubious_lvl_1_inventory_icon.png");
        
        dubious_lvl = Greenfoot.getRandomNumber(5) + 1;
        icon = image;
        setImage(image);
        //icon = new GreenfootImage("blocks/plants/broccoli/broccoli_dubious_lvl_" + dubious_lvl + "_inventory_icon.png");
    }
    
    public int get_dubious_lvl(){
        return dubious_lvl;
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
        return "broccoli";
    }
    
    public int will_drop_item(TOOL tool){
        if(tool.get_kind() == TOOLKIND.HOE && is_fully_grown()){
            return 1;
        }
        return -1;
    }
    
    public boolean is_placeable(){
        return true;
    }
}
