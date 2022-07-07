import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IMAGESHOWER here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IMAGESHOWER extends Actor
{
    private GreenfootImage image;
    private POSITION scale;
    public IMAGESHOWER(String path, POSITION scale){
        image = new GreenfootImage(path);
        this.scale = scale;
        image.scale(scale.get_x(), scale.get_y());
        this.image = image;
    }
    
    public IMAGESHOWER(String path, int y_size){
        image = new GreenfootImage(path);
        double width_over_height = image.getWidth() / image.getHeight();
        scale = new POSITION((int) (((double)y_size) * width_over_height), y_size);
        image.scale((int) (((double)y_size) * width_over_height), y_size);
        this.image = image;
    }
    
    public IMAGESHOWER(String path){
        GreenfootImage image = new GreenfootImage(path);
        this.image = image;
    }
    
    public IMAGESHOWER(GreenfootImage image){
        this.image = image;
    }
    
    public IMAGESHOWER(GreenfootImage image, int y_size){
        this.image = image;
        double width_over_height = image.getWidth() / image.getHeight();
        scale = new POSITION((int) (((double)y_size) * width_over_height), y_size);
        image.scale((int) (((double)y_size) * width_over_height), y_size);
    }
    
    public IMAGESHOWER(int y_size){
        scale = new POSITION(y_size, -1);
    }
    
    public IMAGESHOWER(){
    }
    
    public void set_image(GreenfootImage image){
        if(scale.get_x() == -1){
            double width_over_height = image.getWidth() / image.getHeight();
            scale = new POSITION((int)((width_over_height * scale.get_y())), scale.get_y());
        }
        if(scale.get_y() == -1){
            double height_over_width = image.getHeight() / image.getWidth();
            scale = new POSITION(scale.get_x(), (int)(height_over_width * scale.get_x()));
        }
        image.scale(scale.get_x(), scale.get_y());
        this.image = image;
    }

    public void update_view(){
        setImage(image);
    }
    
    public void set_y_scale(int y_scale){
        scale = new POSITION(-1, y_scale);
    }
    
    public void set_x_scale(int x_scale){
        scale = new POSITION(x_scale, -1);
    }
    
    protected void addedToWorld(World world){
        setImage(image);
    }
    
    public void set_position(POSITION position){
        if(image==null){
            //System.out.println("no image to be found");
            return;
        }
        setLocation(position.get_x() + image.getHeight()/2, position.get_y() + image.getWidth()/2);
    }
    
    public int get_width(){
        return image.getWidth();
    }
    
    public int get_height(){
        return image.getHeight();
    }
    
    public POSITION get_size(){
        return new POSITION(get_width(), get_height());
    }
}
