package com.jakspinning.kingdomwar.system;

import com.artemis.annotations.Wire;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.jakspinning.kingdomwar.manager.CameraManager;

/**
 * Created by manu on 21/03/2015.
 */
@Wire
public class PrepareGraphicSystem extends VoidEntitySystem{

    CameraManager cameraManager;

    @Override
    protected void processSystem() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cameraManager.camera.update();
    }
}
