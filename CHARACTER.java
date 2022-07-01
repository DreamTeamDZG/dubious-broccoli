import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
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


    private boolean paused = false;
    public static final double pickup_range = 8;
    private boolean wheel_pressed; // two events on press
    DIRECTION current_direction;

    DIRECTION last_direction_NS = DIRECTION.NORTH; // last walked to north or south
    DIRECTION last_direction_EW = DIRECTION.EAST; // last walked to east or west

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
    
    public void pause(){
        paused = true;
    }

    public void loadImage()
    {
        switch(current_direction)
        {
            case NORTH:
                setImage(g_i[0]);
                //System.out.println("NORTH");
                break;
            case NORTH_EAST:
                setImage(g_i[0]);
                //System.out.println("NORTH_EAST");
                break;
            case EAST:
                setImage(g_i[1]);
                //System.out.println("EAST");
                break;
            case SOUTH_EAST:
                setImage(g_i[2]);
                //System.out.println("SOUTH_EAST");
                break;
            case SOUTH:
                setImage(g_i[2]);
                //System.out.println("SOUTH");
                break;
            case SOUTH_WEST:
                setImage(g_i[2]);
                //System.out.println("SOUTH_WEST");
                break;
            case WEST:
                setImage(g_i[3]);
                //System.out.println("WEST");
                break;
            case NORTH_WEST:
                setImage(g_i[0]);
                //System.out.println("NORTH_WEST");
                break;
            case ZERO:
                setImage(g_i[0]);
                //System.out.println("ZERO");
                break;
        }
    }

    private void control()
    {
        if(paused){
            return;
        }
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

        if(direction_x != DIRECTION.ZERO){
            last_direction_EW = direction_x;
        }
        if(direction_y != DIRECTION.ZERO){
            last_direction_NS = direction_y;
        }

        current_direction = add_directions(direction_x,direction_y);
        if(current_direction != DIRECTION.ZERO){
            ((MAINWORLD) getWorld()).move_world(current_direction);
        }

    }

    private void mouse_interactions(){
        MouseInfo mouse_info = Greenfoot.getMouseInfo();
        if(mouse_info != null){
            switch (mouse_info.getButton()){
                case 1:
                    System.out.println("block is being destroyed");
                    break;

                case 2:
                    System.out.println("changing to next inv slot");
                    select_next_slot();
                    break;
                case 3:
                    System.out.println("block is being placed");
                    //place_block();
                    System.out.println("block is placed");
                    break;

            }
        }
    }
    
    public void crafting_control(){
        if(Greenfoot.isKeyDown("C")){
            System.out.println("C");
            ((MAINWORLD) getWorld()).add_crafting_menu();
        }
    }


    public void pick_up_items(){
        ((MAINWORLD) getWorld()).pick_up_items();
    }


    public void select_next_slot(){
        if(wheel_pressed){
            wheel_pressed = false;
            return;
        }
        Greenfoot.delay(10);
        MAINWORLD world = ((MAINWORLD) getWorld());
        world.select_next_slot();
        wheel_pressed = true;
    }

    public void select_prev_slot(){
        MAINWORLD world = ((MAINWORLD) getWorld());
        world.select_prev_slot();
    }

    //returns the vector it CAN move
    public POSITION move_vec(POSITION vec){
        POSITION new_position = POSITION.add(position, vec);
        /*
        if(new_position.get_x() < MAINWORLD.border_top_left.get_x() && vec.get_x() < 0){
            System.out.println("cant move further in this direction");
            vec.set_x(0);
        } else if(new_position.get_x() > MAINWORLD.border_bottom_right.get_x() && vec.get_x() > 0){
            System.out.println("cant move further in this direction");
            vec.set_x(0);
        }*/
        List<WATER> waters_one = getObjectsAtOffset(vec.get_x()*5,0, WATER.class);
        if(waters_one.size() != 0){
            System.out.println("cant move further in this direction, WATER");
            MAINWORLD world = ((MAINWORLD) getWorld());
            for(WATER water: waters_one){
                
                POSITION dir = world.get_block_middle().direction_to(water.get_position());
                System.out.println(" in direction x:" + dir.get_x() +"" + vec.get_x());
                if(dir.get_x() == vec.get_x()){
                    vec.set_x(0);
                    break;
                }
                // wtf
                if(dir.get_x() == 1 && vec.get_x() == -1){
                    vec.set_x(0);
                    break;
                }
            }
            //vec.set_x(0);
        }
        
        List<WATER> waters_two = getObjectsAtOffset(0,vec.get_y(), WATER.class);
        if(waters_two.size() != 0){
            System.out.print("cant move further in this direction, WATER");
            MAINWORLD world = ((MAINWORLD) getWorld());
            for(WATER water: waters_two){
                POSITION dir = world.get_block_middle().direction_to(water.get_position());
                System.out.println(" in direction y:" + dir.get_y() +"" + vec.get_y());
                dir.inverse_y();
                if(dir.get_y() == vec.get_y()){
                    vec.set_y(0);
                    break;
                }
            }
            //vec.set_y(0);
        }
        
        /*
        if(new_position.get_y() < MAINWORLD.border_top_left.get_y() && vec.get_y() < 0){
            System.out.println("cant move further in this direction");
            vec.set_y(0);
        } else if(new_position.get_y() > MAINWORLD.border_bottom_right.get_y() && vec.get_y() > 0){
            System.out.println("cant move further in this direction");
            vec.set_y(0);
        }*/
        position.add(vec);
        return vec;
        //vec.inverse();
        //position.add(vec);
    }
    /*
    public void place_block(){
        MAINWORLD world = ((MAINWORLD) getWorld());
        BLOCK current_block = world.get_selected_item();
        System.out.println("character block placing");
        if(current_block == null){
            System.out.println("no block selected");
            return;
        }
        POSITION pos = get_next_block_position(world.get_position_middle(), last_direction_EW, last_direction_NS);
        if(current_block.place(pos, world)){
            System.out.println("character block placing");
            world.retrieve_selected_item();
            
        }
        if(current_block instanceof TOOL){
            TOOL current_tool = (TOOL) current_block;
            POSITION block_position = get_next_block_position(world.get_position_middle(), last_direction_EW, last_direction_NS);
            BLOCK block = world.get_block_at(block_position);
            block.use(current_tool);
        }
    }*/

    public static POSITION get_next_block_position(POSITION position, DIRECTION ew, DIRECTION ns){
        if(ew == DIRECTION.ZERO && ns == DIRECTION.ZERO){
            System.out.println("get_next_block directions must NOT BOTH be ZERO");
            return null;
        }
        try
        {
            POSITION place_position = (POSITION) position.clone();

            while((place_position.get_x() % MAINWORLD.block_size) != 0 && ew != DIRECTION.ZERO){
                place_position.add(MAINWORLD.direction_to_position(ew));
            }
            while((place_position.get_y() % MAINWORLD.block_size) != 0 && ns != DIRECTION.ZERO){
                place_position.add(MAINWORLD.direction_to_position(ns));
            }
            if((place_position.get_x() % MAINWORLD.block_size) == 0 && (place_position.get_y() % MAINWORLD.block_size) == 0){
                return place_position;
            }
            //System.out.println("cant find correct block " + place_position.get_x() +"  "+ place_position.get_y());
            return null;
        }
        catch (CloneNotSupportedException cnse)
        {
            System.out.println("CloneNotSupportedExcpetion"+ cnse);
        }
        return null;
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
        //mouse_interactions();
        crafting_control();
        loadImage();
        //pick_up_items();
    }
}
