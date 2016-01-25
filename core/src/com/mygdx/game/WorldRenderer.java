/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.BatchTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import static com.mygdx.game.WorldRenderer.State.ATTACKING;
import static com.mygdx.game.WorldRenderer.State.MOVING;
import static com.mygdx.game.WorldRenderer.State.NOTHING;
import static com.mygdx.game.WorldRenderer.State.PLACING;
import com.mygdx.models.Entity;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author johns6971
 */
public class WorldRenderer {

    private final int V_WIDTH = 1280;
    private final int V_HEIGHT = 1024;
    private SpriteBatch batch;
    private Entity currentSelected;
    private Array<Entity> player1Units;
    private Array<Entity> player2Units;
    private State currentState;
    private int turn;
    private boolean player1Turn, moved, alreadyPlaced;
    private int count;
    private int count2;
    //rendering in cam variables
    private OrthographicCamera cam;
    private Viewport port;
    private TmxMapLoader loader;
    private TiledMap map;
    private HexagonalTiledMapRenderer ronderer;
    private int width = V_WIDTH;
    private int height = V_HEIGHT;
    //variables for the units
    private Sprite figure;
    private Sprite figure2;
    private boolean plusX, plusY, sameX, sameY, player1Won, player2Won;
    private BitmapFont font;
    private BitmapFont font2;
   // TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(0);
    TiledMapTileLayer layer;
    int xGrid;
    int yGrid;

    public enum State {

        MOVING, ATTACKING, PLACING, NOTHING,
    }

    public WorldRenderer() {
        currentState = PLACING;
        moved = false;
        player1Units = new Array<Entity>();
        player2Units = new Array<Entity>();

        batch = new SpriteBatch();
        Texture figureTexture = new Texture("stick.png");
        Texture figureTexture2 = new Texture("stick2.png");
        figure = new Sprite(figureTexture);
        figure2 = new Sprite(figureTexture2);
        turn = 1;
        player1Turn = true;
        alreadyPlaced = false;
        font = new BitmapFont();
        font.setColor(Color.BLUE);
        font2 = new BitmapFont();
        font2.setColor(Color.RED);

        player1Won = false;
        player2Won = false;

        //sets the cam to an orthagraphic camera
        cam = new OrthographicCamera();
        //adjusts the viewport to be in the middle of the screen
        port = new FitViewport(V_WIDTH, V_HEIGHT, cam);
        //loads in the map and sets map to the loaded tiled map
        loader = new TmxMapLoader();
        map = loader.load("map.tmx");
        //makes the renderer a hexagonal renderer
        ronderer = new HexagonalTiledMapRenderer(map, batch);
        //sets the cam's view to the camera
        ronderer.setView(cam);

        //sets the cam's position to the middle of the screen
        cam.position.set(width / 2, height / 2, 0);
        
        //MapLayer layer = map.getLayers().get(0);
        
        layer = (TiledMapTileLayer)map.getLayers().get(0);
        
        
    }
    

    public void render(float deltaTime) {
        //sets background colour
        Gdx.gl20.glClearColor(0.5f, 0.5f, 0.5f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //sets the render's view to look through cam 
        ronderer.setView(cam);
        //key bindings to move the camera's position
        if (Gdx.input.isKeyPressed(Keys.T)) {
            height += 14;
        }
        if (Gdx.input.isKeyPressed(Keys.G)) {
            height -= 14;
        }
        if (Gdx.input.isKeyPressed(Keys.H)) {
            width += 14;
        }
        if (Gdx.input.isKeyPressed(Keys.F)) {
            width -= 14;
        }
        //sets the position at which the camera will see after any movement has happend
        cam.position.x = width / 2;
        cam.position.y = height / 2;
        //updates the camera
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        //renders the tiled map
        ronderer.render();

        batch.begin();
        if(player1Turn) {
            font.draw(batch, "It is player 1's turn", (Gdx.graphics.getHeight() / 2) - 100, 1000);
        } else {
            font.draw(batch, "It is player 2's turn", (Gdx.graphics.getHeight() / 2) - 100, 1000);
        }
        for (Entity e : player1Units) {
            //display entity's place
            batch.draw(figure, e.getX(), e.getY(), e.getWidth(), e.getHeight());
            //display entity's unit count
            font.draw(batch, e.unitCount() + "", e.getX() + (e.getWidth() / 2), e.getY() + (e.getHeight() / 2));
        }
        for (Entity e : player2Units) {
            //display entity's place
            batch.draw(figure2, e.getX(), e.getY(), e.getWidth(), e.getHeight());
            
            font.draw(batch, e.unitCount() + "", e.getX() + (e.getWidth() / 2), e.getY() + (e.getHeight() / 2));
        }
        if (player1Won) {
            font2.draw(batch, "Player 1 won the game", Gdx.graphics.getHeight() / 2, Gdx.graphics.getWidth() / 2);
        } else if (player2Won) {
            font2.draw(batch, "Player 2 won the game", Gdx.graphics.getHeight() / 2, Gdx.graphics.getWidth() / 2);
        }
        batch.end();

        
        if(Gdx.input.isTouched()){
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touch);
            
            System.out.println("Click (" + touch.x + ", " + touch.y + ")");
        }
        
    }

    /**
     * Determines which side wins a battle.
     *
     * @param a entity doing battle.
     * @param b entity doing battle.
     */
    public void battle(Entity a, Entity b) {
        //if b has more troops
        if (a.unitCount() < b.unitCount()) {
            //if a has more troops then it has as many troops as the difference between a's and b's troops
            b.setUnits(b.unitCount() - a.unitCount());
            Iterator<Entity> it = player1Units.iterator();
            while (it.hasNext()) {
                Entity e = it.next();
                if (e == a) {
                    it.remove();
                }
            }
            it = player2Units.iterator();
            while (it.hasNext()) {
                Entity e = it.next();
                if (e == a) {
                    it.remove();
                }
            }
            //if a has more troops
        } else if (b.unitCount() < a.unitCount()) {
            a.setUnits(a.unitCount() - b.unitCount());
            Iterator<Entity> it = player1Units.iterator();
            while (it.hasNext()) {
                Entity e = it.next();
                if (e == b) {
                    it.remove();
                }
            }
            it = player2Units.iterator();
            while (it.hasNext()) {
                Entity e = it.next();
                if (e == b) {
                    it.remove();
                }
            }
        } else {
            int rand = randNum(1, 2);
            //if A wins random battle
            if (rand == 1) {
                //removing the losing unit 
                //by searching both arrays
                Iterator<Entity> it = player1Units.iterator();
                while (it.hasNext()) {
                    Entity p1 = it.next();
                    if (p1 == b) {
                        it.remove();
                    }
                }
                it = player2Units.iterator();
                while (it.hasNext()) {
                    Entity p2 = it.next();
                    if (p2 == b) {
                        it.remove();
                    }
                }
                a.setUnits((int) a.unitCount() / 2);
                //if after the above division the unit count is rounded to zero
                if (a.unitCount() == 0) {
                    a.setUnits(1);
                }
                //if b wins random battle
            } else {
                //removing the losing unit 
                //by searching both arrays
                Iterator<Entity> it = player1Units.iterator();
                while (it.hasNext()) {
                    Entity p1 = it.next();
                    if (p1 == a) {
                        it.remove();
                    }
                }
                it = player1Units.iterator();
                while (it.hasNext()) {
                    Entity p2 = it.next();
                    if (p2 == a) {
                        it.remove();
                    }
                }
                b.setUnits((int) b.unitCount() / 2);
                //if after the above division the unit count is rounded to zero
                if (b.unitCount() == 0) {
                    b.setUnits(1);
                }
            }

        }
    }

    /**
     * Finds which unit the player clicked. Checks if the player is selecting
     * things for the first time or is doing an action.
     *
     * @param x the X coordinate of the click
     * @param y the Y coordinate of the click
     */
    public void click(int x, int y) {
        Rectangle rect = new Rectangle(x, y, 10, 10);
        if (currentState == MOVING) {
            if (currentSelected == null) {
                //Checks if player clicked enemy unit
                for (Entity p1 : player1Units) {
                    if (p1.clicked(rect)) {
                        currentSelected = p1;
                    }
                }
                //Checks if player clicked a friendly unit
                for (Entity p2 : player2Units) {
                    if (p2.clicked(rect)) {
                        currentSelected = p2;
                    }
                }
            } else {
                if (!moved) {
                    //will only move unit if the player is moving their own units
                    if (currentSelected.getPlayer().equals("player1") && player1Turn) {
                        currentSelected.Move(x, y);
                        moved = true;
                        //will only move unit if the player is moving their own units
                    } else if (currentSelected.getPlayer().equals("player2") && !player1Turn) {
                        currentSelected.Move(x, y);
                        moved = true;
                    }

                }
                currentSelected = null;
            }
        } else if (currentState == NOTHING) {
            //checks if player clicked on a unit
            for (Entity p1 : player1Units) {
                if (p1.clicked(rect)) {
                    currentSelected = p1;
                }
            }
            //Checks if player clicked on a unit
            for (Entity p2 : player2Units) {
                if (p2.clicked(rect)) {
                    currentSelected = p2;
                }
            }
        } else if (currentState == ATTACKING) {
            if (currentSelected == null) {
                for (Entity p1 : player1Units) {
                    if (p1.clicked(rect)) {
                        currentSelected = p1;
                    }
                }
                for (Entity p2 : player2Units) {
                    if (p2.clicked(rect)) {
                        currentSelected = p2;
                    }
                }
            } else {
                Iterator<Entity> it = player1Units.iterator();
                while (it.hasNext()) {
                    Entity p1 = it.next();
                    //checks if the player has clicked to attack and the entity they are attacking are not their own units
                    if (p1.clicked(rect) && !moved && !p1.getPlayer().equals(currentSelected.getPlayer())) {
                        battle(currentSelected, p1);
                        break;
                    }
                }

                it = player2Units.iterator();
                while (it.hasNext()) {
                    Entity p2 = it.next();
                    //checks if the player has clicked to attack and the entity they are attacking are not their own units
                    if (p2.clicked(rect) && !moved && !p2.getPlayer().equals(currentSelected.getPlayer())) {
                        battle(currentSelected, p2);
                        break;
                    }
                }
                moved = true;
            }
        } else if (currentState == PLACING) {
            for (Entity e : player1Units) {
                if (e.clicked(rect)) {
                    alreadyPlaced = true;
                }
            }
            for (Entity e : player2Units) {
                if (e.clicked(rect)) {
                    alreadyPlaced = true;
                }
            }
            if (count + count2 != 10 && !alreadyPlaced) {
                if (player1Turn) {
                    xGrid = (int)(x/40);
                    yGrid = (int)y/45;
                    TiledMapTileLayer.Cell box = layer.getCell(xGrid,yGrid);
                    System.out.println("cooordinate og the tile X" +xGrid);
                    System.out.println("cooordinate og the tile Y" +yGrid);
                    player1Units.add(new Entity((xGrid*40 + 10), (yGrid*45 + 20), 50, 50));
                    player1Units.get(count).setUnits(randNum(2, 5));
                    //player1Units.get(count).setUnits(5);
                    count++;
                } else {
                    xGrid = (int)(x/40);
                    yGrid = (int)y/45;
                    TiledMapTileLayer.Cell box = layer.getCell(xGrid,yGrid);
                    System.out.println("cooordinate og the tile X" +xGrid);
                    System.out.println("cooordinate og the tile Y" +yGrid);
                    player2Units.add(new Entity((xGrid*40 + 10), (yGrid*45 + 20), 50, 50));
                    player2Units.get(count2).setUnits(randNum(2, 5));
                    //player2Units.get(count2).setUnits(5);
                    count2++;
                }
                for (Entity e : player1Units) {
                    e.setPlayer("player1");
                }
                for (Entity e : player2Units) {
                    e.setPlayer("player2");
                }
                endTurn();
            } else if (count + count2 == 10) {
                currentState = NOTHING;
            }
            alreadyPlaced = false;
        }
    }

    public void endTurn() {
        turn++;
        if (turn % 2 != 0) {
            player1Turn = true;
        } else {
            player1Turn = false;
        }
        currentSelected = null;
        moved = false;
    }

    /**
     * Sets currentSelected to null so player does not have a unit selected.
     */
    public void clearSelected() {
        currentSelected = null;
    }

    public void checkIfWon() {
        if (player1Units.size == 0 && currentState != PLACING) {
            player2Won = true;
        } else if (player2Units.size == 0 && currentState != PLACING) {
            player1Won = true;
        }
    }

    public void resize(int width, int height) {
        port.update(width, height);
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
        currentState = s;
    }

    public State getState() {
        return currentState;
    }

    private int randNum(int min, int max) {
        Random rand = new Random();
        int n = rand.nextInt(max) + min;
        return n;
    }
//    public TiledMapTileLayer.Cell getCell(int x, int y){
//        return TiledMap.Cell(x,y);
//    }
    
}
