
package com.invalidname.spawnerblocks.entities;

import dangerzone.ModelBase;
import dangerzone.ModelRenderer;
import dangerzone.entities.Entity;

public class ModelDracorat extends ModelBase {
	//fields
	ModelRenderer Nose;
	ModelRenderer Head;
	ModelRenderer Body;
	ModelRenderer BodyThing;
	ModelRenderer Tail1;
	ModelRenderer Tail2;
	ModelRenderer RightEar;
	ModelRenderer LeftEar;
	ModelRenderer LeftFrontLeg;
	ModelRenderer RightFrontLeg;
	ModelRenderer LeftBackLeg;
	ModelRenderer RightBackLeg;
	ModelRenderer LeftWingThing;
	ModelRenderer RightWingThing;
	ModelRenderer LeftWing;
	ModelRenderer RightWing;
	
	public ModelDracorat() {
		
		Nose = new ModelRenderer(this, 14, 0);
		Nose.addBox(-0.5F, -0.5F, -6F, 1, 1, 2);
		Nose.setRotationPoint(0F, 22F, -5F);
		Nose.setTextureSize(64, 32);
		Nose.mirror = true;
		setRotation(Nose, 0F, 0F, 0F);
		Head = new ModelRenderer(this, 0, 0);
		Head.addBox(-1.5F, -1F, -4F, 3, 2, 4);
		Head.setRotationPoint(0F, 21.5F, -5F);
		Head.setTextureSize(64, 32);
		Head.mirror = true;
		setRotation(Head, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 0, 8);
		Body.addBox(-2.5F, -1.5F, -5F, 5, 3, 10);
		Body.setRotationPoint(0F, 21F, 0F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;setRotation(Body, 0F, 0F, 0F);
		BodyThing = new ModelRenderer(this, 50, 11);
		BodyThing.addBox(-0.5F, -0.5F, -3F, 1, 1, 6);
		BodyThing.setRotationPoint(0F, 19F, 0.5F);
		BodyThing.setTextureSize(64, 32);
		BodyThing.mirror = true;
		setRotation(BodyThing, 0F, 0F, 0F);
		Tail1 = new ModelRenderer(this, 42, 0);
		Tail1.addBox(-1F, -1F, 0F, 2, 2, 9);
		Tail1.setRotationPoint(0F, 21F, 5F);
		Tail1.setTextureSize(64, 32);
		Tail1.mirror = true;
		setRotation(Tail1, 0F, 0F, 0F);
		Tail2 = new ModelRenderer(this, 38, 19);
		Tail2.addBox(-0.5F, -0.5F, 0F, 1, 1, 12);
		Tail2.setRotationPoint(0F, 21F, 5F);
		Tail2.setTextureSize(64, 32);
		Tail2.mirror = true;
		setRotation(Tail2, 0F, 0F, 0F);
		RightEar = new ModelRenderer(this, 0, 0);
		RightEar.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		RightEar.setRotationPoint(-1.5F, 20.5F, -5.5F);
		RightEar.setTextureSize(64, 32);
		RightEar.mirror = true;
		setRotation(RightEar, 0F, 0F, 0F);
		LeftEar = new ModelRenderer(this, 0, 0);
		LeftEar.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		LeftEar.setRotationPoint(1.5F, 20.5F, -5.5F);
		LeftEar.setTextureSize(64, 32);
		LeftEar.mirror = true;
		setRotation(LeftEar, 0F, 0F, 0F);
		LeftFrontLeg = new ModelRenderer(this, 38, 0);
		LeftFrontLeg.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
		LeftFrontLeg.setRotationPoint(1.5F, 22F, -3.5F);
		LeftFrontLeg.setTextureSize(64, 32);
		LeftFrontLeg.mirror = true;
		setRotation(LeftFrontLeg, 0F, 0F, 0F);
		RightFrontLeg = new ModelRenderer(this, 38, 0);
		RightFrontLeg.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
		RightFrontLeg.setRotationPoint(-1.5F, 22F, -3.5F);
		RightFrontLeg.setTextureSize(64, 32);
		RightFrontLeg.mirror = true;
		setRotation(RightFrontLeg, 0F, 0F, 0F);
		LeftBackLeg = new ModelRenderer(this, 28, 0);
		LeftBackLeg.addBox(-1F, 0F, -1F, 2, 3, 2);
		LeftBackLeg.setRotationPoint(2F, 21F, 2F);
		LeftBackLeg.setTextureSize(64, 32);
		LeftBackLeg.mirror = true;
		setRotation(LeftBackLeg, 0F, 0F, 0F);
		RightBackLeg = new ModelRenderer(this, 28, 0);
		RightBackLeg.addBox(-1F, 0F, -1F, 2, 3, 2);
		RightBackLeg.setRotationPoint(-2F, 21F, 2F);
		RightBackLeg.setTextureSize(64, 32);
		RightBackLeg.mirror = true;
		setRotation(RightBackLeg, 0F, 0F, 0F);
		LeftWingThing = new ModelRenderer(this, 0, 30);
		LeftWingThing.addBox(0F, -0.5F, -0.5F, 12, 1, 1);
		LeftWingThing.setRotationPoint(1.5F, 19.5F, -3.5F);
		LeftWingThing.setTextureSize(64, 32);
		LeftWingThing.mirror = true;
		setRotation(LeftWingThing, 0F, 0F, 0F);
		RightWingThing = new ModelRenderer(this, 0, 30);
		RightWingThing.addBox(-12F, -0.5F, -0.5F, 12, 1, 1);
		RightWingThing.setRotationPoint(-1.5F, 19.5F, -3.5F);
		RightWingThing.setTextureSize(64, 32);
		RightWingThing.mirror = true;
		setRotation(RightWingThing, 0F, 0F, 0F);
		LeftWing = new ModelRenderer(this, 16, 22);
		LeftWing.addBox(0F, 0F, -3F, 12, 0, 8);
		LeftWing.setRotationPoint(1.5F, 19.5F, 0F);
		LeftWing.setTextureSize(64, 32);
		LeftWing.mirror = true;
		setRotation(LeftWing, 0F, 0F, 0F);
		RightWing = new ModelRenderer(this, -8, 22);
		RightWing.addBox(-12F, 0F, -3F, 12, 0, 8);
		RightWing.setRotationPoint(-1.5F, 19.5F, 0F);
		RightWing.setTextureSize(64, 32);
		RightWing.mirror = true;  
		setRotation(RightWing, 0F, 0F, 0F);
		
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		
		float newangle = 0;
		//System.out.printf("floats: %f,  %f, %f, %f, %f, %f\n", f, f1, f2, f3, f4, f5);
		if(f1 > 0.1) {
			newangle = (float) (Math.cos(Math.toRadians(f*27.6f)) * (float)Math.PI * 0.35F * f1);
		} else {
			newangle = 0.0F;
		}
		
		if(entity.getAttacking()) {
			newangle = (float) (Math.cos(Math.toRadians(f*17.6f)) * (float)Math.PI * 0.25F);
		} else {
			newangle = (float) (Math.cos(Math.toRadians(f*4.6f)) * (float)Math.PI * 0.05F);
		}
		
		this.RightFrontLeg.rotateAngleX = newangle;
		this.LeftFrontLeg.rotateAngleX = -newangle;
		this.RightBackLeg.rotateAngleX = -newangle;
		this.LeftBackLeg.rotateAngleX = newangle;
		
		this.LeftWing.rotateAngleZ = newangle;
		this.RightWing.rotateAngleZ = -newangle;
		this.LeftWingThing.rotateAngleZ = newangle;
		this.RightWingThing.rotateAngleZ = -newangle;
		
		this.Tail1.rotateAngleY = newangle * 0.5F;
		this.Tail2.rotateAngleY = newangle * 1.25F;
		this.Tail2.rotationPointZ = this.Tail1.rotationPointZ + (float)Math.cos(this.Tail1.rotateAngleY)*9;
		this.Tail2.rotationPointX = this.Tail1.rotationPointX + (float)Math.sin(this.Tail1.rotateAngleY)*9;
		
		Nose.render(f5);
		Head.render(f5);
		Body.render(f5);
		BodyThing.render(f5);
		Tail1.render(f5);
		Tail2.render(f5);
		RightEar.render(f5);
		LeftEar.render(f5);
		LeftFrontLeg.render(f5);
		RightFrontLeg.render(f5);
		LeftBackLeg.render(f5);
		RightBackLeg.render(f5);
		LeftWingThing.render(f5);
		RightWingThing.render(f5);
		LeftWing.render(f5);
		RightWing.render(f5);
	}
}
