package rebelkeithy.mods.metallurgy.machines;

import java.io.File;
import rebelkeithy.mods.guiregistry.GuiRegistry;
import rebelkeithy.mods.metallurgy.machines.mint.ContainerMintStorage;

public class CommonProxy
{
    public File getMinecraftDir()
    {
        return new File(".");
    }

    public void registerTileEntitySpecialRenderer() {}

    public void registerGUIs()
    {
        GuiRegistry.registerGuiServer(ContainerMintStorage.class, MetallurgyMachines.instance, "MintStorage");
    }
}
