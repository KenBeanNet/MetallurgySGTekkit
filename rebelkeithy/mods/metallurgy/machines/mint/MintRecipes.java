package rebelkeithy.mods.metallurgy.machines.mint;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.ItemStack;

public class MintRecipes
{
    private static final MintRecipes smeltingBase = new MintRecipes();
    private static Map metaMintingList = new HashMap();
    private static Map mintingImage = new HashMap();

    public static final MintRecipes minting()
    {
        return smeltingBase;
    }

    public void addMinting(int var1, int var2, int var3)
    {
        metaMintingList.put(Integer.valueOf(var1), Integer.valueOf(var3));
    }

    public static void addMinting(int var0, int var1, String var2)
    {
        metaMintingList.put(Integer.valueOf(var0), Integer.valueOf(var1));
        mintingImage.put(Integer.valueOf(var0), var2);
    }

    public int getMintingResult(ItemStack var1)
    {
        if (var1 == null)
        {
            return 0;
        }
        else
        {
            Integer var2 = (Integer)metaMintingList.get(Integer.valueOf(var1.itemID));
            return var2 == null ? 0 : var2.intValue();
        }
    }

    public String getImage(int var1)
    {
        return mintingImage.containsKey(Integer.valueOf(var1)) ? (String)mintingImage.get(Integer.valueOf(var1)) : "";
    }
}
