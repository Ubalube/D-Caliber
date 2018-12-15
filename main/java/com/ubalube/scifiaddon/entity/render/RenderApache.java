package com.ubalube.scifiaddon.entity.render;

import com.ubalube.scifiaddon.entity.EntityApache;
import com.ubalube.scifiaddon.entity.EntityGoliath;
import com.ubalube.scifiaddon.entity.EntitySoldier;
import com.ubalube.scifiaddon.entity.model.ModelApache;
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

public class RenderApache extends RenderLiving<EntityApache>
{
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/apache.png");
	
	public RenderApache(RenderManager manager) 
	{
		super(manager, new ModelApache(), 0.5F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityApache entity) 
	{
		return TEXTURES;
	}

	@Override
	protected void applyRotations(EntityApache entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
	{
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
}
