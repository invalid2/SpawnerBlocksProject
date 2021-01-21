package com.invalidname.spawnerblocks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dangerzone.blocks.Block;
import dangerzone.items.Item;

public class LyInfusing {
public static  List<LyInfusingRecipe> recipes;
	
	public LyInfusing(){
		recipes = new ArrayList<LyInfusingRecipe>();
	}
	
	/*
	 * Yes, all recipe registration is done by UNIQUENAME, because itemIDs and blockIDs are indeterminate at registration time!
	 * In other words, itemIDs and blockIDs don't have a stable value until AFTER all registration is complete.
	 * 
	 * "ordered" specifies whether items can be in random places or not.
	 * 
	 */
	public static void registerInfusingRecipe( Object i1, Object i2, float ly_input){
		
		LyInfusingRecipe r = new LyInfusingRecipe();
		if(ly_input <= 0 || ly_input > 64000)return;
		if(i1 == null)return;
		if(i2 == null)return;
		
		if(i1 instanceof Item)r.inname = ((Item) i1).uniquename;
		if(i1 instanceof Block)r.inname = ((Block) i1).uniquename;
		
		if(i2 instanceof Item)r.outname = ((Item) i2).uniquename;
		if(i2 instanceof Block)r.outname = ((Block) i2).uniquename;
		
		r.ly_input = ly_input;
		//add recipe to list!
		recipes.add(r);		
		
	}
	
	//should some day change to a hashmap...
	public static LyInfusingRecipe find(String i1){
		if(i1 == null)return null;
		Iterator<LyInfusingRecipe> ii = recipes.iterator();
		LyInfusingRecipe r;
		while(ii.hasNext()){
			r = ii.next();
			if(i1.equals(r.inname))return r;			
		}		
		return null;
	}
}
