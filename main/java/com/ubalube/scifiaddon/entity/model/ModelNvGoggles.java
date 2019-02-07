package com.ubalube.scifiaddon.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * NVGoggles - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
@SideOnly(Side.CLIENT)
public class ModelNvGoggles extends ModelBiped {
    public ModelRenderer NVStrap;
    public ModelRenderer NVHolder;
    public ModelRenderer NVLeft;
    public ModelRenderer NVRight;
    public ModelRenderer shape27;
    public ModelRenderer Part2;
    public ModelRenderer shape24;
    public ModelRenderer shape28;
    public ModelRenderer Goggle;
    public ModelRenderer shape28_1;
    public ModelRenderer shape28_2;
    public ModelRenderer Part2_1;
    public ModelRenderer shape24_1;
    public ModelRenderer shape28_3;
    public ModelRenderer Goggle_1;
    public ModelRenderer shape28_4;
    public ModelRenderer shape28_5;

    public ModelNvGoggles(float scale) {
    	super(scale, 0, 64, 64);
        this.textureWidth = 200;
        this.textureHeight = 200;
        this.NVStrap = new ModelRenderer(this, 150, 150);
        this.NVStrap.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.NVStrap.addBox(-4.5F, 0.0F, -4.5F, 9, 1, 9, 0.0F);
        this.shape24 = new ModelRenderer(this, 140, 180);
        this.shape24.setRotationPoint(-0.5F, 0.5F, 0.0F);
        this.shape24.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(shape24, 0.0F, 0.0F, -0.04258603374866164F);
        this.shape28_2 = new ModelRenderer(this, 20, 188);
        this.shape28_2.setRotationPoint(1.7F, 1.1F, -0.5F);
        this.shape28_2.addBox(-1.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(shape28_2, 0.0F, 0.0F, -0.85259333959923F);
        this.shape28 = new ModelRenderer(this, 20, 179);
        this.shape28.setRotationPoint(1.4F, -0.1F, -1.7F);
        this.shape28.addBox(-1.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(shape28, 0.0F, 0.0F, -0.85259333959923F);
        this.Part2 = new ModelRenderer(this, 0, 171);
        this.Part2.setRotationPoint(-2.9F, -0.5F, -0.8F);
        this.Part2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(Part2, 0.0F, 0.0F, -0.2637192499763432F);
        this.shape28_1 = new ModelRenderer(this, 20, 179);
        this.shape28_1.setRotationPoint(1.7F, 0.9F, -0.1F);
        this.shape28_1.addBox(-1.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(shape28_1, 0.0F, 0.0F, -0.85259333959923F);
        this.shape28_4 = new ModelRenderer(this, 20, 179);
        this.shape28_4.setRotationPoint(1.7F, 0.9F, -0.1F);
        this.shape28_4.addBox(-1.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(shape28_4, 0.0F, 0.0F, -0.85259333959923F);
        this.shape28_3 = new ModelRenderer(this, 20, 179);
        this.shape28_3.setRotationPoint(0.8F, -0.1F, -1.7F);
        this.shape28_3.addBox(-1.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(shape28_3, 0.0F, 0.0F, -0.85259333959923F);
        this.Goggle = new ModelRenderer(this, 8, 190);
        this.Goggle.setRotationPoint(0.1F, 1.7F, 0.3F);
        this.Goggle.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
        this.shape24_1 = new ModelRenderer(this, 140, 180);
        this.shape24_1.setRotationPoint(-0.4F, 0.35F, 0.0F);
        this.shape24_1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(shape24_1, 0.0F, 0.0F, 0.04258603374866164F);
        this.shape28_5 = new ModelRenderer(this, 20, 188);
        this.shape28_5.setRotationPoint(1.7F, 1.1F, -0.5F);
        this.shape28_5.addBox(-1.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(shape28_5, 0.0F, 0.0F, -0.85259333959923F);
        this.Part2_1 = new ModelRenderer(this, 0, 171);
        this.Part2_1.setRotationPoint(2.0F, -1.0F, -0.8F);
        this.Part2_1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(Part2_1, 0.0F, 0.0F, 0.2637192499763432F);
        this.shape27 = new ModelRenderer(this, 30, 160);
        this.shape27.setRotationPoint(0.0F, 1.8F, -5.0F);
        this.shape27.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 1, 0.0F);
        this.NVHolder = new ModelRenderer(this, 0, 150);
        this.NVHolder.setRotationPoint(0.0F, 0.9F, -3.9F);
        this.NVHolder.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(NVHolder, -0.593411945678072F, 0.0F, 0.0F);
        this.Goggle_1 = new ModelRenderer(this, 8, 190);
        this.Goggle_1.setRotationPoint(-0.1F, 1.6F, 0.3F);
        this.Goggle_1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
        this.NVLeft = new ModelRenderer(this, 0, 161);
        this.NVLeft.setRotationPoint(-2.3F, 2.3F, -4.2F);
        this.NVLeft.addBox(-1.0F, -1.0F, -1.0F, 3, 2, 2, 0.0F);
        this.setRotateAngle(NVLeft, 0.0F, 0.0F, -0.19338248112097173F);
        this.NVRight = new ModelRenderer(this, 0, 161);
        this.NVRight.setRotationPoint(1.5F, 2.125F, -4.2F);
        this.NVRight.addBox(-1.0F, -1.0F, -1.0F, 3, 2, 2, 0.0F);
        this.setRotateAngle(NVRight, 0.0F, 0.0F, 0.19338248112097173F);
        this.NVLeft.addChild(this.shape24);
        this.Goggle.addChild(this.shape28_2);
        this.NVLeft.addChild(this.shape28);
        this.NVLeft.addChild(this.Part2);
        this.Part2.addChild(this.shape28_1);
        this.Part2_1.addChild(this.shape28_4);
        this.NVRight.addChild(this.shape28_3);
        this.Part2.addChild(this.Goggle);
        this.NVRight.addChild(this.shape24_1);
        this.Goggle_1.addChild(this.shape28_5);
        this.NVRight.addChild(this.Part2_1);
        this.NVStrap.addChild(this.shape27);
        this.NVStrap.addChild(this.NVHolder);
        this.Part2_1.addChild(this.Goggle_1);
        this.NVStrap.addChild(this.NVLeft);
        this.NVStrap.addChild(this.NVRight);
        
        bipedHead.addChild(this.NVStrap);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
    	super.render(entity, f, f1, f2, f3, f4, f5);
    }
    
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

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
