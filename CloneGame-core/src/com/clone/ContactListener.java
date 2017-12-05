package com.clone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public class ContactListener implements com.badlogic.gdx.physics.box2d.ContactListener {

	public Player deadPlayer;
	public boolean splash;
	public boolean endLevel;

	public ContactListener() {
		deadPlayer = null;
		endLevel = false;
		splash = false;
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
		// wreckingBallSwitchDirection(objectA, objectB);
		//checkBreak(objectA, objectB);
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
		wreckingBall(objectA, objectB);
		saw(objectA, objectB);
	}

	private void checkPlayerHit(final Object objectA, final Object objectB) {
		if (objectA instanceof Player || objectB instanceof Player) {
			if (objectA instanceof Obstacle
					|| (objectA instanceof WreckingBall
							&& ((WreckingBall) objectA).body.getType() != BodyType.StaticBody)
					|| (objectA instanceof Saw && ((Saw) objectA).body.getType() != BodyType.StaticBody)) {
				if (((Player) objectB).getActive()) {
					deadPlayer = ((Player) objectB);
				}
				if (objectA instanceof Saw && ((Saw) objectA).body.getType() != BodyType.StaticBody) {
					splash = true;
				}
			} else if (objectB instanceof Obstacle
					|| (objectB instanceof WreckingBall
							&& ((WreckingBall) objectB).body.getType() != BodyType.StaticBody)
					|| (objectB instanceof Saw && ((Saw) objectB).body.getType() != BodyType.StaticBody)) {
				if (((Player) objectA).getActive()) {
					deadPlayer = ((Player) objectA);
				}
				if (objectB instanceof Saw && ((Saw) objectB).body.getType() != BodyType.StaticBody) {
					splash = true;
				}
			}
		}
	}

	private void checkBouncingPlayer(Object objectA, Object objectB) {
		// if (objectA instanceof BouncingPlayer || objectB instanceof
		// BouncingPlayer) {
		// if (objectA instanceof Player) {
		// ((Player) objectA).jumpHigher(); // A method that makes the player
		// jump a lot
		// higher instead of just
		// // jumping a little bit
		// } else if (objectB instanceof Player) {
		// ((Player) objectB).jumpHigher();
		// }
		// }
	}

	private void wreckingBall(final Object objectA, final Object objectB) {
		if (objectA instanceof WreckingBall) {
			if (objectB instanceof FreezingPlayer) {
				Gdx.app.postRunnable(new Runnable() {

					@Override
					public void run() {
						((WreckingBall) objectA).body.setType(BodyType.StaticBody);
					}
				});
			} else if (objectB instanceof Player || objectB instanceof GroundSquare || objectB instanceof SpikeL
					|| objectB instanceof BouncingBlock || objectB instanceof Ragdoll) {
				if (((WreckingBall) objectA).body.getType() != BodyType.StaticBody) {
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							Vector2 spawn = ((WreckingBall) objectA).spawn;
							World world = ((WreckingBall) objectA).world;
							if (objectB instanceof BouncingBlock) {
								((BouncingBlock) objectB).dispose();
							}
							((WreckingBall) objectA).destroy();
							new WreckingBall(world, spawn);

						}
					});
				}
			}
		} else if (objectB instanceof WreckingBall) {
			if (objectA instanceof FreezingPlayer) {
				Gdx.app.postRunnable(new Runnable() {

					@Override
					public void run() {
						((WreckingBall) objectB).body.setType(BodyType.StaticBody);
					}
				});
			} else if (objectA instanceof Player || objectA instanceof GroundSquare || objectA instanceof BouncingBlock
					|| objectA instanceof Ragdoll) {
				if (((WreckingBall) objectB).body.getType() != BodyType.StaticBody) {
					Gdx.app.postRunnable(new Runnable() {

						@Override
						public void run() {
							Vector2 spawn = ((WreckingBall) objectB).spawn;
							World world = ((WreckingBall) objectB).world;
							if (objectA instanceof BouncingBlock) {
								((BouncingBlock) objectA).dispose();
							}
							((WreckingBall) objectB).destroy();
							new WreckingBall(world, spawn);
						}
					});
				}
			}
		}
	}

	private void saw(final Object objectA, final Object objectB) {
		if (objectA instanceof Saw) {
			if (objectB instanceof FreezingPlayer) {
				Gdx.app.postRunnable(new Runnable() {

					@Override
					public void run() {
						((Saw) objectA).body.setType(BodyType.StaticBody);
					}
				});
			}
		} else if (objectB instanceof Saw) {
			if (objectA instanceof FreezingPlayer) {
				Gdx.app.postRunnable(new Runnable() {

					@Override
					public void run() {
						((Saw) objectB).body.setType(BodyType.StaticBody);
					}
				});
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

	/*
	private void checkBreak(final Object objectA, final Object objectB) {
		if (isPlayer(objectA, objectB)) {
			if (objectA instanceof Breakable) {
				Gdx.app.postRunnable(new Runnable() {

					@Override
					public void run() {
						((Breakable) objectA).dispose();
					}
				});
			} else if (objectB instanceof Breakable) {
				Gdx.app.postRunnable(new Runnable() {

					@Override
					public void run() {
						((Breakable) objectB).dispose();
					}
				});
			}
		}
	}
	*/

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