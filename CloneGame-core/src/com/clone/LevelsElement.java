package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public abstract class LevelsElement extends StaticObject{
	
	public final static float SCALE = 0.07f;
	
	public LevelsElement(World world, Vector2 position) {
		super(world, position);
	}
}
