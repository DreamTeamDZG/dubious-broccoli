import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class PLANT here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class PLANT extends BLOCK
{
    private int growth_stage = 0;
    protected GreenfootImage[] growth_stage_image;
    PLANT(String path, int growth_stage_count ){
        growth_stage_image = new GreenfootImage[growth_stage_count];
        for(int i = 0; i< growth_stage_count; i++){
            growth_stage_image[i] = new GreenfootImage(path + get_name() + "_plant_phase_" + i + ".png");
        }
        setImage(growth_stage_image[0]);
    }
    
    public boolean grow(){
        if(((MAINWORLD) getWorld()).get_weather() == WEATHER.RAINY){
            return false;
        }
        growth_stage++;
        if(growth_stage<growth_stage_image.length){
            setImage(growth_stage_image[growth_stage]);
            return true;
        }
        growth_stage--;
        return false;
    }
    
    public boolean is_fully_grown(){
        return growth_stage == growth_stage_image.length - 1;
    }
    /*
    public boolean place(POSITION position, MAINWORLD main_world){
        set_position(position);
        main_world.update_view();
        FIELD field = get_field_or_null();
        if(field != null){
            field.add_plant(this);
            main_world.add_entity(this);
            mode = BLOCKMODE.BLOCK;
            return true;
        } else {
            System.out.println("cant place here, no field");
            return false;
        }
    }
    
    public FIELD get_field_or_null(){
        List<FIELD> fields = getWorld().getObjectsAt(getX(), getY(), FIELD.class);
        if(fields.size() != 1){
            System.out.println("Error to many fields at "+ getX() + "|" + getY());
            return null;
        }
        if(fields.size() == 1){
            return fields.get(0);
        }
        return null;
    }*/
    
    
    public void set_location(POSITION position){
        setImage(get_image());
        set_position(position);
    }
    
    public GreenfootImage get_image(){
        return growth_stage_image[growth_stage];
    }
}
