package rebelkeithy.mods.metallurgy.core;

import cpw.mods.fml.client.FMLClientHandler;
import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import rebelkeithy.mods.metallurgy.core.metalsets.MetalSet;
import rebelkeithy.mods.particleregistry.ParticleRegistry;

public class ClientProxy extends CommonProxy
{
    public void registerNamesForMetalSet(MetalSet var1)
    {
        var1.registerNames();
    }

    public File getMinecraftDir()
    {
        return Minecraft.getMinecraftDir();
    }

    public World getClientWorld()
    {
        return FMLClientHandler.instance().getClient().theWorld;
    }

    public void spawnParticle(String var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13)
    {
        ParticleRegistry.spawnParticle(var1, var2, var3, var5, var7, var9, var11, var13);
    }
}
