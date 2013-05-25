package rebelkeithy.mods.metallurgy.metals;

import java.io.File;
import net.minecraft.client.Minecraft;
import rebelkeithy.mods.particleregistry.ParticleRegistry;

public class ClientProxy extends CommonProxy
{
    public File getMinecraftDir()
    {
        return Minecraft.getMinecraftDir();
    }

    public void registerParticles()
    {
    }
}
