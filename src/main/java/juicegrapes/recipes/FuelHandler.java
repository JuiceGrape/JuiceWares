package juicegrapes.recipes;

import juicegrapes.blocks.Blocks;
import juicegrapes.items.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class FuelHandler implements IFuelHandler {
	
	public FuelHandler() {
		GameRegistry.registerFuelHandler(this);
	}

	@Override
	public int getBurnTime(ItemStack fuel) {
		int id = fuel.itemID;
		
		if (id == Items.stringreed.itemID) {
			return 150;
		} else if (id == Blocks.drawer.blockID){
			return 300;
		} else {
			return 0;
		} 
	}

}
