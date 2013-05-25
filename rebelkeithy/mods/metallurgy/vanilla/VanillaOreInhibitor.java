package rebelkeithy.mods.metallurgy.vanilla;

import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;

public class VanillaOreInhibitor
{
    @ForgeSubscribe
    public void stopOre(GenerateMinable var1)
    {
        if (var1.type != EventType.CUSTOM)
        {
            var1.setResult(Result.DENY);
        }
    }
}
