package com.ubalube.scifiaddon.entity.model;
import com.ubalube.scifiaddon.entity.EntitySimBoss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * SimulatedBoss - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelSimBoss extends ModelBase {
    public ModelRenderer Shield_Rotate_Point;
    public ModelRenderer Eye;
    public ModelRenderer Shield_4;
    public ModelRenderer Shield_3;
    public ModelRenderer Shield_2;
    public ModelRenderer Shield_1;
    public ModelRenderer Shield_4_Part;
    public ModelRenderer Shield_4_Part_1;
    public ModelRenderer Shield_3_Part;
    public ModelRenderer Shield_3_Part_1;
    public ModelRenderer Shield_2_Part;
    public ModelRenderer Shield_2_Part_1;
    public ModelRenderer Shield_1_Part;
    public ModelRenderer Shield_1_Part_1;
    public ModelRenderer Pole;

    public ModelSimBoss() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.Shield_1_Part_1 = new ModelRenderer(this, 33, 0);
        this.Shield_1_Part_1.setRotationPoint(-4.0F, 0.0F, 0.2F);
        this.Shield_1_Part_1.addBox(-1.5F, -9.0F, -1.5F, 5, 18, 3, 0.0F);
        this.Shield_4_Part = new ModelRenderer(this, 68, 25);
        this.Shield_4_Part.setRotationPoint(-0.2F, 0.0F, 2.0F);
        this.Shield_4_Part.addBox(-1.5F, -9.0F, -1.5F, 5, 18, 3, 0.0F);
        this.setRotateAngle(Shield_4_Part, 0.0F, -1.5707963267948966F, 0.0F);
        this.Shield_1_Part = new ModelRenderer(this, 17, 0);
        this.Shield_1_Part.setRotationPoint(-0.2F, 0.0F, 2.0F);
        this.Shield_1_Part.addBox(-1.5F, -9.0F, -1.5F, 5, 18, 3, 0.0F);
        this.setRotateAngle(Shield_1_Part, 0.0F, -1.5707963267948966F, 0.0F);
        this.Pole = new ModelRenderer(this, 0, 50);
        this.Pole.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Pole.addBox(-2.0F, -7.5F, -2.0F, 4, 15, 4, 0.0F);
        this.Shield_2 = new ModelRenderer(this, 50, 0);
        this.Shield_2.setRotationPoint(-15.0F, 0.0F, -15.0F);
        this.Shield_2.addBox(-1.5F, -10.0F, -1.5F, 3, 20, 3, 0.0F);
        this.setRotateAngle(Shield_2, 0.0F, 1.5707963267948966F, 0.0F);
        this.Shield_3_Part = new ModelRenderer(this, 18, 25);
        this.Shield_3_Part.setRotationPoint(-0.2F, 0.0F, 2.0F);
        this.Shield_3_Part.addBox(-1.5F, -9.0F, -1.5F, 5, 18, 3, 0.0F);
        this.setRotateAngle(Shield_3_Part, 0.0F, -1.5707963267948966F, 0.0F);
        this.Shield_3 = new ModelRenderer(this, 0, 25);
        this.Shield_3.setRotationPoint(-15.0F, 0.0F, 15.0F);
        this.Shield_3.addBox(-1.5F, -10.0F, -1.5F, 3, 20, 3, 0.0F);
        this.setRotateAngle(Shield_3, 0.0F, 3.141592653589793F, 0.0F);
        this.Shield_2_Part = new ModelRenderer(this, 65, 0);
        this.Shield_2_Part.setRotationPoint(-0.2F, 0.0F, 2.0F);
        this.Shield_2_Part.addBox(-1.5F, -9.0F, -1.5F, 5, 18, 3, 0.0F);
        this.setRotateAngle(Shield_2_Part, 0.0F, -1.5707963267948966F, 0.0F);
        this.Shield_4 = new ModelRenderer(this, 55, 25);
        this.Shield_4.setRotationPoint(15.0F, 0.0F, 15.0F);
        this.Shield_4.addBox(-1.5F, -10.0F, -1.5F, 3, 20, 3, 0.0F);
        this.setRotateAngle(Shield_4, 0.0F, -1.5707963267948966F, 0.0F);
        this.Eye = new ModelRenderer(this, 0, 100);
        this.Eye.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Eye.addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F);
        this.Shield_1 = new ModelRenderer(this, 0, 0);
        this.Shield_1.setRotationPoint(15.0F, 0.0F, -15.0F);
        this.Shield_1.addBox(-1.5F, -10.0F, -1.5F, 3, 20, 3, 0.0F);
        this.Shield_2_Part_1 = new ModelRenderer(this, 85, 0);
        this.Shield_2_Part_1.setRotationPoint(-4.0F, 0.0F, 0.2F);
        this.Shield_2_Part_1.addBox(-1.5F, -9.0F, -1.5F, 5, 18, 3, 0.0F);
        this.Shield_Rotate_Point = new ModelRenderer(this, 100, 100);
        this.Shield_Rotate_Point.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shield_Rotate_Point.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.Shield_3_Part_1 = new ModelRenderer(this, 36, 25);
        this.Shield_3_Part_1.setRotationPoint(-4.0F, 0.0F, 0.2F);
        this.Shield_3_Part_1.addBox(-1.5F, -9.0F, -1.5F, 5, 18, 3, 0.0F);
        this.Shield_4_Part_1 = new ModelRenderer(this, 88, 25);
        this.Shield_4_Part_1.setRotationPoint(-4.0F, 0.0F, 0.2F);
        this.Shield_4_Part_1.addBox(-1.5F, -9.0F, -1.5F, 5, 18, 3, 0.0F);
        this.Shield_1.addChild(this.Shield_1_Part_1);
        this.Shield_4.addChild(this.Shield_4_Part);
        this.Shield_1.addChild(this.Shield_1_Part);
        this.Eye.addChild(this.Pole);
        this.Shield_Rotate_Point.addChild(this.Shield_2);
        this.Shield_3.addChild(this.Shield_3_Part);
        this.Shield_Rotate_Point.addChild(this.Shield_3);
        this.Shield_2.addChild(this.Shield_2_Part);
        this.Shield_Rotate_Point.addChild(this.Shield_4);
        this.Shield_Rotate_Point.addChild(this.Shield_1);
        this.Shield_2.addChild(this.Shield_2_Part_1);
        this.Shield_3.addChild(this.Shield_3_Part_1);
        this.Shield_4.addChild(this.Shield_4_Part_1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Eye.render(f5);
        this.Shield_Rotate_Point.render(f5);
        
        EntitySimBoss boss = (EntitySimBoss) entity;
        if(!boss.isOnFinalPhase() && boss.getHealth() == boss.getMaxHealth())
        {
            this.Shield_1.isHidden = false;
            this.Shield_2.isHidden = false;
            this.Shield_3.isHidden = false;
            this.Shield_4.isHidden = false;
        }
        else
        {
        	if(boss.isOnFinalPhase())
            {
                this.Shield_1.isHidden = true;
                this.Shield_2.isHidden = true;
                this.Shield_3.isHidden = true;
                this.Shield_4.isHidden = true;
            }
            else
            {
            	if(boss.getHealth() < boss.getMaxHealth() - 480)
                {
                    this.Shield_1.isHidden = true;
                    this.Shield_2.isHidden = true;
                    this.Shield_3.isHidden = true;
                    this.Shield_4.isHidden = true;
                }
                else if(boss.getHealth() < boss.getMaxHealth() - 75)
                {
                    this.Shield_1.isHidden = true;
                    this.Shield_2.isHidden = true;
                    this.Shield_3.isHidden = true;
                    this.Shield_4.isHidden = false;
                }
                else if(boss.getHealth() < boss.getMaxHealth() -50)
                {
                    this.Shield_1.isHidden = true;
                    this.Shield_2.isHidden = true;
                    this.Shield_3.isHidden = false;
                    this.Shield_4.isHidden = false;
                }
                else if(boss.getHealth() < boss.getMaxHealth() - 25)
                {
                    this.Shield_1.isHidden = true;
                    this.Shield_2.isHidden = false;
                    this.Shield_3.isHidden = false;
                    this.Shield_4.isHidden = false;
                }
            }
        }
        
        
        
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
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    	
    	this.Shield_Rotate_Point.rotateAngleY = (float) Math.toRadians(360 * ageInTicks * .05f);
    	
    }
}
