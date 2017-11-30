package com.clone;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.google.gwt.user.client.ui.Widget;

public class CloneGame extends ApplicationAdapter {

	private World world;
	private Box2DDebugRenderer debugRenderer;
	private ContactListener contactListener;
	private Object currentLevel;

	private Stage stage;
	private Table table;

	private Ragdoll r;
	private Player player;

	private int kindOfClone;

	private final static float BUTTONHEIGHT = 50;
	private final static float BUTTONWIDTH = 300;

	private final static int NORMALCLONE = 0;
	private final static int BOUNCINGCLONE = 1;
	private final static int FREEZINGCLONE = 2;

	private boolean noChosenPlayer = false;

	// BUG DOUBLE SPACE

	@Override
	public void create() {
		world = new World(new Vector2(0, 0), true);
		world.setGravity(new Vector2(0, -9.81f * 2));

		world.setContactListener(contactListener = new ContactListener());
		debugRenderer = new Box2DDebugRenderer();

		kindOfClone = NORMALCLONE;

		currentLevel = new Level3(world);
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		table = createTable();
		stage.addActor(table);

	}

	private Table createTable() {
		table = new Table();
		table.setFillParent(true);
		table.setVisible(false);
		Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
		table.setSkin(skin);
		TextButton normal = new TextButton("Normal Clone", skin);
		TextButton bouncing = new TextButton("Bouncing Clone", skin);
		TextButton freezing = new TextButton("Freezing Clone", skin);
		normal.addCaptureListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				kindOfClone = NORMALCLONE;
				noChosenPlayer = false;
				table.setVisible(false);
			};
		});
		bouncing.addCaptureListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				kindOfClone = BOUNCINGCLONE;
				noChosenPlayer = false;
				table.setVisible(false);
			};
		});
		freezing.addCaptureListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				kindOfClone = FREEZINGCLONE;
				noChosenPlayer = false;
				table.setVisible(false);
			};
		});
		table.add("Which clone do you want ?", "default-font", Color.CHARTREUSE).row();
		table.add(" ").row();
		table.add(normal).size(BUTTONWIDTH, BUTTONHEIGHT).row();
		table.add(bouncing).size(BUTTONWIDTH, BUTTONHEIGHT).row();
		table.add(freezing).size(BUTTONWIDTH, BUTTONHEIGHT).row();

		// table.setDebug(true); // This is optional, but enables debug lines for
		// tables.
		return table;
	}

	private long previousTime = System.nanoTime();

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if ((noChosenPlayer && player == null) || ((noChosenPlayer && !player.getActive()))) {
			table.setVisible(true);
		}

		if ((!noChosenPlayer && player == null) || ((!noChosenPlayer && !player.getActive()))) {
			// r = new Ragdoll(world, 55, 210, (float)Math.random());
			r = new Ragdoll(world, 55, 180, 0.1f);
			// table.setVisible(true);
			// long previousTime = System.nanoTime();
			// while(noChosenPlayer) {
			// table.setVisible(true);
			// } // loop to let the real player choose the clone he wants
			
			noChosenPlayer = true;
			switch (kindOfClone) {
			case NORMALCLONE:
				player = new Player(r);
				break;
			case BOUNCINGCLONE:
				player = new BouncingPlayer(r, world);
				break;
			case FREEZINGCLONE:
				player = new FreezingPlayer(r);
				break;
			}
			
		}

		// float force = 0;
		if (player != null) {
			move(player);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			player.dispose();
			r.body.applyAngularImpulse((float) (Math.random() * 2 - 1) * 50, true);
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
		if (player != null) {
			player.setJump(false);
		}
		int iterations = 1;
		for (int i = 0; i < iterations; i++) {
			world.step(delta / iterations, 10, 5);
			if (contactListener.deadPlayer != null) {
				Ragdoll rag = contactListener.deadPlayer.ragdoll;
				contactListener.deadPlayer.dispose();
				contactListener.deadPlayer = null;
				if (contactListener.splash == true) {
					rag.splash(world);
					contactListener.splash = false;
				}
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
			} else if (currentLevel instanceof Level2) {
				currentLevel = new Level3(world);
			} else if (currentLevel instanceof Level3) {
				currentLevel = new Level4(world);
			}
			 else if (currentLevel instanceof Level4) {
			 currentLevel = new Level1(world);
			 }
			player = null;
			contactListener.endLevel = false;
		}

		// Gdx.graphics.setTitle("Can jump: " + player.isCanJump());
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
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