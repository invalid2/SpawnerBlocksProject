package com.invalidname.spawnerblocks.entities;

import org.lwjgl.opengl.GL11;

import dangerzone.ModelBase;
import dangerzone.ModelRenderer;
import dangerzone.entities.Entity;

public class ModelImpostorGoose extends ModelBase {
	//fields
	ModelRenderer lfoot;
	ModelRenderer rfoot;
	ModelRenderer lleg;
	ModelRenderer rleg;
	ModelRenderer body;
	ModelRenderer tail;
	ModelRenderer lwing;
	ModelRenderer rwing;
	ModelRenderer chest;
	ModelRenderer neck;
	ModelRenderer head;
	ModelRenderer beak;
	
	int attackticker = 0;
	float PI = (float) Math.PI;
	
	public ModelImpostorGoose() {
		lfoot = new ModelRenderer( 44, 6);
		lfoot.addCube(0F, 4F, -2F, 2, 1, 3);
		lfoot.setRotationPoint(2F, 19F, -1F);
		lfoot.setTextureSize(64, 128);
		lfoot.mirror = true;
		lfoot.setRotation( 0F, 0F, 0F);
		rfoot = new ModelRenderer(31, 6);
		rfoot.addCube(-1F, 4F, -2F, 2, 1, 3);
		rfoot.setRotationPoint(-3F, 19F, -1F);
		rfoot.setTextureSize(64, 128);
		rfoot.mirror = true;
		rfoot.setRotation( 0F, 0F, 0F);
		lleg = new ModelRenderer(47, 0);
		lleg.addCube(0F, 0F, 0F, 1, 4, 1);
		lleg.setRotationPoint(2F, 19F, -1F);
		lleg.setTextureSize(64, 128);
		lleg.mirror = true;
		lleg.setRotation( 0F, 0F, 0F);
		rleg = new ModelRenderer(34, 0);
		rleg.addCube(0F, 0F, 0F, 1, 4, 1);
		rleg.setRotationPoint(-3F, 19F, -1F);
		rleg.setTextureSize(64, 128);
		rleg.mirror = true;
		rleg.setRotation( 0F, 0F, 0F);
		body = new ModelRenderer( 0, 63);
		body.addCube(0F, 0F, 0F, 10, 5, 14);
		body.setRotationPoint(-5F, 16F, -7F);
		body.setTextureSize(64, 128);
		body.mirror = true;
		body.setRotation( 0F, 0F, 0F);
		tail = new ModelRenderer( 0, 53);
		tail.addCube(0F, 0F, 0F, 6, 2, 7);
		tail.setRotationPoint(-3F, 16F, 6F);
		tail.setTextureSize(64, 128);
		tail.mirror = true;
		tail.setRotation( 0.296706F, 0F, 0F);
		lwing = new ModelRenderer(0, 105);
		lwing.addCube(0F, 0F, 0F, 10, 1, 20);
		lwing.setRotationPoint(4F, 16F, -3F);
		lwing.setTextureSize(64, 128);
		lwing.mirror = true;
		lwing.setRotation( 0F, 0F, 0.6806784F);
		rwing = new ModelRenderer( 0, 83);
		rwing.addCube(-10F, 0F, 0F, 10, 1, 20);
		rwing.setRotationPoint(-4F, 16F, -3F);
		rwing.setTextureSize(64, 128);
		rwing.mirror = true;
		rwing.setRotation( 0F, 0F, -0.6806784F);
		chest = new ModelRenderer( 0, 47);
		chest.addCube(0F, 0F, 0F, 4, 3, 2);
		chest.setRotationPoint(-2F, 17F, -9F);
		chest.setTextureSize(64, 128);
		chest.mirror = true;
		chest.setRotation( 0F, 0F, 0F);
		neck = new ModelRenderer( 3, 30);
		neck.addCube(0F, -12F, 0F, 1, 12, 1);
		neck.setRotationPoint(-0.5F, 18F, -9F);
		neck.setTextureSize(64, 128);
		neck.mirror = true;
		neck.setRotation( -0.1919862F, 0F, 0F);
		head = new ModelRenderer( 0, 22);
		head.addCube(-0.5F, -13F, 0F, 2, 2, 4);
		head.setRotationPoint(-0.5F, 18F, -9F);
		head.setTextureSize(64, 128);
		head.mirror = true;
		head.setRotation( 0F, 0F, 0F);
		beak = new ModelRenderer( 13, 23);
		beak.addCube(0F, -12F, -2F, 1, 1, 2);
		beak.setRotationPoint(-0.5F, 18F, -9F);
		beak.setTextureSize(64, 128);
		beak.mirror = true;
		beak.setRotation( 0F, 0F, 0F);
	}
	
	// f = lifetime ticker. Doing Math.toRadians() on it provides a nice smooth wave cycle.
	// f1 = entity velocity
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float deathfactor) {
		
		float newangle = 0;
		//System.out.printf("floats: %f,  %f, %f, %f, %f, %f\n", f, f1, f2, f3, f4, f5);
		if(f1 > 0.01) {
			newangle = (float) (Math.cos(Math.toRadians(f*5.6f)) * (float)Math.PI * 2.90F * f1);
			if(newangle > 1.3f)newangle = 1.3f;
			if(newangle < -1.3f)newangle = -1.3f;
		}else{
			newangle = 0.0F;
		}
		
		lleg.rotateAngleX = newangle;
		rleg.rotateAngleX = -newangle;
		rfoot.rotateAngleX = -newangle;
		lfoot.rotateAngleX = newangle;
		
		if(entity.isSwarming()) {
			newangle = 1.2f;
			lleg.rotateAngleX = newangle;
			rleg.rotateAngleX = newangle;
			rfoot.rotateAngleX = newangle;
			lfoot.rotateAngleX = newangle;
		}
		if(attackticker > 0) {
			body.rotateAngleX = PI/2;
			
			body.rotationPointY = 19;
			body.rotationPointZ = -3;
			
			tail.rotationPointZ = -1;
			
			chest.rotationPointY = 5;
			chest.rotationPointZ = -5;
			
			neck.rotationPointY = 5;
			neck.rotationPointZ = -5;
			
			head.rotationPointY = 5;
			head.rotationPointZ = -5;
			
			beak.rotationPointY = 5;
			beak.rotationPointZ = -5;
		} else {
			body.rotateAngleX = 0;
			
			body.rotationPointY = 16;
			body.rotationPointZ = -7;
			
			tail.rotationPointZ = 6;
			
			chest.rotationPointY = 17;
			chest.rotationPointZ = -9;
			
			neck.rotationPointY = 17;
			neck.rotationPointZ = -9;
			
			head.rotationPointY = 17;
			head.rotationPointZ = -9;
			
			beak.rotationPointY = 17;
			beak.rotationPointZ = -9;
		}
		
		
		if(entity.madtimer != 0 || (!entity.getOnGround() && !entity.getInLiquid()) || entity.isSwarming()){
			newangle = 0;
			newangle += (float) (Math.cos(Math.toRadians(f*20.6f)) * (float)Math.PI * 0.65F);
		    } else {
		    	newangle = 0.680f;
		    	newangle += (float) (Math.cos(Math.toRadians(f*1.6f)) * (float)Math.PI * 0.02F);
		    }
		lwing.rotateAngleZ = newangle;
		rwing.rotateAngleZ = -newangle;
		
		//Don't go breaking our necks!
		if(f2 > 30)f2 = 30;
		if(f2 < -30)f2 = -30;
		if(f3 > 45)f3 = 45;
		if(f3 < -45)f3 = -45;
		if(f4 > 20)f4 = 20;
		if(f4 < -20)f4 = -20;
		
		neck.rotateAngleX = -0.192f + (float) Math.toRadians(f2);
		head.rotateAngleX = beak.rotateAngleX = (float) Math.toRadians(f2);
		head.rotateAngleY = beak.rotateAngleY = neck.rotateAngleY = (float) -Math.toRadians(f3);
		head.rotateAngleZ = beak.rotateAngleZ = neck.rotateAngleZ = (float) Math.toRadians(f4);
		
		lfoot.render(deathfactor);
		rfoot.render(deathfactor);
		lleg.render(deathfactor);
		rleg.render(deathfactor);
		body.render(deathfactor);
		tail.render(deathfactor);
		lwing.render(deathfactor);
		rwing.render(deathfactor);
		chest.render(deathfactor);
		neck.render(deathfactor);
		head.render(deathfactor);
		beak.render(deathfactor);
		
		if(entity.getAttacking()) {
			attackticker++;
		} else if(attackticker > 0) {
			attackticker--;
		}
			
		if(this.attackticker > 2)
			this.attackticker = 2;
	}
	
	public void doScale(Entity ent) {
		if(ent.isBaby()) {
			GL11.glScalef(0.33f, 0.33f, 0.33f); 
		}
	}
}
