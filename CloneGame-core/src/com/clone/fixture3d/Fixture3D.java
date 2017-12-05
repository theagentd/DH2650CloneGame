package com.clone.fixture3d;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public abstract class Fixture3D {
	
	private static ArrayList<Fixture3D> fixtures = new ArrayList<Fixture3D>();
	
	
	protected Body body;
	protected Fixture fixture;
	
	protected ModelInstance instance;
	
	public Fixture3D(Fixture fixture, ModelInstance instance) {
		
		this.body = fixture.getBody();
		this.fixture = fixture;
		
		this.instance = instance;
		
		fixtures.add(this);
	}
	
	public static void renderAllFixtures(ModelBatch modelBatch, Environment environment) {
		for(int i = 0; i < fixtures.size(); i++){
			fixtures.get(i).render(modelBatch, environment);
		}
	}
	
	public static void destroyAllFixtures(){
		for(int i = 0; i < fixtures.size(); i++){
			fixtures.get(i).dispose();
		}
		fixtures.clear();
	}
	
	public Fixture getFixture() {
		return fixture;
	}
	
	public abstract void render(ModelBatch modelBatch, Environment environment);
	
	public void dispose(){
		body.destroyFixture(fixture);
		fixtures.remove(this);
	}
}
