/**
 * Write a description of class POSITION here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class POSITION  
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;

    /**
     * Constructor for objects of class POSITION
     */
    public POSITION(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int get_x(){
        return x;
    }
    
    public void set_x(int x){
        this.x = x;
    }
    
    public int get_y(){
        return y;
    }
    
    public void set_y(int y){
        this.y = y;
    }
    
    public void add(POSITION p){
        x = x + p.get_x();
        y = y + p.get_y();
    }
    
    public static POSITION add(POSITION p_1, POSITION p_2){
        return new POSITION(p_1.get_x() + p_2.get_x(), p_1.get_y() + p_2.get_y());
    }
}
