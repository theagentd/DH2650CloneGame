package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public abstract class LevelsElement extends StaticObject{
	public LevelsElement(World world, Vector2...points) {
		super(world, points);
		this.killThePlayer = false;
	}
}
