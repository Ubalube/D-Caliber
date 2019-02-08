package com.ubalube.scifiaddon.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;

/**
 * ModelGoggles - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelNV1 extends ModelBiped {
    public ModelRenderer NV_Base;
    public ModelRenderer Holder;
    public ModelRenderer Top;
    public ModelRenderer Top_1;
    public ModelRenderer Goggle;
    public ModelRenderer Goggle_1;
    public ModelRenderer Goggle_2;
    public ModelRenderer Side;
    public ModelRenderer Side_1;

    public ModelNV1(float scale) {
    	super(scale, 0, 64, 64);
        this.textureWidth = 200;
        this.textureHeight = 200;
        this.NV_Base = new ModelRenderer(this, 0, 180);
        this.NV_Base.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.NV_Base.addBox(-4.5F, 0.0F, -4.5F, 9, 1, 9, 0.0F);
        this.Top_1 = new ModelRenderer(this, 0, 140);
        this.Top_1.setRotationPoint(0.0F, 3.4F, -0.8F);
        this.Top_1.addBox(-4.0F, 0.0F, 0.0F, 8, 1, 3, 0.0F);
        this.setRotateAngle(Top_1, 1.275486617357456F, 0.0F, 0.0F);
        this.Side = new ModelRenderer(this, 40, 190);
        this.Side.setRotationPoint(-4.0F, 1.0F, 0.0F);
        this.Side.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3, 0.0F);
        this.Holder = new ModelRenderer(this, 20, 160);
        this.Holder.setRotationPoint(0.0F, 0.5F, -4.3F);
        this.Holder.addBox(-1.0F, -1.5F, -1.0F, 2, 3, 2, 0.0F);
        this.setRotateAngle(Holder, -0.39950586578150205F, 0.0F, 0.0F);
        this.Goggle_2 = new ModelRenderer(this, 40, 180);
        this.Goggle_2.setRotationPoint(1.6F, -1.8F, 0.5F);
        this.Goggle_2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.Goggle_1 = new ModelRenderer(this, 40, 180);
        this.Goggle_1.setRotationPoint(-1.2F, -1.8F, 0.5F);
        this.Goggle_1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.Goggle = new ModelRenderer(this, 40, 180);
        this.Goggle.setRotationPoint(-3.6F, -1.8F, 0.5F);
        this.Goggle.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.Top = new ModelRenderer(this, 0, 140);
        this.Top.setRotationPoint(0.0F, 1.6F, -1.8F);
        this.Top.addBox(-4.0F, 0.0F, 0.0F, 8, 1, 2, 0.0F);
        this.setRotateAngle(Top, 0.7068583470577035F, 0.0F, 0.0F);
        this.Side_1 = new ModelRenderer(this, 40, 190);
        this.Side_1.setRotationPoint(3.0F, 1.0F, 0.0F);
        this.Side_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3, 0.0F);
        this.Top.addChild(this.Top_1);
        this.Top_1.addChild(this.Side);
        this.NV_Base.addChild(this.Holder);
        this.Top_1.addChild(this.Goggle_2);
        this.Top_1.addChild(this.Goggle_1);
        this.Top_1.addChild(this.Goggle);
        this.Holder.addChild(this.Top);
        this.Top_1.addChild(this.Side_1);
        
        bipedHead.addChild(this.NV_Base);
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
