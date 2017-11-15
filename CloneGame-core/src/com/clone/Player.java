package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Player {
	
	private Ragdoll ragdoll;
	
	private Fixture hitbox;
	
	public Player(Ragdoll ragdoll) {
		this.ragdoll = ragdoll;
		ragdoll.body.setFixedRotation(true);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(2, 6, new Vector2(0, -0.5f), 0);
		
		FixtureDef def = new FixtureDef();
		def.shape = shape;
		def.filter.groupIndex = ragdoll.groupIndex;
		hitbox = ragdoll.body.createFixture(def);
	}

	public void applyForce(float x, float y){
		ragdoll.body.applyForceToCenter(x, y, true);
	}
	public void jump(float velocity){
		Vector2 oldVelocity = ragdoll.body.getLinearVelocity();
		ragdoll.body.setLinearVelocity(oldVelocity.x, velocity);
	}
	
	public void dispose() {
		ragdoll.body.destroyFixture(hitbox);
		ragdoll.body.setFixedRotation(false);
	}
}
