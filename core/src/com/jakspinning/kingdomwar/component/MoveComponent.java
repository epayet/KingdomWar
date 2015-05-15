package com.jakspinning.kingdomwar.component;

import com.artemis.Component;
import com.jakspinning.kingdomwar.map.GridPosition;

/**
 * Created by emmanuel_payet on 15/05/15.
 */
public class MoveComponent extends Component {

    public GridPosition targetPosition;

    public MoveComponent(){

    }

    public MoveComponent(GridPosition gridPosition) {
        this.targetPosition = gridPosition;
    }
}
