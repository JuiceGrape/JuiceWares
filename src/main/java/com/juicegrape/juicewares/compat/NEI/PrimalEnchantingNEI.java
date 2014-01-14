package com.juicegrape.juicewares.compat.NEI;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.recipe.ShapelessRecipeHandler;

import com.juicegrape.juicewares.recipes.PrimalEnchantRecipe;

public class PrimalEnchantingNEI extends ShapelessRecipeHandler {

	@Override
	public String getRecipeName() {
		return "Primal Enchanting";
	}
	
	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("crafting") && getClass() == PrimalEnchantingNEI.class) {
			@SuppressWarnings("unchecked")
			List<IRecipe> allrecipes = CraftingManager.getInstance().getRecipeList();
			for(IRecipe irecipe : allrecipes) {
				if (irecipe instanceof PrimalEnchantRecipe) {
					PrimalEnchantRecipe customRecipe = (PrimalEnchantRecipe)irecipe;
					CachedShapelessRecipe recipe = new CachedPrimalEnchantingNEI(customRecipe);
					this.arecipes.add(recipe);
				}
			}
		}
	}
	
	@Override
	public void loadCraftingRecipes(ItemStack result) {
		@SuppressWarnings("unchecked")
		List<IRecipe> allrecipes = CraftingManager.getInstance().getRecipeList();
		for (IRecipe irecipe : allrecipes) {
			if (irecipe instanceof PrimalEnchantRecipe) {
				PrimalEnchantRecipe customRecipe = (PrimalEnchantRecipe)irecipe;
				CachedShapelessRecipe recipe = new CachedPrimalEnchantingNEI(customRecipe);
				if (NEIClientUtils.areStacksSameTypeCrafting(irecipe.getRecipeOutput(), result)) {
					this.arecipes.add(recipe);
				}
			}
		}
	}
	
	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		@SuppressWarnings("unchecked")
		List<IRecipe> allrecipes = CraftingManager.getInstance().getRecipeList();
		for (IRecipe irecipe : allrecipes) {
			if (irecipe instanceof PrimalEnchantRecipe) {
				PrimalEnchantRecipe customRecipe = (PrimalEnchantRecipe)irecipe;
				CachedShapelessRecipe recipe = new CachedPrimalEnchantingNEI(customRecipe);
				if (recipe.contains(recipe.ingredients, ingredient)) {
					recipe.setIngredientPermutation(recipe.ingredients, ingredient);
					this.arecipes.add(recipe);
				}
			}
		}
	}
	
	public class CachedPrimalEnchantingNEI extends CachedShapelessRecipe {
		
		public CachedPrimalEnchantingNEI(PrimalEnchantRecipe recipe) {
			super(recipe.getRecipeOutput());
			setIngredients(recipe);
		}
		
		public void setIngredients(PrimalEnchantRecipe recipe) {
			List<ItemStack> items;
			try {
				items = recipe.items;
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			setIngredients(items);
		}
	}
	
}
