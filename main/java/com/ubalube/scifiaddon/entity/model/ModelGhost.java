package com.ubalube.scifiaddon.entity.model;

import com.ubalube.scifiaddon.entity.EntityGhost;
import com.ubalube.scifiaddon.entity.EntitySoldier;
import com.ubalube.scifiaddon.items.GunBase;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelBiped.ArmPose;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * ModelSoldier - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
@SideOnly(Side.CLIENT)
public class ModelGhost extends ModelBiped 
{
	
	public ModelGhost()
	{
		this(0.0F, false);	
	}
	
    public ModelGhost(float modelSize, boolean p_i1168_2_)
   	{
   		 super(modelSize, 0.0F, 64, p_i1168_2_ ? 32 : 64);
   	}

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.bipedLeftLeg.render(f5);
        this.bipedBody.render(f5);
        this.bipedHead.render(f5);
        this.bipedRightLeg.render(f5);
        this.bipedHeadwear.render(f5);
        this.bipedLeftArm.render(f5);
        this.bipedRightArm.render(f5);
    }
    
    @Override
	protected EnumHandSide getMainHand(Entity entityIn) 
	{
		return EnumHandSide.RIGHT;
	}
    
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
 
        if(entityIn instanceof EntityGhost) {
        	EntityGhost npc = (EntityGhost) entityIn;
        	
        	if(!npc.getHeldItemMainhand().isEmpty() && npc.getHeldItemMainhand().getItem() instanceof GunBase) {
        		if (npc.isLeftHanded()) {
        			this.leftArmPose=ArmPose.BOW_AND_ARROW;
        		} else {
        			this.rightArmPose=ArmPose.BOW_AND_ARROW;
        		}
        	} else if(!npc.getHeldItemMainhand().isEmpty()){
        		if (npc.isLeftHanded()) {
        			this.leftArmPose=ArmPose.ITEM;
        		} else {
        			this.rightArmPose=ArmPose.ITEM;
        		}
        	} else {
        		this.leftArmPose=ArmPose.EMPTY;
        		this.rightArmPose=ArmPose.EMPTY;
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
}
