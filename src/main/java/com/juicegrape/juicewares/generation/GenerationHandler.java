package com.juicegrape.juicewares.generation;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.juicegrape.juicewares.blocks.BlockInfo;
import com.juicegrape.juicewares.blocks.Blocks;
import com.juicegrape.juicewares.config.Enabling;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class GenerationHandler implements IWorldGenerator {
	
	private WorldGenerator stringReedGen;
	private WorldGenerator genMeatyOre;
	
	public GenerationHandler() {
		GameRegistry.registerWorldGenerator(this);
		stringReedGen = new ReedGen(Blocks.stringreed, BlockInfo.STRINGREED_SPAWN);
		genMeatyOre = new WorldGenMinable(BlockInfo.MEATYORE_ID, 8);
	}

	
	
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId) {
		case -1:
			generateNether(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 1:
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
			break;
		default:
			break;
		}
	}
	
	private void generateSurface(World world, Random random, int chunkX, int chunkZ) {
		if (Enabling.enableReedGen) {
			this.addReed(world, random, chunkX, chunkZ);
		}
		if (Enabling.enableMeatyGen) {
			generateStandardOre(random, chunkX, chunkZ, world, 10, genMeatyOre, 20, 60);
		}
	}
	
	private void generateNether(World world, Random random, int chunkX, int chunkZ) {
		
	}
	
	private void generateEnd(World world, Random random, int chunkX, int chunkZ) {
		
	}
	
	private void generateStandardOre(Random random, int chunkX, int chunkZ, World world, int iterations, WorldGenerator gen, int lowestY, int highestY) {
		for (int i = 0; i < iterations; i++) {
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(highestY - lowestY) + lowestY;
			int z = chunkZ + random.nextInt(16);
			gen.generate(world, random, x, y, z);
		}
	}

	
	
	
	public void addReed(World world, Random random, int chunkX, int chunkZ) {
		for(int i = 0; i < 1; i++){
			int posX = chunkX + random.nextInt(16);
			int posZ = chunkZ + random.nextInt(16);
			int posY = 50 + random.nextInt(25);
			
			stringReedGen.generate(world, random, posX, posY, posZ);
		}
	}
	
	

}
