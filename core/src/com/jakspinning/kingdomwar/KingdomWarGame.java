package com.jakspinning.kingdomwar;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.utils.EntityBuilder;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.jakspinning.kingdomwar.component.MapComponent;
import com.jakspinning.kingdomwar.component.PositionComponent;
import com.jakspinning.kingdomwar.component.TextureComponent;
import com.jakspinning.kingdomwar.manager.CameraManager;
import com.jakspinning.kingdomwar.manager.SelectionManager;
import com.jakspinning.kingdomwar.manager.SpriteBatchManager;
import com.jakspinning.kingdomwar.map.HexGridHelper;
import com.jakspinning.kingdomwar.map.TiledMapLoader;
import com.jakspinning.kingdomwar.map.tmx.LibgdxTmxMapLoader;
import com.jakspinning.kingdomwar.system.GridRendererSystem;
import com.jakspinning.kingdomwar.system.PrepareGraphicSystem;
import com.jakspinning.kingdomwar.system.RendererSystem;
import com.jakspinning.kingdomwar.system.SelectTileSystem;


public class KingdomWarGame extends ApplicationAdapter implements InputProcessor {
    private World world;
    private CameraManager cameraManager;

    @Override
	public void create () {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        world = new World();

        cameraManager = new CameraManager();
        world.setManager(cameraManager);

        world.setManager(new SpriteBatchManager());

        SelectionManager selectionManager = new SelectionManager();
        world.setManager(selectionManager);

        world.setSystem(new PrepareGraphicSystem());

        SelectTileSystem selectTileSystem = new SelectTileSystem();
        world.setSystem(selectTileSystem);
        world.setSystem(new GridRendererSystem());

        world.setSystem(new RendererSystem());

        world.initialize();


        LibgdxTmxMapLoader libgdxTmxMapLoader = new LibgdxTmxMapLoader();
        TiledMapLoader tiledMapLoader = new TiledMapLoader(libgdxTmxMapLoader);

        Entity map = new EntityBuilder(world)
                .with(tiledMapLoader.loadMap("test.tmx"))
                .build();

        Entity selectedTile = new EntityBuilder(world)
                .with(new PositionComponent(HexGridHelper.toHexCenterWorldCoord(1, 1, Constants.HEX_TILE_W, Constants.HEX_TILE_H, Constants.HEX_TILE_DEPTH / 2)))
                .with(new TextureComponent(new Texture("Tiles/tileSelected.png")))
                .build();
        selectionManager.position = selectedTile.getComponent(PositionComponent.class);
        selectionManager.map = map.getComponent(MapComponent.class);

        Entity perso = new EntityBuilder(world)
                .with(new PositionComponent(HexGridHelper.toHexCenterWorldCoord(1, 1, Constants.HEX_TILE_W, Constants.HEX_TILE_H, Constants.HEX_TILE_DEPTH)))
                        .with(new TextureComponent(new Texture("Tiles/alienBlue.png")))
                .build();
        Gdx.input.setInputProcessor(this);
        
        //J'aime po les warnings
        System.out.println("created " + perso + " " + map + " " + selectedTile);
    }

	@Override
	public void render () {
		handleInput(cameraManager.camera);
		cameraManager.camera.update();
        world.setDelta(Gdx.graphics.getDeltaTime());
        world.process();
        
    }

    @Override
    public void resize(int width, int height) {
        cameraManager.viewport.update(width, height);
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        cameraManager.camera.zoom += amount * 0.1;
        Gdx.app.debug("camera viewport", cameraManager.camera.viewportWidth + " - " + cameraManager.camera.viewportHeight);
        return false;
    }
}
