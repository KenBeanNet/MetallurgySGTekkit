package rebelkeithy.mods.metallurgy.machines.mint;

import cpw.mods.fml.client.registry.RenderingRegistry;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockMint extends BlockContainer
{
    private Random furnaceRand = new Random();
    private int renderId = RenderingRegistry.getNextAvailableRenderId();
    private static boolean keepFurnaceInventory = false;

    public BlockMint(int var1)
    {
        super(var1, Material.rock);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return this.blockID;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return this.renderId;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (var1.isRemote)
        {
            return true;
        }
        else
        {
            TileEntityMint var10 = (TileEntityMint)var1.getBlockTileEntity(var2, var3, var4);

            if (var5.inventory.getCurrentItem() != null)
            {
                var10.setIngot(var5.inventory.currentItem, var5.inventory);
            }
            else
            {
                ItemStack var11 = var10.currentIngot();

                if (var11 != null)
                {
                    var5.inventory.mainInventory[var5.inventory.currentItem] = var11;
                    var10.removeIngot();
                }
            }

            return true;
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new TileEntityMint();
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack var6)
    {
        int var7 = MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (var7 == 0)
        {
            ((TileEntityMint)((TileEntityMint)var1.getBlockTileEntity(var2, var3, var4))).setDirection(2);
        }

        if (var7 == 1)
        {
            ((TileEntityMint)((TileEntityMint)var1.getBlockTileEntity(var2, var3, var4))).setDirection(5);
        }

        if (var7 == 2)
        {
            ((TileEntityMint)((TileEntityMint)var1.getBlockTileEntity(var2, var3, var4))).setDirection(3);
        }

        if (var7 == 3)
        {
            ((TileEntityMint)((TileEntityMint)var1.getBlockTileEntity(var2, var3, var4))).setDirection(4);
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!var1.isRemote)
        {
            TileEntityMint var6 = (TileEntityMint)var1.getBlockTileEntity(var2, var3, var4);

            if (var1.isBlockIndirectlyGettingPowered(var2, var3, var4))
            {
                var6.power();
            }
            else if (!var1.isBlockIndirectlyGettingPowered(var2, var3, var4))
            {
                var6.unpower();
            }
        }
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        if (!keepFurnaceInventory)
        {
            TileEntityMint var7 = (TileEntityMint)var1.getBlockTileEntity(var2, var3, var4);

            if (var7 != null)
            {
                ItemStack var8 = var7.currentIngot();

                if (var8 != null)
                {
                    float var9 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                    float var10 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                    float var11 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                    EntityItem var12 = new EntityItem(var1, (double)((float)var2 + var9), (double)((float)var3 + var10), (double)((float)var4 + var11), new ItemStack(var8.itemID, 1, var8.getItemDamage()));

                    if (var8.hasTagCompound())
                    {
                        var12.getEntityItem().setTagCompound((NBTTagCompound)var8.getTagCompound().copy());
                    }

                    float var13 = 0.05F;
                    var12.motionX = (double)((float)this.furnaceRand.nextGaussian() * var13);
                    var12.motionY = (double)((float)this.furnaceRand.nextGaussian() * var13 + 0.2F);
                    var12.motionZ = (double)((float)this.furnaceRand.nextGaussian() * var13);
                    var1.spawnEntityInWorld(var12);
                }
            }
        }

        super.breakBlock(var1, var2, var3, var4, var5, var6);
    }
}
