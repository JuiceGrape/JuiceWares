package juicegrapes.recipes;

import juicegrapes.juicewares;
import juicegrapes.compat.MoreEnchantsWrapper;
import juicegrapes.config.Enabling;
import juicegrapes.items.Items;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class PrimalEnchanting {

	public static void init() {
		for (int i = 0; i < Item.itemsList.length; i++) {
			boolean blacklist = false;
			if (Item.itemsList[i] != null) {
				for (int j = 0; j < Enabling.enchant_blacklist.length; j++) {
					if (Item.itemsList[i].itemID == Enabling.enchant_blacklist[j])
						blacklist = true;
				}
			}
			if (!blacklist && Item.itemsList[i] != null) {
				
				ItemStack tool = new ItemStack(Item.itemsList[i]);
				
				if (Enchantment.sharpness.canApply(tool)) {
					GameRegistry.addRecipe(new PrimalEnchantRecipe(Item.itemsList[i], new ItemStack(Items.enchantmentItem, 1, 0), Enchantment.sharpness, 1));
				}
				
				if (Enchantment.fortune.canApply(tool)) {
					GameRegistry.addRecipe(new PrimalEnchantRecipe(Item.itemsList[i], new ItemStack(Items.enchantmentItem, 1, 2), Enchantment.fortune, 1));
					GameRegistry.addRecipe(new PrimalEnchantRecipe(Item.itemsList[i], new ItemStack(Items.enchantmentItem, 1, 3), Enchantment.fortune, 2));
				}
				
				if (Enchantment.looting.canApply(tool)) {
					GameRegistry.addRecipe(new PrimalEnchantRecipe(Item.itemsList[i], new ItemStack(Items.enchantmentItem, 1, 2), Enchantment.looting, 1));
					GameRegistry.addRecipe(new PrimalEnchantRecipe(Item.itemsList[i], new ItemStack(Items.enchantmentItem, 1, 3), Enchantment.looting, 2));
				}
				
				if (Enchantment.unbreaking.canApply(tool)) {
					GameRegistry.addRecipe(new PrimalEnchantRecipe(Item.itemsList[i], Block.obsidian, Enchantment.unbreaking, 1));
				}
				
				if (Enchantment.knockback.canApply(tool)) {
					GameRegistry.addRecipe(new PrimalEnchantRecipe(Item.itemsList[i], Block.pistonBase, Enchantment.knockback, 1));
				}
				
				if (juicewares.compatMoreEnchants != null) {
					if(MoreEnchantsWrapper.venom.canApply(tool)) {
						GameRegistry.addRecipe(new PrimalEnchantRecipe(Item.itemsList[i], new ItemStack(Item.potion, 1, 8260), MoreEnchantsWrapper.venom, 1));
					}
					if(MoreEnchantsWrapper.mending.canApply(tool)) {
						GameRegistry.addRecipe(new PrimalEnchantRecipe(Item.itemsList[i], new ItemStack(Item.potion, 1, 8229), MoreEnchantsWrapper.mending, 1));
					}
				}	
			}
		}
	}
}
