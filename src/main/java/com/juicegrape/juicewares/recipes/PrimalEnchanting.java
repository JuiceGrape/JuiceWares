package com.juicegrape.juicewares.recipes;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.juicegrape.juicewares.juicewares;
import com.juicegrape.juicewares.compat.MoreEnchantsWrapper;
import com.juicegrape.juicewares.config.Enabling;
import com.juicegrape.juicewares.items.Items;

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
				
				if (Enchantment.knockback.canApply(tool)) {
					GameRegistry.addRecipe(new PrimalEnchantRecipe(Item.itemsList[i], Block.pistonBase, Enchantment.knockback, 1));
				}
				
				if (Enchantment.fireAspect.canApply(tool)) {
					GameRegistry.addRecipe(new PrimalEnchantRecipe(Item.itemsList[i], Item.blazeRod, Enchantment.fireAspect, 1));
				}
				
				if (Enchantment.fireProtection.canApply(tool)) {
					GameRegistry.addRecipe(new PrimalEnchantRecipe(Item.itemsList[i], Item.blazePowder, Enchantment.fireProtection, 1));
				}
				
				if (Enchantment.baneOfArthropods.canApply(tool)) {
					GameRegistry.addRecipe(new PrimalEnchantRecipe(Item.itemsList[i], Item.magmaCream, Enchantment.baneOfArthropods, 1));
				}
				
				if (Enchantment.smite.canApply(tool)) {
					GameRegistry.addRecipe(new PrimalEnchantRecipe(Item.itemsList[i], Item.goldenCarrot, Enchantment.smite, 1));
				}
				
				if (Enchantment.thorns.canApply(tool)) {
					GameRegistry.addRecipe(new PrimalEnchantRecipe(Item.itemsList[i], Block.cactus, Enchantment.thorns, 1));
				}
				
				if (juicewares.compatMoreEnchants != null) {
					if (MoreEnchantsWrapper.venom.canApply(tool)) {
						GameRegistry.addRecipe(new PrimalEnchantRecipe(Item.itemsList[i], new ItemStack(Item.potion, 1, 8260), MoreEnchantsWrapper.venom, 1));
					}
					if (MoreEnchantsWrapper.mending.canApply(tool)) {
						GameRegistry.addRecipe(new PrimalEnchantRecipe(Item.itemsList[i], new ItemStack(Item.potion, 1, 8229), MoreEnchantsWrapper.mending, 1));
					}
					if (MoreEnchantsWrapper.defusing.canApply(tool)) {
						GameRegistry.addRecipe(new PrimalEnchantRecipe(Item.itemsList[i], Item.redstone, MoreEnchantsWrapper.defusing, 1));
					}
				}	
			}
		}	
	}
}
