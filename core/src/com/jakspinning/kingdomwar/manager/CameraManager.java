package com.jakspinning.kingdomwar.manager;

import com.artemis.Manager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jakspinning.kingdomwar.Constants;

/**
 * Created by manu on 21/03/2015.
 */
public class CameraManager extends Manager{
    public OrthographicCamera camera;
    public Viewport viewport;

    public CameraManager() {
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(600, 400, camera);
    }
}
