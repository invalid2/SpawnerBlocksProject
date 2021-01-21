package com.invalidname.spawnerblocks.items;

import org.lwjgl.opengl.GL11;

import dangerzone.DangerZone;
import dangerzone.InventoryContainer;
import dangerzone.Player;
import dangerzone.WorldRenderer;
import dangerzone.blocks.Blocks;
import dangerzone.entities.Entity;
import dangerzone.items.Item;

public class Wayfinder extends Item {
	
	int waymarkid = Blocks.findByName("SpawnerBlocks:Waymark");
	float target_angle = DangerZone.rand.nextInt(360);
	float rot_speed = 5;
	float rotate;
	
	public Wayfinder(String n) {
		super(n, "spawnerblocksres/items/"+"wayfinder.png");
		maxstack = 1;
		//flopped = true;
		
		maxuses = 0;
	}
	
	public void inUseTick(Entity e, InventoryContainer ic, int invindex) {
		int x = 0;
		int z = 0;
		int y = 0;
		int d = 0;
		
		x = Math.abs(ic.getAttribute(40));
		y = Math.abs(ic.getAttribute(41));
		z = Math.abs(ic.getAttribute(42));
		d = Math.abs(ic.getAttribute(43));
		if(e.world.getblock(d, x, y, z) == Blocks.findByName("SpawnerBlocks:Waymark")) {
			ic.addAttribute(44, 10);
		} else {
			if(ic.getAttribute(44) > -9) {
				ic.addAttribute(44, -1);
			}
		}
		
		//System.out.println("X:"+x+", Y:"+y+" Z:"+z+", d:"+d);
		//System.out.println(""+ic.getAttribute(44)+", dp:"+e.dimension+", block:"+DangerZone.world.getblock(d, x, y, z));
	}
	
	public boolean rightClickOnBlock(Player p, int dimension, int x, int y, int z, int side) {
		//if(!p.world.isServer)return false;
		
		int id = p.world.getblock(dimension, x, y, z);
		
		if(Blocks.findByName("SpawnerBlocks:Waymark") == id) {
			
			waymarkid = Blocks.findByName("SpawnerBlocks:Waymark");
			
			//Get the currentky held item
			InventoryContainer ic = p.getHotbar(p.gethotbarindex());
			
			//Set the attributes values to 0
			for(int i = (int) Math.ceil(ic.getAttribute(40)/10); i < 10; i++) {
				ic.addAttribute(40, -ic.getAttribute(40) );
			}
			for(int j = (int) Math.ceil(ic.getAttribute(41)/10); j < 10; j++) {
				ic.addAttribute(41, -ic.getAttribute(41) );
			}
			for(int k = (int) Math.ceil(ic.getAttribute(42)/10); k < 10; k++) {
				ic.addAttribute(42, -ic.getAttribute(42) );
			}
			for(int idd = ic.getAttribute(43); idd < 10; idd++) {
				ic.addAttribute(43, -ic.getAttribute(43) );
			}
			//System.out.println(ic.getAttribute(43));
			
			//Set pos as attributes, negative due to maximum value of 10!
			ic.addAttribute(40, -x);
			ic.addAttribute(41, -y);
			ic.addAttribute(42, -z);
			ic.addAttribute(43, -dimension);
			//System.out.println("X:"+ic.getAttribute(40)+", Y:"+ic.getAttribute(41)+" Z:"+ic.getAttribute(42)+", d:"+ic.getAttribute(43));
			//System.out.println("z:"+ic.getAttribute(60));
			return false;
		}
		
		return false; //return TRUE if your item did something and should be deleted/decremented
	}
	
	public void renderMeHeld(WorldRenderer wr, Entity e, InventoryContainer ic, boolean isdisplay) {
		//System.out.println(ic+": "+ic.getAttribute(40));
		int x = 0;
		int z = 0;
		int d = 0;
		
		if(ic.getAttribute(40) == 0)
			GL11.glScalef(-1, 1, -1);
		
		GL11.glPushMatrix();
		if(ic.getAttribute(40) != 0) {
			
			
			
			x = -ic.getAttribute(40);
			z = -ic.getAttribute(42);
			d = -ic.getAttribute(43);
			
			if(e.dimension == d && ic.getAttribute(44) >= 0) {
				float rot = 180-(-(float) Math.toDegrees(Math.atan2(e.posz-z-0.5, e.posx-x-0.5 ) )+e.rotation_yaw);
				//System.out.println(""+rot);
				//System.out.println(e.rotation_yaw);
				GL11.glRotatef(-rot, 0, 0, 1);
			} else if(ic.getAttribute(44) >= 0) {
				
				if(DangerZone.rand.nextInt(32) == 0) {
					rot_speed = DangerZone.rand.nextInt(11);
					if(DangerZone.rand.nextInt(2) == 0)
						rot_speed = -rot_speed;
				}
				
				rotate += rot_speed;
				
				GL11.glRotatef(rotate, 0, 0, 1);
			}
		}
		
		super.renderMeHeld(wr, e, ic, isdisplay);
		GL11.glPopMatrix();
	}

}
