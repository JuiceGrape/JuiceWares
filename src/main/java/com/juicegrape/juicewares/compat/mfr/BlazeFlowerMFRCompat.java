package com.juicegrape.juicewares.compat.mfr;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.juicegrape.juicewares.blocks.Blocks;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.HarvestType;
import powercrystals.minefactoryreloaded.api.IFactoryHarvestable;

public class BlazeFlowerMFRCompat implements IFactoryHarvestable {

	@Override
	public int getPlantId() {
		return Blocks.blazeflower.blockID;
	}

	@Override
	public HarvestType getHarvestType() {
		return HarvestType.Normal;
	}

	@Override
	public boolean breakBlock() {
		return true;
	}

	@Override
	public boolean canBeHarvested(World world,
			Map<String, Boolean> harvesterSettings, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z) >= 15;
	}

	@Override
	public List<ItemStack> getDrops(World world, Random rand,
			Map<String, Boolean> harvesterSettings, int x, int y, int z) {
		return Blocks.blazeflower.getBlockDropped(world, x, y, z, 15, 0);
	}

	@Override
	public void preHarvest(World world, int x, int y, int z) {
		
	}

	@Override
	public void postHarvest(World world, int x, int y, int z) {
		
	}

	
	
	
	
}
