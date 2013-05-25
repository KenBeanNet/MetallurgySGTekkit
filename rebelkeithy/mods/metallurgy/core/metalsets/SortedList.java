package rebelkeithy.mods.metallurgy.core.metalsets;

import java.util.ArrayList;
import java.util.Comparator;

public class SortedList extends ArrayList
{
    Comparator comp;

    public SortedList(Comparator var1)
    {
        this.comp = var1;
    }

    public boolean add(Object var1)
    {
        for (int var2 = 0; var2 < this.size(); ++var2)
        {
            if (this.comp.compare(this.get(var2), var1) >= 0)
            {
                this.add(var2 + 1, var1);
                return true;
            }
        }

        this.add(this.size(), var1);
        return true;
    }
}
