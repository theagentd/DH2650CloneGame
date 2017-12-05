package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.clone.fixture3d.SphereFixture3D;

public class BouncingBlock extends LevelsElement{
	public BouncingBlock(World world, Vector2 position) {
		super(world, position);
		addFixture(new SphereFixture3D(body, 0.25f, 0.25f, 0.25f, 2.5f, 0, 2.5f, 0.1f, 2f, (short)0, this));
	}
}
