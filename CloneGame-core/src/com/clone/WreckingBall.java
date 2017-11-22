package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class WreckingBall{
	public Body body;
	
	public WreckingBall(World world, Vector2...points) {
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set(points[0]);	

		// Create a circle shape and set its radius to 6
		CircleShape circle = new CircleShape();
		circle.setRadius(8);
		
		// Create a fixture definition to apply our shape to
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 0.1f; 
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 1f;

		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef).setUserData(this);
		body.setLinearVelocity(new Vector2(0,-9.81f*4));
		
		circle.dispose();		   
	}
}
