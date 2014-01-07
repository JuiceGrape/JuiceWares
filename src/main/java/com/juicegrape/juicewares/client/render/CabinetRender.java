package com.juicegrape.juicewares.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import com.juicegrape.juicewares.client.models.ModelCabinet;
import com.juicegrape.juicewares.tileentities.TileEntityDrawer;

public class CabinetRender extends TileEntitySpecialRenderer{
	
	private static final ResourceLocation texture = new ResourceLocation("juicewares:textures/models/cabinet.png");
	
	//The model of your block
    public final ModelCabinet model;
    private final RenderItem customRenderItem;
    private float adj = 0;

    public CabinetRender() {
        this.model = new ModelCabinet();
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
        
        
        if (te instanceof TileEntityDrawer) {
        	RenderItem(te, x, y, z, te.xCoord, te.yCoord, te.zCoord, tick);
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
    
    private void RenderItem(TileEntity te, double x, double y, double z, int xTe, int yTe, int zTe, float tick) {
    	  	
    	TileEntityDrawer ted;
    	
    	if (te instanceof TileEntityDrawer) {
    		ted = (TileEntityDrawer)te;
    	} else {
    		return;
    	}
    		
    	if (!ted.hasWorldObj()
    			|| ted.getDistanceFrom(this.tileEntityRenderer.playerX, this.tileEntityRenderer.playerY, this.tileEntityRenderer.playerZ) > 256d
    			) {
    		return;
    	}
    	
    	
    	int dir = ted.worldObj.getBlockMetadata(xTe, yTe, zTe);



    	//Upper slot
    	if (ted.getStackInSlot(TileEntityDrawer.showSlot) != null) {

            GL11.glPushMatrix();
            GL11.glDisable(2896);
            
            
			GL11.glTranslatef((float) x, (float) y, (float) z);

			EntityItem ghostEntityItem = new EntityItem(ted.worldObj);
			ghostEntityItem.hoverStart = 0f;
			ItemStack customCopy = ted.getStackInSlot(TileEntityDrawer.showSlot);
			customCopy.stackSize = 1;
			ghostEntityItem.setEntityItemStack(customCopy);

			if (customCopy.getItem() instanceof ItemBlock) {
				ItemBlock testItem = (ItemBlock)customCopy.getItem();
				Block testBlock = Block.blocksList[testItem.getBlockID()];
				if (RenderBlocks.renderItemIn3d(testBlock.getRenderType())) {
					GL11.glTranslatef(0.5F, 0.6F, 0.5F);
					GL11.glScalef(1.2F, 1.2F, 1.2F);
					translateByRotation(dir);
				} else {
					itemRenderHandler(dir, 1);
				}
			} else {
				itemRenderHandler(dir, 1);
			} 
			
			
			
			
			GL11.glColor3f(1F, 1F, 1F);
			customRenderItem.doRenderItem(ghostEntityItem, 0, 0, 0, 0, 0);
			
	        GL11.glEnable(2896);
	        GL11.glPopMatrix();
		}
    	
    	
    	//Lower slot
    	if (ted.getStackInSlot(TileEntityDrawer.showSlot2) != null) {
    		
            GL11.glPushMatrix();
            GL11.glDisable(2896);

			GL11.glTranslatef((float) x, (float) y, (float) z);

			EntityItem ghostEntityItem = new EntityItem(ted.worldObj);
			ghostEntityItem.hoverStart = 0f;
			ItemStack customCopy = ted.getStackInSlot(TileEntityDrawer.showSlot2);
			customCopy.stackSize = 1;
			ghostEntityItem.setEntityItemStack(customCopy);

			if (customCopy.getItem() instanceof ItemBlock) {
				ItemBlock testItem = (ItemBlock)customCopy.getItem();
				Block testBlock = Block.blocksList[testItem.getBlockID()];
				if (RenderBlocks.renderItemIn3d(testBlock.getRenderType())) {
					GL11.glTranslatef(0.5F, 0.2F, 0.5F);
					GL11.glScalef(1.1F, 1.1F, 1.1F);
					translateByRotation(dir);
				} else {
					itemRenderHandler(dir, 2);
				}
			} else {
				itemRenderHandler(dir, 2);
			}
			
			GL11.glColor3f(1F, 1F, 1F);
			customRenderItem.doRenderItem(ghostEntityItem, 0, 0, 0, 0, 0);

			
	        GL11.glEnable(2896);
	        GL11.glPopMatrix();
		}

    }
    
    public void translateByRotation(int rotation) {

    	
    	switch (rotation) {
    	case 0: {
    		GL11.glRotatef(90F + adj, 0F, 1F, 0F);
    		return;
    	}
    	case 1: {
    		GL11.glRotatef(0F + adj, 0F, 1F, 0F);
    		return;
    	}
    	case 2: {
    		GL11.glRotatef(-90F + adj, 0F, 1F, 0F);
    		return;
    	}
    	case 3: {
    		GL11.glRotatef(180F + adj, 0F, 1F, 0F);
    		return;
    	}
    	default: {
    		return;
    	}
    	}
    }
    
    public void itemRenderHandler(int dir, int slot) {
    	if (slot == 1) {
	    	GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			GL11.glScalef(0.85F, 0.85F, 0.85F);
			if (Minecraft.getMinecraft().gameSettings.fancyGraphics) {
				translateByRotation(dir);
				GL11.glRotatef(-90F, 0F, 1F, 0F);
			}
    	} else if (slot == 2) {
			GL11.glTranslatef(0.5F, 0.1F , 0.5F);
			GL11.glScalef(0.75F, 0.75F, 0.75F);
			if (Minecraft.getMinecraft().gameSettings.fancyGraphics) {
				translateByRotation(dir);
				GL11.glRotatef(-90F, 0F, 1F, 0F);
			}
    	}
    }
}
