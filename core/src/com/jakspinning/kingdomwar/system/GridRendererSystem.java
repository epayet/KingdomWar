package com.jakspinning.kingdomwar.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector2;
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
        for(int xGrid = 0;xGrid < gridComponent.width;xGrid++){
            for(int yGrid = 0;yGrid < gridComponent.height;yGrid++){
                float size = 25;
                Vector2 position = HexGridHelper.toWorldCoord(xGrid,yGrid,size);
                float h = size*2;
                float w = (float)Math.sqrt(3)/2*h;
                spriteBatchManager.spriteBatch.draw(textureComponent.texture, position.x, position.y,w,h);
            }
        }
    }

    @Override
    protected void end() {
        super.end();
        spriteBatchManager.spriteBatch.end();
    }
}
