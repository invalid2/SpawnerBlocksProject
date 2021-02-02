package com.invalidname.spawnerblocks;

import dangerzone.DangerZone;
import dangerzone.GameModes;
import dangerzone.InventoryContainer;
import dangerzone.Player;
import dangerzone.ServerHooks;
import dangerzone.blocks.Blocks;
import dangerzone.items.Items;

public class SpawnerBlocksServerHooks extends ServerHooks {
	
	public void rightClickOnBlock(Player p, int d, int x, int y, int z) {
		//Get block id of right-clicked block
		int bid = p.world.getblock(d, x, y, z);
		
		//Check if it is water or water source
		if(bid == Blocks.water.blockID || bid == Blocks.waterstatic.blockID) {
			//Get the currently held item
			InventoryContainer ic = p.getHotbar(p.gethotbarindex());
			
			//If we are holding something do...
			if(ic != null) {
				//If it is a bottle do...
				if(ic.iid == Items.findByName("DangerZone:Bottle")) {
					//Find if player already has it
					int index = SpawnerBlocksUtils.findInInventory(p, "SpawnerBlocks:Water Bottle");
					
					if(index < 10) {
						InventoryContainer icbottle = p.getHotbar(index);
						icbottle.count++;
						p.setHotbar(index, icbottle);
						p.setHotbarChanged(index);
					} else if(index >= 10 && index < 100) {
						InventoryContainer icbottle = p.getInventory(index-10);
						icbottle.count++;
						p.setInventory(index-10, icbottle);
						p.setInventoryChanged(index);
					} else if(index == 100) {
						InventoryContainer icbottle = new InventoryContainer("SpawnerBlocks:Water Bottle", 1);
						SpawnerBlocksUtils.addToInventory(p, icbottle);
					}
					
					if(p.getGameMode() == GameModes.SURVIVAL) {
						ic.count--;
						p.setHotbarChanged(p.gethotbarindex());
					}
				}
			}
		}
	}
	
	@SuppressWarnings("static-access")
	public void onBlockBroken(Player p, int d, int x, int y, int z, int s) {
		DangerZone.wr.focus_side = s;
	}
	
}
