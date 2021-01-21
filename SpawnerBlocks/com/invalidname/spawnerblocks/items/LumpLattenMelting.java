package com.invalidname.spawnerblocks.items;

import com.invalidname.spawnerblocks.SpawnerBlocksMain;
import com.invalidname.spawnerblocks.entities.EntityLattenFurnace;

import dangerzone.DangerZone;
import dangerzone.Player;
import dangerzone.blocks.Blocks;
import dangerzone.entities.Entity;
import dangerzone.items.Item;

public class LumpLattenMelting extends Item {
	
	public LumpLattenMelting(String n, String txt) {
		super(n, txt);
		
	}
	
	public boolean rightClickOnBlock(Player p, int dimension, int x, int y, int z, int side){
		int bid = p.world.getblock(dimension, x, y, z);
		
		if(bid == Blocks.furnace.blockID) {
			Entity e = DangerZone.server_world.createEntityByName("SpawnerBlocks:EntityLattenFurnace", dimension, x+0.5f, y+0.5f, z+0.5f);
			p.world.setblock(dimension, x, y, z, SpawnerBlocksMain.lattenfurnace.blockID);
			e.init();
			e.doFromSpawner();
			e.setBID(SpawnerBlocksMain.lattenfurnace.blockID);
			DangerZone.server_world.spawnEntityInWorld(e);
			return true;
		}
		
		return false; //return TRUE if your item did something and should be deleted/decremented
	}
}
