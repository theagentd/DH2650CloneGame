package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.clone.fixture3d.BoxFixture3D;

public class BreackableWallRaw extends Breakable {
	public BreackableWallRaw(World world, Vector2 position) {
		super(world, position);
		addFixture(new BoxFixture3D(body, 0.25f, 0.5f, 0.25f, 90f * SCALE, 30f * SCALE, 20 * SCALE, 0, 2.5f, 0.1f, 0.01f, (short)0, this));
	}
}
