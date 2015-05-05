package com.jakspinning.kingdomwar.system;

import com.artemis.annotations.Wire;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.jakspinning.kingdomwar.Constants;
import com.jakspinning.kingdomwar.component.PositionComponent;
import com.jakspinning.kingdomwar.manager.CameraManager;
import com.jakspinning.kingdomwar.manager.SelectionManager;
import com.jakspinning.kingdomwar.map.HexGridHelper;

/**
 * Created by eptwalabha on 28/04/2015.
 */
@Wire
public class SelectTileSystem extends VoidEntitySystem {

    private CameraManager cameraManager;

    @Override
    protected void initialize() {
        this.cameraManager = world.getManager(CameraManager.class);
    }

    @Override
    protected void processSystem() {
        if (Gdx.input.isTouched()) {
            this.selectTile(Gdx.input.getX(), Gdx.input.getY());
        }
    }

    private void selectTile(int screenX, int screenY) {

        Vector3 pos = cameraManager.viewport.unproject(new Vector3(screenX, screenY, 0));

        Vector2 grid = HexGridHelper.pixelToHex((int) (pos.x - Constants.HEX_TILE_W / 2), (int) (pos.y - Constants.HEX_TILE_H / 2), 32);

        PositionComponent position = world.getManager(SelectionManager.class).position;
        position.position = HexGridHelper.toHexCenterWorldCoord((int) grid.x, (int) grid.y, Constants.HEX_TILE_W, Constants.HEX_TILE_H, Constants.HEX_TILE_DEPTH / 2);

    }
}
