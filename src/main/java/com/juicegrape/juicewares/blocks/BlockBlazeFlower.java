package com.juicegrape.juicewares.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockNetherStalk;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.juicegrape.juicewares.items.Items;

public class BlockBlazeFlower extends BlockNetherStalk {
	
	private Random random;
	private Icon[] iconArray;

	protected BlockBlazeFlower(int id) {
		super(id);
		setBlockBounds(0, 0, 0, 1F, 1F, 1F);
		setUnlocalizedName(BlockInfo.BLAZEFLOWER_UNLOCALIZED_NAME);
		random = new Random();
	}
	
	@Override
	public int getRenderType() {
		return 1;
	}
	
	@Override
	public int idPicked(World world, int x, int y, int z) {
		return Items.blazeflowerseeds.itemID;
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		
		int meta = world.getBlockMetadata(x, y, z);
		
		if (meta < 15) {
			int chance = random.nextInt(10);
			if (chance == 0) {
				world.setBlockMetadataWithNotify(x, y, z, meta + 1, 4);
			}
		}
	}
	
	@Override
	public Icon getIcon(int side, int meta) {
		switch (meta) {
		case 0:
		case 1:
		case 2:
			return iconArray[0];
		case 3:
		case 4:
		case 5:
			return iconArray[1];
		case 6:
		case 7:
		case 8:
			return iconArray[2];
		case 9:
		case 10:
		case 11:
			return iconArray[3];
		case 12:
		case 13:
		case 14:	
			return iconArray[4];
		case 15:
			return iconArray[5];
		default:
			return iconArray[4];
		}
	}
	
	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
		
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		
		if (metadata >= 15) {
			ret.add(new ItemStack(Items.blazeflowerseeds, 1 + random.nextInt(1), 0));
			ret.add(new ItemStack(Item.blazeRod, 1, 0));
			ret.add(new ItemStack(Item.blazePowder, random.nextInt(2), 0));
		} else {
			ret.add(new ItemStack(Items.blazeflowerseeds, 1, 0));
		}
		
		return ret;
		
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		iconArray = new Icon[6];
		
		for (int i = 0; i < iconArray.length; i++) {
			iconArray[i] = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.BLAZEFLOWER + i);
		}
		
		
	}

}
