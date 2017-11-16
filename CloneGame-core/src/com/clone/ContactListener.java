package com.clone;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ContactListener implements com.badlogic.gdx.physics.box2d.ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		if (fixtureA == null || fixtureB == null)
			return;
		if (fixtureA.getUserData() == null || fixtureB.getUserData() == null)
			return;

		Object objectA = contact.getFixtureA().getUserData();
		Object objectB = contact.getFixtureB().getUserData();
		checkPlayerHit(objectA, objectB);
		checkCanJump(objectA, objectB);
	}


	@Override
	public void endContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		if (fixtureA == null || fixtureB == null)
			return;
		if (fixtureA.getUserData() == null || fixtureB.getUserData() == null)
			return;

		Object objectA = contact.getFixtureA().getUserData();
		Object objectB = contact.getFixtureB().getUserData();
		checkBouncingPlayer(objectA, objectB);
		checkCanNotJump(objectA, objectB);
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}

	@Override
	public void preSolve(Contact contact, Manifold manifold) {

	}

	private void checkPlayerHit(Object objectA, Object objectB) {
		// if(objectA instanceof Player || objectB instanceof Player) {
		// if(objectA instanceof Obstacle) {
		// ((Player) objectB).dispose();
		// } else if(objectB instanceof Obstacle) {
		// ((Player) objectA).dispose();
		// }
		// }
	}
	
	private void checkBouncingPlayer(Object objectA, Object objectB) {
//		if (objectA instanceof BouncingPlayer || objectB instanceof BouncingPlayer) {
//			if (objectA instanceof Player) {
//				((Player) objectA).jumpHigher(); // A method that makes the player jump a lot higher instead of just
//													// jumping a little bit
//			} else if (objectB instanceof Player) {
//				((Player) objectB).jumpHigher();
//			}
//		}
	}
	
	private void checkCanNotJump(Object objectA, Object objectB) {
//		if(objectA instanceof Player || objectB instanceof Player) {
//			if(objectA instanceof GroudSquare) {
//				((Player) objectB).setJump(false);
//			} else if(objectB instanceof GroudSquare){
//				((Player) objectA).setJump(false);
//			}
//		}
	}
	
	private void checkCanJump(Object objectA, Object objectB) {
//		if(objectA instanceof Player || objectB instanceof Player) {
//			if(objectA instanceof GroudSquare) {
//				((Player) objectB).setJump(true);
//			} else if(objectB instanceof GroudSquare){
//				((Player) objectA).setJump(true);
//			}
//		}
	}

}
