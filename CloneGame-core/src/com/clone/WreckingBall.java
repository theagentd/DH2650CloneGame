package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.clone.fixture3d.SphereFixture3D;

public class WreckingBall{
	
	public Body body;
	public World world;
	public Vector2 spawn;
	public SphereFixture3D fixture;
	
	
	
	public WreckingBall(World world, Vector2 position) {
		this.world = world;
		spawn = position;
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(position);
		body = world.createBody(bodyDef);
		
		fixture = new SphereFixture3D(body, 1, 0.25f, 0.25f, 8, 0, 0.1f, 0.4f, 0.5f, (short)0, this);
		
	}
	
	public void destroy() {
		if(fixture != null){
			fixture.dispose();
			world.destroyBody(body);
		}
		
		fixture = null;
		body = null;
		
	}
}
