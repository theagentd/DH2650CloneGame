package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Player {
	
	public Ragdoll ragdoll;
	
	public boolean canJump;
	public boolean isActive;
	
	public Fixture rectHitbox;	
	public Fixture circleHitbox;
	
	public Player(Ragdoll ragdoll) {
		canJump = false;
		isActive = true;
		this.ragdoll = ragdoll;
		ragdoll.torsoBody.setFixedRotation(true);
		
		
		PolygonShape rectShape = new PolygonShape();
		rectShape.setAsBox(2.5f, 5f, new Vector2(0, +0.75f), 0);
		
		FixtureDef rectDef = new FixtureDef();
		rectDef.shape = rectShape;
		rectDef.restitution = ragdoll.restitution;
		rectDef.filter.groupIndex = ragdoll.groupIndex;
		rectHitbox = ragdoll.torsoBody.createFixture(rectDef);
		rectHitbox.setUserData(this);
		
		
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(2.5f);
		circleShape.setPosition(new Vector2(0, -4.25f));
		
		FixtureDef circleDef = new FixtureDef();
		circleDef.shape = circleShape;
		circleDef.restitution = ragdoll.restitution;
		circleDef.filter.groupIndex = ragdoll.groupIndex;
		circleHitbox = ragdoll.torsoBody.createFixture(circleDef);
		circleHitbox.setUserData(this);
	}

	public void applyForce2(float x, float y){
		ragdoll.torsoBody.applyLinearImpulse(x, y, 0, 0, true);
	}
	
	public void applyForce(float force) {
		Vector2 oldVelocity = ragdoll.torsoBody.getLinearVelocity();
		ragdoll.torsoBody.setLinearVelocity(force, oldVelocity.y);
	}
	
	public void jump(float velocity){
		if(canJump) {
			Vector2 oldVelocity = ragdoll.torsoBody.getLinearVelocity();
			ragdoll.torsoBody.setLinearVelocity(oldVelocity.x, velocity);
			System.out.println(ragdoll.torsoBody.getLinearVelocity());
		}
	}
	
	public void dispose() {
		setActive(false);
		ragdoll.torsoBody.destroyFixture(rectHitbox);
		ragdoll.torsoBody.destroyFixture(circleHitbox);
		ragdoll.torsoBody.setFixedRotation(false);
	}
	
	public void setJump(boolean newJump) {
		this.canJump = newJump;
	}
	
	public boolean isCanJump() {
		return canJump;
	}
	
	public void setActive(boolean newActive) {
		this.isActive = newActive;
	}
	
	public boolean getActive() {
		return this.isActive;
	}
}
