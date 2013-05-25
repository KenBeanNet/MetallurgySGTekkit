package rebelkeithy.mods.metablock;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringTranslate;

public class ItemMetaBlock extends ItemBlock
{
    public ItemMetaBlock(int var1)
    {
        super(var1);
        this.setHasSubtypes(true);
    }

    public CreativeTabs[] getCreativeTabs()
    {
        return ((MetaBlock)Block.blocksList[this.getBlockID()]).getCreativeTabArray();
    }

    public String getItemDisplayName(ItemStack var1)
    {
        return ("" + StringTranslate.getInstance().translateNamedKey(this.getLocalizedName(var1))).trim();
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int var1)
    {
        return var1;
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack var1)
    {
        int var2 = var1.getItemDamage();
        return this.getUnlocalizedName() + "." + var2;
    }
}
