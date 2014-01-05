package juicegrapes.blocks;

import java.util.Random;

import juicegrapes.juicewares;
import juicegrapes.items.ItemLens;
import juicegrapes.items.Items;
import juicegrapes.tileentities.TileEntityAltar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAltar extends BlockContainer {

	protected BlockAltar(int id) {
		super(id, Material.iron);
		setHardness(3F);
		setCreativeTab(juicewares.juiceTab);
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.6875F, 0.9375F);
		setUnlocalizedName(BlockInfo.ALTAR_UNLOCALIZED_NAME);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		blockIcon = Block.obsidian.getIcon(0, 0);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityAltar();
	}
	
	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
	   return false;
	}
    
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
    	TileEntity te = world.getBlockTileEntity(x, y, z);
		if (te instanceof TileEntityAltar) {
			TileEntityAltar altar = (TileEntityAltar)te;
			if (altar.shouldSpawnParticles()) {
				spawnParticles("portal", world, x, y, z, random);
			}
		}
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, int i1, int i2) {
    	TileEntity te = world.getBlockTileEntity(x, y, z);
    	if (te instanceof TileEntityAltar) {
    		TileEntityAltar altar = (TileEntityAltar)te;
    		if (altar.hasLens) {
    			altar.clearLens();
    		}
    		if (altar.hasStone) {
    			altar.clearStone();
    		}
    	}
    }
    
    protected void spawnParticles(String particles, World world, int x, int y, int z, Random random) {
    	for (int i = 0; i < 4; i ++) {
    		float particleX = x + random.nextFloat();
    		float particleY = y + random.nextFloat();
    		float particleZ = z + random.nextFloat();
    		float particleMotionX = -0.5F + random.nextFloat();
    		float particleMotionY = -0.5F + random.nextFloat();
    		float particleMotionZ = -0.5F + random.nextFloat();
    		
    		world.spawnParticle(particles, particleX, particleY, particleZ, particleMotionX, particleMotionY, particleMotionZ);
    	}
    }
    
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    	if (!world.isRemote) {
    		TileEntity te = world.getBlockTileEntity(x, y, z);
    		if (te instanceof TileEntityAltar) {
    			TileEntityAltar altar = (TileEntityAltar)te;
    			if (player.getCurrentEquippedItem() != null) {
	    			if (player.getCurrentEquippedItem().getItem() instanceof ItemLens && !altar.hasLens) {
	    				altar.setLens(player.getCurrentEquippedItem());
	    				ItemStack lens = new ItemStack(player.getCurrentEquippedItem().getItem(), player.getCurrentEquippedItem().stackSize - 1, player.getCurrentEquippedItem().getItemDamage());
	    				player.setCurrentItemOrArmor(0, lens);
	    			} else if (player.getCurrentEquippedItem().isItemEqual(new ItemStack(Items.enchantmentItem, 1, 1))) {
	    				altar.setStone();
	    				ItemStack stone = new ItemStack(player.getCurrentEquippedItem().getItem(), player.getCurrentEquippedItem().stackSize - 1, player.getCurrentEquippedItem().getItemDamage());
	    				player.setCurrentItemOrArmor(0, stone);
	    			}
    			} else {
    				if (player.isSneaking()) {
	    				if (altar.hasLens) {
	    					altar.clearLens();
	    				}
    				} else {
    					if (altar.hasStone) {
    						altar.clearStone();
    					}
    				}
    			}
    		} 

    	}
    	
    	return true;
    }

}
