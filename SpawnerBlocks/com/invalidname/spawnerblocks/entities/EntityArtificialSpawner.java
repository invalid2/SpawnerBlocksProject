package com.invalidname.spawnerblocks.entities;

import dangerzone.DangerZone;
import dangerzone.InventoryContainer;
import dangerzone.ModelBase;
import dangerzone.Player;
import dangerzone.Utils;
import dangerzone.World;
import dangerzone.blocks.Blocks;
import dangerzone.entities.Entity;
import dangerzone.entities.EntityBlockItem;
import dangerzone.items.ItemSpawnEgg;

public class EntityArtificialSpawner extends Entity {
	
	public int blocktries = 0;
	int spawndelay = 100;
	int spawnnum = 100;
	public int spawnsuccesrate = 50;
	public int eggburntime = 100;
	public int currentburn = eggburntime;
	public Entity showentity = null ;
	public ModelBase model = null;
	
	public EntityArtificialSpawner(World w) {
		super(w);
		uniquename = "SpawnerBlocks:EntityArtificialSpawner";
		ignoreCollisions = true;
		width = 0.01f;
		height = 0.01f;
		has_inventory = true;
		maxrenderdist = 64; //it is invisible, so just update when the player gets near
		spawndelay = getVarInt(31);
		spawnnum = getVarInt(32);
		eggburntime = getVarInt(33);
		spawnsuccesrate = getVarInt(34);
		setVarInt(35, getVarInt(33));
	}
	
	public void update(float deltaT) {
		int bid = world.getblock(dimension,  (int)posx, (int)posy, (int)posz);
		if(bid != getBID()){ //check to see if our block types are there
			if(world.isServer) {
				blocktries++;
				if(blocktries > 20) {
					dumpInventory();
					this.deadflag = true;
				}
			}
		}

		
		if(!this.deadflag && world.isServer) {
			//Check if ic has something
			InventoryContainer egg = getVarInventory(0);
			if(egg != null) {
				if(showentity == null) {
					showentity = world.createEntityByName( ((ItemSpawnEgg) egg.getItem()).critter, bid, posx, posy, posz);
					if(showentity != null)
						model = showentity.model;
				}
				
				if(egg.count > 0) {
					Utils.spawnParticlesFromServer(world, "DangerZone:ParticleSmoke", 2, dimension, posx, posy, posz);
					if( world.rand.nextInt(getVarInt(31)) < 150) {
						if( world.rand.nextInt(100) < getVarInt(34)) {
							for(int i = 0; i < getVarInt(32); i++){ //loop for the number we should spawn
								//try to find a reasonable place to spawn this thing!
								float range = showentity.getWidth()*2;
								range+=2; //at least a few..
								int ht = (int)showentity.getHeight();
								//if(ht < 1)ht = 1; //at least 1
								double px, py, pz;
								boolean fits = false;
								px = posx;
								py = posy;
								pz = posz;
								//try a few randomish places
								for(int tries=0;tries<10 && !fits;tries++) {
									px = posx + range*(world.rand.nextFloat()-world.rand.nextFloat());
									pz = posz + range*(world.rand.nextFloat()-world.rand.nextFloat());
									py = posy;							
									for(int k = ht; k>=-ht && !fits;k--) {	//search down!							
										if(showentity.canFly || isSolidHere(world, dimension, px, py+k, pz, showentity.getWidth())) {
											if(!isSolidHere(world, dimension, px, py+k+1, pz, showentity.getWidth())) {
												//found an empty spot over a solid. See if we fit here...
												fits = true; //almost!
												if(ht > 0){ //check entire height
													for(int j=1;j<=ht && fits;j++) {
														if(isSolidHere(world, dimension, px, py+k+j+1, pz, showentity.getWidth()))fits = false;
													}
												}
												if(fits) {
													py = py+k+1;
												}
											}
										}
									}							
								}							
								if(fits){
									Entity e = world.createEntityByName(showentity.uniquename, dimension, px, py, pz);
									if(e != null){
										e.init();
										e.doFromSpawner();
										world.spawnEntityInWorld(e);
									}
								}
							}
						}
					}
					if(getVarInt(35) < 0) {
						InventoryContainer iic = new InventoryContainer(egg.getUniqueName(), egg.count-1);
						setVarInventory(0, iic);
						setVarInt(35, getVarInt(33));
					} else {
						setVarInt(35, getVarInt(35)-1);
					}
				} else {
					//System.out.println("blah blah");
					setVarInventory(0, null);
				}				
			} else {
				setVarInt(35, 100);
			}
		}
		
		//if(this.getInventory(0) != null)
			//System.out.println("curnumeggs:"+this.getInventory(0).count+"delay:"+getVarInt(31)+", num:"+getVarInt(32)+", eggburntime:"+getVarInt(33)+", succesrate:"+getVarInt(34)+", current burn:"+getVarInt(35));
		//kick entity out to other players!
		motionx = motiony = motionz = 0;
		rotation_yaw_motion = 10;
		super.update(deltaT);
	}
	
	public boolean rightClickedByPlayer(Player p, InventoryContainer ic) {
		
		if(ic != null) {
			if(ic.getItem() instanceof ItemSpawnEgg) {
				if(getInventory(0) != null) {
					if(getInventory(0).getUniqueName() == ic.getUniqueName()) {
						if(getInventory(0).count < getInventory(0).getMaxStack()) {
							InventoryContainer eic = new InventoryContainer( getInventory(0).getUniqueName(), getInventory(0).count+1);
							setInventory(0, eic);
							
							//eic = new InventoryContainer( ic.getUniqueName(), ic.count-1);
							//p.setHotbar(p.gethotbarindex(), eic);
							 //setVarInt(35, getVarInt(33));
							return true;
						}
					}
				} else {
					InventoryContainer eic = new InventoryContainer(ic.getUniqueName(), 1);
					setInventory(0, eic);
					//eic = new InventoryContainer( ic.getUniqueName(), ic.count-1);
					//p.setHotbar(p.gethotbarindex(), eic);
					return true;
				}
				
			}
		}
		
		
		return false;
	}
	
	private void dumpInventory() {
		InventoryContainer ic = null;
		for(int i=0;i<77;i++){
			ic = getInventory(i);
			if(ic != null){
				EntityBlockItem e = (EntityBlockItem)world.createEntityByName(DangerZone.blockitemname, dimension, posx, posy, posz);
				if(e != null){
					e.fill(ic);
					e.rotation_pitch = world.rand.nextInt(360);
					e.rotation_yaw = world.rand.nextInt(360);
					e.rotation_roll = world.rand.nextInt(360);
					e.motionx = (world.rand.nextFloat()-world.rand.nextFloat()/10f); 
					e.motiony = world.rand.nextFloat()/2;
					e.motionz = (world.rand.nextFloat()-world.rand.nextFloat()/10f); 
					world.spawnEntityInWorld(e);
				}	
			}
		}
	}
	
	private boolean isSolidHere(World w, int d, double x, double y, double z, float width) {
		int intwidth = (int)((width/2.0f)+0.5f);
		int i, j;
		int itemp;
		double dx, dz;
		for(i=-intwidth;i<=intwidth;i++) {
			for(j=-intwidth;j<=intwidth;j++) {
				if(Blocks.isSolid(w.getblock(d, (int)x+i, (int)y, (int)z+j))) {
					itemp = (int)(x)+i;
					dx = x - ((double)itemp + 0.5f);
					dx = Math.abs(dx);
					if(dx < (0.49f + (width/2.0f))) {
						itemp = (int)(z)+j;
						dz = z - ((double)itemp + 0.5f);
						dz = Math.abs(dz);
						if(dz < (0.49f + (width/2.0f))) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
