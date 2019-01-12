package com.ubalube.scifiaddon.entity.model.shields;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

/**
 * Blitzshield - Undefined
 * Created using Tabula 7.0.0
 */
public class Blitzshield extends ModelShield {
    public double[] modelScale = new double[] { 2.0D, 2.0D, 2.0D };
    public ModelRenderer handle;
    public ModelRenderer plate;
    public ModelRenderer Bottom;
    public ModelRenderer plate_1;
    public ModelRenderer plate_2;
    public ModelRenderer Bottom_1;
    public ModelRenderer Window;
    public ModelRenderer Bottom_2;
    public ModelRenderer Bottom_3;

    public Blitzshield() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Bottom_3 = new ModelRenderer(this, 29, 34);
        this.Bottom_3.setRotationPoint(12.0F, 0.0F, 0.0F);
        this.Bottom_3.addBox(0.0F, 0.0F, 0.0F, 5, 16, 1, 0.0F);
        this.setRotateAngle(Bottom_3, 0.0F, -0.3673918075448064F, 0.0F);
        this.Bottom = new ModelRenderer(this, 0, 0);
        this.Bottom.setRotationPoint(-2.0F, 7.0F, 0.0F);
        this.Bottom.addBox(0.0F, 0.0F, 0.0F, 12, 17, 1, 0.0F);
        this.plate = new ModelRenderer(this, 0, 25);
        this.plate.setRotationPoint(-4.0F, -11.0F, -2.0F);
        this.plate.addBox(0.0F, 0.0F, 0.0F, 8, 2, 1, 0.0F);
        this.Window = new ModelRenderer(this, 41, 23);
        this.Window.setRotationPoint(8.0F, 2.0F, 0.3F);
        this.Window.addBox(0.0F, 0.0F, 0.0F, 5, 8, 1, 0.0F);
        this.setRotateAngle(Window, 0.0F, 0.0F, 1.5707963267948966F);
        this.plate_2 = new ModelRenderer(this, 24, 29);
        this.plate_2.setRotationPoint(10.0F, 0.0F, 0.0F);
        this.plate_2.addBox(0.0F, 0.0F, 0.0F, 7, 2, 1, 0.0F);
        this.setRotateAngle(plate_2, 0.0F, 0.0F, 1.5707963267948966F);
        this.handle = new ModelRenderer(this, 47, 8);
        this.handle.setRotationPoint(-1.0F, -3.0F, -1.0F);
        this.handle.addBox(0.0F, 0.0F, 0.0F, 2, 6, 6, 0.0F);
        this.plate_1 = new ModelRenderer(this, 24, 25);
        this.plate_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.plate_1.addBox(0.0F, 0.0F, 0.0F, 7, 2, 1, 0.0F);
        this.setRotateAngle(plate_1, 0.0F, 0.0F, 1.5707963267948966F);
        this.Bottom_1 = new ModelRenderer(this, 45, 1);
        this.Bottom_1.setRotationPoint(0.0F, 8.3F, -0.4F);
        this.Bottom_1.addBox(0.0F, 0.0F, 0.0F, 8, 5, 1, 0.0F);
        this.Bottom_2 = new ModelRenderer(this, 29, 0);
        this.Bottom_2.setRotationPoint(-4.6F, 0.0F, 1.9F);
        this.Bottom_2.addBox(0.0F, 0.0F, 0.0F, 5, 16, 1, 0.0F);
        this.setRotateAngle(Bottom_2, 0.0F, 0.3673918075448064F, 0.0F);
        this.Bottom.addChild(this.Bottom_3);
        this.plate.addChild(this.Bottom);
        this.plate.addChild(this.Window);
        this.plate.addChild(this.plate_2);
        this.plate.addChild(this.plate_1);
        this.plate.addChild(this.Bottom_1);
        this.Bottom.addChild(this.Bottom_2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        GlStateManager.pushMatrix();
        GlStateManager.scale(1D / modelScale[0], 1D / modelScale[1], 1D / modelScale[2]);
        this.plate.render(f5);
        this.handle.render(f5);
        GlStateManager.popMatrix();
    }
    
    @Override
    public void render() { 
        this.handle.render(0.0625F);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
