package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class GroudSquare extends LevelsElement{
	public GroudSquare(World world, Vector2... points) {
		super(world, points);
		bodyDef.position.set(points[0]);
		
		//shape
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(10f, 10f);
		
		//fixture
		fixtureDef.shape = shape;
		fixtureDef.density = 2.5f;
		fixtureDef.friction = 0.5f;
		fixtureDef.restitution = 0.01f;
		
		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		
		shape.dispose();
	}

}
