package rebelkeithy.mods.metallurgy.vanilla;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import java.util.Map;
import net.minecraft.creativetab.CreativeTabs;
import rebelkeithy.mods.metallurgy.core.MetalInfoDatabase;
import rebelkeithy.mods.metallurgy.core.metalsets.MetalSet;

@Mod(
        modid = "Metallurgy3Vanilla",
        name = "Metallurgy 3 Vanilla",
        version = "1.4.7-1.11.13-1a"
)
@NetworkMod(
        channels = {"MetallurgyVanilla"},
        clientSideRequired = true,
        serverSideRequired = false
)
public class MetallurgyVanilla
{
    @SidedProxy(
            clientSide = "rebelkeithy.mods.metallurgy.vanilla.ClientProxy",
            serverSide = "rebelkeithy.mods.metallurgy.vanilla.CommonProxy"
    )
    public static CommonProxy proxy;
    public static MetalSet vanillaSet;

    @Mod.PreInit
    public void preInit(FMLPreInitializationEvent var1)
    {
        Map var2 = MetalInfoDatabase.getSpreadsheetDataForSet("Vanilla");
        var2.remove("Wood/Leather");
        var2.remove("Stone/Chainmail");
        vanillaSet = new MetalSet("Vanilla", var2, CreativeTabs.tabBlock);
        VanillaAddons.init();
    }

    @Mod.Init
    public void Init(FMLInitializationEvent var1)
    {
        VanillaAddons.load();
        proxy.registerNames();
    }
}
