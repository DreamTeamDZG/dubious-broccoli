import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Provides the positions of a Entity existing in the game
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class ENTITY extends Actor
{
    private POSITION position;
    private boolean visible;
    GreenfootImage icon;
    
    public ENTITY(){
        this.position = new POSITION(0,0);
        this.visible = true;
    }
    
    public ENTITY(int x, int y, boolean visible){
        this.position = new POSITION(x, y);
        this.visible = visible;
    }
    
    public ENTITY(POSITION position, boolean visible){
        this.position = position;
        this.visible = visible;
    }
    
    public ENTITY(POSITION position){
        this.position = position;
    }
    
    public ENTITY(int x, int y){
        this.position = new POSITION(x, y);
        this.visible = true;
    }
    
    public void move(int x, int y){
        System.out.println("Old Pos" + get_x() +"|"+ get_y() + "    " + getX() + "|" + getY());
        position.add(new POSITION(x, y));
        // this is very unsafe but will get the job done
        // consider adding an if block to this.
        ((MAINWORLD) getWorld()).update_view();
        System.out.println("New Pos" + get_x() +"|"+ get_y() + "    " + getX() + "|" + getY());
    }
    
    public POSITION get_position(){
        return position;
    }
    
    // set position in world
    public void set_position(POSITION position){
        this.position = position;
    }
    
    
    //set location on screen
    public void set_location(POSITION position){
        setLocation(position.get_x(), position.get_y());
    }
    
    public void set_visible(boolean visible){
        this.visible = visible;
    }
    
    public boolean get_visible(){
        return visible;

    }
    
    public int get_x(){
        return position.get_x();
    }
    
    public void set_x(int x){
        position.set_x(x);
    }
    
    public int get_y(){
        return position.get_y();
    }
    
    public void set_y(int y){
        position.set_y(y);
    }
}
