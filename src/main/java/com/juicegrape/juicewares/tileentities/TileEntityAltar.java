package com.juicegrape.juicewares.tileentities;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

import com.juicegrape.juicewares.items.Items;

public class TileEntityAltar extends TileEntity {

	public boolean isDay;
	public boolean hasStone;
	public boolean stoneDone;
	public boolean hasLens;
	public boolean isNormalLens;
	public int timer;
	private final Random random = new Random();
	
	
	
	public TileEntityAltar() {
		if (worldObj != null) {
			isDay = worldObj.isDaytime();
		} else {
			isDay = true;
		}
		hasStone = false;
		stoneDone = false;
		hasLens = false;
		isNormalLens = true;
		timer = 0;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		hasStone = nbt.getBoolean("hasStone");
		stoneDone = nbt.getBoolean("stoneDone");
		hasLens = nbt.getBoolean("hasLens");
		isNormalLens = nbt.getBoolean("isNormalLens");
		timer = nbt.getInteger("timer");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setBoolean("hasStone", hasStone);
		nbt.setBoolean("stoneDone", stoneDone);
		nbt.setBoolean("hasLens", hasLens);
		nbt.setBoolean("isNormalLens", isNormalLens);
		nbt.setInteger("timer", timer);
	}
	
	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			isDay = worldObj.isDaytime();
			
			if (willWork()) {
				if (isDay && isNormalLens) {
					stoneDone = true;
				} else if (!isDay && !isNormalLens) {
					stoneDone = true;
				}
			} else if (!hasLens || !hasStone) {
				stoneDone = false;
			} else if(stoneDone && hasLens && hasStone) {
				timer++;
				if (timer >= 200) {
					done();
				}
			}
		}
	}
	
	public boolean willWork() {
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		if (isDay && hasLens && isNormalLens && hasStone && !stoneDone) {
			return true;
		} else if(!isDay && hasLens && !isNormalLens && hasStone && !stoneDone) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setStone() {
		if (!worldObj.isRemote) {
			hasStone = true;
			stoneDone = false;
			timer = 0;
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
	
	public void done() {
		if (!worldObj.isRemote) {
			ItemStack stone;
			if (isNormalLens) {
				stone = new ItemStack(Items.enchantmentItem, 1, 2);
			} else {
				stone = new ItemStack(Items.enchantmentItem, 1, 3);
			}

			EntityItem entityStone = createItem(stone);
			worldObj.spawnEntityInWorld(entityStone);
			hasStone = false;
			stoneDone = false;
			hasLens = false;
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			timer = 0;
		}
	}
	
	public void setLens(ItemStack item) {
		ItemStack normal = new ItemStack(Items.lens, 1, 0);
		ItemStack nightVision = new ItemStack(Items.lens, 1, 1);
		if (!worldObj.isRemote) {
			if (item != null) {
				if (item.isItemEqual(normal)) {
					hasLens = true;
					isNormalLens = true;
					timer = 0;
				} else if (item.isItemEqual(nightVision)) {
					hasLens = true;
					isNormalLens = false;
					timer = 0;
				}
			}
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
	
	public void clearLens() {
		if (!worldObj.isRemote) {
			if (hasLens) {
				if (isNormalLens) {
					EntityItem lens = createItem(new ItemStack(Items.lens, 1, 0));
					worldObj.spawnEntityInWorld(lens);
					hasLens = false;
				} else {
					EntityItem lens = createItem(new ItemStack(Items.lens, 1, 1));
					worldObj.spawnEntityInWorld(lens);
					hasLens = false;
				}
			}
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
	
	public void clearStone() {
		if (!worldObj.isRemote) {
			if (hasStone) {
				EntityItem stone = createItem(new ItemStack(Items.enchantmentItem, 1, 1));
				worldObj.spawnEntityInWorld(stone);
				hasStone = false;
			}
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
	
	public boolean shouldSpawnParticles() {
		return stoneDone;
	}

	
	private EntityItem createItem(ItemStack itemStack) {
		float xThang = random.nextFloat() * 0.8F + 0.1F;
		float yThang = random.nextFloat() * 0.8F + 0.1F;
		float zThang = random.nextFloat() * 0.8F + 0.1F;
		EntityItem entityItem = new EntityItem(worldObj, xCoord + xThang, yCoord + yThang, zCoord + zThang, itemStack);
        entityItem.motionX = (float) random.nextGaussian() * 0.05F;
        entityItem.motionY = (float) random.nextGaussian() * 0.05F + 0.2F;
        entityItem.motionZ = (float) random.nextGaussian() * 0.05F;
        return entityItem;
	}
	
    @Override
    public Packet getDescriptionPacket() {
    	NBTTagCompound nbtTag = new NBTTagCompound();
    	writeToNBT(nbtTag);
    	return new Packet132TileEntityData(xCoord, yCoord, zCoord, 1, nbtTag);
    }
    
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData packet) {
    	readFromNBT(packet.data);
    }
	
}
