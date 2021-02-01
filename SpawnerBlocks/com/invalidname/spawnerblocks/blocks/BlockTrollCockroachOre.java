package com.invalidname.spawnerblocks.blocks;

import dangerzone.Player;
import dangerzone.blocks.Block;
import dangerzone.entities.Entity;

public class BlockTrollCockroachOre extends Block {

	public BlockTrollCockroachOre(String n, String txt, int hardness, int mindam) {
		super(n, txt);
		isStone = true;
		maxdamage = hardness;
		mindamage = mindam;
		breaksound = "DangerZone:stonebreak"; 
		placesound = "DangerZone:stoneplace"; 
		hitsound =   "DangerZone:stonehit";
	}
	
	public void onBlockBroken(Player p, int dimension, int x, int y, int z) {
		
		
		
		for(int i = 0; i < p.world.rand.nextInt(2)+6; i++) {
			Entity cockroach = p.world.createEntityByName("DangerZone:Cockroach", dimension, x, y, z);
			cockroach.init();
			cockroach.doFromSpawner();
			p.world.spawnEntityInWorld(cockroach);
		}
		
		return; //your block is about to be set to 0.
	}
}
