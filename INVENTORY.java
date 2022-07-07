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
    private static final int y_offset = -MAINWORLD.block_size - 1;
    private GreenfootImage background_image;
    
    private int selected_slot;
    private GreenfootImage background_image_selected;
    /**
     * Constructor for objects of class INVENTORY
     */
    public INVENTORY(GreenfootImage background_image, GreenfootImage background_image_selected)
    {
        selected_slot = 0;
        slots = new INVENTORYSLOT[num_slots];
        this.background_image = background_image;
        this.background_image_selected = background_image_selected;
    }
    public void init(int screen_size_x, int screen_size_y, MAINWORLD main_world){
        int left_border = (int) (screen_size_x * (1-size_percent) / 2);
        int size_inv = (int) (screen_size_x * size_percent);
        int size_per_slot = (int) size_inv / num_slots;
        for(int i = 0; i < slots.length; i++){
            INVENTORYSLOT slot = new INVENTORYSLOT(size_per_slot, size_per_slot);
            slot.setImage(background_image);
            slot.getImage().scale(size_per_slot, size_per_slot);
            background_image_selected.scale(size_per_slot, size_per_slot);
            
            int y = screen_size_y - (int) (size_per_slot / 2) + y_offset;
            int x = left_border + (int) (size_per_slot * (i + 0.5));
            main_world.addObject(slot, x, y);
            slot.init(main_world);
            slots[i] = slot;
        }
        update_view();
    }
    
    public boolean add_item(BLOCK item){
        for(INVENTORYSLOT slot : slots){
            if(slot.add(item)){
                return true;
            }
        }
        return false;
    }
    
    public BLOCK retrieve_item(BLOCK item){
        for(INVENTORYSLOT slot: slots){
            if(slot.get_item() != null)
                if(slot.get_item().get_name() == item.get_name()){
                    return slot.retrieve_item();
                }
            }
        return null;
    }
    
    public void update_view(){
        for(INVENTORYSLOT slot: slots){
            slot.setImage(background_image);
        }
        slots[selected_slot].setImage(background_image_selected);
    }
        
    public BLOCK retrieve_item_idx(int idx){
        if(slots[idx].get_item() != null){
            return slots[idx].retrieve_item();
        }
        return null;
    }
        
    public BLOCK get_item_idx(int idx){
        return slots[idx].get_item();
    }
        
    public void select_next_slot(){
        selected_slot++;
        if(selected_slot > num_slots-1){
            selected_slot = 0;
        }
    }
    
    public void select_prev_slot(){
        selected_slot--;
        if(selected_slot < 0){
            selected_slot = num_slots-1;
        }
    }
    

    public int get_selected_slot(){
        return selected_slot;
    }
    
    public BLOCK get_selected_item(){
        return get_item_idx(selected_slot);
    }
    
    public BLOCK retrieve_selected_item(){
        return retrieve_item_idx(selected_slot);
    }
    }
