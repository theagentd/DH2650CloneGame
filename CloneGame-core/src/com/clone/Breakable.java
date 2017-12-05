package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Breakable extends LevelsElement {
	public Breakable(World world, Vector2 position) {
		super(world, position);
	}
}
