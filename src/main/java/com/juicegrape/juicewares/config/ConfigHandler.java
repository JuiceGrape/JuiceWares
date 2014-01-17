package com.juicegrape.juicewares.config;

import java.io.File;

import net.minecraftforge.common.Configuration;

import com.juicegrape.juicewares.blocks.BlockInfo;
import com.juicegrape.juicewares.entities.EntityInfo;
import com.juicegrape.juicewares.items.ItemInfo;
import com.juicegrape.juicewares.recipes.RecipeInfo;

public class ConfigHandler {
	
	
	public static void init(File file) {
		Configuration config = new Configuration(file);
		
		config.load();
		
		//Blocks
		BlockInfo.STRINGREED_ID = config.getBlock(BlockInfo.STRINGREED_KEY, BlockInfo.STRINGREED_DEFAULT).getInt();
		BlockInfo.DRAWER_ID = config.getBlock(BlockInfo.DRAWER_KEY, BlockInfo.DRAWER_DEFAULT).getInt();
		BlockInfo.MEATYORE_ID = config.getBlock(BlockInfo.MEATYORE_KEY, BlockInfo.MEATYORE_DEFAULT).getInt();
		BlockInfo.ALTAR_ID = config.getBlock(BlockInfo.ALTAR_KEY, BlockInfo.ALTAR_DEFAULT).getInt();
		BlockInfo.BLAZEFLOWER_ID = config.getBlock(BlockInfo.BLAZEFLOWER_KEY, BlockInfo.BLAZEFLOWER_DEFAULT).getInt();
		
		
		//Items
		ItemInfo.STRINGREED_ID = config.getItem(ItemInfo.STRINGREED_KEY, ItemInfo.STRINGREED_DEFAULT).getInt() - 256;
		ItemInfo.DIVINGHELMET_ID = config.getItem(ItemInfo.DIVINGHELMET_KEY, ItemInfo.DIVINGHELMET_DEFAULT).getInt() - 256;
		ItemInfo.GOGGLES_ID = config.getItem(ItemInfo.GOGGLES_KEY, ItemInfo.GOGGLES_DEFAULT).getInt() - 256;
		ItemInfo.DEBUG_ID = config.getItem(ItemInfo.DEBUG_KEY, ItemInfo.DEBUG_DEFAULT).getInt() - 256;
		ItemInfo.LENS_ID = config.getItem(ItemInfo.LENS_KEY, ItemInfo.LENS_DEFAULT).getInt() - 256;
		ItemInfo.ENCHANTMENT_ID = config.getItem(ItemInfo.ENCHANTMENT_KEY, ItemInfo.ENCHANTMENT_DEFAULT).getInt() - 256;
		ItemInfo.TIMESPRING_ID = config.getItem(ItemInfo.TIMESPRING_KEY, ItemInfo.TIMESPRING_DEFAULT).getInt() - 256;
		ItemInfo.BLAZEFLOWERSEEDS_ID = config.getItem(ItemInfo.BLAZEFLOWERSEEDS_KEY, ItemInfo.BLAZEFLOWERSEEDS_DEFAULT).getInt() - 256;
		
		//Recipes
		RecipeInfo.WOOL_TO_STRING = config.get("Recipes", RecipeInfo.WOOL_KEY, RecipeInfo.WOOL_STRING_DEF).getInt();
		Enabling.enableEasyNightVisionLensRecipe = config.get("Recipes", "Easy night vision lens recipe", false).getBoolean(false);
		Enabling.enableModerateNightVisionLensRecipe = config.get("Recipes", "Moderate night vision lens recipe", false).getBoolean(false);
		Enabling.enableHardNightVisionLensRecipe = config.get("Recipes", "Hard night vision lens recipe", false).getBoolean(false);
		Enabling.enableSuperHardNightVisionLensRecipe = config.get("Recipes", "Super hard night vision lens recipe", false).getBoolean(false);
		Enabling.enableDiscRecipes = config.get("Recipes", "Disc Recipes", true).getBoolean(true);
		Enabling.enableNameTagRecipe = config.get("Recipes", "Name tag recipe", true).getBoolean(true);
		Enabling.enableSaddleRecipe = config.get("Recipes", "Saddle recipe", true).getBoolean(true);
		Enabling.enableWoolToString = config.get("Recipes", "Wool to string recipes", true).getBoolean(true);
		
		//worldgen
		BlockInfo.STRINGREED_SPAWN = config.get("Worldgen", BlockInfo.STRINGREED_SPAWN_KEY, BlockInfo.STRINGREED_SPAWN_DEF).getInt();
		Enabling.enableReedGen = config.get("Worldgen", "Reed Generation", true).getBoolean(true);
		Enabling.enableMeatyGen = config.get("Worldgen", "Meaty Ore Generation", true).getBoolean(true);
		
		//misc
		BlockInfo.STRINGREED_GROWTH = config.get("Misc", BlockInfo.STRINGREED_GROWTH_KEY, BlockInfo.STRINGREED_GROWTH_DEF).getInt();
		BlockInfo.MEATYORE_DROP = config.get("Misc", "Meatyore item drop list. code: ItemID:DamageValue", BlockInfo.MEATYORE_DROP_DEFAULT).getStringList();
		Enabling.enchant_blacklist = config.get("Misc", "Primal Enchanting blacklist", Enabling.enchant_blacklistDef).getIntList();
		Enabling.mysteryOption = config.get("Misc", "For ultimate fun", false).getBoolean(false);
		Enabling.enableTimeSpring = config.get("Misc", "Enable Timewound Clock", true).getBoolean(false);
		
		//Spawns
		EntityInfo.EYEBALL_SPAWN = config.get("Spawns", EntityInfo.EYEBALL_SPAWN_CONF, EntityInfo.EYEBALL_SPAWN_DEF).getBoolean(EntityInfo.EYEBALL_SPAWN_DEF);
		EntityInfo.EYEBALL_SPAWNRATE = config.get("Spawns", EntityInfo.EYEBALL_SPAWNRATE_CONF, EntityInfo.EYEBALL_SPAWNRATE_DEF).getInt();
		
		
		
		config.save();
	}
}
