package com.invalidname.spawnerblocks.items;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import dangerzone.DamageTypes;
import dangerzone.DangerZone;
import dangerzone.InventoryContainer;
import dangerzone.TextureMapper;
import dangerzone.WorldRenderer;
import dangerzone.entities.Entity;
import dangerzone.entities.EntityExtendedRangeDamage;
import dangerzone.gui.InventoryMenus;
import dangerzone.items.Item;

public class GiantSword extends Item {
	
	Texture texturebig = null;
	ModelGiantSword ma = null;
	String modeltxtpath = null;
	
	public GiantSword(String n, String txt, String modeltxt, int uses, int attack) {
		super(n, txt);
		modeltxtpath = modeltxt;
		maxuses = uses;
		maxstack = 1;
		attackstrength = attack;
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
			ma = new ModelGiantSword();
		}
		GL11.glRotatef(15, 0, 1, 0);
		GL11.glRotatef(-135, 0, 0, 1);
		GL11.glRotatef(22.5f, 1, 0, 0);
		GL11.glTranslatef(0, -10, 2);
		GL11.glScalef(1.2f, 1.2f, 1.2f);
		if(ma != null)ma.render();
		
	}
	
	public boolean onLeftClick(Entity holder, Entity clickedon, InventoryContainer ic){
		if(holder == null)return false;
		if(!holder.world.isServer)return true;
		
		EntityExtendedRangeDamage e = (EntityExtendedRangeDamage)holder.world.createEntityByName("DangerZone:ExtendedRangeDamage", 
				holder.dimension, 
				holder.posx+(float)Math.sin(Math.toRadians(holder.rotation_yaw_head))*(holder.getWidth()+1)*(float)Math.cos(Math.toRadians(holder.rotation_pitch_head)),
				holder.posy+(holder.getHeight()*9/10) - (float)Math.sin(Math.toRadians(holder.rotation_pitch_head))*(holder.getWidth()+1),
				holder.posz+(float)Math.cos(Math.toRadians(holder.rotation_yaw_head))*(holder.getWidth()+1)*(float)Math.cos(Math.toRadians(holder.rotation_pitch_head)));
		if(e != null){
			e.init();
			e.setAttackDamage(attackstrength); //damage amount
			e.setDamageType(DamageTypes.SWORD); //damage type
			e.setExplosivePower(0); //explosive power
			e.setRange(10); //range
			e.thrower = holder;
			e.setDirectionAndVelocity(
					(float)Math.sin(Math.toRadians(holder.rotation_yaw_head))*(float)Math.cos(Math.toRadians(holder.rotation_pitch_head)), 
					-(float)Math.sin(Math.toRadians(holder.rotation_pitch_head)),
					(float)Math.cos(Math.toRadians(holder.rotation_yaw_head))*(float)Math.cos(Math.toRadians(holder.rotation_pitch_head)),
					25f, 0.01f);
			
			holder.world.spawnEntityInWorld(e);
		}
		
		return true; //continue with normal left click logic, else it is handled special here
	}
}
