/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


/**
 *
 * @author thomt9963
 */
public class MainMenu implements Screen{
    
     private static final String TITLE = "RISK-EH 2";
     private Sprite splash;
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton buttonStart;
    private BitmapFont white;
    private SpriteBatch batch;
    private Label heading;

    @Override
    public void show() {
        Texture splashTexture = new Texture("Splash.png");
        splash = new Sprite(splashTexture);
        splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        stage = new Stage();
        
        Gdx.input.setInputProcessor(stage);
        
        atlas = new TextureAtlas("button.pack");
        skin = new Skin(atlas);
        
        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
       // table.background(new TextureRegionDrawable(new TextureRegion("Splash.png")));
        
        // creating font 
        white = new BitmapFont(Gdx.files.internal("font.fnt"),false);
        
        // creating buttons
        TextButtonStyle buttonStyle = new TextButtonStyle();
        buttonStyle.up = skin.getDrawable("button.up");
        buttonStyle.down = skin.getDrawable("button.down");
        buttonStyle.pressedOffsetX = 1;
        buttonStyle.pressedOffsetY = -1;
        buttonStyle.font = white;
        
        
        buttonStart = new TextButton("START", buttonStyle);
        buttonStart.pad(20);
        
        // creating heading
        LabelStyle headingStyle = new LabelStyle(white, Color.WHITE);
        
        heading = new Label(TITLE, headingStyle);
        
        table.add(buttonStart);
        table.debug();
        stage.addActor(table);
    }
    

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
        
        stage.act(delta);
        
        stage.draw();
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
