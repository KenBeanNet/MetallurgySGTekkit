package rebelkeithy.mods.metallurgy.core.metalsets;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;

public interface ISwordHitListener
{
    boolean hitEntity(ItemStack var1, EntityLiving var2, EntityLiving var3);
}
