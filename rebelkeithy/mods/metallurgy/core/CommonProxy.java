package rebelkeithy.mods.metallurgy.core;

import java.io.File;
import net.minecraft.world.World;
import rebelkeithy.mods.metallurgy.core.metalsets.MetalSet;

public class CommonProxy
{
    public File getMinecraftDir()
    {
        return new File(".");
    }

    public void registerNamesForMetalSet(MetalSet var1) {}

    public void spawnParticle(String var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13) {}

    public World getClientWorld()
    {
        return null;
    }
}
