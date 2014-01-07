package com.juicegrape.juicewares.blocks;

import java.util.Random;

import net.minecraft.block.BlockReed;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

import com.juicegrape.juicewares.items.Items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHemp extends BlockReed {

	public BlockHemp(int id) {
		super(id);
		setUnlocalizedName(BlockInfo.STRINGREED_UNLOCALIZED);	
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		blockIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.STRINGREED);
	}
	
	public int getPlanterID() {
		return Items.stringreed.itemID;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {
		int id = world.getBlockId(x, y - 1, z);
		if (id == blockID) {
			world.setBlockMetadataWithNotify(x, y, z, 0, 4);
		} else {
			world.setBlockMetadataWithNotify(x, y, z, 1, 4);
		}
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		if (canBlockStay(world, x, y, z)) {
			if (world.isAirBlock(x, y + 1, z)) {
				int beneath = world.getBlockId(x, y - 1, z);
				if (beneath != blockID) {
					int meta = world.getBlockMetadata(x, y, z);
					if (meta >= BlockInfo.STRINGREED_GROWTH) {
						world.setBlock(x, y + 1, z, blockID);
						world.setBlockMetadataWithNotify(x, y, z, 0, 4);
						world.setBlockMetadataWithNotify(x, y, z, 5, 4);
					} else {
						world.setBlockMetadataWithNotify(x, y, z, meta + 1, 4);
					}
				}
			}
		} else {
			checkBlockCoordValid(world, x, y, z);
		}
	}

	@Override
	public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant) {
		int plantID = plant.getPlantID(world, x, y + 1, z);
		if (plantID == blockID && blockID == Blocks.stringreed.blockID) {
			return true;
		}
		return super.canSustainPlant(world, x, y, z, direction, plant);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World world, int x, int y, int z) {
		return getPlanterID();
	}
	
	@Override
	public int idDropped(int metadata, Random random, int fortune) {
		return getPlanterID();
	}

	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		return EnumPlantType.Plains;
	}

}
