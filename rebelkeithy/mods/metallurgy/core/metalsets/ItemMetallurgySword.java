package rebelkeithy.mods.metallurgy.core.metalsets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemMetallurgySword extends ItemSword
{
    private List hlList = new ArrayList();
    String subText;

    public ItemMetallurgySword(int var1, EnumToolMaterial var2)
    {
        super(var1, var2);
    }

    public void addHitListener(ISwordHitListener var1)
    {
        this.hlList.add(var1);
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack var1, EntityLiving var2, EntityLiving var3)
    {
        Iterator var4 = this.hlList.iterator();

        while (var4.hasNext())
        {
            ISwordHitListener var5 = (ISwordHitListener)var4.next();
            var5.hitEntity(var1, var2, var3);
        }

        return super.hitEntity(var1, var2, var3);
    }

    public void setSubText(String var1)
    {
        this.subText = var1;
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4)
    {
        if (this.subText != null)
        {
            String[] var5 = this.subText.split("-");
            int var6 = var5.length;

            for (int var7 = 0; var7 < var6; ++var7)
            {
                String var8 = var5[var7];
                var3.add("\u00a7" + var8);
            }
        }
    }
}
