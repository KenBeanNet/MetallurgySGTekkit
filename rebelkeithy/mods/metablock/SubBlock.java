package rebelkeithy.mods.metablock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SubBlock
{
    private MetaBlock metaBlock;
    private CreativeTabs tab;
    int meta;
    private int textureIndex;
    private ItemStack drop;
    private int dropMin;
    private int dropMax;
    private float hardness;
    private float blockResistance;
    public List dlList = new ArrayList();
    public List clList = new ArrayList();
    private boolean collisionEffect;
    public Icon icon;
    public String iconName;

    public SubBlock(int var1, int var2, String var3)
    {
        if (Block.blocksList[var1] == null)
        {
            this.metaBlock = new MetaBlock(var1);
            this.metaBlock.addSubBlock(this, var2);
        }
        else
        {
            this.metaBlock = (MetaBlock)Block.blocksList[var1];
            this.metaBlock.addSubBlock(this, var2);
        }

        this.iconName = var3;
    }

    public void addDisplayListener(IDisplayListener var1)
    {
        this.metaBlock.setTickRandomly(this.meta);
        this.dlList.add(var1);
    }

    public void addCollisionListener(ICollisionListener var1)
    {
        this.collisionEffect = true;
        this.clList.add(var1);
    }

    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        Iterator var6 = this.dlList.iterator();

        while (var6.hasNext())
        {
            IDisplayListener var7 = (IDisplayListener)var6.next();
            var7.randomDisplayTick(var1, var2, var3, var4, var5);
        }
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        if (this.collisionEffect)
        {
            float var5 = 0.025F;
            return AxisAlignedBB.getAABBPool().getAABB((double)var2, (double)var3, (double)var4, (double)(var2 + 1), (double)((float)(var3 + 1) - var5), (double)(var4 + 1));
        }
        else
        {
            return null;
        }
    }

    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
    {
        Iterator var6 = this.clList.iterator();

        while (var6.hasNext())
        {
            ICollisionListener var7 = (ICollisionListener)var6.next();
            var7.collide(var1, var2, var3, var4, var5, this.meta);
        }
    }

    public Icon getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.icon;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister var1)
    {
        this.icon = var1.registerIcon(this.iconName);
    }

    public Icon getBlockTextureFromSide(int var1)
    {
        return this.icon;
    }

    public SubBlock setCreativeTab(CreativeTabs var1)
    {
        this.metaBlock.setCreativeTab(var1);
        this.tab = var1;
        return this;
    }

    public CreativeTabs getCreativeTab()
    {
        return this.tab;
    }

    public SubBlock setUnlocalizedName(String var1)
    {
        this.metaBlock.setUnlocalizedName(var1);
        return this;
    }

    public Block getBlock()
    {
        return this.metaBlock;
    }

    public void setBlockDrops(ItemStack var1, int var2, int var3)
    {
        this.drop = var1.copy();
        this.dropMin = var2;
        this.dropMax = var3;
    }

    public int quantityDroppedWithBonus(int var1, Random var2)
    {
        return this.drop != null && this.dropMax > 1 ? this.dropMin + var2.nextInt(this.dropMax + var1) + var1 : 1;
    }

    public int idDropped(Random var1, int var2)
    {
        return this.drop != null ? this.drop.itemID : this.metaBlock.blockID;
    }

    public SubBlock setHardness(float var1)
    {
        this.hardness = var1;

        if (this.blockResistance < var1 * 5.0F)
        {
            this.blockResistance = var1 * 5.0F;
        }

        return this;
    }

    public float getBlockHardness()
    {
        return this.hardness;
    }

    public SubBlock setResistance(float var1)
    {
        this.blockResistance = var1 * 3.0F;
        return this;
    }

    public float getExplosionResistance(Entity var1)
    {
        return this.blockResistance / 5.0F;
    }

    public String toString()
    {
        return super.toString() + this.metaBlock.getUnlocalizedName();
    }

    public int damageDropped(int var1)
    {
        return this.idDropped(new Random(), var1) == this.metaBlock.blockID ? var1 : 0;
    }
}
