package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public abstract class StaticObject {
	protected BodyDef bodyDef;
	protected FixtureDef fixtureDef;
	protected Body body;
	protected Vector2[] points;
	protected World world;
	
	public StaticObject(World world, Vector2 ... points) {
		this.world = world;
		this.points = points;
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		fixtureDef = new FixtureDef();
	}
}
