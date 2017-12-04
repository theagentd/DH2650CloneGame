package com.clone;

import static com.clone.LevelsElement.SCALE;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.clone.fixture3d.ConeFixture3D;

public class SpikeB extends Obstacle{
	public SpikeB(World world, Vector2 position) {
		super(world, new Vector2(position).scl(2).add(90*SCALE, 260*SCALE));
		
		addFixture(new ConeFixture3D(body, 1, 0.25f, 0.25f, 90*SCALE, 260f*SCALE, 90*SCALE, 0, 2.5f, 0.5f, 0.01f, (short)0, this));
	}

}
