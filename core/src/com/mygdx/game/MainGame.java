/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import static com.mygdx.game.WorldRenderer.State.PLACEMENT;
import com.mygdx.models.Entity;

/**
 *
 * @author thomt9963
 */
public class MainGame implements Screen {
    
   

   
    private WorldRenderer renderer;
    private int clickX;
    private int clickY;
    private boolean buttonDown;
    private Sprite splash;
    private SpriteBatch batch;

    public MainGame() {
        renderer = new WorldRenderer();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        
        Texture splashTexture = new Texture("UI.png");
        splash = new Sprite(splashTexture);
        splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
    }

    @Override
    public void render(float deltaTime) {
        
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            buttonDown = true;
        } else if (!Gdx.input.isButtonPressed(Input.Buttons.LEFT) && buttonDown) {
            buttonDown = false;
            clickX = Gdx.input.getX();
            clickY = (Gdx.graphics.getHeight()-Gdx.input.getY());
            renderer.click(clickX, clickY);
        } else if (Gdx.input.isKeyJustPressed(Keys.M)) {
            renderer.setState(WorldRenderer.State.MOVING);
        } else if(Gdx.input.isKeyJustPressed(Keys.C)) {
            renderer.clearSelected();
        } else if (Gdx.input.isKeyJustPressed(Keys.A)) {
            renderer.setState(WorldRenderer.State.ATTACKING);
        } else if (Gdx.input.isKeyJustPressed(Keys.SPACE) && renderer.getState() != PLACEMENT) {
            renderer.endTurn();
        } 
        renderer.render(deltaTime);
        
        
        
        
        batch.begin();
        splash.draw(batch);
        batch.end();
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
