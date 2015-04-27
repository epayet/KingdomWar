package com.jakspinning.kingdomwar.system;

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
import com.jakspinning.kingdomwar.map.HexGridHelper;
import com.jakspinning.kingdomwar.map.HexTile;

/**
 * Created by emmanuel_payet on 16/04/15.
 */
@Wire
public class GridRendererSystem extends EntityProcessingSystem{
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
		MapComponent mapComponent = hexGridMapper.get(e);
		int h = mapComponent.mapHeight;
		int w = mapComponent.mapWidth;
		for(int y = h-1;y >= 0 ;y--){
			for(int x = 0; x < w;x++){
				HexTile tile = mapComponent.getTile(x,y);
				if(tile != null){
					Vector2 position = HexGridHelper.toWorldCoord(x, y,tile.height, Constants.HEX_TILE_W,Constants.HEX_TILE_H,Constants.HEX_TILE_DEPTH);
					//System.out.print("1, ");
					spriteBatchManager.spriteBatch.draw(tile.texture, position.x , position.y);
				}else {
					//System.out.print("0, ");
				}
			}
			//System.out.println("");
		}
		//System.out.println("================================");
	}


	@Override
	protected void end() {
		super.end();
		spriteBatchManager.spriteBatch.end();
	}
}
