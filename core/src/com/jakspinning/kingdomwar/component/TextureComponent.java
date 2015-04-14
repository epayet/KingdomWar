package com.jakspinning.kingdomwar.component;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by emmanuel_payet on 14/04/15.
 */
public class TextureComponent extends Component{
    public Texture texture;

    public TextureComponent(Texture texture) {
        this.texture = texture;
    }
}
