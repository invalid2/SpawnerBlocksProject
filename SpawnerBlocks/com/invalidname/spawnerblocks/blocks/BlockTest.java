package com.invalidname.spawnerblocks.blocks;

import com.invalidname.spawnerblocks.SpawnerBlocksMain;

import dangerzone.Player;
import dangerzone.StuffList;
import dangerzone.Utils;
import dangerzone.World;
import dangerzone.blocks.Block;
import dangerzone.blocks.Blocks;
import dangerzone.items.Item;
import dangerzone.items.Items;

public class BlockTest extends Block {
	
	StuffList gift = null;
	
	public static StuffList[] gifts = new StuffList[] {
			new StuffList(Items.coinsilver, 5, 25, 100),
			new StuffList(Items.pistol, 1, 1, 100),
			new StuffList(Items.bullets, 4, 8, 100),
			new StuffList(Items.woodenaxe, 1, 1, 100),
			new StuffList(Items.woodenshovel, 1, 1, 100),
			new StuffList(Items.woodenhoe, 1, 1, 100),
			new StuffList(Items.woodenpickaxe, 1, 1, 100),
			new StuffList(Items.woodensword, 1, 1, 100),
			new StuffList(Items.furball, 1, 10, 100),
			new StuffList(Items.furball, 1, 10, 100),
			new StuffList(Items.furball, 1, 10, 100),
			new StuffList(Items.furball, 1, 10, 100),
			new StuffList(Blocks.stand, 5, 10, 100),
			new StuffList(Blocks.stone, 1, 5, 100),
			new StuffList(Blocks.greystone, 1, 5, 100),
			new StuffList(Blocks.lightstick, 1, 16, 100),
			new StuffList(Blocks.darkstick, 1, 16, 100),
			new StuffList(Blocks.blocklight, 1, 1, 100),
			new StuffList(Blocks.blockdark, 1, 1, 100),
			new StuffList(Blocks.blockcopper, 1, 1, 100),
			new StuffList(Blocks.blocktin, 1, 1, 100),
			new StuffList(Blocks.log, 5, 10, 100),
			new StuffList(Blocks.willowlog, 5, 10, 100),
			new StuffList(Blocks.redwoodlog, 5, 10, 100),
			new StuffList(Blocks.stopblock, 1, 5, 100),
			new StuffList(SpawnerBlocksMain.giantstick, 1, 2, 100),
			new StuffList(SpawnerBlocksMain.giantwoodensword, 1, 1, 100),
			new StuffList(SpawnerBlocksMain.giantwoodenpickaxe, 1, 1, 100),
			new StuffList(SpawnerBlocksMain.giantwoodenaxe, 1, 1, 100),
			new StuffList(SpawnerBlocksMain.giantwoodenshovel, 1, 1, 100),
			new StuffList(SpawnerBlocksMain.giantstonesword, 1, 1, 100),
			new StuffList(SpawnerBlocksMain.giantstonepickaxe, 1, 1, 100),
			new StuffList(SpawnerBlocksMain.giantstoneaxe, 1, 1, 100),
			new StuffList(SpawnerBlocksMain.giantstoneshovel, 1, 1, 100),
			new StuffList(SpawnerBlocksMain.eggbutterFly, 1, 8, 100),
			new StuffList(SpawnerBlocksMain.ammo, 1, 4, 100)
	};

	public BlockTest() {
		super("SpawnerBlocks:Test Block", "res/blocks/stone.png");
		
	}
	
	public int getBlockDrop(Player p, World w, int dimension, int x, int y, int z){
		
		if(gift != null) {
			
			if(gift.thing instanceof Block) {
				Utils.spawnExperience(10, w, dimension, (double)x+0.5f, (double)y+0.5f, (double)z+0.5f);
				return ((Block) gift.thing).blockID;
			}
		}
		return 0;
	}
	
	public int getItemDrop(Player p, World w, int d, int x, int y, int z){
		//Player can be NULL! Make sure you check!
		
		if(gift != null) {
			if(gift.thing instanceof Item) {
				Utils.spawnExperience(10, w, d, (double)x+0.5f, (double)y+0.5f, (double)z+0.5f);
				return ((Item) gift.thing).itemID;
			}
		}
		
		return 0; //override this if you want to drop an item instead of a block.
	}
	
	public void onBlockBroken(Player p, int dimension, int x, int y, int z){
		gift = gifts[p.world.rand.nextInt(gifts.length)];
		
		
		return; //your block is about to be set to 0.
	}
}
