import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class CRAFTINGMENU here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CRAFTINGMENU extends Actor
{
    enum TAB{
        WOOD,
        STONE
    }
    
    private GreenfootImage wood_tab;
    private GreenfootImage stone_tab;
    private TAB selected_tab;
    
    private MAINWORLD main_world;
    
    public CRAFTINGMENU(MAINWORLD main_world){
        this.main_world = main_world;
    }
    
    /**
     * Act - do whatever the CRAFTINGMENU wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
        if(Greenfoot.isKeyDown("T")){
            switch_tab();
        } else if(Greenfoot.isKeyDown("X")){
            
            //axe
        } else if(Greenfoot.isKeyDown("Y")){
            //hoe
        } else if(Greenfoot.isKeyDown("C")){
            //pickaxe
        }

    }

    
    public void switch_tab(){
        if(selected_tab == TAB.WOOD){
            selected_tab = TAB.STONE;
            setImage(stone_tab);
        } else {
            selected_tab = TAB.WOOD;
            setImage(wood_tab);
        }
    }
    
    private void craft_axe(){
        
        BLOCK[] ingredients;
        if(selected_tab == TAB.WOOD){
            ingredients = AXESTONE.get_ingredients();
            if(craft(ingredients)){
                main_world.add_item(new AXESTONE());
            }
        } else {
            ingredients = AXEWOOD.get_ingredients();
            if(craft(ingredients)){
                main_world.add_item(new AXEWOOD());
            }
        }
        
    }
    
    private void craft_pickaxe(){
        BLOCK[] ingredients;
        if(selected_tab == TAB.WOOD){
            ingredients = PICKAXESTONE.get_ingredients();
            if(craft(ingredients)){
                main_world.add_item(new PICKAXEWOOD());
            }
        } else {
            ingredients = PICKAXEWOOD.get_ingredients();
            if(craft(ingredients)){
                main_world.add_item(new PICKAXEWOOD());
            }
        }
    }
    
    private void craft_hoe(){
        BLOCK[] ingredients;
        if(selected_tab == TAB.WOOD){
            ingredients = HOESTONE.get_ingredients();
            if(craft(ingredients)){
                main_world.add_item(new HOEWOOD());
            }
        } else {
            ingredients = HOEWOOD.get_ingredients();
            if(craft(ingredients)){
                main_world.add_item(new HOEWOOD());
            }
        }
    }
    
    public boolean craft(BLOCK[] ingredients){
        List<BLOCK> taken_from_inv = new ArrayList();
        for(BLOCK b: ingredients){
                BLOCK item = main_world.retrieve_item(b);
                if(item == null ){
                    main_world.add_item(item);
                    return false;
                } else {
                    taken_from_inv.add(b);
                }
        }
        return true;
    }
}
