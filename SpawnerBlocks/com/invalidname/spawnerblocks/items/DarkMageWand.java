package com.invalidname.spawnerblocks.items;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import dangerzone.DamageTypes;
import dangerzone.DangerZone;
import dangerzone.GameModes;
import dangerzone.InventoryContainer;
import dangerzone.Player;
import dangerzone.TextureMapper;
import dangerzone.WorldRenderer;
import dangerzone.entities.Entity;
import dangerzone.entities.EntityExtendedRangeDamage;
import dangerzone.gui.InventoryMenus;
import dangerzone.items.Item;

public class DarkMageWand extends Item {
	
	String modeltxtpath;
	Texture texturebig = null;
	ModelDarkMageWand ma = null;
	
	//Analogous to the real holdcount
	float holdcount = getfullholdcount();
	
	//currentuses = numuses
	int numuses = 0;
	
	public DarkMageWand(String n, String txt, String modeltxt, int attackdamage) {
		super(n, txt);
		maxstack = 1;
		maxuses = 31;
		modeltxtpath = modeltxt;
		menu = InventoryMenus.HARDWARE;
		attackstrength = attackdamage;
		brightness = 0.5f;
	}
	
	public void renderMeHeld(WorldRenderer wr, Entity e, InventoryContainer ic, boolean isdisplay) {
		//texture is already auto-loaded for us
		if(texturebig == null) {
			if(modeltxtpath == null) {
				TextureMapper.getTexture("spawnerblocksres/items/"+"darkmagewand_texture.png");
			} else {
				texturebig = TextureMapper.getTexture(modeltxtpath);
			}
			
		}
		DangerZone.wr.loadtexture(texturebig);
		if(ma == null) {
			ma = new ModelDarkMageWand();
		}
		GL11.glRotatef(90, 0, 1, 0);
		GL11.glRotatef(-15, 0, 0, 1);
		GL11.glRotatef(135, 1, 0, 0);
		GL11.glTranslatef(-2, -10, 1);
		if(ma != null)ma.render();
		
	}
	
	public void inUseTick(Entity e, InventoryContainer ic, int invindex) {
		
		//This makes numuses equal to the uses we had last time the game opened
		if(numuses == 0 && ic.currentuses != 0)
			this.numuses = ic.currentuses;
		
		//Manually increment currentuses
		ic.currentuses = this.numuses;
		//System.out.println(ic.getAttribute(30)+"   "+ic.currentuses+"    "+numuses);
		if(DangerZone.world == null) {
			holdcount += 6;
		} else {
			holdcount++;
		}
		
	}
	
	public boolean onLeftClick(Entity holder, Entity clickedon, InventoryContainer ic) {
		if(holder == null)return false;
		if(!holder.world.isServer)return true;
		//Check if smaller to avoid it breaking
		if(this.maxuses-ic.currentuses <= 1)return false;
		
		//Checks if the player pressed left for long enough
		if(holdcount >= getfullholdcount()) {
			
			//Reset houldcount so that the player has to wait again
			holdcount = 0;
			//System.out.println("Shoot!");
			//Standard extended range use
			EntityExtendedRangeDamage e = (EntityExtendedRangeDamage)holder.world.createEntityByName("DangerZone:ExtendedRangeDamage", 
				holder.dimension, 
				holder.posx+(float)Math.sin(Math.toRadians(holder.rotation_yaw_head))*(holder.getWidth()+1)*(float)Math.cos(Math.toRadians(holder.rotation_pitch_head)),
				holder.posy+(holder.getHeight()*9/10) - (float)Math.sin(Math.toRadians(holder.rotation_pitch_head))*(holder.getWidth()+1),
				holder.posz+(float)Math.cos(Math.toRadians(holder.rotation_yaw_head))*(holder.getWidth()+1)*(float)Math.cos(Math.toRadians(holder.rotation_pitch_head)));
			if(e != null) {
				e.init();
				e.setAttackDamage(attackstrength); //damage amount
				e.setDamageType(DamageTypes.PROJECTILE); //damage type
				e.setExplosivePower(3); //explosive power
				e.setRange(60); //range
				e.thrower = holder;
				e.setDirectionAndVelocity(
					(float)Math.sin(Math.toRadians(holder.rotation_yaw_head))*(float)Math.cos(Math.toRadians(holder.rotation_pitch_head)), 
					-(float)Math.sin(Math.toRadians(holder.rotation_pitch_head)),
					(float)Math.cos(Math.toRadians(holder.rotation_yaw_head))*(float)Math.cos(Math.toRadians(holder.rotation_pitch_head)),
					15f, 0.01f);
				
				holder.world.spawnEntityInWorld(e);
				//Play shooting sound
				holder.world.playSound("SpawnerBlocks:darkmagewand", holder.dimension, holder.posx, holder.posy+holder.getHeight(), holder.posz, 1.15f, 1.0f+((holder.world.rand.nextFloat()-holder.world.rand.nextFloat())*0.2f));
				
				ic.addAttribute(30, -1);
				//Increments numuses
				
				if( ((Player) holder).getGameMode() == GameModes.SURVIVAL)
					numuses++;
				
				return true;
			}
		}
		
		return false; //continue with normal left click logic, else it is handled special here
	}
	
	public void onCrafted(Player p, InventoryContainer ic) {
		numuses = 0;
	}
	
	public float getfullholdcount() {
		//for display so we can scale, stretch, fill something, whatever, to show progress.
		return 95;
	}
}
