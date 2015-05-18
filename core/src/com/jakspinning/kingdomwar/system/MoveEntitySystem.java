package com.jakspinning.kingdomwar.system;

import com.artemis.Aspect;
import com.artemis.ComponentManager;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.jakspinning.kingdomwar.Constants;
import com.jakspinning.kingdomwar.component.MoveComponent;
import com.jakspinning.kingdomwar.component.PositionComponent;
import com.jakspinning.kingdomwar.map.GridPosition;
import com.jakspinning.kingdomwar.map.HexGridHelper;

import javax.swing.text.Position;
import java.util.Vector;

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
        Vector2 currentTarget = moveComponent.currentTarget;

        if(currentTarget != null){
            Vector2 direction = new Vector2();
            direction.x = currentTarget.x - positionComponent.position.x;
            direction.y = currentTarget.y - positionComponent.position.y;
            positionComponent.position.add(direction.nor().scl(moveComponent.velocity* Gdx.graphics.getDeltaTime()));
            if(isOnTile(positionComponent.position,currentTarget)){
                moveComponent.currentTarget = null;
            }
        }else{

            if(moveComponent.path != null && !moveComponent.path.isEmpty()){
                System.out.println(moveComponent.path.size());
                GridPosition pos = moveComponent.path.remove(0);
                moveComponent.currentTarget = HexGridHelper.toWorldCoord(pos.xGrid,pos.yGrid,0, Constants.HEX_TILE_W,Constants.HEX_TILE_H,Constants.HEX_TILE_DEPTH/2);
            }
        }


    }

    private boolean isOnTile(Vector2 source, Vector2 currentTarget) {
        return Math.sqrt(Math.pow(source.x-currentTarget.x,2)+Math.pow(source.y-currentTarget.y,2)) < Constants.MIN_TEST_DISTANCE;
    }
}
