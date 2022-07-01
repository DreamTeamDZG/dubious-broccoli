import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class CRAFTINGMENU here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CRAFTINGMENU extends IMAGESHOWER
{
    enum TAB{
        WOOD,
        STONE
    }
    
    private GreenfootImage wood_tab;
    private GreenfootImage stone_tab;
    private TAB selected_tab;
    
    private MAINWORLD main_world;
    
    public CRAFTINGMENU(MAINWORLD main_world, int y_size){
        super(y_size);
        this.main_world = main_world;
        wood_tab = new GreenfootImage("hud/crafting_wood.png");
        stone_tab = new GreenfootImage("hud/crafting_stone.png");
        set_image(wood_tab);
        
    }
    
    /**
     * Act - do whatever the CRAFTINGMENU wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
        if(Greenfoot.isKeyDown("T")){
            System.out.println("T");
            switch_tab();
            Greenfoot.delay(20);
        } else if(Greenfoot.isKeyDown("2")){
            System.out.println("2");
            craft_axe();
            Greenfoot.delay(20);
            //axe
        } else if(Greenfoot.isKeyDown("1")){
            System.out.println("1");
            craft_hoe();
            Greenfoot.delay(20);
            //hoe
        } else if(Greenfoot.isKeyDown("3")){
            System.out.println("3");
            craft_pickaxe();
            Greenfoot.delay(20);
            //pickaxe
        }

    }

    
    public void switch_tab(){
        if(selected_tab == TAB.WOOD){
            System.out.println("switching to wood");
            selected_tab = TAB.STONE;
            set_image(stone_tab);
        } else {
            System.out.println("switching to stone");
            selected_tab = TAB.WOOD;
            set_image(wood_tab);
        }
        update_view();
    }
    
    private void craft_axe(){
        
        BLOCK[] ingredients;
        if(selected_tab == TAB.WOOD){
            ingredients = AXESTONE.get_ingredients();
            if(craft(ingredients)){
                main_world.move_item_to_inv(new AXESTONE());
            }
        } else {
            ingredients = AXEWOOD.get_ingredients();
            if(craft(ingredients)){
                main_world.move_item_to_inv(new AXEWOOD());
            }
        }
        
    }
    
    private void craft_pickaxe(){
        BLOCK[] ingredients;
        if(selected_tab == TAB.WOOD){
            ingredients = PICKAXESTONE.get_ingredients();
            if(craft(ingredients)){
                main_world.move_item_to_inv(new PICKAXEWOOD());
            }
        } else {
            ingredients = PICKAXEWOOD.get_ingredients();
            if(craft(ingredients)){
                main_world.move_item_to_inv(new PICKAXEWOOD());
            }
        }
    }
    
    private void craft_hoe(){
        BLOCK[] ingredients;
        if(selected_tab == TAB.WOOD){
            ingredients = HOESTONE.get_ingredients();
            if(craft(ingredients)){
                main_world.move_item_to_inv(new HOEWOOD());
            }
        } else {
            ingredients = HOEWOOD.get_ingredients();
            if(craft(ingredients)){
                main_world.move_item_to_inv(new HOEWOOD());
            }
        }
    }
    
    public boolean craft(BLOCK[] ingredients){
        List<BLOCK> taken_from_inv = new ArrayList();
        for(BLOCK b: ingredients){
                BLOCK item = main_world.retrieve_item(b);
                if(item == null ){
                    for(BLOCK b_r: taken_from_inv){
                        main_world.move_item_to_inv(b_r);
                    }
                    return false;
                } else {
                    taken_from_inv.add(b);
                }
        }
        return true;
    }
}
