package com.clone;

import static com.clone.LevelsElement.*;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.clone.fixture3d.BoxFixture3D;

public class Saw {
	
	public World world;
	public Body body;
	public Vector2 spawn;
	private BoxFixture3D blade1, blade2;

	public Saw(World world, Vector2... points){
		this.world = world;
		spawn = points[0];
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set(points[0]);
		body = world.createBody(bodyDef);
		body.setAngularVelocity(10f);

		blade1 = new BoxFixture3D(body, 1, 0.2f, 0.2f, 100*SCALE, 100*SCALE, 0.01f, 0, 0.1f, 0.4f, 0.3f, (short)0, this);
		blade2 = new BoxFixture3D(body, 1, 0.2f, 0.2f, 100*SCALE, 100*SCALE, 0.01f, 0, 0.1f, 0.4f, 0.3f, (short)0, this, new Matrix4().rotate(0, 0, 1, 45));
	}
	
	public void destroy() {
		
		if(blade1 != null){
			blade1.dispose();
			blade2.dispose();
			world.destroyBody(body);
		}
		
		blade1 = null;
		blade2 = null;
		body = null;
	}
}
