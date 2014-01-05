package juicegrapes.client.render;

import juicegrapes.client.models.ModelAltar;
import juicegrapes.items.Items;
import juicegrapes.tileentities.TileEntityAltar;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class AltarRender extends TileEntitySpecialRenderer{
	
	
	public final ModelAltar model;
	private final RenderItem customRenderItem;
	
	private static final ResourceLocation texture = new ResourceLocation("juicewares:textures/models/altar.png");
	
	public AltarRender() {
		this.model = new ModelAltar();
		
		customRenderItem = new RenderItem() {
        	@Override
        	public boolean shouldBob() {
        		return false;
        	}
        	
        	@Override
        	public boolean shouldSpreadItems() {
        		return false;
        	}
        	
        	@Override
        	public byte getMiniBlockCount(ItemStack stack) {
        		return 1;
        	}
        	
        	@Override
        	public byte getMiniItemCount(ItemStack stack) {
        		return 1;
        	} 
        };
        
        customRenderItem.setRenderManager(RenderManager.instance);
		
	}
	
	@Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float tick) {
    	this.bindTexture(texture);
        //The PushMatrix tells the renderer to "start" doing something.
        GL11.glPushMatrix();

        //This is setting the initial location.
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        //This is the texture of your block. It's pathed to be the same place as your other blocks here.
        //This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!    

        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);


        adjustLightFixture(te.worldObj, te.xCoord, te.yCoord, te.zCoord, te.blockType);
        
        

        //A reference to your Model file. Again, very important.
        //Tell it to stop rendering for both the PushMatrix's
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        
        if (te instanceof TileEntityAltar) {
        	renderItem((TileEntityAltar)te, x, y, z, te.xCoord, te.yCoord, te.zCoord);
        }

    }
	
	//Set the lighting stuff, so it changes it's brightness properly.       
    private void adjustLightFixture(World world, int i, int j, int k, Block block) {
        //  the if statement checking for if the world is null or not if not renders the block if null renders the item model
        // != means not equal
        if (world != null) {
            int dir = world.getBlockMetadata(i, j, k);

            GL11.glPushMatrix();
            //This line actually rotates the renderer.
            GL11.glRotatef(dir * (90F), 0F, 1F, 0F);

            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            /*
             * Place your rendering code here.
             */

            
            GL11.glPopMatrix();
        }
        else {
            GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        }

    }
    
    private void renderItem(TileEntityAltar altar, double x, double y, double z, int xTe, int yTe, int zTe) {
    	
    	if (!altar.hasWorldObj()
    			|| altar.getDistanceFrom(this.tileEntityRenderer.playerX, this.tileEntityRenderer.playerY, this.tileEntityRenderer.playerZ) > 256d
    			) {
    		return;
    	}
    	if (altar.hasStone) {
	    	GL11.glPushMatrix();
	        GL11.glDisable(2896);
	        
	        GL11.glTranslatef((float) x, (float) y, (float) z);
	    	
	    	EntityItem ghostEntityItem = new EntityItem(altar.worldObj);
			ghostEntityItem.hoverStart = 0f;
			ItemStack customCopy = new ItemStack(Items.enchantmentItem, 1, 1);
			customCopy.stackSize = 1;
			ghostEntityItem.setEntityItemStack(customCopy);
			
			GL11.glTranslatef(0.5F, 0.125F , 0.5F);
			
			GL11.glColor3f(1F, 1F, 1F);
			customRenderItem.doRenderItem(ghostEntityItem, 0, 0, 0, 0, 0);
			
	        GL11.glEnable(2896);
	        GL11.glPopMatrix();
    	}
    	
    	if (altar.hasLens) {
    	    GL11.glPushMatrix();
    	    GL11.glDisable(2896);
    	       
    	    GL11.glTranslatef((float) x, (float) y, (float) z);
    	    	
    	    EntityItem ghostEntityItem = new EntityItem(altar.worldObj);
    		ghostEntityItem.hoverStart = 0f;
    		ItemStack customCopy;
    		if (altar.isNormalLens) {
    			customCopy = new ItemStack(Items.lens, 1, 0);
    		} else {
    			customCopy = new ItemStack(Items.lens, 1, 1);
    		}
    		customCopy.stackSize = 1;
    		ghostEntityItem.setEntityItemStack(customCopy);
    		
    		GL11.glTranslatef(0.5F, 0.75F , 0.5F);
    		
    		GL11.glColor3f(1F, 1F, 1F);
    		customRenderItem.doRenderItem(ghostEntityItem, 0, 0, 0, 0, 0);
    			
    	    GL11.glEnable(2896);
    	    GL11.glPopMatrix();
        	}
    	
    }

}
