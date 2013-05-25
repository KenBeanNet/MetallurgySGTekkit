package rebelkeithy.mods.metallurgy.machines.mint;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import rebelkeithy.mods.metallurgy.machines.MetallurgyMachines;

public class TileEntityMintStorage extends TileEntity implements IInventory
{
    private ItemStack[] chestContents = new ItemStack[6];
    public int numUsingPlayers;
    private int ticksSinceSync;
    private int direction;

    public void setDirection(int var1)
    {
        this.direction = var1;
    }

    public int getDirection()
    {
        return this.direction;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 6;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int var1)
    {
        return this.chestContents[var1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int var1, int var2)
    {
        if (this.chestContents[var1] != null)
        {
            ItemStack var3;

            if (this.chestContents[var1].stackSize <= var2)
            {
                var3 = this.chestContents[var1];
                this.chestContents[var1] = null;
                this.onInventoryChanged();
                return var3;
            }
            else
            {
                var3 = this.chestContents[var1].splitStack(var2);

                if (this.chestContents[var1].stackSize == 0)
                {
                    this.chestContents[var1] = null;
                }

                this.onInventoryChanged();
                return var3;
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
    public ItemStack getStackInSlotOnClosing(int var1)
    {
        if (this.chestContents[var1] != null)
        {
            ItemStack var2 = this.chestContents[var1];
            this.chestContents[var1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int var1, ItemStack var2)
    {
        this.chestContents[var1] = var2;

        if (var2 != null && var2.stackSize > this.getInventoryStackLimit())
        {
            var2.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "container.chest";
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        this.direction = var1.getInteger("Direction");
        NBTTagList var2 = var1.getTagList("Items");
        this.chestContents = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            int var5 = var4.getByte("Slot") & 255;

            if (var5 >= 0 && var5 < this.chestContents.length)
            {
                this.chestContents[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        NBTTagList var2 = new NBTTagList();
        var1.setInteger("Direction", this.direction);

        for (int var3 = 0; var3 < this.chestContents.length; ++var3)
        {
            if (this.chestContents[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.chestContents[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        var1.setTag("Items", var2);
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
    public boolean isUseableByPlayer(EntityPlayer var1)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        super.updateEntity();

        if (++this.ticksSinceSync % 20 * 4 == 0)
        {
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, MetallurgyMachines.chest.blockID, 2, this.direction);
        }
    }

    /**
     * Called when a client event is received with the event number and argument, see World.sendClientEvent
     */
    public boolean receiveClientEvent(int var1, int var2)
    {
        if (var1 == 1)
        {
            this.numUsingPlayers = var2;
        }
        else
        {
            if (var1 != 2)
            {
                return true;
            }

            this.direction = var2;
        }

        return true;
    }

    public void openChest()
    {
        ++this.numUsingPlayers;
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, MetallurgyMachines.chest.blockID, 1, this.numUsingPlayers);
    }

    public void closeChest()
    {
        --this.numUsingPlayers;
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, MetallurgyMachines.chest.blockID, 1, this.numUsingPlayers);
    }

    /**
     * invalidates a tile entity
     */
    public void invalidate()
    {
        this.updateContainingBlockInfo();
        super.invalidate();
    }

    public int addItem(ItemStack var1, boolean var2, ForgeDirection var3)
    {
        if (MintRecipes.minting().getMintingResult(var1) == 0)
        {
            return 0;
        }
        else
        {
            int var4 = var1.stackSize;

            for (int var5 = 0; var5 < 6; ++var5)
            {
                if (this.chestContents[var5] == null)
                {
                    if (var2)
                    {
                        this.chestContents[var5] = new ItemStack(var1.itemID, var1.stackSize, var1.getItemDamage());
                    }

                    return var1.stackSize;
                }

                if (this.chestContents[var5].itemID == var1.itemID && this.chestContents[var5].stackSize < this.chestContents[var5].getMaxStackSize())
                {
                    int var6 = this.chestContents[var5].getMaxStackSize() - this.chestContents[var5].stackSize;

                    if (var6 >= var4)
                    {
                        if (var2)
                        {
                            this.chestContents[var5].stackSize += var4;
                        }

                        return var1.stackSize;
                    }

                    var4 -= var6;

                    if (var2)
                    {
                        this.chestContents[var5].stackSize = this.chestContents[var5].getMaxStackSize();
                    }
                }
            }

            return var1.stackSize - var4;
        }
    }

    public ItemStack[] extractItem(boolean var1, ForgeDirection var2, int var3)
    {
        for (int var4 = 0; var4 < 6; ++var4)
        {
            if (this.chestContents[var4] != null)
            {
                if (this.chestContents[var4].stackSize > var3)
                {
                    if (var1)
                    {
                        this.chestContents[var4].stackSize -= var3;
                    }

                    return new ItemStack[] {new ItemStack(this.chestContents[var4].itemID, var3, this.chestContents[var4].getItemDamage())};
                }

                ItemStack var5 = new ItemStack(this.chestContents[var4].itemID, this.chestContents[var4].stackSize, this.chestContents[var4].getItemDamage());

                if (var1)
                {
                    this.chestContents[var4] = null;
                }

                return new ItemStack[] {var5};
            }
        }

        return null;
    }

    /**
     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
     * language. Otherwise it will be used directly.
     */
    public boolean isInvNameLocalized()
    {
        return false;
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isStackValidForSlot(int var1, ItemStack var2)
    {
        return false;
    }
}
