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
    private IMAGESHOWER image;
    
    private static int y_offset = 8;
    //move plants 8 px up -> starts growing in the middle of a field
    PLANT(String path, int growth_stage_count ){
        image = new IMAGESHOWER();
        for(int i = 0; i< growth_stage_count; i++){
            growth_stage_image[i] = new GreenfootImage(path + get_name() + "_plant_phase_" + i);
        }
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
    
    public void place(POSITION position){
        set_position(position);
        ((MAINWORLD) getWorld()).update_view();
        FIELD field = get_field_or_null();
        if(field != null){
            field.add_plant(this);
            ((MAINWORLD) getWorld()).add_entity(this);
            mode = BLOCKMODE.ITEM;
        } else {
            System.out.println("cant place here, no field");
        }
    }
    
    public FIELD get_field_or_null(){
        List<FIELD> fields = getWorld().getObjectsAt(getX(), getY(), FIELD.class);
        if(fields.size() != 1){
            System.out.println("Error to many fields at"+ getX() + "|" + getY());
            return null;
        }
        if(fields.size() == 1){
            return fields.get(0);
        }
        return null;
    }
    
    public void set_location(POSITION position){
        position.add(new POSITION(0, y_offset));
        image.set_position(position);
    }
}
