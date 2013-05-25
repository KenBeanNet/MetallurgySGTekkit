package rebelkeithy.mods.metablock;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MetaBlock extends Block
{
    SubBlock[] subBlocks;
    List tickList;
    ArrayList tabs;
    public static List registeredIDs;

    public static void registerID(int var0)
    {
        if (registeredIDs == null)
        {
            registeredIDs = new ArrayList();
        }

        if (!registeredIDs.contains(Integer.valueOf(var0)))
        {
            Block var1 = Block.blocksList[var0];

            if (var1 instanceof MetaBlock)
            {
                GameRegistry.registerBlock(var1, ItemMetaBlock.class);
                registeredIDs.add(Integer.valueOf(var0));
            }
        }
    }

    public MetaBlock(int var1)
    {
        super(var1, Material.rock);
        System.out.println("test");
        this.subBlocks = new SubBlock[16];
        this.tickList = new ArrayList();
    }

    public void addSubBlock(SubBlock var1, int var2)
    {
        if (this.subBlocks[var2] == null)
        {
            this.subBlocks[var2] = var1;
        }
        else
        {
            throw new IllegalArgumentException("[MetaBlock] In block " + this.blockID + " " + this + " metadata " + var2 + " is already occupied by " + this.subBlocks[var2] + " when adding " + var1);
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return this.subBlocks[var1] != null ? this.subBlocks[var1].damageDropped(var1) : var1;
    }

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        for (int var4 = 0; var4 < 16; ++var4)
        {
            if (this.subBlocks[var4] != null && var2 == this.subBlocks[var4].getCreativeTab())
            {
                var3.add(new ItemStack(this, 1, var4));
            }
        }
    }

    public void setTickRandomly(int var1)
    {
        if (!this.tickList.contains(Integer.valueOf(var1)))
        {
            this.setTickRandomly(true);
            this.tickList.add(Integer.valueOf(var1));
        }
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        AxisAlignedBB var6 = this.subBlocks[var5].getCollisionBoundingBoxFromPool(var1, var2, var3, var4);
        return var6 != null ? var6 : super.getCollisionBoundingBoxFromPool(var1, var2, var3, var4);
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        this.subBlocks[var6].onEntityCollidedWithBlock(var1, var2, var3, var4, var5);
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        this.subBlocks[var6].randomDisplayTick(var1, var2, var3, var4, var5);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        return this.subBlocks[var6].getBlockTexture(var1, var2, var3, var4, var5);
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        for (int var2 = 0; var2 < 16; ++var2)
        {
            if (this.subBlocks[var2] != null)
            {
                this.subBlocks[var2].registerIcons(var1);
            }
        }
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return this.subBlocks[var2] != null ? this.subBlocks[var2].getBlockTextureFromSide(var1) : null;
    }

    public ArrayList getBlockDropped(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        ArrayList var7 = new ArrayList();
        int var8 = this.quantityDropped(var5, var6, var1.rand);

        for (int var9 = 0; var9 < var8; ++var9)
        {
            int var10 = this.idDropped(var5, var1.rand, var6);

            if (var10 > 0)
            {
                var7.add(new ItemStack(var10, 1, this.damageDropped(var5)));
            }
        }

        return var7;
    }

    public int quantityDropped(int var1, int var2, Random var3)
    {
        return this.subBlocks[var1].quantityDroppedWithBonus(var2, var3);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return this.subBlocks[var1].idDropped(var2, var3);
    }

    /**
     * Returns the block hardness at a location. Args: world, x, y, z
     */
    public float getBlockHardness(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return this.subBlocks[var5] != null ? this.subBlocks[var5].getBlockHardness() : 0.0F;
    }

    public float getExplosionResistance(Entity var1, World var2, int var3, int var4, int var5, double var6, double var8, double var10)
    {
        int var12 = var2.getBlockMetadata(var3, var4, var5);
        return this.subBlocks[var12] != null ? this.subBlocks[var12].getExplosionResistance(var1) : 0.0F;
    }

    /**
     * Sets the CreativeTab to display this block on.
     */
    public Block setCreativeTab(CreativeTabs var1)
    {
        if (this.tabs == null)
        {
            this.tabs = new ArrayList();
        }

        if (!this.tabs.contains(var1))
        {
            this.tabs.add(var1);
        }

        return this;
    }

    public CreativeTabs[] getCreativeTabArray()
    {
        return this.tabs == null ? new CreativeTabs[0] : (CreativeTabs[])this.tabs.toArray(new CreativeTabs[this.tabs.size()]);
    }
}
