package juicegrapes.items;

import juicegrapes.juicewares;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTimeSpring extends Item {

	public ItemTimeSpring(int id) {
		super(id);
		setUnlocalizedName(ItemInfo.TIMESPRING_UNLOCALIZED_NAME);
		setCreativeTab(juicewares.juiceTab);
		setMaxStackSize(16);
	}
	
	
//	First code, could be done better I thought
/*	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		if (world != null) {
			player.attackEntityFrom(DamageSource.generic, 2);
			world.setWorldTime(world.getWorldTime() + 6250);
			ItemStack returnStack = itemStack.copy();
			returnStack.stackSize = itemStack.stackSize - 1;
			return returnStack;
		}
		return itemStack;
	} */
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		itemIcon = register.registerIcon(ItemInfo.TEXTURE_LOCATION + ":" + ItemInfo.TIMESPRING_ICON);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));
		return itemStack;
	} 
	
	
	@Override
	public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player) {
		return itemStack;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack itemStack) {
		return 72000;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack itemStack) {
		return EnumAction.bow;
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int itemInUseCount) {
		if (world != null) {
			int usage = getMaxItemUseDuration(itemStack) - itemInUseCount;
			if (usage > 100)
				usage = 100;
			player.attackEntityFrom(DamageSource.generic, usage / 5);
			world.setWorldTime(world.getWorldTime() + 125 * usage);
			itemStack.stackSize--;
			if (itemStack.stackSize <= 0) {
				player.setCurrentItemOrArmor(0, null);
			} else {
				player.setCurrentItemOrArmor(0, itemStack);
			}
				
		} 


	} 
	
	

}
