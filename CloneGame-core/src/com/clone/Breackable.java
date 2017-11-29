package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Breackable extends LevelsElement {
	Fixture fix;
	public Breackable(World world, Vector2...points) {
		super(world, points);
	}
	
	public void destroy() {
		world.destroyBody(body);
	}
}