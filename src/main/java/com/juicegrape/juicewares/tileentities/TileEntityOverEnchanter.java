package com.juicegrape.juicewares.tileentities;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityOverEnchanter extends TileEntity {
	
	
	public ItemStack tool;
	
	
	public TileEntityOverEnchanter() {
		tool = null;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
	}
	
	
	
	
}
