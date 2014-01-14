package com.juicegrape.juicewares.compat;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import com.juicegrape.juicewares.ModInformation;
import com.juicegrape.juicewares.blocks.Blocks;
import com.juicegrape.juicewares.entities.EntityInfo;
import com.juicegrape.juicewares.items.Items;

import cpw.mods.fml.common.event.FMLInterModComms;

public class ThaumcraftHandler {
	
	public static void init() {
		//Harvestables
		FMLInterModComms.sendMessage("Thaumcraft", "harvestStandardCrop", new ItemStack(Blocks.stringreed, 1, 0));
		FMLInterModComms.sendMessage("Thaumcraft", "harvestStandardCrop", new ItemStack(Blocks.blazeflower, 1, 15));
	}
	
	public static void Postinit() {
		//complex blocks
		ThaumcraftApi.registerComplexObjectTag(Blocks.drawer.blockID, -1,(new AspectList().add(Aspect.VOID, 4)) );
		
		ThaumcraftApi.registerComplexObjectTag(Blocks.altar.blockID, -1,(new AspectList().add(Aspect.CRAFT, 4)) );
		
		//complex items
		ThaumcraftApi.registerComplexObjectTag(Items.divinghelmet.itemID, -1, (new AspectList().add(Aspect.AIR, 4)));
		
		ThaumcraftApi.registerComplexObjectTag(Items.enchantmentItem.itemID, 0, (new AspectList().add(Aspect.WEAPON, 4)));
		
		ThaumcraftApi.registerComplexObjectTag(Items.itemTimeSpring.itemID, -1, (new AspectList().add(Aspect.ELDRITCH, 4).add(Aspect.MAGIC, 6)));
		
		
		
		//normal blocks
		ThaumcraftApi.registerObjectTag(Blocks.meatyore.blockID, -1, (new AspectList().add(Aspect.FLESH, 8).add(Aspect.STONE, 2)));
		
		//normal items
		ThaumcraftApi.registerObjectTag(Items.lens.itemID, 0, (new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.SENSES, 2)));
		ThaumcraftApi.registerObjectTag(Items.lens.itemID, 1, (new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.SENSES, 4).add(Aspect.MAGIC, 2)));
		
		ThaumcraftApi.registerObjectTag(Items.enchantmentItem.itemID, 2, (new AspectList().add(Aspect.GREED, 6).add(Aspect.SENSES, 4).add(Aspect.METAL, 3)));
		ThaumcraftApi.registerObjectTag(Items.enchantmentItem.itemID, 3, (new AspectList().add(Aspect.GREED, 10).add(Aspect.SENSES, 4).add(Aspect.METAL, 3)));
		
		ThaumcraftApi.registerObjectTag(Items.stringreed.itemID, -1, (new AspectList().add(Aspect.PLANT, 1).add(Aspect.EARTH, 1).add(Aspect.AIR, 1)));
		
		ThaumcraftApi.registerObjectTag(Items.blazeflowerseeds.itemID, -1, (new AspectList().add(Aspect.FIRE, 4).add(Aspect.MAGIC, 2).add(Aspect.PLANT, 1)));
		
		//Entities
		ThaumcraftApi.registerEntityTag(ModInformation.ID + "." + EntityInfo.EYEBALL_SYSTEM_NAME, (new AspectList().add(Aspect.MOTION, 4).add(Aspect.SENSES, 4)));

	}

}
