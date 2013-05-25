package rebelkeithy.mods.metallurgy.metals;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class ItemOreFinder extends Item
{
    public ItemOreFinder(int var1)
    {
        super(var1);
        this.setMaxDamage(64);
        this.maxStackSize = 1;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        int var11 = 0;

        if (var1.hasTagCompound())
        {
            var11 = var1.getTagCompound().getInteger("mode");
        }
        else
        {
            var1.setTagCompound(new NBTTagCompound());
            var1.getTagCompound().setInteger("mode", var11);
        }

        System.out.println("Mode " + var11);

        if (var2.isSneaking())
        {
            ++var11;
            var11 %= 3;
            var1.getTagCompound().setInteger("mode", var11);
            var2.sendChatToPlayer("Radius: " + (var11 + 1) + " chunk" + (var11 != 0 ? "s" : ""));
            return false;
        }
        else if (var3.isRemote)
        {
            return false;
        }
        else
        {
            HashMap var12 = new HashMap();
            System.out.println("Checking");
            int var17;

            for (int var13 = 0; var13 < 128; ++var13)
            {
                for (int var14 = var4 - var4 % 16 - 16 * var11; var14 < var4 - var4 % 16 + 16 * (var11 + 1); ++var14)
                {
                    for (int var15 = var6 - var6 % 16 - 16 * var11; var15 < var6 - var6 % 16 + 16 * (var11 + 1); ++var15)
                    {
                        int var16 = var3.getBlockId(var14, var13, var15);
                        var17 = var3.getBlockMetadata(var14, var13, var15);
                        ItemStack var18 = new ItemStack(var16, 1, var17);
                        String var19 = null;
                        int var20 = OreDictionary.getOreID(var18);

                        if (var20 != -1)
                        {
                            var19 = OreDictionary.getOreName(var20);
                        }

                        if (var16 == Block.oreIron.blockID)
                        {
                            var19 = "oreIron";
                        }
                        else if (var16 == Block.oreGold.blockID)
                        {
                            var19 = "oreGold";
                        }
                        else if (var16 == Block.oreDiamond.blockID)
                        {
                            var19 = "oreDiamond";
                        }

                        if (var19 != null)
                        {
                            if (var12.containsKey(var19))
                            {
                                int var21 = ((Integer)var12.get(var19)).intValue();
                                var12.put(var19, Integer.valueOf(var21 + 1));
                            }
                            else
                            {
                                var12.put(var19, Integer.valueOf(1));
                            }
                        }
                    }
                }
            }

            Set var22 = var12.keySet();
            String[] var23 = (String[])var22.toArray(new String[var22.size()]);
            Arrays.sort(var23);
            var2.sendChatToPlayer("In Area (" + (var4 - var4 % 16 - 16 * var11) + ", " + (var6 - var6 % 16 - 16 * var11) + ") to (" + (var4 - var4 % 16 + 16 * (var11 + 1)) + ", " + (var6 - var6 % 16 + 16 * (var11 + 1)) + ")");
            Iterator var25 = var22.iterator();

            while (var25.hasNext())
            {
                String var24 = (String)var25.next();
                var17 = ((Integer)var12.get(var24)).intValue();
                var2.sendChatToPlayer("Found " + var17 + " " + var24);
            }

            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister var1)
    {
        this.itemIcon = var1.registerIcon("Metallurgy:machines/OreFinder");
    }
}
