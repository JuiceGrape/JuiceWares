package com.juicegrape.juicewares.potionEffects;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CustomPotions extends Potion {

	public CustomPotions(int par1, boolean par2, int par3) {
		super(par1, par2, par3);
	}
	
	private static final ResourceLocation INV_ICONS = new ResourceLocation("juicewares:textures/icons/inv_potion_icons.png");
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasStatusIcon() 
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(INV_ICONS);
		
	    return true;
	}

}
