package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.clone.fixture3d.ConeFixture3D;

public class SpikeL extends Obstacle{
	public SpikeL(World world, Vector2... points) {
		super(world, points);
		bodyDef.position.set(points[0]).scl(2);
		/*//Shape
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
		fixtureDef.restitution = 0.01f;*/
		
		body = world.createBody(bodyDef);
		//body.createFixture(fixtureDef).setUserData(this);
				
		//shape.dispose();
		
		
		/*TODO: dispose? and what is the group index?
		 * Additionally, when removing the code above, the spikes are not drawn in the right places in level 1,
		 * Cone spikes are only drawn on the bottom.
		 * 
		 */
		
		new ConeFixture3D(body, 20f*GroundSquare.scale, 30f*GroundSquare.scale, 20*GroundSquare.scale, 0, 2.5f, 0.5f, 0.01f, (short)0, this);
	}

}
