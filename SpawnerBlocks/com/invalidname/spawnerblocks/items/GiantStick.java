package com.invalidname.spawnerblocks.items;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.newdawn.slick.opengl.Texture;

import dangerzone.DangerZone;
import dangerzone.InventoryContainer;
import dangerzone.TextureMapper;
import dangerzone.WorldRenderer;
import dangerzone.entities.Entity;
import dangerzone.items.Item;

public class GiantStick extends Item {
	
	Texture texturebig = null;
	ModelGiantStick ma = null;

	public GiantStick(String n, String txt) {
		super(n, txt);
		
	}
	
	public void renderMeHeld(WorldRenderer wr, Entity e, InventoryContainer ic, boolean isdisplay){
		//texture is already auto-loaded for us
				if(texturebig == null){
					texturebig = TextureMapper.getTexture("spawnerblocksres/items/"+"giantstick_texture.png");
				}
				DangerZone.wr.loadtexture(texturebig);
				if(ma == null){
					ma = new ModelGiantStick();
				}
				GL11.glRotatef(90, 0, 1, 0);
				GL11.glRotatef(-15, 0, 0, 1);
				GL11.glRotatef(135, 1, 0, 0);
				GL11.glTranslatef(-2, -10, 1);
				if(ma != null)ma.render();
	}

}
