package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;

public class BouncingBlock extends LevelsElement{
	public BouncingBlock(World world, Vector2... points) {
		super(world, points);
		bodyDef.position.set(points[0]);
		//shape
		CircleShape shape = new CircleShape();
		shape.setRadius(2.5f);
		
		//fixture
		fixtureDef.shape = shape;
		fixtureDef.density = 2.5f;
		fixtureDef.friction = 0.1f;
		fixtureDef.restitution = 2f;
		
		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef).setUserData(this);
		
		shape.dispose();
	}
	public void destroy() {
		world.destroyBody(body);
	}
}
