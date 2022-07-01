import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.time.Instant;
import java.time.Duration;

/**
 * Write a description of class FIELD here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FIELD extends BLOCK
{
    private boolean hoed;
    private GreenfootImage img;
    private PLANT plant;
    private int watered_amount;
    private static final int watered_full = 1000000;
    private static final int grow_clocking_ms = 10000;
    
    
    private Instant last;
    
    
    public FIELD(){
        img = new GreenfootImage("blocks/field/field_watered.png");
        setImage(img);
        last = Instant.now();
    }
    
    public void water(){
        watered_amount = watered_full;
    }
    
    public void add_plant(PLANT plant){
        this.plant = plant;
    }
    
    public String get_name(){
        return "FIELD";
    }
    
    public boolean is_stackable(BLOCK block){
        return false;
    }
    
    public boolean is_placeable(){
        return true;
    }
    
    public int will_drop_item(TOOL tool){
        return -1;
    }
    
    public GreenfootImage get_image(){
        return img;
    }
    
    public GreenfootImage get_icon(){
        return null;
    }
    
    public void grow(){
        if(watered_amount > 0 && plant != null){
            if (plant.grow()){
                last = Instant.now();
                watered_amount--;
                if(watered_amount<900000){
                  img = new GreenfootImage("blocks/field/field_not_watered.png");
                  setImage(img);
                }
                System.out.println("growing plant; watered_amount " + watered_amount);
            }
        }
    }
    
    public void act(){
        Instant now = Instant.now();
        if((int) Duration.between(last, now).toMillis() > grow_clocking_ms){
            grow();
        }
    }
}
