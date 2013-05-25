package rebelkeithy.mods.metallurgy.api;

import java.util.Map;

public interface IMetalSet
{
    IOreInfo getOreInfo(String var1);

    IOreInfo getOreInfo(int var1);

    Map getOreList();
}
