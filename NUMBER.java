import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * uses its position as the top left reference for the digits
 * assumes all digits have the same size
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NUMBER
{
    private POSITION position;
    private final int digits_min;
    private final int digits_max;
    private double y_size;
    private DIGIT[] digits;
    private int number;
    private boolean initialized = false;
    private static final int spacing = 10;
    
    public NUMBER(POSITION position, double y_size, int digits_min, int digits_max, int number){
        this.position = position;
        this.y_size = y_size;
        this.digits_min = digits_min;
        this.digits_max = digits_max;
        this.number = number;
        digits = new DIGIT[digits_max];
    }
    
    public NUMBER(POSITION position, double y_size, int digits, int number){
        this.position = position;
        this.y_size = y_size;
        digits_min = digits;
        digits_max = digits;
        this.number = number;
        this.digits = new DIGIT[digits_max];
    }
    
    public void init(MAINWORLD world){
        if(initialized){
            return;
        }
        for(int i = 0; i < digits_max; i++){
            DIGIT d = new DIGIT(y_size);
            world.addObject(d, 0, 0);
            int y = (int) (d.get_height() / 2) + position.get_y();
            int x = (int) (d.get_width() * (i + 0.5)+ i * spacing) + position.get_x();
            d.setLocation(x, y);
            digits[i] = d;
        }
    }
    
    public void set_number(int number){
        this.number = number;
        update_view();
    }
    
    public void set_position(POSITION position){
        this.position = position;
        for(int i = 0; i < digits_max; i++){
            DIGIT d = digits[i];
            int y = (int) (d.get_height() / 2) + position.get_y();
            int x = (int) (d.get_width() * (i + 0.5)+ i * spacing) + position.get_x();
            d.setLocation(x, y);
        }
    }
    
    public void update_view(){
        int[] digits_numbers = get_digits();
        for(int i = 0; i < digits.length; i++){
            digits[i].set_number(digits_numbers[i]);
        }
    }
    
    public int get_width(){
        int width = 0;
        for(DIGIT digit: digits){
            width = width + digit.get_width() + spacing;
        }
        // decrement by spacing
        // for one i stays 0 and spacing stays 0
        return width - spacing;
    }
    public int get_height(){
        int height = 0;
        for(DIGIT digit: digits){
            height = height + digit.get_height();
        }
        return height;
    }
    
    public int[] get_digits(){
        int number = this.number;
        int[] arr = new int[digits_max];
        for(int i = 0; i < digits_max; i++){
            int last_digit = number % 10;
            number = (int) (number - last_digit)/10;
            arr[(digits_max-1) - i] = last_digit; //reverse the array on the fly
        }
        return remove_leading_zeros_with_min(arr);
    }
    
    //removes leading zeros while letting the array be at least digits_min long
    private int[] remove_leading_zeros_with_min(int[] arr){
        int length = count_needed_digits(arr);
        int first_idx = arr.length - length;
        int[] arr_done = new int[length];
        int done_idx = 0;
        for(int i = first_idx; i < arr.length; i++){
            arr_done[done_idx] = arr[i];
            done_idx++;
        }
        return arr_done;
    }
    
    private int count_needed_digits(int[] arr){
        int count_needed_digits = 0;
        boolean first_non_zero_found = false;
        for(int i: arr){
            if(first_non_zero_found || i != 0){
                count_needed_digits++;
                first_non_zero_found = true;
            }
        }
        count_needed_digits = Math.max(count_needed_digits, digits_min);
        // cuts off the ending digits if the number gets bigger than digits_max
        // could be rewritten to throw an exception
        return Math.min(count_needed_digits, digits_max);
    }
    
}
