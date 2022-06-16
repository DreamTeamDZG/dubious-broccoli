import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class MAINWORLD here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MAINWORLD extends World
{
    private POSITION top_left;

    private CLOCK clock;
    private COORDINATES coordinates;
    private INVENTORY inventory;
    private WEATHERSHOWER weathershower;
    private static final int y_size = 30;

    private static final int screen_size_x = (1920 / 10) * 8;
    private static final int screen_size_y = (1080 / 10) * 8;
    //1
    private GreenfootImage img = new GreenfootImage("sandstone.jpg");
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
    }

    public void update_view(){
        List<ENTITY> entities = getObjects(ENTITY.class);
        for(ENTITY e : entities){
            e.setLocation(e.get_x() - top_left.get_x(), e.get_y() - top_left.get_y());
        }
    }

    public void move_world(DIRECTION direction){
        top_left.add(direction_to_position(inverse_direction(direction)));
        update_view();
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
                return new POSITION (1,-1);
            case ZERO:
                return new POSITION (0,0);
        }
        //error with compiler
        return null;
    }

    public void act(){
        // cheats
        add_item();
        change_weather();
        //cheats
        clock.update_time();
        coordinates.update_position(this);
    }

    public void change_weather(){
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

    public void add_item(){
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
            } else {
                System.out.println("adding Broccoli failed");
            }
        }
        if(Greenfoot.isKeyDown("k")){
            if (inventory.retrieve_item(new BROCCOLI()) != null){
                System.out.println("removing Broccoli worked");
            } else {
                System.out.println("removing Broccoli failed");
            }
        }
    }

    public void started(){
        add_huds();
    }

    public void add_stone(){
        inventory.add_item(new STONE());
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
        inventory = new INVENTORY(new GreenfootImage("/inventory/inv_slot_big.png"));
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
        weathershower = new WEATHERSHOWER("/huds/weathershower");
        int x = getWidth()-CLOCK.get_width_static() - weathershower.get_width() - 10;
        weathershower.init(this, new POSITION(x, 20));
    }
}
