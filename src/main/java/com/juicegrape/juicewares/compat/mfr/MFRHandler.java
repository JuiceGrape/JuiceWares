package com.juicegrape.juicewares.compat.mfr;

import powercrystals.minefactoryreloaded.api.FactoryRegistry;

public class MFRHandler {

	
	public static void init() {
		FactoryRegistry.registerHarvestable(new BlazeFlowerMFRCompat());
		FactoryRegistry.registerHarvestable(new HempBlockMFRCompat());
		FactoryRegistry.registerPlantable(new HempItemMFRCompat());
		FactoryRegistry.registerPlantable(new BlazeFlowerSeedsMFRCompat());
	}
}
