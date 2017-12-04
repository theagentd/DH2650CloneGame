package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Level2 {
	public Level2(World world) {
		Utils.Column3(world, new Vector2(50,160+(GroundSquare.SCALE*60)));
		Utils.Column3(world, new Vector2(50,160+4*(GroundSquare.SCALE*60)));
		Utils.Column3(world, new Vector2(50,160+7*(GroundSquare.SCALE*60)));
		Utils.Column3(world, new Vector2(50,160+10*(GroundSquare.SCALE*60)));
		Utils.Column3(world, new Vector2(50,160+13*(GroundSquare.SCALE*60)));
		new GroundSquare(world, new Vector2(50,160));
		Utils.GroundSquare3(world, new Vector2(50+(GroundSquare.SCALE*60),160));
		Utils.GroundSquare3(world, new Vector2(50+4*(GroundSquare.SCALE*60),160));
		
		Utils.Column3(world, new Vector2(50+6*(GroundSquare.SCALE*60),160-2*(GroundSquare.SCALE*60)));
		Utils.SpikeL5(world, new Vector2(23.95f+7*(GroundSquare.SCALE*30),78.9f-1*(GroundSquare.SCALE*30)));
		Utils.SpikeL5(world, new Vector2(23.95f+12*(GroundSquare.SCALE*30),78.9f-1*(GroundSquare.SCALE*30)));
		new SpikeL(world, new Vector2(23.95f+17*(GroundSquare.SCALE*30),78.9f-1*(GroundSquare.SCALE*30)));
		new SpikeL(world, new Vector2(23.95f+18*(GroundSquare.SCALE*30),78.9f-1*(GroundSquare.SCALE*30)));
		
		Utils.GroundSquare3(world, new Vector2(50+7*(GroundSquare.SCALE*60),160-2*(GroundSquare.SCALE*60)));
		Utils.GroundSquare3(world, new Vector2(50+10*(GroundSquare.SCALE*60),160-2*(GroundSquare.SCALE*60)));
		Utils.GroundSquare3(world, new Vector2(50+13*(GroundSquare.SCALE*60),160-2*(GroundSquare.SCALE*60)));
		Utils.GroundSquare3(world, new Vector2(50+16*(GroundSquare.SCALE*60),160-2*(GroundSquare.SCALE*60)));
		
		Utils.GroundSquare3(world, new Vector2(50+14*(GroundSquare.SCALE*60),160+5*(GroundSquare.SCALE*60)));
		
		Utils.Column3(world, new Vector2(50+19*(GroundSquare.SCALE*60),160-2*(GroundSquare.SCALE*60)));
		Utils.Column3(world, new Vector2(50+20*(GroundSquare.SCALE*60),160-2*(GroundSquare.SCALE*60)));
		Utils.Column3(world, new Vector2(50+21*(GroundSquare.SCALE*60),160-2*(GroundSquare.SCALE*60)));
		new SpikeB(world, new Vector2(23.95f+19*(GroundSquare.SCALE*30),78.9f+1*(GroundSquare.SCALE*30)));
		
		Utils.GroundSquare5(world, new Vector2(50+24*(GroundSquare.SCALE*60),160+7*(GroundSquare.SCALE*60)));
		
		new GroundSquare(world, new Vector2(50+40*(GroundSquare.SCALE*60),160+7*(GroundSquare.SCALE*60)));
		
		new GroundSquare(world, new Vector2(50+48*(GroundSquare.SCALE*60),160-10*(GroundSquare.SCALE*60)));
		/*for(int i = 12; i < 24; i = i + 4) {
			new GroundSquare(world, new Vector2(50+48*(GroundSquare.scale*60),140+i*(GroundSquare.scale*60)));
		}*/
		new GroundSquare(world, new Vector2(50+48*(GroundSquare.SCALE*60),160+23*(GroundSquare.SCALE*60)));
		new WreckingBall(world, new Vector2(50+48*(GroundSquare.SCALE*60),160+23*(GroundSquare.SCALE*60)));
		
		Utils.GroundSquare5(world, new Vector2(50+54*(GroundSquare.SCALE*60),160+7*(GroundSquare.SCALE*60)));
		Utils.SpikesTheWorld(world);
		
		new End(world, new Vector2(282f, 211.5f));


	}
}
