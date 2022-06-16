import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NUMBER here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DIGIT extends Actor
{
    private static GreenfootImage[] images;
    private static final String[] paths = {"zero.png" , "one.png", "two.png", "three.png", "four.png", "five.png", "six.png", "seven.png", "eight.png", "nine.png"};
    private int number;
    private boolean visible;
    
    public DIGIT(double y_size){
        visible = true;
        images = new GreenfootImage[10];
        for(int i = 0; i <= 9; i++){
            GreenfootImage image = new GreenfootImage("inventory/numbers_and_symbols/number_"+paths[i]);
            double width_over_height = image.getWidth() / image.getHeight();
            image.scale((int) (y_size * width_over_height),(int) y_size);   
            images[i]= image;
        }
    }
    
    public void set_number(int number){
        if(number >= 0 && number <= 9){
            this.number = number;
        } else {
            throw new ArithmeticException("number out of range"+ number);
        }
        update_number();
    }
    
    public void update_number(){
        setImage(images[number]);
    }
    
    public int get_number(){
        return number;
    }
    
    public int get_width(){
        return getImage().getWidth();
    }
    
    public int get_height(){
        return getImage().getHeight();
    }
}
