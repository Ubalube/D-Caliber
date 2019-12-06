package com.ubalube.scifiaddon.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Shield - XboxSignsOut
 * Created using Tabula 7.0.0
 */
public class ModelDeployableShield extends ModelBase {
    public ModelRenderer Holder;
    public ModelRenderer shape1;
    public ModelRenderer shape1_1;
    public ModelRenderer shape1_2;
    public ModelRenderer shape1_3;
    public ModelRenderer shape1_4;
    public ModelRenderer shape1_5;

    public ModelDeployableShield() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.shape1 = new ModelRenderer(this, 0, 51);
        this.shape1.setRotationPoint(5.0F, 12.0F, -1.0F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 10, 12, 1, 0.0F);
        this.setRotateAngle(shape1, 0.0F, -0.7740535232594852F, 0.0F);
        this.shape1_1 = new ModelRenderer(this, 0, 30);
        this.shape1_1.setRotationPoint(-5.0F, 13.0F, -1.0F);
        this.shape1_1.addBox(0.0F, 0.0F, 0.0F, 10, 11, 1, 0.0F);
        this.shape1_2 = new ModelRenderer(this, 10, 0);
        this.shape1_2.setRotationPoint(-4.3F, 0.0F, 0.7F);
        this.shape1_2.addBox(0.0F, 0.0F, 0.0F, 10, 24, 1, 0.0F);
        this.setRotateAngle(shape1_2, 0.0F, -2.3038346126325147F, 0.0F);
        this.shape1_3 = new ModelRenderer(this, 42, 30);
        this.shape1_3.setRotationPoint(-5.0F, 0.0F, 0.0F);
        this.shape1_3.addBox(0.0F, 0.0F, 0.0F, 10, 24, 1, 0.0F);
        this.shape1_4 = new ModelRenderer(this, 40, 0);
        this.shape1_4.setRotationPoint(5.0F, 0.0F, 0.0F);
        this.shape1_4.addBox(0.0F, 0.0F, 0.0F, 10, 24, 1, 0.0F);
        this.setRotateAngle(shape1_4, 0.0F, -0.7740535232594852F, 0.0F);
        this.shape1_5 = new ModelRenderer(this, 0, 51);
        this.shape1_5.setRotationPoint(-4.3F, 12.0F, -0.3F);
        this.shape1_5.addBox(0.0F, 0.0F, 0.0F, 10, 12, 1, 0.0F);
        this.setRotateAngle(shape1_5, 0.0F, -2.3038346126325147F, 0.0F);
        this.Holder = new ModelRenderer(this, 0, 0);
        this.Holder.setRotationPoint(0.0F, 0.0F, -7.0F);
        this.Holder.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.Holder.addChild(this.shape1);
        this.Holder.addChild(this.shape1_1);
        this.Holder.addChild(this.shape1_2);
        this.Holder.addChild(this.shape1_3);
        this.Holder.addChild(this.shape1_4);
        this.Holder.addChild(this.shape1_5);
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
