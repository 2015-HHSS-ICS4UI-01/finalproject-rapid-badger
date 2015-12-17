/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.levels;

import com.mygdx.models.Player;

/**
 *
 * @author johns6971
 */
public class World {
    
    private Player player;
    
    public World() {
        player = new Player(0, 0, 10, 10);
    }
    
    
    public Player getPlayer() {
        return player;
    }
    
    
}
