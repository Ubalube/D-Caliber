package com.ubalube.scifiaddon.entity.render;

import com.ubalube.scifiaddon.entity.EntityBandit;
import com.ubalube.scifiaddon.entity.EntityGhost;
import com.ubalube.scifiaddon.entity.EntityGlitch;
import com.ubalube.scifiaddon.entity.EntityHazmat;
import com.ubalube.scifiaddon.entity.EntityHostage;
import com.ubalube.scifiaddon.entity.EntitySoldier;
import com.ubalube.scifiaddon.entity.model.ModelBandit;
import com.ubalube.scifiaddon.entity.model.ModelGhost;
import com.ubalube.scifiaddon.entity.model.ModelGlitch;
import com.ubalube.scifiaddon.entity.model.ModelHazmat;
import com.ubalube.scifiaddon.entity.model.ModelHostage;
import com.ubalube.scifiaddon.entity.model.ModelSoldier;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHazmat extends RenderBiped<EntityHazmat>{

	public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/hazmat.png");
	
	public RenderHazmat(RenderManager manager) {
		super(manager, new ModelHazmat(), 0.3F);
	}
	
	protected void applyRotations(EntityHazmat entityLiving, float p_77043_2_, float rotationYaw, float PartialTicks ) {
		
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, PartialTicks);
		
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHazmat entity)
    {
        return TEXTURE;
    }
}
