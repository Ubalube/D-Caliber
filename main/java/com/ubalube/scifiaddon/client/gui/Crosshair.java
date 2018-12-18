package com.ubalube.scifiaddon.client.gui;

import org.lwjgl.opengl.GL11;

import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Crosshair 
{
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void eventHandler(RenderGameOverlayEvent event) {
		if (!event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {

            int posX = (event.getResolution().getScaledWidth()) / 2;
            int posY = (event.getResolution().getScaledHeight()) / 2;
            int offsetFromScreenLeft = (event.getResolution().getScaledWidth() - 256) / 2;
            int offsetFromScreenTop = offsetFromScreenLeft * 2;

            EntityPlayer entitySP = Minecraft.getMinecraft().player;

            MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
            World world = server.worlds[0];

            EntityPlayer entity = null;
            for (EntityPlayer entityMP : world.playerEntities) {
                if (entityMP.getName().equals(entitySP.getName()))
                    entity = entityMP;
            }
            Minecraft mc = Minecraft.getMinecraft();
            if (entity == null)
                entity = entitySP;

            int i = (int) entity.posX;
            int j = (int) entity.posY;
            int k = (int) entity.posZ;
            int x = i;
            int y = j;
            int z = k;
			if ((true)) 
			{
				Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID + "textures/crosshairs/gun.png"));
				Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 0, 0, 256, 256);
                GL11.glColor4f(1, 1, 1, 1);
			}
		}
	}
}
