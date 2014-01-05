package juicegrapes.blocks;

import java.util.Random;

import juicegrapes.items.Items;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BlockStringReed extends Block implements IPlantable {
	
	
	public static Random generator = new Random();
	public static int r;
	
	public BlockStringReed(int id) {
		super(id, Material.plants);
        float f = 0.375F;
        /**
         * Sets the bounds of the block.  minX, minY, minZ, maxX, maxY, maxZ
         */
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
		this.setTickRandomly(true);
		setStepSound(Block.soundGrassFootstep);
		setUnlocalizedName(BlockInfo.STRINGREED_UNLOCALIZED);
	}	
	
	@Override
	public void registerIcons(IconRegister register) {
		blockIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.STRINGREED);
	}
	

	
	
	public void updateTick(World world, int x, int y, int z, Random random) {
		if (this.canBlockStay(world, x, y, z)) {
			if (world.isAirBlock(x, y + 1, z)) {
					if (world.getBlockId(x, y - 1, z) != Blocks.stringreed.blockID) {
							int i1 = world.getBlockMetadata(x, y, z);
							if (i1 >= BlockInfo.STRINGREED_GROWTH) {
								world.setBlock(x, y + 1, z, Blocks.stringreed.blockID);
								world.setBlockMetadataWithNotify(x, y, z, 0, 4);;
							}
							else {
								world.setBlockMetadataWithNotify(x, y, z, i1 + 1, 4);
							}
					}
			} 
/*			int i1 = world.getBlockMetadata(x, y, z);
			if (i1 == 1) {
				world.setBlock(x, y + 1, z, Blocks.stringreed.blockID);
				world.setBlockMetadataWithNotify(x, y, z, 0, 4);
			} else {
				world.setBlockMetadataWithNotify(x, y, z, i1 + 1, 4);
			} */

		} else {
			this.checkBlockCoordValid(world, x, y, z);
		}
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return this.canBlockStay(world, x, y, z);
	}
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
    {
        if (world.getBlockId(x, y - 1, z) == Blocks.stringreed.blockID){
        	return true;
        }else if (world.getBlockId(x, y - 1, z) == Block.grass.blockID) {
        	return true;
        }else if (world.getBlockId(x, y - 1, z) == Block.dirt.blockID) {
        	return true;
        } else {
        	return false;
        } 
    }
	
	 
	public void onNeighborBlockChange(World world, int x, int y, int z, int par5) {
		this.checkBlockCoordValid(world, x, y, z);
	}
	
	 protected final void checkBlockCoordValid(World world, int x, int y, int z) {
		 if (!this.canBlockStay(world, x, y, z)) {
			 this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			 world.setBlockToAir(x, y, z);
		 }
	 }
	 
	 
	public int idDropped(int par1, Random par2Random, int par3) {
		 return Items.stringreed.itemID;
	}
	
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }
	
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return 1;
    }
	
    @Override
    public EnumPlantType getPlantType(World world, int x, int y, int z)
    {
        return EnumPlantType.Plains;
    }

    @Override
    public int getPlantID(World world, int x, int y, int z)
    {
        return blockID;
    }

    @Override
    public int getPlantMetadata(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }
    
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return Items.stringreed.itemID;
    }
	

}
