package com.clone.fixture3d;

import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class BoxFixture3D extends Fixture3D{
	
	private static final Model model = new ModelBuilder().createBox(2, 2, 2, new Material(ColorAttribute.createDiffuse(1, 1, 1, 0)), Usage.Position | Usage.Normal);
	
	private float width, height, depth;
	private float z;
	
	public BoxFixture3D(Body body, float width, float height, float depth, float z, float density, float friction, float restitution, short groupIndex) {
		super(createFixture(body, width, height, density, friction, restitution, groupIndex), new ModelInstance(model));
		
		this.width = width;
		this.height = height;
		this.depth = depth;
		
		this.z = z;
	}
	
	private static Fixture createFixture(Body body, float width, float height, float density, float friction, float restitution, short groupIndex){
		
		PolygonShape s = new PolygonShape();
		s.setAsBox(width, height);
		
		FixtureDef fd = new FixtureDef();
		fd.shape = s;
		fd.density = density;
		fd.friction = friction;
		fd.restitution = restitution;
		fd.friction = 0.0f;
		
		fd.filter.groupIndex = groupIndex;
		
		return body.createFixture(fd);
	}

	@Override
	public void render(ModelBatch modelBatch, Environment environment) {
		
		instance.transform.set(new Vector3(body.getPosition(), z), new Quaternion().setEulerAnglesRad(0, 0, body.getAngle()), new Vector3(width, height, depth));
		modelBatch.render(instance, environment);
	}

}
