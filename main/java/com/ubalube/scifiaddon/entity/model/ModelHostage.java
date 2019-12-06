package com.ubalube.scifiaddon.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelPlayer - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelHostage extends ModelBase {
    public ModelRenderer Holder;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedBody;
    public ModelRenderer head;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer LowerLeg_Left;
    public ModelRenderer bipedLeftLeg_1;
    public ModelRenderer LowerLeg_Right;

    public ModelHostage() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.setRotateAngle(head, 0.7330382858376184F, 0.0F, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 16, 48);
        this.bipedLeftLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.setRotateAngle(bipedLeftLeg, 0.0F, 0.4553564018453205F, 0.31869712141416456F);
        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(bipedRightArm, 0.7740535232594852F, 0.6373942428283291F, 0.0F);
        this.LowerLeg_Left = new ModelRenderer(this, 16, 38);
        this.LowerLeg_Left.setRotationPoint(-3.1F, 18.0F, -1.1F);
        this.LowerLeg_Left.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.setRotateAngle(LowerLeg_Left, 1.593485607070823F, -0.6373942428283291F, 0.0F);
        this.Holder = new ModelRenderer(this, 0, 0);
        this.Holder.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.Holder.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.LowerLeg_Right = new ModelRenderer(this, 16, 38);
        this.LowerLeg_Right.setRotationPoint(3.4F, 18.0F, -0.9F);
        this.LowerLeg_Right.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.setRotateAngle(LowerLeg_Right, 1.593485607070823F, 0.6373942428283291F, 0.0F);
        this.bipedLeftLeg_1 = new ModelRenderer(this, 16, 48);
        this.bipedLeftLeg_1.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.bipedLeftLeg_1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.setRotateAngle(bipedLeftLeg_1, 0.0F, -0.4553564018453205F, -0.36425021489121656F);
        this.bipedLeftArm = new ModelRenderer(this, 32, 48);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(bipedLeftArm, 0.7740535232594852F, -0.5462880558742251F, 0.0F);
        this.Holder.addChild(this.bipedBody);
        this.Holder.addChild(this.head);
        this.Holder.addChild(this.bipedLeftLeg);
        this.Holder.addChild(this.bipedRightArm);
        this.Holder.addChild(this.LowerLeg_Left);
        this.Holder.addChild(this.LowerLeg_Right);
        this.Holder.addChild(this.bipedLeftLeg_1);
        this.Holder.addChild(this.bipedLeftArm);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Holder.render(f5);
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
