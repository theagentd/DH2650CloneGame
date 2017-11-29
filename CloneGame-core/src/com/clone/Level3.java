package com.clone;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Level3 {
	public Level3(World world) {
		Utils.Column3(world, new Vector2(50,160+(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50,160+4*(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50,160+7*(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50,160+10*(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50,160+13*(GroundSquare.scale*60)));
		new GroundSquare(world, new Vector2(50,160));
		Utils.GroundSquare3(world, new Vector2(50+(GroundSquare.scale*60),160));
		Utils.GroundSquare3(world, new Vector2(50+4*(GroundSquare.scale*60),160));
		
		Utils.GroundSquare3(world, new Vector2(50+10*(GroundSquare.scale*60),160));
		new BreackableWallRaw(world, new Vector2(50+14*(GroundSquare.scale*60),160));
		Utils.GroundSquare3(world, new Vector2(50+16*(GroundSquare.scale*60),160));
		new BreackableWallColumn(world, new Vector2(50+16*(GroundSquare.scale*60),160+2*(GroundSquare.scale*60))); 
		new BreackableWallColumn(world, new Vector2(50+16*(GroundSquare.scale*60),160+5*(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50+16*(GroundSquare.scale*60),160+7*(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50+16*(GroundSquare.scale*60),160+10*(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50+16*(GroundSquare.scale*60),160+13*(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50+16*(GroundSquare.scale*60),160+16*(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50+16*(GroundSquare.scale*60),160+19*(GroundSquare.scale*60)));
		
		Utils.GroundSquare3(world, new Vector2(50+21*(GroundSquare.scale*60),160+2*(GroundSquare.scale*60)));
		Utils.GroundSquare3(world, new Vector2(50+26*(GroundSquare.scale*60),160+4*(GroundSquare.scale*60)));
		Utils.GroundSquare3(world, new Vector2(50+31*(GroundSquare.scale*60),160+6*(GroundSquare.scale*60)));
		
		Utils.GroundSquare3(world, new Vector2(50+36*(GroundSquare.scale*60),160+8*(GroundSquare.scale*60)));
		new BreackableWallRaw(world, new Vector2(50+40*(GroundSquare.scale*60),160+8*(GroundSquare.scale*60)));
		new GroundSquare(world, new Vector2(50+41*(GroundSquare.scale*60),160+8*(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50+41*(GroundSquare.scale*60),160+9*(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50+41*(GroundSquare.scale*60),160+12*(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50+41*(GroundSquare.scale*60),160+15*(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50+41*(GroundSquare.scale*60),160+18*(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50+41*(GroundSquare.scale*60),160+21*(GroundSquare.scale*60)));
		
		Utils.GroundSquare3(world, new Vector2(50+41*(GroundSquare.scale*60),160+0*(GroundSquare.scale*60)));
		Utils.SpikeL5(world, new Vector2(23.95f+44*(GroundSquare.scale*30),79f+0*(GroundSquare.scale*30)));
		new GroundSquare(world, new Vector2(50+43*(GroundSquare.scale*60),160-1*(GroundSquare.scale*60)));
		Utils.GroundSquare5(world, new Vector2(50+44*(GroundSquare.scale*60),160-1*(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50+49*(GroundSquare.scale*60),160-1*(GroundSquare.scale*60)));
		new BreackableWallColumn(world, new Vector2(50+49*(GroundSquare.scale*60),160+3*(GroundSquare.scale*60)));
		new BreackableWallColumn(world, new Vector2(50+49*(GroundSquare.scale*60),160+6*(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50+49*(GroundSquare.scale*60),160+8*(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50+49*(GroundSquare.scale*60),160+11*(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50+49*(GroundSquare.scale*60),160+14*(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50+49*(GroundSquare.scale*60),160+17*(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50+49*(GroundSquare.scale*60),160+20*(GroundSquare.scale*60)));
		
		Utils.GroundSquare5(world, new Vector2(50+50*(GroundSquare.scale*60),160-0*(GroundSquare.scale*60)));
		new End(world, new Vector2(275, 182));
		
		
		Utils.SpikesTheWorld(world);
	}
}
