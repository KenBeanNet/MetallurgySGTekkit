package rebelkeithy.mods.metallurgy.core.metalsets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import rebelkeithy.mods.metallurgy.core.MetallurgyCore;

public class MetallurgyWorldGenMinable extends WorldGenerator
{
    private List replaceableBlocks;
    private int numberOfBlocks;
    private int metadata;
    private int minableBlockId;
    private int density;
    private static List possibleMoves = new ArrayList();

    public MetallurgyWorldGenMinable(int var1, int var2, int var3, int var4, Object ... var5)
    {
        this.minableBlockId = var1;
        this.metadata = var2;
        this.numberOfBlocks = var3;
        this.density = var4;
        this.replaceableBlocks = new ArrayList();
        Object[] var6 = var5;
        int var7 = var5.length;

        for (int var8 = 0; var8 < var7; ++var8)
        {
            Object var9 = var6[var8];
            this.replaceableBlocks.add((Integer)var9);
        }
    }

    public boolean spawnOre(World var1, Integer[] var2)
    {
        return this.spawnOre(var1, var2[0].intValue(), var2[1].intValue(), var2[2].intValue());
    }

    public boolean spawnOre(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);

        if (this.replaceableBlocks.contains(Integer.valueOf(var5)))
        {
            var1.setBlock(var2, var3, var4, this.minableBlockId, this.metadata, 2);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean generate(World var1, Random var2, int var3, int var4, int var5)
    {
        float var6 = var2.nextFloat() * (float)Math.PI;
        double var7 = (double)((float)(var3 + 8) + MathHelper.sin(var6) * (float)this.numberOfBlocks / 8.0F);
        double var9 = (double)((float)(var3 + 8) - MathHelper.sin(var6) * (float)this.numberOfBlocks / 8.0F);
        double var11 = (double)((float)(var5 + 8) + MathHelper.cos(var6) * (float)this.numberOfBlocks / 8.0F);
        double var13 = (double)((float)(var5 + 8) - MathHelper.cos(var6) * (float)this.numberOfBlocks / 8.0F);
        double var15 = (double)(var4 + var2.nextInt(3) - 2);
        double var17 = (double)(var4 + var2.nextInt(3) - 2);

        for (int var19 = 0; var19 <= this.numberOfBlocks; ++var19)
        {
            double var20 = var7 + (var9 - var7) * (double)var19 / (double)this.numberOfBlocks;
            double var22 = var15 + (var17 - var15) * (double)var19 / (double)this.numberOfBlocks;
            double var24 = var11 + (var13 - var11) * (double)var19 / (double)this.numberOfBlocks;
            double var26 = var2.nextDouble() * (double)this.numberOfBlocks / 16.0D;
            double var28 = (double)(MathHelper.sin((float)var19 * (float)Math.PI / (float)this.numberOfBlocks) + 1.0F) * var26 + 1.0D;
            double var30 = (double)(MathHelper.sin((float)var19 * (float)Math.PI / (float)this.numberOfBlocks) + 1.0F) * var26 + 1.0D;
            int var32 = MathHelper.floor_double(var20 - var28 / 2.0D);
            int var33 = MathHelper.floor_double(var22 - var30 / 2.0D);
            int var34 = MathHelper.floor_double(var24 - var28 / 2.0D);
            int var35 = MathHelper.floor_double(var20 + var28 / 2.0D);
            int var36 = MathHelper.floor_double(var22 + var30 / 2.0D);
            int var37 = MathHelper.floor_double(var24 + var28 / 2.0D);

            for (int var38 = var32; var38 <= var35; ++var38)
            {
                double var39 = ((double)var38 + 0.5D - var20) / (var28 / 2.0D);

                if (var39 * var39 < 1.0D)
                {
                    for (int var41 = var33; var41 <= var36; ++var41)
                    {
                        double var42 = ((double)var41 + 0.5D - var22) / (var30 / 2.0D);

                        if (var39 * var39 + var42 * var42 < 1.0D)
                        {
                            for (int var44 = var34; var44 <= var37; ++var44)
                            {
                                double var45 = ((double)var44 + 0.5D - var24) / (var28 / 2.0D);
                                Block var47 = Block.blocksList[var1.getBlockId(var38, var41, var44)];

                                if (var39 * var39 + var42 * var42 + var45 * var45 < 1.0D)
                                {
                                    if (MetallurgyCore.spawnInAir)
                                    {
                                        var1.setBlock(var38, var41, var44, this.minableBlockId, this.metadata, 2);
                                    }
                                    else if (var47 != null && (var47.blockID == Block.stone.blockID || var47.isGenMineableReplaceable(var1, var38, var41, var44, Block.stone.blockID) || var47.blockID == Block.netherrack.blockID || var47.blockID == Block.whiteStone.blockID))
                                    {
                                        var1.setBlock(var38, var41, var44, this.minableBlockId, this.metadata, 2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    public boolean generate2(World var1, Random var2, int var3, int var4, int var5)
    {
        ArrayList var6 = new ArrayList();
        this.spawnOre(var1, var3, var4, var5);
        var6.add(new Integer[] {Integer.valueOf(var3), Integer.valueOf(var4), Integer.valueOf(var5)});
        int var7 = 1;

        for (int var8 = 1; var8 < this.numberOfBlocks; ++var8)
        {
            ArrayList var9 = new ArrayList();
            Iterator var10 = possibleMoves.iterator();
            Integer[] var11;

            while (var10.hasNext())
            {
                var11 = (Integer[])var10.next();
                var9.add(var11);
            }

            int var10000 = (int)(var2.nextFloat() * var2.nextFloat() * (float)var6.size());
            int var15 = var6.size() - 1;
            var11 = (Integer[])var6.get(var15);
            Integer[] var12 = new Integer[] {var11[0], var11[1], var11[2]};

            do
            {
                if (var9.size() == 0)
                {
                    --var8;
                    var6.remove(var15);
                    break;
                }

                int var13 = (int)(var2.nextFloat() * (float)var9.size());
                Integer[] var14 = (Integer[])var9.get(var13);
                var12 = new Integer[] {Integer.valueOf(var11[0].intValue() + var14[0].intValue()), Integer.valueOf(var11[1].intValue() + var14[1].intValue()), Integer.valueOf(var11[2].intValue() + var14[2].intValue())};
                var9.remove(var13);
            }
            while (var6.contains(var12));

            if (var2.nextInt(100) < this.density)
            {
                this.spawnOre(var1, var12);
                ++var7;
            }

            var6.add(var12);
        }

        return true;
    }

    static
    {
        possibleMoves.add(new Integer[] {Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(0)});
        possibleMoves.add(new Integer[] {Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(0)});
        possibleMoves.add(new Integer[] {Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(1)});
        possibleMoves.add(new Integer[] {Integer.valueOf(-1), Integer.valueOf(0), Integer.valueOf(0)});
        possibleMoves.add(new Integer[] {Integer.valueOf(0), Integer.valueOf(-1), Integer.valueOf(0)});
        possibleMoves.add(new Integer[] {Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(-1)});
        possibleMoves.add(new Integer[] {Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(0)});
        possibleMoves.add(new Integer[] {Integer.valueOf(-1), Integer.valueOf(1), Integer.valueOf(0)});
        possibleMoves.add(new Integer[] {Integer.valueOf(1), Integer.valueOf(-1), Integer.valueOf(0)});
        possibleMoves.add(new Integer[] {Integer.valueOf(-1), Integer.valueOf(-1), Integer.valueOf(0)});
        possibleMoves.add(new Integer[] {Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(1)});
        possibleMoves.add(new Integer[] {Integer.valueOf(-1), Integer.valueOf(0), Integer.valueOf(1)});
        possibleMoves.add(new Integer[] {Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(-1)});
        possibleMoves.add(new Integer[] {Integer.valueOf(-1), Integer.valueOf(0), Integer.valueOf(-1)});
        possibleMoves.add(new Integer[] {Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(1)});
        possibleMoves.add(new Integer[] {Integer.valueOf(0), Integer.valueOf(-1), Integer.valueOf(1)});
        possibleMoves.add(new Integer[] {Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(-1)});
        possibleMoves.add(new Integer[] {Integer.valueOf(0), Integer.valueOf(-1), Integer.valueOf(-1)});
    }
}
