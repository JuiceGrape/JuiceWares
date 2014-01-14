package com.juicegrape.juicewares.items;

import java.util.List;
import java.util.Random;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.juicegrape.juicewares.juicewares;
import com.juicegrape.juicewares.config.Enabling;
import com.juicegrape.juicewares.misc.CustomDamageSource;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTimeSpring extends Item {

	Random random;
	
	public ItemTimeSpring(int id) {
		super(id);
		setUnlocalizedName(ItemInfo.TIMESPRING_UNLOCALIZED_NAME);
		setCreativeTab(juicewares.juiceTab);
		setMaxStackSize(1);
		setMaxDamage(16);
		random = new Random();
	}
	
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
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemStack, int pass) {
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean useExtraInfo) {
		if (!Enabling.enableTimeSpring) {
			info.add("DISABLED BY CONFIG");
		}
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int itemInUseCount) {
		if (world != null) {
			if (Enabling.enableTimeSpring) {
				int usage = getMaxItemUseDuration(itemStack) - itemInUseCount;
				if (usage > 100)
					usage = 100;
				world.setWorldTime(world.getWorldTime() + 125 * usage);
				itemStack.setItemDamage(itemStack.getItemDamage() + 1);;
				if (itemStack.getItemDamage() >= 16) {
					player.setCurrentItemOrArmor(0, null);
				} else {
					player.setCurrentItemOrArmor(0, itemStack);
				}
				world.playSoundAtEntity(player, "juicewares:timeclock", 1.0F, random.nextFloat());	
				player.attackEntityFrom(CustomDamageSource.timeWound, usage / 5);
			} 
		}


	} 
	
	

}
