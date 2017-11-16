package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Level2 {
	public Level2(World world) {
		new WreckingBall(world, new Vector2(500*GroundSquare.scale, 200));
	}
}
