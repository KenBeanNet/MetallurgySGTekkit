package rebelkeithy.mods.metallurgy.core;

import cpw.mods.fml.common.registry.LanguageRegistry;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.OreDictionary;

public class MetalInfoDatabase
{
    private static List spreadsheet;
    private static Map items;
    private static Map oreDictNames;

    private static void readOreData(BufferedReader var0)
    {
        if (spreadsheet == null)
        {
            spreadsheet = new ArrayList();
        }

        try
        {
            String var1 = var0.readLine();
            String[] var2 = var1.split(",");

            for (var1 = var0.readLine(); var1 != null; var1 = var0.readLine())
            {
                String[] var3 = var1.split(",");
                HashMap var4 = new HashMap();

                for (int var5 = 0; var5 < var3.length; ++var5)
                {
                    if (var3[var5].equals("") || var3[var5].equals("-"))
                    {
                        var3[var5] = "0";
                    }

                    var4.put(var2[var5], var3[var5]);
                }

                spreadsheet.add(var4);
            }

            var0.close();
        }
        catch (IOException var6)
        {
            var6.printStackTrace();
        }
    }

    private static void readItemData(Configuration var0, BufferedReader var1, CreativeTabs var2)
    {
        if (items == null)
        {
            items = new HashMap();
        }

        if (oreDictNames == null)
        {
            oreDictNames = new HashMap();
        }

        try
        {
            String var3 = var1.readLine();
            String[] var4 = var3.split(",");

            for (var3 = var1.readLine(); var3 != null; var3 = var1.readLine())
            {
                String[] var5 = var3.split(",");
                HashMap var6 = new HashMap();
                int var7;

                for (var7 = 0; var7 < var5.length; ++var7)
                {
                    if (var5[var7].equals(""))
                    {
                        var5[var7] = "0";
                    }

                    System.out.println("reading property " + var4[var7] + " as " + var5[var7]);
                    var6.put(var4[var7], var5[var7]);
                }

                var7 = Integer.parseInt((String)var6.get("Item ID"));
                System.out.println("config for item " + (String)var6.get("Item Name") + " = " + var7);
                var7 = var0.get("Item IDs", (String)var6.get("Item Name"), var7).getInt();
                Item var8 = (new Item(var7)).setUnlocalizedName("Metallurgy:" + (String)var6.get("Set Name") + "/" + (String)var6.get("Item Name")).setCreativeTab(var2);
                LanguageRegistry.addName(var8, (String)var6.get("Item Name"));
                items.put(var6.get("Item Name"), var8);

                if (!((String)var6.get("Ore Dictionary Name")).equals("0"))
                {
                    oreDictNames.put(var6.get("Item Name"), var6.get("Ore Dictionary Name"));
                }
            }

            var1.close();
        }
        catch (IOException var9)
        {
            var9.printStackTrace();
        }
    }

    public static void registerItemsWithOreDict()
    {
        Iterator var0 = oreDictNames.keySet().iterator();

        while (var0.hasNext())
        {
            String var1 = (String)var0.next();
            OreDictionary.registerOre((String)oreDictNames.get(var1), (Item)items.get(var1));
        }
    }

    public static void readItemDataFromFile(Configuration var0, String var1, CreativeTabs var2)
    {
        try
        {
            File var3 = new File(var1);
            BufferedReader var4 = new BufferedReader(new FileReader(var3));
            readItemData(var0, var4, var2);
        }
        catch (FileNotFoundException var5)
        {
            var5.printStackTrace();
        }
    }

    public static void readItemDataFromJar(Configuration var0, String var1, String var2, CreativeTabs var3)
    {
        try
        {
            ZipFile var4 = new ZipFile(var2);
            ZipEntry var5 = var4.getEntry(var1);
            BufferedReader var6 = new BufferedReader(new InputStreamReader(var4.getInputStream(var5)));
            readItemData(var0, var6, var3);
        }
        catch (IOException var7)
        {
            var7.printStackTrace();
        }
    }

    public static void readMetalDataFromFile(String var0)
    {
        try
        {
            File var1 = new File(var0);
            BufferedReader var2 = new BufferedReader(new FileReader(var1));
            readOreData(var2);
        }
        catch (FileNotFoundException var3)
        {
            var3.printStackTrace();
        }
    }

    public static void readMetalDataFromJar(String var0, String var1)
    {
        System.out.println("reading file " + var0 + "  from file " + var1);

        try
        {
            ZipFile var2 = new ZipFile(var1);
            ZipEntry var3 = var2.getEntry(var0);
            BufferedReader var4 = new BufferedReader(new InputStreamReader(var2.getInputStream(var3)));
            readOreData(var4);
        }
        catch (IOException var5)
        {
            var5.printStackTrace();
        }
    }

    public static Map getSpreadsheetDataForSet(String var0)
    {
        HashMap var1 = new HashMap();

        if (spreadsheet == null)
        {
            return var1;
        }
        else
        {
            Iterator var2 = spreadsheet.iterator();

            while (var2.hasNext())
            {
                Map var3 = (Map)var2.next();

                if (((String)var3.get("Metal Set")).equals(var0))
                {
                    var1.put(var3.get("Name"), var3);
                }
            }

            return var1;
        }
    }

    public static ItemStack getItem(String var0)
    {
        return items != null && items.containsKey(var0) ? new ItemStack((Item)items.get(var0)) : null;
    }
}
