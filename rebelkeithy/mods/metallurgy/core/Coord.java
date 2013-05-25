package rebelkeithy.mods.metallurgy.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Coord
{
    public static final List adjacent = new ArrayList();
    public int x;
    public int y;
    public int z;

    public Coord(int var1, int var2, int var3)
    {
        this.x = var1;
        this.y = var2;
        this.z = var3;
    }

    public Coord(Coord var1)
    {
        this.x = var1.x;
        this.y = var1.y;
        this.z = var1.z;
    }

    public static List getAdjacentCoords(Coord var0)
    {
        ArrayList var1 = new ArrayList();
        Iterator var2 = adjacent.iterator();

        while (var2.hasNext())
        {
            Coord var3 = (Coord)var2.next();
            var1.add(var3.add(var0));
        }

        return var1;
    }

    public static List between(Coord var0, Coord var1)
    {
        ArrayList var2 = new ArrayList();

        for (int var3 = Math.min(var0.x, var1.x); var3 <= Math.max(var0.x, var1.x); ++var3)
        {
            for (int var4 = Math.min(var0.y, var1.y); var4 <= Math.max(var0.y, var1.y); ++var4)
            {
                for (int var5 = Math.min(var0.z, var1.z); var5 <= Math.max(var0.z, var1.z); ++var5)
                {
                    var2.add(new Coord(var3, var4, var5));
                }
            }
        }

        return var2;
    }

    private Coord add(Coord var1)
    {
        return new Coord(this.x + var1.x, this.y + var1.y, this.z + var1.z);
    }

    public Coord clone()
    {
        return new Coord(this);
    }

    public boolean equals(Object var1)
    {
        Coord var2 = (Coord)var1;
        return this.x == var2.x && this.y == var2.y && this.z == var2.z;
    }

    public String toString()
    {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }

    public Coord subtract(Coord var1)
    {
        return new Coord(this.x - var1.x, this.y - var1.y, this.z - var1.z);
    }

    public boolean equals(int var1, int var2, int var3)
    {
        return this.x == var1 && this.y == var2 && this.z == var3;
    }

    static
    {
        adjacent.add(new Coord(-1, 0, 0));
        adjacent.add(new Coord(1, 0, 0));
        adjacent.add(new Coord(0, -1, 0));
        adjacent.add(new Coord(0, 1, 0));
        adjacent.add(new Coord(0, 0, -1));
        adjacent.add(new Coord(0, 0, 1));
    }
}
