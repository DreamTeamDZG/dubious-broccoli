import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CHARACTER here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CHARACTER extends Actor
{
    POSITION position;
    private String name;
    
    CHARACTER(POSITION position, String name)
    {
        this.position = position;
        this.name = name;
    }
    
    public POSITION get_position()
    {
        return position;
    }
    
    public void set_position(POSITION position)
    {
        this.position = position;
    }
    
    public String get_name()
    {
        return name;
    }
    
    public void set_name(String name)
    {
        this.name = name;
    }
    
    private void control()
    {
        DIRECTION direction= DIRECTION.ZERO;
        if(Greenfoot.isKeyDown("a"))
        {
            direction = add_directions(direction, DIRECTION.WEST);
        }
        if(Greenfoot.isKeyDown("d"))
        {
            direction = add_directions(direction, DIRECTION.EAST);    
        }
        if(Greenfoot.isKeyDown("w"))
        {
            direction = add_directions(direction, DIRECTION.NORTH);
        }
        if(Greenfoot.isKeyDown("s"))
        {
            direction = add_directions(direction, DIRECTION.SOUTH);   
        }
        ((MAINWORLD) getWorld()).move_world(direction);
    }
    
    public static DIRECTION add_directions(DIRECTION d_1, DIRECTION d_2)
    {
        if(d_1 != d_2)
        {
            if(d_1 == DIRECTION.ZERO || d_2 == DIRECTION.ZERO)
            {
                if(d_1 == DIRECTION.ZERO)
                {
                    return d_2;
                }
                else
                {
                    return d_1;
                }
            }
            switch(d_1)
            {
                case NORTH:
                    switch(d_2)
                    {
                        case EAST:
                            return DIRECTION.NORTH_EAST;
                            
                        case SOUTH:
                            return DIRECTION.ZERO;
                            
                        case WEST:
                            return DIRECTION.NORTH_WEST;
                            
                    }
                    
                case EAST:
                    switch(d_2)
                    {
                        case NORTH:
                            return DIRECTION.NORTH_EAST;
                            
                        case SOUTH:
                            return DIRECTION.SOUTH_EAST;
                            
                        case WEST:
                            return DIRECTION.ZERO;
                            
                    }
                    
                case SOUTH:
                    switch(d_2)
                    {
                        case NORTH:
                            return DIRECTION.ZERO;
                            
                        case EAST:
                            return DIRECTION.SOUTH_EAST;
                            
                        case WEST:
                            return DIRECTION.SOUTH_WEST;
                            
                    }
                    
                case WEST:
                    switch(d_2)
                    {
                        case NORTH:
                            return DIRECTION.NORTH_WEST;
                            
                        case EAST:
                            return DIRECTION.ZERO;
                            
                        case SOUTH:
                            return DIRECTION.SOUTH_WEST;
                        
                    }
                    
            }
            throw new ArithmeticException("fault, not compatible");
        }
        return d_1;
    }
    
    /**
     * Act - do whatever the CHARACTER wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        control();
        
    }
}
