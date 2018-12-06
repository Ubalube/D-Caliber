package com.ubalube.scifiaddon.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

/**
 * Tank - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelTank extends ModelBase {
    public ModelRenderer Base;
    public ModelRenderer Body;
    public ModelRenderer Top;
    public ModelRenderer WheelsLeft;
    public ModelRenderer WheelsRight;
    public ModelRenderer front_Slope;
    public ModelRenderer WheelsTopGuard;
    public ModelRenderer WheelsTopGuard_1;
    public ModelRenderer Top_Holder;
    public ModelRenderer Barrel;
    public ModelRenderer SideExtend;
    public ModelRenderer SideExtend_1;
    public ModelRenderer Hatch;
    public ModelRenderer Barrel_Side;
    public ModelRenderer Barrel_Side_1;

    public ModelTank() {
        this.textureWidth = 100;
        this.textureHeight = 100;
        this.WheelsLeft = new ModelRenderer(this, 3, 69);
        this.WheelsLeft.setRotationPoint(8.4F, 1.6F, -1.5F);
        this.WheelsLeft.addBox(0.0F, 0.0F, 0.0F, 2, 5, 18, 0.0F);
        this.SideExtend = new ModelRenderer(this, 54, 60);
        this.SideExtend.setRotationPoint(3.2F, 0.4F, -3.8F);
        this.SideExtend.addBox(0.0F, 0.0F, 0.0F, 2, 2, 11, 0.0F);
        this.front_Slope = new ModelRenderer(this, 0, 21);
        this.front_Slope.setRotationPoint(0.0F, -0.2F, 0.1F);
        this.front_Slope.addBox(0.0F, 0.0F, 0.0F, 9, 4, 4, 0.0F);
        this.setRotateAngle(front_Slope, -0.8651597102135892F, 0.0F, 0.0F);
        this.WheelsTopGuard = new ModelRenderer(this, 2, 47);
        this.WheelsTopGuard.setRotationPoint(0.4F, -1.5F, -0.5F);
        this.WheelsTopGuard.addBox(0.0F, 0.0F, 0.0F, 3, 2, 19, 0.0F);
        this.setRotateAngle(WheelsTopGuard, 0.0F, 0.0F, 0.40980330836826856F);
        this.Barrel_Side_1 = new ModelRenderer(this, 27, 70);
        this.Barrel_Side_1.setRotationPoint(1.4F, 0.5F, 0.3F);
        this.Barrel_Side_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 12, 0.0F);
        this.Hatch = new ModelRenderer(this, 54, 32);
        this.Hatch.setRotationPoint(-0.9F, -0.5F, 0.0F);
        this.Hatch.addBox(0.0F, 0.0F, 0.0F, 5, 2, 5, 0.0F);
        this.WheelsTopGuard_1 = new ModelRenderer(this, 2, 47);
        this.WheelsTopGuard_1.setRotationPoint(-1.4F, -0.3F, -0.5F);
        this.WheelsTopGuard_1.addBox(0.0F, 0.0F, 0.0F, 3, 2, 19, 0.0F);
        this.setRotateAngle(WheelsTopGuard_1, 0.0F, 0.0F, -0.40980330836826856F);
        this.WheelsRight = new ModelRenderer(this, 3, 69);
        this.WheelsRight.setRotationPoint(-1.2F, 1.6F, -1.5F);
        this.WheelsRight.addBox(0.0F, 0.0F, 0.0F, 2, 5, 18, 0.0F);
        this.Base = new ModelRenderer(this, 0, 0);
        this.Base.setRotationPoint(-22.0F, -7.0F, 0.0F);
        this.Base.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.SideExtend_1 = new ModelRenderer(this, 54, 60);
        this.SideExtend_1.setRotationPoint(-5.4F, 0.4F, -3.8F);
        this.SideExtend_1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 11, 0.0F);
        this.Barrel_Side = new ModelRenderer(this, 27, 70);
        this.Barrel_Side.setRotationPoint(-0.4F, 0.5F, 0.3F);
        this.Barrel_Side.addBox(0.0F, 0.0F, 0.0F, 1, 1, 12, 0.0F);
        this.Body = new ModelRenderer(this, 0, 0);
        this.Body.setRotationPoint(0.0F, -0.3F, -7.0F);
        this.Body.addBox(0.0F, 0.0F, 0.0F, 9, 5, 15, 0.0F);
        this.Top = new ModelRenderer(this, 54, 6);
        this.Top.setRotationPoint(4.5F, -3.7F, 0.0F);
        this.Top.addBox(-5.1F, 0.0F, -4.3F, 10, 3, 12, 0.0F);
        this.Top_Holder = new ModelRenderer(this, 33, 46);
        this.Top_Holder.setRotationPoint(-2.4F, 0.9F, -1.4F);
        this.Top_Holder.addBox(0.0F, 0.0F, 0.0F, 5, 3, 5, 0.0F);
        this.Barrel = new ModelRenderer(this, 6, 31);
        this.Barrel.setRotationPoint(1.8F, 0.6F, -16.0F);
        this.Barrel.addBox(0.0F, 0.0F, 0.0F, 2, 2, 12, 0.0F);
        this.Body.addChild(this.WheelsLeft);
        this.Top.addChild(this.SideExtend);
        this.Body.addChild(this.front_Slope);
        this.WheelsLeft.addChild(this.WheelsTopGuard);
        this.Barrel.addChild(this.Barrel_Side_1);
        this.Top.addChild(this.Hatch);
        this.WheelsRight.addChild(this.WheelsTopGuard_1);
        this.Body.addChild(this.WheelsRight);
        this.Top.addChild(this.SideExtend_1);
        this.Barrel.addChild(this.Barrel_Side);
        this.Base.addChild(this.Body);
        this.Base.addChild(this.Top);
        this.Top.addChild(this.Top_Holder);
        this.Top.addChild(this.Barrel);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Base.offsetX, this.Base.offsetY, this.Base.offsetZ);
        GlStateManager.translate(this.Base.rotationPointX * f5, this.Base.rotationPointY * f5, this.Base.rotationPointZ * f5);
        GlStateManager.scale(5.0D, 5.0D, 5.0D);
        GlStateManager.translate(-this.Base.offsetX, -this.Base.offsetY, -this.Base.offsetZ);
        GlStateManager.translate(-this.Base.rotationPointX * f5, -this.Base.rotationPointY * f5, -this.Base.rotationPointZ * f5);
        this.Base.render(f5);
        GlStateManager.popMatrix();
    }
    
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
    		float headPitch, float scaleFactor, Entity entityIn) {
    	
    	this.Top.rotateAngleY = netHeadYaw * 0.017453292F;
    	
    	super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
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
