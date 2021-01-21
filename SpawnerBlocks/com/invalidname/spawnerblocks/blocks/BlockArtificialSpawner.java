package com.invalidname.spawnerblocks.blocks;

import java.util.List;
import java.util.ListIterator;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import com.invalidname.spawnerblocks.entities.EntityArtificialSpawner;

import dangerzone.DangerZone;
import dangerzone.Player;
import dangerzone.StitchedTexture;
import dangerzone.VBOBuffer;
import dangerzone.World;
import dangerzone.WorldRenderer;
import dangerzone.blocks.Block;
import dangerzone.entities.Entity;
import dangerzone.gui.InventoryMenus;
import dangerzone.threads.VBODataBuilderThread;

public class BlockArtificialSpawner extends Block {
	
	private static ModelBlockArtificialSpawner mbas = null;
	private String fulltexturestring = null;
	private Texture fulltexture = null;
	String critter;
	int spawntime = 100;
	int spawncount = 1;
	int eggburntime = 100;
	int esuccessrate = 50;
		
	public BlockArtificialSpawner(String n, String invtxt, String txt, int eggeficiency, int espawntime, int espawncount, int successrate) {
		super(n, invtxt);
		isSolidForRendering = false;
		isStone = true;
		//renderAllSides = true;
		breaksound = "DangerZone:stonebreak"; 
		placesound = "DangerZone:stoneplace"; 
		hitsound =   "DangerZone:stonehit";
		alwaystick = true;
		maxdamage = 300;
		mindamage = 10;
		maxstack = 8;
		hasOwnRenderer = true;
		spawntime = espawntime;
		spawncount = espawncount;
		eggburntime = eggeficiency;
		esuccessrate = successrate;
		menu = InventoryMenus.SPAWNER;
		fulltexturestring = txt;
		if(mbas == null)mbas = new ModelBlockArtificialSpawner();
	}
	
	public Texture getTexture(int side){
		if(texture == null || fulltexture == null){
			texture = initBlockTexture(texturepath);
			fulltexture = initBlockTexture(fulltexturestring);
		}
		if(side == 5)return fulltexture;
		return texture;
	}
	
	public String getStitchedTextureName(int side){
		return fulltexturestring;
	}
	
	public void renderMe(WorldRenderer wr, World w, int d, int x, int y, int z, int bid, int meta, int sides, boolean focus) {
		wr.loadtextureforblockside(5, bid, false);
		//GL11.glPushMatrix();
		GL11.glScalef(-1, -1, -1);
		GL11.glTranslatef(0,  8,  0);
		mbas.Shape1.render(1);
		mbas.Shape2.render(1);
		mbas.Shape3.render(1);
		mbas.Shape4.render(1);
		mbas.Shape5.render(1);
		mbas.Shape6.render(1);
		mbas.Shape7.render(1);
		mbas.Shape8.render(1);
		mbas.Shape9.render(1);
		mbas.Shape10.render(1);
		mbas.Shape11.render(1);
		mbas.Shape12.render(1);
		mbas.Shape13.render(1);
		mbas.Shape14.render(1);
		mbas.Shape15.render(1);
		mbas.Shape16.render(1);
		mbas.Shape17.render(1);
		mbas.Shape18.render(1);
		mbas.Shape19.render(1);
		mbas.Shape20.render(1);
		mbas.Shape21.render(1);
		mbas.Shape22.render(1);
		mbas.Shape23.render(1);
		mbas.Shape24.render(1);
		mbas.Shape25.render(1);
		mbas.Shape26.render(1);
		mbas.Shape27.render(1);
		mbas.Shape28.render(1);
		mbas.Shape29.render(1);
		mbas.Shape30.render(1);
		mbas.Shape31.render(1);
		mbas.Shape32.render(1);
		mbas.Shape33.render(1);
		mbas.Shape34.render(1);
		mbas.Shape35.render(1);
		mbas.Shape36.render(1);
		//GL11.glPopMatrix();
	}
	
	public void renderMeHeld(WorldRenderer wr, Entity e, int bid, boolean isdisplay) {
		if(e == null)return;
		wr.loadtextureforblockside(5, bid, false);
		GL11.glPushMatrix();
		GL11.glTranslatef(0,  4,  0);
		GL11.glScalef(1.5f, 1.5f, 1.5f);
		GL11.glTranslatef(0,  -8,  0);
		mbas.Shape1.render(1);
		mbas.Shape2.render(1);
		mbas.Shape3.render(1);
		mbas.Shape4.render(1);
		mbas.Shape5.render(1);
		mbas.Shape6.render(1);
		mbas.Shape7.render(1);
		mbas.Shape8.render(1);
		mbas.Shape9.render(1);
		mbas.Shape10.render(1);
		mbas.Shape11.render(1);
		mbas.Shape12.render(1);
		mbas.Shape13.render(1);
		mbas.Shape14.render(1);
		mbas.Shape15.render(1);
		mbas.Shape16.render(1);
		mbas.Shape17.render(1);
		mbas.Shape18.render(1);
		mbas.Shape19.render(1);
		mbas.Shape20.render(1);
		mbas.Shape21.render(1);
		mbas.Shape22.render(1);
		mbas.Shape23.render(1);
		mbas.Shape24.render(1);
		mbas.Shape25.render(1);
		mbas.Shape26.render(1);
		mbas.Shape27.render(1);
		mbas.Shape28.render(1);
		mbas.Shape29.render(1);
		mbas.Shape30.render(1);
		mbas.Shape31.render(1);
		mbas.Shape32.render(1);
		mbas.Shape33.render(1);
		mbas.Shape34.render(1);
		mbas.Shape35.render(1);
		mbas.Shape36.render(1);
		//GL11.glScalef(0.5f, 0.5f, 0.5f);
		GL11.glPopMatrix();
	}
	
	public void renderMeToVBO(long chunkvbos[], WorldRenderer wr, World w, int d, int x, int y, int z, int bid, int meta, int sides, boolean focus, int xo, int yo, int zo) {
		VBOBuffer v = null;
		StitchedTexture st = null;

		st = VBODataBuilderThread.findVBOtextureforblockside(5, bid);
		if(st == null)return;
		v = VBODataBuilderThread.findOrMakeVBOForTexture(chunkvbos, st, isTranslucent);
		if(v == null)return;
		
		mbas.Shape1.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape2.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape3.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape4.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape5.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape6.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape7.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape8.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape9.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape10.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape11.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape12.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape13.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape14.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape15.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape16.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape17.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape18.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape19.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape20.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape21.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape22.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape23.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape24.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape25.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape26.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape27.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape28.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape29.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape30.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape31.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape32.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape33.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape34.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape35.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
		mbas.Shape36.renderToVBO(v, st, VBODataBuilderThread.cbr, VBODataBuilderThread.cbg, VBODataBuilderThread.cbb, xo, yo, zo, 0);
	}
	
	public void tickMe(World w, int d, int x, int y, int z) {
		if(!w.isServer)return;
		EntityArtificialSpawner ec = findOurEntity(w, d, x, y, z);
		
		if(ec == null) { //where did our entity go???
			//make one!
			Entity eb = w.createEntityByName("SpawnerBlocks:EntityArtificialSpawner", d, ((double)x)+0.5f, ((double)y)+0.05f, ((double)z)+0.5f);
			if(eb != null) {
				eb.setBID(this.blockID);
				eb.setVarInt(31, spawntime);
				eb.setVarInt(32, spawncount);
				eb.setVarInt(33, eggburntime);
				eb.setVarInt(34, esuccessrate);
				eb.init();
				w.spawnEntityInWorld(eb);
			}
		}
	}
	
	public void onBlockPlaced(World w, int dimension, int x, int y, int z) {
		if(w.isServer) {
			//System.out.printf("onBlockPlaced spawning new spawner entity\n");
			Entity eb = w.createEntityByName("SpawnerBlocks:EntityArtificialSpawner", dimension, ((double)x)+0.5f, ((double)y)+0.05f, ((double)z)+0.5f);
			if(eb != null) {
				eb.setBID(this.blockID);
				eb.setVarInt(31, spawntime);
				eb.setVarInt(32, spawncount);
				eb.setVarInt(33, eggburntime);
				eb.setVarInt(34, esuccessrate);
				eb.init();
				w.spawnEntityInWorld(eb);
			}
		}
	}
	
	public boolean rightClickOnBlock(Player p, int dimension, int x, int y, int z) {
		
		if(p != null && p.world.isServer) {
			EntityArtificialSpawner eas = findOurEntity( p.world, dimension, x, y, z);
			if(eas != null) {
				if(eas.rightClickedByPlayer(p, p.getHotbar(p.gethotbarindex())))
					return false;
			}
		}
		return true; //return TRUE if normal block processing should continue (block placement)
	}
	
	public EntityArtificialSpawner findOurEntity(World w, int d, int x, int y, int z) {
		List<Entity> nearby_list = null;
		EntityArtificialSpawner ec = null;
		//Find our chest entity!
		nearby_list = DangerZone.server.entityManager.findALLEntitiesInRange(2, d, x, y, z);
		if(nearby_list != null) {
			if(!nearby_list.isEmpty()) {
				Entity e = null;
				ListIterator<Entity> li;
				li = nearby_list.listIterator();
				while(li.hasNext()) {
					e = (Entity)li.next();
					if(e instanceof EntityArtificialSpawner) { 						
						if((int)e.posx == x && (int)e.posy == y && (int)e.posz == z) {
							ec = (EntityArtificialSpawner)e;
							return ec;
						}
					}
				}								
			}
		}
		return null;
	}
}
