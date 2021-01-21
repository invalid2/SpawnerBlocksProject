package com.invalidname.spawnerblocks.items;

import dangerzone.InventoryContainer;
import dangerzone.entities.Entity;
import dangerzone.items.Item;

public class LychemistHandbook extends Item {

	public LychemistHandbook(String n, String txt) {
		super(n, txt);
		// TODO Auto-generated constructor stub
	}
	
	public boolean onRightClick(Entity holder, Entity clickedon, InventoryContainer ic) {
		return false; //return TRUE if your item did something and should be deleted/decremented
	}
	
}
