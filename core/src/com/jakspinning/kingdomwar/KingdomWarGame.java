package com.jakspinning.kingdomwar;

import com.artemis.World;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.jakspinning.kingdomwar.manager.CameraManager;
import com.jakspinning.kingdomwar.system.PrepareGraphicSystem;

public class KingdomWarGame extends ApplicationAdapter {
    private World world;

    @Override
	public void create () {
        world = new World();

        world.setManager(new CameraManager());

        world.setSystem(new PrepareGraphicSystem());

        world.initialize();
	}

	@Override
	public void render () {
        world.setDelta(Gdx.graphics.getDeltaTime());
        world.process();
    }
}
