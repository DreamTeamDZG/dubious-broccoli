import greenfoot.*;
/**
 * Write a description of class GRASS here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GRASS extends BLOCK 
{
    // instance variables - replace the example below with your own
    private GreenfootImage icon;
    /**
     * Constructor for objects of class GRASS
     */
    public GRASS(){
        icon = new GreenfootImage("blocks/grass/grass_small_1.png");
    }
    
    public void use(TOOL tool){
        if(tool.get_kind() == TOOLKIND.HOE){
            MAINWORLD world = (MAINWORLD) getWorld();
            world.delete_block(this);
            FIELD field = new FIELD();
            world.add_entity(field);
        } else {
        super.use(tool);
        }
    }
    
    public GreenfootImage get_icon(){
        return icon;
    }
    
    public GreenfootImage get_image(){
        return icon;
    }
    
    public boolean is_stackable(){
        return true;
    }
    
    public String get_name(){
        return "Grass";
    }
    
    public int will_drop_item(TOOL tool){
        return -1;
    }
    
    public boolean is_placeable(){
        return false;
    }
}
