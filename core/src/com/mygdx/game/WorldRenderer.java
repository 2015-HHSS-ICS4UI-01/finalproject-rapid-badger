/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.screens.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.models.Enemy;
import com.mygdx.models.Entity;
import com.mygdx.models.Friendly;

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
    
    private Entity currentSelected;
    
    private Array<Enemy> enemies;
    private Array<Friendly> friendlies;
    
    public WorldRenderer(World w){
        enemies = new Array<Enemy>();
        friendlies = new Array<Friendly>();
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
    
        /**
     * Determines which side wins a battle.
     * @param a Friendly doing battle.
     * @param b Enemy doing battle.
     */
    public void battle(Friendly a, Enemy b) {
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
    
    public void resize (int width, int height) {
        viewport.update(width, height);
    }
    
    
    
    public void move(float currentX, float currentY) {
        
    }
    
    public Array<Enemy> getEnemies() {
        return enemies;
    }
    
}
