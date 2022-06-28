import greenfoot.*;
/**
 * Write a description of class WATER here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WATER extends BLOCK 
{
    // instance variables - replace the example below with your own
    private GreenfootImage icon;
    /**
     * Constructor for objects of class WATER
     */
    public WATER(){
        icon = new GreenfootImage("blocks/water/water.png");
    }
    
    public void act(){
        MAINWORLD world = (MAINWORLD) getWorld();
        POSITION[] blocks_positions = new POSITION[4];
        
        blocks_positions[0] = CHARACTER.get_next_block_position(get_position(), DIRECTION.ZERO, DIRECTION.NORTH);
        blocks_positions[1] = CHARACTER.get_next_block_position(get_position(), DIRECTION.ZERO, DIRECTION.SOUTH);
        blocks_positions[2] = CHARACTER.get_next_block_position(get_position(), DIRECTION.EAST, DIRECTION.ZERO);
        blocks_positions[3] = CHARACTER.get_next_block_position(get_position(), DIRECTION.WEST, DIRECTION.NORTH);
        
        for(int i = 0; i < 4; i++){
            BLOCK block = world.get_block_at(blocks_positions[i]);
            if(block instanceof FIELD){
                ((FIELD) block).water();
            }
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
        return "Water";
    }
    
    public int will_drop_item(TOOL tool){
        return 1;
    }
    
    public boolean is_placeable(){
        return false;
    }
}
