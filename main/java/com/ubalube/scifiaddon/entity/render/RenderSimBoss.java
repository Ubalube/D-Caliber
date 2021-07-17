package com.ubalube.scifiaddon.entity.render;

import com.ubalube.scifiaddon.entity.EntityGoliath;
import com.ubalube.scifiaddon.entity.EntitySimBoss;
import com.ubalube.scifiaddon.entity.EntitySoldier;
import com.ubalube.scifiaddon.entity.model.ModelSimBoss;
import com.ubalube.scifiaddon.entity.model.ModelSoldier;
import com.ubalube.scifiaddon.entity.model.ModelTank;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderSimBoss extends RenderLiving<EntitySimBoss>
{
	public static final ResourceLocation[] TEXTURES = new ResourceLocation[] {new ResourceLocation(Reference.MOD_ID + ":textures/entity/simboss.png"), new ResourceLocation(Reference.MOD_ID + ":textures/entity/simboss_phase_2.png"), new ResourceLocation(Reference.MOD_ID + ":textures/entity/simboss_phase_3.png")};
	
	public RenderSimBoss(RenderManager manager) 
	{
		super(manager, new ModelSimBoss(), 0.5F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntitySimBoss entity) 
	{
		return TEXTURES[entity.getDamageStage()];
	}

	@Override
	protected void applyRotations(EntitySimBoss entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
	{
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
}
