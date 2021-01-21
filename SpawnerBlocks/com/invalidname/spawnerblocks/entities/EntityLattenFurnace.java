package com.invalidname.spawnerblocks.entities;

import java.util.List;

import com.invalidname.spawnerblocks.Cremating;
import com.invalidname.spawnerblocks.CrematingRecipe;
import com.invalidname.spawnerblocks.blocks.LyMachine;

import dangerzone.DangerZone;
import dangerzone.InventoryContainer;
import dangerzone.Player;
import dangerzone.Utils;
import dangerzone.World;
import dangerzone.blocks.Blocks;
import dangerzone.entities.Entity;
import dangerzone.entities.EntityBlockItem;
import dangerzone.entities.EntityLiving;
import dangerzone.items.Items;

public class EntityLattenFurnace extends Entity {

	public int blocktries = 0;

	public EntityLattenFurnace(World w) {
		super(w);
		uniquename = "SpawnerBlocks:EntityLattenFurnace";
		ignoreCollisions = true;
		width = 0.01f;
		height = 0.01f;
		setVarFloat(4, 0);
		setVarFloat(6, 0);
		setVarFloat(8, 0);
		setVarFloat(10, 0);
		setVarFloat(5, 0);
		setVarFloat(7, 0);
		setVarFloat(9, 0);
		setVarFloat(11, 0);
		has_inventory = true;
		maxrenderdist = 64; //it is invisible, so just update when the player gets near
	}
	
	public void update(float deltaT){
		int bid = world.getblock(dimension,  (int)posx, (int)posy, (int)posz);
		int meta = world.getblockmeta(dimension,  (int)posx, (int)posy, (int)posz);
		if(bid != getBID()){ //check to see if our block types are there
			if(world.isServer){
				blocktries++;
				if(blocktries > 20){
					dumpInventory();
					this.deadflag = true;
				}
			}
		}

		
		if(!this.deadflag && world.isServer){
			//inventory
			//2 is fuel
			//1 is uncooked
			//0 is cooked
			
			//varfloats
			//4 is fuel remaining
			//6 is fuel started with
			//8 is cook time remaining
			//10 is cook time started with
			
			InventoryContainer ic = null;
			
					
			float fuel = getVarFloat(4);
			if(fuel > 0)fuel -= 0.1f;
			if(fuel <= 0){
				fuel = 0;
				ic = getInventory(2);
				if(ic != null){
					float newfuel = 0;
					if(ic.bid != 0){
						newfuel = Blocks.getBurnTime(ic.bid);
					}
					if(ic.iid != 0){
						newfuel = Items.getBurnTime(ic.iid);
					}
					if(newfuel > 0 && getInventory(1) != null){
						ic.count--;
						if(ic.count <= 0){
							ic = null;														
						}
						setInventory(2, ic);
						
						fuel = newfuel;						
					}					
				}
				setVarFloat(6, fuel);
			}
			setVarFloat(4, fuel);
			if(fuel > 0){
				ic = getInventory(1);
				if(ic != null){
					InventoryContainer ic2 = getInventory(0);
					boolean canadd = true;
					int iid = 0, bbd = 0;
					CrematingRecipe r = null;
					if(ic.bid != 0)r = Cremating.find(Blocks.getUniqueName(ic.bid));
					if(ic.iid != 0)r = Cremating.find(Items.getUniqueName(ic.iid));
					if(r != null){
						iid = Items.findByName(r.outname);
						bbd = Blocks.findByName(r.outname);
					}else{
						canadd = false;
					}						
					
					if(ic2 != null){
						if(bbd != ic2.bid || iid != ic2.iid)canadd = false;
						if(ic2.count >= ic2.getMaxStack())canadd = false;
					}
					if(canadd){
						if(getVarFloat(10) != 0){
							float te = getVarFloat(8); //time elapsed
							float tr = getVarFloat(10) - te;
							te += 0.1f;
							if(tr > 3.999f && tr < 4.001f)world.playSound("DangerZone:furnace_ding", dimension, posx, posy, posz, 0.75f, 1.0f);
							if(te >= getVarFloat(10)){
								//done!
								if(ic2 == null){
									ic2 = new InventoryContainer();
									ic2.iid = iid;
									ic2.bid = bbd;
									ic2.count = 0;
									setInventory(0, ic2);
								}
								ic2.count++;
								setInventoryChanged(0);
								
								ic.count--;
								if(ic.count <= 0){
									ic = null;
									setInventory(1, ic);
								}
								setInventoryChanged(1);
								
								setVarFloat(8, 0);
								setVarFloat(10, 0);
								
								Utils.spawnExperience(1, this.world, dimension, posx, posy, posz);
								
								List<Entity> entities = DangerZone.server.entityManager.findEntitiesInRange(2, dimension, this.posx, this.posy, this.posz);
								
								for(Entity entity : entities) {
									if(entity instanceof EntityLiving)
										entity.heal(r.ly_output/10);
										//entity.setVarFloat(4, entity.getVarFloat(4)+r.ly_output);
								}
								
								entities = DangerZone.server.entityManager.findALLEntitiesInRange(2, dimension, this.posx, this.posy, this.posz);
								
								for(Entity entity : entities) {
									if(entity instanceof EntityLyInfuser) {
										entity.setVarFloat(4, entity.getVarFloat(4)+r.ly_output);
										world.playSound("SpawnerBlocks:lyinfuser_input", entity.dimension, entity.posx, entity.posy, entity.posz, 1.55f, 1f);
									}
								}
								//player puts things in and out through a GUI, not here...
								
							}else{
								setVarFloat(8, te);
							}
						}else{
							//start the timer!							
							setVarFloat(8, 0);
							setVarFloat(10, (float)r.cooktime);
						}
					}
					if(lifetimeticker % 4 == 0) {
						Utils.spawnParticlesFromServer(world, "DangerZone:ParticleFire", world.rand.nextInt(5)+10, dimension, posx, posy-0.45, posz);
						world.playSound("SpawnerBlocks:lattenfurnace", dimension, posx, posy, posz, 0.85f, 1.0f);
					}
				}else{
					setVarFloat(8, 0);
					setVarFloat(10, 0);
				}
			}else{
				setVarFloat(4, 0);
				setVarFloat(6, 0);
				setVarFloat(8, 0);
				setVarFloat(10, 0);
			}
		}
		
		//kick entity out to other players!
		motionx = motiony = motionz = 0;
		super.update(deltaT);
	}
	
	public boolean rightClickedByPlayer(Player p, InventoryContainer ic){
		int bid = world.getblock(dimension,  (int)posx, (int)posy, (int)posz);
		if(bid != getBID() && bid != getIID()){
			if(world.isServer){
				dumpInventory();
			}
			this.deadflag = true;
			return false;
		}
		Blocks.rightClickOnBlock(getBID(), p, dimension, (int)posx, (int)posy, (int)posz);
		return false;
	}
	
	private void dumpInventory(){
		InventoryContainer ic = null;
		for(int i=0;i<50;i++){
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
}
