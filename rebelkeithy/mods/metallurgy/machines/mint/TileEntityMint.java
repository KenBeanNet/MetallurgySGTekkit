package rebelkeithy.mods.metallurgy.machines.mint;

import cpw.mods.fml.common.network.PacketDispatcher;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import rebelkeithy.mods.metallurgy.machines.MetallurgyMachines;
import rebelkeithy.mods.metallurgy.metals.MetallurgyMetals;

public class TileEntityMint extends TileEntity
{
    public int direction = 0;
    private int ingotId = 0;
    public int amount = 0;
    public int resetTime = 0;
    public boolean powered = false;
    private int ticksSinceSync;
    private int timeSinceSinc;

    public void setDirection(int var1)
    {
        this.direction = var1;
    }

    public int getDirection()
    {
        return this.direction;
    }

    public void power()
    {
        if (this.ingotId == 0)
        {
            int var1;
            TileEntity var2;
            IInventory var3;
            int var4;
            ItemStack var5;

            for (var1 = -1; var1 <= 1; var1 += 2)
            {
                var2 = this.worldObj.getBlockTileEntity(this.xCoord + var1, this.yCoord, this.zCoord);

                if (var2 instanceof TileEntityMintStorage)
                {
                    var3 = (IInventory)var2;

                    for (var4 = 0; var4 < var3.getSizeInventory(); ++var4)
                    {
                        var5 = var3.getStackInSlot(var4);

                        if (var5 != null && MintRecipes.minting().getMintingResult(var5) > 0)
                        {
                            this.setIngot(var4, var3);
                            return;
                        }
                    }
                }
            }

            for (var1 = -1; var1 <= 1; var1 += 2)
            {
                var2 = this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord + var1);

                if (var2 instanceof TileEntityMintStorage)
                {
                    var3 = (IInventory)var2;

                    for (var4 = 0; var4 < var3.getSizeInventory(); ++var4)
                    {
                        var5 = var3.getStackInSlot(var4);

                        if (var5 != null && MintRecipes.minting().getMintingResult(var5) > 0)
                        {
                            this.setIngot(var4, var3);
                            return;
                        }
                    }
                }
            }
        }

        if (this.resetTime == 0)
        {
            this.resetTime = 10;
        }

        this.powered = true;
    }

    public void unpower()
    {
        this.powered = false;
    }

    public void setIngot(int var1, IInventory var2)
    {
        if (this.ingotId == 0 && this.resetTime == 0)
        {
            this.amount = MintRecipes.minting().getMintingResult(var2.getStackInSlot(var1));

            if (this.amount != 0)
            {
                this.ingotId = var2.getStackInSlot(var1).itemID;
                var2.decrStackSize(var1, 1);
                int var3 = this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord);
                this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, var3, 3, this.ingotId);
            }
        }
    }

    public boolean hasIngot()
    {
        return this.ingotId > 0;
    }

    public ItemStack currentIngot()
    {
        ItemStack var1 = new ItemStack(this.ingotId, 1, 0);
        return this.ingotId != 0 && this.amount == MintRecipes.minting().getMintingResult(var1) ? var1 : null;
    }

    public void removeIngot()
    {
        this.ingotId = 0;
        this.amount = 0;
        int var1 = this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord);
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, var1, 3, this.ingotId);
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        this.direction = var1.getShort("Direction");
        this.ingotId = var1.getShort("Ingot");
        this.amount = var1.getShort("Amount");
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        var1.setShort("Direction", (short)this.direction);
        var1.setShort("Ingot", (short)this.ingotId);
        var1.setShort("Amount", (short)this.amount);
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        int var1;

        if (this.timeSinceSinc-- == 0 && !this.worldObj.isRemote)
        {
            this.timeSinceSinc = 80;
            var1 = this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord);
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, var1, 2, this.resetTime);
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, var1, 3, this.ingotId);
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, var1, 4, this.amount);
        }

        this.timeSinceSinc = this.timeSinceSinc < 0 ? 0 : this.timeSinceSinc;

        if (this.resetTime > 0)
        {
            --this.resetTime;
            var1 = this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord);
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, var1, 2, this.resetTime);

            if (this.resetTime == 6)
            {
                this.mint();
            }
        }
    }

    /**
     * Called when a client event is received with the event number and argument, see World.sendClientEvent
     */
    public boolean receiveClientEvent(int var1, int var2)
    {
        if (var1 == 1)
        {
            this.direction = var2;
        }
        else if (var1 == 2)
        {
            this.resetTime = var2;
        }
        else if (var1 == 3)
        {
            this.ingotId = var2;
        }
        else
        {
            if (var1 != 4)
            {
                return false;
            }

            this.amount = var2;
        }

        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        return true;
    }

    public boolean isUseableByPlayer(EntityPlayer var1)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void sendPacket()
    {
        if (!this.worldObj.isRemote)
        {
            ByteArrayOutputStream var1 = new ByteArrayOutputStream(140);
            DataOutputStream var2 = new DataOutputStream(var1);

            try
            {
                var2.writeInt(2);
                var2.writeInt(this.xCoord);
                var2.writeInt(this.yCoord);
                var2.writeInt(this.zCoord);
                var2.writeInt(this.direction);
            }
            catch (IOException var4)
            {
                ;
            }

            Packet250CustomPayload var3 = new Packet250CustomPayload();
            var3.channel = "MetallurgyFantas";
            var3.data = var1.toByteArray();
            var3.length = var1.size();
            var3.isChunkDataPacket = true;

            if (var3 != null)
            {
                PacketDispatcher.sendPacketToAllAround((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, 16.0D, this.worldObj.provider.dimensionId, var3);
            }
        }
    }

    public void mint()
    {
        ItemStack var1 = new ItemStack(MetallurgyMachines.coin, 1, 0);
        Random var2 = new Random();

        if (!this.worldObj.isRemote && this.hasIngot())
        {
            int var3;
            TileEntity var4;
            IInventory var5;
            int var6;
            ItemStack var7;

            for (var3 = -1; var3 <= 1; var3 += 2)
            {
                var4 = this.worldObj.getBlockTileEntity(this.xCoord + var3, this.yCoord, this.zCoord);

                if (var4 instanceof IInventory && !(var4 instanceof TileEntityMintStorage))
                {
                    var5 = (IInventory)var4;

                    for (var6 = 0; var6 < var5.getSizeInventory(); ++var6)
                    {
                        var7 = var5.getStackInSlot(var6);

                        if (var7 == null)
                        {
                            var5.setInventorySlotContents(var6, var1);
                            this.increaseIngotMintCount();
                            return;
                        }

                        if (var7.itemID == var1.itemID && var7.stackSize < 64)
                        {
                            ++var7.stackSize;
                            this.increaseIngotMintCount();
                            return;
                        }
                    }
                }
            }

            for (var3 = -1; var3 <= 1; var3 += 2)
            {
                var4 = this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord + var3);

                if (var4 instanceof IInventory && !(var4 instanceof TileEntityMintStorage))
                {
                    var5 = (IInventory)var4;

                    for (var6 = 0; var6 < var5.getSizeInventory(); ++var6)
                    {
                        var7 = var5.getStackInSlot(var6);

                        if (var7 == null)
                        {
                            var5.setInventorySlotContents(var6, var1);
                            this.increaseIngotMintCount();
                            return;
                        }

                        if (var7.itemID == var1.itemID && var7.stackSize < 64)
                        {
                            ++var7.stackSize;
                            this.increaseIngotMintCount();
                            return;
                        }
                    }
                }
            }

            float var8 = var2.nextFloat() * 0.8F + 0.1F;
            float var9 = var2.nextFloat() * 0.8F + 0.1F;
            float var10 = var2.nextFloat() * 0.8F + 0.1F;
            EntityItem var11 = new EntityItem(this.worldObj, (double)((float)this.xCoord + var8), (double)((float)this.yCoord + var9), (double)((float)this.zCoord + var10), new ItemStack(var1.itemID, 1, var1.getItemDamage()));

            if (var1.hasTagCompound())
            {
                var11.getEntityItem().setTagCompound((NBTTagCompound)var1.getTagCompound().copy());
            }

            float var12 = 0.05F;
            var11.motionX = (double)((float)var2.nextGaussian() * var12);
            var11.motionY = (double)((float)var2.nextGaussian() * var12 + 0.2F);
            var11.motionZ = (double)((float)var2.nextGaussian() * var12);
            var11.delayBeforeCanPickup = 20;
            this.worldObj.spawnEntityInWorld(var11);
            this.increaseIngotMintCount();
        }
    }

    public void increaseIngotMintCount()
    {
        if (this.amount > MintRecipes.minting().getMintingResult(new ItemStack(this.ingotId, 1, 0)))
        {
            this.amount = MintRecipes.minting().getMintingResult(new ItemStack(this.ingotId, 1, 0));
        }

        if (--this.amount <= 0)
        {
            this.ingotId = 0;
            int var1 = this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord);
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, var1, 3, this.ingotId);
        }
    }

    public String getIngotImage()
    {
        return this.ingotId == MetallurgyMetals.preciousSet.getOreInfo("Silver").ingot.itemID ? "/mods/Metallurgy/textures/blocks/machines/mint/MintSilver.png" : (this.ingotId == MetallurgyMetals.preciousSet.getOreInfo("Brass").ingot.itemID ? "/mods/Metallurgy/textures/blocks/machines/mint/MintBrass.png" : (this.ingotId == MetallurgyMetals.preciousSet.getOreInfo("Electrum").ingot.itemID ? "/mods/Metallurgy/textures/blocks/machines/mint/MintElectrum.png" : (this.ingotId == MetallurgyMetals.preciousSet.getOreInfo("Platinum").ingot.itemID ? "/mods/Metallurgy/textures/blocks/machines/mint/MintPlatinum.png" : (this.ingotId == Item.ingotGold.itemID ? "/mods/Metallurgy/textures/blocks/machines/mint/MintGold.png" : (!MintRecipes.minting().getImage(this.ingotId).equals("") ? MintRecipes.minting().getImage(this.ingotId) : "/mods/Metallurgy/textures/blocks/machines/mint/MintBrass.png")))));
    }
}
