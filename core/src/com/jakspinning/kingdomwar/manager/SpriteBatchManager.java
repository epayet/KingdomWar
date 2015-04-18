package com.jakspinning.kingdomwar.manager;

import com.artemis.Manager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by emmanuel_payet on 14/04/15.
 */
public class SpriteBatchManager extends Manager{
    public SpriteBatch spriteBatch;

    public SpriteBatchManager() {
        spriteBatch = new SpriteBatch();        
    }
}
