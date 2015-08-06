package com.jakspinning.kingdomwar.manager;

import com.artemis.Manager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.jakspinning.kingdomwar.Constants;

/**
 * Created by manu on 21/03/2015.
 */
public class CameraManager extends Manager{
    public OrthographicCamera camera;

    public CameraManager() {
        camera = new OrthographicCamera(Constants.CAMERA_VIEWPORT_WIDTH, Constants.CAMERA_VIEWPORT_HEIGHT);
    }
}
