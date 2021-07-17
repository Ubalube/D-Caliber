package com.ubalube.scifiaddon.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.math.MathHelper;

/**
 * ModelPlayer - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelHazmat extends ModelBiped {
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedHead;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer bipedRightArmwear;
    public ModelRenderer bipedLeftArmwear;
    public ModelRenderer bipedBodyWear;
    public ModelRenderer bipedHeadwear;
    public ModelRenderer bipedRightLegwear;
    public ModelRenderer bipedLeftLegwear;

    public ModelHazmat() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(bipedRightArm, 0.0F, 0.0F, 0.10000000149011613F);
        this.bipedLeftLeg = new ModelRenderer(this, 16, 48);
        this.bipedLeftLeg.setRotationPoint(1.899999976158142F, 12.0F, 0.10000000149011612F);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.bipedBodyWear = new ModelRenderer(this, 16, 32);
        this.bipedBodyWear.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBodyWear.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.bipedLeftLegwear = new ModelRenderer(this, 0, 48);
        this.bipedLeftLegwear.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedLeftLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 32, 48);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(bipedLeftArm, 0.0F, 0.0F, -0.10000000149011613F);
        this.bipedRightLegwear = new ModelRenderer(this, 0, 32);
        this.bipedRightLegwear.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedRightLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
        this.bipedRightArmwear.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedRightArmwear.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
        this.bipedLeftArmwear.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg.setRotationPoint(-1.899999976158142F, 12.0F, 0.10000000149011612F);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.bipedHeadwear = new ModelRenderer(this, 32, 0);
        this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.bipedBody.addChild(this.bipedBodyWear);
        this.bipedLeftLeg.addChild(this.bipedLeftLegwear);
        this.bipedRightLeg.addChild(this.bipedRightLegwear);
        this.bipedRightArm.addChild(this.bipedRightArmwear);
        this.bipedLeftArm.addChild(this.bipedLeftArmwear);
        this.bipedHead.addChild(this.bipedHeadwear);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.bipedRightArm.render(f5);
        this.bipedLeftLeg.render(f5);
        this.bipedLeftArm.render(f5);
        this.bipedBody.render(f5);
        this.bipedHead.render(f5);
        this.bipedRightLeg.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        boolean flag = entityIn instanceof EntityZombie && ((EntityZombie)entityIn).isArmsRaised();
        float f = MathHelper.sin(this.swingProgress * (float)Math.PI);
        float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.bipedRightArm.rotateAngleY = -(0.1F - f * 0.6F);
        this.bipedLeftArm.rotateAngleY = 0.1F - f * 0.6F;
        float f2 = -(float)Math.PI / (flag ? 1.5F : 2.25F);
        this.bipedRightArm.rotateAngleX = f2;
        this.bipedLeftArm.rotateAngleX = f2;
        this.bipedRightArm.rotateAngleX += f * 1.2F - f1 * 0.4F;
        this.bipedLeftArm.rotateAngleX += f * 1.2F - f1 * 0.4F;
        this.bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
    }
    
}
