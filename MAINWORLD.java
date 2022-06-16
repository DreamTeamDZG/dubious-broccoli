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
    /**
     * Constructor for objects of class MAINWORLD.
     * 
     */
    public MAINWORLD()
    {    
        super((1920 / 10) * 8, (1080 / 10) * 8, 1);
        top_left = new POSITION(0,0);
        addObject(new CHARACTER(new POSITION(20,20)), ((1920 / 10) * 8)/2, ((1080 / 10) * 8)/2);
        addObject(new BACKGROUND(), 300, 300);
        setPaintOrder(CHARACTER.class, BACKGROUND.class);
    }
    
    public void update_view(){
        System.out.println("updateView");
        List<ENTITY> entities = getObjects(ENTITY.class);
        for(ENTITY e : entities){
            e.setLocation((e.get_x() - top_left.get_x()),(e.get_y() - top_left.get_y()));
        }
    }
    
    public void move_world(DIRECTION direction){

        POSITION direction_pos = direction_to_position(direction);
        direction_pos.inverse_y();
        top_left.add(direction_pos);
        System.out.println("top left "+top_left.get_x() + "|" + top_left.get_y());

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
                return new POSITION (-1,1);
            case ZERO:
                return new POSITION (0,0);
        }
        //error with compiler
        return null;
    }
}
