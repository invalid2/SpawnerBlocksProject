package com.invalidname.spawnerblocks.items;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import dangerzone.DangerZone;
import dangerzone.ModelRenderer;
import dangerzone.TextureMapper;
import dangerzone.entities.Entity;
import dangerzone.items.Item;

public class AnteaterAmor extends Item {
	
	public float protection = 0.0f;
	public Texture armortexture = null;
	public String armortexturepath = null;
	public AnteaterAmor(String n, String txt, String armortxt, int durability, float prot) {
		super(n, txt);
		maxuses = durability;
		armortexturepath = armortxt;
		protection = prot;
	}
	
	
	public Texture getArmorTexture() {
		if(armortexture == null)
			armortexture = TextureMapper.getTexture(armortexturepath);
		
		return armortexture;
	}
	
	
	public void drawArmor(Entity ent, ModelRenderer armor, float deathfactor){
		  DangerZone.wr.loadtexture(getArmorTexture());
		  GL11.glTranslatef(0f, -3.0f, 0f); //translate down a little because we will scale differently...
		  GL11.glScalef(1.125f, 1.125f, 1.125f); //scale up
		  armor.rotationPointY = 0.5f; //rotate off center a little
		  armor.render(deathfactor); //texture is in same place in the file!
		  armor.rotationPointY = 0.0f; //restore this!!!!!
		  GL11.glScalef(0.88889f, 0.88889f, 0.88889f); //unscale
		  GL11.glTranslatef(0f, 3.0f, 0f); //translate back!!!
	}
}
