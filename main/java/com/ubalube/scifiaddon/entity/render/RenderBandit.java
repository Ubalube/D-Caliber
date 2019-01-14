package com.ubalube.scifiaddon.entity.render;

import com.ubalube.scifiaddon.entity.EntityBandit;
import com.ubalube.scifiaddon.entity.EntityGhost;
import com.ubalube.scifiaddon.entity.EntitySoldier;
import com.ubalube.scifiaddon.entity.model.ModelBandit;
import com.ubalube.scifiaddon.entity.model.ModelGhost;
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
public class RenderBandit extends RenderBiped<EntityBandit>{

	public static final ResourceLocation[] TEXTURES = new ResourceLocation[] {new ResourceLocation(Reference.MOD_ID + ":textures/entity/bandit_1.png"), new ResourceLocation(Reference.MOD_ID + ":textures/entity/bandit_2.png"), new ResourceLocation(Reference.MOD_ID + ":textures/entity/bandit_3.png"), new ResourceLocation(Reference.MOD_ID + ":textures/entity/bandit_4.png")};
	
	public RenderBandit(RenderManager manager) {
		super(manager, new ModelBandit(), 0.3F);
	}
	
	protected void applyRotations(EntityBandit entityLiving, float p_77043_2_, float rotationYaw, float PartialTicks ) {
		
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, PartialTicks);
		
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBandit entity)
    {
        return TEXTURES[entity.getVariant()];
    }
}
