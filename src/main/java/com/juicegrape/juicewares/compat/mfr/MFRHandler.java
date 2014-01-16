package com.juicegrape.juicewares.compat.mfr;

import powercrystals.minefactoryreloaded.api.FarmingRegistry;

public class MFRHandler {

	
	public static void init() {
		FarmingRegistry.registerHarvestable(new BlazeFlowerMFRCompat());
		FarmingRegistry.registerHarvestable(new HempBlockMFRCompat());
		FarmingRegistry.registerPlantable(new HempItemMFRCompat());
		FarmingRegistry.registerPlantable(new BlazeFlowerSeedsMFRCompat());
	}
}
