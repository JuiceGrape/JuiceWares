package juicegrapes.recipes;

import juicegrapes.blocks.Blocks;
import juicegrapes.config.Enabling;
import juicegrapes.items.Items;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class CustomRecipes {
	
	public static boolean checkRegOre(String name) {
		for (String entry : OreDictionary.getOreNames()) {
			if (entry == name) {
				return true;
			}
		}
		return false;
	}

	public static void regCustomModRecipes() {
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(Item.silk),
				new Object[] { new ItemStack(Items.stringreed) }); 
		
		GameRegistry.addRecipe(new ShapedOreRecipe(Blocks.drawer, new Object[] { 	
						"WWW",
						" W ",
						"WWW",

		Character.valueOf('W'), "plankWood"}));

		GameRegistry.addRecipe( new ItemStack(Items.divinghelmet), new Object[] {
				" H ",
				"IGI",
				" B ",
				Character.valueOf('H'), Item.helmetGold,
				Character.valueOf('B'), Block.fenceIron,
				Character.valueOf('I'), Item.ingotIron,
				Character.valueOf('G'), Items.lens
		});
		
		//Easy night vision lens recipe
		if (Enabling.enableEasyNightVisionLensRecipe) {
			if (checkRegOre("gemEmerald")) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.lens, 1, 1),
						new Object[] {	"E",
										"L",
					Character.valueOf('L'), new ItemStack(Items.lens, 1, 0),
					Character.valueOf('E'), "gemEmerald"
					}));
			}
		}
		if (Enabling.enableModerateNightVisionLensRecipe) {
			if (checkRegOre("gemEmerald")) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.lens, 1, 1),
						new Object[] {	" E ",
										"ELE",
										" E ",
					Character.valueOf('L'), new ItemStack(Items.lens, 1, 0),
					Character.valueOf('E'), "gemEmerald"
					}));
			}
		}
		if (Enabling.enableHardNightVisionLensRecipe) {
			if (checkRegOre("gemEmerald")) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.lens, 1, 1),
						new Object[] {	"PEP",
										"ELE",
										"PEP",
					Character.valueOf('L'), new ItemStack(Items.lens, 1, 0),
					Character.valueOf('E'), "gemEmerald",
					Character.valueOf('P'), new ItemStack(Item.potion,1, 8230),
					}));
			}
		}
		if (Enabling.enableSuperHardNightVisionLensRecipe) {
			if (checkRegOre("gemEmerald")) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.lens, 1, 1),
						new Object[] {	"PEP",
										"ELE",
										"PEP",
					Character.valueOf('L'), new ItemStack(Items.lens, 1, 0),
					Character.valueOf('E'), "gemEmerald",
					Character.valueOf('P'), new ItemStack(Item.potion,1, 8262),
					}));
			}
		}
		
		GameRegistry.addRecipe(new ItemStack(Items.nightvisiongoggles), new Object[] {
			"S S",
			"OLO",
			"LIL",
			Character.valueOf('S'), Item.silk,
			Character.valueOf('L'), new ItemStack(Items.lens, 1, 1),
			Character.valueOf('I'), Item.ingotIron,
			Character.valueOf('O'), Block.obsidian
		});
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(Items.enchantmentItem, 1, 0), new Object[] {
			" C ",
			"CIC",
			" C ",
			Character.valueOf('C'), Block.blockClay,
			Character.valueOf('I'), Item.ingotIron
		});
		
		GameRegistry.addRecipe(new ItemStack(Items.enchantmentItem, 1, 1), new Object[] {
			" L ",
			"LGL",
			" L ",
			Character.valueOf('L'), new ItemStack(Item.dyePowder, 1, 4),
			Character.valueOf('G'), Item.ingotGold
		});
		
		GameRegistry.addRecipe(new ItemStack(Blocks.altar), new Object[] {
			"I I",
			"I I",
			"OOO",
			Character.valueOf('I'), Item.ingotIron,
			Character.valueOf('O'), Block.obsidian
		}); 
		
		if (Enabling.enableTimeSpring) {	
			if (checkRegOre("gemEmerald")) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.itemTimeSpring), new Object[] {
				"E ",
				"CI",
				"O ",
				Character.valueOf('E'), "gemEmerald",
				Character.valueOf('C'), Item.pocketSundial,
				Character.valueOf('I'), Item.ingotIron,
				Character.valueOf('O'), Item.eyeOfEnder
				
				}));
			}
		}
		
		
		

		
	}
}
