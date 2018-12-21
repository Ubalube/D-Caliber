package com.ubalube.scifiaddon.entity.render;

import org.lwjgl.opengl.GL11;

import com.ubalube.scifiaddon.entity.EntityBullet;
import com.ubalube.scifiaddon.entity.EntityFrag;
import com.ubalube.scifiaddon.entity.EntitySoldier;
import com.ubalube.scifiaddon.entity.model.ImpactNade;
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
public class RenderGrenade extends Render<EntityFrag>
{
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/frag.png");
    private final ImpactNade model = new ImpactNade();
 
    public RenderGrenade(RenderManager manager) 
    {
		super(manager);
	}
    
    @Override
    public void doRender(EntityFrag entity, double x, double y, double z, float entityYaw, float partialTicks) {
        
    	GL11.glPushMatrix();
        bindTexture(TEXTURES);
        GL11.glTranslated(x, y - 1.3, z);
        model.render(entity, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
    }
    
    @Override
    public ResourceLocation getEntityTexture(EntityFrag entity)
    {
        return TEXTURES;
    }
}
