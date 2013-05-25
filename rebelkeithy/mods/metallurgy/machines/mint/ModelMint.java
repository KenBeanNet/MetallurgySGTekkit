package rebelkeithy.mods.metallurgy.machines.mint;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelMint extends ModelBase
{
    private ModelRenderer Body;
    private ModelRenderer Shape2;
    private ModelRenderer Shape3;
    private ModelRenderer Shape4;
    private ModelRenderer Shape5;
    private ModelRenderer Bar0;
    private ModelRenderer Bar1;
    private ModelRenderer Bar2;
    private ModelRenderer Bar3;
    private ModelRenderer Head;

    public ModelMint()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Body = new ModelRenderer(this, 0, 11);
        this.Body.addBox(0.0F, 11.0F, 0.0F, 16, 5, 16);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.setTextureSize(64, 32);
        this.Body.mirror = true;
        this.setRotation(this.Body, 0.0F, 0.0F, 0.0F);
        this.Shape2 = new ModelRenderer(this, 0, 1);
        this.Shape2.addBox(0.0F, 10.0F, 0.0F, 16, 1, 4);
        this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape2.setTextureSize(64, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        this.Shape3 = new ModelRenderer(this, 0, 6);
        this.Shape3.addBox(0.0F, 10.0F, 12.0F, 16, 1, 4);
        this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape3.setTextureSize(64, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
        this.Shape4 = new ModelRenderer(this, 30, 2);
        this.Shape4.addBox(0.0F, 10.0F, 4.0F, 6, 1, 8);
        this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape4.setTextureSize(64, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
        this.Shape5 = new ModelRenderer(this, 36, 2);
        this.Shape5.addBox(10.0F, 10.0F, 4.0F, 6, 1, 8);
        this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape5.setTextureSize(64, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
        this.Bar0 = new ModelRenderer(this, 0, 16);
        this.Bar0.addBox(1.0F, 0.0F, 1.0F, 1, 10, 1);
        this.Bar0.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Bar0.setTextureSize(64, 32);
        this.Bar0.mirror = true;
        this.setRotation(this.Bar0, 0.0F, 0.0F, 0.0F);
        this.Bar1 = new ModelRenderer(this, 0, 16);
        this.Bar1.addBox(14.0F, 0.0F, 1.0F, 1, 10, 1);
        this.Bar1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Bar1.setTextureSize(64, 32);
        this.Bar1.mirror = true;
        this.setRotation(this.Bar1, 0.0F, 0.0F, 0.0F);
        this.Bar2 = new ModelRenderer(this, 0, 16);
        this.Bar2.addBox(1.0F, 0.0F, 14.0F, 1, 10, 1);
        this.Bar2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Bar2.setTextureSize(64, 32);
        this.Bar2.mirror = true;
        this.setRotation(this.Bar2, 0.0F, 0.0F, 0.0F);
        this.Bar3 = new ModelRenderer(this, 0, 16);
        this.Bar3.addBox(14.0F, 0.0F, 14.0F, 1, 10, 1);
        this.Bar3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Bar3.setTextureSize(64, 32);
        this.Bar3.mirror = true;
        this.setRotation(this.Bar3, 0.0F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 0, 11);
        this.Head.addBox(0.0F, 0.0F, 0.0F, 16, 4, 16);
        this.Head.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.Head.setTextureSize(64, 32);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0F, 0.0F, 0.0F);
    }

    public void renderAll()
    {
        this.Body.render(0.0625F);
        this.Shape2.render(0.0625F);
        this.Shape3.render(0.0625F);
        this.Shape4.render(0.0625F);
        this.Shape5.render(0.0625F);
        this.Bar0.render(0.0625F);
        this.Bar1.render(0.0625F);
        this.Bar2.render(0.0625F);
        this.Bar3.render(0.0625F);
    }

    private void setRotation(ModelRenderer var1, float var2, float var3, float var4)
    {
        var1.rotateAngleX = var2;
        var1.rotateAngleY = var3;
        var1.rotateAngleZ = var4;
    }
}
