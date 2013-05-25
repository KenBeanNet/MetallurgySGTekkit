package rebelkeithy.mods.metallurgy.machines.mint;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class GuiMintStorage extends GuiContainer
{
    private IInventory upperChestInventory;
    private IInventory lowerChestInventory;
    private int inventoryRows = 0;
    private int inventoryCols = 0;
    private String image;

    public GuiMintStorage(InventoryPlayer var1, TileEntity var2)
    {
        super(new ContainerMintStorage(var1, var2));
        this.upperChestInventory = var1;
        this.lowerChestInventory = (IInventory)var2;
        this.allowUserInput = false;
        short var3 = 222;
        int var4 = var3 - 108;
        this.inventoryRows = 2;
        this.inventoryCols = 3;
        this.ySize = var4 + this.inventoryRows * 18;
        this.image = "/mods/Metallurgy/textures/guis/mintstorage.png";
    }

    protected void drawGuiContainerForegroundLayer() {}

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(this.image);
        short var4 = 176;
        short var5 = 168;
        int var6 = this.width / 2 - var4 / 2;
        int var7 = this.height / 2 - var5 / 2;
        this.drawTexturedModalRect(var6, var7, 0, 0, var4, var5);
    }
}
