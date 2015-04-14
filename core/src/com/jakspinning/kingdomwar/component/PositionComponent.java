package com.jakspinning.kingdomwar.component;

import com.artemis.Component;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by emmanuel_payet on 14/04/15.
 */
public class PositionComponent extends Component {
    public float x;
    public float y;

    public PositionComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
