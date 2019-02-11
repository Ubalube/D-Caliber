package com.ubalube.scifiaddon.entity.model;

import com.ubalube.scifiaddon.entity.EntitySoldier;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.items.GunBase;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * ModelPlayer - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
@SideOnly(Side.CLIENT)
public class ModelSoldier extends ModelBiped {
    
	
	public ModelSoldier()
	{
		this(0.0F, false);	
	}

	 public ModelSoldier(float modelSize, boolean p_i1168_2_)
	{
		 super(modelSize, 0.0F, 64, p_i1168_2_ ? 32 : 64);
	}
    
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
 
        if(entityIn instanceof EntitySoldier) {
        	EntitySoldier npc = (EntitySoldier) entityIn;
        	
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
    
}
