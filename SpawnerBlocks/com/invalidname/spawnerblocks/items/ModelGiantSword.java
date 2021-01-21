package com.invalidname.spawnerblocks.items;

import dangerzone.ModelBase;
import dangerzone.ModelRenderer;

public class ModelGiantSword extends ModelBase {
	//fields
	ModelRenderer Pommel;
	ModelRenderer Handle;
	ModelRenderer GuardMiddle;
	ModelRenderer GuardLeft;
	ModelRenderer GuardRight;
	ModelRenderer BladeBottom;
	ModelRenderer BladeMiddle;
	ModelRenderer BladeTop;
	ModelRenderer BladeEnd;
	
	public ModelGiantSword() {
		
		Pommel = new ModelRenderer(this, 0, 0);
		Pommel.addBox(-1.5F, -2F, -1.5F, 3, 2, 3);
		Pommel.setRotationPoint(0F, 24F, 0F);
		Pommel.setTextureSize(64, 32);
		Pommel.mirror = true;
		setRotation(Pommel, 0F, 0F, 0F);
		Handle = new ModelRenderer(this, 12, 0);
		Handle.addBox(-1F, -10F, -1F, 2, 8, 2);
		Handle.setRotationPoint(0F, 24F, 0F);
		Handle.setTextureSize(64, 32);
		Handle.mirror = true;
		setRotation(Handle, 0F, 0F, 0F);
		GuardMiddle = new ModelRenderer(this, 20, 0);
		GuardMiddle.addBox(-3F, -11F, -1.5F, 6, 1, 3);
		GuardMiddle.setRotationPoint(0F, 24F, 0F);
		GuardMiddle.setTextureSize(64, 32);
		GuardMiddle.mirror = true;
		setRotation(GuardMiddle, 0F, 0F, 0F);
		GuardLeft = new ModelRenderer(this, 38, 0);
		GuardLeft.addBox(5.5F, -9.9F, -1.5F, 4, 1, 3);
		GuardLeft.setRotationPoint(0F, 24F, 0F);
		GuardLeft.setTextureSize(64, 32);
		GuardLeft.mirror = true;
		setRotation(GuardLeft, 0F, 0F, -0.2617994F);
		GuardRight = new ModelRenderer(this, 38, 4);
		GuardRight.addBox(-9.5F, -9.9F, -1.5F, 4, 1, 3);
		GuardRight.setRotationPoint(0F, 24F, 0F);
		GuardRight.setTextureSize(64, 32);
		GuardRight.mirror = true;
		setRotation(GuardRight, 0F, 0F, 0.2617994F);
		BladeBottom = new ModelRenderer(this, 20, 4);
		BladeBottom.addBox(-2.5F, -19F, -0.5F, 5, 8, 1);
		BladeBottom.setRotationPoint(0F, 24F, 0F);
		BladeBottom.setTextureSize(64, 32);
		BladeBottom.mirror = true;
		setRotation(BladeBottom, 0F, 0F, 0F);
		BladeMiddle = new ModelRenderer(this, 0, 5);
		BladeMiddle.addBox(-2F, -27F, -0.5F, 4, 8, 1);
		BladeMiddle.setRotationPoint(0F, 24F, 0F);
		BladeMiddle.setTextureSize(64, 32);
		BladeMiddle.mirror = true;
		setRotation(BladeMiddle, 0F, 0F, 0F);
		BladeTop = new ModelRenderer(this, 12, 10);
		BladeTop.addBox(-1.5F, -35F, -0.5F, 3, 8, 1);
		BladeTop.setRotationPoint(0F, 24F, 0F);
		BladeTop.setTextureSize(64, 32);
		BladeTop.mirror = true;
		setRotation(BladeTop, 0F, 0F, 0F);
		BladeEnd = new ModelRenderer(this, 32, 4);
		BladeEnd.addBox(-1F, -36F, -0.5F, 2, 1, 1);
		BladeEnd.setRotationPoint(0F, 24F, 0F);
		BladeEnd.setTextureSize(64, 32);
		BladeEnd.mirror = true;
		setRotation(BladeEnd, 0F, 0F, 0F);
	}
	
	public void render() {
		Pommel.render(1);
		Handle.render(1);
		GuardMiddle.render(1);
		GuardLeft.render(1);
		GuardRight.render(1);
		BladeBottom.render(1);
		BladeMiddle.render(1);
		BladeTop.render(1);
		BladeEnd.render(1);
	}
}
