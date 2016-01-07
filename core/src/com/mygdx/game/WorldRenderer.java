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
import static com.mygdx.game.WorldRenderer.State.ATTACKING;
import static com.mygdx.game.WorldRenderer.State.MOVING;
import static com.mygdx.game.WorldRenderer.State.NOTHING;
import com.mygdx.models.Entity;
import java.util.Random;

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

    private Entity lastSelected;
    private Entity currentSelected;

    private Array<Entity> player1Units;
    private Array<Entity> player2Units;

    private State currentState;

    public enum State {

        MOVING, ATTACKING, NOTHING
    }

    public WorldRenderer(World w) {
        currentState = NOTHING;
        player1Units = new Array<Entity>();
        player2Units = new Array<Entity>();
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
        //put stuff here once the one guy who has the maps gets back from Cuba
        batch.end();

    }

    /**
     * Determines which side wins a battle.
     *
     * @param a Friendly doing battle.
     * @param b Enemy doing battle.
     */
    public void battle(Entity a, Entity b) {
        //if b has more troops
        if (a.unitCount() < b.unitCount()) {
            //if a has more troops then it has as many troops as the difference between a's and b's troops
            b.setUnits(b.unitCount() - a.unitCount());
            a.setUnits(0);
            //if a has more troops
        } else if (b.unitCount() < a.unitCount()) {
            a.setUnits(a.unitCount() - b.unitCount());
            b.setUnits(0);
        } else {
            int rand = randNum();
            if (rand == 3) {
                a.setUnits(a.unitCount() / 2);
                b.setUnits(b.unitCount() / 2);
            } else if (rand == 2) {
                a.setUnits(a.unitCount() / 2 - 2);
                b.setUnits(b.unitCount() / 2);
            } else {
                a.setUnits(a.unitCount() / 2);
                b.setUnits(b.unitCount() / 2 - 2);
            }

        }
    }

    /**
     * Finds which unit the player clicked.
     *
     * @param x the X coordinate of the click
     * @param y the Y coordinate of the click
     */
    public void click(float x, float y) {
        if (currentState == MOVING) {
            if (currentSelected == null) {
                //Checks if player clicked enemy unit
                for (Entity p1 : player1Units) {
                    if (p1.getX() == x && p1.getY() == y) {
                        currentSelected = p1;
                    }
                }
                //Checks if player clicked a friendly unit
                for (Entity p2 : player2Units) {
                    if (p2.getX() == x && p2.getY() == y) {
                        currentSelected = p2;
                    } else {
                        currentSelected = null;
                    }
                }
            } else {
                for(Entity p1: player1Units) {
                    if(p1.getX() == x && p1.getY() == y) {
                        currentSelected.Move((int)x, (int)y);
                    }
                }
                for(Entity p2: player2Units) {
                    if(p2.getX() == x && p2.getY() == y) {
                        currentSelected.Move((int)x, (int)y);
                    }
                }
            }
        } else if (currentState == ATTACKING) {
            if(currentSelected == null) {
                
            } else {
                
            }
        }
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void move(float currentX, float currentY) {

    }

    public Array<Entity> getPlayer1units() {
        return player1Units;
    }

    public Array<Entity> getPlayer2units() {
        return player2Units;
    }

    public Entity getCurrentSelected() {
        return currentSelected;
    }

    public void setState(State s) {
        s = currentState;
    }

    private int randNum() {
        Random rand = new Random();
        int n = rand.nextInt(3) + 1;
        return n;
    }

}
