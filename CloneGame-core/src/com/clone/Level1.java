package com.clone;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Level1 {
	public Level1(World world) {
		Utils.Column3(world, new Vector2(50,160+(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50,160+4*(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50,160+7*(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50,160+10*(GroundSquare.scale*60)));
		Utils.Column3(world, new Vector2(50,160+13*(GroundSquare.scale*60)));
		new GroundSquare(world, new Vector2(50,160));
		Utils.GroundSquare3(world, new Vector2(50+(GroundSquare.scale*60),160));
		Utils.GroundSquare3(world, new Vector2(50+4*(GroundSquare.scale*60),160));
		//jump3
		Utils.GroundSquare3(world, new Vector2(50+10*(GroundSquare.scale*60),160));
		Utils.GroundSquare3(world, new Vector2(50+12*(GroundSquare.scale*60),160-(60*GroundSquare.scale)));
		Utils.GroundSquare3(world, new Vector2(50+15*(GroundSquare.scale*60),160-(60*GroundSquare.scale)));
		new GroundSquare(world, new Vector2(50+18*(GroundSquare.scale*60),160-(60*GroundSquare.scale)));
		//jump 5
		Utils.SpikeL5(world, new Vector2(23.95f+13*(GroundSquare.scale*30),80-15*(GroundSquare.scale)));
		
		//double jump
		Utils.GroundSquare3(world, new Vector2(50+18*(GroundSquare.scale*60),160));
		Utils.SpikeL5(world, new Vector2(23.95f+21*(GroundSquare.scale*30),80-15*(GroundSquare.scale)));
		Utils.SpikeL5(world, new Vector2(23.95f+26*(GroundSquare.scale*30),80-15*(GroundSquare.scale)));
		Utils.GroundSquare3(world, new Vector2(50+20*(GroundSquare.scale*60),160-(60*GroundSquare.scale)));
		Utils.GroundSquare3(world, new Vector2(50+23*(GroundSquare.scale*60),160-(60*GroundSquare.scale)));
		Utils.GroundSquare3(world, new Vector2(50+26*(GroundSquare.scale*60),160-(60*GroundSquare.scale)));
		Utils.GroundSquare3(world, new Vector2(50+29*(GroundSquare.scale*60),160-(60*GroundSquare.scale)));

		Utils.GroundSquare3(world, new Vector2(50+31*(GroundSquare.scale*60),160));
		Utils.GroundSquare3(world, new Vector2(50+35*(GroundSquare.scale*60),160 + 2*(GroundSquare.scale*60)));
		//jump
		Utils.SpikeL5(world, new Vector2(23.95f+40*(GroundSquare.scale*30),80-15*(GroundSquare.scale)));
		Utils.GroundSquare3(world, new Vector2(50+40*(GroundSquare.scale*60),160-(60*GroundSquare.scale)));
		Utils.GroundSquare3(world, new Vector2(50+42*(GroundSquare.scale*60),160-(60*GroundSquare.scale)));
		
		Utils.GroundSquare3(world, new Vector2(50+46*(GroundSquare.scale*60),160));
		Utils.GroundSquare3(world, new Vector2(50+49*(GroundSquare.scale*60),160));
		Utils.GroundSquare3(world, new Vector2(50+52*(GroundSquare.scale*60),160));
		
		//Under the level
		Utils.SpikesTheWorld(world);
	}
}
