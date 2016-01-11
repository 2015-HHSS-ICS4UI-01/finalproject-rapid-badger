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
    private String player;
    
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

    public void setUnits(int newunits) {
        units = newunits;
    }

    public void Move(int newX, int newY) {
        place.x = newX;
        place.y = newY;
    }
    
    public void setPlayer(String player) {
        this.player = player;
    }
    
    public String getPlayer() {
        return player;
    }
}
