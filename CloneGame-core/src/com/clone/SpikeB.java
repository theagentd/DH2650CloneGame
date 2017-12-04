package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.clone.fixture3d.ConeFixture3D;

public class SpikeB extends Obstacle{
	public SpikeB(World world, Vector2... points) {
		super(world, points);
		bodyDef.position.set(points[0]);
		//Shape
		Vector2[] vertices = new Vector2[3];
		vertices[0] = new Vector2(points[0].x + 90*GroundSquare.scale, points[0].y + 520 * GroundSquare.scale);
		vertices[1] = new Vector2(points[0].x, points[0].y);
		vertices[2] = new Vector2(points[0].x + 180*GroundSquare.scale, points[0].y);
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
		
		/*TODO: dispose? and what is the group index supposed to be?
		 * This one does not seem to work at all? not drawn at all?
		 */
		
		new ConeFixture3D(body, 180f*GroundSquare.scale, 520f*GroundSquare.scale, 90*GroundSquare.scale, 0, 2.5f, 0.5f, 0.01f, (short)0, this);
	}

}
