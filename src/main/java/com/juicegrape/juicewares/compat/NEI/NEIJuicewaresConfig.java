package com.juicegrape.juicewares.compat.NEI;

import com.juicegrape.juicewares.blocks.Blocks;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIJuicewaresConfig implements IConfigureNEI {

	@Override
	public void loadConfig() {
		API.registerRecipeHandler(new PrimalEnchantingNEI());
		API.registerUsageHandler(new PrimalEnchantingNEI());
		API.hideItem(Blocks.blazeflower.blockID);
		API.hideItem(Blocks.stringreed.blockID);
	}

	@Override
	public String getName() {
		return "Primal Enchanting Config";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}

}
