package juicegrapes.blocks;

import static net.minecraftforge.common.ForgeDirection.DOWN;
import static net.minecraftforge.common.ForgeDirection.UP;


import java.util.Random;

import juicegrapes.juicewares;
import juicegrapes.tileentities.TileEntityDrawer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDrawer extends BlockContainer {

	public BlockDrawer(int id) {
		super(id, Material.wood);
		setCreativeTab(juicewares.juiceTab);
		setHardness(2F);
		setStepSound(Block.soundWoodFootstep);
		setUnlocalizedName(BlockInfo.DRAWER_UNLOCALIZED_NAME);
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);

	}
	
	public static Random random = new Random();
	private static final ForgeDirection[] validRotationAxes = new ForgeDirection[] { UP, DOWN };

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		blockIcon = Block.planks.getIcon(0, 0);
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
    

    

    /*	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		if (meta == 0) {
			if (side == 3) {
				return frontIcon;
			} else {
				return restIcon;
			}
		} else if (side == meta) {
			return frontIcon;
		} else {
			return restIcon;
		} 

	}
*/
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		if (par1World.isRemote) {
			return true;
		} else {
			IInventory iinventory = this.getInventory(par1World, par2, par3,
					par4);

			if (iinventory != null) {
				par5EntityPlayer.displayGUIChest(iinventory);
			}

			return true;
		}
	}

	public IInventory getInventory(World par1World, int par2, int par3, int par4) {
		Object object = (TileEntityDrawer) par1World.getBlockTileEntity(par2,
				par3, par4);
		
		if (object == null) {
			return null;
		} else {
			return (IInventory) object;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		TileEntityDrawer tileentitydrawer = new TileEntityDrawer();
		return tileentitydrawer;
	}

	/**
	 * Called when the block is placed in the world.
	 */
	  @Override
	    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack itemStack)
	    {
		  int dir = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
	        world.setBlockMetadataWithNotify(x, y, z, dir, 3);
	    }
	  
	   @Override
	    public void breakBlock(World world, int i, int j, int k, int i1, int i2)
	    {
	        TileEntityDrawer tileentitydrawer = (TileEntityDrawer) world.getBlockTileEntity(i, j, k);
	        if (tileentitydrawer != null)
	        {
	            dropContent(0, tileentitydrawer, world, tileentitydrawer.xCoord, tileentitydrawer.yCoord, tileentitydrawer.zCoord);
	        }
	        super.breakBlock(world, i, j, k, i1, i2);
	    }
	   
	   public void dropContent(int newSize, IInventory chest, World world, int xCoord, int yCoord, int zCoord)
	    {
	        for (int l = newSize; l < chest.getSizeInventory(); l++)
	        {
	            ItemStack itemstack = chest.getStackInSlot(l);
	            if (itemstack == null)
	            {
	                continue;
	            }
	            float f = random.nextFloat() * 0.8F + 0.1F;
	            float f1 = random.nextFloat() * 0.8F + 0.1F;
	            float f2 = random.nextFloat() * 0.8F + 0.1F;
	            while (itemstack.stackSize > 0)
	            {
	                int i1 = random.nextInt(21) + 10;
	                if (i1 > itemstack.stackSize)
	                {
	                    i1 = itemstack.stackSize;
	                }
	                itemstack.stackSize -= i1;
	                EntityItem entityitem = new EntityItem(world, (float) xCoord + f, (float) yCoord + (newSize > 0 ? 1 : 0) + f1, (float) zCoord + f2,
	                        new ItemStack(itemstack.itemID, i1, itemstack.getItemDamage()));
	                float f3 = 0.05F;
	                entityitem.motionX = (float) random.nextGaussian() * f3;
	                entityitem.motionY = (float) random.nextGaussian() * f3 + 0.2F;
	                entityitem.motionZ = (float) random.nextGaussian() * f3;
	                if (itemstack.hasTagCompound())
	                {
	                    entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
	                }
	                world.spawnEntityInWorld(entityitem);
	            }
	        }
	    }

	   @Override
	   public ForgeDirection[] getValidRotations(World world, int x, int y, int z) {
		   return validRotationAxes;
	   }
	   
	   @Override
	   public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis) {
		   if (world.isRemote) {
			   return false;
		   }
		   
		   if (axis == UP || axis == DOWN) {
			   int dir = world.getBlockMetadata(x, y, z);
			   int newdir = dir + 1;
			   if (newdir == 4 ) {
				   newdir = 0;   
			   }
			   System.out.println("Old dir: " + dir + " New dir: " + newdir);
			   world.setBlockMetadataWithNotify(x, y, z, newdir, 3);
			   return true;
		   }
		   return false;
	   } 



}
