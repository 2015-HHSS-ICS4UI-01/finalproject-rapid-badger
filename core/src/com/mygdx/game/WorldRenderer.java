/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import static com.mygdx.game.WorldRenderer.State.ATTACKING;
import static com.mygdx.game.WorldRenderer.State.MOVING;
import static com.mygdx.game.WorldRenderer.State.NOTHING;
import static com.mygdx.game.WorldRenderer.State.PLACEMENT;
import com.mygdx.models.Entity;
import java.util.Random;

/**
 *
 * @author kampn2687
 */
public class WorldRenderer {

    private final int V_WIDTH = 800;
    private final int V_HEIGHT = 600;
    private SpriteBatch batch;
    private Entity lastSelected;
    private Entity currentSelected;
    private Array<Entity> player1Units;
    private Array<Entity> player2Units;
    private State currentState;
    private int turn;
    private boolean player1Turn, moved, alreadyPlaced;
    private int count;
    private int count2;
    private Sprite figure;
    private Sprite figure2;

    public enum State {

        MOVING, ATTACKING, PLACEMENT, NOTHING,
    }

    public WorldRenderer() {
        currentState = PLACEMENT;
        moved = false;
        player1Units = new Array<Entity>();
        player2Units = new Array<Entity>();
       
        System.out.println("It is player 1's turn");
        batch = new SpriteBatch();
        Texture figureTexture = new Texture("stick.png");
        Texture figureTexture2 = new Texture("stick2.png");
        figure = new Sprite(figureTexture);
        figure2 = new Sprite(figureTexture2);
        turn = 1;
        player1Turn = true;
        alreadyPlaced = false;
    }

    public void render(float deltaTime) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        for (Entity e : player1Units) {
            batch.draw(figure, e.getX(), e.getY(), 50, 50);
        }
        for(Entity e: player2Units) {
            batch.draw(figure2, e.getX(), e.getY(), 50, 50);
        }
        //someone for the love of god put something in here so we know that the entire game can actually work
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
            for(Entity e: player1Units) {
                int loop = 0;
                if(e == a) {
                    player1Units.removeIndex(loop);
                }
                loop++;
            }
            for(Entity e: player2Units) {
                int loop = 0;
                if(e == a) {
                    player1Units.removeIndex(loop);
                }
                loop++;
            }
            //if a has more troops
        } else if (b.unitCount() < a.unitCount()) {
            a.setUnits(a.unitCount() - b.unitCount());
            b.setUnits(0);
        } else {
            int rand = randNum(1, 2);
            //if a wins random battle
            if (rand == 1) {

                b.setUnits(0);
                //if b wins random battle
            } else {

                a.setUnits(0);
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
        if (currentState == MOVING) {
            if (currentSelected == null) {
                //Checks if player clicked enemy unit
                for (Entity p1 : player1Units) {
                    if (p1.getX() <= x && p1.getWidth() >= x && p1.getY() <= y && y <= p1.getHeight()) {
                        currentSelected = p1;
                        System.out.println(currentSelected.getX() + " " + currentSelected.getY());
                    }
                }
                //Checks if player clicked a friendly unit
                for (Entity p2 : player2Units) {
                    if (p2.getX() == x && p2.getY() == y ) {
                        currentSelected = p2;
                        System.out.println(currentSelected.getX() + " " + currentSelected.getY());
                    } else {
                        currentSelected = null;
                    }
                }
            } else if (currentSelected != null){
                //Checking which way to move
                if (x < currentSelected.getX() && y < currentSelected.getY() && !moved) {
                    currentSelected.Move(x + 1, y + 1);
                } else if (x > currentSelected.getX() && y > currentSelected.getY() && !moved) {
                    currentSelected.Move(x - 1, y - 1);
                } else if (x < currentSelected.getX() && y > currentSelected.getY() && !moved) {
                    currentSelected.Move(x + 1, y - 1);
                } else if (!moved) {
                    currentSelected.Move(x - 1, y + 1);
                }
                moved = true;
            }
        } else if (currentState == ATTACKING) {
            if (currentSelected == null) {
                for (Entity p1 : player1Units) {
                    if (p1.getX() == 2 && p1.getY() == y && player1Turn) {
                        currentSelected = p1;
                    }
                }
                for (Entity p2 : player2Units) {
                    if (p2.getX() == x && p2.getY() == y && player1Turn == false) {
                        currentSelected = p2;
                    } else {
                        currentSelected = null;
                    }
                }
            } else {
                for (Entity p1 : player1Units) {
                    if (p1.getX() == x && p1.getY() == y && !currentSelected.getPlayer().equals(p1.getPlayer())
                            && !moved) {
                        battle(currentSelected, p1);
                    }
                }
                for (Entity p2 : player2Units) {
                    if (p2.getX() == x && p2.getY() == y && !currentSelected.getPlayer().equals(p2.getPlayer())
                            && !moved) {
                        battle(currentSelected, p2);
                    } else {
                        currentSelected = null;
                    }
                }
                moved = true;
            }
        } else if (currentState == PLACEMENT) {
            for (Entity e : player1Units) {
                if (e.getX() == x && e.getY() == y) {
                    System.out.println("There is already a unit there");
                    alreadyPlaced = true;
                }
            }
            for (Entity e : player2Units) {
                if (e.getX() == x && e.getY() == y) {
                    System.out.println("There is already a unit there");
                    alreadyPlaced = true;
                }
            }
            if (count + count2 != 10 && !alreadyPlaced) {
                if (player1Turn) {
                    player1Units.add(new Entity(x, y, 50, 50));
                    player1Units.get(count).setUnits(randNum(1, 5));
                    System.out.println("Player 1 placed an entity at " + x + " " + y);
                    count++;
                } else {
                    player2Units.add(new Entity(x, y, 50, 50));
                    player2Units.get(count2).setUnits(randNum(1, 5));
                    System.out.println("Player 2 placed an entity at " + x + " " + y);
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
                System.out.println("All units have been placed");
                currentState = NOTHING;
            }
            alreadyPlaced = false;
        }

        if (currentState != PLACEMENT) {
            if (player1Turn) {
                System.out.println("player 1 clicked " + x + " " + y);
            } else {
                System.out.println("player 2 clicked " + x + " " + y);
            }
        }

    }

    public void endTurn() {
        if (player1Turn) {
            System.out.println("Player 1's turn has ended");
            System.out.println();
        } else {
            System.out.println("Player 2's turn has ended");
            System.out.println();
        }
        turn++;
        if (turn % 2 != 0) {
            player1Turn = true;
            System.out.println("It is player 1's turn.");
        } else {
            player1Turn = false;
            System.out.println("It is player 2's turn.");
        }
    }

    public void resize(int width, int height) {
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
        System.out.println(currentState);
    }

    public State getState() {
        return currentState;
    }

    private int randNum(int min, int max) {
        Random rand = new Random();
        int n = rand.nextInt(max) + min;
        return n;
    }
}
