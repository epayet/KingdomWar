package com.jakspinning.kingdomwar.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;
import com.jakspinning.kingdomwar.Constants;
import com.jakspinning.kingdomwar.map.GridPosition;
import com.jakspinning.kingdomwar.map.HexGridHelper;

/**
 * Created by emmanuel_payet on 14/04/15.
 */
public class PositionComponent extends Component {
    public Vector2 position;

    public PositionComponent(float x, float y) {
        this.position = new Vector2(x,y);
    }

    public PositionComponent(Vector2 position) {
        this.position = new Vector2(position);
    }

    public void setGridPosition(GridPosition targetPosition) {
        this.position = HexGridHelper.toWorldCoord(targetPosition.xGrid,targetPosition.yGrid,0, Constants.HEX_TILE_W,Constants.HEX_TILE_H,Constants.HEX_TILE_DEPTH);
    }
}
