package com.jakspinning.kingdomwar;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.utils.EntityBuilder;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.jakspinning.kingdomwar.component.PositionComponent;
import com.jakspinning.kingdomwar.component.TextureComponent;
import com.jakspinning.kingdomwar.manager.CameraManager;
import com.jakspinning.kingdomwar.manager.SpriteBatchManager;
import com.jakspinning.kingdomwar.map.HexGridHelper;
import com.jakspinning.kingdomwar.map.TiledMapLoader;
import com.jakspinning.kingdomwar.map.tmx.ITmxMapLoader;
import com.jakspinning.kingdomwar.map.tmx.LibgdxTmxMapLoader;
import com.jakspinning.kingdomwar.system.GridRendererSystem;
import com.jakspinning.kingdomwar.system.PrepareGraphicSystem;
import com.jakspinning.kingdomwar.system.RendererSystem;


public class KingdomWarGame extends ApplicationAdapter {
    private World world;

    @Override
	public void create () {
        world = new World();

        world.setManager(new CameraManager());
        world.setManager(new SpriteBatchManager());

        world.setSystem(new PrepareGraphicSystem());

        world.setSystem(new GridRendererSystem());

        world.setSystem(new RendererSystem());

        world.initialize();


        LibgdxTmxMapLoader libgdxTmxMapLoader = new LibgdxTmxMapLoader();
        TiledMapLoader tiledMapLoader = new TiledMapLoader(libgdxTmxMapLoader);

        Entity map = new EntityBuilder(world)
                .with(tiledMapLoader.loadMap("test.tmx"))
                .build();

        Entity perso = new EntityBuilder(world)
                .with(new PositionComponent(HexGridHelper.toHexCenterWorldCoord(1, 1, Constants.HEX_TILE_W, Constants.HEX_TILE_H, Constants.HEX_TILE_DEPTH)))
                        .with(new TextureComponent(new Texture("Tiles/alienBlue.png")))
                .build();
	}

	@Override
	public void render () {
		CameraManager cameraManager = world.getManager(CameraManager.class);
		handleInput(cameraManager.camera);
		cameraManager.camera.update();
        world.setDelta(Gdx.graphics.getDeltaTime());
        world.process();
        
    }
	
	private void handleInput(OrthographicCamera cam) {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            cam.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            cam.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cam.translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cam.translate(3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cam.translate(0, -3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cam.translate(0, 3, 0);
        }
        int rotationSpeed = 1;
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            cam.rotate(-rotationSpeed , 0, 0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            cam.rotate(rotationSpeed, 0, 0, 1);
        }


    }
}
