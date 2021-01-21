package com.invalidname.spawnerblocks.entities;

import dangerzone.ModelBase;
import dangerzone.ModelRenderer;
import dangerzone.entities.Entity;

public class ModelImpostor extends ModelBase {
	//fields
	ModelRenderer body;
	ModelRenderer lfleg;
	ModelRenderer rfleg;
	ModelRenderer lrleg;
	ModelRenderer rrleg;
	ModelRenderer neck;
	ModelRenderer head;
	ModelRenderer lant1;
	ModelRenderer lant2;
	ModelRenderer lant3;
	ModelRenderer rant1;
	ModelRenderer rant2;
	ModelRenderer rant3;
	
	int attackticker = 0;
	float PI = (float) Math.PI;
	
	public ModelImpostor() {
		
		body = new ModelRenderer(0, 194);
		body.addCube(-7F, 0F, -6F, 14, 18, 40);
		body.setRotationPoint(0F, -15F, 0F);
		body.setTextureSize(128, 256);
		body.mirror = true;
		body.setRotation( 0F, 0F, 0F);
		lfleg = new ModelRenderer(0, 75);
		lfleg.addCube(-2F, -2F, -2F, 4, 26, 4);
		lfleg.setRotationPoint(6F, 0F, 0F);
		lfleg.setTextureSize(128, 256);
		lfleg.mirror = true;
		lfleg.setRotation( 0F, 0F, 0F);
		rfleg = new ModelRenderer(19, 75);
		rfleg.addCube(-2F, -2F, -2F, 4, 26, 4);
		rfleg.setRotationPoint(-6F, 0F, 0F);
		rfleg.setTextureSize(128, 256);
		rfleg.mirror = true;
		rfleg.setRotation( 0F, 0F, 0F);
		lrleg = new ModelRenderer(38, 75);
		lrleg.addCube(-2F, -2F, -2F, 4, 26, 4);
		lrleg.setRotationPoint(6F, 0F, 29F);
		lrleg.setTextureSize(128, 256);
		lrleg.mirror = true;
		lrleg.setRotation( 0F, 0F, 0F);
		rrleg = new ModelRenderer( 57, 75);
		rrleg.addCube(-2F, -2F, -2F, 4, 26, 4);
		rrleg.setRotationPoint(-6F, 0F, 29F);
		rrleg.setTextureSize(128, 256);
		rrleg.mirror = true;
		rrleg.setRotation( 0F, 0F, 0F);
		neck = new ModelRenderer(0, 161);
		neck.addCube(-3F, -5F, -15F, 6, 11, 17);
		neck.setRotationPoint(0F, -9F, 0F);
		neck.setTextureSize(128, 256);
		neck.mirror = true;
		neck.setRotation( -0.837758F, 0F, 0F);
		head = new ModelRenderer(49, 161);
		head.addCube(-4F, -19F, -21F, 8, 9, 19);
		head.setRotationPoint(0F, -9F, 0F);
		head.setTextureSize(128, 256);
		head.mirror = true;
		head.setRotation( 0.2268928F, 0F, 0F);
		lant1 = new ModelRenderer(0, 32);
		lant1.addCube(5F, -18F, -7F, 5, 1, 4);
		lant1.setRotationPoint(0F, -9F, 0F);
		lant1.setTextureSize(128, 256);
		lant1.mirror = true;
		lant1.setRotation( 0.3316126F, 0F, -0.1047198F);
		lant2 = new ModelRenderer(29, 0);
		lant2.addCube(12F, -17F, -4F, 11, 1, 27);
		lant2.setRotationPoint(0F, -9F, 0F);
		lant2.setTextureSize(128, 256);
		lant2.mirror = true;
		lant2.setRotation( 0.4014257F, 0.2094395F, -0.1745329F);
		lant3 = new ModelRenderer(0, 47);
		lant3.addCube(2F, -14F, -22F, 1, 1, 8);
		lant3.setRotationPoint(0F, -9F, 0F);
		lant3.setTextureSize(128, 256);
		lant3.mirror = true;
		lant3.setRotation( -0.1570796F, -0.2269F, 0F);
		rant1 = new ModelRenderer( 0, 39);
		rant1.addCube(-10F, -18F, -7F, 5, 1, 4);
		rant1.setRotationPoint(0F, -9F, 0F);
		rant1.setTextureSize(128, 256);
		rant1.mirror = true;
		rant1.setRotation( 0.3316126F, 0F, 0.1047198F);
		rant2 = new ModelRenderer(29, 30);
		rant2.addCube(-23F, -17F, -4F, 11, 1, 27);
		rant2.setRotationPoint(0F, -9F, 0F);
		rant2.setTextureSize(128, 256);
		rant2.mirror = true;
		rant2.setRotation( 0.4014257F, -0.2094395F, 0.1745329F);
		rant3 = new ModelRenderer(0, 59);
		rant3.addCube(-3F, -14F, -22F, 1, 1, 8);
		rant3.setRotationPoint(0F, -9F, 0F);
		rant3.setTextureSize(128, 256);
		rant3.mirror = true;
		rant3.setRotation( -0.1570796F, 0.2269F, 0F);
		
		neck.connectPart(head);
		
		
		neck.finishConnect();
	}
	
	// f = lifetime ticker. Doing Math.toRadians() on it provides a nice smooth wave cycle.
	// f1 = entity velocity
	public void render(Entity entity, float lifetimeticker, float velocity, float headupdown, float headleftright, float headtilt, float deathfactor) {
		float newangle = 0;
		//System.out.printf("floats: %f,  %f, %f, %f, %f, %f\n", f, f1, f2, f3, f4, f5);
		if(velocity > 0.01) {
			newangle = (float) (Math.cos(Math.toRadians(lifetimeticker*5.6f)) * (float)Math.PI * 0.30F * velocity);
		}else{
			newangle = 0.0F;
		}
		
		//System.out.println("headupdown:"+headupdown+" headleftright:"+headleftright+" headtilt:"+headtilt+" attacking:"+entity.getAttacking());
		//System.out.println(attackticker+"  "+entity.getAttacking());
		//System.out.println(headleftright);
		
		rfleg.rotateAngleX = newangle;
		rfleg.rotateAngleZ = PI / 12*(attackticker/2);
		
		
		lfleg.rotateAngleX = -newangle;
		lfleg.rotateAngleZ = -(PI / 12*(attackticker/2));
		
		rrleg.rotateAngleX = -newangle;
		lrleg.rotateAngleX = newangle;
		
		
		
		
		if(attackticker > 0) {
			rfleg.rotateAngleZ = PI / 12;//*(attackticker/100);
			
			lfleg.rotateAngleZ = -(PI / 12);//*(attackticker/100));
			
			rfleg.rotationPointY = -24;//*(attackticker/100);
			rfleg.rotationPointZ = (2);//*(attackticker/100);
			
			lfleg.rotationPointY = -24;//*(attackticker/100);
			lfleg.rotationPointZ = (2);//*(attackticker/100);
			
			//this.rrleg.rotationPointY = -12;//*(attackticker/100);
			rrleg.rotationPointZ = (2);//*(attackticker/100);
			
			//this.lrleg.rotationPointY = -12;//*(attackticker/100);
			lrleg.rotationPointZ = (2);//*(attackticker/100);
			
			neck.rotationPointY = -16-8-1;
			neck.rotationPointZ = -4;
			
			lant1.rotationPointY = -25;
			lant2.rotationPointY = -25;
			lant3.rotationPointY = -25;
			rant1.rotationPointY = -25;
			rant2.rotationPointY = -25;
			rant3.rotationPointY = -25;
			
			lant1.rotationPointZ = -3;
			lant2.rotationPointZ = -3;
			lant3.rotationPointZ = -3;
			rant1.rotationPointZ = -3;
			rant2.rotationPointZ = -3;
			rant3.rotationPointZ = -3;
			
			body.rotateAngleX = PI/2;//*(attackticker/100);
			body.rotationPointY = 4;//*(attackticker/100);
			body.rotationPointZ = -7;//*(attackticker/100);
			
			neck.rotateAngleX = newangle - 0.8377f;
			head.rotateAngleX = newangle + 0.2269f;
			lant1.rotateAngleX = newangle + 0.3316f;
			lant2.rotateAngleX = newangle + 0.4014f;
			lant3.rotateAngleX = newangle - 0.1570f;
			rant1.rotateAngleX = newangle + 0.3316f;
			rant2.rotateAngleX = newangle + 0.4014f;
			rant3.rotateAngleX = newangle - 0.1570f;
			//this.body.offsetY = (-32-5.5f)*(attackticker/100);
			//this.body.offsetZ = (-16-8)*(attackticker/100);
		} else {
			rfleg.rotationPointY = 0;//*(attackticker/100);
			rfleg.rotationPointZ = 0;//*(attackticker/100);
			
			lfleg.rotationPointY = 0;//*(attackticker/100);
			lfleg.rotationPointZ = 0;//*(attackticker/100);
			
			rrleg.rotationPointZ = 29;//*(attackticker/100);
			lrleg.rotationPointZ = 29;//*(attackticker/100);
			
			neck.rotationPointY = -7;
			neck.rotationPointZ = 0;
			
			lant1.rotationPointY = -9;
			lant2.rotationPointY = -9;
			lant3.rotationPointY = -9;
			rant1.rotationPointY = -9;
			rant2.rotationPointY = -9;
			rant3.rotationPointY = -9;
			
			lant1.rotationPointZ = 0;
			lant2.rotationPointZ = 0;
			lant3.rotationPointZ = 0;
			rant1.rotationPointZ = 0;
			rant2.rotationPointZ = 0;
			rant3.rotationPointZ = 0;
			
			body.rotateAngleX = 0;
			body.rotationPointY = -15;
			body.rotationPointZ = 0;
		}
		
		
		
		
		body.render(deathfactor);
		lfleg.render(deathfactor);
		rfleg.render(deathfactor);
		lrleg.render(deathfactor);
		rrleg.render(deathfactor);
		neck.render(deathfactor);
		head.render(deathfactor);
		lant1.render(deathfactor);
		lant2.render(deathfactor);
		lant3.render(deathfactor);
		rant1.render(deathfactor);
		rant2.render(deathfactor);
		rant3.render(deathfactor);
		
		if(entity.getAttacking()) {
			attackticker++;
		} else if(attackticker > 0) {
			attackticker--;
		}
			
		if(attackticker > 2)
			attackticker = 2;
	}
	
}