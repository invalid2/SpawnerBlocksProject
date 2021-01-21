package com.invalidname.spawnerblocks.blocks;

import java.util.List;
import java.util.ListIterator;

import com.invalidname.spawnerblocks.SpawnerBlocksMain;
import com.invalidname.spawnerblocks.entities.EntityLyInfuser;
import com.invalidname.spawnerblocks.gui.PlayerLyInfuserGUI;

import dangerzone.DangerZone;
import dangerzone.Player;
import dangerzone.World;
import dangerzone.entities.Entity;

public class LyInfuser extends LyMachine {
	
	public PlayerLyInfuserGUI lyinfusergui = new PlayerLyInfuserGUI();
	
	public LyInfuser(String n, String txt) {
		super(n, txt);
		maxstack = 16;
		isStone = true;
		renderAllSides = true;
		renderSmaller = true;
		isSolidForRendering = false;
	}
	
	public void takeInput(float num, int d, int x, int y, int z) {
		List<Entity> nearby_list = null;
		//Find our Furnace entity!
		nearby_list = DangerZone.entityManager.findEntitiesInRange(2, d, x, y, z);
		if(nearby_list != null){
			if(!nearby_list.isEmpty()){
				Entity e = null;
				ListIterator<Entity> li;
				li = nearby_list.listIterator();
				while(li.hasNext()){
					e = (Entity)li.next();
					if(e instanceof EntityLyInfuser){ 						
						if((int)e.posx == x && (int)e.posy == y && (int)e.posz == z){
							e.setVarFloat(4, e.getVarFloat(4)+num);
							
							break;
						}
					}
				}								
			}			
		}
	}
	
	//Player right-clicked on this block
		public boolean rightClickOnBlock(Player p, int dimension, int x, int y, int z){
			if(p == null || p.world.isServer)return false;
			

			List<Entity> nearby_list = null;
			EntityLyInfuser ec = null;
			//Find our Furnace entity!
			nearby_list = DangerZone.entityManager.findEntitiesInRange(2, dimension, x, y, z);
			if(nearby_list != null){
				if(!nearby_list.isEmpty()){
					Entity e = null;
					ListIterator<Entity> li;
					li = nearby_list.listIterator();
					while(li.hasNext()){
						e = (Entity)li.next();
						if(e instanceof EntityLyInfuser){ 						
							if((int)e.posx == x && (int)e.posy == y && (int)e.posz == z){
								ec = (EntityLyInfuser)e;
								break;
							}
							ec = null;
						}
					}								
				}			
			}
			if(ec == null){ //where did our entity go???
				//System.out.printf("spawning new Furnace entity\n");
				Entity eb = DangerZone.server_world.createEntityByName("SpawnerBlocks:EntityLyInfuser", dimension, ((double)x)+0.5f, ((double)y)+0.05f, ((double)z)+0.5f);
				if(eb != null){
					eb.init();
					eb.setBID(SpawnerBlocksMain.lyinfuser.blockID);
					DangerZone.server_world.spawnEntityInWorld(eb);
				}
				return false; //must click again... 
			}
			
			if(!p.world.isServer){
				lyinfusergui.ec = ec;
				//System.out.printf("Furnace entity ID = %d\n", ec.entityID);
				DangerZone.setActiveGui(lyinfusergui);
			}
			
			return false; //return FALSE because we kicked off a GUI! DO NOT PLACE A BLOCK!
		}
		
		public void onBlockPlaced(World w, int dimension, int x, int y, int z){
			if(w.isServer){
				//System.out.printf("onBlockPlaced spawning new Furnace entity\n");
				Entity eb = w.createEntityByName("SpawnerBlocks:EntityLyInfuser", dimension, ((double)x)+0.5f, ((double)y)+0.05f, ((double)z)+0.5f);
				if(eb != null){
					eb.init();
					eb.setBID(SpawnerBlocksMain.lyinfuser.blockID);
					w.spawnEntityInWorld(eb);
				}
			}
		}
}
