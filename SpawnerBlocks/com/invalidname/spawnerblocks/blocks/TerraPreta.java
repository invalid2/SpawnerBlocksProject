package com.invalidname.spawnerblocks.blocks;

import dangerzone.DangerZone;
import dangerzone.blocks.Block;

public class TerraPreta extends Block {
	public TerraPreta(String n, String txt) {
		super(n, txt);
		isDirt = true;
		maxdamage = 21;
		randomtick = true;
		breaksound = "DangerZone:dirt_hit";
		placesound = "DangerZone:dirt_place";
		hitsound =   "DangerZone:dirt_hit";
	}
	
	public String getStepSound(){
		int i = DangerZone.rand.nextInt(3);
		if(i == 0)return "DangerZone:dirt1";
		if(i == 1)return "DangerZone:dirt2";
		return "DangerZone:dirt3";
	}

}
