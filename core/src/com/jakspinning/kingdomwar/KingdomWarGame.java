package com.jakspinning.kingdomwar;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.utils.EntityBuilder;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.jakspinning.kingdomwar.component.HexGridComponent;
import com.jakspinning.kingdomwar.component.PositionComponent;
import com.jakspinning.kingdomwar.component.TextureComponent;
import com.jakspinning.kingdomwar.manager.CameraManager;
import com.jakspinning.kingdomwar.manager.SpriteBatchManager;
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

        world.setSystem(new RendererSystem());
        //world.setSystem(new GridRendererSystem());

        world.initialize();

        Entity map = new EntityBuilder(world)
                .with(new HexGridComponent(10, 10))
                .build();

        Entity perso = new EntityBuilder(world)
                .with(new PositionComponent(0, 0))
                .with(new TextureComponent(new Texture("badlogic.jpg")))
                .build();
	}

	@Override
	public void render () {
        world.setDelta(Gdx.graphics.getDeltaTime());
        world.process();
    }
}
