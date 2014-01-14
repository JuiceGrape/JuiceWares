package com.juicegrape.juicewares.compat.NEI;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIJuicewaresConfig implements IConfigureNEI {

	@Override
	public void loadConfig() {
//		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++TEST");
		API.registerRecipeHandler(new PrimalEnchantingNEI());
		API.registerUsageHandler(new PrimalEnchantingNEI());
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
