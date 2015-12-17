/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.levels.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author kampn2687
 */
public class WorldRenderer {
    World world;
    
    private Viewport viewport;
    private OrthographicCamera camera;
    private final int V_WIDTH = 800;
    private final int V_HEIGHT = 600;
    
    private SpriteBatch batch;
    
    private float clickX;
    private float clickY;
    
    
    public WorldRenderer(World w){
        world = w;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera); 
        
    }

    
    public void render(float deltaTime) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        
        batch.begin();
        //put stuff here
        batch.end();
        
    }
    
    public void resize (int width, int height) {
        //viewport.update(width, height);
    }
    
    public void Click (float x, float y) {
        
    }
    
    public void move(float currentX, float currentY) {
        
    }
    
}
