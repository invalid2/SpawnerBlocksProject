package com.invalidname.spawnerblocks.blocks;

import dangerzone.World;
import dangerzone.blocks.Block;
import dangerzone.entities.Entity;

public class BlockGuardian extends Block {
	
	float health = 20;
	
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
			entity.doFromSpawner();
			entity.setHealth(health);
			w.setblock(d, x, y, z, 0);
			w.spawnEntityInWorld(entity);
			w.playSound("SpawnerBlocks:guardian_activate", d, x, y, z, 1.0f, 1.0f);
		//}
		
	}
	
	
}
