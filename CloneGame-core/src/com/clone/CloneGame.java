package com.clone;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class CloneGame extends ApplicationAdapter {
	
	private World world;
	private Box2DDebugRenderer debugRenderer;
	
	private Ragdoll r;
	private Player player;
	
	@Override
	public void create () {
		
		world = new World(new Vector2(0, 0), true);
		debugRenderer = new Box2DDebugRenderer();

		for(int i = 0; i < 5; i++){
			r = new Ragdoll(world, 10 + i*2, 20);
		}
		player = new Player(r);
	}

	private long previousTime = System.nanoTime();
	
	@Override
	public void render () {
		
		
		float force = 0;
		try {
			move(player);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
//			force = 20;
//			player.jump(force);
//			if(r.body != null) r.body.applyForceToCenter(-force, 0, true);
//			if(r.head != null) r.head.applyForceToCenter(+force, 0, true);
//		}
		
		
		
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Matrix4 matrix = new Matrix4();
		matrix.setToOrtho2D(0, 0, Gdx.graphics.getWidth()/20f, Gdx.graphics.getHeight()/20f, -1, +1);
		
		long time = System.nanoTime();
		float delta = (time - previousTime) / 1000000000f;
		previousTime = time;
		world.step(delta, 6, 2);
		
		debugRenderer.render(world, matrix);
		
	}
	
	@Override
	public void dispose () {
		
	}
	
	private void move(Player player) throws InterruptedException {
		float force = 20;
		if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)){
			player.jump(force);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)){
			player.applyForce(force, 0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)){
			player.applyForce(-force, 0);;
		}
		
		
	}
}
