package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.clone.fixture3d.BoxFixture3D;

public class BreackableWallRaw extends Breakable {
	Fixture fix;
	public BreackableWallRaw(World world, Vector2... points) {
		super(world, points);
		bodyDef.position.set(points[0]);
		/*//shape
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(90f * GroundSquare.scale, 30f * GroundSquare.scale);
		
		//fixture
		fixtureDef.shape = shape;
		fixtureDef.density = 2.5f;
		fixtureDef.friction = 0.1f;
		fixtureDef.restitution = 0.01f;
		*/
		body = world.createBody(bodyDef);
		//fix = body.createFixture(fixtureDef);
		//fix.setUserData(this);
		//shape.dispose();
		
		//TODO: need a dispose!
		
		new BoxFixture3D(body, 90f * GroundSquare.scale, 30f * GroundSquare.scale, 20 * GroundSquare.scale, 0, 2.5f, 0.1f, 0.01f, (short)0, this);
	}
}
