package com.invalidname.spawnerblocks;

import dangerzone.DZWorldDecorator;
import dangerzone.Dimensions;
import dangerzone.InventoryContainer;
import dangerzone.StuffList;
import dangerzone.Utils;
import dangerzone.World;
import dangerzone.WorldDecorator;
import dangerzone.biomes.Biome;
import dangerzone.blocks.BlockRotation;
import dangerzone.blocks.Blocks;
import dangerzone.entities.Entity;
import dangerzone.entities.EntityChest;
import dangerzone.items.Items;

public class SpawnerBlocksWorldDecorator extends WorldDecorator{
	
	
	public void decorate(World world, int dimension, Biome b, int chunkx, int chunkz) {
		if(b.should_add_dungeons) {
			if(world.rand.nextInt(128) == 0) {
				int xoff, yoff, zoff;
				int x = chunkx << 4;
				int z = chunkz << 4;
				xoff = world.rand.nextInt(16);
				zoff = world.rand.nextInt(16);
				yoff = world.rand.nextInt(40) + 5;
				
				SpawnerBlocksUtils.doBuild(SpawnerBlocksBuilds.spidernest, world.rand.nextInt(2) == 0 ? SpawnerBlocksBuilds.palette_spidernestsmall_muffin : SpawnerBlocksBuilds.palette_spidernestsmall_skelly, world, dimension, x+xoff, yoff, z+zoff, DZWorldDecorator.things);
			}
		}
		
		if(world.rand.nextInt(80) == 0) {
			putBuildOnSurface( SpawnerBlocksBuilds.abandoned_shack, SpawnerBlocksBuilds.palette_abandonedshack, world, dimension, chunkx, chunkz, LootTables.surface_loot);
			//return;
		}
		
		if(world.rand.nextInt(200) == 0) {
			putBuildOnSurface( SpawnerBlocksBuilds.abandoned_tower, SpawnerBlocksBuilds.palette_abandonedtower, world, dimension, chunkx, chunkz, LootTables.surface_loot);
			//return;
		}
		
		if(world.rand.nextInt(160) == 0) {
			putBuildOnSurface( SpawnerBlocksBuilds.small_well, SpawnerBlocksBuilds.palette_smallwell, world, dimension, chunkx, chunkz, LootTables.surface_loot);
			//return;
		}
		
		if(world.rand.nextInt(540) == 0) {
			putBuildOnSurface( SpawnerBlocksBuilds.vampireoutpost, SpawnerBlocksBuilds.palette_vampirepost, world, dimension, chunkx, chunkz, DZWorldDecorator.things);
			//return;
		}
		
		
		if(dimension != Dimensions.skyislandsdimension.dimensionID /*&& dimension != SpawnerBlocksMain.skycurrents.dimensionID*/) {
			if(world.rand.nextInt(720) == 0) {
				doGiantSpiderNest( world, dimension, b, chunkx, chunkz);
				//return;
			}
			
			if(world.rand.nextInt(4) == 0) {
				int xoff, zoff;
				int x = chunkx << 4;
				int z = chunkz << 4;
				int yoff = 200;
				xoff = world.rand.nextInt(16) - 8;
				zoff = world.rand.nextInt(16) - 8;
				
				if(Blocks.isSolid(world.getblock(dimension, x+xoff+6, yoff-1, z+zoff+6)) && Blocks.isSolid(world.getblock(dimension, x+xoff, yoff-1, z+zoff)))
					SpawnerBlocksUtils.doBuild(SpawnerBlocksBuilds.dracoratnest, SpawnerBlocksBuilds.palette_dracoratnest, world, dimension, x+xoff, yoff, z+zoff, null);
				//return;
			}
			
			//if(world.rand.nextInt(384) == 0) {
				//doBattleTower( world, dimension, chunkx, chunkz);
				//return;
			//}
			
			if(world.rand.nextInt(256) == 0) {
				//Secret buried treasure, oh my!
				int xoff, yoff, zoff;
				int x = chunkx << 4;
				int z = chunkz << 4;
				xoff = world.rand.nextInt(16);
				zoff = world.rand.nextInt(16);
				yoff = world.rand.nextInt(20) + 1;
				
				Utils.add_chest(world, dimension, x+xoff, yoff, z+zoff, DZWorldDecorator.things);
				world.setblock(dimension, x+xoff, yoff+1, z+zoff, SpawnerBlocksMain.waypointblock.blockID);
				world.setblock(dimension, x+xoff, yoff+2, z+zoff, 0);
				SpawnerBlocksMain.waymarks.insert( x+xoff, yoff+1, z+zoff, dimension);
				//return;
			}
		}
	}
	
	public void putBuildOnSurface(int[][][] build, String[] palette, World world, int dimension, int chunkx, int chunkz, StuffList[] loot) {
		int ix = (chunkx<<4)+world.rand.nextInt(16);
		int iz = (chunkz<<4)+world.rand.nextInt(16);
		int iy;
		
		for(iy=150;iy>70;iy--) {
			
			//Checks for grass at the corner, so no air builds
			if( (Blocks.isDirt(world.getblock(dimension, ix, iy, iz)) && Blocks.isDirt(world.getblock(dimension, ix+build.length, iy, iz+build[0][0].length)))  ||  (Blocks.isDirt(world.getblock(dimension, ix+build.length, iy, iz)) && Blocks.isDirt(world.getblock(dimension, ix, iy, iz+build[0][0].length))) ) {
				
				//Checks if there is already something there, roughly, for perfomance
				if(Blocks.isSolid(world.getblock(dimension, ix, iy+1, iz)))return;
				if(Blocks.isSolid(world.getblock(dimension, ix+build.length, iy+1, iz)))return;
				if(Blocks.isSolid(world.getblock(dimension, ix, iy+build[0].length+1, iz)))return;
				if(Blocks.isSolid(world.getblock(dimension, ix, iy+1, iz+build[0][0].length)))return;
				if(Blocks.isSolid(world.getblock(dimension, ix+build.length, iy+build[0].length+1, iz)))return;
				if(Blocks.isSolid(world.getblock(dimension, ix+build.length, iy+1, iz+build[0][0].length)))return;
				if(Blocks.isSolid(world.getblock(dimension, ix, iy+build[0].length+1, iz+build[0][0].length)))return;
				if(Blocks.isSolid(world.getblock(dimension, ix+build.length, iy+build[0].length+1, iz+build[0][0].length)))return;
				
				SpawnerBlocksUtils.doBuild(build, palette, world, dimension, ix, iy, iz, loot);
			}
		}
	}
	
	public void putBuildOnSolid(String[][][] build, World world, int dimension, int chunkx, int chunkz, boolean hasfiller, int fillerblock, StuffList[] loot) {
		int xoff, yoff, zoff;
		int x = chunkx << 4;
		int z = chunkz << 4;
		xoff = world.rand.nextInt(16);
		zoff = world.rand.nextInt(16);
		yoff = world.rand.nextInt(40) + 5;
		
		if(!Blocks.isSolid(world.getblock(dimension, x+xoff, yoff-1, z+zoff)))return;
		if(!Blocks.isSolid(world.getblock(dimension, x+xoff+build.length, yoff-1, z+zoff)))return;
		if(!Blocks.isSolid(world.getblock(dimension, x+xoff, yoff-1, z+zoff+build[0][0].length)))return;
		if(!Blocks.isSolid(world.getblock(dimension, x+xoff+build.length, yoff-1, z+zoff+build[0][0].length)))return;
		
		for(int i = 0; i < build.length; i++) {
			for(int j = 0; j < build[0].length; j++) {
				for(int k = 0; k < build[0][0].length; k++) {
					if(build[i][j][k] != "-6" && build[i][j][k] != "-1") {
						world.setblock(dimension, x+xoff+i, yoff+j, z+zoff+k, Blocks.findByName(build[i][j][k]));
					} else if(build[i][j][k] == "-6") {
						Utils.add_chest(world, dimension, x+xoff+i, yoff+j, z+zoff+k, loot);
					} else if(build[i][j][k] == "-1") {
						world.setblock(dimension, x+xoff+i, yoff+j, z+zoff+k, fillerblock);
					}			
				}
			}
		}
	}
	
	public void doGiantSpiderNest(World world, int dimension, Biome b, int chunkx, int chunkz) {
		int xoff = 0, yoff = 0, zoff = 0;
		int x = chunkx << 4;
		int z = chunkz << 4;
		
		xoff = world.rand.nextInt(16);
		zoff = world.rand.nextInt(16);
		yoff = world.rand.nextInt(30) + 1;
		
		int type = world.rand.nextInt(2);
		SpawnerBlocksUtils.doBuild(SpawnerBlocksBuilds.spidernestgiantcore, type == 0? SpawnerBlocksBuilds.palette_spidernestgiant_muffin : SpawnerBlocksBuilds.palette_spidernestgiant_skelly, world, dimension, x+xoff, yoff, z+zoff, DZWorldDecorator.things);
		
		if(world.rand.nextInt(2) == 0)
			SpawnerBlocksUtils.doBuild(SpawnerBlocksBuilds.spidernestgiantside, type == 0? SpawnerBlocksBuilds.palette_spidernestgiant_muffin : SpawnerBlocksBuilds.palette_spidernestgiant_skelly, world, dimension, x+xoff, yoff, z+zoff+16, DZWorldDecorator.things);
		
		if(world.rand.nextInt(2) == 0)
			SpawnerBlocksUtils.doBuild(SpawnerBlocksUtils.rotateBuild(SpawnerBlocksBuilds.spidernestgiantside, SpawnerBlocksUtils.BuildAngles.A_90), type == 0? SpawnerBlocksBuilds.palette_spidernestgiant_muffin : SpawnerBlocksBuilds.palette_spidernestgiant_skelly, world, dimension, x+xoff+16, yoff, z+zoff, DZWorldDecorator.things);
		
		if(world.rand.nextInt(2) == 0)
			SpawnerBlocksUtils.doBuild(SpawnerBlocksUtils.rotateBuild(SpawnerBlocksBuilds.spidernestgiantside, SpawnerBlocksUtils.BuildAngles.A_180), type == 0? SpawnerBlocksBuilds.palette_spidernestgiant_muffin : SpawnerBlocksBuilds.palette_spidernestgiant_skelly, world, dimension, x+xoff, yoff, z+zoff-8, DZWorldDecorator.things);
		
		if(world.rand.nextInt(2) == 0)
			SpawnerBlocksUtils.doBuild(SpawnerBlocksUtils.rotateBuild(SpawnerBlocksBuilds.spidernestgiantside, SpawnerBlocksUtils.BuildAngles.A_270), type == 0? SpawnerBlocksBuilds.palette_spidernestgiant_muffin : SpawnerBlocksBuilds.palette_spidernestgiant_skelly, world, dimension, x+xoff-8, yoff, z+zoff, DZWorldDecorator.things);
		
	}
	
	public void putBuildOnSolidWTrippleFiller(String[][][] build, World world, int dimension, int posx, int posy, int posz, boolean hasfiller, int fillerblock, int chancedfiller, int chance, int typefiller, int target1, int target2, int targetchance, StuffList[] loot) {
		
		for(int i = 0; i < build.length; i++) {
			for(int j = 0; j < build[0].length; j++) {
				for(int k = 0; k < build[0][0].length; k++) {
					
					if(build[i][j][k] != null) {
						switch(build[i][j][k]) {
							case "-1":
								world.setblock(dimension, posx+i, posy+j, posz+k, fillerblock);
								break;
							case "-2":
								if(world.rand.nextInt(chance) == 0) {
									world.setblock(dimension, posx+i, posy+j, posz+k, chancedfiller);
								} else {
									world.setblock(dimension, posx+i, posy+j, posz+k, 0);
								}
								break;
							case "-3":
								if(targetchance == 0)
									world.setblock(dimension, posx+i, posy+j, posz+k, target1);
								if(targetchance == 1)
									world.setblock(dimension, posx+i, posy+j, posz+k, target2);
								break;
							case "-6":
								Utils.add_chest(world, dimension, posx+i, posy+j, posz+k, loot);
								break;
							default:
								world.setblock(dimension, posx+i, posy+j, posz+k, Blocks.findByName(build[i][j][k]));
						}
					}
				}
			}
		}
	}
	
	public void doFill( World world, int dimension, int startx, int starty, int startz, int length, int height, int width, int BID) {
		
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < height; j++) {
				for(int k = 0; k < width; k++) {
					world.setblock(dimension, startx+i, starty+j, startz+k, BID);
				}
			}
		}
	}
	
	public void doDracoratNest(String[][][] build, World world, int dimension, int chunkx, int chunkz, boolean hasfiller, int fillerblock, StuffList[] loot) {
		int xoff, zoff;
		int x = chunkx << 4;
		int z = chunkz << 4;
		int yoff = 200;
		xoff = world.rand.nextInt(16) - 8;
		zoff = world.rand.nextInt(16) - 8;
		
		if(!Blocks.isSolid(world.getblock(dimension, x+xoff+build.length, yoff-1, z+zoff+build[0][0].length)) || !Blocks.isSolid(world.getblock(dimension, x+xoff, yoff-1, z+zoff)))return;
		
		for(int i = 0; i < build.length; i++) {
			for(int j = 0; j < build[0].length; j++) {
				for(int k = 0; k < build[0][0].length; k++) {
					if(build[i][j][k] != Blocks.chest.uniquename && build[i][j][k] != "-1") {
						world.setblock(dimension, x+xoff+i, yoff+j, z+zoff+k, Blocks.findByName(build[i][j][k]));
					} else if(build[i][j][k] == Blocks.chest.uniquename) {
						Utils.add_chest(world, dimension, x+xoff+i, yoff+j, z+zoff+k, loot);
					} else if(build[i][j][k] == "-1") {
						world.setblock(dimension, x+xoff+i, yoff+j, z+zoff+k, fillerblock);
					} else if(build[i][j][k] == null) {
						
					}
				}
			}
		}
	
	}
	
	public void doBattleTower(World world, int dimension, int chunkx, int chunkz) {
		
		int btsize = 14;
		int ix = (chunkx<<4)+world.rand.nextInt(16);
		int iz = (chunkz<<4)+world.rand.nextInt(16);
		int iy;
		
		for(iy=100;iy>70;iy--) {
			
			//Checks for ground at the corners, so no air builds
			if( world.getblock(dimension, ix, iy, iz) != 0 && world.getblock(dimension, ix+btsize, iy, iz) != 0 && world.getblock(dimension, ix, iy, iz+btsize) != 0 && world.getblock(dimension, ix+btsize, iy, iz+btsize) != 0 ) {
				int bttype = world.rand.nextInt(5);
				int numfloors = world.rand.nextInt(7)+3;
				String[] pal = {"DangerZone:Grass Block", "DangerZone:Grey Stone", "DangerZone:Grey Stone", "DangerZone:Moose Spawner"};
				//Eww so many ifs, change to a switch for gods sake! --done!
				switch(bttype) {
					case 0:
						pal[3] = "DangerZone:Moose Spawner";
						break;
					case 1:
						pal[2] = "DangerZone:Dark Block";
						pal[3] = "DangerZone:Vampire Spawner";
						break;
					case 2:
						pal[2] = "DangerZone:Dark Block";
						pal[3] = "DangerZone:Werewolf Spawner";
						break;
					case 3:
						numfloors = 10;
						pal[1] = "DangerZone:Bloodstone Block";
						pal[2] = "DangerZone:Dark Block";
						pal[3] = "DangerZone:Vampire Moose Spawner";
						break;
					case 4:
						pal[3] = "SpawnerBlocks:Impostor Spawner";
						break;
				}
				
				SpawnerBlocksUtils.doBuild(SpawnerBlocksBuilds.battletowerbase, pal, world, dimension, ix, iy, iz, DZWorldDecorator.things);
				for(int i = 1; i < numfloors; i++) {
					SpawnerBlocksUtils.doBuild(SpawnerBlocksBuilds.battletowerfloor, pal, world, dimension, ix, iy+6*i, iz, DZWorldDecorator.things);
				}
				SpawnerBlocksUtils.doBuild(SpawnerBlocksBuilds.battletowertop, pal, world, dimension, ix, iy+6*numfloors, iz, DZWorldDecorator.things);
				break;
			}
		}
	}
	
	public void putBTFloor( String[][][] build, World world, int dimension, int posx, int posy, int posz, int groundBID, int wallBID, int spawnerBID, int belowBID, StuffList[] loot) {
		for(int i = 0; i < build.length; i++) {
			for(int j = 0; j < build[0].length; j++) {
				for(int k = 0; k < build[0][0].length; k++) {
					if(build[i][j][k] != null) {
						switch(build[i][j][k]) {
							case "-1":
								world.setblock(dimension, posx+i, posy+j, posz+k, groundBID);
								break;
							case "-2":
								world.setblock(dimension, posx+i, posy+j, posz+k, wallBID);
								break;
							case "-3":
								world.setblock(dimension, posx+i, posy+j, posz+k, spawnerBID);
								break;
							case "-4":
								if(world.getblock(dimension, posx+i, posy+j, posz+k) != 0)
									world.setblock(dimension, posx+i, posy+j, posz+k, 0);
								break;
							case "-5":
								world.setblock(dimension, posx+i, posy+j, posz+k, belowBID);
								break;
							case "-6":
								Utils.add_chest(world, dimension, posx+i, posy+j, posz+k, loot);
								break;
							case "DangerZone:Chest":
								Utils.add_chest(world, dimension, posx+i, posy+j, posz+k, loot);
								break;
						}
					}
				}
			}
		}
	}
	
	public static void add_chest_with_wayfinder(World w, int dimension, int x, int y, int z, StuffList[] stuff){
		int nthings = w.rand.nextInt(25)+20; //lots of "tries"
		add_chest_with_wayfinder(w, dimension, x, y, z, stuff, nthings);
	}
	
	public static void add_chest_with_wayfinder(World w, int dimension, int x, int y, int z, StuffList[] stuff, int chances){	
		if(!w.isServer)return;
		//add a chest and put some things in it
		//try to face an open block
		int meta = 0;
		if(w.getblock(dimension, x, y, z+1)!=0){
			meta = BlockRotation.Y_ROT_180; //try this!
			if(w.getblock(dimension, x, y, z-1)!=0){
				meta = BlockRotation.Y_ROT_270; //try this!
				if(w.getblock(dimension, x-1, y, z)!=0){
					meta = BlockRotation.Y_ROT_90; //try this!
					if(w.getblock(dimension, x+1, y, z)!=0){
						meta = 0; //give up
					}
				}
			}			
		}
		w.setblockandmeta(dimension, x, y, z, Blocks.chest.blockID, meta); //hopefully it's pointing the right way!
		Entity eb = w.createEntityByName("DangerZone:EntityChest", dimension, (double)(x)+0.5f, (double)(y)+0.05f, (double)(z)+0.5f);
		if(eb != null){
			//put some things into our new chest entity **BEFORE** we spawn it.
			eb.init();
			EntityChest ec = (EntityChest)eb;
			if(stuff != null && stuff.length > 0 && chances > 0){

				int where, i;
				int val, which, howmany;
				int bid, iid;

				for(i=0;i<chances;i++){
					where = w.rand.nextInt(50);
					val = w.rand.nextInt(100);
					if(stuff.length < 2){
						which = 0;
					}else{
						which = w.rand.nextInt(stuff.length);
					}
					if(val <= stuff[which].chance){
						howmany = stuff[which].min;
						if(stuff[which].max - stuff[which].min > 0){
							howmany += w.rand.nextInt((stuff[which].max - stuff[which].min)+1);
						}
						bid = stuff[which].getbid();
						iid = stuff[which].getiid();
						if(bid != 0){
							if(howmany > Blocks.getMaxStack(bid))howmany = Blocks.getMaxStack(bid);
						}
						if(iid != 0){
							if(howmany > Items.getMaxStack(iid))howmany = Items.getMaxStack(iid);
						}
						if(bid != 0 && iid != 0)continue;
						if(bid == 0 && iid == 0)continue;
						if(bid < 0 || iid < 0)continue;
						if(howmany > 0){
							if(iid == SpawnerBlocksMain.wayfinder.itemID) {
								//InventoryContainer
								ec.setInventory(where, new InventoryContainer(bid, iid, howmany, 0));
							} else {
								ec.setInventory(where, new InventoryContainer(bid, iid, howmany, 0)); //just set it	
							}
												
						}
					}
				}
			}
			//spawn the chest entity!	

			w.spawnEntityInWorld(eb);
		}
	}
}
