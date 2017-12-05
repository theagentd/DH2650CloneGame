package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.clone.fixture3d.BoxFixture3D;
import com.clone.fixture3d.Fixture3D;
import com.badlogic.gdx.physics.box2d.EdgeShape;

public class End {

	protected World world;
	
	protected Body flagBody;
	protected Fixture3D flagFixture;
	
	protected Body poleBody;
	protected Fixture3D poleFixture;
	
	
	public End(World world, Vector2 position) {
		this.world = world;
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		
		bodyDef.position.set(position);
		flagBody = world.createBody(bodyDef);
		
		bodyDef.position.set(position).add(-6, -8);
		poleBody = world.createBody(bodyDef);
		
		flagFixture = new BoxFixture3D(flagBody, 1, 0.1f, 0.1f, 6, 4, 0.1f, 0, 1, 0.1f, 0.1f, (short)0, this);
		
		poleFixture = new BoxFixture3D(poleBody, 1, 1, 1, 0.15f, 12, 0.15f, 0, 1, 0.1f, 0.1f, (short)0, this);
		
		
		
		/*FixtureDef fixtureSquare = new FixtureDef();
		//Shape Flag (rectangle)
		PolygonShape rectangle = new PolygonShape();
		rectangle.setAsBox(6f, 4f);
		fixtureSquare.shape = rectangle;
		fixtureSquare.density = 0.1f;
		fixtureSquare.friction = 0.1f;
		fixtureSquare.restitution = 0f;
		
		flagBody.createFixture(fixtureSquare).setUserData(this);
		// shape Flag (line)
		EdgeShape line = new  EdgeShape();
		line.set(-6, 0, -6, -20);
		
		FixtureDef fixtureLine = new FixtureDef();
		fixtureLine.shape = line;
		fixtureSquare.density = 0.1f;
		fixtureSquare.friction = 0.1f;
		fixtureSquare.restitution = 0f;
		
		flagBody.createFixture(fixtureLine).setUserData(this);*/
		
	}
}
