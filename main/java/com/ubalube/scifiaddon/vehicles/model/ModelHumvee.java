package com.ubalube.scifiaddon.vehicles.model;

import com.ubalube.scifiaddon.entity.model.shields.ModelShield;
import com.ubalube.scifiaddon.vehicles.VehicleHumvee;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.AbstractHorse;

/**
 * ModelHumvee - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelHumvee extends ModelBase {
    public ModelRenderer truck1;
    public ModelRenderer truck1Child;
    public ModelRenderer truck1Child_1;
    public ModelRenderer truck1Child_2;
    public ModelRenderer truck1Child_3;
    public ModelRenderer wheel;
    public ModelRenderer truck1Child_4;
    public ModelRenderer GunnerTurnable;
    public ModelRenderer truck1Child_5;
    public ModelRenderer truck1Child_6;
    public ModelRenderer truck1Child_7;
    public ModelRenderer wheel_1;
    public ModelRenderer truck1Child_8;
    public ModelRenderer truck1Child_9;
    public ModelRenderer truck1Child_10;
    public ModelRenderer truck1Child_11;
    public ModelRenderer wheel_2;
    public ModelRenderer truck1Child_12;
    public ModelRenderer truck1Child_13;
    public ModelRenderer truck1Child_14;
    public ModelRenderer truck1Child_15;
    public ModelRenderer truck1Child_16;
    public ModelRenderer truck1Child_17;
    public ModelRenderer truck1Child_18;
    public ModelRenderer wheel_3;
    public ModelRenderer truck1Child_19;
    public ModelRenderer truck1Child_20;
    public ModelRenderer truck1Child_21;
    public ModelRenderer truck1Child_22;
    public ModelRenderer truck1Child_23;
    public ModelRenderer truck1Child_24;
    public ModelRenderer truck1ChildChild;
    public ModelRenderer Turret;
    public ModelRenderer truck1ChildChild_1;
    public ModelRenderer truck1ChildChild_2;
    public ModelRenderer truck1ChildChild_3;
    public ModelRenderer truck1ChildChild_4;
    public ModelRenderer truck1ChildChild_5;
    public ModelRenderer truck1ChildChild_6;
    public ModelRenderer truck1ChildChild_7;
    public ModelRenderer truck1ChildChild_8;
    public ModelRenderer truck1ChildChild_9;
    public ModelRenderer truck1ChildChild_10;
    public ModelRenderer truck1ChildChild_11;

    public ModelHumvee() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.truck1Child_11 = new ModelRenderer(this, 80, 80);
        this.truck1Child_11.setRotationPoint(14.0F, 5.0F, -4.0F);
        this.truck1Child_11.addBox(-1.0F, -1.0F, 0.0F, 2, 12, 26, 0.0F);
        this.truck1Child_1 = new ModelRenderer(this, 36, 40);
        this.truck1Child_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child_1.addBox(2.0F, 10.0F, 2.0F, 8, 4, 8, 0.0F);
        this.truck1ChildChild_8 = new ModelRenderer(this, 60, 30);
        this.truck1ChildChild_8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1ChildChild_8.addBox(2.0F, 2.0999999046325684F, 8.0F, 8, 8, 2, 0.0F);
        this.GunnerTurnable = new ModelRenderer(this, 0, 176);
        this.GunnerTurnable.setRotationPoint(0.0F, -8.0F, 11.0F);
        this.GunnerTurnable.addBox(-9.0F, -7.4F, -7.0F, 16, 8, 16, 0.0F);
        this.setRotateAngle(GunnerTurnable, 0.0F, 0.7853981633974483F, 0.0F);
        this.truck1Child_24 = new ModelRenderer(this, 0, 0);
        this.truck1Child_24.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child_24.addBox(-15.0F, 2.0999999046325684F, -23.0F, 30, 10, 19, 0.0F);
        this.Turret = new ModelRenderer(this, 12, 154);
        this.Turret.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Turret.addBox(-2.0F, -3.0F, -2.0F, 4, 4, 4, 0.0F);
        this.setRotateAngle(Turret, 0.0F, -0.7853981633974483F, 0.0F);
        this.truck1ChildChild_7 = new ModelRenderer(this, 64, 156);
        this.truck1ChildChild_7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1ChildChild_7.addBox(-0.5F, -2.700000047683716F, -14.0F, 1, 1, 8, 0.0F);
        this.truck1Child_12 = new ModelRenderer(this, 106, 122);
        this.truck1Child_12.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child_12.addBox(-15.0F, -15.0F, 19.0F, 30, 12, 23, 0.0F);
        this.setRotateAngle(truck1Child_12, -0.34906584024429316F, 0.0F, 0.0F);
        this.truck1Child_16 = new ModelRenderer(this, 90, 0);
        this.truck1Child_16.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child_16.addBox(-14.800000190734863F, -7.0F, -3.0F, 2, 14, 2, 0.0F);
        this.setRotateAngle(truck1Child_16, -0.4886921942234039F, 0.0F, 0.0F);
        this.truck1ChildChild_3 = new ModelRenderer(this, 32, 154);
        this.truck1ChildChild_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1ChildChild_3.addBox(-1.5F, -3.0F, -6.0F, 3, 3, 4, 0.0F);
        this.truck1ChildChild_6 = new ModelRenderer(this, 32, 168);
        this.truck1ChildChild_6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1ChildChild_6.addBox(1.5F, -1.5F, -5.5F, 2, 2, 3, 0.0F);
        this.truck1Child_20 = new ModelRenderer(this, 90, 36);
        this.truck1Child_20.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child_20.addBox(-14.0F, -7.0F, -2.0F, 28, 14, 0, 0.0F);
        this.setRotateAngle(truck1Child_20, -0.4886921942234039F, 0.0F, 0.0F);
        this.truck1Child = new ModelRenderer(this, 90, 0);
        this.truck1Child.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child.addBox(12.800000190734863F, -7.0F, -3.0F, 2, 14, 2, 0.0F);
        this.setRotateAngle(truck1Child, -0.4886921942234039F, 0.0F, 0.0F);
        this.truck1ChildChild_5 = new ModelRenderer(this, 64, 156);
        this.truck1ChildChild_5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1ChildChild_5.addBox(-0.5F, -3.700000047683716F, -13.0F, 1, 1, 1, 0.0F);
        this.truck1Child_7 = new ModelRenderer(this, 114, 64);
        this.truck1Child_7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child_7.addBox(-4.0F, -7.0F, -14.0F, 24, 12, 0, 0.0F);
        this.setRotateAngle(truck1Child_7, 0.0F, -1.5707963705062866F, 0.0F);
        this.truck1Child_17 = new ModelRenderer(this, 0, 8);
        this.truck1Child_17.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child_17.addBox(4.0F, 4.0F, -6.5F, 3, 3, 3, 0.0F);
        this.setRotateAngle(truck1Child_17, 0.34906584024429316F, 0.0F, 0.0F);
        this.truck1Child_8 = new ModelRenderer(this, 36, 40);
        this.truck1Child_8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child_8.addBox(-10.0F, 10.0F, 13.800000190734863F, 8, 4, 5, 0.0F);
        this.truck1Child_15 = new ModelRenderer(this, 114, 64);
        this.truck1Child_15.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child_15.addBox(-4.0F, -7.0F, 14.0F, 24, 12, 0, 0.0F);
        this.setRotateAngle(truck1Child_15, 0.0F, -1.5707963705062866F, 0.0F);
        this.wheel_3 = new ModelRenderer(this, 0, 30);
        this.wheel_3.setRotationPoint(12.0F, 17.5F, -15.0F);
        this.wheel_3.addBox(-0.1F, -6.0F, -6.0F, 3, 12, 12, 0.0F);
        this.truck1ChildChild_11 = new ModelRenderer(this, 60, 30);
        this.truck1ChildChild_11.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1ChildChild_11.addBox(2.0F, 2.0999999046325684F, 18.799999237060547F, 8, 8, 2, 0.0F);
        this.truck1Child_13 = new ModelRenderer(this, 102, 0);
        this.truck1Child_13.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child_13.addBox(-14.800000190734863F, -7.0F, 20.0F, 2, 14, 2, 0.0F);
        this.truck1Child_3 = new ModelRenderer(this, 102, 0);
        this.truck1Child_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child_3.addBox(12.800000190734863F, -7.0F, 20.0F, 2, 14, 2, 0.0F);
        this.truck1ChildChild_1 = new ModelRenderer(this, 50, 168);
        this.truck1ChildChild_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1ChildChild_1.addBox(2.700000047683716F, -1.5F, -5.5F, 0, 2, 3, 0.0F);
        this.setRotateAngle(truck1ChildChild_1, 0.0F, 0.0F, -0.6829473376274109F);
        this.truck1ChildChild_2 = new ModelRenderer(this, 48, 156);
        this.truck1ChildChild_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1ChildChild_2.addBox(-0.5F, -1.5F, -12.0F, 1, 1, 6, 0.0F);
        this.truck1ChildChild_9 = new ModelRenderer(this, 60, 30);
        this.truck1ChildChild_9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1ChildChild_9.addBox(-10.0F, 2.0999999046325684F, 8.0F, 8, 8, 2, 0.0F);
        this.wheel = new ModelRenderer(this, 0, 30);
        this.wheel.setRotationPoint(12.0F, 17.5F, 30.5F);
        this.wheel.addBox(-0.1F, -6.0F, -6.0F, 3, 12, 12, 0.0F);
        this.truck1ChildChild_4 = new ModelRenderer(this, 88, 154);
        this.truck1ChildChild_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1ChildChild_4.addBox(-1.5F, -3.0F, 2.0F, 3, 3, 4, 0.0F);
        this.wheel_1 = new ModelRenderer(this, 0, 30);
        this.wheel_1.setRotationPoint(-12.0F, 17.5F, 30.5F);
        this.wheel_1.addBox(-2.9F, -6.0F, -6.0F, 3, 12, 12, 0.0F);
        this.truck1Child_21 = new ModelRenderer(this, 0, 72);
        this.truck1Child_21.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child_21.addBox(-15.0F, 12.0F, -10.0F, 30, 4, 6, 0.0F);
        this.truck1Child_14 = new ModelRenderer(this, 0, 84);
        this.truck1Child_14.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child_14.addBox(-12.0F, 12.0F, 26.0F, 24, 4, 9, 0.0F);
        this.truck1ChildChild = new ModelRenderer(this, 60, 30);
        this.truck1ChildChild.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1ChildChild.addBox(-10.0F, 2.0999999046325684F, 18.799999237060547F, 8, 8, 2, 0.0F);
        this.truck1Child_23 = new ModelRenderer(this, 106, 0);
        this.truck1Child_23.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child_23.addBox(-14.5F, 4.0F, 22.0F, 29, 8, 21, 0.0F);
        this.truck1ChildChild_10 = new ModelRenderer(this, 0, 0);
        this.truck1ChildChild_10.setRotationPoint(5.5F, 5.400000095367432F, -3.5F);
        this.truck1ChildChild_10.addBox(-2.5F, -2.5F, 0.0F, 5, 5, 1, 0.0F);
        this.truck1Child_5 = new ModelRenderer(this, 180, 34);
        this.truck1Child_5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child_5.addBox(4.5F, -15.0F, 1.0F, 3, 30, 23, 0.0F);
        this.setRotateAngle(truck1Child_5, 0.0F, 0.0F, -1.5707963705062866F);
        this.truck1Child_2 = new ModelRenderer(this, 96, 162);
        this.truck1Child_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child_2.addBox(-15.0F, 10.0F, 24.100000381469727F, 30, 2, 10, 0.0F);
        this.wheel_2 = new ModelRenderer(this, 0, 30);
        this.wheel_2.setRotationPoint(-12.0F, 17.5F, -15.0F);
        this.wheel_2.addBox(-2.9F, -6.0F, -6.0F, 3, 12, 12, 0.0F);
        this.truck1Child_18 = new ModelRenderer(this, 0, 84);
        this.truck1Child_18.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child_18.addBox(-12.0F, 12.0F, -21.0F, 24, 4, 9, 0.0F);
        this.truck1 = new ModelRenderer(this, 0, 130);
        this.truck1.setRotationPoint(0.0F, 0.0F, -3.799999952316284F);
        this.truck1.addBox(-15.0F, 16.0F, -23.0F, 30, 4, 4, 0.0F);
        this.truck1Child_4 = new ModelRenderer(this, 0, 140);
        this.truck1Child_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child_4.addBox(-15.0F, 12.0F, 22.0F, 30, 4, 4, 0.0F);
        this.truck1Child_6 = new ModelRenderer(this, 140, 80);
        this.truck1Child_6.setRotationPoint(-14.0F, 5.0F, -4.0F);
        this.truck1Child_6.addBox(-1.0F, -1.0F, 0.0F, 2, 12, 26, 0.0F);
        this.truck1Child_19 = new ModelRenderer(this, 0, 100);
        this.truck1Child_19.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child_19.addBox(-13.0F, 14.0F, -4.0F, 26, 2, 26, 0.0F);
        this.truck1Child_9 = new ModelRenderer(this, 36, 40);
        this.truck1Child_9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child_9.addBox(2.0F, 10.0F, 13.800000190734863F, 8, 4, 5, 0.0F);
        this.truck1Child_22 = new ModelRenderer(this, 36, 40);
        this.truck1Child_22.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child_22.addBox(-10.0F, 10.0F, 2.0F, 8, 4, 8, 0.0F);
        this.truck1Child_10 = new ModelRenderer(this, 0, 56);
        this.truck1Child_10.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.truck1Child_10.addBox(-15.0F, 12.0F, 35.0F, 30, 4, 8, 0.0F);
        this.truck1.addChild(this.truck1Child_11);
        this.truck1.addChild(this.truck1Child_1);
        this.truck1Child_8.addChild(this.truck1ChildChild_8);
        this.truck1.addChild(this.GunnerTurnable);
        this.truck1.addChild(this.truck1Child_24);
        this.GunnerTurnable.addChild(this.Turret);
        this.Turret.addChild(this.truck1ChildChild_7);
        this.truck1.addChild(this.truck1Child_12);
        this.truck1.addChild(this.truck1Child_16);
        this.Turret.addChild(this.truck1ChildChild_3);
        this.Turret.addChild(this.truck1ChildChild_6);
        this.truck1.addChild(this.truck1Child_20);
        this.truck1.addChild(this.truck1Child);
        this.Turret.addChild(this.truck1ChildChild_5);
        this.truck1.addChild(this.truck1Child_7);
        this.truck1.addChild(this.truck1Child_17);
        this.truck1.addChild(this.truck1Child_8);
        this.truck1.addChild(this.truck1Child_15);
        this.truck1.addChild(this.wheel_3);
        this.truck1Child_22.addChild(this.truck1ChildChild_11);
        this.truck1.addChild(this.truck1Child_13);
        this.truck1.addChild(this.truck1Child_3);
        this.Turret.addChild(this.truck1ChildChild_1);
        this.Turret.addChild(this.truck1ChildChild_2);
        this.truck1Child_9.addChild(this.truck1ChildChild_9);
        this.truck1.addChild(this.wheel);
        this.Turret.addChild(this.truck1ChildChild_4);
        this.truck1.addChild(this.wheel_1);
        this.truck1.addChild(this.truck1Child_21);
        this.truck1.addChild(this.truck1Child_14);
        this.truck1Child_1.addChild(this.truck1ChildChild);
        this.truck1.addChild(this.truck1Child_23);
        this.truck1Child_17.addChild(this.truck1ChildChild_10);
        this.truck1.addChild(this.truck1Child_5);
        this.truck1.addChild(this.truck1Child_2);
        this.truck1.addChild(this.wheel_2);
        this.truck1.addChild(this.truck1Child_18);
        this.truck1.addChild(this.truck1Child_4);
        this.truck1.addChild(this.truck1Child_6);
        this.truck1.addChild(this.truck1Child_19);
        this.truck1.addChild(this.truck1Child_9);
        this.truck1.addChild(this.truck1Child_22);
        this.truck1.addChild(this.truck1Child_10);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.truck1.offsetX, this.truck1.offsetY, this.truck1.offsetZ);
        GlStateManager.translate(this.truck1.rotationPointX * f5, this.truck1.rotationPointY * f5, this.truck1.rotationPointZ * f5);
        GlStateManager.scale(1.2D, 1.2D, 1.2D);
        GlStateManager.translate(-this.truck1.offsetX, -this.truck1.offsetY, -this.truck1.offsetZ);
        GlStateManager.translate(-this.truck1.rotationPointX * f5, -this.truck1.rotationPointY * f5 * 2, -this.truck1.rotationPointZ * f5);
        this.truck1.render(f5);
        GlStateManager.popMatrix();
    }
    
    @Override
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount,
    		float partialTickTime) {
    	VehicleHumvee humv = (VehicleHumvee)entitylivingbaseIn;
        float f7 = 1.0F;
        float f9 = (float)entitylivingbaseIn.ticksExisted + partialTickTime + 0.4F;
        boolean flag2 = humv.isMoving();
        if(flag2)
        {
        	if(humv.isBeingRidden())
        	{
        		this.wheel.rotateAngleX = f9;
        		this.wheel_1.rotateAngleX = f9;
        		this.wheel_2.rotateAngleX = f9;
        		this.wheel_3.rotateAngleX = f9;
        	}
        }
        
        if(humv.isBeingRidden() && humv.getPassengers().size() == 2)
        {
        	this.GunnerTurnable.rotateAngleY = humv.getGunnerRotationYaw();
        }
        
    	super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
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
