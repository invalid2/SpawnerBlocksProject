package com.invalidname.spawnerblocks.items;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import dangerzone.DangerZone;
import dangerzone.InventoryContainer;
import dangerzone.TextureMapper;
import dangerzone.WorldRenderer;
import dangerzone.blocks.Block;
import dangerzone.blocks.Blocks;
import dangerzone.entities.Entity;
import dangerzone.gui.InventoryMenus;
import dangerzone.items.Item;

public class GiantShovel extends Item {
	Texture texturebig = null;
	ModelGiantShovel ma = null;
	String modeltxtpath = null;

	public GiantShovel(String n, String txt, String modeltxt, int uses, int attack, int strength) {
		super(n, txt);
		modeltxtpath = modeltxt;
		maxuses = uses;
		maxstack = 1;
		attackstrength = attack;
		dirtstrength = strength;
		burntime = 70;
		menu = InventoryMenus.HARDWARE;
	}
	
	public void renderMeHeld(WorldRenderer wr, Entity e, InventoryContainer ic, boolean isdisplay) {
		//texture is already auto-loaded for us
		if(texturebig == null){
			texturebig = TextureMapper.getTexture(modeltxtpath);
		}
		DangerZone.wr.loadtexture(texturebig);
		if(ma == null){
			ma = new ModelGiantShovel();
		}
		GL11.glRotatef(15, 0, 1, 0);
		GL11.glRotatef(-135, 0, 0, 1);
		GL11.glRotatef(22.5f, 1, 0, 0);
		GL11.glTranslatef(0, -10, 2);
		GL11.glScalef(1.2f, 1.2f, 1.2f);
		if(ma != null)ma.render();
		
	}
	
	@SuppressWarnings("static-access")
	public void onBlockBroken(Entity ent, int dimension, int x, int y, int z, int wasbid) {
		int blockside = DangerZone.wr.focus_side;
		
		/*if(DangerZone.world == null) {
			//Change blockside based on the direction the player is facing, for multiplayer compability
			if((ent.rotation_yaw >= 45 && ent.rotation_yaw < 135) || (ent.rotation_yaw >= 225 && ent.rotation_yaw < 315)) {
				blockside = 3;
			} else if((ent.rotation_yaw >= 135 && ent.rotation_yaw < 225) || ent.rotation_yaw >= 315 || ent.rotation_yaw < 45) {
				blockside = 1;
			}
			if((ent.rotation_pitch_head > 40 && ent.rotation_pitch_head <= 90) || (ent.rotation_pitch_head < 330 && ent.rotation_pitch_head > 90))
				blockside = 0;
		}*/
		
		
		//System.out.println("side: "+blockside+", pitch:"+ent.rotation_pitch_head);
		
		
		//Breaks the blocks on a 3X3 area based on which side the block was broken
		if(Blocks.getBlock(wasbid).isDirt) {
			if(blockside == 2 || blockside == 1) {
				for(int i = -1; i < 2; i++) {
					for(int j = -1; j < 2; j++) {
						// 
						Block block = Blocks.getBlock(ent.world.getblock(dimension, x+i, y+j, z));
						if(block != null) {
							if(block.isDirt) {
								Blocks.doBreakBlock(block.blockID, ent.world, dimension, x+i, y+j, z);
								ent.world.setblock(dimension, x+i, y+j, z, 0);
							}
						}
					}
				}
			} else if(blockside == 3 || blockside == 4) {
				for(int i = -1; i < 2; i++) {
					for(int j = -1; j < 2; j++) {
						Block block = Blocks.getBlock(ent.world.getblock(dimension, x, y+j, z+i));
						if(block != null) {
							if(block.isDirt) {
								Blocks.doBreakBlock(block.blockID, ent.world, dimension, x, y+j, z+i);
								ent.world.setblock(dimension, x, y+j, z+i, 0);
							}
						}
					}
				}
			} else if(blockside == 0 || blockside == 5) {
				for(int i = -1; i < 2; i++) {
					for(int j = -1; j < 2; j++) {
						Block block = Blocks.getBlock(ent.world.getblock(dimension, x+i, y, z+j));
						if(block != null) {
							if(block.isDirt) {
								Blocks.doBreakBlock(block.blockID, ent.world, dimension, x+i, y, z+j);
								ent.world.setblock(dimension, x+i, y, z+j, 0);
							}
						}
					}
				}
			}
		}
		
		//Blocks.doBreakBlock(wasbid, ent.world, dimension, x, y, z);
	}
}
