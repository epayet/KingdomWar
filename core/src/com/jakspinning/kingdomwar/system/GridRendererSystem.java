package com.jakspinning.kingdomwar.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector2;
import com.jakspinning.kingdomwar.Constants;
import com.jakspinning.kingdomwar.HexGridHelper;
import com.jakspinning.kingdomwar.component.HexGridComponent;
import com.jakspinning.kingdomwar.component.TextureComponent;
import com.jakspinning.kingdomwar.manager.SpriteBatchManager;

/**
 * Created by emmanuel_payet on 16/04/15.
 */
@Wire
public class GridRendererSystem extends EntityProcessingSystem{

    SpriteBatchManager spriteBatchManager;
    ComponentMapper<HexGridComponent> hexGridMapper;
    ComponentMapper<TextureComponent> textureMapper;

    /**
     * Creates a new EntityProcessingSystem.
     *
     * @param aspect the aspect to match entites
     */
    public GridRendererSystem() {
        super(Aspect.getAspectForAll(HexGridComponent.class));
    }

    @Override
    protected void begin() {
        super.begin();
        spriteBatchManager.spriteBatch.begin();
    }

    @Override
    protected void process(Entity e) {
        HexGridComponent gridComponent = hexGridMapper.get(e);
        TextureComponent textureComponent = textureMapper.get(e);

            for(int yGrid = gridComponent.height;yGrid >=0;yGrid--){
                for(int xGrid = 0;xGrid < gridComponent.width;xGrid++){

                Vector2 position = HexGridHelper.toWorldCoord(xGrid,yGrid, Constants.HEX_TILE_W,Constants.HEX_TILE_H);

                spriteBatchManager.spriteBatch.draw(textureComponent.texture, position.x, position.y);
            }
        }
    }

    @Override
    protected void end() {
        super.end();
        spriteBatchManager.spriteBatch.end();
    }
}
