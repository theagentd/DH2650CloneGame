package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class BouncingPlayer extends Player{
	
	//private Fixture circleHitbox;
	private World world;
	
	public BouncingPlayer(Ragdoll ragdoll, World world) {
		super(ragdoll);
		
		this.world = world;
		
		/*CircleShape circleShape = new CircleShape();
		circleShape.setRadius(2.5f);
		circleShape.setPosition(new Vector2(0, -4.25f));
		
		FixtureDef circleDef = new FixtureDef();
		circleDef.shape = circleShape;
		circleDef.restitution = ragdoll.restitution;
		circleDef.filter.groupIndex = ragdoll.groupIndex;
		circleHitbox = ragdoll.body.createFixture(circleDef);
		circleHitbox.setUserData(this);*/
	}

	public void applyForce2(float x, float y){
		super.ragdoll.torsoBody.applyLinearImpulse(x, y, 0, 0, true);
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
	@Override
	public void dispose() {
		setActive(false);
		ragdoll.torsoBody.destroyFixture(rectHitbox);
		ragdoll.torsoBody.destroyFixture(circleHitbox);
		ragdoll.torsoBody.setFixedRotation(false);
		new BouncingBlock(world, new Vector2(ragdoll.torsoBody.getPosition().x, ragdoll.torsoBody.getPosition().y -4.5f));
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

