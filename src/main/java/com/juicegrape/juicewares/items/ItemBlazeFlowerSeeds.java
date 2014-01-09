package com.juicegrape.juicewares.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemSeeds;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

import com.juicegrape.juicewares.juicewares;
import com.juicegrape.juicewares.blocks.Blocks;

public class ItemBlazeFlowerSeeds extends ItemSeeds {

	public ItemBlazeFlowerSeeds(int id) {
		super(id, Blocks.blazeflower.blockID, Block.slowSand.blockID);
		setUnlocalizedName(ItemInfo.BLAZEFLOWERSEEDS_UNLOCALIZED_NAME);
		setCreativeTab(juicewares.juiceTab);
	}
	
	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		return EnumPlantType.Nether;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		itemIcon = register.registerIcon(ItemInfo.TEXTURE_LOCATION + ":" + ItemInfo.BLAZEFLOWERSEEDS_ICON);
	}

}
