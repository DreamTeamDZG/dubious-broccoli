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
    }
    
    public void update_view(){
        List<ENTITY> entities = getObjects(ENTITY.class);
        for(ENTITY e : entities){
            e.setLocation(e.get_x() - top_left.get_x(), e.get_y() - top_left.get_y());
        }
    }
    
    public void move_world(int x, int y){
        top_left.add(new POSITION(x, y));
        update_view();
    }
}
