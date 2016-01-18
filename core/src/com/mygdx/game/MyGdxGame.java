package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.screens.MainMenu;
import com.mygdx.screens.SplashArt;
import com.mygdx.screens.SplashArt1;

public class MyGdxGame extends Game {

    private SplashArt1 screen1 = new SplashArt1();
    private SplashArt screen2 = new SplashArt();
    private float delay = 2;
    private float delay1 = 4;
    private float delayAgain = 2;

    @Override
    public void create() {
        setScreen(screen1);

        Timer.schedule(new Task() {
            @Override
            public void run() {
                setScreen(screen2);
//                Timer.schedule(new Task() {
//                            @Override
//                            public void run() {
//                                setScreen(new MainMenu());
//                            }
//                        }, delayAgain);
                Timer.schedule(new Task() {
                    @Override
                    public void run() {
                        setScreen(new MainGame());
                        
                    }
                }, delay1);
            }
        }, delay);
        

    }
}
