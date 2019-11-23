package com.ubalube.scifiaddon.util;

import org.lwjgl.opengl.GL11;

import com.ubalube.scifiaddon.items.GunBase;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderParrot;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerGunBack implements LayerRenderer<AbstractClientPlayer>
{
	
	private final RenderPlayer renderPlayer;
	
	public LayerGunBack(RenderPlayer renderPlayer) {
		this.renderPlayer = renderPlayer;
	}
	
	@Override
	public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		ItemStack itemToRender = null;
		for(int i = 0; i < entitylivingbaseIn.inventory.getSizeInventory(); i++)
		{
			if(entitylivingbaseIn.inventory.getStackInSlot(i).getItem() instanceof GunBase)
			{
				itemToRender = entitylivingbaseIn.inventory.getStackInSlot(i);
				break;
			}
		}
		
		GL11.glPushMatrix();
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		
		ModelRenderer body = renderPlayer.getMainModel().bipedBody;
		body.postRender(0.0625f);
		
		if(entitylivingbaseIn.isSneaking())
		{
			GL11.glTranslatef(0.0f, 0.18f, -0.1f);
		}
		
		//Minecraft.getMinecraft().renderEngine.bindTexture(itemToRender.get);
		
	}

	@Override
	public boolean shouldCombineTextures() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
