package rebelkeithy.mods.metallurgy.machines.mint;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelIngot extends ModelBase
{
    ModelRenderer Shape1;

    public ModelIngot()
    {
        this.textureWidth = 32;
        this.textureHeight = 16;
        this.Shape1 = new ModelRenderer(this, 0, 0);
        this.Shape1.addBox(-12.0F, 9.0F, 6.0F, 8, 2, 4);
        this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, ((float)Math.PI / 2F), 0.0F);
    }

    public void renderAll()
    {
        this.Shape1.render(0.0625F);
    }

    private void setRotation(ModelRenderer var1, float var2, float var3, float var4)
    {
        var1.rotateAngleX = var2;
        var1.rotateAngleY = var3;
        var1.rotateAngleZ = var4;
    }
}
