package com.juicegrape.juicewares.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.juicegrape.juicewares.juicewares;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockMeatyOre extends Block {
	Random random;

	public BlockMeatyOre(int id) {
		
		super(id, Material.rock);
		setStepSound(Block.soundStoneFootstep);
		setUnlocalizedName(BlockInfo.MEATYORE_UNLOCALIZED_NAME);
		setCreativeTab(juicewares.juiceTab);
		setHardness(1.5F);
		random = new Random();
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		blockIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.MEATYORE);
	}

	
	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y,
			int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		int amount = 1 + random.nextInt(fortune + 1);
		for (int i = 0; i < amount; i++) {
			ret.add(decode (BlockInfo.MEATYORE_DROP[random.nextInt(BlockInfo.MEATYORE_DROP.length)]));
		}
		return ret;
	}
	
	
	private ItemStack decode(String code) {
		String[] data = code.split(":");
		int meta;
		if (data[1] != null) {
			meta = Integer.parseInt(data[1]);
		} else {
			meta = 0;
		}
		return new ItemStack(Integer.parseInt(data[0]), 1, meta);
	}
	

}
