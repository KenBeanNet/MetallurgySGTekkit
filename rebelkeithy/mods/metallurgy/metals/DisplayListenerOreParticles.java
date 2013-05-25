package rebelkeithy.mods.metallurgy.metals;

import java.util.Random;
import net.minecraft.world.World;
import rebelkeithy.mods.metablock.IDisplayListener;
import rebelkeithy.mods.particleregistry.ParticleRegistry;

public class DisplayListenerOreParticles implements IDisplayListener
{
    String name;
    double r;
    double g;
    double b;

    DisplayListenerOreParticles(String var1, double var2, double var4, double var6)
    {
        this.name = var1;
        this.r = var2;
        this.g = var4;
        this.b = var6;
    }

    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        double var6 = 0.0625D;

        for (int var8 = 0; var8 < 6; ++var8)
        {
            if (Math.random() >= 0.3D)
            {
                double var9 = (double)var2 + var5.nextDouble();
                double var11 = (double)var3 + var5.nextDouble();
                double var13 = (double)var4 + var5.nextDouble();

                if (var8 == 0 && !var1.isBlockOpaqueCube(var2, var3 + 1, var4))
                {
                    var11 = (double)(var3 + 1) + var6;
                }

                if (var8 == 1 && !var1.isBlockOpaqueCube(var2, var3 - 1, var4))
                {
                    var11 = (double)(var3 + 0) - var6;
                }

                if (var8 == 2 && !var1.isBlockOpaqueCube(var2, var3, var4 + 1))
                {
                    var13 = (double)(var4 + 1) + var6;
                }

                if (var8 == 3 && !var1.isBlockOpaqueCube(var2, var3, var4 - 1))
                {
                    var13 = (double)(var4 + 0) - var6;
                }

                if (var8 == 4 && !var1.isBlockOpaqueCube(var2 + 1, var3, var4))
                {
                    var9 = (double)(var2 + 1) + var6;
                }

                if (var8 == 5 && !var1.isBlockOpaqueCube(var2 - 1, var3, var4))
                {
                    var9 = (double)(var2 + 0) - var6;
                }

                if (var9 < (double)var2 || var9 > (double)(var2 + 1) || var11 < 0.0D || var11 > (double)(var3 + 1) || var13 < (double)var4 || var13 > (double)(var4 + 1))
                {
                    ParticleRegistry.spawnParticle(this.name, var1, var9, var11, var13, this.r, this.g, this.b);
                }
            }
        }
    }
}
