package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class BreackableWallColumn extends Breackable {
	Fixture fix;
	public BreackableWallColumn(World world, Vector2... points) {
		super(world, points);
		bodyDef.position.set(points[0]);
		//shape
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(20f * GroundSquare.scale, 90f * GroundSquare.scale);
		
		//fixture
		fixtureDef.shape = shape;
		fixtureDef.density = 2.5f;
		fixtureDef.friction = 0.1f;
		fixtureDef.restitution = 0.01f;
		
		body = world.createBody(bodyDef);
		fix = body.createFixture(fixtureDef);
		fix.setUserData(this);
		shape.dispose();
	}
}