package com.invalidname.spawnerblocks.items;

import dangerzone.ModelBase;
import dangerzone.ModelRenderer;

public class ModelGiantAxe extends ModelBase {
	//fields
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	
	public ModelGiantAxe() {
		
		Shape1 = new ModelRenderer(this, 8, 0);
		Shape1.addBox(-1.5F, -2F, -1.5F, 3, 2, 3);
		Shape1.setRotationPoint(0F, 24F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 0);
		Shape2.addBox(-1F, -32F, -1F, 2, 30, 2);
		Shape2.setRotationPoint(0F, 24F, 0F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 20, 0);
		Shape3.addBox(-4F, -30F, -1.5F, 8, 3, 3);
		Shape3.setRotationPoint(0F, 24F, 0F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 8, 6);
		Shape4.addBox(4F, -30F, -1F, 2, 3, 2);
		Shape4.setRotationPoint(0F, 24F, 0F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 16, 6);
		Shape5.addBox(6F, -30F, -0.5F, 1, 3, 1);
		Shape5.setRotationPoint(0F, 24F, 0F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 8, 16);
		Shape6.addBox(7F, -30F, -0.5F, 2, 6, 1);
		Shape6.setRotationPoint(0F, 24F, 0F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 8, 12);
		Shape7.addBox(14.4F, -29.6F, -0.5F, 2, 3, 1);
		Shape7.setRotationPoint(0F, 24F, 0F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, -0.2617994F);
		Shape8 = new ModelRenderer(this, 14, 12);
		Shape8.addBox(0.5F, -25.6F, -0.5F, 2, 4, 1);
		Shape8.setRotationPoint(0F, 24F, 0F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0.2617994F);
	}
	
	public void render() {
		
		Shape1.render(1);
		Shape2.render(1);
		Shape3.render(1);
		Shape4.render(1);
		Shape5.render(1);
		Shape6.render(1);
		Shape7.render(1);
		Shape8.render(1);
	}
}
