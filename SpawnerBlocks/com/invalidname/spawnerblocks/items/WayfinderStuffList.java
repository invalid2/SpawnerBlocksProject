package com.invalidname.spawnerblocks.items;

import dangerzone.StuffList;
import dangerzone.items.Item;

public class WayfinderStuffList extends StuffList{
	public Object thing;
	public int min;
	public int max;
	public int chance;
	
	public WayfinderStuffList(Object inobj, int inmin, int inmax, int inchance) {
		super(inobj, inmin, inmax, inchance);
		thing = inobj;
		min = inmin;
		max = inmax;
		chance = inchance;		
	}
	
	public int getbid(){
		return 0;
	}
	
	public int getiid(){
		if(thing instanceof Item)return ((Item) thing).itemID;
		return 0;
	}
}
