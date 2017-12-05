package com.clone;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.clone.fixture3d.Fixture3D;

public abstract class StaticObject {
	
	private World world;
	
	protected Body body;
	
	private ArrayList<Fixture3D> fixtures;
	
	
	public StaticObject(World world, Vector2 position) {
		
		this.world = world;
		
		BodyDef bd = new BodyDef();
		bd.type = BodyType.StaticBody;
		bd.position.set(position);
		
		this.body = world.createBody(bd);
		
		fixtures = new ArrayList<Fixture3D>();
	}
	
	protected void addFixture(Fixture3D fixture){
		fixtures.add(fixture);
	}
	
	public void dispose(){
		
		for(Fixture3D f : fixtures){
			f.dispose();
		}
		
		world.destroyBody(body);
		
	}
}
