package rebelkeithy.mods.metallurgy.core;

import net.minecraft.creativetab.CreativeTabs;

public class MetallurgyTabs extends CreativeTabs
{
    int iconID = 1;

    public MetallurgyTabs(String var1)
    {
        super(var1);
    }

    public void setIconItem(int var1)
    {
        this.iconID = var1;
    }

    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex()
    {
        return this.iconID;
    }
}
