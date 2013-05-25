package rebelkeithy.mods.metallurgy.core.metalsets;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class ItemMetallurgyArmor extends ItemArmor implements IArmorTextureProvider
{
    public String textureFile;

    public ItemMetallurgyArmor(int var1, EnumArmorMaterial var2, int var3, int var4)
    {
        super(var1, var2, var3, var4);
    }

    public Item setTextureFile(String var1)
    {
        this.textureFile = var1;
        return this;
    }

    public String getArmorTextureFile(ItemStack var1)
    {
        return "/armor/" + this.textureFile + ".png";
    }
}
