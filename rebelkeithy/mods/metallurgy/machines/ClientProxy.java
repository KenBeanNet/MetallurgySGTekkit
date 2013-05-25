package rebelkeithy.mods.metallurgy.machines;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import java.io.File;
import net.minecraft.client.Minecraft;
import rebelkeithy.mods.guiregistry.GuiRegistry;
import rebelkeithy.mods.metallurgy.machines.mint.ContainerMintStorage;
import rebelkeithy.mods.metallurgy.machines.mint.GuiMintStorage;
import rebelkeithy.mods.metallurgy.machines.mint.MintRenderHelper;
import rebelkeithy.mods.metallurgy.machines.mint.TileEntityMint;
import rebelkeithy.mods.metallurgy.machines.mint.TileEntityMintRenderer;

public class ClientProxy extends CommonProxy
{
    public void registerTileEntitySpecialRenderer()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMint.class, new TileEntityMintRenderer());
        RenderingRegistry.registerBlockHandler(new MintRenderHelper());
    }

    public void registerGUIs()
    {
        GuiRegistry.registerGuiClient(GuiMintStorage.class, ContainerMintStorage.class, MetallurgyMachines.instance, "MintStorage");
    }

    public File getMinecraftDir()
    {
        return Minecraft.getMinecraftDir();
    }
}
