package com.jakspinning.kingdomwar.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by emmanuel_payet on 27/04/15.
 */
public class HexTile {
    public int height;
    public TextureRegion texture;
    
    public HexTile(int height,TextureRegion texture){
        this.height = height;
        this.texture = texture;
    }

    public HexTile() {

    }
}
