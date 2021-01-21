package com.invalidname.spawnerblocks.items;

import dangerzone.ModelBase;
import dangerzone.ModelRenderer;

public class ModelGiantShovel extends ModelBase {
	//fields
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;
	ModelRenderer Shape10;
	ModelRenderer Shape11;
	ModelRenderer Shape12;
	ModelRenderer Shape13;
	ModelRenderer Shape14;
	ModelRenderer Shape15;
	
	public ModelGiantShovel() {
		
		Shape1 = new ModelRenderer(this, 28, 0);
		Shape1.addBox(-1F, -3F, -1.5F, 2, 2, 3);
		Shape1.setRotationPoint(0F, 24F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 8, 0);
		Shape2.addBox(-1.5F, -6F, 1.5F, 3, 6, 2);
		Shape2.setRotationPoint(0F, 24F, 0F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 18, 0);
		Shape3.addBox(-1.5F, -6F, -3.5F, 3, 6, 2);
		Shape3.setRotationPoint(0F, 24F, 0F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 38, 0);
		Shape4.addBox(-1.5F, -6F, -1.5F, 3, 2, 3);
		Shape4.setRotationPoint(0F, 24F, 0F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 0);
		Shape5.addBox(-1F, -26F, -1F, 2, 20, 2);
		Shape5.setRotationPoint(0F, 24F, 0F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 24, 10);
		Shape6.addBox(-6F, -27F, -1.5F, 12, 1, 3);
		Shape6.setRotationPoint(0F, 24F, 0F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 8, 8);
		Shape7.addBox(5F, -35F, -1.5F, 1, 8, 3);
		Shape7.setRotationPoint(0F, 24F, 0F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 16, 8);
		Shape8.addBox(-6F, -35F, -1.5F, 1, 8, 3);
		Shape8.setRotationPoint(0F, 24F, 0F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 8, 20);
		Shape9.addBox(27.8F, -24.5F, -1.5F, 1, 4, 3);
		Shape9.setRotationPoint(0F, 24F, 0F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, -0.7853982F);
		Shape10 = new ModelRenderer(this, 16, 20);
		Shape10.addBox(-28.8F, -24.5F, -1.5F, 1, 4, 3);
		Shape10.setRotationPoint(0F, 24F, 0F);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0.7853982F);
		Shape11 = new ModelRenderer(this, 0, 28);
		Shape11.addBox(-3.5F, -37.6F, -1.5F, 7, 1, 3);
		Shape11.setRotationPoint(0F, 24F, 0F);
		Shape11.setTextureSize(64, 32);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(this, 24, 14);
		Shape12.addBox(-5F, -35F, 1.5F, 10, 8, 0);
		Shape12.setRotationPoint(0F, 24F, 0F);
		Shape12.setTextureSize(64, 32);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0F);
		Shape13 = new ModelRenderer(this, 24, 22);
		Shape13.addBox(-24F, -28F, 1.5F, 4, 4, 0);
		Shape13.setRotationPoint(0F, 24F, 0F);
		Shape13.setTextureSize(64, 32);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, 0F, 0.7853982F);
		Shape14 = new ModelRenderer(this, 24, 26);
		Shape14.addBox(20F, -28F, 1.5F, 4, 4, 0);
		Shape14.setRotationPoint(0F, 24F, 0F);
		Shape14.setTextureSize(64, 32);
		Shape14.mirror = true;
		setRotation(Shape14, 0F, 0F, -0.7853982F);
		Shape15 = new ModelRenderer(this, 24, 30);
		Shape15.addBox(-3F, -36.6F, 1.5F, 6, 2, 0);
		Shape15.setRotationPoint(0F, 24F, 0F);
		Shape15.setTextureSize(64, 32);
		Shape15.mirror = true;
		setRotation(Shape15, 0F, 0F, 0F);
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
		Shape9.render(1);
		Shape10.render(1);
		Shape11.render(1);
		Shape12.render(1);
		Shape13.render(1);
		Shape14.render(1);
		Shape15.render(1);
	}
}
