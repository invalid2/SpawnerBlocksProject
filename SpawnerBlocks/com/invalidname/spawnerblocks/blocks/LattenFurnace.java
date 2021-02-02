package com.invalidname.spawnerblocks.blocks;

import java.util.List;
import java.util.ListIterator;

import org.newdawn.slick.opengl.Texture;

import com.invalidname.spawnerblocks.SpawnerBlocksMain;
import com.invalidname.spawnerblocks.entities.EntityLattenFurnace;
import com.invalidname.spawnerblocks.gui.PlayerLattenFurnaceGUI;

import dangerzone.DangerZone;
import dangerzone.Player;
import dangerzone.StitchedTexture;
import dangerzone.World;
import dangerzone.blocks.Block;
import dangerzone.entities.Entity;

public class LattenFurnace extends Block {
	Texture ttop = null;
	Texture tbottom = null;
	Texture tsides = null;
	Texture tfront = null;
	String topname;
	String bottomname;
	String sidesname;
	String frontname;
	StitchedTexture sttop = new StitchedTexture();
	StitchedTexture stbottom = new StitchedTexture();
	StitchedTexture stsides = new StitchedTexture();
	StitchedTexture stfront = new StitchedTexture();
	
	PlayerLattenFurnaceGUI lattenfurnacegui = new PlayerLattenFurnaceGUI();
	
	public LattenFurnace(String n) {
		super(n, "");
		topname = "spawnerblocksres/blocks/"+"lattenfurnace_top.png";
		bottomname = "res/blocks/furnace_bottom.png";
		sidesname = "spawnerblocksres/blocks/"+"lattenfurnace_sides.png";
		frontname = "spawnerblocksres/blocks/"+"lattenfurnace_front.png";
		maxstack = 8;
		isStone = true;
		hasFront = true;
		renderAllSides = true;
		renderSmaller = true;
		isSolidForRendering = false;
	}
	
	
	//Player right-clicked on this block
	public boolean rightClickOnBlock(Player p, int dimension, int x, int y, int z){
		if(p == null || p.world.isServer)return false;
		

		List<Entity> nearby_list = null;
		EntityLattenFurnace ec = null;
		//Find our Furnace entity!
		nearby_list = DangerZone.entityManager.findEntitiesInRange(2, dimension, x, y, z);
		if(nearby_list != null){
			if(!nearby_list.isEmpty()){
				Entity e = null;
				ListIterator<Entity> li;
				li = nearby_list.listIterator();
				while(li.hasNext()){
					e = (Entity)li.next();
					if(e instanceof EntityLattenFurnace){ 						
						if((int)e.posx == x && (int)e.posy == y && (int)e.posz == z){
							ec = (EntityLattenFurnace)e;
							break;
						}
						ec = null;
					}
				}								
			}			
		}
		if(ec == null){ //where did our entity go???
			//System.out.printf("spawning new Furnace entity\n");
			Entity eb = p.world.createEntityByName("SpawnerBlocks:EntityLattenFurnace", dimension, ((double)x)+0.5f, ((double)y)+0.05f, ((double)z)+0.5f);
			if(eb != null){
				eb.init();
				eb.setBID(SpawnerBlocksMain.lattenfurnace.blockID);
				p.world.spawnEntityInWorld(eb);
			}
			return false; //must click again... 
		}
		
		if(!p.world.isServer){
			lattenfurnacegui.ec = ec;
			//System.out.printf("Furnace entity ID = %d\n", ec.entityID);
			DangerZone.setActiveGui(lattenfurnacegui);
		}
		
		return false; //return FALSE because we kicked off a GUI! DO NOT PLACE A BLOCK!
	}
	
	public void onBlockPlaced(World w, int dimension, int x, int y, int z){
		if(w.isServer){
			//System.out.printf("onBlockPlaced spawning new Furnace entity\n");
			Entity eb = w.createEntityByName("SpawnerBlocks:EntityLattenFurnace", dimension, ((double)x)+0.5f, ((double)y)+0.05f, ((double)z)+0.5f);
			if(eb != null){
				eb.init();
				eb.setBID(SpawnerBlocksMain.lattenfurnace.blockID);
				w.spawnEntityInWorld(eb);
			}
		}
	}
	
	//side 0 = top
	//side 1 = front
	//side 2 = back
	//side 3 = left
	//side 4 = right
	//side 5 = bottom
	public Texture getTexture(int side){

		if(ttop == null){
			ttop = initBlockTexture(topname);
		}
		if(tbottom == null){
			tbottom = initBlockTexture(bottomname);
		}
		if(tsides == null){
			tsides = initBlockTexture(sidesname);
		}
		if(tfront == null){
			tfront = initBlockTexture(frontname);
		}
		
		if(side == 0)return ttop;
		if(side == 5)return tbottom;
		if(side == 3)return tsides;
		if(side == 4)return tsides;
		if(side == 1)return tfront;
		if(side == 2)return tsides;
		return null;
	}
	
	public StitchedTexture getStitchedTexture(int side){	
		if(side == 0)return sttop;
		if(side == 5)return stbottom;
		if(side == 3)return stsides;
		if(side == 4)return stsides;
		if(side == 1)return stfront;
		return stsides;
	}
	
	public String getStitchedTextureName(int side){
		if(side == 0)return topname;
		if(side == 5)return bottomname;
		if(side == 3)return sidesname;
		if(side == 4)return sidesname;
		if(side == 1)return frontname;
		return sidesname;
	}
	
}
