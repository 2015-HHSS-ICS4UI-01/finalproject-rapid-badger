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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;


/**
 *
 * @author thomt9963
 */
public class MainMenu implements Screen{
    
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton buttonStart;
    private BitmapFont white;
    private SpriteBatch batch;

    @Override
    public void show() {  
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
        buttonStart.pad(10);
        
        
        table.add(buttonStart);
        table.debug();
        stage.addActor(table);
    }
    

    @Override
    public void render(float delta) {     
        
        stage.act(delta);
        
        stage.draw();
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
