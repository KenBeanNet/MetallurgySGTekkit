package rebelkeithy.mods.metallurgy.metals;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import java.io.File;
import java.io.IOException;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import rebelkeithy.mods.metallurgy.core.MetalInfoDatabase;
import rebelkeithy.mods.metallurgy.core.MetallurgyCore;
import rebelkeithy.mods.metallurgy.core.MetallurgyTabs;
import rebelkeithy.mods.metallurgy.core.metalsets.MetalSet;

@Mod(
        modid = "Metallurgy3Base",
        name = "Metallurgy 3 Base",
        version = "1.4.7-1.11.13-1a"
)
@NetworkMod(
        channels = {"MetallurgyBase"},
        clientSideRequired = true,
        serverSideRequired = false
)
public class MetallurgyMetals
{
    public boolean isRelease = false;
    public static MetalSet preciousSet;
    public static MetallurgyTabs preciousTab;
    public static MetallurgyTabs utilityTab;
    public static Configuration baseConfig;
    public static Configuration fantasyConfig;
    public static Configuration utilityConfig;
    public static Item dustIron;
    public static Item dustGold;
    
    @SidedProxy(
            clientSide = "rebelkeithy.mods.metallurgy.metals.ClientProxy",
            serverSide = "rebelkeithy.mods.metallurgy.metals.CommonProxy"
    )
    public static CommonProxy proxy;
    @Mod.Instance("Metallurgy3Base")
    public static MetallurgyMetals instance;

    @Mod.PreInit
    public void preInit(FMLPreInitializationEvent var1)
    {
        baseConfig = this.initConfig("Base");
        baseConfig.load();
        utilityConfig = this.initConfig("Utility");
        utilityConfig.load();
        fantasyConfig = this.initConfig("Fantasy");
        preciousTab = new MetallurgyTabs("Metallurgy: Precious");
        LanguageRegistry.instance().addStringLocalization("itemGroup.Metallurgy: Precious", "Metallurgy: Precious");
        String var2 = var1.getSourceFile().getAbsolutePath();

        if (!this.isRelease)
        {
            var2 = "C:/Metallurgy.jar";
        }

        MetalInfoDatabase.readMetalDataFromJar("spreadsheet.csv", var2);
        MetalInfoDatabase.readItemDataFromJar(utilityConfig, "Items.csv", var2, utilityTab);
        preciousSet = new MetalSet("Precious", MetalInfoDatabase.getSpreadsheetDataForSet("Precious"), preciousTab);
    }

    @Mod.Init
    public void Init(FMLInitializationEvent var1)
    {
        dustIron = (new Item(5100)).setUnlocalizedName("Metallurgy:Vanilla/IronDust").setCreativeTab(CreativeTabs.tabMaterials);
        dustGold = (new Item(5101)).setUnlocalizedName("Metallurgy:Vanilla/GoldDust").setCreativeTab(CreativeTabs.tabMaterials);
        FurnaceRecipes.smelting().addSmelting(dustIron.itemID, 0, new ItemStack(Item.ingotIron), 0.7F);
        FurnaceRecipes.smelting().addSmelting(dustGold.itemID, 0, new ItemStack(Item.ingotGold), 0.7F);
        LanguageRegistry.addName(dustIron, "Iron Dust");
        LanguageRegistry.addName(dustGold, "Gold Dust");
        OreDictionary.registerOre("dustIron", dustIron);
        OreDictionary.registerOre("dustGold", dustGold);
        Item var2 = (new ItemOreFinder(5102)).setUnlocalizedName("stick").setCreativeTab(CreativeTabs.tabTools);
        proxy.registerParticles();
    }

    @Mod.PostInit
    public void postInit(FMLPostInitializationEvent var1)
    {
        this.createMidasiumRecipes();
    }

    public Configuration initConfig(String var1)
    {
        File var2 = new File(MetallurgyCore.proxy.getMinecraftDir() + "/config/Metallurgy3");
        var2.mkdir();
        File var3 = new File(MetallurgyCore.proxy.getMinecraftDir() + "/config/Metallurgy3/Metallurgy" + var1 + ".cfg");

        try
        {
            var3.createNewFile();
        }
        catch (IOException var5)
        {
            System.out.println(var5);
        }

        return new Configuration(var3);
    }

    public void createMidasiumRecipes()
    {
        String[] var1 = OreDictionary.getOreNames();
        System.out.println("Searching for dust for midsasium recipes");
        int var2 = 0;
        String[] var3 = var1;
        int var4 = var1.length;

        for (int var5 = 0; var5 < var4; ++var5)
        {
            String var6 = var3[var5];

            if (var6.contains("dust"))
            {
                System.out.println("Adding recipe for " + var6 + " midasium = gold");
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(dustGold), new Object[] {"dustMidasium", var6}));
                ++var2;
            }
        }
    }
}
