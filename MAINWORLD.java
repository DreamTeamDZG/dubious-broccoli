import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.core.WorldHandler;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;

/**
 * Write a description of class MAINWORLD here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MAINWORLD extends World
{
    private POSITION top_left;
    
    // public as it is used in CHARACTER
    public static final POSITION border_top_left = new POSITION(0,0);
    public static final POSITION border_bottom_right = new POSITION(999, 999);
    public static final int block_size = 16;
    
    private List<ENTITY> entities;
    private CHARACTER character;

    private CLOCK clock;
    private COORDINATES coordinates;
    private INVENTORY inventory;
    private WEATHERSHOWER weathershower;
    private static final int y_size = 30;

    private static final int screen_size_x = (1920 / 10) * 8;
    private static final int screen_size_y = (1080 / 10) * 8;
    //1
    //
    private boolean huds_added = false;
    /**
     * Constructor for objects of class MAINWORLD.
     * 
     */
    public MAINWORLD()
    {    
        super(screen_size_x, screen_size_y, 1);
        top_left = new POSITION(0,0);
        POSITION middle = new POSITION(screen_size_x/2, screen_size_y/2);
        entities = new ArrayList<ENTITY>();
        addObject(new CHARACTER(middle), middle.get_x(), middle.get_y());
        add_entity(new BACKGROUND());
        setPaintOrder(CHARACTER.class, ENTITY.class, BACKGROUND.class);
    }

    public void update_view(){
        System.out.println("updateView");
        if(entities == null){
            return;
        }
        for(ENTITY e : entities){
            try
            {
                POSITION pos_screen = (POSITION) e.get_position().clone();
                pos_screen.subtract(top_left);
                e.set_location(pos_screen);
            }
            catch (CloneNotSupportedException cnse)
            {
                System.out.println("CloneNotSupportedExcpetion"+ cnse);
            }
        }
    }
    
    public void add_entity(ENTITY entity){
        System.out.println("adding entity");
        if(!entities.contains(entity)){
            entities.add(entity);
        }
        addObject(entity, 0, 0);
        update_view();
    }

    public void move_world(DIRECTION direction){
        POSITION direction_pos = direction_to_position(direction);
        direction_pos = character.move_vec(direction_pos);
        direction_pos.inverse_y();
        top_left.add(direction_pos);
        //System.out.println("top left "+top_left.get_x() + "|" + top_left.get_y());
        
        update_view();
    }
    
    public void select_next_slot(){
        inventory.select_next_slot();
    }
    
    public void select_prev_slot(){
        inventory.select_prev_slot();
    }

    public BLOCK get_selected_item(){
        return inventory.get_selected_item();
    }
    
    public BLOCK retrieve_selected_item(){
        BLOCK item = inventory.retrieve_selected_item();
        if(!entities.contains(item)){
            entities.add(item);
        }
        return item;
    }
    
    public boolean add_item(BLOCK item){
        if(entities.contains(item)){
            entities.remove(item);
        }
        return inventory.add_item(item);
    }
    
    public void delete_block(BLOCK block){
        if(entities.contains(block)){
            entities.remove(block);
        }
        removeObject(block);
    }
    
    public BLOCK get_block_at(POSITION position){
        position.subtract(top_left);
        List<BLOCK> blocks = getObjectsAt(position.get_x(), position.get_y(), BLOCK.class);
        switch(blocks.size()){
            case 0:
                System.out.println("no block found at x:" + position.get_x()+ "y:" + position.get_y());
                return null;
            case 1:
                return blocks.get(0);
            default:
                System.out.println("to many(" + blocks.size() + " )blocks found at x:" + position.get_x()+ "y:" + position.get_y());
                return null;
        }
    }
    
    public static DIRECTION inverse_direction(DIRECTION direction)
    {
        switch(direction)
        {
            case NORTH:
                return DIRECTION.SOUTH;
            case NORTH_EAST:
                return DIRECTION.SOUTH_WEST;
            case EAST:
                return DIRECTION.WEST;
            case SOUTH_EAST:
                return DIRECTION.NORTH_WEST;
            case SOUTH:
                return DIRECTION.NORTH;
            case SOUTH_WEST:
                return DIRECTION.NORTH_EAST;
            case WEST:
                return DIRECTION.EAST;
            case NORTH_WEST:
                return DIRECTION.SOUTH_EAST;
            case ZERO:
                return DIRECTION.ZERO;
        }
        // error with compiler
        return null;
    }
    //direction converts to position of X and Y
    public static POSITION direction_to_position(DIRECTION direction)
    {
        switch(direction)
        {
            case NORTH:
                return new POSITION (0,1);
            case NORTH_EAST:
                return new POSITION (1,1);
            case EAST:
                return new POSITION (1,0);
            case SOUTH_EAST:
                return new POSITION (1,-1);
            case SOUTH:
                return new POSITION (0,-1);
            case SOUTH_WEST:
                return new POSITION (-1,-1);
            case WEST:
                return new POSITION (-1,0);
            case NORTH_WEST:
                return new POSITION (-1,1);
            case ZERO:
                return new POSITION (0,0);
        }
        //error with compiler
        return null;
    }
    
    public CHARACTER get_character(){
        return character;
    }

    public void act(){
        // cheats
        add_item_cheat();
        change_weather_cheat();
        //cheats
        clock.update_time();
        coordinates.update_position(this);
    }

    public void change_weather_cheat(){
        if(Greenfoot.isKeyDown("M")){
            System.out.println("M");
            switch(weathershower.get_weather()){
                case SUNNY:
                    System.out.println("sunny -> rainy");
                    weathershower.set_weather(WEATHER.RAINY);
                    break;
                case RAINY:
                    System.out.println("rainy -> sunny");
                    weathershower.set_weather(WEATHER.SUNNY);
                    break;
            }
            Greenfoot.delay(20);
        }
    }

    public void add_item_cheat(){
        int dubious_lvl = 1;
        if(Greenfoot.isKeyDown("O")){
            if (inventory.add_item(new STONE())){
                System.out.println("adding Stone worked");
            } else {
                System.out.println("adding Stone failed");
            }
        }
        if(Greenfoot.isKeyDown("L")){
            if (inventory.retrieve_item(new STONE()) != null){
                System.out.println("removing Stone worked");
            } else {
                System.out.println("removing Stone failed");
            }
        }

        if(Greenfoot.isKeyDown("i")){
            if (inventory.add_item(new BROCCOLI())){
                System.out.println("adding Broccoli worked");
                dubious_lvl++;
            } else {
                System.out.println("adding Broccoli failed");
            }
        }
        if(Greenfoot.isKeyDown("k")){
            if (inventory.retrieve_item(new BROCCOLI()) != null){
                System.out.println("removing Broccoli worked");
                dubious_lvl--;
            } else {
                System.out.println("removing Broccoli failed");
            }
        }
        
        if(Greenfoot.isKeyDown("ä")){
            if (inventory.add_item(new CARROT())){
                System.out.println("adding Carrot worked");
            } else {
                System.out.println("adding Carrot failed");
            }
        }
        if(Greenfoot.isKeyDown("ü")){
            if (inventory.retrieve_item(new CARROT()) != null){
                System.out.println("removing Carrot worked");
            } else {
                System.out.println("removing Carrot failed");
            }
        }
    }

    public void started(){
        character = getObjects(CHARACTER.class).get(0);
        add_huds();
    }
    
    public void add_huds(){
        if(huds_added){
            return;
        }
        huds_added = true;
        add_inventory();
        add_clock();
        add_coordinates();
        add_weathershower();
    }

    private void add_inventory(){
        inventory = new INVENTORY(new GreenfootImage("/inventory/inv_slot_big.png"), new GreenfootImage("/inventory/inv_slot_big.png"));
        inventory.init(getWidth(), getHeight(), this);
    }

    private void add_clock(){
        clock = new CLOCK(new POSITION(getWidth()-CLOCK.get_width_static()-10, 10), y_size,15, 15, 15);
        clock.init(this);
    }

    private void add_coordinates(){
        coordinates = new COORDINATES(new POSITION(0, 10));
        coordinates.init(this);
    }

    private void add_weathershower(){
        weathershower = new WEATHERSHOWER("hud/weather");
        int x = getWidth()-CLOCK.get_width_static() - weathershower.get_width() - 10;
        weathershower.init(this, new POSITION(x, 20));
    }
    
    /*
    public void mouseWheelMoved(MouseWheelEvent e){
        System.out.println("MouseWheel");
        int rot = e.getWheelRotation();
        if(rot > 0){
            for(int i = 0;i<rot; i++){
                select_next_slot();
            }
        } else if (rot < 0){
            for(int i = 0;i<(-rot); i++){
                select_next_slot();
            }
        }
    }*/
}
