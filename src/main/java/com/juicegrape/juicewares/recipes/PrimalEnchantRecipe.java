package com.juicegrape.juicewares.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class PrimalEnchantRecipe implements IRecipe {
	
	private ItemStack output;
	private Item tool;
	private ItemStack toolUsed;
	private ItemStack toolUsed2;
	private ItemStack enchanter;
	private Enchantment enchantment;
	private int enchantmentLevel;
	public ItemStack output2;
	public List<ItemStack> items;
	
	private static ItemStack recipeOutputSetter(ItemStack tool, Enchantment enchantment, int level) {
		int damage = tool.getItemDamage() + ((tool.getMaxDamage() - tool.getItemDamage()) / 10);
		ItemStack out = tool.copy();
		out.setItemDamage(damage);
		out.addEnchantment(enchantment, level);
		return out;
	}
	
	private static List<ItemStack> getUsedItems(ItemStack tool, ItemStack enchanter) {
		List<ItemStack> items = new ArrayList<ItemStack>();
		items.add(tool);
		items.add(enchanter);
		return items;
	}
	
	public PrimalEnchantRecipe(Item tool, Item enchanter, Enchantment enchantment, int enchantmentLevel) {
		this(tool, new ItemStack(enchanter), enchantment, enchantmentLevel);
	}
	
	public PrimalEnchantRecipe(Item tool, Block enchanter, Enchantment enchantment, int enchantmentLevel) {
		this(tool, new ItemStack(enchanter), enchantment, enchantmentLevel);
	}
	
	
	public PrimalEnchantRecipe(Item tool, ItemStack enchanter, Enchantment enchantment, int enchantmentLevel) {
		this.tool = tool;
		output = null;
		toolUsed = null;
		toolUsed2 = new ItemStack(tool);
		this.enchanter = enchanter;
		this.enchantment = enchantment;
		this.enchantmentLevel = enchantmentLevel;
		output2 = recipeOutputSetter(toolUsed2, enchantment, enchantmentLevel);
		items = getUsedItems(toolUsed2, enchanter);
	}
	
	
	
	public boolean matches(InventoryCrafting grid, World world) {
		boolean toolIn = false;
		boolean enchanterIn = false;
		for (int i = 0; i < grid.getSizeInventory(); i++) {
			ItemStack stackInSlot = grid.getStackInSlot(i);
			if (stackInSlot != null) {
				Item itemInStack = stackInSlot.getItem();
				if (itemInStack == tool) {
					if (!toolIn) {
						toolUsed = stackInSlot;
						toolIn = true;
					} else {
						return false;
					}	
				} else if (stackInSlot.isItemEqual(enchanter)) {
					if (!enchanterIn) {
						enchanterIn = true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
		}

		if (toolUsed != null) {
			if (!toolUsed.isItemEnchantable()) {
				return false;
			}
			setOutput();
		}
		return toolIn && enchanterIn && toolUsed != null && output != null;
	}
	
	private void setOutput() {
		int damage = toolUsed.getItemDamage() + ((toolUsed.getMaxDamage() - toolUsed.getItemDamage()) / 10);
		output = new ItemStack(tool, 1, damage);
		output.addEnchantment(enchantment, enchantmentLevel);
	}
	
	public ItemStack getCraftingResult(InventoryCrafting grid) {
		return output.copy();
	}
	
	public int getRecipeSize() {
		return 2;
	}
	
	public ItemStack getRecipeOutput() {
		return output != null ? output : output2;
	}
}
