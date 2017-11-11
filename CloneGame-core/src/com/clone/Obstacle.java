package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Obstacle extends StaticObject{
	public Obstacle(World world, Vector2... points) {
		super(world, points);
		this.killThePlayer = true;
	}
}
