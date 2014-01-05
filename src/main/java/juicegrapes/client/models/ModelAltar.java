// Date: 13-12-2013 21:53:21
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package juicegrapes.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAltar extends ModelBase
{
  //fields
    ModelRenderer Base;
    ModelRenderer Pillar1;
    ModelRenderer Pillar2;
    ModelRenderer Pillar3;
    ModelRenderer Pillar4;
  
  public ModelAltar()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      Base = new ModelRenderer(this, 0, 0);
      Base.addBox(-8F, 0F, -8F, 14, 1, 14);
      Base.setRotationPoint(1F, 23F, 1F);
      Base.setTextureSize(128, 64);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      Pillar1 = new ModelRenderer(this, 0, 16);
      Pillar1.addBox(0F, 0F, 0F, 1, 10, 1);
      Pillar1.setRotationPoint(-7F, 13F, 6F);
      Pillar1.setTextureSize(128, 64);
      Pillar1.mirror = true;
      setRotation(Pillar1, 0F, 0F, 0F);
      Pillar2 = new ModelRenderer(this, 0, 16);
      Pillar2.addBox(0F, 0F, 0F, 1, 10, 1);
      Pillar2.setRotationPoint(-7F, 13F, -7F);
      Pillar2.setTextureSize(128, 64);
      Pillar2.mirror = true;
      setRotation(Pillar2, 0F, 0F, 0F);
      Pillar3 = new ModelRenderer(this, 0, 16);
      Pillar3.addBox(0F, 0F, 0F, 1, 10, 1);
      Pillar3.setRotationPoint(6F, 13F, 6F);
      Pillar3.setTextureSize(128, 64);
      Pillar3.mirror = true;
      setRotation(Pillar3, 0F, 0F, 0F);
      Pillar4 = new ModelRenderer(this, 0, 16);
      Pillar4.addBox(0F, 0F, 0F, 1, 10, 1);
      Pillar4.setRotationPoint(6F, 13F, -7F);
      Pillar4.setTextureSize(128, 64);
      Pillar4.mirror = true;
      setRotation(Pillar4, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Base.render(f5);
    Pillar1.render(f5);
    Pillar2.render(f5);
    Pillar3.render(f5);
    Pillar4.render(f5);
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
