package juicegrapes.tileentities;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import juicegrapes.ModInformation;
import juicegrapes.blocks.BlockInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityDrawer extends TileEntity implements IInventory
{
    private ItemStack[] drawerContents;
    private int invSize = 18;
    public static final int showSlot = 0;
    public static final int showSlot2 = 9;

    /** The number of players currently using this chest */
    public int numUsingPlayers;

    /** Server sync counter (once per 20 ticks) */
    private String customName;

    public TileEntityDrawer()
    {
    	drawerContents = new ItemStack[invSize];
    }

    @SideOnly(Side.CLIENT)
    public TileEntityDrawer(int par1)
    {
    	drawerContents = new ItemStack[invSize];
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return invSize;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int par1)
    {
        return drawerContents[par1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.drawerContents[par1] != null)
        {
            ItemStack itemstack;

            if (this.drawerContents[par1].stackSize <= par2)
            {
                itemstack = this.drawerContents[par1];
                this.drawerContents[par1] = null;
                this.onInventoryChanged();
                return itemstack;
            }
            else
            {
                itemstack = this.drawerContents[par1].splitStack(par2);

                if (this.drawerContents[par1].stackSize == 0)
                {
                    this.drawerContents[par1] = null;
                }

                this.onInventoryChanged();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.drawerContents[par1] != null)
        {
            ItemStack itemstack = this.drawerContents[par1];
            this.drawerContents[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }
    
    @Override
    public void onInventoryChanged() {
    	super.onInventoryChanged();
    	worldObj.updateAllLightTypes(xCoord, yCoord, zCoord);
    	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.drawerContents[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return this.isInvNameLocalized() ? this.customName : BlockInfo.DRAWER_NAME;
    }
    
    @Override
    public Packet getDescriptionPacket() {
    	ItemStack itemStack = getStackInSlot(showSlot);
    	ItemStack itemStack2 = getStackInSlot(showSlot2);
    	ByteArrayOutputStream bos;
    	DataOutputStream outputStream;
    	
    	if (itemStack != null) {
    		if (itemStack2 != null) {
    	    	bos = new ByteArrayOutputStream(37);
    	    	outputStream = new DataOutputStream(bos);
    	    	
    	    	try {
    	    		outputStream.writeByte(1);
    	    		outputStream.writeInt(xCoord);
    	    		outputStream.writeInt(yCoord);
    	    		outputStream.writeInt(zCoord);
    	    		outputStream.writeInt(itemStack.itemID);
    	    		outputStream.writeInt(itemStack.getItemDamage());
    	    		outputStream.writeInt(itemStack.stackSize);
    	    		outputStream.writeInt(itemStack2.itemID);
    	    		outputStream.writeInt(itemStack2.getItemDamage());
    	    		outputStream.writeInt(itemStack2.stackSize);
    	    	} catch (Exception ex) {
    	    		ex.printStackTrace();
    	    	}
    	    	
    		} else {
    			bos = new ByteArrayOutputStream(25);
    	    	outputStream = new DataOutputStream(bos);
    	    	
    	    	try {
    	    		outputStream.writeByte(2);
    	    		outputStream.writeInt(xCoord);
    	    		outputStream.writeInt(yCoord);
    	    		outputStream.writeInt(zCoord);
    	    		outputStream.writeInt(itemStack.itemID);
    	    		outputStream.writeInt(itemStack.getItemDamage());
    	    		outputStream.writeInt(itemStack.stackSize);
    	    	} catch (Exception ex) {
    	    		ex.printStackTrace();
    	    	}
    	    	
    	    	
    		}
    	} else if (itemStack2 != null) {
    		bos = new ByteArrayOutputStream(25);
	    	outputStream = new DataOutputStream(bos);
	    	
	    	try {
	    		outputStream.writeByte(3);
	    		outputStream.writeInt(xCoord);
	    		outputStream.writeInt(yCoord);
	    		outputStream.writeInt(zCoord);
	    		outputStream.writeInt(itemStack2.itemID);
	    		outputStream.writeInt(itemStack2.getItemDamage());
	    		outputStream.writeInt(itemStack2.stackSize);
	    	} catch (Exception ex) {
	    		ex.printStackTrace();
	    	}
    	} else {
    		bos = new ByteArrayOutputStream(13);
	    	outputStream = new DataOutputStream(bos);
	    	
	    	try {
	    		outputStream.writeByte(4);
	    		outputStream.writeInt(xCoord);
	    		outputStream.writeInt(yCoord);
	    		outputStream.writeInt(zCoord);

	    	} catch (Exception ex) {
	    		ex.printStackTrace();
	    	}
    	}
    	
    	Packet250CustomPayload packet = new Packet250CustomPayload();
    	packet.channel = ModInformation.CHANNEL;
    	packet.data = bos.toByteArray();
    	packet.length = bos.size();
    	return packet;
    	
    } 

    /**
     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
     * language. Otherwise it will be used directly.
     */
    public boolean isInvNameLocalized()
    {
        return this.customName != null && this.customName.length() > 0;
    }

    /**
     * Sets the custom display name to use when opening a GUI for this specific TileEntityChest.
     */
    public void setChestGuiName(String par1Str)
    {
        this.customName = par1Str;
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        this.drawerContents = new ItemStack[this.getSizeInventory()];

        if (par1NBTTagCompound.hasKey("CustomName"))
        {
            this.customName = par1NBTTagCompound.getString("CustomName");
        }

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.drawerContents.length)
            {
                this.drawerContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.drawerContents.length; ++i)
        {
            if (this.drawerContents[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.drawerContents[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);

        if (this.isInvNameLocalized())
        {
            par1NBTTagCompound.setString("CustomName", this.customName);
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }


    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        super.updateEntity();
    }



    /**
     * Called when a client event is received with the event number and argument, see World.sendClientEvent
     */
    public boolean receiveClientEvent(int par1, int par2)
    {
        if (par1 == 1)
        {
            this.numUsingPlayers = par2;
            return true;
        }
        else
        {
            return super.receiveClientEvent(par1, par2);
        }
    }

    public void openChest()
    {

    }

    public void closeChest()
    {

    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
    {
        return true;
    }

    /**
     * invalidates a tile entity
     */
    public void invalidate()
    {
        super.invalidate();
        this.updateContainingBlockInfo();
    }


}
