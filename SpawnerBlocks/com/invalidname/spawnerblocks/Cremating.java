package com.invalidname.spawnerblocks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dangerzone.blocks.Block;
import dangerzone.items.Item;

public class Cremating {
	public static  List<CrematingRecipe> recipes;
	
	public Cremating(){
		recipes = new ArrayList<CrematingRecipe>();
	}
	
	/*
	 * Yes, all recipe registration is done by UNIQUENAME, because itemIDs and blockIDs are indeterminate at registration time!
	 * In other words, itemIDs and blockIDs don't have a stable value until AFTER all registration is complete.
	 * 
	 * "ordered" specifies whether items can be in random places or not.
	 * 
	 */
	public static void registerCrematingRecipe( Object i1, Object i2, int cooktime, int ly_output){
		
		CrematingRecipe r = new CrematingRecipe();
		if(cooktime <= 0 || cooktime > 64000)return;
		if(i1 == null)return;
		if(i2 == null)return;
		
		if(i1 instanceof Item)r.inname = ((Item) i1).uniquename;
		if(i1 instanceof Block)r.inname = ((Block) i1).uniquename;
		
		if(i2 instanceof Item)r.outname = ((Item) i2).uniquename;
		if(i2 instanceof Block)r.outname = ((Block) i2).uniquename;
		
		r.cooktime = cooktime;
		r.ly_output = ly_output;
		//add recipe to list!
		recipes.add(r);		
		
	}
	
	//should some day change to a hashmap...
	public static CrematingRecipe find(String i1){
		if(i1 == null)return null;
		Iterator<CrematingRecipe> ii = recipes.iterator();
		CrematingRecipe r;
		while(ii.hasNext()){
			r = ii.next();
			if(i1.equals(r.inname))return r;			
		}		
		return null;
	}
}
