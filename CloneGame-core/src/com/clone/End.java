package com.clone;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.EdgeShape;

public class End {
	
	protected BodyDef bodyDef;
	protected Body body;
	protected Vector2[] points;
	protected World world;
	
	public End(World world, Vector2 ... points) {
		this.world = world;
		this.points = points;
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		
		bodyDef.position.set(points[0]);
		FixtureDef fixtureSquare = new FixtureDef();
		//Shape Flag (rectangle)
		PolygonShape rectangle = new PolygonShape();
		rectangle.setAsBox(6f, 4f);
		fixtureSquare.shape = rectangle;
		fixtureSquare.density = 0.1f;
		fixtureSquare.friction = 0.1f;
		fixtureSquare.restitution = 0f;
		
		body = world.createBody(bodyDef);
		body.createFixture(fixtureSquare).setUserData(this);
		// shape Flag (line)
		EdgeShape line = new  EdgeShape();
		line.set(-6, 0, -6, -20);
		
		FixtureDef fixtureLine = new FixtureDef();
		fixtureLine.shape = line;
		fixtureSquare.density = 0.1f;
		fixtureSquare.friction = 0.1f;
		fixtureSquare.restitution = 0f;
		
		body.createFixture(fixtureLine).setUserData(this);
		
	}
	
	public void dispose() {
	}
	
	
}
