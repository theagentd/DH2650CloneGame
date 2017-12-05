package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.clone.fixture3d.BoxFixture3D;

public class BreackableWallColumn extends Breakable {
	public BreackableWallColumn(World world, Vector2 points) {
		super(world, points);
		addFixture(new BoxFixture3D(body, 0.25f, 0.5f, 0.25f, 30f * GroundSquare.SCALE, 90f * GroundSquare.SCALE, 20 * GroundSquare.SCALE, 0, 2.5f, 0.1f, 0.01f, (short)0, this));
	}
}