package com.clone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ContactListener implements com.badlogic.gdx.physics.box2d.ContactListener {

	public Player deadPlayer;
	public boolean endLevel;

	public ContactListener() {
		deadPlayer = null;
		endLevel = false;
	}

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
		checkEnd(objectA, objectB);
		wreckingBallSwitchDirection(objectA, objectB);
		checkBreak(objectA, objectB);
	}

	@Override
	public void endContact(Contact contact) {
		/*
		 * Fixture fixtureA = contact.getFixtureA(); Fixture fixtureB =
		 * contact.getFixtureB(); if (fixtureA == null || fixtureB == null) return; if
		 * (fixtureA.getUserData() == null || fixtureB.getUserData() == null) return;
		 * Object objectA = contact.getFixtureA().getUserData(); Object objectB =
		 * contact.getFixtureB().getUserData(); checkBouncingPlayer(objectA, objectB);
		 * checkCanNotJump(objectA, objectB);
		 */
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		Object objectA = contact.getFixtureA().getUserData();
		Object objectB = contact.getFixtureB().getUserData();
		if (objectA instanceof Player) {
			((Player) objectA).setJump(true);
		}
		if (objectB instanceof Player) {
			((Player) objectB).setJump(true);
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold manifold) {
		Object objectA = contact.getFixtureA().getUserData();
		Object objectB = contact.getFixtureB().getUserData();
		wreckingBallSwitchDirection(objectA, objectB);
	}

	private void checkPlayerHit(final Object objectA, final Object objectB) {
		if (objectA instanceof Player || objectB instanceof Player) {
			if (objectA instanceof Obstacle || objectA instanceof WreckingBall) {
				if (((Player) objectB).getActive()) {
					deadPlayer = ((Player) objectB);
				}
			} else if (objectB instanceof Obstacle || objectB instanceof WreckingBall) {
				if (((Player) objectA).getActive()) {
					deadPlayer = ((Player) objectA);
				}
			}
		}
	}

	private void checkBouncingPlayer(Object objectA, Object objectB) {
		// if (objectA instanceof BouncingPlayer || objectB instanceof BouncingPlayer) {
		// if (objectA instanceof Player) {
		// ((Player) objectA).jumpHigher(); // A method that makes the player jump a lot
		// higher instead of just
		// // jumping a little bit
		// } else if (objectB instanceof Player) {
		// ((Player) objectB).jumpHigher();
		// }
		// }
	}

	private void wreckingBallSwitchDirection(Object objectA, Object objectB) {
		if (isWreckingBall(objectA, objectB)) {
			System.out.println("jgo");
			if (objectA instanceof GroundSquare) {
				System.out.println("hg");
				((WreckingBall) objectB).body
						.setLinearVelocity(new Vector2(0, -((WreckingBall) objectB).body.getLinearVelocity().y));
			} else if (objectB instanceof GroundSquare) {
				System.out.println("ooi");
				((WreckingBall) objectA).body
						.setLinearVelocity(new Vector2(0, -((WreckingBall) objectA).body.getLinearVelocity().y));
			}
		}
	}

	private void checkCanNotJump(Object objectA, Object objectB) {
		if (isPlayer(objectA, objectB)) {
			if (objectA instanceof GroundSquare) {
				((Player) objectB).setJump(false);
			} else if (objectB instanceof GroundSquare) {
				((Player) objectA).setJump(false);
			}
		}
	}

	private void checkCanJump(Object objectA, Object objectB) {
		if (isPlayer(objectA, objectB)) {
			if (objectA instanceof GroundSquare) {
				((Player) objectB).setJump(true);
			} else if (objectB instanceof GroundSquare) {
				((Player) objectA).setJump(true);
			}
		}
	}

	private void checkEnd(Object objectA, Object objectB) {
		if (isPlayer(objectA, objectB)) {
			if (isEnd(objectA, objectB)) {
				endLevel = true;
			}
		}
	}

	private void checkBreak(final Object objectA, final Object objectB) {
		if (isPlayer(objectA, objectB)) {
			if (objectA instanceof Breakable) {
				Gdx.app.postRunnable(new Runnable() {

					@Override
					public void run() {
						((Breakable) objectA).dispose();

					}
				});
				// ((Breakable) objectA).destroy();
			} else if (objectB instanceof Breakable) {
				Gdx.app.postRunnable(new Runnable() {

					@Override
					public void run() {
						((Breakable) objectB).dispose();

					}
				});
				//((Breakable) objectA).destroy();
			}
		}
	}

	private boolean isEnd(Object objectA, Object objectB) {
		return objectA instanceof End || objectB instanceof End;
	}

	private boolean isPlayer(Object objectA, Object objectB) {
		return objectA instanceof Player || objectB instanceof Player;
	}

	private boolean isWreckingBall(Object objectA, Object objectB) {
		return objectA instanceof WreckingBall || objectB instanceof WreckingBall;
	}

}
