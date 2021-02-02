package com.invalidname.spawnerblocks.entities;

import dangerzone.ModelBase;
import dangerzone.ModelRenderer;
import dangerzone.entities.Entity;

public class ModelGuardian extends ModelBase {
	//fields
	ModelRenderer Body;
	ModelRenderer HandLeft;
	ModelRenderer HandRight;
	ModelRenderer Eye;
	
	public ModelGuardian() {
		
		Body = new ModelRenderer(this, 0, 0);
		Body.addBox(-8F, -8F, -8F, 16, 16, 16);
		Body.setRotationPoint(0F, 16F, 0F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		HandLeft = new ModelRenderer(this, 0, 0);
		HandLeft.addBox(14F, -5F, -2F, 4, 2, 4);
		HandLeft.setRotationPoint(0F, 15F, 0F);
		HandLeft.setTextureSize(64, 32);
		HandLeft.mirror = true;
		setRotation(HandLeft, 0F, 0.7853982F, 0.7853982F);
		HandRight = new ModelRenderer(this, 0, 0);
		HandRight.addBox(14F, -5F, -2F, 4, 2, 4);
		HandRight.setRotationPoint(0F, 15F, 0F);
		HandRight.setTextureSize(64, 32);
		HandRight.mirror = true;
		setRotation(HandRight, 0F, 2.356194F, -0.7853982F);
		Eye = new ModelRenderer(this, 48, 0);
		Eye.addBox(-3F, -3F, 0F, 6, 6, 0);
		Eye.setRotationPoint(0F, 16F, -8.05F);
		Eye.setTextureSize(64, 32);
		Eye.mirror = true;
		setRotation(Eye, 0F, 0F, 0F);
		
		//Body.connectPart(Eye);
	}
	
	public void render(Entity entity, float lifetimeticker, float velocity, float headupdown, float headleftright, float headtilt, float deathfactor) {
		super.render(entity, lifetimeticker, velocity, headupdown, headleftright, headtilt, deathfactor);
		//Guardian g = (Guardian) entity;
		
		//float cdir = (float) Math.toRadians(g.rotation_yaw);
    	//float tdir = 0;
    	//if(g.target != null)
    		//tdir = (float) Math.atan2(g.target.targetx - g.posx, g.target.targetz - g.posz);
    	
    	//if(tdir < 0)
    		//tdir += 2*Math.PI;
    	//float ddiff = tdir - cdir;
		System.out.println(entity.rotation_yaw_motion);
		
		//Eye.offsetX = Math.round((2*headleftright/90))-3;
		Eye.offsetX = Math.round((entity.rotation_yaw_motion/5)-3);
		if(Eye.offsetX < -5) {
			Eye.offsetX = -5;
		}
		
		if(Eye.offsetX > -1) {
			Eye.offsetX = -1;
		}
		Body.render(deathfactor);
		HandLeft.render(deathfactor);
		HandRight.render(deathfactor);
		Eye.render(deathfactor);
	}
}
