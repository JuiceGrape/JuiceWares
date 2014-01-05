package juicegrapes.recipes;

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
	private ItemStack enchanter;
	private Enchantment enchantment;
	private int enchantmentLevel;
	
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
		this.enchanter = enchanter;
		this.enchantment = enchantment;
		this.enchantmentLevel = enchantmentLevel;
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
		return 10;
	}
	
	public ItemStack getRecipeOutput() {
		return output;
	}
}
