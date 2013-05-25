package rebelkeithy.mods.metallurgy.core.metalsets;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemMetallurgy extends Item
{
    private float xp = -1.0F;

    public ItemMetallurgy(int var1)
    {
        super(var1);
    }

    public ItemMetallurgy setSmeltinExperience(float var1)
    {
        this.xp = var1;
        return this;
    }

    public float getSmeltingExperience(ItemStack var1)
    {
        return this.xp;
    }
}
