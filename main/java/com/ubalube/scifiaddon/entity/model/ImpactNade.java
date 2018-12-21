package com.ubalube.scifiaddon.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

/**
 * M67Frag - VampireRedEye
 * Created using Tabula 7.0.0
 */
public class ImpactNade extends ModelBase {
    public ModelRenderer fragbase;
    public ModelRenderer fragside1;
    public ModelRenderer fragside2;
    public ModelRenderer fragside3;
    public ModelRenderer fragside4;
    public ModelRenderer fragside5;
    public ModelRenderer fragside6;

    public ImpactNade() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.fragside3 = new ModelRenderer(this, 8, 0);
        this.fragside3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.fragside3.addBox(-3.5F, -3.5F, -8.5F, 7, 7, 1, 0.0F);
        this.setRotateAngle(fragside3, 1.5707963267948966F, 0.0F, 0.0F);
        this.fragside2 = new ModelRenderer(this, 0, 18);
        this.fragside2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.fragside2.addBox(-3.5F, 0.5F, 3.5F, 7, 7, 1, 0.0F);
        this.fragside4 = new ModelRenderer(this, 0, 18);
        this.fragside4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.fragside4.addBox(-3.5F, 0.5F, -4.5F, 7, 7, 1, 0.0F);
        this.fragbase = new ModelRenderer(this, 0, 0);
        this.fragbase.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.fragbase.addBox(-4.0F, 0.0F, -4.0F, 8, 8, 8, 0.0F);
        this.fragside1 = new ModelRenderer(this, 8, 1);
        this.fragside1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.fragside1.addBox(-3.5F, -3.5F, -0.5F, 7, 7, 1, 0.0F);
        this.setRotateAngle(fragside1, 1.5707963267948966F, 0.0F, 0.0F);
        this.fragside5 = new ModelRenderer(this, 0, 18);
        this.fragside5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.fragside5.addBox(-3.5F, 0.5F, 3.5F, 7, 7, 1, 0.0F);
        this.setRotateAngle(fragside5, 0.0F, -1.5707963267948966F, 0.0F);
        this.fragside6 = new ModelRenderer(this, 0, 18);
        this.fragside6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.fragside6.addBox(-3.5F, 0.5F, 3.5F, 7, 7, 1, 0.0F);
        this.setRotateAngle(fragside6, 0.0F, 1.5707963267948966F, 0.0F);
        this.fragbase.addChild(this.fragside3);
        this.fragbase.addChild(this.fragside2);
        this.fragbase.addChild(this.fragside4);
        this.fragbase.addChild(this.fragside1);
        this.fragbase.addChild(this.fragside5);
        this.fragbase.addChild(this.fragside6);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.fragbase.offsetX, this.fragbase.offsetY, this.fragbase.offsetZ);
        GlStateManager.translate(this.fragbase.rotationPointX * f5, this.fragbase.rotationPointY * f5, this.fragbase.rotationPointZ * f5);
        GlStateManager.scale(0.5D, 0.5D, 0.5D);
        GlStateManager.translate(-this.fragbase.offsetX, -this.fragbase.offsetY, -this.fragbase.offsetZ);
        GlStateManager.translate(-this.fragbase.rotationPointX * f5, -this.fragbase.rotationPointY * f5, -this.fragbase.rotationPointZ * f5);
        this.fragbase.render(f5);
        GlStateManager.popMatrix();
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
