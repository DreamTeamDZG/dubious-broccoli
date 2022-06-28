import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BLOCK here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class BLOCK extends ENTITY
{
    private GreenfootImage icon;
    protected
    BLOCKMODE mode;
    
    private int time_broken = 0; // if 1000 then break; on act -- -> 0

    public abstract String get_name();
    public abstract boolean is_stackable();
    public abstract GreenfootImage get_icon();
    public abstract GreenfootImage get_image();
    public abstract int will_drop_item(TOOL tool); // returns time in ticks it takes to destroy
    public abstract boolean is_placeable();
    
    public void use(TOOL tool){
        if(mode == BLOCKMODE.ITEM){
            return;
        }
        double time = ((double)will_drop_item(tool)) / tool.get_speed_multiplier();
        if(time == 0){
            System.out.println("instant break");
            drop_item();
        } else if (time > 0){
            System.out.println("breaking progress " + time_broken);
            time_broken++;
            if(time_broken >= time){
                drop_item();
                time_broken = 0;
            }
        } else {
            System.out.println("this block cant be broken");
        }
    }
    
    public void drop_item(){
        ((MAINWORLD) getWorld()).add_entity(this);
        mode = BLOCKMODE.ITEM;
        //setImage(get_icon());
    }
    
    public void set_mode(BLOCKMODE mode){
        this.mode = mode;
    }
    

    public BLOCKMODE get_mode(){
        return mode;
    }
    
    public void place(POSITION position){
        //setting the posiiton
        System.out.println("placing block at"+ position);
        set_position(position);
        if(is_placeable()){
            ((MAINWORLD) getWorld()).add_entity(this);
            mode = BLOCKMODE.ITEM;
            //setImage(get_image());
        } else {
            System.out.println("cannot place block " + get_name());
        }
    }
}
