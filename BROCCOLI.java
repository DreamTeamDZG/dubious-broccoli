import greenfoot.*;
/**
 * Write a description of class BROCCOLI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BROCCOLI extends BLOCK
{
    private GreenfootImage icon;
    private GreenfootImage image;
    private int dubious_lvl;
    public BROCCOLI(){
        //super("blocks/plants/broccoli/",5);
        dubious_lvl = 1;
        image = new GreenfootImage("blocks/plants/broccoli/broccoli_dubious_lvl_"+dubious_lvl+"_inventory_icon.png");
        
        
        icon = image;
        setImage(image);
        //icon = new GreenfootImage("blocks/plants/broccoli/broccoli_dubious_lvl_" + dubious_lvl + "_inventory_icon.png");
    }
    
    public int get_dubious_lvl(){
        return dubious_lvl;
    }
    
    public void set_dubious_lvl(int lvl){
        dubious_lvl = lvl;
    }
    
    
    public GreenfootImage get_icon(){
        return get_image();
    }
    
    public GreenfootImage get_image(){
        image = new GreenfootImage("blocks/plants/broccoli/broccoli_dubious_lvl_"+dubious_lvl+"_inventory_icon.png");
        
        return image;
    }
    
    public boolean is_stackable(BLOCK block){
        if(!(block instanceof BROCCOLI)){
            return false;
        }
        return this.dubious_lvl == ((BROCCOLI) block).dubious_lvl;
    }
    
    public String get_name(){
        return "broccoli";
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
