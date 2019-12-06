package com.ubalube.scifiaddon.entity.render;

import org.lwjgl.opengl.GL11;

import com.ubalube.scifiaddon.entity.EntityBullet;
import com.ubalube.scifiaddon.entity.EntityDeployableShield;
import com.ubalube.scifiaddon.entity.EntityGhost;
import com.ubalube.scifiaddon.entity.EntityGoliath;
import com.ubalube.scifiaddon.entity.model.ModelBullet;
import com.ubalube.scifiaddon.entity.model.ModelDeployableShield;
import com.ubalube.scifiaddon.entity.model.ModelGhost;
import com.ubalube.scifiaddon.entity.model.ModelTank;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDeployableShield extends RenderLiving<EntityDeployableShield>
{
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/deployableshield.png");
	
	public RenderDeployableShield(RenderManager manager) 
	{
		super(manager, new ModelDeployableShield(), 0.5F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityDeployableShield entity) 
	{
		return TEXTURES;
	}

	@Override
	protected void applyRotations(EntityDeployableShield entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
	{
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
}
