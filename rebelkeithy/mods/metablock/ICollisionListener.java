package rebelkeithy.mods.metablock;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public interface ICollisionListener
{
    void collide(World var1, int var2, int var3, int var4, Entity var5, int var6);
}
