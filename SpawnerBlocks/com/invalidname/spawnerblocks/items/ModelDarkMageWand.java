package com.invalidname.spawnerblocks.items;

import org.lwjgl.opengl.GL11;

import dangerzone.ModelBase;
import dangerzone.ModelRenderer;
import dangerzone.entities.Entity;

public class ModelDarkMageWand extends ModelBase {
	//fields
	ModelRenderer Pommel;
	ModelRenderer Handle;
	ModelRenderer Ornament;
	ModelRenderer Head;
	ModelRenderer EyeFront;
	ModelRenderer EyeBack;
	ModelRenderer EyeLeft;
	ModelRenderer EyeRight;
	ModelRenderer TopMiddle;
	ModelRenderer Top;
	ModelRenderer GuardLeft;
	ModelRenderer GuardRight;
	ModelRenderer ParticleOrigin;
	
	public ModelDarkMageWand() {
		int textureWidth = 128;
		int textureHeight = 64;
		
		Pommel = new ModelRenderer(this, 20, 48);
		Pommel.addBox(-3.5F, -7F, -3.5F, 7, 7, 7);
		Pommel.setRotationPoint(0F, 24F, 0F);
		Pommel.setTextureSize(textureWidth, textureHeight);
		Pommel.mirror = true;
		setRotation(Pommel, 0F, 0F, 0F);
		Handle = new ModelRenderer(this, 0, 30);
		Handle.addBox(-2.5F, -31F, -2.5F, 5, 24, 5);
		Handle.setRotationPoint(0F, 24F, 0F);
		Handle.setTextureSize(textureWidth, textureHeight);
		Handle.mirror = true;
		setRotation(Handle, 0F, 0F, 0F);
		Ornament = new ModelRenderer(this, 20, 30);
		Ornament.addBox(-3.5F, -42F, -3.5F, 7, 11, 7);
		Ornament.setRotationPoint(0F, 24F, 0F);
		Ornament.setTextureSize(textureWidth, textureHeight);
		Ornament.mirror = true;
		setRotation(Ornament, 0F, 0F, 0F);
		Head = new ModelRenderer(this, 0, 0);
		Head.addBox(-7.5F, -57F, -7.5F, 15, 15, 15);
		Head.setRotationPoint(0F, 24F, 0F);
		Head.setTextureSize(textureWidth, textureHeight);
		Head.mirror = true;
		setRotation(Head, 0F, 0F, 0F);
		EyeFront = new ModelRenderer(this, 60, 0);
		EyeFront.addBox(-5.5F, -55F, -8.5F, 11, 11, 1);
		EyeFront.setRotationPoint(0F, 24F, 0F);
		EyeFront.setTextureSize(textureWidth, textureHeight);
		EyeFront.mirror = true;
		setRotation(EyeFront, 0F, 0F, 0F);
		EyeBack = new ModelRenderer(this, 60, 0);
		EyeBack.addBox(-5.5F, -55F, 7.5F, 11, 11, 1);
		EyeBack.setRotationPoint(0F, 24F, 0F);
		EyeBack.setTextureSize(textureWidth, textureHeight);
		EyeBack.mirror = true;
		setRotation(EyeBack, 0F, 0F, 0F);
		EyeLeft = new ModelRenderer(this, 84, 0);
		EyeLeft.addBox(7.5F, -55F, -5.5F, 1, 11, 11);
		EyeLeft.setRotationPoint(0F, 24F, 0F);
		EyeLeft.setTextureSize(textureWidth, textureHeight);
		EyeLeft.mirror = true;
		setRotation(EyeLeft, 0F, 0F, 0F);
		EyeRight = new ModelRenderer(this, 84, 0);
		EyeRight.addBox(-8.5F, -55F, -5.5F, 1, 11, 11);
		EyeRight.setRotationPoint(0F, 24F, 0F);
		EyeRight.setTextureSize(textureWidth, textureHeight);
		EyeRight.mirror = true;
		setRotation(EyeRight, 0F, 0F, 0F);
		TopMiddle = new ModelRenderer(this, 48, 30);
		TopMiddle.addBox(-3.5F, -61F, -3.5F, 7, 4, 7);
		TopMiddle.setRotationPoint(0F, 24F, 0F);
		TopMiddle.setTextureSize(textureWidth, textureHeight);
		TopMiddle.mirror = true;
		setRotation(TopMiddle, 0F, 0F, 0F);
		Top = new ModelRenderer(this, 60, 12);
		Top.addBox(-2.5F, -64F, -2.5F, 5, 3, 5);
		Top.setRotationPoint(0F, 24F, 0F);
		Top.setTextureSize(textureWidth, textureHeight);
		Top.mirror = true;
		setRotation(Top, 0F, 0F, 0F);
		GuardLeft = new ModelRenderer(this, 48, 42);
		GuardLeft.addBox(16.5F, -34.5F, -1.5F, 11, 4, 3);
		GuardLeft.setRotationPoint(0F, 24F, 0F);
		GuardLeft.setTextureSize(textureWidth, textureHeight);
		GuardLeft.mirror = true;
		setRotation(GuardLeft, 0F, 0F, -0.3926991F);
		GuardRight = new ModelRenderer(this, 48, 50);
		GuardRight.addBox(-27.5F, -34.5F, -1.5F, 11, 4, 3);
		GuardRight.setRotationPoint(0F, 24F, 0F);
		GuardRight.setTextureSize(textureWidth, textureHeight);
		GuardRight.mirror = true;
		setRotation(GuardRight, 0F, 0F, 0.3926991F);
		ParticleOrigin = new ModelRenderer(this, 0, 0);
		ParticleOrigin.addBox(0F, -49.5F, 0F, 0, 0, 0);
		ParticleOrigin.setRotationPoint(0F, 24.5F, 0F);
		ParticleOrigin.setTextureSize(textureWidth, textureHeight);
		ParticleOrigin.mirror = true;
		setRotation(ParticleOrigin, 0F, 0F, 0F);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		
		Pommel.render(f5);
		Handle.render(f5);
		Ornament.render(f5);
		Head.render(f5);
		EyeFront.render(f5);
		EyeBack.render(f5);
		EyeLeft.render(f5);
		EyeRight.render(f5);
		TopMiddle.render(f5);
		Top.render(f5);
		GuardLeft.render(f5);
		GuardRight.render(f5);
		ParticleOrigin.render(f5);
	}
	
	public void render() {
		
		GL11.glScalef(0.25f, 0.25f, 0.25f);
		
		Pommel.render(1);
		Handle.render(1);
		Ornament.render(1);
		Head.render(1);
		EyeFront.render(1);
		EyeBack.render(1);
		EyeLeft.render(1);
		EyeRight.render(1);
		TopMiddle.render(1);
		Top.render(1);
		GuardLeft.render(1);
		GuardRight.render(1);
		ParticleOrigin.render(1);
	}
}