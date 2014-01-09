package com.juicegrape.juicewares.items;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.oredict.OreDictionary;

import com.juicegrape.juicewares.juicewares;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items {

	public static Item stringreed;
	public static Item divinghelmet;
	public static Item nightvisiongoggles;
	public static Item debugitem;
	public static Item lens;
	public static Item enchantmentItem;
	public static Item itemTimeSpring;
	public static Item blazeflowerseeds;
	
	public static void init() {
		stringreed = new ItemHemp(ItemInfo.STRINGREED_ID);
		GameRegistry.registerItem(stringreed, ItemInfo.STRINGREED_KEY);
		
		divinghelmet = new DivingHelmet(ItemInfo.DIVINGHELMET_ID, EnumArmorMaterial.GOLD, juicewares.proxy.addArmor("Diving"), 0);
		GameRegistry.registerItem(divinghelmet, ItemInfo.DIVINGHELMET_KEY);
		
		nightvisiongoggles = new NightVisionGoggles(ItemInfo.GOGGLES_ID, EnumArmorMaterial.IRON, juicewares.proxy.addArmor("Goggles"), 0);
		GameRegistry.registerItem(nightvisiongoggles, ItemInfo.GOGGLES_KEY);
		
		debugitem = new DebugItem(ItemInfo.DEBUG_ID);
		GameRegistry.registerItem(debugitem, ItemInfo.DEBUG_KEY);
		
		lens = new ItemLens(ItemInfo.LENS_ID);
		GameRegistry.registerItem(lens, ItemInfo.LENS_KEY);
		
		enchantmentItem = new EnchantmentItem(ItemInfo.ENCHANTMENT_ID);
		GameRegistry.registerItem(enchantmentItem, ItemInfo.ENCHANTMENT_KEY);
		
		itemTimeSpring = new ItemTimeSpring(ItemInfo.TIMESPRING_ID);
		GameRegistry.registerItem(itemTimeSpring, ItemInfo.TIMESPRING_KEY);
		
		blazeflowerseeds = new ItemBlazeFlowerSeeds(ItemInfo.BLAZEFLOWERSEEDS_ID);
		GameRegistry.registerItem(blazeflowerseeds, ItemInfo.BLAZEFLOWERSEEDS_KEY);

	}
	
	
	public static void addNames() {
		
		LanguageRegistry.addName(stringreed, ItemInfo.STRINGREED_NAME);
		LanguageRegistry.addName(divinghelmet, ItemInfo.DIVINGHELMET_NAME);
		LanguageRegistry.addName(nightvisiongoggles, ItemInfo.GOGGLES_NAME);
		LanguageRegistry.addName(debugitem, ItemInfo.DEBUG_NAME);
		LanguageRegistry.addName(itemTimeSpring, ItemInfo.TIMESPRING_NAME);
		
		for (int i = 0; i < ItemInfo.LENS_NAMES.length; i++) {
			LanguageRegistry.addName(new ItemStack(lens, 1, i), ItemInfo.LENS_NAMES[i]);
		}
		
		for (int i = 0; i < ItemInfo.ENCHANTMENT_NAMES.length; i++) {
			LanguageRegistry.addName(new ItemStack(enchantmentItem, 1, i), ItemInfo.ENCHANTMENT_NAMES[i]);
		}
		
	
		
	}
	
	
	public static void addOreDictionary() {
		OreDictionary.registerOre("gemEmerald", new ItemStack(Item.emerald));
	}
	
	public static void miscInit() {
		(Block.commandBlock).setCreativeTab(CreativeTabs.tabRedstone);
	}
	
	public static void dungeonLoot() {
		ItemStack divingChest = new ItemStack(Items.divinghelmet);
		divingChest.addEnchantment(Enchantment.aquaAffinity, 1);
		
		ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(new ItemStack(Items.nightvisiongoggles), 1, 1, 5));
		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(Items.nightvisiongoggles), 1, 1, 3));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(divingChest, 1, 1, 2));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(Items.lens, 1, 1),1, 5, 8));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(Items.lens, 1, 0),1, 5, 8));
	}

	

	

				
			 

}
