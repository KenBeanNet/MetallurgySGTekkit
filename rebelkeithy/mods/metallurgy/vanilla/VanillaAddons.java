package rebelkeithy.mods.metallurgy.vanilla;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import java.io.File;
import java.io.IOException;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import rebelkeithy.mods.metablock.MetaBlock;
import rebelkeithy.mods.metablock.SubBlock;
import rebelkeithy.mods.metallurgy.core.MetallurgyCore;

public class VanillaAddons
{
    public static SubBlock goldBrick;
    public static SubBlock ironBrick;
    public static Item dustIron;
    public static Item dustGold;
    public static int goldBrickID;
    public static int ironBrickID;
    public static int goldBrickMeta;
    public static int ironBrickMeta;

    public static void init()
    {
        initConfig();
        goldBrick = (new SubBlock(goldBrickID, goldBrickMeta, "Metallurgy:Vanilla/GoldBrick")).setUnlocalizedName("Metallurgy:Vanilla/GoldBricks").setCreativeTab(CreativeTabs.tabBlock);
        ironBrick = (new SubBlock(ironBrickID, ironBrickMeta, "Metallurgy:Vanilla/IronBrick")).setUnlocalizedName("Metallurgy:Vanilla/IronBricks").setCreativeTab(CreativeTabs.tabBlock);
        MetaBlock.registerID(goldBrickID);
        MetaBlock.registerID(ironBrickID);
    }

    public static void initConfig()
    {
        File var0 = new File(MetallurgyCore.proxy.getMinecraftDir() + "/config/Metallurgy3");
        var0.mkdir();
        File var1 = new File(MetallurgyCore.proxy.getMinecraftDir() + "/config/Metallurgy3/MetallurgyVanilla.cfg");

        try
        {
            var1.createNewFile();
            System.out.println("[Metallurgy3] Successfully created/read configuration file for Metallurgy 3\'s metal set Vanilla");
        }
        catch (IOException var3)
        {
            System.out.println("[Metallurgy3] Could not create configuration file for Metallurgy 3 metal set Vanilla. Reason:");
            System.out.println(var3);
        }

        Configuration var2 = new Configuration(var1);
        var2.load();
        ironBrickID = Integer.parseInt(var2.get("Iron", "Brick ID", "900:3").getString().split(":")[0]);
        ironBrickMeta = Integer.parseInt(var2.get("Iron", "Brick ID", "900:3").getString().split(":")[1]);
        goldBrickID = Integer.parseInt(var2.get("Gold", "Brick ID", "900:4").getString().split(":")[0]);
        goldBrickMeta = Integer.parseInt(var2.get("Gold", "Brick ID", "900:4").getString().split(":")[1]);
        var2.save();
    }

    public static void load()
    {
        GameRegistry.addRecipe(new ItemStack(goldBrickID, 4, goldBrickMeta), new Object[] {"XX", "XX", 'X', Item.ingotGold});
        GameRegistry.addRecipe(new ItemStack(ironBrickID, 4, ironBrickMeta), new Object[] {"XX", "XX", 'X', Item.ingotIron});
        GameRegistry.addShapelessRecipe(new ItemStack(Item.ingotGold), new Object[] {new ItemStack(goldBrickID, 1, goldBrickMeta)});
        GameRegistry.addShapelessRecipe(new ItemStack(Item.ingotIron), new Object[] {new ItemStack(ironBrickID, 1, ironBrickMeta)});
    }

    public static void registerNames()
    {
        LanguageRegistry.addName(new ItemStack(goldBrickID, 1, goldBrickMeta), "Gold Bricks");
        LanguageRegistry.addName(new ItemStack(ironBrickID, 1, ironBrickMeta), "Iron Bricks");
    }
}
