package com.jakspinning.kingdomwar.system;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;

/**
 * Created by emmanuel_payet on 16/04/15.
 */
public class GridRendererSystem extends EntityProcessingSystem{
    /**
     * Creates a new EntityProcessingSystem.
     *
     * @param aspect the aspect to match entites
     */
    public GridRendererSystem(Aspect aspect) {
        super(aspect);
    }

    @Override
    protected void process(Entity e) {

    }
}
