package com.jakspinning.kingdomwar.system;

import java.util.List;
import java.util.Map.Entry;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector2;
import com.jakspinning.kingdomwar.Constants;
import com.jakspinning.kingdomwar.component.MapComponent;
import com.jakspinning.kingdomwar.manager.CameraManager;
import com.jakspinning.kingdomwar.manager.SpriteBatchManager;
import com.jakspinning.kingdomwar.map.GridPosition;
import com.jakspinning.kingdomwar.map.HexGridHelper;
import com.jakspinning.kingdomwar.map.HexTile;

/**
 * Created by emmanuel_payet on 16/04/15.
 */
@Wire
public class GridRendererSystem extends EntityProcessingSystem{
	SpriteBatchManager spriteBatchManager;
	CameraManager cameraManager;
	ComponentMapper<MapComponent> mapComponentMapper;

	/**
	 * Creates a new EntityProcessingSystem.
	 *
	 * @param aspect the aspect to match entites
	 */
	@SuppressWarnings("unchecked")
	public GridRendererSystem() {
		super(Aspect.getAspectForAll(MapComponent.class));
	}

	@Override
	protected void begin() {
		super.begin();       
		cameraManager.camera.update();
		spriteBatchManager.spriteBatch.setProjectionMatrix(cameraManager.camera.combined);
		spriteBatchManager.spriteBatch.begin();
	}

	@Override
	protected void process(Entity e) {
		MapComponent mapComponent = mapComponentMapper.get(e);
		List<Entry<GridPosition, HexTile>> tiles = mapComponent.getSortedTiles();
		for (Entry<GridPosition, HexTile> entry : tiles) {
			GridPosition grid = entry.getKey();
			HexTile tile = entry.getValue();
			Vector2 position = HexGridHelper.toWorldCoord(grid.xGrid, grid.yGrid,tile.height, Constants.HEX_TILE_W,Constants.HEX_TILE_H,Constants.HEX_TILE_DEPTH);
			spriteBatchManager.spriteBatch.draw(tile.texture, position.x -Constants.HEX_TILE_W/2 , position.y-Constants.HEX_TILE_H/2-Constants.HEX_TILE_DEPTH/2); 
		}	
	}


	@Override
	protected void end() {
		super.end();
		spriteBatchManager.spriteBatch.end();
	}
}
