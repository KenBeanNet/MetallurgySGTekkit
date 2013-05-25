package rebelkeithy.mods.metallurgy.machines;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import rebelkeithy.mods.guiregistry.GuiRegistry;
import rebelkeithy.mods.metallurgy.core.MetallurgyTabs;
import rebelkeithy.mods.metallurgy.machines.mint.BlockMint;
import rebelkeithy.mods.metallurgy.machines.mint.BlockMintStorage;
import rebelkeithy.mods.metallurgy.machines.mint.MetallurgyTradeHandler;
import rebelkeithy.mods.metallurgy.machines.mint.MintRecipes;
import rebelkeithy.mods.metallurgy.machines.mint.TileEntityMint;
import rebelkeithy.mods.metallurgy.machines.mint.TileEntityMintStorage;
import rebelkeithy.mods.metallurgy.metals.MetallurgyMetals;

@Mod(
        modid = "Metallurgy3Machines",
        name = "Metallurgy 3 Machines",
        dependencies = "required-after:Metallurgy3Base",
        version = "1.4.7_1.16.13_1a"
)
@NetworkMod(
        channels = {"M3Machines"},
        clientSideRequired = true,
        serverSideRequired = false,
        packetHandler = PacketHandler.class
)
public class MetallurgyMachines
{
    @SidedProxy(
            clientSide = "rebelkeithy.mods.metallurgy.machines.ClientProxy",
            serverSide = "rebelkeithy.mods.metallurgy.machines.CommonProxy"
    )
    public static CommonProxy proxy;
    @Mod.Instance("Metallurgy3Machines")
    public static MetallurgyMachines instance;
    public static MetallurgyTabs machineTab;
    public static Item coin;
    public static Item stack;
    public static Item bag;
    public static Item bullion;
    public static Item glassDust;
    public static Item goldCog;
    public static Block chest;
    public static Block mint;
    public static Block mintStorage;

    @Mod.PreInit
    public void preInit(FMLPreInitializationEvent var1)
    {
        machineTab = new MetallurgyTabs("Metallurgy: Machines");
        ConfigMachines.initConfig();
        this.initChests();
        this.initMint();
        proxy.registerGUIs();
        proxy.registerTileEntitySpecialRenderer();
        NetworkRegistry.instance().registerGuiHandler(this, new StorageGuiHandler());
        NetworkRegistry.instance().registerGuiHandler(this, GuiRegistry.instance());
        LanguageRegistry.instance().addStringLocalization("itemGroup.Metallurgy: Machines", "Metallurgy: Machines");
    }

    @Mod.Init
    public void Init(FMLInitializationEvent var1)
    {
        this.loadMints();
    }

    public void initMint()
    {
        mint = (new BlockMint(ConfigMachines.mintID)).setHardness(2.0F).setUnlocalizedName("M3Mint").setCreativeTab(machineTab);
        mintStorage = (new BlockMintStorage(ConfigMachines.mintStorageID)).setHardness(2.0F).setUnlocalizedName("M3MintStorage").setCreativeTab(machineTab);
        GameRegistry.registerBlock(mint, "M3Mint");
        GameRegistry.registerBlock(mintStorage, "M3MintStorage");
        GameRegistry.registerTileEntity(TileEntityMint.class, "TileEntityMint");
        GameRegistry.registerTileEntity(TileEntityMintStorage.class, "TileEntityMintStorage");
        LanguageRegistry.addName(mint, "Mint");
        LanguageRegistry.addName(mintStorage, "Mint Storage");
        coin = (new Item(ConfigMachines.coinID)).setUnlocalizedName("Metallurgy:Precious/coin").setCreativeTab(machineTab);
        stack = (new Item(ConfigMachines.stackID)).setUnlocalizedName("Metallurgy:Precious/ctack").setCreativeTab(machineTab);
        bag = (new Item(ConfigMachines.coinBagID)).setUnlocalizedName("Metallurgy:Precious/bag").setCreativeTab(machineTab);
        bullion = (new Item(ConfigMachines.bullionID)).setUnlocalizedName("Metallurgy:Precious/bullion").setCreativeTab(machineTab);
        goldCog = (new Item(ConfigMachines.goldCogID)).setUnlocalizedName("Metallurgy:Precious/goldCog").setCreativeTab(machineTab);
        LanguageRegistry.addName(coin, "Coin");
        LanguageRegistry.addName(stack, "Stack");
        LanguageRegistry.addName(bag, "Bag");
        LanguageRegistry.addName(bullion, "Bullion");
        LanguageRegistry.addName(goldCog, "Gold Cog");

        if (MetallurgyMetals.preciousSet != null)
        {
            Item var1 = MetallurgyMetals.preciousSet.getOreInfo("Silver").ingot;

            if (var1 != null)
            {
                MintRecipes.minting().addMinting(var1.itemID, 0, 3);
            }

            var1 = MetallurgyMetals.preciousSet.getOreInfo("Platinum").ingot;

            if (var1 != null)
            {
                MintRecipes.minting().addMinting(var1.itemID, 0, 27);
            }

            var1 = MetallurgyMetals.preciousSet.getOreInfo("Brass").ingot;

            if (var1 != null)
            {
                MintRecipes.minting().addMinting(var1.itemID, 0, 1);
            }

            var1 = MetallurgyMetals.preciousSet.getOreInfo("Electrum").ingot;

            if (var1 != null)
            {
                MintRecipes.minting().addMinting(var1.itemID, 0, 13);
            }

            MintRecipes.minting().addMinting(Item.ingotGold.itemID, 0, 9);
        }

        if (ConfigMachines.tradesEnabled)
        {
            for (int var2 = 0; var2 < 5; ++var2)
            {
                VillagerRegistry.instance().registerVillageTradeHandler(var2, new MetallurgyTradeHandler());
            }
        }
    }

    public void initChests()
    {
        chest = (new BlockMintStorage(ConfigMachines.chestID)).setUnlocalizedName("M3PreciousChest").setCreativeTab(machineTab);

    }

    public void loadMints()
    {
        if (ConfigMachines.mintEnabled)
        {
            GameRegistry.addRecipe(new ItemStack(goldCog), new Object[] {" G ", "GIG", " G ", 'G', Item.ingotGold, 'I', Item.ingotIron});
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(mint), new Object[] {"III", "SRS", "IPI", 'I', Item.ingotIron, 'S', Item.stick, 'R', Item.redstone, 'P', Block.pistonBase}));
            GameRegistry.addRecipe(new ItemStack(mintStorage), new Object[] {"GIG", "PCP", "GIG", 'G', goldCog, 'P', Block.pistonBase, 'C', Block.chest, 'I', Item.ingotIron});
        }

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(stack), new Object[] {"CCC", "CCC", "CCC", 'C', coin}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bag), new Object[] {"CCC", "CCC", "CCC", 'C', stack}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bullion), new Object[] {"CCC", "CCC", "CCC", 'C', bag}));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(coin, 9), new Object[] {stack}));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(stack, 9), new Object[] {bag}));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(bag, 9), new Object[] {bullion}));
    }
}
