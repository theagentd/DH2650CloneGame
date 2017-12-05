package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Level4 {
	public Level4(World world) {
		Utils.Column3(world, new Vector2(50,160+(GroundSquare.SCALE*60)));
		Utils.Column3(world, new Vector2(50,160+4*(GroundSquare.SCALE*60)));
		Utils.Column3(world, new Vector2(50,160+7*(GroundSquare.SCALE*60)));
		Utils.Column3(world, new Vector2(50,160+10*(GroundSquare.SCALE*60)));
		Utils.Column3(world, new Vector2(50,160+13*(GroundSquare.SCALE*60)));
		new GroundSquare(world, new Vector2(50,160));
		Utils.GroundSquare3(world, new Vector2(50+(GroundSquare.SCALE*60),160));
		Utils.GroundSquare3(world, new Vector2(50+4*(GroundSquare.SCALE*60),160));
		
		new Saw(world, new Vector2(new Vector2(50+10*(GroundSquare.SCALE*60),160+ 3*(GroundSquare.SCALE*60))));
		new Saw(world, new Vector2(new Vector2(50+15*(GroundSquare.SCALE*60),160+ 5*(GroundSquare.SCALE*60))));
		
		for(int i = 0; i < 6; i++) {
			Utils.Column3(world, new Vector2(50+20*(GroundSquare.SCALE*60), 120 + (3*i)*(GroundSquare.SCALE*60)));
		}
		
		new BreackableWallColumn(world, new Vector2(50+20*(GroundSquare.SCALE*60), 120 + 19 * (GroundSquare.SCALE*60)));
		new BreackableWallColumn(world, new Vector2(50+20*(GroundSquare.SCALE*60), 120 + 22 * (GroundSquare.SCALE*60)));
		
		for(int i = 0; i < 3; i++) {
			Utils.Column3(world, new Vector2(50+20*(GroundSquare.SCALE*60), 220.75f + (3*i)*(GroundSquare.SCALE*60)));
		}
		
		Utils.GroundSquare3(world, new Vector2(50+21*(GroundSquare.SCALE*60), 120 + 17*(GroundSquare.SCALE*60)));
		
		Utils.GroundSquare3(world, new Vector2(50+26*(GroundSquare.SCALE*60), 120 + 15*(GroundSquare.SCALE*60)));
		new WreckingBall(world, new Vector2(50+27*(GroundSquare.SCALE*60), 120 + 30*(GroundSquare.SCALE*60)));
		
		new SpikeB(world, new Vector2(23.95f+30*(GroundSquare.SCALE*30),78.9f+1*(GroundSquare.SCALE*30)));
		Utils.Column3(world, new Vector2(50+30*(GroundSquare.SCALE*60),160-2*(GroundSquare.SCALE*60)));
		Utils.Column3(world, new Vector2(50+31*(GroundSquare.SCALE*60),160-2*(GroundSquare.SCALE*60)));
		Utils.Column3(world, new Vector2(50+32*(GroundSquare.SCALE*60),160-2*(GroundSquare.SCALE*60)));
		Utils.Column3(world, new Vector2(50+30*(GroundSquare.SCALE*60),160-5*(GroundSquare.SCALE*60)));
		Utils.Column3(world, new Vector2(50+31*(GroundSquare.SCALE*60),160-5*(GroundSquare.SCALE*60)));
		Utils.Column3(world, new Vector2(50+32*(GroundSquare.SCALE*60),160-5*(GroundSquare.SCALE*60)));
		new BreackableWallColumn(world, new Vector2(50+30*(GroundSquare.SCALE*60),160-7*(GroundSquare.SCALE*60)));
		new BreackableWallColumn(world, new Vector2(50+31*(GroundSquare.SCALE*60),160-7*(GroundSquare.SCALE*60)));
		new BreackableWallColumn(world, new Vector2(50+32*(GroundSquare.SCALE*60),160-7*(GroundSquare.SCALE*60)));
		Utils.Column3(world, new Vector2(50+30*(GroundSquare.SCALE*60),160-11*(GroundSquare.SCALE*60)));
		Utils.Column3(world, new Vector2(50+31*(GroundSquare.SCALE*60),160-11*(GroundSquare.SCALE*60)));
		Utils.Column3(world, new Vector2(50+32*(GroundSquare.SCALE*60),160-11*(GroundSquare.SCALE*60)));
		
		Utils.GroundSquare3(world, new Vector2(50+35*(GroundSquare.SCALE*60),160+10*(GroundSquare.SCALE*60)));
		
		Utils.GroundSquare3(world, new Vector2(50+42*(GroundSquare.SCALE*60),160+12*(GroundSquare.SCALE*60)));
		new BreackableWallRaw(world, new Vector2(50+46*(GroundSquare.SCALE*60),160+12*(GroundSquare.SCALE*60)));
		new BreackableWallRaw(world, new Vector2(50+49*(GroundSquare.SCALE*60),160+12*(GroundSquare.SCALE*60)));
		Utils.GroundSquare3(world, new Vector2(50+51*(GroundSquare.SCALE*60),160+12*(GroundSquare.SCALE*60)));
		new GroundSquare(world, new Vector2(50+54*(GroundSquare.SCALE*60),160+12*(GroundSquare.SCALE*60)));
		new GroundSquare(world, new Vector2(50+54*(GroundSquare.SCALE*60),160+12*(GroundSquare.SCALE*60)));
		Utils.GroundSquare3(world, new Vector2(50+51*(GroundSquare.SCALE*60),160+12*(GroundSquare.SCALE*60)));
		Utils.Column3(world, new Vector2(50+54*(GroundSquare.SCALE*60),160+13*(GroundSquare.SCALE*60)));
		Utils.Column3(world, new Vector2(50+54*(GroundSquare.SCALE*60),160+16*(GroundSquare.SCALE*60)));
		new SpikeL(world, new Vector2(23.95f+54*(GroundSquare.SCALE*30),78.9f + 19*(GroundSquare.SCALE*30)));
		new WreckingBall(world, new Vector2(50+43*(GroundSquare.SCALE*60), 120 + 30*(GroundSquare.SCALE*60)));
		
		Utils.GroundSquare3(world, new Vector2(50+52*(GroundSquare.SCALE*60),160-3*(GroundSquare.SCALE*60)));
		new End(world, new Vector2(275, 169.5f));
		
		Utils.SpikesTheWorld(world);
	}
}
