package com.juicegrape.juicewares.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockNetherStalk;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.juicegrape.juicewares.items.Items;

public class BlockBlazeFlower extends BlockNetherStalk {
	
	private Random random;
	private Icon[] iconArray;

	protected BlockBlazeFlower(int id) {
		super(id);
		float f1 = 0.125F;
		setBlockBounds(0.5F - f1, 0F, 0.5F - f1, 0.5F + f1, 1.5F * f1, 0.5F + f1);
		setUnlocalizedName(BlockInfo.BLAZEFLOWER_UNLOCALIZED_NAME);
		random = new Random();
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess iBlock, int x, int y, int z) {
		int meta = iBlock.getBlockMetadata(x, y, z);
		float f = 0.0625F;
		float height;
		switch (meta) {
		case 0:
		case 1:
		case 2:
		case 3:
			height = 3F*f;
			break;
		case 4:
		case 5:
		case 6:
		case 7:
			height = 5F*f;
			break;
		case 8:
		case 9:
		case 10:
		case 11:
			height = 7F*f;
			break;
		case 12:
		case 13:
		case 14:
			height = 11F*f;
			break;
		case 15:	
			height = 15F*f;
			break;
		default:
			height = 3F*f;
			break;
		}
		float f1 = 0.125F;
		setBlockBounds(0.5F - f1, 0F, 0.5F - f1, 0.5F + f1, height, 0.5F + f1);
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
		
		if (world.isRaining() && !checkBlockAbove(world, x, y + 1, z)) {
			this.dropBlockAsItem(world, x, y, z, meta, 0);
			world.setBlockToAir(x, y, z);
		}
		
		
		if (meta < 15) {
			int chance = random.nextInt(10);
			if (world.getBlockId(x, y - 2, z) == Block.lavaStill.blockID) {
				world.setBlockMetadataWithNotify(x, y, z, meta + 1, 4);
				if (random.nextInt(100) == 0 ) {
					world.setBlock(x, y - 2, z, Block.obsidian.blockID);
				}
			} else if (chance == 0) {
				world.setBlockMetadataWithNotify(x, y, z, meta + 1, 4);
			}
		}

	}
	
	 @Override
	    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		 if (random.nextInt(10) == 0) {
		    	spawnParticles("flame", world, x, y, z, random);
		 }
	    }
	
	@Override
	public Icon getIcon(int side, int meta) {
		switch (meta) {
		case 0:
		case 1:
		case 2:
		case 3:
			return iconArray[0];
		case 4:
		case 5:
		case 6:
		case 7:
			return iconArray[1];
		case 8:
		case 9:
		case 10:
		case 11:
			return iconArray[2];
		case 12:
		case 13:
		case 14:
			return iconArray[3];
		case 15:	
			return iconArray[4];
		default:
			return iconArray[4];
		}
	}
	
    protected void spawnParticles(String particles, World world, int x, int y, int z, Random random) {
    		float particleX = x + random.nextFloat();
    		float particleY = y + random.nextFloat();
    		float particleZ = z + random.nextFloat();
    		float particleMotionX = 0F;
    		float particleMotionY = 0F;
    		float particleMotionZ = 0F;
    		
    		world.spawnParticle(particles, particleX, particleY, particleZ, particleMotionX, particleMotionY, particleMotionZ);
    }
	
	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
		
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		
		if (metadata >= 15) {
			ret.add(new ItemStack(Items.blazeflowerseeds, 1 + random.nextInt(2), 0));
			ret.add(new ItemStack(Item.blazeRod, 1, 0));
			ret.add(new ItemStack(Item.blazePowder, random.nextInt(2), 0));
		} else {
			ret.add(new ItemStack(Items.blazeflowerseeds, 1, 0));
		}
		
		return ret;
		
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		iconArray = new Icon[5];
		
		for (int i = 0; i < iconArray.length; i++) {
			iconArray[i] = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":blazeflower/" + BlockInfo.BLAZEFLOWER + i);
		}
		
		
	}
	
	private boolean checkBlockAbove(World world, int x, int y, int z) {
		for (int i = 0; i < 128; i++) {
			if (!world.isAirBlock(x, y + i, z)) {
				return true;
			}
		}
		return false;
	}

}
