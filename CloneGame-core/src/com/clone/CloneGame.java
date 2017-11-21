package com.clone;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class CloneGame extends ApplicationAdapter {

	private World world;
	private Box2DDebugRenderer debugRenderer;
	private ContactListener contactListener;
	private Object currentLevel;

	private Ragdoll r;
	private Player player;

	private int kindOfClone;

	@Override
	public void create() {
		world = new World(new Vector2(0, 0), true);
		world.setGravity(new Vector2(0, -9.81f * 2));

		world.setContactListener(contactListener = new ContactListener());
		debugRenderer = new Box2DDebugRenderer();

		kindOfClone = 0;

		currentLevel = new Level1(world);
		//new Level3(world);
	}

	private long previousTime = System.nanoTime();

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (player == null || !player.getActive()) {
			// r = new Ragdoll(world, 55, 210, (float)Math.random());
			r = new Ragdoll(world, 55, 180, 0.1f);

			switch (kindOfClone) {
			case 0:
				player = new Player(r);
				break;
			case 1:
				player = new BouncingPlayer(r, world);
				break;
			}
		}

		// float force = 0;
		move(player);

		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			player.dispose();
			r.body.applyAngularImpulse((float) (Math.random() * 2 - 1) * 50, true);
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
			kindOfClone = 0;
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
			kindOfClone = 1;
		}

		// if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
		// force = 20;
		// player.jump(force);
		// if(r.body != null) r.body.applyForceToCenter(-force, 0, true);
		// if(r.head != null) r.head.applyForceToCenter(+force, 0, true);
		// }
		float cameraScale = 15f;
		Matrix4 matrix = new Matrix4();
		matrix.setToOrtho2D(45, 120, 16 * cameraScale, 9 * cameraScale, -1, +1);

		// long time = System.nanoTime();
		// float delta = (time - previousTime) / 1000000000f;
		// previousTime = time;
		float delta = 1 / 60f;

		player.setJump(false);
		int iterations = 1;
		for (int i = 0; i < iterations; i++) {
			world.step(delta / iterations, 10, 5);
			if (contactListener.deadPlayer != null) {
				contactListener.deadPlayer.dispose();
				contactListener.deadPlayer = null;
			}
		}

		if (contactListener.endLevel) {
			Array<Body> bodies = new Array<Body>();
			world.getBodies(bodies);
			for (Body body : bodies) {
				world.destroyBody(body);
			}
			if (currentLevel instanceof Level1) {
				currentLevel = new Level2(world);
			} else if(currentLevel instanceof Level2) {
				currentLevel = new Level3(world);
			}else if(currentLevel instanceof Level3) {
				currentLevel = new Level1(world);
			}
			player = null;
			contactListener.endLevel = false;
		}

		// Gdx.graphics.setTitle("Can jump: " + player.isCanJump());

		debugRenderer.render(world, matrix);
	}

	@Override
	public void dispose() {
	}

	private void move(Player player) {
		int moving = 0;
		if (player.getActive()) {
			if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
				player.jump(20);
			}
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
				player.applyForce2(+10f, 0);
				moving = 1;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
				player.applyForce2(-10f, 0);
				moving = -1;
			}
		}

		Vector2 v = r.body.getLinearVelocity();
		float maxSpeed = 20;
		if (v.x < -maxSpeed) {
			v.x = -maxSpeed;
		}
		if (v.x > +maxSpeed) {
			v.x = +maxSpeed;
		}

		if (moving != 0 || (moving < 0 && v.x > 0) || (moving > 0 && v.x < 0)) {
			v.x *= 0.9f;
		}
		r.body.setLinearVelocity(v);

	}
}

/*
 * package com.clone;
 * 
 * import com.badlogic.gdx.ApplicationAdapter; import com.badlogic.gdx.Gdx;
 * import com.badlogic.gdx.Input; import com.badlogic.gdx.graphics.GL20; import
 * com.badlogic.gdx.graphics.Texture; import
 * com.badlogic.gdx.graphics.g2d.SpriteBatch; import
 * com.badlogic.gdx.math.Matrix4; import com.badlogic.gdx.math.Vector2; import
 * com.badlogic.gdx.physics.box2d.Body; import
 * com.badlogic.gdx.physics.box2d.Box2DDebugRenderer; import
 * com.badlogic.gdx.physics.box2d.World; import com.badlogic.gdx.utils.Array;
 * 
 * public class CloneGame extends ApplicationAdapter {
 * 
 * private World world; private Box2DDebugRenderer debugRenderer; private
 * ContactListener contactListener;
 * 
 * private Ragdoll r; private Player player;
 * 
 * @Override public void create() { world = new World(new Vector2(0, 0), true);
 * world.setGravity(new Vector2(0, -9.81f*2));
 * 
 * world.setContactListener(contactListener = new ContactListener());
 * debugRenderer = new Box2DDebugRenderer();
 * 
 * new Level1(world); // new Level2(world); }
 * 
 * private long previousTime = System.nanoTime();
 * 
 * @Override public void render() { Gdx.gl.glClearColor(0, 0, 0, 1);
 * Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
 * 
 * if(player == null || !player.getActive()) { r = new Ragdoll(world, 55, 210,
 * (float)Math.random()); player = new Player(r); }
 * 
 * // float force = 0; move(player);
 * 
 * if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){ player.dispose();
 * r.body.applyAngularImpulse((float)(Math.random()*2-1) * 50, true); }
 * 
 * // if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){ // force = 20; //
 * player.jump(force); // if(r.body != null) r.body.applyForceToCenter(-force,
 * 0, true); // if(r.head != null) r.head.applyForceToCenter(+force, 0, true);
 * // } float cameraScale = 15f; Matrix4 matrix = new Matrix4();
 * matrix.setToOrtho2D(45, 120, 16*cameraScale, 9*cameraScale, -1, +1);
 * 
 * //long time = System.nanoTime(); //float delta = (time - previousTime) /
 * 1000000000f; //previousTime = time; float delta = 1/60f;
 * 
 * player.setJump(false); int iterations = 1; for(int i = 0; i < iterations;
 * i++) { world.step(delta / iterations, 10, 5); if(contactListener.deadPlayer
 * != null){ contactListener.deadPlayer.dispose(); contactListener.deadPlayer =
 * null; } }
 * 
 * if(contactListener.endLevel) { Array<Body> bodies = new Array<Body>();
 * world.getBodies(bodies); for(Body body : bodies) { world.destroyBody(body); }
 * new Level2(world); player = null; contactListener.endLevel = false; }
 * 
 * 
 * 
 * //Gdx.graphics.setTitle("Can jump: " + player.isCanJump());
 * 
 * debugRenderer.render(world, matrix); }
 * 
 * @Override public void dispose() {
 * 
 * }
 * 
 * private void move(Player player){ int moving = 0; if (player.getActive()) {
 * if (Gdx.input.isKeyPressed(Input.Keys.UP) ||
 * Gdx.input.isKeyPressed(Input.Keys.W)) { player.jump(20); } if
 * (Gdx.input.isKeyPressed(Input.Keys.RIGHT) ||
 * Gdx.input.isKeyPressed(Input.Keys.D)) { player.applyForce2(+10f, 0); moving =
 * 1; } if (Gdx.input.isKeyPressed(Input.Keys.LEFT) ||
 * Gdx.input.isKeyPressed(Input.Keys.A)) { player.applyForce2(-10f, 0); moving =
 * -1; } }
 * 
 * Vector2 v = r.body.getLinearVelocity(); float maxSpeed = 20; if(v.x <
 * -maxSpeed) { v.x = -maxSpeed; } if(v.x > +maxSpeed) { v.x = +maxSpeed; }
 * 
 * if(moving != 0 || (moving < 0 && v.x > 0) || (moving > 0 && v.x < 0)){ v.x *=
 * 0.9f; } r.body.setLinearVelocity(v);
 * 
 * 
 * } }
 */