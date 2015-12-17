/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.levels.World;
import com.mygdx.models.Player;

/**
 *
 * @author thomt9963
 */
public class MainGame implements Screen {
    
    public final int V_WIDTH = 800;
    public final int V_HEIGHT = 600;
    
    private World theWorld;
    private Player player;
    public WorldRenderer renderer;
    
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
