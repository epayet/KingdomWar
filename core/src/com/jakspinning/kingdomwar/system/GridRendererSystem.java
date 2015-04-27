package com.jakspinning.kingdomwar.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.jakspinning.kingdomwar.Constants;
import com.jakspinning.kingdomwar.map.HexGridHelper;
import com.jakspinning.kingdomwar.component.MapComponent;
import com.jakspinning.kingdomwar.manager.CameraManager;
import com.jakspinning.kingdomwar.manager.PointyTopHexGridMapRenderer;
import com.jakspinning.kingdomwar.manager.SpriteBatchManager;

/**
 * Created by emmanuel_payet on 16/04/15.
 */
@Wire
public class GridRendererSystem extends EntityProcessingSystem{
	PointyTopHexGridMapRenderer renderer;
	SpriteBatchManager spriteBatchManager;
	CameraManager cameraManager;
	ComponentMapper<MapComponent> hexGridMapper;

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
		MapComponent gridComponent = hexGridMapper.get(e);
		for (MapLayer layer : gridComponent..getLayers()) {
			if(layer instanceof TiledMapTileLayer){
				renderTileLayer((TiledMapTileLayer) layer);
			}
		}	
	}

	public void renderTileLayer (TiledMapTileLayer layer) {

		final int layerWidth = layer.getWidth();
		final int layerHeight = layer.getHeight();
		final int layerZ =  Integer.parseInt(layer.getProperties().get("height").toString());
		final float layerTileWidth = layer.getTileWidth();
		final float layerTileHeight = layer.getTileHeight() - Constants.HEX_TILE_DEPTH ;


		final int col1 = 0;
		final int col2 = layerWidth;

		final int row1 = 0;
		final int row2 = layerHeight;


		for (int row = row2; row > row1; row--) {
			for (int col = col1; col < col2; col++) {	
				Vector2 position = HexGridHelper.toWorldCoord(col, row, layerTileWidth, layerTileHeight);
				final TiledMapTileLayer.Cell cell = layer.getCell(col, row);
				if (cell == null) {
					continue;
				}
				position.y += layerZ*Constants.HEX_TILE_DEPTH;

				final TiledMapTile tile = cell.getTile();
				if (tile != null) {
					if (tile instanceof AnimatedTiledMapTile) continue;
					spriteBatchManager.spriteBatch.draw(tile.getTextureRegion(), position.x , position.y);
				}        
			}
		}
	}

	@Override
	protected void end() {
		super.end();
		spriteBatchManager.spriteBatch.end();
	}
}
