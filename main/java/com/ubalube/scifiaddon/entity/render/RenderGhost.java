package com.ubalube.scifiaddon.entity.render;

import com.ubalube.scifiaddon.entity.EntityGhost;
import com.ubalube.scifiaddon.entity.EntitySoldier;
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
public class RenderGhost extends RenderBiped<EntityGhost>{

	public static final ResourceLocation[] TEXTURES = new ResourceLocation[] {new ResourceLocation(Reference.MOD_ID + ":textures/entity/ghost_1.png"), new ResourceLocation(Reference.MOD_ID + ":textures/entity/ghost_2.png"), new ResourceLocation(Reference.MOD_ID + ":textures/entity/ghost_3.png"), new ResourceLocation(Reference.MOD_ID + ":textures/entity/ghost_4.png")};
	
	public RenderGhost(RenderManager manager) {
		super(manager, new ModelGhost(), 0.3F);
	}
	
	protected void applyRotations(EntityGhost entityLiving, float p_77043_2_, float rotationYaw, float PartialTicks ) {
		
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, PartialTicks);
		
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityGhost entity)
    {
        return TEXTURES[entity.getVariant()];
    }
}
