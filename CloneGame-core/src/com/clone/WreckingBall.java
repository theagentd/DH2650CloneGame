package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class WreckingBall{
	public Body body;
	public World world;
	public Vector2 spawn;
	public WreckingBall(World world, Vector2...points) {
		this.world = world;
		spawn = points[0];
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(points[0]);	

		// Create a circle shape and set its radius to 6
		CircleShape circle = new CircleShape();
		circle.setRadius(8);
		
		// Create a fixture definition to apply our shape to
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 0.1f; 
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.5f;

		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef).setUserData(this);
		
		circle.dispose();		   
	}
	
	public void destroy() {
		world.destroyBody(body);
	}
}
