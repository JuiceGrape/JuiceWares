package com.juicegrape.juicewares.compat.mfr;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.juicegrape.juicewares.blocks.Blocks;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.HarvestType;
import powercrystals.minefactoryreloaded.api.IFactoryHarvestable;

public class HempBlockMFRCompat implements IFactoryHarvestable {

	@Override
	public int getPlantId() {
		return Blocks.stringreed.blockID;
	}

	@Override
	public HarvestType getHarvestType() {
		return HarvestType.LeaveBottom;
	}

	@Override
	public boolean breakBlock() {
		return true;
	}

	@Override
	public boolean canBeHarvested(World world,
			Map<String, Boolean> harvesterSettings, int x, int y, int z) {
		return world.getBlockId(x, y, z) == Blocks.stringreed.blockID;
	}

	@Override
	public List<ItemStack> getDrops(World world, Random rand,
			Map<String, Boolean> harvesterSettings, int x, int y, int z) {
		return Blocks.stringreed.getBlockDropped(world, x, y, z, 1, 0);
	}

	@Override
	public void preHarvest(World world, int x, int y, int z) {
		
	}

	@Override
	public void postHarvest(World world, int x, int y, int z) {
		
	}

}
