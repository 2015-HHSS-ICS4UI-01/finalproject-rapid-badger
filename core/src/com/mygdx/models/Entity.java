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
    
    
}
