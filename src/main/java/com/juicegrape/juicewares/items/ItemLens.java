package com.juicegrape.juicewares.items;


import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.juicegrape.juicewares.juicewares;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLens extends Item{

	public ItemLens(int id) {
		super(id);
		setCreativeTab(juicewares.juiceTab);
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return ItemInfo.LENS_UNLOCALIZED_NAME + itemStack.getItemDamage();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		itemIcon = register.registerIcon(ItemInfo.TEXTURE_LOCATION + ":" + ItemInfo.LENS_ICON);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemStack, int par2)
    {
		if (itemStack.getItem() instanceof Item) {
			
		} else {
			return super.getColorFromItemStack(itemStack, par2);	
		}
		
		if (itemStack.getItemDamage() == 1) {
			return (60 << 16) | (180 << 8) | 60;
		} else {
			return super.getColorFromItemStack(itemStack, par2);
		}
    }
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs tab, List list) {
		for (int i = 0; i < ItemInfo.LENS_NAMES.length; i++) {
			ItemStack stack = new ItemStack(id, 1, i);
			
			list.add(stack);
		}

		
	}
	


}
