import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WEATHERSHOWER here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WEATHERSHOWER extends Actor
{   
    private WEATHER weather;
    
    private GreenfootImage sunny;
    private GreenfootImage rainy;
    // same ratio of width and height

    private POSITION position;
    private static final int y_size = 30;
    
    public WEATHERSHOWER(String path){
        sunny = new GreenfootImage(path + "/sunny.jpeg");
        double width_over_height = sunny.getWidth() / sunny.getHeight();
        POSITION scale = new POSITION((int)(((double)y_size)* width_over_height), y_size);
        sunny.scale(scale.get_x(), scale.get_y());
        rainy = new GreenfootImage(path + "/rainy.jpeg");
        rainy.scale(scale.get_x(), scale.get_y());
    }
    
    public void set_weather(WEATHER weather){
        this.weather = weather;
        switch(weather){
            case SUNNY:
                System.out.println("sunny");
                setImage(sunny);
                break;
            case RAINY:
                System.out.println("rainy");
                setImage(rainy);
                break;
        }
    }
    
    public WEATHER get_weather(){
        return weather;
    }
    
    public void init(MAINWORLD main_world, POSITION position){
        this.position = position;
        main_world.addObject(this, position.get_x(), position.get_y());
        set_weather(WEATHER.SUNNY);
    }
    
    public int get_width(){
        return Math.max(sunny.getWidth(), rainy.getWidth());
    }
}
