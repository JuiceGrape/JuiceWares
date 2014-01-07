package com.juicegrape.juicewares.client.models;

import java.util.ArrayList;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEyeball extends ModelBase {

	private ArrayList<ModelRenderer> parts;
	
	ModelRenderer main;
	
	public ModelEyeball() {
		parts = new ArrayList<ModelRenderer>();
		
		main = new ModelRenderer(this, 0, 0);
		main.addBox(-5, 5, -5, 
					10, 10, 10);
		main.setRotationPoint(0, 9F, 0);
		setRotation(main, 0F, 0F, 0F);
		parts.add(main);
	}
	
	@Override
	public void render(Entity entity, float val1, float val2, float val3, float val4, float val5, float mult) {
		super.render(entity, val1, val2, val3, val4, val5, mult);
		setRotationAngles(val1, val2, val3, val4, val5, mult, entity);
		for(ModelRenderer part : parts) {
			part.render(mult);
		}
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }
	
	public void setRotationAngles(float f1, float f2, float f3, float f4, float f5, float f6, Entity entity)
	  {
	    super.setRotationAngles(f1, f2, f3, f4, f5, f6, entity);


	  }
	
	
	
	
}
