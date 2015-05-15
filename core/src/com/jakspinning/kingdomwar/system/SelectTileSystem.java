package com.jakspinning.kingdomwar.system;

import com.artemis.annotations.Wire;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.jakspinning.kingdomwar.Constants;
import com.jakspinning.kingdomwar.component.MapComponent;
import com.jakspinning.kingdomwar.component.PositionComponent;
import com.jakspinning.kingdomwar.manager.CameraManager;
import com.jakspinning.kingdomwar.manager.SelectionManager;
import com.jakspinning.kingdomwar.map.GridPosition;
import com.jakspinning.kingdomwar.map.HexGridHelper;
import com.jakspinning.kingdomwar.map.HexTile;

/**
 * Created by eptwalabha on 28/04/2015.
 */
@Wire
public class SelectTileSystem extends VoidEntitySystem {

	private CameraManager cameraManager;
	private SelectionManager selectionManager;

	@Override
	protected void initialize() {
	}

	@Override
	protected void processSystem() {
		if (Gdx.input.isTouched()) {
			this.selectTile(Gdx.input.getX(), Gdx.input.getY());
		}
	}

	private void selectTile(int screenX, int screenY) {

		Vector3 pos = cameraManager.viewport.unproject(new Vector3(screenX, screenY, 0));

		PositionComponent position = selectionManager.position;
		MapComponent map = selectionManager.map;

		GridPosition grid = HexGridHelper.toHexCoord(pos.x, pos.y, Constants.HEX_TILE_W, Constants.HEX_TILE_H);
		HexTile tile = map.getTile(grid);
		if(tile != null){
			if(tile.height > 0){
				// Might be a little brainfucked
				pos.y -= tile.height*Constants.HEX_TILE_DEPTH; 
				grid = HexGridHelper.toHexCoord(pos.x, pos.y, Constants.HEX_TILE_W, Constants.HEX_TILE_H);
				tile = map.getTile(grid);
			}
			Vector2 newPos = HexGridHelper.toWorldCoord(grid.xGrid, grid.yGrid, tile.height, Constants.HEX_TILE_W, Constants.HEX_TILE_H, Constants.HEX_TILE_DEPTH);        

			position.position.x = newPos.x;
			position.position.y = newPos.y;
		}
	}
}
