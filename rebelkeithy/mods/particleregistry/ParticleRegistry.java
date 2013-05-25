package rebelkeithy.mods.particleregistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class ParticleRegistry
{
    private static Map particleList = new HashMap();

    public static void registerParticle(String var0, Class var1)
    {
        particleList.put(var0, var1);
    }

    @SideOnly(Side.CLIENT)
    public static void spawnParticle(String var0, World var1, double var2, double var4, double var6, double var8, double var10, double var12)
    {
        Class var14 = (Class)particleList.get(var0);

        if (var14 != null)
        {
            try
            {
                Constructor var15 = var14.getConstructor(new Class[] {World.class, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE});

                if (var15 != null)
                {
                    EntityFX var16 = (EntityFX)var15.newInstance(new Object[] {var1, Double.valueOf(var2), Double.valueOf(var4), Double.valueOf(var6), Double.valueOf(var8), Double.valueOf(var10), Double.valueOf(var12)});
                    var16.setRBGColorF((float)var8, (float)var10, (float)var12);
                    Minecraft.getMinecraft().effectRenderer.addEffect(var16);
                }
            }
            catch (Exception var17)
            {
                var17.printStackTrace();
            }
        }
    }
}
