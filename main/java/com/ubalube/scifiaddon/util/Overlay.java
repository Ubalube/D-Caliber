package com.ubalube.scifiaddon.util;

import com.google.common.eventbus.Subscribe;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Overlay 
{
	@SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Pre event)
    {
		
		int height = event.getResolution().getScaledHeight();
		int width = event.getResolution().getScaledWidth();
		
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/overlay/nightvision.png"));
		Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect(256, 256, 256, 256, height, width);
    }
}
