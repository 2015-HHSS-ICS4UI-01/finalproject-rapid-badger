/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.screens.World;
import com.mygdx.models.Entity;
import com.mygdx.models.Player;

/**
 *
 * @author thomt9963
 */
public class MainGame implements Screen {
    
    private final int V_WIDTH = 800;
    private final int V_HEIGHT = 600;
    
    private World theWorld;
    private Player player;
    private WorldRenderer renderer;
    
    
    private float clickX;
    private float clickY;
    
    public MainGame() {
        theWorld = new World();
        player = theWorld.getPlayer();
        renderer = new WorldRenderer(theWorld);
    }

    @Override
    public void show() {
        
    }

    @Override
    public void render(float deltaTime) {
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            clickX = Gdx.input.getX();
            clickY = Gdx.input.getY();
            player.Click(clickX, clickY, renderer.getEnemies());
        }
        
        
        
        
        renderer.render(deltaTime);
    }

    /**
     * Determines which side wins a battle
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
        } else {
            a.setUnits(a.unitCount()/2);
            b.setUnits(b.unitCount()/2);
        }
    }
    
    
    @Override
    public void resize(int width, int height) {
        
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void dispose() {
        
    }
    
}
