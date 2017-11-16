package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class GroundSquare extends LevelsElement{
	final static float scale = 0.07f;
	public GroundSquare(World world, Vector2... points) {
		super(world, points);
		bodyDef.position.set(points[0]);
		//shape
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(30f * scale, 30f * scale);
		
		//fixture
		fixtureDef.shape = shape;
		fixtureDef.density = 2.5f;
		fixtureDef.friction = 0.1f;
		fixtureDef.restitution = 0.01f;
		
		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef).setUserData(this);
		
		shape.dispose();
	}
}
