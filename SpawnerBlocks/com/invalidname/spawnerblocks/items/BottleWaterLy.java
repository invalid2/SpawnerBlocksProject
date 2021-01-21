package com.invalidname.spawnerblocks.items;

import com.invalidname.spawnerblocks.SpawnerBlocksUtils;

import dangerzone.GameModes;
import dangerzone.InventoryContainer;
import dangerzone.Player;
import dangerzone.entities.Entity;
import dangerzone.items.Item;
import dangerzone.items.ItemFood;

public class BottleWaterLy extends ItemFood {
	
	public BottleWaterLy(String n, String tx){
		super(n, tx, 0);
		isfood = true;
		eatanytime = true;
		maxstack = 64;
	}
	
	public void onFoodEaten(Entity e) {
		if(e != null){
			if(e.world.isServer) {
				Player p = (Player) e;
				p.heal(10f);
				
				if(p.getGameMode() == GameModes.SURVIVAL) {
					int index = SpawnerBlocksUtils.findInInventory(p, "DangerZone:Bottle");
					
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
						InventoryContainer icbottle = new InventoryContainer("DangerZone:Bottle", 1);
						SpawnerBlocksUtils.addToInventory(p, icbottle);
					}
				}
			}
		}
	}
}
