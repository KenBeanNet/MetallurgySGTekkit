package rebelkeithy.mods.metallurgy.api;

import net.minecraft.item.ItemStack;

public interface IOreInfo
{
    String getName();

    OreType getType();

    ItemStack getOre();

    ItemStack getBlock();

    ItemStack getBrick();

    ItemStack getDust();

    ItemStack getIngot();

    String getDrop();

    int getDropAmountMin();

    int getDropAmountMax();

    String[] getAlloyRecipe();
}
