package com.jakspinning.kingdomwar.system;

import com.artemis.Aspect;
import com.artemis.ComponentManager;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.jakspinning.kingdomwar.component.MoveComponent;
import com.jakspinning.kingdomwar.component.PositionComponent;
import com.jakspinning.kingdomwar.map.HexGridHelper;

import javax.swing.text.Position;

/**
 * Created by emmanuel_payet on 15/05/15.
 */
@Wire
public class MoveEntitySystem extends EntityProcessingSystem{
    private ComponentMapper<MoveComponent> moveComponentMapper;
    private ComponentMapper<PositionComponent> positionComponentMapper;
    /**
     * Creates a new EntityProcessingSystem.
     *
     * @param aspect the aspect to match entites
     */
    public MoveEntitySystem() {
        super(Aspect.getAspectForAll(MoveComponent.class,PositionComponent.class));
    }

    @Override
    protected void process(Entity e) {
        MoveComponent moveComponent = moveComponentMapper.get(e);
        PositionComponent positionComponent = positionComponentMapper.get(e);
        if(moveComponent.targetPosition != null){
            positionComponent.setGridPosition(moveComponent.targetPosition);
            moveComponent.targetPosition = null;
        }
    }
}
