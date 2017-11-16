package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class SpikeL extends Obstacle{
	public SpikeL(World world, Vector2... points) {
		super(world, points);
		bodyDef.position.set(points[0]);
		//Shape
		Vector2[] vertices = new Vector2[3];
		vertices[0] = new Vector2(points[0].x + 30*GroundSquare.scale, points[0].y + 60 * GroundSquare.scale);
		vertices[1] = new Vector2(points[0].x, points[0].y);
		vertices[2] = new Vector2(points[0].x + 60*GroundSquare.scale, points[0].y);
		PolygonShape shape = new PolygonShape();
		shape.set(vertices);
		
		//fixture
		fixtureDef.shape = shape;
		fixtureDef.density = 2.5f;
		fixtureDef.friction = 0.5f;
		fixtureDef.restitution = 0.01f;
			
		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef).setUserData(this);
				
		shape.dispose();
	}

}
