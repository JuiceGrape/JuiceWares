package juicegrapes.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderEyeball extends RenderLiving {

	
	public RenderEyeball(ModelBase par1ModelBase, float shadowSize) {
		
		super(par1ModelBase, shadowSize);
	}


	
//public RenderEyeball() {
//	model = new ModelEyeball();
//	this.shadowSize = 0.5F;
//}


	
	private static final ResourceLocation texture = new ResourceLocation("juicewares", "textures/models/eyeball.png");
	
/*	public void renderEyeball(EntityEyeball eyeball, double x, double y, double z, float yaw, float partialTickTime) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glScalef(-1F, -1F, 1F);
		
		bindEntityTexture(eyeball);
		
		model.render(eyeball, 0F, 0F, 0F, 0F, 0F, 0.0625F);
		
		GL11.glPopMatrix();
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTickTime) {
		renderEyeball((EntityEyeball)entity, x, y, z, yaw, partialTickTime);
		
	} */

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}

}
