package rebelkeithy.mods.metallurgy.core.metalsets;

import java.util.Comparator;

class MetallurgyWorldGenMinable$CompareCoordinates implements Comparator
{
    final MetallurgyWorldGenMinable this$0;

    private MetallurgyWorldGenMinable$CompareCoordinates(MetallurgyWorldGenMinable var1)
    {
        this.this$0 = var1;
    }

    public int compare(Integer[] var1, Integer[] var2)
    {
        return var1[0].intValue() - var2[0].intValue() + (var1[1].intValue() - var2[1].intValue()) + (var1[2].intValue() - var2[2].intValue());
    }

    public int compare(Object var1, Object var2)
    {
        return this.compare((Integer[])var1, (Integer[])var2);
    }
}
