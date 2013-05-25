package rebelkeithy.mods.metallurgy.core.metalsets;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import rebelkeithy.mods.metallurgy.api.IMetalSet;
import rebelkeithy.mods.metallurgy.api.IOreInfo;
import rebelkeithy.mods.metallurgy.core.MetallurgyCore;

public class MetalSet implements IMetalSet
{
    private String setName;
    private Map metals;
    private Configuration config;

    public MetalSet(String var1, Map var2, CreativeTabs var3)
    {
        this.setName = var1;
        this.metals = new HashMap();
        Iterator var4 = var2.values().iterator();

        while (var4.hasNext())
        {
            Map var5 = (Map)var4.next();
            this.metals.put(var5.get("Name"), new OreInfo(var5, var3));
        }

        MetallurgyCore.getMetalSetList().add(this);
        this.initConfig();
        this.init();
    }

    public void initConfig()
    {
        File var1 = new File(MetallurgyCore.proxy.getMinecraftDir() + "/config/Metallurgy3");
        var1.mkdir();
        File var2 = new File(MetallurgyCore.proxy.getMinecraftDir() + "/config/Metallurgy3/Metallurgy" + this.setName + ".cfg");

        try
        {
            var2.createNewFile();
            System.out.println("[Metallurgy3] Successfully created/read configuration file for Metallurgy 3\'s metal set " + this.setName);
        }
        catch (IOException var5)
        {
            System.out.println("[Metallurgy3] Could not create configuration file for Metallurgy 3 metal set " + this.setName + ". Reason:");
            System.out.println(var5);
        }

        this.config = new Configuration(var2);
        this.config.load();
        Iterator var3 = this.metals.values().iterator();

        while (var3.hasNext())
        {
            IOreInfo var4 = (IOreInfo)var3.next();
            ((OreInfo)var4).initConfig(this.config);
        }

        this.config.save();
    }

    public void init()
    {
        Iterator var1 = this.metals.values().iterator();

        while (var1.hasNext())
        {
            IOreInfo var2 = (IOreInfo)var1.next();
            ((OreInfo)var2).init();
        }
    }

    public void load()
    {
        Iterator var1 = this.metals.values().iterator();

        while (var1.hasNext())
        {
            IOreInfo var2 = (IOreInfo)var1.next();
            ((OreInfo)var2).load();
        }
    }

    public void registerNames()
    {
        Iterator var1 = this.metals.values().iterator();

        while (var1.hasNext())
        {
            IOreInfo var2 = (IOreInfo)var1.next();
            ((OreInfo)var2).registerNames();
        }
    }

    public OreInfo getOreInfo(String var1)
    {
        return (OreInfo)this.metals.get(var1);
    }

    public OreInfo getOreInfo(int var1)
    {
        Iterator var2 = this.metals.values().iterator();
        IOreInfo var3;

        do
        {
            if (!var2.hasNext())
            {
                return null;
            }

            var3 = (IOreInfo)var2.next();
        }
        while (((OreInfo)var3).oreMeta != var1);

        return (OreInfo)var3;
    }

    public Map getOreList()
    {
        return this.metals;
    }
}
