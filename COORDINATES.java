import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Displays x and y coordinate of the player
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class COORDINATES
{
    //private final POSITION pos = new POSITION( 50, 15);
    
    private POSITION position;
    
    private NUMBER x;
    private NUMBER y;
    private IMAGESHOWER x_equals;
    private IMAGESHOWER y_equals;
    private static final int y_size = 30;
    private static final int seperator_x = 10;
    
    private static final String x_equals_path = "inventory/hud/x_equals_symbol.png";
    private static final String y_equals_path = "inventory/hud/y_equals_symbol.png";
    private static final int y_offset_equals = 9;
    private static final int x_offset_equals = 25;
    
    public COORDINATES(POSITION position){
        this.position = position;
    }
    
    public void init(MAINWORLD main_world){
        POSITION x_equals_position = position;
        x_equals = new IMAGESHOWER(x_equals_path, y_size);
        x_equals.set_position(POSITION.add(x_equals_position, new POSITION(x_offset_equals, y_offset_equals)));
        main_world.addObject(x_equals, x_equals_position.get_x() + x_offset_equals, x_equals_position.get_y() + y_offset_equals);
        
        POSITION x_position = POSITION.add(position, new POSITION(x_equals.get_width(), 0));
        x = new NUMBER(x_position, y_size, 2, 0);
        x.init(main_world);
        
        
        POSITION y_equals_position = POSITION.add(x_position, new POSITION(x.get_width() + seperator_x, 0));
        y_equals = new IMAGESHOWER(y_equals_path, y_size);
        y_equals.set_position(POSITION.add(y_equals_position, new POSITION(x_offset_equals, y_offset_equals)));
        main_world.addObject(y_equals, y_equals_position.get_x() + x_offset_equals, y_equals_position.get_y() + y_offset_equals);
        
        POSITION y_position = POSITION.add(y_equals_position, new POSITION(y_equals.get_width(),0));
        y = new NUMBER(y_position, y_size, 2, 0);
        y.init(main_world);
    }
    public void update_position(MAINWORLD main_world){
        //PLAYER player = main_world.getObjects(PLAYER.class).get(0); // should only be one
        int x_coordinate = 25;//player.get_x();
        int y_coordinate = 12;//player.get_y();
        x.set_number(x_coordinate);
        y.set_number(y_coordinate);
        //main_world.showText("h", position.get_x(), position.get_y());
    }
    
    public static void get_width(){
    
    }
}
