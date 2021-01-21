package com.invalidname.spawnerblocks.entities;

import org.lwjgl.opengl.GL11;

import dangerzone.ModelBase;
import dangerzone.entities.Entity;

public class ModelArtificialSpawner extends ModelBase {
	public ModelArtificialSpawner() {
		
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float deathfactor) {
		EntityArtificialSpawner et = (EntityArtificialSpawner)entity;
		if(et.model != null && et.showentity != null){
			et.model.render(et.showentity, f, f1, 0, 0, 0, deathfactor);
		}   
	}
	  
	  public void doScale(Entity ent){
		  EntityArtificialSpawner et = (EntityArtificialSpawner)ent;
		  if(et.model != null && et.showentity != null){
			  et.model.doScale(et.showentity);
			  GL11.glScalef( 1.0f, 1, 1);
		  }	  
	  }
}
