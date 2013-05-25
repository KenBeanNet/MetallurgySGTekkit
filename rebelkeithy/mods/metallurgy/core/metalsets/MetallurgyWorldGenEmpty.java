package rebelkeithy.mods.metallurgy.core.metalsets;

import net.minecraft.world.World;

public class MetallurgyWorldGenEmpty
{
    public static boolean removeBlocks(World var0, int var1, int var2, int var3)
    {
        for (int var4 = 0; var4 < 16; ++var4)
        {
            for (int var5 = 0; var5 < var0.getActualHeight(); ++var5)
            {
                for (int var6 = 0; var6 < 16; ++var6)
                {
                    if (var0.getBlockId(var1 + var4, var5, var2 + var6) == var3)
                    {
                        var0.setBlockToAir(var1 + var4, var5, var2 + var6);
                    }
                }
            }
        }

        return true;
    }
}
