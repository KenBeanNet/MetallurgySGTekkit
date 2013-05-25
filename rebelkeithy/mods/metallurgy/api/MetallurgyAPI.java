package rebelkeithy.mods.metallurgy.api;

import java.lang.reflect.Field;

public class MetallurgyAPI
{
    public static IMetalSet getMetalSet(String var0)
    {
        try
        {
            Class var1 = Class.forName("rebelkeithy.mods.metallurgy.metals.MetallurgyMetals");
            Field var2 = var1.getField(var0 + "Set");
            return (IMetalSet)var2.get((Object)null);
        }
        catch (NoSuchFieldException var3)
        {
            var3.printStackTrace();
        }
        catch (SecurityException var4)
        {
            var4.printStackTrace();
        }
        catch (ClassNotFoundException var5)
        {
            var5.printStackTrace();
        }
        catch (IllegalArgumentException var6)
        {
            var6.printStackTrace();
        }
        catch (IllegalAccessException var7)
        {
            var7.printStackTrace();
        }

        return null;
    }
}
