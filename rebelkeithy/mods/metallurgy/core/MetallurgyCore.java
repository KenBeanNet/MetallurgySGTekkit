package rebelkeithy.mods.metallurgy.core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import rebelkeithy.mods.guiregistry.GuiRegistry;
import rebelkeithy.mods.metallurgy.core.metalsets.MetalSet;

@Mod(
        modid = "Metallurgy3Core",
        name = "Metallurgy 3 Core",
        version = "1.4.7-1.11.13-1a"
)
@NetworkMod(
        channels = {"MetallurgyCore"},
        clientSideRequired = true,
        serverSideRequired = false
)
public class MetallurgyCore
{
    @SidedProxy(
            clientSide = "rebelkeithy.mods.metallurgy.core.ClientProxy",
            serverSide = "rebelkeithy.mods.metallurgy.core.CommonProxy"
    )
    public static CommonProxy proxy;
    @Mod.Instance("Metallurgy3Core")
    public static MetallurgyCore instance;
    public static boolean spawnInAir = false;
    Configuration config;
    List csvFiles;
    List setsToRead;
    private static List metalSets;
    MetalSet baseSet;

    @Mod.PreInit
    public void preInit(FMLPreInitializationEvent var1)
    {
        Iterator var2;
        MetalSet var3;

        for (var2 = getMetalSetList().iterator(); var2.hasNext(); var3 = (MetalSet)var2.next())
        {
            ;
        }

        this.initConfig();
        var2 = this.csvFiles.iterator();
        String var5;

        while (var2.hasNext())
        {
            var5 = (String)var2.next();

            if (!var5.equals(""))
            {
                MetalInfoDatabase.readMetalDataFromFile("/config/Metallurgy3/" + var5);
            }
        }

        var2 = this.setsToRead.iterator();

        while (var2.hasNext())
        {
            var5 = (String)var2.next();

            if (!var5.equals(""))
            {
                CreativeTabs var4 = new CreativeTabs(var5);
                new MetalSet(var5, MetalInfoDatabase.getSpreadsheetDataForSet(var5), var4);
            }
        }

        NetworkRegistry.instance().registerGuiHandler(this, GuiRegistry.instance());
    }

    @Mod.Init
    public void init(FMLInitializationEvent var1)
    {
        Iterator var2 = getMetalSetList().iterator();

        while (var2.hasNext())
        {
            MetalSet var3 = (MetalSet)var2.next();
            var3.load();
            proxy.registerNamesForMetalSet(var3);
        }

        MetalInfoDatabase.registerItemsWithOreDict();
    }

    @Mod.PostInit
    public void postInit(FMLPostInitializationEvent var1) {}

    public void initConfig()
    {
        File var1 = new File(proxy.getMinecraftDir() + "/config/Metallurgy3");
        var1.mkdir();
        File var2 = new File(proxy.getMinecraftDir() + "/config/Metallurgy3/MetallurgyCore.cfg");

        try
        {
            var2.createNewFile();
            System.out.println("[Metallurgy3] Successfully created/read configuration file for Metallurgy 3 Core");
        }
        catch (IOException var4)
        {
            System.out.println("[Metallurgy3] Could not create configuration file for Metallurgy 3 Core, Reason:");
            System.out.println(var4);
        }

        this.config = new Configuration(var2);
        this.config.load();
        spawnInAir = this.config.get("Cheats", "Spawn Ore In Air", false).getBoolean(false);
        this.csvFiles = Arrays.asList(this.config.get("Metal Sets", "File List", "").getString().split("\\s*,\\s*"));
        this.setsToRead = Arrays.asList(this.config.get("Metal Sets", "Metal Set List", "").getString().split("\\s*,\\s*"));
        System.out.println("reading sets " + this.setsToRead.size());
        this.config.save();
    }

    public static List getMetalSetList()
    {
        if (metalSets == null)
        {
            metalSets = new ArrayList();
        }

        return metalSets;
    }
}
