package com.juicegrape.juicewares.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {

	public static Block stringreed;
	public static Block drawer;
	public static Block meatyore;
	public static Block altar;
	public static Block blazeflower;
	
	public static void init() {
		
		stringreed = new BlockHemp(BlockInfo.STRINGREED_ID);
		GameRegistry.registerBlock(stringreed, BlockInfo.STRINGREED_KEY);
		
		drawer = new BlockDrawer(BlockInfo.DRAWER_ID);
		GameRegistry.registerBlock(drawer, BlockInfo.DRAWER_KEY);
		
		meatyore = new BlockMeatyOre(BlockInfo.MEATYORE_ID);
		GameRegistry.registerBlock(meatyore, BlockInfo.MEATYORE_KEY);
		
		altar = new BlockAltar(BlockInfo.ALTAR_ID);
		GameRegistry.registerBlock(altar, BlockInfo.ALTAR_KEY);
		
		blazeflower = new BlockBlazeFlower(BlockInfo.BLAZEFLOWER_ID);
		GameRegistry.registerBlock(blazeflower, BlockInfo.BLAZEFLOWER_KEY);
		
		

	}
	
	public static void addNames() {
		
		LanguageRegistry.addName(stringreed, BlockInfo.STRINGREED_NAME);
		
		LanguageRegistry.addName(drawer, BlockInfo.DRAWER_NAME);
		
		LanguageRegistry.addName(meatyore, BlockInfo.MEATYORE_NAME);
		
		LanguageRegistry.addName(altar, BlockInfo.ALTAR_NAME);
	}
	
	public static void addOreDict() {
		
		OreDictionary.registerOre("oreMeat", meatyore);
		
	}
}
