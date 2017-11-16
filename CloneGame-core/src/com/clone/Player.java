package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Player {
	
	private Ragdoll ragdoll;
	
	private boolean canJump;
	private boolean isActive;
	
	private Fixture hitbox;
	
	public Player(Ragdoll ragdoll) {
		canJump = false;
		isActive = true;
		this.ragdoll = ragdoll;
		ragdoll.body.setFixedRotation(true);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(2, 6, new Vector2(0, -0.5f), 0);
		
		FixtureDef def = new FixtureDef();
		def.shape = shape;
		def.filter.groupIndex = ragdoll.groupIndex;
		hitbox = ragdoll.body.createFixture(def);
		hitbox.setUserData(this);
	}

	public void applyForce2(float x, float y){
		ragdoll.body.applyForceToCenter(x, y, true);
	}
	
	public void applyForce(float force) {
		Vector2 oldVelocity = ragdoll.body.getLinearVelocity();
		ragdoll.body.setLinearVelocity(force,0);//it's buggued the gravity didn't apply anymore
	}
	
	public void jump(float velocity){
		if(canJump) {
			Vector2 oldVelocity = ragdoll.body.getLinearVelocity();
			ragdoll.body.setLinearVelocity(oldVelocity.x, velocity);
			
		}
	}
	
	public void dispose() {
		setActive(false);
		ragdoll.body.destroyFixture(hitbox);
		ragdoll.body.setFixedRotation(false);
	}
	
	public void setJump(boolean newJump) {
		this.canJump = newJump;
	}
	
	public void setActive(boolean newActive) {
		this.isActive = newActive;
	}
	
	public boolean getActive() {
		return this.isActive;
	}
}
