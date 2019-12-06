package com.ubalube.scifiaddon.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * ModelPlayer - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
@SideOnly(Side.CLIENT)
public class ModelNVG extends ModelBiped {
    public ModelRenderer Goggles;
    public ModelRenderer LensShower;
    public ModelRenderer Goggle;
    public ModelRenderer GoggleHolder;
    public ModelRenderer Goggle_1;
    public ModelRenderer Goggle_2;
    public ModelRenderer Goggle_3;
    public ModelRenderer LensShower_1;
    public ModelRenderer LensShower_2;
    public ModelRenderer LensShower_3;
    public ModelRenderer Goggle_4;
    public ModelRenderer Goggle_5;
    public ModelRenderer Goggle_6;
    public ModelRenderer Goggle_7;

    public ModelNVG(float scale) {
    	super(scale, 0, 64, 64);
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.LensShower_2 = new ModelRenderer(this, 76, 90);
        this.LensShower_2.setRotationPoint(-3.7F, -4.9F, -4.7F);
        this.LensShower_2.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.setRotateAngle(LensShower_2, 0.0F, 0.18203784098300857F, 0.0F);
        this.Goggle_5 = new ModelRenderer(this, 100, 120);
        this.Goggle_5.setRotationPoint(1.0F, 1.7F, -0.4F);
        this.Goggle_5.addBox(-1.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(Goggle_5, 0.0F, 0.0F, 0.7740535232594852F);
        this.Goggle_2 = new ModelRenderer(this, 100, 100);
        this.Goggle_2.setRotationPoint(-2.7F, -5.0F, -8.2F);
        this.Goggle_2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(Goggle_2, 0.0F, 0.17453292519943295F, 0.0F);
        this.Goggles = new ModelRenderer(this, 0, 0);
        this.Goggles.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Goggles.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.Goggle_3 = new ModelRenderer(this, 100, 100);
        this.Goggle_3.setRotationPoint(-5.3F, -5.0F, -7.4F);
        this.Goggle_3.addBox(0.0F, 0.0F, 0.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(Goggle_3, 0.0F, 0.3839724354387525F, 0.0F);
        this.Goggle_4 = new ModelRenderer(this, 100, 120);
        this.Goggle_4.setRotationPoint(1.0F, 1.7F, -0.4F);
        this.Goggle_4.addBox(-1.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(Goggle_4, 0.0F, 0.0F, 0.7740535232594852F);
        this.Goggle_6 = new ModelRenderer(this, 100, 120);
        this.Goggle_6.setRotationPoint(1.0F, 1.7F, -0.4F);
        this.Goggle_6.addBox(-1.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(Goggle_6, 0.0F, 0.0F, 0.7740535232594852F);
        this.GoggleHolder = new ModelRenderer(this, 90, 90);
        this.GoggleHolder.setRotationPoint(0.0F, -5.5F, -6.0F);
        this.GoggleHolder.addBox(-1.0F, -3.0F, 0.0F, 2, 3, 1, 0.0F);
        this.setRotateAngle(GoggleHolder, -0.6829473363053812F, 0.0F, 0.0F);
        this.Goggle_7 = new ModelRenderer(this, 100, 120);
        this.Goggle_7.setRotationPoint(1.0F, 1.7F, -0.4F);
        this.Goggle_7.addBox(-1.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(Goggle_7, 0.0F, 0.0F, 0.7740535232594852F);
        this.LensShower_1 = new ModelRenderer(this, 100, 90);
        this.LensShower_1.setRotationPoint(0.0F, -5.5F, -6.0F);
        this.LensShower_1.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1, 0.0F);
        this.setRotateAngle(LensShower_1, 0.0F, -0.18203784098300857F, 0.0F);
        this.LensShower_3 = new ModelRenderer(this, 76, 90);
        this.LensShower_3.setRotationPoint(0.6F, -4.9F, -5.4F);
        this.LensShower_3.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.setRotateAngle(LensShower_3, 0.0F, -0.18203784098300857F, 0.0F);
        this.Goggle_1 = new ModelRenderer(this, 100, 100);
        this.Goggle_1.setRotationPoint(0.3F, -5.0F, -8.6F);
        this.Goggle_1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(Goggle_1, 0.0F, -0.17453292519943295F, 0.0F);
        this.Goggle = new ModelRenderer(this, 100, 100);
        this.Goggle.setRotationPoint(3.0F, -5.0F, -8.0F);
        this.Goggle.addBox(0.0F, 0.0F, 0.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(Goggle, 0.0F, -0.3839724354387525F, 0.0F);
        this.LensShower = new ModelRenderer(this, 100, 90);
        this.LensShower.setRotationPoint(-4.0F, -5.5F, -5.4F);
        this.LensShower.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1, 0.0F);
        this.setRotateAngle(LensShower, 0.0F, 0.18203784098300857F, 0.0F);
        this.Goggles.addChild(this.LensShower_2);
        this.Goggle_1.addChild(this.Goggle_5);
        this.Goggles.addChild(this.Goggle_2);
        this.Goggles.addChild(this.Goggle_3);
        this.Goggle.addChild(this.Goggle_4);
        this.Goggle_2.addChild(this.Goggle_6);
        this.Goggles.addChild(this.GoggleHolder);
        this.Goggle_3.addChild(this.Goggle_7);
        this.Goggles.addChild(this.LensShower_1);
        this.Goggles.addChild(this.LensShower_3);
        this.Goggles.addChild(this.Goggle_1);
        this.Goggles.addChild(this.Goggle);
        this.Goggles.addChild(this.LensShower);
        
        bipedHead.addChild(this.Goggles);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
    	super.render(entity, f, f1, f2, f3, f4, f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    
    @Override
   	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
   		// this prevents helmets from always facing south, and the armor "breathing" on the stand
   		if (entityIn instanceof EntityArmorStand) {
   			EntityArmorStand entityarmorstand = (EntityArmorStand) entityIn;
   			this.bipedHead.rotateAngleX = 0.017453292F * entityarmorstand.getHeadRotation().getX();
   			this.bipedHead.rotateAngleY = 0.017453292F * entityarmorstand.getHeadRotation().getY();
   			this.bipedHead.rotateAngleZ = 0.017453292F * entityarmorstand.getHeadRotation().getZ();
   			this.bipedHead.setRotationPoint(0.0F, 1.0F, 0.0F);
   			copyModelAngles(this.bipedHead, this.bipedHeadwear);
   		} else super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
   	}
    
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
