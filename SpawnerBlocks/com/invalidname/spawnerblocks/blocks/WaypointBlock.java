package com.invalidname.spawnerblocks.blocks;

import org.newdawn.slick.opengl.Texture;

import dangerzone.DangerZone;
import dangerzone.StitchedTexture;
import dangerzone.blocks.Block;

public class WaypointBlock extends Block{
	
	String topname,bottomname,sidesname;
	Texture ttop = null;
	Texture tbottom = null;
	Texture tsides = null;
	StitchedTexture stop = new StitchedTexture();
	StitchedTexture sbottom = new StitchedTexture();
	StitchedTexture ssides = new StitchedTexture();
	
	public WaypointBlock(String n) {
		super(n, "");
		maxstack = 1;
		topname = "spawnerblocksres/blocks/waymark_top.png";
		bottomname = "spawnerblocksres/blocks/waymark_bottom.png";
		sidesname = "spawnerblocksres/blocks/waymark_sides.png";
		uniquename = n;
		breaksound = "DangerZone:stonebreak"; 
		placesound = "DangerZone:stoneplace"; 
		hitsound =   "DangerZone:stonehit";
		
	}
	
	public String getStepSound(){
		int i = DangerZone.rand.nextInt(3);
		if(i == 0)return "DangerZone:stone1";
		if(i == 1)return "DangerZone:stone2";
		return "DangerZone:stone3";
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
		if(side == 0)return ttop;
		if(side == 5)return tbottom;
		
		return tsides;
	}
	
	public StitchedTexture getStitchedTexture(int side){	
		if(side == 0)return stop;
		if(side == 5)return sbottom;
		return ssides;
	}
	
	public String getStitchedTextureName(int side){
		if(side == 0)return topname;
		if(side == 5)return bottomname;
		return sidesname;
	}
}
