package com.invalidname.spawnerblocks.biomes;

import dangerzone.Chunk;
import dangerzone.Fastmath;
import dangerzone.Ores;
import dangerzone.World;
import dangerzone.biomes.Biome;
import dangerzone.biomes.Trees;
import dangerzone.blocks.Blocks;

public class SkyCubesBiome extends Biome {

	Trees tr;
	
	public SkyCubesBiome(String s){
		super(s);
		tr = new Trees();
	}
	
	/*
	 * USE ONLY CHUNK.set/get functions!!! Otherwise infinite recursion may occur!
	 * THIS ROUTINE IS JUST FOR SUPPLYING IN-CHUNK DATA, LIKE TERRAIN!!!
	 * THINK INSIDE THE CHUNK... er... BOX!
	 * Most parameters passed in for reference only. 
	 * Mostly what you need here is just the chunk itself.
	 */
	public void generateheightmaps(World w, int d, Chunk c, int cx, int cz, int dirtheight[][], int stoneheight[][], int baseheight[][]){

		
		int i, j, k;
		float noise[][] = new float[16][16];
		float noise2[][] = new float[16][16];

		int dirtamp = 2;
		int dx = (cx << 4)+(int)(w.worldseed&0xffff);
		int dz = (cz << 4)+(int)((w.worldseed>>16)&0xffff);
		double t;	
		
		dx = dx % 1080000;
		dz = dz % 1080000;
		
		for(i=0;i<16;i++){
			for(k=0;k<16;k++){
				noise[i][k] = 1;
				noise2[i][k] = (float) Fastmath.sin((double)((dx+i)/188d)%Math.PI)*2;
				noise2[i][k] *= (float) Fastmath.sin((double)((dz+k)/137d)%Math.PI)*2;
				noise2[i][k] *= (float) Fastmath.sin((double)((dx+i)/100d)%Math.PI)*2;
				noise2[i][k] *= (float) Fastmath.sin((double)((dz+k)/61d)%Math.PI)*2;	
			}
		}
		
		for(j=1;j<4;j++){
			for(i=0;i<16;i++){
				for(k=0;k<16;k++){
					noise[i][k] += (float) Fastmath.sin(Math.toRadians((double)((dx+i)/(11d/j))));
					noise[i][k] += (float) Fastmath.sin(Math.toRadians((double)((dz+k)/(9d/j))));

					noise[i][k] *= (float) Fastmath.sin(Math.toRadians((double)((dx+i)/(7d/j))));
					noise[i][k] *= (float) Fastmath.sin(Math.toRadians((double)((dz+k)/(5d/j))));

					noise[i][k] *= (float) Fastmath.sin(Math.toRadians((double)((dx+i)/(5d/j))));
					noise[i][k] *= (float) Fastmath.sin(Math.toRadians((double)((dz+k)/(3d/j))));

					noise[i][k] *= (float) Fastmath.sin(Math.toRadians((double)((dx+i)*(2d/j))));
					noise[i][k] *= (float) Fastmath.sin(Math.toRadians((double)((dz+k)*(3/j))));

					noise[i][k] *= (float) Fastmath.sin(Math.toRadians((double)((dx+i)*(3/j))));
					noise[i][k] *= (float) Fastmath.sin(Math.toRadians((double)((dz+k)*(5/j))));



				}
			}
		}
		
		for(i=0;i<16;i++){
			for(k=0;k<16;k++){
				noise[i][k] *= 35f;
			}
		}


		for(i=0;i<16;i++){
			for(k=0;k<16;k++){
				t = noise[i][k]*2.1f + avgheight;
				stoneheight[i][k] = (int) t;

				t = Math.abs(noise2[i][k]/2*dirtamp);
				dirtheight[i][k] = (int)t;
				dirtheight[i][k] += stoneheight[i][k];
				
				if(stoneheight[i][k] < waterlevel+10 && stoneheight[i][k] >= waterlevel){
					t = Math.abs(noise2[i][k]/2*dirtamp)*((float)stoneheight[i][k]-(float)waterlevel)/10f;	
					dirtheight[i][k] = (int)t;
					dirtheight[i][k] += stoneheight[i][k];
				}
				
				baseheight[i][k] = 0;
			}
		}
	}

	//use adjusted maps from the biome manager to generate. it may have raised or lowered a bit!
	public void generate(World w, int d, Chunk c, int cx, int cz, int lm1[][], int lm2[][], int lm3[][]){
		int type = w.rand.nextInt(2);
		int yoff = w.rand.nextInt(84)+(84*type);
		int x = cx << 4;
		int z = cz << 4;
		if(w.rand.nextInt(8) != 0)return;
		for(int i = 0; i < 16; i++) {
			for(int k = 0; k < 16; k++) {
				for(int j = 0; j < 16; j++) {
					if(type == 0) {
						w.setblock(d, x+i, yoff+k, z+j, Blocks.stone.blockID);
					} else {
						if(k == 0)
							w.setblock(d, x+i, yoff+k, z+j, Blocks.stone.blockID);
						if(k == 15)
							w.setblock(d, x+i, yoff+k, z+j, Blocks.grassblock.blockID);
						if(k != 0 && k != 15)
							w.setblock(d, x+i, yoff+k, z+j, Blocks.dirt.blockID);
					}
				}
			}
		}
		//Put in a few ores now!
		Ores.generate(w, d, this, c, cx, cz);
		
	}
	
		
	/*
	 * You can (and probably should) use world.set/get calls here. 
	 * That's what this is for... structures that can cross chunk boundaries!
	 * Note that structures larger than 16*16 chunks (256*256 blocks) can cause slowness...
	 * Large trees and dungeons are welcome here... 
	 */
	public void decorate(World world, int d, Chunk c, int chunkx, int chunkz){
		tr.addGrass(world, d, chunkx<<4, chunkz<<4, c);
	}
	
}
