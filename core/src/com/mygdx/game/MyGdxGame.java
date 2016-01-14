package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.screens.SplashArt;
import com.mygdx.screens.SplashArt1;
import java.awt.event.MouseEvent;


public class MyGdxGame extends Game {
    

    @Override
    public void create() {
         setScreen(new SplashArt());

         setScreen (new SplashArt1());
        }
       
        
    }

