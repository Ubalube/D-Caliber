package com.ubalube.scifiaddon.util.handlers;

import com.ubalube.scifiaddon.client.gui.AmmoCounter;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderGUIHandler 
{
	@SubscribeEvent
    public void onRenderGui(RenderGameOverlayEvent.Post event)
    {
		if (event.getType() != ElementType.EXPERIENCE) return;
		new AmmoCounter(Minecraft.getMinecraft());
    }
}
