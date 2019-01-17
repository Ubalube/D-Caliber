package com.ubalube.scifiaddon.entity.render;

import com.ubalube.scifiaddon.entity.EntityGhost;
import com.ubalube.scifiaddon.entity.EntityMilita;
import com.ubalube.scifiaddon.entity.EntitySoldier;
import com.ubalube.scifiaddon.entity.model.ModelGhost;
import com.ubalube.scifiaddon.entity.model.ModelMilita;
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
public class RenderMilita extends RenderBiped<EntityMilita>{

	public static final ResourceLocation[] TEXTURES = new ResourceLocation[] {new ResourceLocation(Reference.MOD_ID + ":textures/entity/milita_1.png"), new ResourceLocation(Reference.MOD_ID + ":textures/entity/milita_2.png"), new ResourceLocation(Reference.MOD_ID + ":textures/entity/milita_3.png"), new ResourceLocation(Reference.MOD_ID + ":textures/entity/milita_4.png")};
	
	public RenderMilita(RenderManager manager) {
		super(manager, new ModelMilita(), 0.3F);
	}
	
	protected void applyRotations(EntityMilita entityLiving, float p_77043_2_, float rotationYaw, float PartialTicks ) {
		
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, PartialTicks);
		
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMilita entity)
    {
        return TEXTURES[entity.getVariant()];
    }
}
