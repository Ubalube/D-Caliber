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
public class ModelChromaticHelm extends ModelBiped {
    public ModelRenderer Main;
    public ModelRenderer Helmet;
    //public ModelRenderer Helmet_1;
    public ModelRenderer HelmHolder;
    public ModelRenderer RightSide;
    public ModelRenderer LeftSide;
    public ModelRenderer LeftSide_1;
    public ModelRenderer shape21;
    public ModelRenderer shape21_1;
    public ModelRenderer shape23;
    public ModelRenderer shape23_1;
    public ModelRenderer LeftSide_2;

    public ModelChromaticHelm(float scale) {
    	super(scale, 0, 64, 64);
        this.textureWidth = 200;
        this.textureHeight = 200;
        this.shape21_1 = new ModelRenderer(this, 100, 190);
        this.shape21_1.setRotationPoint(5.2F, 5.5F, 6.0F);
        this.shape21_1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(shape21_1, 0.29094638630745473F, -0.2602285914723545F, 0.0F);
        //this.Helmet_1 = new ModelRenderer(this, 100, 150);
        //this.Helmet_1.setRotationPoint(-4.0F, -7.3F, -4.5F);
        //this.Helmet_1.addBox(0.0F, 0.0F, 0.0F, 8, 7, 9, 0.0F);
        this.shape21 = new ModelRenderer(this, 33, 181);
        this.shape21.setRotationPoint(-0.2F, 5.8F, 7.6F);	
        this.shape21.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
        this.setRotateAngle(shape21, -0.5918411493512771F, -0.7155849933176751F, 0.0F);
        this.Helmet = new ModelRenderer(this, 46, 137);
        this.Helmet.setRotationPoint(-4.0F, -8.0F, -4.0F);
        this.Helmet.addBox(0.0F, 0.0F, 0.0F, 8, 9, 9, 0.2F);
        this.HelmHolder = new ModelRenderer(this, 0, 0);
        this.HelmHolder.setRotationPoint(0.0F, 0.5F, 0.0F);
        this.HelmHolder.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.LeftSide = new ModelRenderer(this, 3, 129);
        this.LeftSide.setRotationPoint(7.3F, 2.0F, 7.6F);
        this.LeftSide.addBox(0.0F, 0.0F, 0.0F, 1, 4, 3, 0.0F);
        this.setRotateAngle(LeftSide, 0.0F, -0.6457718232379019F, 0.0F);
        this.shape23 = new ModelRenderer(this, 49, 169);
        this.shape23.setRotationPoint(2.0F, 1.0F, 7.6F);
        this.shape23.addBox(0.0F, 0.0F, 0.0F, 4, 1, 3, 0.0F);
        this.setRotateAngle(shape23, -0.1684242728174528F, 0.0F, 0.0F);
        this.RightSide = new ModelRenderer(this, 3, 129);
        this.RightSide.setRotationPoint(0.2F, 2.0F, 8.3F);
        this.RightSide.addBox(0.0F, 0.0F, 0.0F, 1, 4, 3, 0.0F);
        this.setRotateAngle(RightSide, 0.0F, 0.6457718232379019F, 0.0F);
        this.Main = new ModelRenderer(this, 0, 0);
        this.Main.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Main.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(Main, 0.0F, 3.141592653589793F, 0.0F);
        this.LeftSide_2 = new ModelRenderer(this, 3, 129);
        this.LeftSide_2.setRotationPoint(0.0F, 0.0F, 3.9F);
        this.LeftSide_2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 3, 0.0F);
        this.setRotateAngle(LeftSide_2, 0.0F, 0.7960446718346137F, 0.0F);
        this.LeftSide_1 = new ModelRenderer(this, 3, 129);
        this.LeftSide_1.setRotationPoint(6.1F, 2.0F, 10.6F);
        this.LeftSide_1.addBox(0.0F, 0.0F, 0.0F, 1, 4, 4, 0.0F);
        this.setRotateAngle(LeftSide_1, 0.0F, 1.5707963267948966F, 1.5707963267948966F);
        this.shape23_1 = new ModelRenderer(this, 4, 176);
        this.shape23_1.setRotationPoint(1.0F, 1.4F, 7.3F);
        this.shape23_1.addBox(0.0F, 0.0F, 0.0F, 6, 1, 2, 0.0F);
        this.setRotateAngle(shape23_1, -0.1684242728174528F, 0.0F, 0.0F);
        this.HelmHolder.addChild(this.shape21_1);
        //this.Main.addChild(this.Helmet_1);
        this.HelmHolder.addChild(this.shape21);
        this.Main.addChild(this.Helmet);
        this.Helmet.addChild(this.HelmHolder);
        this.HelmHolder.addChild(this.LeftSide);
        this.HelmHolder.addChild(this.shape23);
        this.HelmHolder.addChild(this.RightSide);
        this.LeftSide_1.addChild(this.LeftSide_2);
        this.HelmHolder.addChild(this.LeftSide_1);
        this.HelmHolder.addChild(this.shape23_1);
        
        bipedHead.addChild(this.Main);
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
