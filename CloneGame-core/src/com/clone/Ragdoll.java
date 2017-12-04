package com.clone;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.clone.fixture3d.BoxFixture3D;
import com.clone.fixture3d.ConeFixture3D;
import com.clone.fixture3d.Fixture3D;
import com.clone.fixture3d.SphereFixture3D;

public class Ragdoll { 
	
	private static int groupCounter = 1;
	
	public final short groupIndex;
	
	public final float restitution;
	
	

	public final Body headBody;
	public final Body torsoBody;
	
	public final Body leftArmBody, rightArmBody;
	public final Body leftForeArmBody, rightForeArmBody;
	
	public final Body leftUpperLegBody, rightUpperLegBody;
	public final Body leftLowerLegBody, rightLowerLegBody;
	
	
	public final Fixture3D headFixture;
	public final Fixture3D torsoFixture;
	
	public final Fixture3D leftArmFixture, rightArmFixture;
	public final Fixture3D leftForeArmFixture, rightForeArmFixture;
	
	public final Fixture3D leftUpperLegFixture, rightUpperLegFixture;
	public final Fixture3D leftLowerLegFixture, rightLowerLegFixture;
	
	
	
	
	
	
	public final RevoluteJoint neckJoint;
	
	public final RevoluteJoint leftShoulderJoint, rightShoulderJoint;	
	public final RevoluteJoint leftElbowJoint, rightElbowJoint;

	public final RevoluteJoint leftHipJoint, rightHipJoint;
	public final RevoluteJoint leftKneeJoint, rightKneeJoint;
	
	
	public Ragdoll(World world, float x, float y, float restitution) {
		
		int baseIndex = groupCounter++;
		groupIndex = (short)-baseIndex;
		
		this.restitution = restitution;
		
		//A negative group index that isn't 0 means that the objects within that group do not collide.
		//By setting all bodies in the ragdoll to the same negative non-zero group,
		//we can prevent they body parts from colliding with each other.


		float friction = 0.75f;
		float nonTorsoDensity = 0.01f;
		float nonTorsoDamping = 10;
		
		float r = 1;
		float g = 1;
		float b = 1;
		
		torsoBody = createBody(world, x, y, 0);
		torsoFixture = new BoxFixture3D(torsoBody, r, g, b, 0.7f, 2, 1.1f, 0, 1, friction, restitution, groupIndex, this);
		
		headBody = createBody(world, x, y+3.5f, nonTorsoDamping);
		headFixture = new SphereFixture3D(headBody, r, g, b, 1.5f, 0, nonTorsoDensity, friction, restitution, groupIndex, this);

		leftArmBody = createBody(world, x, y+0.5f, nonTorsoDamping);
		rightArmBody = createBody(world, x, y+0.5f, nonTorsoDamping);
		leftArmFixture = new BoxFixture3D(leftArmBody, r, g, b, 0.3f, 1.2f, 0.3f, -1.1f, nonTorsoDensity, friction, restitution, groupIndex, this);
		rightArmFixture = new BoxFixture3D(rightArmBody, r, g, b, 0.3f, 1.2f, 0.3f, +1.1f, nonTorsoDensity, friction, restitution, groupIndex, this);

		leftForeArmBody = createBody(world, x, y-1.5f, nonTorsoDamping);
		rightForeArmBody = createBody(world, x, y-1.5f, nonTorsoDamping);
		leftForeArmFixture = new BoxFixture3D(leftForeArmBody, r, g, b, 0.3f, 1.2f, 0.3f, -1.1f, nonTorsoDensity, friction, restitution, groupIndex, this);
		rightForeArmFixture = new BoxFixture3D(rightForeArmBody, r, g, b, 0.3f, 1.2f, 0.3f, +1.1f, nonTorsoDensity, friction, restitution, groupIndex, this);
		
		leftUpperLegBody = createBody(world, x, y-3.2f, nonTorsoDamping);
		rightUpperLegBody = createBody(world, x, y-3.2f, nonTorsoDamping);
		leftUpperLegFixture = new BoxFixture3D(leftUpperLegBody, r, g, b, 0.3f, 1.2f, 0.3f, -1.1f, nonTorsoDensity, friction, restitution, groupIndex, this);
		rightUpperLegFixture = new BoxFixture3D(rightUpperLegBody, r, g, b, 0.3f, 1.2f, 0.3f, +1.1f, nonTorsoDensity, friction, restitution, groupIndex, this);
		
		leftLowerLegBody = createBody(world, x, y-5.6f, nonTorsoDamping);
		rightLowerLegBody = createBody(world, x, y-5.6f, nonTorsoDamping);
		leftLowerLegFixture = new BoxFixture3D(leftLowerLegBody, r, g, b, 0.3f, 1.2f, 0.3f, -1.1f, nonTorsoDensity, friction, restitution, groupIndex, this);
		rightLowerLegFixture = new BoxFixture3D(rightLowerLegBody, r, g, b, 0.3f, 1.2f, 0.3f, +1.1f, nonTorsoDensity, friction, restitution, groupIndex, this);

		
		neckJoint = createRevoluteJoint(world, headBody, 0, -1.7f, torsoBody, 0, +2, 0.3f);

		leftShoulderJoint = createRevoluteJoint(world, torsoBody, 0, +2, leftArmBody, 0, 1.2f, 0f);
		rightShoulderJoint = createRevoluteJoint(world, torsoBody, 0, +2, rightArmBody, 0, 1.2f, 0f);

		leftElbowJoint = createRevoluteJoint(world, leftArmBody, 0, -1.2f, leftForeArmBody, 0, 1.2f, 2.4f);
		rightElbowJoint = createRevoluteJoint(world, rightArmBody, 0, -1.2f, rightForeArmBody, 0, 1.2f, 2.4f);


		leftHipJoint = createRevoluteJoint(world, torsoBody, 0, -2f, leftUpperLegBody, 0, 1.2f, 2.0f);
		rightHipJoint = createRevoluteJoint(world, torsoBody, 0, -2f, rightUpperLegBody, 0, 1.2f, 2.0f);

		leftKneeJoint = createRevoluteJoint(world, leftUpperLegBody, 0, -1.2f, leftLowerLegBody, 0, 1.2f, 2.0f);
		rightKneeJoint = createRevoluteJoint(world, rightUpperLegBody, 0, -1.2f, rightLowerLegBody, 0, 1.2f, 2.0f);
		
		
	}

	private Body createBody(World world, float x, float y, float angularDamping){
		
		BodyDef bd = new BodyDef();
		bd.type = BodyType.DynamicBody;
		bd.position.set(x, y);
		bd.linearDamping = 0.1f;
		bd.angularDamping = angularDamping;
		Body body = world.createBody(bd);
		body.setUserData(this);
		return body;
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
		
		
		headFixture.dispose();
		torsoFixture.dispose();

		leftArmFixture.dispose();
		rightArmFixture.dispose();
		leftForeArmFixture.dispose();
		rightForeArmFixture.dispose();
		
		leftUpperLegFixture.dispose();
		rightUpperLegFixture.dispose();
		leftLowerLegFixture.dispose();
		rightLowerLegFixture.dispose();
		
		
		

		world.destroyBody(headBody);
		world.destroyBody(torsoBody);

		world.destroyBody(leftArmBody);
		world.destroyBody(rightArmBody);
		world.destroyBody(leftForeArmBody);
		world.destroyBody(rightForeArmBody);

		world.destroyBody(leftUpperLegBody);
		world.destroyBody(rightUpperLegBody);
		world.destroyBody(leftLowerLegBody);
		world.destroyBody(rightLowerLegBody);
		
		
	}
	
}