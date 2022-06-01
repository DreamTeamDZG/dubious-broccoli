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
    DIRECTION current_direction;
    GreenfootImage [] g_i;
    String [] paths_to_image;
    CHARACTER(POSITION position)
    {
        this.position = position;
        this.name = "blub";
        current_direction = DIRECTION.ZERO;
        g_i = new GreenfootImage[4];
        paths_to_image = new String[4];
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
    
    public void loadImage()
    {
        switch(current_direction)
        {
            case NORTH:
                setImage(g_i[0]);
            case NORTH_EAST:
                setImage(g_i[0]);
            case EAST:
                setImage(g_i[1]);
            case SOUTH_EAST:
                setImage(g_i[2]);
            case SOUTH:
                setImage(g_i[2]);
            case SOUTH_WEST:
                setImage(g_i[2]);
            case WEST:
                setImage(g_i[3]);
            case NORTH_WEST:
                setImage(g_i[0]);   
            case ZERO:
                setImage(g_i[0]);
        }
    }
    private void control()
    {
        DIRECTION direction_x= DIRECTION.ZERO;
        DIRECTION direction_y= DIRECTION.ZERO;
        if(Greenfoot.isKeyDown("a"))
        {
            direction_x = add_directions(direction_x, DIRECTION.WEST);
        }
        if(Greenfoot.isKeyDown("d"))
        {
            direction_x = add_directions(direction_x, DIRECTION.EAST);    
        }
        if(Greenfoot.isKeyDown("w"))
        {
            direction_y = add_directions(direction_y, DIRECTION.SOUTH);
        }
        if(Greenfoot.isKeyDown("s"))
        {
            direction_y = add_directions(direction_y, DIRECTION.NORTH);   
        }
        ((MAINWORLD) getWorld()).move_world(add_directions(direction_x,direction_y));
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
