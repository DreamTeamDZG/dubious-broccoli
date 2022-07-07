import greenfoot.*;
/**
 * Write a description of class BROCCOLI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BROCCOLI extends NEEDSBACKGROUND
{
    private static int dubious_offset;
    
    private GreenfootImage icon;
    private GreenfootImage image;
    private int dubious_lvl;
    public BROCCOLI(){
        //super("blocks/plants/broccoli/",5);
        dubious_lvl = 1;
        image = new GreenfootImage("blocks/plants/broccoli/broccoli_dubious_lvl_"+dubious_lvl+"_inventory_icon.png");
        
        
        icon = image;
        setImage(image);
        //System.out.println("dubious_offset"+dubious_offset);
        //icon = new GreenfootImage("blocks/plants/broccoli/broccoli_dubious_lvl_" + dubious_lvl + "_inventory_icon.png");
    }
    
    public int get_dubious_lvl(){
        int actual_num = dubious_lvl + dubious_offset;
        if(actual_num > 5){
            actual_num = actual_num - 5;
        }
        return actual_num;
    }
    
    public void set_dubious_lvl(int lvl){
        int actual_num = lvl - dubious_offset;
        if(actual_num < 1){
            actual_num = actual_num + 5;
        }
        dubious_lvl = lvl;
    }
    
    public static void set_dubious_offset(int offset){
        dubious_offset = offset;
    }
    
    public static int get_dubious_offset(){
        return dubious_offset;
    }
    
    
    public GreenfootImage get_icon(){
        return get_image();
    }
    
    public GreenfootImage get_image(){
        int actual_num = dubious_lvl + dubious_offset;
        if(actual_num > 5){
            actual_num = actual_num - 5;
        }
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
