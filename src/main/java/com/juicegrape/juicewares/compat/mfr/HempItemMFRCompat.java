package com.juicegrape.juicewares.compat.mfr;

import com.juicegrape.juicewares.blocks.Blocks;
import com.juicegrape.juicewares.items.Items;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.IFactoryPlantable;

public class HempItemMFRCompat implements IFactoryPlantable {

	@Override
	public int getSeedId() {
		return Items.stringreed.itemID;
	}

	@Override
	public int getPlantedBlockId(World world, int x, int y, int z,
			ItemStack stack) {
		return Blocks.stringreed.blockID;
	}

	@Override
	public int getPlantedBlockMetadata(World world, int x, int y, int z,
			ItemStack stack) {
		return 0;
	}

	@Override
	public boolean canBePlantedHere(World world, int x, int y, int z,
			ItemStack stack) {
		return Blocks.stringreed.canPlaceBlockAt(world, x, y, z);
	}

	@Override
	public void prePlant(World world, int x, int y, int z, ItemStack stack) {
	}

	@Override
	public void postPlant(World world, int x, int y, int z, ItemStack stack) {	
	}

}
