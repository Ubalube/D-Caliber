package com.ubalube.scifiaddon.entity.render;

import com.ubalube.scifiaddon.entity.EntitySoldier;
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
public class RenderSoldier extends RenderBiped<EntitySoldier>{

	public static final ResourceLocation[] TEXTURES = new ResourceLocation[] {new ResourceLocation(Reference.MOD_ID + ":textures/entity/soldier_1_a.png"), new ResourceLocation(Reference.MOD_ID + ":textures/entity/soldier_1_b.png"), new ResourceLocation(Reference.MOD_ID + ":textures/entity/soldier_2_a.png"), new ResourceLocation(Reference.MOD_ID + ":textures/entity/soldier_2_b.png")};
	
	public RenderSoldier(RenderManager manager) {
		super(manager, new ModelSoldier(), 0.3F);
	}
	
	protected void applyRotations(EntitySoldier entityLiving, float p_77043_2_, float rotationYaw, float PartialTicks ) {
		
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, PartialTicks);
		
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySoldier entity)
    {
        return TEXTURES[entity.getVariant()];
    }
}
