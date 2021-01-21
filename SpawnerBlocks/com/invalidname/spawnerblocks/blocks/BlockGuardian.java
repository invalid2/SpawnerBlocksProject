package com.invalidname.spawnerblocks.blocks;


import java.util.List;

import dangerzone.DangerZone;
import dangerzone.GameModes;
import dangerzone.Player;
import dangerzone.World;
import dangerzone.blocks.Block;
import dangerzone.entities.Entity;
import dangerzone.entities.EntityLiving;
import dangerzone.entities.Flag;
import dangerzone.entities.Temperament;

public class BlockGuardian extends Block {
	
	float health = 10;
	
	public BlockGuardian(String n, String txt) {
		super(n, txt);
		
		randomtick = true;
		isSolid = true;
		isStone = true;
		
	}
	
	public void tickMe(World w, int d, int x, int y, int z){
		//Override me if you want ticks!
		//See grass for an example!
		
		System.out.println("A tick");
		
		
		//if(w.rand.nextInt(4) == 0) {
			System.out.println();
			Entity entity = w.createEntityByName("SpawnerBlocks:Guardian", d, x+0.5, y, z+0.5);
			entity.init();
			entity.setHealth(health);
			entity.doFromSpawner();
			w.setblock(d, x, y, z, 0);
			w.spawnEntityInWorld(entity);
			w.playSound("SpawnerBlocks:guardian_activate", d, x, y, z, 1.0f, 1.0f);
		//}
		
	}
	
	
}
