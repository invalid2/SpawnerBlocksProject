package com.invalidname.spawnerblocks.items;

import java.awt.event.KeyEvent;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import dangerzone.DangerZone;
import dangerzone.GameModes;
import dangerzone.InventoryContainer;
import dangerzone.Player;
import dangerzone.TextureMapper;
import dangerzone.WorldRenderer;
import dangerzone.blocks.Block;
import dangerzone.blocks.Blocks;
import dangerzone.entities.Entity;
import dangerzone.gui.InventoryMenus;
import dangerzone.items.Item;
import dangerzone.threads.Server;

public class GiantAxe extends Item {
	Texture texturebig = null;
	ModelGiantAxe ma = null;
	String modeltxtpath = null;
	String test = null;

	public GiantAxe(String n, String txt, String modeltxt, int uses, int attack, int strength) {
		super(n, txt);
		test = txt;
		modeltxtpath = modeltxt;
		maxuses = uses;
		maxstack = 1;
		attackstrength = attack;
		woodstrength = strength;
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
			ma = new ModelGiantAxe();
		}
		GL11.glRotatef(15+180, 0, 1, 0);
		GL11.glRotatef(135, 0, 0, 1);
		GL11.glRotatef(-22.5f, 1, 0, 0);
		GL11.glTranslatef(0, -10, -2);
		GL11.glScalef(1.2f, 1.2f, 1.2f);
		if(ma != null)ma.render();
		
		//System.out.println(e.getHotbar(e.gethotbarindex()).currentuses);
	}
	
	
	public void onBlockBroken(Entity ent, int dimension, int x, int y, int z, int wasbid) {
		int count = 1;
		int iy = 1;
		if(Blocks.getBlock(wasbid).isWood) {
			
			for(int ib = -1; ib < 2; ib++) {
				for(int jb = -1; jb < 2; jb++) {
					int block = ent.world.getblock(dimension, x+ib, y, z+jb);
					if(block != 0) {
						if(Blocks.getBlock(block).isWood) {
							if(ent.getHotbar(ent.gethotbarindex()).currentuses+Blocks.getBlock(block).maxdamage/woodstrength >= maxuses)break;
							
							
							if(ent.getGameMode() == GameModes.SURVIVAL)
								ent.getHotbar(ent.gethotbarindex()).currentuses += (int) Blocks.getBlock(block).maxdamage/woodstrength;
							
							Blocks.doBreakBlock(block, ent.world, dimension, x+ib, y+iy, z+jb);
							ent.world.setblock(dimension, x+ib, y, z+jb, 0);
							count++;
						}
					}
				}
			}
			while(count > 0) {
				
				count = 0;
				for(int i = -1; i < 2; i++) {
					for(int j = -1; j < 2; j++) {
						
						int block = ent.world.getblock(dimension, x+i, y+iy, z+j);
						if(block != 0) {
							//System.out.println("I:"+i+", j:"+j);
							if(Blocks.getBlock(block).isWood) {
								if(ent.getHotbar(ent.gethotbarindex()).currentuses >= maxuses)break;
								if(ent.getGameMode() == GameModes.SURVIVAL)
									ent.getHotbar(ent.gethotbarindex()).currentuses += (int) Blocks.getBlock(block).maxdamage/woodstrength;
								Blocks.doBreakBlock(block, ent.world, dimension, x+i, y+iy, z+j);
								ent.world.setblock(dimension, x+i, y+iy, z+j, 0);
								count++;
								
							}
						}
					}
				}
				
				iy++;
			}
			
		}
		
		//Blocks.doBreakBlock(wasbid, ent.world, dimension, x, y, z);
	}
}
