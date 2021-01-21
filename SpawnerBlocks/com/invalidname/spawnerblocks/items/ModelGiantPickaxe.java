package com.invalidname.spawnerblocks.items;

import dangerzone.ModelBase;
import dangerzone.ModelRenderer;

public class ModelGiantPickaxe extends ModelBase {
	//fields
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	
	public ModelGiantPickaxe() {
		
		int textureWidth = 32;
		int textureHeight = 64;
		
		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(-1F, -32F, -1F, 2, 32, 2);
		Shape1.setRotationPoint(0F, 24F, 0F);
		Shape1.setTextureSize(textureWidth, textureHeight);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 8, 0);
		Shape2.addBox(-3F, -30F, -2F, 6, 2, 4);
		Shape2.setRotationPoint(0F, 24F, 0F);
		Shape2.setTextureSize(textureWidth, textureHeight);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 8, 6);
		Shape3.addBox(-2F, -30F, -2F, 4, 2, 4);
		Shape3.setRotationPoint(0F, 24F, 0F);
		Shape3.setTextureSize(textureWidth, textureHeight);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0.1570796F);
		Shape4 = new ModelRenderer(this, 8, 12);
		Shape4.addBox(-2F, -30F, -2F, 4, 2, 4);
		Shape4.setRotationPoint(0F, 24F, 0F);
		Shape4.setTextureSize(textureWidth, textureHeight);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0.2792527F);
		Shape5 = new ModelRenderer(this, 8, 18);
		Shape5.addBox(-2F, -30F, -2F, 4, 2, 4);
		Shape5.setRotationPoint(0F, 24F, 0F);
		Shape5.setTextureSize(textureWidth, textureHeight);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, -0.1570796F);
		Shape6 = new ModelRenderer(this, 8, 24);
		Shape6.addBox(-2F, -30F, -2F, 4, 2, 4);
		Shape6.setRotationPoint(0F, 24F, 0F);
		Shape6.setTextureSize(textureWidth, textureHeight);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, -0.2792527F);
	}
	
	public void render() {
		
		Shape1.render(1);
		Shape2.render(1);
		Shape3.render(1);
		Shape4.render(1);
		Shape5.render(1);
		Shape6.render(1);
	}

}
