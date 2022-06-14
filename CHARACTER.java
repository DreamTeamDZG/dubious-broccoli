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
    GreenfootImage[] g_i;
    String[] paths_to_image = {"char/back/cs_back.png","char/right/cs_right.png","char/front/cs_front.png","char/left/cs_left.png"};
    CHARACTER(POSITION position)
    {
        this.position = position;
        this.name = "blub";
        current_direction = DIRECTION.ZERO;
        g_i = new GreenfootImage[4];
        for(int i=0;i<4;i++)
        {
            g_i[i]= new GreenfootImage(paths_to_image[i]);
        }
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
                System.out.println("NORTH");
                break;
            case NORTH_EAST:
                setImage(g_i[0]);
                System.out.println("NORTH_EAST");
                break;
            case EAST:
                setImage(g_i[1]);
                System.out.println("EAST");
                break;
            case SOUTH_EAST:
                setImage(g_i[2]);
                System.out.println("SOUTH_EAST");
                break;
            case SOUTH:
                setImage(g_i[2]);
                System.out.println("SOUTH");
                break;
            case SOUTH_WEST:
                setImage(g_i[2]);
                System.out.println("SOUTH_WEST");
                break;
            case WEST:
                setImage(g_i[3]);
                System.out.println("WEST");
                break;
            case NORTH_WEST:
                setImage(g_i[0]);
                System.out.println("NORTH_WEST");
                break;
            case ZERO:
                setImage(g_i[0]);
                System.out.println("ZERO");
                break;
        }
    }
    
    private void control()
    {
        DIRECTION direction_x = DIRECTION.ZERO;
        DIRECTION direction_y = DIRECTION.ZERO;
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
            direction_y = add_directions(direction_y, DIRECTION.NORTH);
        }
        if(Greenfoot.isKeyDown("s"))
        {
            direction_y = add_directions(direction_y, DIRECTION.SOUTH);   
        }
        current_direction = add_directions(direction_x,direction_y);
        ((MAINWORLD) getWorld()).move_world(current_direction);
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
        loadImage();
    }
}
