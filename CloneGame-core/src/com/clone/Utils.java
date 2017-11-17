package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Utils {
	public static void GroundSquare3(World world, Vector2 point) {
		new GroundSquare(world, point);
		new GroundSquare(world, new Vector2(point.x+(60*GroundSquare.scale), point.y));
		new GroundSquare(world, new Vector2(point.x+(120*GroundSquare.scale), point.y));
		}
	
	public static void GroundSquare5(World world, Vector2 point) {
		new GroundSquare(world, point);
		new GroundSquare(world, new Vector2(point.x+(60*GroundSquare.scale), point.y));
		new GroundSquare(world, new Vector2(point.x+(120*GroundSquare.scale), point.y));
		new GroundSquare(world, new Vector2(point.x+(180*GroundSquare.scale), point.y));
		new GroundSquare(world, new Vector2(point.x+(240*GroundSquare.scale), point.y));
		}
	
	public static void Column3(World world, Vector2 point) {
		new GroundSquare(world, point);
		new GroundSquare(world, new Vector2(point.x, point.y+(60*GroundSquare.scale)));
		new GroundSquare(world, new Vector2(point.x, point.y+(120*GroundSquare.scale)));
		}
	
	public static void SpikeL5(World world, Vector2 point) {
		new SpikeL(world, point);
		new SpikeL(world, new Vector2(point.x +(30*GroundSquare.scale), point.y));
		new SpikeL(world, new Vector2(point.x +(60*GroundSquare.scale), point.y));
		new SpikeL(world, new Vector2(point.x +(90*GroundSquare.scale), point.y));
		new SpikeL(world, new Vector2(point.x +(120*GroundSquare.scale), point.y));
	}
	
	public static void GroundSpike(World world, Vector2 point) {
		for(int i = 0; i < 20; i++) {
			SpikeL5(world, new Vector2(20+i*150*GroundSquare.scale, point.y));
		}
	}
	
	public static void SpikesColumn(World world, Vector2 point) {
		for(int i = 0; i < 40; i++) {
			new SpikeL(world, new Vector2(point.x, 55+i*30*GroundSquare.scale));
		}
	}
	
	public static void SpikesTheWorld(World world) {
		GroundSpike(world, new Vector2(0, 55));
		GroundSpike(world, new Vector2(0, 128));
		SpikesColumn(world, new Vector2(15, 0));
		SpikesColumn(world, new Vector2(145, 0));
	}
}
