package rebelkeithy.mods.metallurgy.machines.mint;

import cpw.mods.fml.common.registry.VillagerRegistry;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import rebelkeithy.mods.metallurgy.machines.MetallurgyMachines;
import rebelkeithy.mods.metallurgy.metals.MetallurgyMetals;

public class MetallurgyTradeHandler implements VillagerRegistry.IVillageTradeHandler
{
    public void manipulateTradesForVillager(EntityVillager var1, MerchantRecipeList var2, Random var3)
    {
        if (var1.getProfession() == 0)
        {
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Item.appleRed, 8)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Item.bread, 4)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Item.chickenCooked, 8)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Item.cookie, 10)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Item.melon, 8)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Item.arrow, 5)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 27), new ItemStack(Item.flintAndSteel, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 27), new ItemStack(Item.shears, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.chickenRaw, 17), new ItemStack(MetallurgyMachines.coin, 9)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.wheat, 17), new ItemStack(MetallurgyMachines.coin, 9)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.fishRaw, 12), new ItemStack(MetallurgyMachines.coin, 9)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(Block.cloth, 21), new ItemStack(MetallurgyMachines.coin, 9)));
        }
        else if (var1.getProfession() == 1)
        {
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Block.glass, 5)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 27), new ItemStack(Block.bookShelf, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 10), new ItemStack(Item.compass, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 10), new ItemStack(Item.pocketSundial, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.paper, 29), new ItemStack(MetallurgyMachines.coin, 9)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.book, 14), new ItemStack(MetallurgyMachines.coin, 9)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.writableBook), new ItemStack(MetallurgyMachines.coin, 9)));
        }
        else if (var1.getProfession() == 2)
        {
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Item.expBottle, 4)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Item.redstone, 4)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Block.glowStone, 3)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 10), new ItemStack(Item.eyeOfEnder, 1)));
        }

        if (var1.getProfession() == 3)
        {
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 9), new ItemStack(Item.helmetDiamond, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 19), new ItemStack(Item.plateDiamond, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 13), new ItemStack(Item.legsDiamond, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 9), new ItemStack(Item.bootsDiamond, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 13), new ItemStack(Item.swordDiamond, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 11), new ItemStack(Item.pickaxeDiamond, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 11), new ItemStack(Item.axeDiamond, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 7), new ItemStack(Item.shovelDiamond, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 7), new ItemStack(Item.hoeDiamond, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 6), new ItemStack(Item.helmetChain, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 14), new ItemStack(Item.plateChain, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 10), new ItemStack(Item.legsChain, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 6), new ItemStack(Item.bootsChain, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 5), new ItemStack(Item.helmetIron, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 13), new ItemStack(Item.plateIron, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 9), new ItemStack(Item.legsIron, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 5), new ItemStack(Item.bootsIron, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 10), new ItemStack(Item.swordIron, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 8), new ItemStack(Item.pickaxeIron, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 7), new ItemStack(Item.axeIron, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 5), new ItemStack(Item.shovelIron, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 5), new ItemStack(Item.hoeIron, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.ingotIron, 1), new ItemStack(MetallurgyMachines.coin, 3, 1)));

        }

        if (var1.getProfession() == 4)
        {
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Item.beefCooked, 7)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Item.porkCooked, 7)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 27), new ItemStack(Item.helmetLeather, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 27), new ItemStack(Item.plateLeather, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 27), new ItemStack(Item.legsLeather, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 27), new ItemStack(Item.bootsLeather, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 64), new ItemStack(Item.saddle, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.beefRaw, 17), new ItemStack(MetallurgyMachines.coin, 9)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.porkRaw, 17), new ItemStack(MetallurgyMachines.coin, 9)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.coal, 23), new ItemStack(MetallurgyMachines.coin, 9)));
        }
    }
}
