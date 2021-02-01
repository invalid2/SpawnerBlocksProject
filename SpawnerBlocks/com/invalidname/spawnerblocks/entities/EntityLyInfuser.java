package com.invalidname.spawnerblocks.entities;

import com.invalidname.spawnerblocks.LyInfusing;
import com.invalidname.spawnerblocks.LyInfusingRecipe;

import dangerzone.DangerZone;
import dangerzone.InventoryContainer;
import dangerzone.Player;
import dangerzone.Utils;
import dangerzone.World;
import dangerzone.blocks.Blocks;
import dangerzone.entities.Entity;
import dangerzone.entities.EntityBlockItem;
import dangerzone.items.Items;

public class EntityLyInfuser extends Entity {

	public int blocktries = 0;

	public EntityLyInfuser(World w) {
		super(w);
		uniquename = "SpawnerBlocks:EntityLyInfuser";
		ignoreCollisions = true;
		width = 0.01f;
		height = 0.01f;
		setVarFloat(4, 0);
		setVarFloat(5, 0);
		setVarFloat(6, 0.3f);
		setVarFloat(7, 0);
		has_inventory = true;
		maxrenderdist = 64; //it is invisible, so just update when the player gets near
	}
	
	public void update(float deltaT){
		int bid = world.getblock(dimension,  (int)posx, (int)posy, (int)posz);
		
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
			//1 is uninfused
			//0 is result
			
			//varfloats
			//4 is ly quantity
			//5 is minimum ly quantity necessary
			//6 is ly intake
			//7 is ly infused
			
			InventoryContainer ic = null;
			
					
			float ly_quant = getVarFloat(4);
			if(ly_quant > 0)ly_quant -= 0.01f;
			if(ly_quant <= 0){
				ly_quant = 0;
			}
			setVarFloat(4, ly_quant);
			if(ly_quant > 0) {
				//System.out.println(ly_quant);
				ic = getInventory(1);
				if(ic != null) {
					InventoryContainer ic2 = getInventory(0);
					boolean canadd = true;
					int iid = 0, bbd = 0;
					LyInfusingRecipe r = null;
					if(ic.bid != 0)r = LyInfusing.find(Blocks.getUniqueName(ic.bid));
					if(ic.iid != 0)r = LyInfusing.find(Items.getUniqueName(ic.iid));
					if(r != null) {
						iid = Items.findByName(r.outname);
						bbd = Blocks.findByName(r.outname);
					}else{
						canadd = false;
					}						
					
					if(ic2 != null) {
						if(bbd != ic2.bid || iid != ic2.iid)canadd = false;
						if(ic2.count >= ic2.getMaxStack())canadd = false;
					}
					if(canadd) {
						if(getVarFloat(5) != 0) {
							float lyavailable = getVarFloat(4);
							float lynecessary = getVarFloat(5);
							float lytaken =  getVarFloat(7);
							
							if(lytaken >= lynecessary) {
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
								if(ic.count <= 0) {
									ic = null;
									setInventory(1, ic);
								}
								setInventoryChanged(1);
								
								setVarFloat(7, 0);
								
								world.playSound("SpawnerBlocks:lyinfuser_output", dimension, posx, posy, posz, 1.15f, 1f);
								Utils.spawnExperience(1, this.world, dimension, posx, posy, posz);
								
								//player puts things in and out through a GUI, not here...
								
							} else {
								//Decrease available ly pool
								setVarFloat(4, lyavailable-getVarFloat(6));
								
								//Add to infused ly pool
								setVarFloat(7, lytaken+getVarFloat(6));
							}
						} else {
							//start the timer!							
							setVarFloat(5, r.ly_input);
							setVarFloat(7, 0);
						}
					}
				} else {
					setVarFloat(5, 0);
					setVarFloat(7, 0);
				}
			} else {
				setVarFloat(4, 0);
				setVarFloat(5, 0);
				setVarFloat(7, 0);
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
		Blocks.rightClickOnBlock(getBID(), p, dimension, (int)posx, (int)posy, (int)posz, 0);
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
