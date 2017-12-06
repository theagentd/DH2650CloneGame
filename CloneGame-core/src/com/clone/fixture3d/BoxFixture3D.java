package com.clone.fixture3d;

import java.util.HashMap;

import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class BoxFixture3D extends Fixture3D{
	
	private static final ModelBuilder modelBuilder = new ModelBuilder();
	private static final HashMap<Vector3, Model> models = new HashMap<Vector3, Model>();
	
	private float width, height, depth;
	private float z;
	
	private Matrix4 matrix;

	public BoxFixture3D(Body body, float r, float g, float b, float width, float height, float depth, float z, float density, float friction, float restitution, short groupIndex, Object userData) {
		this(body, r, g, b, width, height, depth, z, density, friction, restitution, groupIndex, userData, null);
	}
	
	public BoxFixture3D(Body body, float r, float g, float b, float width, float height, float depth, float z, float density, float friction, float restitution, short groupIndex, Object userData, Matrix4 matrix) {
		super(createFixture(body, width, height, density, friction, restitution, groupIndex, userData, matrix), new ModelInstance(getModel(r, g, b)));
		
		this.width = width;
		this.height = height;
		this.depth = depth;
		
		this.z = z;
		
		this.matrix = matrix;
	}
	
	private static Model getModel(float r, float g, float b) {
		Vector3 color = new Vector3(r, g, b);
		Model result = models.get(color);
		if(result == null){
			result = modelBuilder.createBox(2, 2, 2, new Material(ColorAttribute.createDiffuse(r, g, b, 0)), Usage.Position | Usage.Normal);
			models.put(new Vector3(r, g, b), result);
		}
		return result;
	}
	
	private static Fixture createFixture(Body body, float width, float height, float density, float friction, float restitution, short groupIndex, Object userData, Matrix4 matrix){
		
		PolygonShape shape = new PolygonShape();
		if(matrix == null){
			shape.setAsBox(width, height);
		}else{
			
			Vector2[] vertices = new Vector2[4];
			Vector3 temp = new Vector3();
			
			temp.set(-width, -height, 0).mul(matrix);
			vertices[0] = new Vector2(temp.x, temp.y);
			
			temp.set(-width, +height, 0).mul(matrix);
			vertices[1] = new Vector2(temp.x, temp.y);
			
			temp.set(+width, +height, 0).mul(matrix);
			vertices[2] = new Vector2(temp.x, temp.y);
			
			temp.set(+width, -height, 0).mul(matrix);
			vertices[3] = new Vector2(temp.x, temp.y);
			
			shape.set(vertices);
		}
		
		FixtureDef fd = new FixtureDef();
		fd.shape = shape;
		fd.density = density;
		fd.friction = friction;
		fd.restitution = restitution;
		fd.filter.groupIndex = groupIndex;
		
		Fixture fixture = body.createFixture(fd);
		
		fixture.setUserData(userData);
		
		shape.dispose();
		
		return fixture;
	}

	@Override
	public void render(ModelBatch modelBatch, Environment environment) {
		
		instance.transform.set(new Vector3(body.getPosition(), z), new Quaternion().setEulerAnglesRad(0, 0, body.getAngle()), new Vector3(width, height, depth));
		if(matrix != null){
			instance.transform.mul(matrix);
		}
		modelBatch.render(instance, environment);
	}

}
