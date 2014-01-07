package com.juicegrape.juicewares.generation;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class ReedGen extends WorldGenerator {
		/** The block to be generated */
	    private Block spawnBlock;
	    
	    /** spawnrate to be used in code and set in config **/
	    private int spawnrate;

	    public ReedGen(Block block, int spawnrate)
	    {
	        this.spawnBlock = block;
	        this.spawnrate = spawnrate;
	    }

	    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	    {
	    	BiomeGenBase c = par1World.getWorldChunkManager().getBiomeGenAt(par3, par5);
	    	if(BiomeDictionary.isBiomeOfType(c, Type.FOREST) || BiomeDictionary.isBiomeOfType(c, Type.PLAINS)) {
	    	
		    	for (int l = 0; l < spawnrate; ++l)
		        {
		            int i1 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
		            int j1 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
		            int k1 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);
		
		            if (par1World.isAirBlock(i1, j1, k1) && spawnBlock.canPlaceBlockAt(par1World, i1, j1, k1))
		            {
		                int l1 = spawnrate + par2Random.nextInt(par2Random.nextInt(2) + 1);
		
		                for (int i2 = 0; i2 < l1; ++i2)
		                {
		                    if (spawnBlock.canBlockStay(par1World, i1, j1 + i2, k1))
		                    {
		                        par1World.setBlock(i1, j1 + i2, k1, spawnBlock.blockID, 0, 2);
		                    }
		                }
		            }
		        }
	        }

	        return true;
	    }
	}

