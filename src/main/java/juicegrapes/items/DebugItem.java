package juicegrapes.items;

import juicegrapes.juicewares;
import juicegrapes.entities.EntityEyeball;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DebugItem extends Item {

	public DebugItem(int par1) {
		super(par1);
		setUnlocalizedName(ItemInfo.DEBUG_UNLOCALIZED_NAME);
		setCreativeTab(juicewares.juiceTab);
	}
	
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		itemIcon = register.registerIcon(ItemInfo.TEXTURE_LOCATION + ":" + ItemInfo.DEBUG_ICON);
	}
	
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		if (world != null && !world.isRemote) {
			System.out.println(world.getWorldTime());
		}
		return itemStack;
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		int blockId = world.getBlockId(x, y, z);
		Block block = Block.blocksList[blockId];
		
		if(block == null) {
			System.out.println("Not a block");
			return false;
		}
		
		if (block.rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side))) {
			player.swingItem();
			return !world.isRemote;
		}
		return false;

	}

	
	@SuppressWarnings("unused")
	private void spawnEyeball(World world, int x, int y, int z, int amount) {
		for (int i = 0; i < amount; i ++) {
			Entity entity = new EntityEyeball(world);
			
			EntityLiving entityliving = (EntityLiving)entity;
	        entity.setLocationAndAngles(x + 0.5, y + 1, z + 0.5, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
	        entityliving.rotationYawHead = entityliving.rotationYaw;
	        entityliving.renderYawOffset = entityliving.rotationYaw;
	        entityliving.onSpawnWithEgg((EntityLivingData)null);
	        world.spawnEntityInWorld(entity);
	        entityliving.playLivingSound();
		}
	}

}
