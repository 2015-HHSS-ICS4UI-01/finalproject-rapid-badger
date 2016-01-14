
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.screens.World;
import com.mygdx.models.Entity;

/**
 *
 * @author thomt9963
 */
public class MainGame implements Screen {
    
    private final int V_WIDTH = 800;
    private final int V_HEIGHT = 600;
    
    private World theWorld;
    private WorldRenderer renderer;
    
    
    private float clickX;
    private float clickY;
    
    public MainGame() {
        theWorld = new World();
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
            System.out.println(clickX + " " + clickY);
            renderer.click(clickX, clickY);
        } else if (Gdx.input.isKeyJustPressed(Keys.M)) {
            renderer.setState(WorldRenderer.State.MOVING);
        } else if (Gdx.input.isKeyJustPressed(Keys.A)) {
            renderer.setState(WorldRenderer.State.ATTACKING);
        }
        
        renderer.render(deltaTime);
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
