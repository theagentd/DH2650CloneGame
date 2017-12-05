package com.clone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.JointEdge;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class ExplodingPlayer extends Player{
	
	//private Fixture circleHitbox;
	private World world;
	
	public ExplodingPlayer(Ragdoll ragdoll, World world) {
		super(ragdoll);
		
		this.world = world;
		
	}

	@Override
	public void dispose() {
		super.dispose();
		
		ragdoll.splash(world);
		
		Array<Fixture> fixtures = new Array<Fixture>();
		world.getFixtures(fixtures);
		
		for(final Fixture fix: fixtures){
			
	        if (fix.getUserData() instanceof Breakable) {
	        		Vector2 position = ragdoll.torsoBody.getPosition();
	        		Body newpos = (Body)  fix.getBody();
	        		if (position.dst(newpos.getPosition()) < 25){
	        			((Breakable) fix.getUserData()).dispose();
	        		}
	        	
					

				
				
	        }
	        else  {
	        	Vector2 position = ragdoll.torsoBody.getPosition();
        		Body newpos = (Body)  fix.getBody();
        		float distance = position.dst(newpos.getPosition());
        		if (distance < 25){
        			//System.out.println(distance);
        			Vector2 direction = new Vector2(1000*(newpos.getPosition().x - position.x)/(distance+1), 1000*(newpos.getPosition().y - position.y)/(distance+1));
        			newpos.applyLinearImpulse(direction, newpos.getPosition(), true);
        			
        			
        			
        			//if (fix.getUserData() instanceof Body)
        			//	System.out.println("found you");
        			//Array<JointEdge> joints = newpos.getJointList();
        			
        		}
	        }
	    }
		
	}
	
}

