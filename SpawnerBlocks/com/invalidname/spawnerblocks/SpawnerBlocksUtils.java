package com.invalidname.spawnerblocks;

import dangerzone.InventoryContainer;
import dangerzone.Player;
import dangerzone.StuffList;
import dangerzone.Utils;
import dangerzone.World;
import dangerzone.blocks.Blocks;
import dangerzone.entities.Entity;
import dangerzone.items.Item;
import dangerzone.items.Items;

public class SpawnerBlocksUtils {
	
	/**
	 * Find an item in the player's inventory <br>
	 * 0-9 = hotbar   10-59 = inventory   100 = not found
	 * @param p Player whose inventory we will be looking through
	 * @param name Item we are looking for uniquename
	 * @return Returns position of item, if none are found returns 0
	 * @see dangerzone.Player
	 * @see dangerzone.InventoryContainer
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
	
	/**
	 * Look through inventory to add item to next available slot
	 * @param p Player whose inventory we will be looking into
	 * @param ic InventoryContainer the slot will be set as
	 * @see dangerzone.Player
	 * @see dangerzone.InventoryContainer
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
	
	/**
	 * Generic, no checks, puts int array build on the world based on the corresponding palette string array <br>
	 * Special block codes:<br>
	 *  0 - nothing is put <br>
	 * -1 - replace with air <br>
	 * -2 - chest <br>
	 * -3 - furnace <br>
	 * -4 - % replace with block, else air 
	 * @param build Integer array of relative index of blocks composing the build
	 * @param palette String array of block uniquenames, build[i][j][k]-1 is used as index the array
	 * @param world dangerzone.World
	 * @param dimension Dimension id
	 * @param x Starting x position
	 * @param y Starting y position
	 * @param z Starting z position
	 * @param loot Loot table for chests
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
							world.setblock(dimension, x+i, y+j, z+k, Blocks.furnace.blockID);
							Entity eb = world.createEntityByName("DangerZone:EntityFurnace", dimension, (double)(x+i)+0.5f, (double)(y+j)+0.05f, (double)(z+k)+0.5f);
							eb.setBID(Blocks.furnace.blockID);
							eb.setIID(Blocks.furnaceOn.blockID);
							eb.init();
							eb.doFromSpawner();
							//System.out.println(eb);
							world.spawnEntityInWorld(eb);
							break;
						case -4:
							//System.out.println(Blocks.stone.blockID);
							world.setblock(dimension, x+i, y+j, z+k, world.rand.nextInt(3) == 0? Blocks.findByName(palette[4]): 0);
							break;
						default:
							world.setblock(dimension, x+i, y+j, z+k, Blocks.findByName(palette[build[i][j][k]-1]));
					}
				}
			}
		}
	}
	/**
	 * Rotates build relative to a angle clockwise
	 * @param build Integer array of relative index of blocks composing the build
	 * @param angle Rotate build to this, is a BuildAngles constant
	 * @return Returns rotated build
	 * @since 1
	 * @version 1
	 */
	public static int[][][] rotateBuild(int[][][] build, int angle) {
		int[][][] newbuild = null;
		
		
		switch(angle) {
			case BuildAngles.A_90:
				newbuild = rotateBuild90Degrees(build);
				break;
			case BuildAngles.A_180:
				newbuild = rotateBuild180Degrees(build);
				break;
			case BuildAngles.A_270:
				newbuild = rotateBuild90Degrees(rotateBuild180Degrees(build));
				break;
			default:
				break;
		}
		
		return newbuild;
	}
	
	private static int[][][] rotateBuild90Degrees(int[][][] build) {
		int[][][] newbuild = new int[build[0][0].length][][];
		int i = 0, j = 0, k = 0;
		for(i = 0; i < newbuild.length; i++) {
			newbuild[i] = new int[build[i].length][];
			for(j = 0; j < newbuild[i].length; j++) {
				newbuild[i][j] = new int[build.length];
				for(k = 0; k < newbuild[i][j].length; k++) {
					if(build[k][j].length > 0)
						newbuild[i][j][k] = build[k][j][i];
				}
			}
		}
		
		return newbuild;
	}
	
	private static int[][][] rotateBuild180Degrees(int[][][] build) {
		int[][][] newbuild = new int[build.length][build[0].length][build[0][0].length];
		
		for(int i = 0; i < newbuild.length; i++) {
			for(int j = 0; j < newbuild[i].length; j++) {
				for(int k = 0; k < newbuild[i][j].length / 2; k++) {
					if(build[i][j].length-1-k > -1) {
						newbuild[i][j][k] = build[i][j][build[i][j].length-1-k];
						newbuild[i][j][newbuild[i][j].length-1-k] = build[i][j][k];
					}
					
					//System.out.println(newbuild.length+" "+newbuild[i].length+" "+newbuild[i][j].length+" "+(build[i][j].length-1-k));
				}
			}
		}
		
		return newbuild;
	}
	
	/**
	 * BuildAngles constants for the rotateBuild() method 
	 * @since 1
	 * @version 1
	 */
	public class BuildAngles {
		public static final int A_0 = 0;
		public static final int A_90 = 1;
		public static final int A_180 = 2;
		public static final int A_270 = 3;
		public static final int A_360 = 4;
	}
}
