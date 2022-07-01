import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.time.Instant;
import java.time.Duration;
/**
 * uses its position as the top left reference
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CLOCK
{
    private POSITION position;
    private MAINWORLD main_world;
    
    private int day;
    private int hour;
    private int minute;
    private int millis;
    
    private NUMBER display_hour;
    private NUMBER display_minute;
    private NUMBER display_day;
    
    private int y_size;
    
    private boolean paused;
    
    
    private IMAGESHOWER day_colon;
    // replace this with the new texture
    private static final String day_colon_path = "hud/numbers_and_symbols/tag_symbol.png";
    
    private IMAGESHOWER colon;
    private static final String colon_path = "hud/numbers_and_symbols/symbol_colon.png";
    // 1000 millis = 1 minute in this case to make time move faster

    private Instant last;

    public CLOCK(int y_size){
        position = new POSITION(0,0);
        day = 0;
        hour = 0;
        minute = 0;
        last = Instant.now();
    }
    
    public CLOCK(POSITION position, int y_size){
        this.position = position;
        day = 0;
        hour = 0;
        minute = 0;
        last = Instant.now();
    }

    public CLOCK(POSITION position,int y_size, int day, int  hour, int minute){
        this.position = position;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.y_size = y_size;
        last = Instant.now();
    }
    
    public void init(MAINWORLD main_world){
        // 12:15
        // Tag: 2
        
        this.main_world = main_world;
        
        display_hour = new NUMBER(position, y_size, 2, 0);
        display_hour.init(main_world);
        System.out.println("created display hour");
        
        POSITION colon_pos = POSITION.add(position, new POSITION(display_hour.get_width(), 0));
        colon = new IMAGESHOWER(colon_path, y_size);
        main_world.addObject(colon, 0,0);
        colon.set_position(POSITION.add(colon_pos, new POSITION(0, -5)));
        System.out.println("created display colon");
        
        POSITION minute_pos = POSITION.add(colon_pos, new POSITION(colon.get_width(), 0));
        display_minute = new NUMBER(minute_pos, y_size, 2, 0);
        display_minute.init(main_world);
        System.out.println("created display minute");
        
        int max_height = Math.max(display_hour.get_height(), Math.max(display_minute.get_height(), colon.get_height()));
        POSITION day_colon_pos = POSITION.add(position, new POSITION(0, max_height));
        day_colon = new IMAGESHOWER(day_colon_path,y_size);
        //main_world.addObject(day_colon, 0, 0);
        day_colon.set_position(POSITION.add(day_colon_pos, new POSITION(5, -22)));
        System.out.println("created display day colon");
        
        
        POSITION day_pos = POSITION.add(day_colon_pos, new POSITION(day_colon.get_width(),0));
        display_day = new NUMBER(day_pos, y_size, 2,0);
        //display_day.init(main_world);
        System.out.println("created display day");
        
    }
    
    protected void addedToWorld(World world){
        MAINWORLD main_world = (MAINWORLD) world;
        display_hour.init(main_world);
        display_minute.init(main_world);
        world.addObject(day_colon, 0, 0);
        world.addObject(colon, 0, 0);
    }

    public void update_time(){
        if(paused){
            return;
        }
        Instant now = Instant.now();
        millis = millis + (int) Duration.between(last, now).toMillis();
        while(millis >= 1000){
            millis = millis - 1000;
            minute++;
            // debug
            //minute += 10;
            // debug
        }
        while(minute >= 60){
            minute = minute - 60;
            hour++;
        }
        while(hour >= 24){
            hour = hour - 24;
            day++;
            set_random_weather();
        }
        last = now;
        
        display_minute.set_number(minute);
        display_hour.set_number(hour);
        //display_day.set_number(day);
    }
    
    public void pause(){
        paused = true;
    }
    
    public void set_random_weather(){
        int number = Greenfoot.getRandomNumber(2);
        if(number == 0){
            main_world.set_weather(WEATHER.SUNNY);
        } else {
            main_world.set_weather(WEATHER.RAINY);
        }
    }
    
    public int get_width(){
        int width_clock = display_hour.get_width() + display_minute.get_width() + colon.get_width();
        int width_day = display_day.get_width() + day_colon.get_width();
        return Math.max(width_clock, width_day);
    }
    
    public static int get_width_static(){
        return 114;
        // obtained by running get_width after start
    }
}
