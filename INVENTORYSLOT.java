import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class INVENTORYSLOT here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class INVENTORYSLOT extends Actor
{
    private int height;
    private int width;

    private BLOCK item;
    private int count;
    private static final int max = 9;

    private DIGIT digit;
    private POSITION digit_position;
    private static final double y_size_ratio = 0.18; // relative to Height
    private static final POSITION offset_ratio_percentage = new POSITION(-15, -16); //relative to Height

    private IMAGESHOWER item_icon;
    private static final double icon_size_ratio = 0.8;
    private POSITION item_icon_position;
    public INVENTORYSLOT(int height, int width){
        this.height = height;
        this.width = width;
        item = null;
        count = 0;
        double y_size = y_size_ratio * height;
        System.out.println("y_size"+ height);
        digit = new DIGIT(y_size);
    }

    public boolean add(BLOCK item){
        if(this.item == null){
            add_item(item);
            ensure_item_in_world(item.get_icon());
            return true;
        }
        if( this.item.get_name() == item.get_name() 
        && count < max 
        && this.item.is_stackable(item)){
            add_item(item);
            ensure_item_in_world(item.get_icon());
            return true;
        }
        return false;
    }

    private void add_item(BLOCK item){
        ensure_digit_in_world();
        this.item = item;
        count++;
        digit.set_number(count); //is set visible by set_number automatically
    }

    public void init(MAINWORLD main_world){
        //digit
        int x_offset = (int)(((double) offset_ratio_percentage.get_x()/100) * width);
        int y_offset = (int)(((double) offset_ratio_percentage.get_y()/100) * height);
        System.out.println("offset x" + x_offset + "y" + y_offset);
        int x = getX() + width/2 + x_offset;
        int y = getY() + height/2 + y_offset;
        System.out.println("x" + x + "y" + y);
        digit_position = new POSITION(x,y);
        //main_world.addObject(digit, x,y);
        //digit.set_number(0);
        
        //icon
        x = getX();
        y = getY();
        item_icon_position = new POSITION(x,y); 
        item_icon = new IMAGESHOWER((int)(height * icon_size_ratio));
    }

    public BLOCK get_item(){
        return item;
    }

    public int get_count(){
        return count;
    }

    public BLOCK retrieve_item(){
        BLOCK return_item = null;
        if(count > 0){
            count--;
            return_item = get_item();
        }
        if(count != 0){
            ensure_digit_in_world();
            digit.set_number(count);
            ensure_item_in_world(get_item().get_icon());
        } else {
            getWorld().removeObject(digit);
            getWorld().removeObject(item_icon);
            item = null;
        }
        return return_item;
    }
    
    public void ensure_digit_in_world(){
        if(digit.getWorld() == null){
            getWorld().addObject(digit, digit_position.get_x(), digit_position.get_y());
        }
    }
    
    public void ensure_item_in_world(GreenfootImage icon){
        if(item_icon.getWorld() == null){
            item_icon.set_image(icon);
            getWorld().addObject(item_icon, item_icon_position.get_x(), item_icon_position.get_y());
        }
    }
}
