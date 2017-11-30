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
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class SphereFixture3D extends Fixture3D{
	
	private static final Model model = new ModelBuilder().createSphere(2, 2, 2, 20, 20, new Material(ColorAttribute.createDiffuse(1, 1, 1, 0)), Usage.Position | Usage.Normal);
	
	private float radius;
	private float z;
	
	public SphereFixture3D(Body body, float radius, float z, float density, float friction, float restitution, short groupIndex, Object userData) {
		super(createFixture(body, radius, density, friction, restitution, groupIndex, userData), new ModelInstance(model));
		
		this.radius = radius;
		
		this.z = z;
	}
	
	private static Fixture createFixture(Body body, float radius, float density, float friction, float restitution, short groupIndex, Object userData){
		
		CircleShape shape = new CircleShape();
		shape.setRadius(radius);
		
		FixtureDef fd = new FixtureDef();
		fd.shape = shape;
		fd.density = density;
		fd.friction = friction;
		fd.restitution = restitution;
		fd.friction = 0.0f;
		fd.filter.groupIndex = groupIndex;
		
		Fixture fixture = body.createFixture(fd);
		
		fixture.setUserData(userData);
		
		shape.dispose();
		
		return fixture;
	}

	@Override
	public void render(ModelBatch modelBatch, Environment environment) {
		
		instance.transform.set(new Vector3(body.getPosition(), z), new Quaternion().setEulerAnglesRad(0, 0, body.getAngle()), new Vector3(radius, radius, radius));
		modelBatch.render(instance, environment);
	}

}
