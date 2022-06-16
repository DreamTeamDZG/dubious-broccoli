import greenfoot.*;
/**
 * Write a description of class INVENTORY here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class INVENTORY
{
    private INVENTORYSLOT[] slots;
    private static final int num_slots = 7;
    private static final double size_percent = 0.5;
    private GreenfootImage background_image;
    //private GreenfootImage background_image_empty; //maybe add this later
    /**
     * Constructor for objects of class INVENTORY
     */
    public INVENTORY(GreenfootImage background_image/*, GreenfootImage background_image_empty*/)
    {
        slots = new INVENTORYSLOT[num_slots];
        this.background_image = background_image;
    }
    public void init(int screen_size_x, int screen_size_y, MAINWORLD main_world){
        int left_border = (int) (screen_size_x * (1-size_percent) / 2);
        int size_inv = (int) (screen_size_x * size_percent);
        int size_per_slot = (int) size_inv / num_slots;
        for(int i = 0; i < slots.length; i++){
            INVENTORYSLOT slot = new INVENTORYSLOT(size_per_slot, size_per_slot);
            // debug
            slot.setImage(background_image);
            // debug
            slot.getImage().scale(size_per_slot, size_per_slot);
            int y = screen_size_y - (int) (size_per_slot / 2);
            int x = left_border + (int) (size_per_slot * (i + 0.5));
            main_world.addObject(slot, x, y);
            slot.init(main_world);
            slots[i] = slot;
        }
    }
    
    public boolean add_item(ITEM item){
        for(INVENTORYSLOT slot : slots){
            if(slot.add(item)){
                return true;
            }
        }
        return false;
    }
    
    public ITEM retrieve_item(ITEM item){
        for(INVENTORYSLOT slot: slots){
            if(slot.get_item() != null)
                if(slot.get_item().get_name() == item.get_name()){
                    return slot.retrieve_item();
                }
            }
        return null;
        }
    }
