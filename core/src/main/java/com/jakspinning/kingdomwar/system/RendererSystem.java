package com.jakspinning.kingdomwar.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.jakspinning.kingdomwar.component.PositionComponent;
import com.jakspinning.kingdomwar.component.TextureComponent;
import com.jakspinning.kingdomwar.manager.SpriteBatchManager;

/**
 * Created by emmanuel_payet on 14/04/15.
 */
@Wire
public class RendererSystem extends EntityProcessingSystem{

    SpriteBatchManager spriteBatchManager;
    ComponentMapper<PositionComponent> positionMapper;
    ComponentMapper<TextureComponent> textureMapper;

    /**
     * Creates a new EntityProcessingSystem.
     *
     * @param aspect the aspect to match entites
     */
    public RendererSystem() {
        super(Aspect.getAspectForAll(PositionComponent.class, TextureComponent.class));
    }

    @Override
    protected void begin() {
        super.begin();
        spriteBatchManager.spriteBatch.begin();
    }

    @Override
    protected void process(Entity e) {
        TextureComponent textureComponent = textureMapper.get(e);
        PositionComponent positionComponent = positionMapper.get(e);
        spriteBatchManager.spriteBatch.draw(textureComponent.texture, positionComponent.position.x-textureComponent.texture.getWidth()/2,positionComponent.position.y-textureComponent.texture.getHeight()/2);
    }

    @Override
    protected void end() {
        super.end();
        spriteBatchManager.spriteBatch.end();
    }
}
