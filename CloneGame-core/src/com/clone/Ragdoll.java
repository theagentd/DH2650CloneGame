package com.clone;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

public class Ragdoll { 
	
	private static int groupIndex = 1;

	public Body head;
	public Body body;
	
	private Body leftArm, rightArm;
	private Body leftForeArm, rightForeArm;
	
	private Body leftUpperLeg, rightUpperLeg;
	private Body leftLowerLeg, rightLowerLeg;
	
	public RevoluteJoint neckJoint;
	
	public RevoluteJoint leftShoulderJoint, rightShoulderJoint;	
	public RevoluteJoint leftElbowJoint, rightElbowJoint;

	public RevoluteJoint leftHipJoint, rightHipJoint;
	public RevoluteJoint leftKneeJoint, rightKneeJoint;
	
	
	public Ragdoll(World world, float x, float y) {
		
		int baseIndex = groupIndex++;
		short group = (short)-baseIndex;
		
		//A negative group index that isn't 0 means that the objects within that group do not collide.
		//By setting all bodies in the ragdoll to the same negative non-zero group,
		//we can prevent they body parts from colliding with each other.
		

		head = createCircle(world, group, x, y+3.5f, 1.5f);		
		body = createRectangle(world, group, x, y, 0.7f, 2);

		leftArm = createRectangle(world, group, x, y+0.5f, 0.3f, 1.2f);
		rightArm = createRectangle(world, group, x, y+0.5f, 0.3f, 1.2f);

		leftForeArm = createRectangle(world, group, x, y-1.5f, 0.3f, 1.2f);
		rightForeArm = createRectangle(world, group, x, y-1.5f, 0.3f, 1.2f);
		
		leftUpperLeg = createRectangle(world, group, x, y-3.2f, 0.3f, 1.2f);
		rightUpperLeg = createRectangle(world, group, x, y-3.2f, 0.3f, 1.2f);
		
		leftLowerLeg = createRectangle(world, group, x, y-5.6f, 0.3f, 1.2f);
		rightLowerLeg = createRectangle(world, group, x, y-5.6f, 0.3f, 1.2f);
		
		
		
		
		neckJoint = createRevoluteJoint(world, head, 0, -1.7f, body, 0, +2, 0.75f);

		leftShoulderJoint = createRevoluteJoint(world, body, 0, +2, leftArm, 0, 1.2f, 0f);
		rightShoulderJoint = createRevoluteJoint(world, body, 0, +2, rightArm, 0, 1.2f, 0f);

		leftElbowJoint = createRevoluteJoint(world, leftArm, 0, -1.2f, leftForeArm, 0, 1.2f, 2.4f);
		rightElbowJoint = createRevoluteJoint(world, rightArm, 0, -1.2f, rightForeArm, 0, 1.2f, 2.4f);


		leftHipJoint = createRevoluteJoint(world, body, 0, -2f, leftUpperLeg, 0, 1.2f, 2.0f);
		rightHipJoint = createRevoluteJoint(world, body, 0, -2f, rightUpperLeg, 0, 1.2f, 2.0f);

		leftKneeJoint = createRevoluteJoint(world, leftUpperLeg, 0, -1.2f, leftLowerLeg, 0, 1.2f, 2.0f);
		rightKneeJoint = createRevoluteJoint(world, rightUpperLeg, 0, -1.2f, rightLowerLeg, 0, 1.2f, 2.0f);
		
		
		
	}

	private Body createBody(World world, float x, float y){
		
		BodyDef bd = new BodyDef();
		bd.type = BodyType.DynamicBody;
		bd.position.set(x, y);
		return world.createBody(bd);
	}

	private Body createRectangle(World world, short groupIndex, float x, float y, float width, float height) {
		
		Body b = createBody(world, x, y);
		
		PolygonShape s = new PolygonShape();
		s.setAsBox(width, height);
		
		FixtureDef fd = new FixtureDef();
		fd.shape = s;
		fd.friction = 0.75f;
		fd.density = 1;
		fd.restitution = 0.1f;
		
		fd.filter.groupIndex = groupIndex;
		
		b.createFixture(fd);
		
		return b;
	}

	private Body createCircle(World world, short groupIndex, float x, float y, float radius) {

		Body b = createBody(world, x, y);
		
		CircleShape s = new CircleShape();
		s.setRadius(radius);
		
		FixtureDef fd = new FixtureDef();
		fd.shape = s;
		fd.friction = 0.75f;
		fd.density = 1;
		fd.restitution = 0.1f;
		
		fd.filter.groupIndex = groupIndex;
		
		b.createFixture(fd);
		
		return b;
	}
	
	private RevoluteJoint createRevoluteJoint(World world, Body b1, float x1, float y1, Body b2, float x2, float y2, float maxAngle) {
		
		RevoluteJointDef def = new RevoluteJointDef();
		
		def.bodyA = b1;
		def.localAnchorA.set(x1, y1);
		
		def.bodyB = b2;
		def.localAnchorB.set(x2, y2);
		
		def.collideConnected = false;
		
		def.enableLimit = maxAngle != 0;
		def.lowerAngle = -maxAngle;
		def.upperAngle = +maxAngle;
		
		
		
		return (RevoluteJoint)world.createJoint(def);
	}
	
	public void dispose(World world) {
		world.destroyJoint(neckJoint);

		world.destroyJoint(leftShoulderJoint);
		world.destroyJoint(rightShoulderJoint);
		world.destroyJoint(leftElbowJoint);
		world.destroyJoint(rightElbowJoint);

		world.destroyJoint(leftHipJoint);
		world.destroyJoint(rightHipJoint);
		world.destroyJoint(leftKneeJoint);
		world.destroyJoint(rightKneeJoint);
		
		world.destroyBody(head);

		world.destroyBody(leftArm);
		world.destroyBody(rightArm);
		world.destroyBody(leftForeArm);
		world.destroyBody(rightForeArm);

		world.destroyBody(leftUpperLeg);
		world.destroyBody(rightUpperLeg);
		world.destroyBody(leftLowerLeg);
		world.destroyBody(rightLowerLeg);
		
		
	}
	
}