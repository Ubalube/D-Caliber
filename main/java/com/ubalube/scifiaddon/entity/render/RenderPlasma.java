package com.ubalube.scifiaddon.entity.render;

import org.lwjgl.opengl.GL11;

import com.ubalube.scifiaddon.entity.EntityBullet;
import com.ubalube.scifiaddon.entity.EntityPlasma;
import com.ubalube.scifiaddon.entity.model.ModelBullet;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPlasma extends Render<EntityPlasma>
{
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/plasma.png");
    private final ModelBullet model = new ModelBullet();
 
    public RenderPlasma(RenderManager manager) 
    {
		super(manager);
	}
 
    @Override
    public void doRender(EntityPlasma entity, double x, double y, double z, float entityYaw, float partialTicks) {
        
    	GL11.glPushMatrix();
        bindTexture(TEXTURES);
        GL11.glTranslated(x, y - 1.3, z);
        model.render(entity, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
        GL11.glPopMatrix();
    }
    
    @Override
    public ResourceLocation getEntityTexture(EntityPlasma entity)
    {
        return TEXTURES;
    }
}
