package com.jakspinning.kingdomwar.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;
import com.jakspinning.kingdomwar.map.GridPosition;

import java.util.List;

/**
 * Created by emmanuel_payet on 15/05/15.
 */
public class MoveComponent extends Component {

    public List<GridPosition> path;
    public Vector2 currentTarget;
    public float velocity = 50;

    public MoveComponent(){
    }

    public MoveComponent(List<GridPosition> path) {
        this.path = path;
    }
}
