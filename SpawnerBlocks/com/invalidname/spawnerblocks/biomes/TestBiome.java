package com.invalidname.spawnerblocks.biomes;

import dangerzone.Chunk;
import dangerzone.World;
import dangerzone.biomes.Biome;
import dangerzone.blocks.Blocks;

public class TestBiome extends Biome {

	public TestBiome(String s){
		super(s);
	}
	
	public void generate(World w, int d, Chunk c, int cx, int cz, int lm1[][], int lm2[][], int lm3[][]){
		int x = cx << 4;
		int z = cz << 4;
		
		for(int i = 0; i < 16; i++) {
			for(int k = 0; k < 16; k++) {
				c.setblock(x+i, 71, z+k, Blocks.stopblock.blockID);
				c.setblock(x+i, 72, z+k, Blocks.stone.blockID);
				c.setblock(x+i, 73, z+k, Blocks.dirt.blockID);
				c.setblock(x+i, 74, z+k, Blocks.grassblock.blockID);
			}
		}
	}
}
