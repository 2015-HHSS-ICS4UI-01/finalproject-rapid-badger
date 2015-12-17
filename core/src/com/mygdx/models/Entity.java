/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.models;

import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author johns6971
 */
public class Entity {
    
    private Rectangle place;
    private int units;
    
    public Entity(float x, float y, float width, float height) {
        place = new Rectangle(x, y, width, height);
    }
    
    public float getX() {
        return place.x;
    }
    
    public float getY() {
        return place.y;
    }
    
    public float getWidth() {
        return place.width;
    }
    
    public float getHeight() {
        return place.height;
    }
    
    public int unitCount() {
        return units;
    }
    
    public void setUnits(int setUnits) {
        units = setUnits;
    }
    
    /**
     * Determanes which side wins a battle
     * @param a Doing battle
     * @param b Doing battle
     */
    public void battle(Entity a, Entity b) {
        //if a has more troops
        if(a.unitCount() < b.unitCount()) {
            //if a has more troops then it has as many troops as the difference between a's and b's troops
            b.setUnits(b.unitCount() - a.unitCount());
            a.setUnits(0);
            
            //if b has more troops
        } else if (b.unitCount() < a.unitCount()) {
            a.setUnits(a.unitCount() - b.unitCount());
            b.setUnits(0);
        }
    }
    
    
}
