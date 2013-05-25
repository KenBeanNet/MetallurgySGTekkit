package rebelkeithy.mods.metallurgy.machines;

import java.io.File;
import java.io.IOException;
import net.minecraftforge.common.Configuration;
import rebelkeithy.mods.metallurgy.core.MetallurgyCore;

public class ConfigMachines
{
    public static boolean chestEnabled = true;
    public static int chestID = 914;
    public static boolean mintEnabled = true;
    public static int mintID = 915;
    public static boolean mintStorageEnabled = true;
    public static int mintStorageID = 916;
    public static int coinID = 29002;
    public static int stackID = 29003;
    public static int coinBagID = 29004;
    public static int bullionID = 29005;
    public static int goldCogID = 29017;
    public static boolean tradesEnabled = true;

    public static void initConfig()
    {
        File var0 = new File(MetallurgyCore.proxy.getMinecraftDir() + "/config/Metallurgy3");
        var0.mkdir();
        File var1 = new File(MetallurgyCore.proxy.getMinecraftDir() + "/config/Metallurgy3/MetallurgyMachines.cfg");

        try
        {
            var1.createNewFile();
        }
        catch (IOException var5)
        {
            System.out.println(var5);
        }

        Configuration var2 = new Configuration(var1);
        mintID = var2.get("Block IDs", "Mint", mintID).getInt();
        mintStorageID = var2.get("Block IDs", "Mint Storage", mintStorageID).getInt();
        coinID = var2.get("Item IDs", "Coin", coinID).getInt();
        stackID = var2.get("Item IDs", "Stack", stackID).getInt();
        coinBagID = var2.get("Item IDs", "Coin Bag", coinBagID).getInt();
        bullionID = var2.get("Item IDs", "Bullion", bullionID).getInt();
        tradesEnabled = var2.get("Enabled", "Trades", tradesEnabled).getBoolean(true);
        
        var2.save();
    }
}
