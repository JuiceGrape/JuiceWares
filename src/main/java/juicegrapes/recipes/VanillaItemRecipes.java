package juicegrapes.recipes;

import juicegrapes.config.Enabling;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class VanillaItemRecipes {


	
	public static void regCustomVanillaRecipes() {
		
		if (Enabling.enableWoolToString) {
			GameRegistry.addShapelessRecipe(new ItemStack(Item.silk, RecipeInfo.WOOL_TO_STRING), new Object[] { new ItemStack(Block.cloth, 1, OreDictionary.WILDCARD_VALUE) });
		}

		if (Enabling.enableDiscRecipes) {

			GameRegistry.addRecipe(
					new ItemStack(Item.record13),
					new Object[] { "OOO", "O#O", "OOO",

					Character.valueOf('O'), Block.obsidian,
							Character.valueOf('#'), Item.ingotGold });
	

			GameRegistry.addRecipe(
					new ItemStack(Item.recordBlocks),
					new Object[] { "OOO", "O#O", "OOO",

					Character.valueOf('O'), Block.obsidian,
							Character.valueOf('#'), Block.netherBrick });

			GameRegistry.addRecipe(
					new ItemStack(Item.recordChirp),
					new Object[] { "OOO", "O#O", "OOO",

					Character.valueOf('O'), Block.obsidian,
							Character.valueOf('#'), Item.redstone });
			
			if (CustomRecipes.checkRegOre("gemEmerald")) {
				GameRegistry.addRecipe(new ShapedOreRecipe(Item.recordCat,
						new Object[] {	"OOO",
										"O#O",
										"OOO",
	
						Character.valueOf('O'), Block.obsidian,
						Character.valueOf('#'), "gemEmerald"
					}));
			}

			GameRegistry.addRecipe(
					new ItemStack(Item.recordFar),
					new Object[] { "OOO", "O#O", "OOO",

					Character.valueOf('O'), Block.obsidian,
							Character.valueOf('#'), Item.slimeBall });

			GameRegistry.addRecipe(
					new ItemStack(Item.recordMellohi),
					new Object[] { "OOO", "O#O", "OOO",

					Character.valueOf('O'), Block.obsidian,
							Character.valueOf('#'), Item.cake });

			GameRegistry.addRecipe(new ItemStack(Item.recordStal),
					new Object[] { "OOO", "OOO", "OOO",

					Character.valueOf('O'), Block.obsidian

					});

			GameRegistry.addRecipe(
					new ItemStack(Item.recordStrad),
					new Object[] { "OOO", "O#O", "OOO",

					Character.valueOf('O'), Block.obsidian,
							Character.valueOf('#'), Block.blockGold });

			GameRegistry.addRecipe(
					new ItemStack(Item.recordWard),
					new Object[] { "OOO", "O#O", "OOO",

					Character.valueOf('O'), Block.obsidian,
							Character.valueOf('#'), Block.blockEmerald });

			GameRegistry.addRecipe(
					new ItemStack(Item.recordWait),
					new Object[] { "OOO", "O#O", "OOO",

					Character.valueOf('O'), Block.obsidian,
							Character.valueOf('#'), Item.diamond });
			
			
			if (CustomRecipes.checkRegOre("dyeBlue")) {
				GameRegistry.addRecipe(new ShapedOreRecipe(Item.recordMall,
						new Object[] { "OOO", "O#O", "OOO",
	
						Character.valueOf('O'), Block.obsidian,
						Character.valueOf('#'), "dyeBlue"
						}));
			}

//			GameRegistry.addSmelting(Item.recordStal.itemID, new ItemStack(
//					Item.record11), 0);
			
			FurnaceRecipes.smelting().addSmelting(Item.recordStal.itemID, new ItemStack(Item.record11), 0);

		}

		if (Enabling.enableSaddleRecipe) {

			GameRegistry.addRecipe(new ItemStack(Item.saddle),
					new Object[] {	"S L",
									"LLL",
									"LIL",
									Character.valueOf('S'), Item.silk,
									Character.valueOf('L'), Item.leather,
									Character.valueOf('I'), Item.ingotIron
					});

		}

		if (Enabling.enableNameTagRecipe) {

			GameRegistry.addRecipe(new ItemStack(Item.nameTag),
					new Object[] {	" S",
									"P ",
									Character.valueOf('S'), Item.silk,
									Character.valueOf('P'), Item.paper
					});

		}

	}
}
