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
    //public static final POSITION border_top_left = new POSITION(000,000);
    //public static final POSITION border_bottom_right = new POSITION(1666,999);
    public static final int block_size = 32;

    private List<ENTITY> entities;
    private List<BLOCK> items; // dropped items;
    private CHARACTER character;

    private CRAFTINGMENU crafting_menu;
    private CLOCK clock;
    private COORDINATES coordinates;
    private INVENTORY inventory;
    private WEATHERSHOWER weathershower;
    private static final int y_size = 18;

    private static final POSITION character_offset_position = new POSITION(-70, 100);

    private POSITION most_dubious_broccoli_position;
    private static final int broccoli_range = block_size / 2;

    private static final int screen_size_x = (1920 / 10) * 8;
    private static final int screen_size_y = (1080 / 10) * 8;
    private static final POSITION middle =  new POSITION(screen_size_x/2, screen_size_y/2);
    //1
    //
    private boolean huds_added = false;
    private int drop_broccoli = 3;
    /**
     * Constructor for objects of class MAINWORLD.
     * 
     */
    public MAINWORLD()
    {    
        super(screen_size_x, screen_size_y, 1);
        top_left = new POSITION(0,0);
        entities = new ArrayList<ENTITY>();
        items = new ArrayList<BLOCK>();
        addObject(new CHARACTER(POSITION.add(middle, character_offset_position)), middle.get_x(), middle.get_y());
        setPaintOrder(DIGIT.class, IMAGESHOWER.class, CRAFTINGMENU.class, CHARACTER.class,INVENTORYSLOT.class, NEEDSBACKGROUND.class, ENTITY.class, BACKGROUND.class);
        crafting_menu = null;
    }

    public void update_view(){
        //System.out.println("updateView");
        if(entities == null){
            return;
        }
        for(ENTITY e : entities){
            try
            {
                if(e.get_position() == null){
                    continue;
                }
                POSITION pos_screen = (POSITION) e.get_position().clone();
                pos_screen.subtract(top_left);
                if(is_position_on_screen(pos_screen)){
                    if(!e.get_visible()){
                        addObject(e, pos_screen.get_x(), pos_screen.get_y());
                    }
                    e.set_location(pos_screen);
                } else {
                    //System.out.println("Entity is not on the screen, removing it from World" + pos_screen.get_x() + " " + pos_screen.get_y());
                    e.set_visible(false);
                    removeObject(e);
                }
            }
            catch (CloneNotSupportedException cnse)
            {
                System.out.println("CloneNotSupportedExcpetion"+ cnse);
            }
        }
    }

    public List<BROCCOLI> get_all_broccolies(){
        List<BROCCOLI> broccolies = new ArrayList();
        for(ENTITY e: entities){
            if(e instanceof BROCCOLI){
                broccolies.add((BROCCOLI)e);
            }
        }
        return broccolies;
    }

    public void add_entity(ENTITY entity){
        if(entity instanceof BROCCOLI){
            System.out.println("adding entity");
        }
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
        //check_for_broccoli();
        pick_up_items();
    }

    public void set_most_dubious_broccoli_position(POSITION position){
        most_dubious_broccoli_position = position;
    }

    /*public void check_for_broccoli(){
        List<BROCCOLI> broccolies = get_all_broccolies();
        POSITION middle_pos = POSITION.add(top_left, middle);
        for(BROCCOLI broccoli : broccolies){
            if(POSITION.get_distance(middle_pos, broccoli.get_position()) <= broccoli_range){
                move_item_to_inv(broccoli);
            }
        }
        if(POSITION.get_distance(middle_pos, most_dubious_broccoli_position) <= broccoli_range){
            //System.out.println("you won");
            end_game(true);
        }
    }*/

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

    public BLOCK retrieve_item(BLOCK item){
        return inventory.retrieve_item(item);
    }

    public boolean move_item_to_inv(BLOCK item){
        if(entities.contains(item)){
            entities.remove(item);
        }
        if(items.contains(item)){
            items.remove(item);
        }
        removeObject(item);
        return inventory.add_item(item);
    }

    public void delete_block(BLOCK block){
        if(entities.contains(block)){
            entities.remove(block);
        }
        removeObject(block);
    }

    public POSITION get_block_middle(){
        POSITION pos = POSITION.add(top_left, middle);
        return pos;
    }

    public void drop_item(BLOCK item){
        if(!items.contains(item)){
            items.add(item);
        }
        item.set_mode(BLOCKMODE.ITEM);
    }

    public void pick_up_items(){
        List<BLOCK> to_add = new ArrayList<BLOCK>();
        for(BLOCK item: items){
            if(POSITION.get_distance(item.get_position(), get_block_middle()) < broccoli_range){
                to_add.add(item);

            }
        }
        for(BLOCK item: to_add){
            move_item_to_inv(item);
            if(item instanceof BROCCOLI){
                if(((BROCCOLI) item).get_dubious_lvl() == 5){
                    List<BROCCOLI> broccolies = get_all_broccolies();
                    boolean one_most_dubious_left = false;
                    for(BROCCOLI broccoli: broccolies){
                        if(broccoli.get_dubious_lvl() == 5){
                            one_most_dubious_left = true;
                        }
                    }
                    if(!one_most_dubious_left){
                        end_game(true);
                    }
                }
            }
        }
    }

    
    public boolean is_position_on_screen(POSITION position){
        if(position.get_x() > screen_size_x || position.get_x() < 0 ){
            return false;
        }
        if(position.get_y() > screen_size_y || position.get_y() < 0){
            return false;
        }
        return true;
    }

    public POSITION get_position_middle(){
        POSITION middle = new POSITION(screen_size_x/2, screen_size_y/2);
        return POSITION.add(middle, top_left);
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

    public void end_game(boolean won){
        if(won){
            IMAGESHOWER win_screen = new IMAGESHOWER("hud/concrats_end_screen.png",(int) ((double)(screen_size_y)*0.60));
            POSITION middle = new POSITION(screen_size_x/2, screen_size_y/2);
            addObject(win_screen, middle.get_x(), middle.get_y());
            //win_screen.set_position(POSITION.subtract(middle, win_screen.get_size()));
            pause();
            Greenfoot.delay(50);
        }
        Greenfoot.stop();
    }

    public void pause(){
        character.pause();
        clock.pause();
    }

    public CHARACTER get_character(){
        return character;
    }

    public void act(){
        // cheats
        //add_item_cheat();
        //change_weather_cheat();
        //cheats
        if(drop_broccoli > 0){
            drop_broccoli--;
        }
        if(drop_broccoli <= 0){
            drop_all_broccolies();
        }
        clock.update_time();
        coordinates.update_position(this);
    }

    public void drop_all_broccolies(){
        List<BROCCOLI> broccolies = get_all_broccolies();
        for(BROCCOLI broccoli: broccolies ){
            drop_item(broccoli);
        }
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

        if(Greenfoot.isKeyDown("U")){
            if (inventory.add_item(new WOOD())){
                System.out.println("adding WOOD worked");
            } else {
                System.out.println("adding WOOD failed");
            }
        }
        if(Greenfoot.isKeyDown("J")){
            if (inventory.retrieve_item(new WOOD()) != null){
                System.out.println("removing WOOD worked");
            } else {
                System.out.println("removing WOOD failed");
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

    public void set_weather(WEATHER weather){
        weathershower.set_weather(weather);
    }

    public WEATHER get_weather(){
        return weathershower.get_weather();
    }

    public void started(){
        character = getObjects(CHARACTER.class).get(0);
        add_huds();
        BROCCOLI.set_dubious_offset(Greenfoot.getRandomNumber(4));
        world_build();
        drop_all_broccolies();
    }

    public void world_build(){
        WORLDBUILDER builder = new WORLDBUILDER(this);
        builder.init();

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
        clock = new CLOCK(new POSITION(getWidth()-CLOCK.get_width_static()-10, 10), y_size, 0, 0, 0);
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

    public void add_crafting_menu(){
        if(crafting_menu == null){
            crafting_menu = new CRAFTINGMENU(this, (int)(((double) screen_size_y) * 0.5 ));
            addObject(crafting_menu, screen_size_x/2, screen_size_y/2);
            //POSITION middle = new POSITION(screen_size_x/2, screen_size_y/2);
            //crafting_menu.set_position(middle);
            Greenfoot.delay(20);
        } else {
            removeObject(crafting_menu);
            crafting_menu = null;
            Greenfoot.delay(20);
        }
    }
    
    public void prime_property(){
        IMAGESHOWER img = new IMAGESHOWER(get_all_broccolies().get(0).get_most_dubious_icon(),(int)(((double) screen_size_y) * 0.5 ));
        addObject(img, screen_size_x/2, screen_size_y/2);
        Greenfoot.delay(30);
        removeObject(img);
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
