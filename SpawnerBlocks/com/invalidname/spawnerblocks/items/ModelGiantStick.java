package com.invalidname.spawnerblocks.items;

import dangerzone.ModelBase;
import dangerzone.ModelRenderer;

public class ModelGiantStick extends ModelBase {
	//fields
	ModelRenderer Shape1;
	
	public ModelGiantStick() {
		int textureWidth = 16;
		
		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(-1F, -14F, -1F, 2, 14, 2);
		Shape1.setRotationPoint(0F, 24F, 0F);
		Shape1.setTextureSize(textureWidth, textureWidth);
		Shape1.mirror = true;  
		setRotation(Shape1, 0F, 0F, 0F);
	}
	
	public void render() {
		
		Shape1.render(1);
  }
}
