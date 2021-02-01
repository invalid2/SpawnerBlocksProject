package com.invalidname.spawnerblocks;

import dangerzone.InventoryContainer;
import dangerzone.Player;
import dangerzone.StuffList;
import dangerzone.Utils;
import dangerzone.World;
import dangerzone.blocks.BlockRotation;
import dangerzone.blocks.Blocks;
import dangerzone.entities.Entity;
import dangerzone.items.Item;
import dangerzone.items.Items;

public class SpawnerBlocksUtils {
	
	/*
	 * Find an item in the player's inventory
	 * 0-9 = hotbar   10-59 = inventory   100 = not found
	 */
	public static int findInInventory(Player p, String name) {
		int iid = Items.findByName(name);
		Item item = Items.getItem(iid);
		for(int i = 0; i < 10; i++) {
			if(p.getHotbar(i) != null) {
				if(p.getHotbar(i).iid == iid && p.getHotbar(i).count < item.maxstack) {
					return i;
				}
			}
			
		}
		
		for(int i = 0; i < 50; i++) {
			if(p.getInventory(i) != null) {
				if(p.getInventory(i).iid == iid  && p.getInventory(i).count < item.maxstack) {
					return i+10;
				}
			}
			
		}
		return 100;
	}
	
	/*
	 * Look through inventory to add to next available slot
	 */
	public static void addToInventory(Player p, InventoryContainer ic) {
		//Check in hotbar first
		for(int i = 0; i < 10; i++) {
			if(p.getHotbar(i) == null) {
				p.setHotbar(i, ic);
				p.setHotbarChanged(i);
				return;
			}
		}
		
		//Didn't find it in the hotbar, so look in the inventory
		for(int i = 0; i < 50; i++) {
			if(p.getInventory(i) == null) {
				p.setInventory(i, ic);
				p.setInventoryChanged(i);
				return;
			}
		}
	}
	/*
	 * Generic, no checks, puts int array build on the world based on the corresponding dictionary array 
	 */
	public static void doBuild( int[][][] build, String[] palette, World world, int dimension, int x, int y, int z, StuffList[] loot) {
		
		for(int i = 0; i < build.length; i++) {
			for(int j = 0; j < build[i].length; j++) {
				for(int k = 0; k < build[i][j].length; k++) {
					switch(build[i][j][k]) {
						case 0:
							break;
						case -1:
							if(world.getblock(dimension, x+i, y+j, z+k) != 0)
								world.setblock(dimension, x+i, y+j, z+k, 0);
							break;
						case -2:
							Utils.add_chest(world, dimension, x+i, y+j, z+k, loot);
							break;
						case -3:
							world.setblock(dimension, i, j, k, Blocks.furnace.blockID);
							Entity eb = world.createEntityByName("DangerZone:EntityFurnace", dimension, (double)(i)+0.5f, (double)(j)+0.05f, (double)(k)+0.5f);
							eb.init();
							eb.doFromSpawner();
							eb.setBID(Blocks.furnace.blockID);
							eb.setIID(Blocks.furnaceOn.blockID);
							//System.out.println(eb);
							world.spawnEntityInWorld(eb);
							break;
						default:
							world.setblock(dimension, x+i, y+j, z+k, Blocks.findByName(palette[build[i][j][k]-1]));
					}
				}
			}
		}
	}
}
