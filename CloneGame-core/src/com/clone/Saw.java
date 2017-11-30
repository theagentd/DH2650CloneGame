package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Saw {
	public Body body;
	public World world;
	public Vector2 spawn;

	public Saw(World world, Vector2... points){
	this.world = world;
	spawn = points[0];
	BodyDef bodyDef = new BodyDef();
	bodyDef.type = BodyType.KinematicBody;
	bodyDef.position.set(points[0]);

	// Shape
	Vector2[] vertices = new Vector2[4];
	vertices[0] = new Vector2(100 * GroundSquare.scale, 0);
	vertices[1] = new Vector2(-100 * GroundSquare.scale, 0);
	vertices[2] = new Vector2(0, 100* GroundSquare.scale);
	vertices[3] = new Vector2(0, - 100 * GroundSquare.scale);
	PolygonShape shape = new PolygonShape();
	shape.set(vertices);
	
	// Create a fixture definition to apply our shape to
	FixtureDef fixtureDef = new FixtureDef();
	fixtureDef.shape = shape;
	fixtureDef.density = 0.1f;
	fixtureDef.friction = 0.4f;
	fixtureDef.restitution = 0.3f;

	Vector2[] vertices2 = new Vector2[4];
	vertices2[0] = new Vector2(75 * GroundSquare.scale, 75 * GroundSquare.scale);
	vertices2[1] = new Vector2(-75 * GroundSquare.scale, -75 * GroundSquare.scale);
	vertices2[2] = new Vector2(-75 * GroundSquare.scale, 75* GroundSquare.scale);
	vertices2[3] = new Vector2(75 * GroundSquare.scale, - 75 * GroundSquare.scale);
	
	PolygonShape shape2 = new PolygonShape();
	shape2.set(vertices2);
	
	FixtureDef f2 = new FixtureDef();
	f2.shape = shape2;
	f2.density = 0.1f;
	f2.friction = 0.4f;
	f2.restitution = 0.3f;
	
	body = world.createBody(bodyDef);
	body.createFixture(fixtureDef).setUserData(this);
	body.createFixture(f2).setUserData(this);
	body.setAngularVelocity(10f);
	shape.dispose();
	shape2.dispose();
}

public void destroy() {
		world.destroyBody(body);
	}
}
