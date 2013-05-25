package rebelkeithy.mods.guiregistry;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.network.IGuiHandler;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiRegistry implements IGuiHandler
{
    private static Map guiList = new HashMap();
    private static Map containerList = new HashMap();
    private static GuiRegistry instance;

    public static GuiRegistry instance()
    {
        if (instance == null)
        {
            instance = new GuiRegistry();
        }

        return instance;
    }

    public static void registerGuiServer(Class var0, Object var1, String var2)
    {
        ModContainer var3 = FMLCommonHandler.instance().findContainerFor(var1);
        String var4 = var2 + var3.getName();
        containerList.put(Integer.valueOf(var4.hashCode()), var0);
    }

    public static void registerGuiClient(Class var0, Class var1, Object var2, String var3)
    {
        ModContainer var4 = FMLCommonHandler.instance().findContainerFor(var2);
        String var5 = var3 + var4.getName();
        guiList.put(Integer.valueOf(var5.hashCode()), var0);
        containerList.put(Integer.valueOf(var5.hashCode()), var1);
    }

    public static void openGui(String var0, Object var1, EntityPlayer var2, World var3, int var4, int var5, int var6)
    {
        ModContainer var7 = FMLCommonHandler.instance().findContainerFor(var1);
        String var8 = var0 + var7.getName();
        var2.openGui(var1, var8.hashCode(), var3, var4, var5, var6);
    }

    public Object getServerGuiElement(int var1, EntityPlayer var2, World var3, int var4, int var5, int var6)
    {
        TileEntity var7 = var3.getBlockTileEntity(var4, var5, var6);
        Container var8 = null;

        try
        {
            var8 = (Container)((Class)containerList.get(Integer.valueOf(var1))).getConstructor(new Class[] {InventoryPlayer.class, TileEntity.class}).newInstance(new Object[] {var2.inventory, var7});
        }
        catch (Exception var10)
        {
            var10.printStackTrace();
        }

        return var8;
    }

    public Object getClientGuiElement(int var1, EntityPlayer var2, World var3, int var4, int var5, int var6)
    {
        TileEntity var7 = var3.getBlockTileEntity(var4, var5, var6);
        GuiContainer var8 = null;

        try
        {
            var8 = (GuiContainer)((Class)guiList.get(Integer.valueOf(var1))).getConstructor(new Class[] {InventoryPlayer.class, TileEntity.class}).newInstance(new Object[] {var2.inventory, var7});
        }
        catch (Exception var10)
        {
            var10.printStackTrace();
        }

        return var8;
    }
}
