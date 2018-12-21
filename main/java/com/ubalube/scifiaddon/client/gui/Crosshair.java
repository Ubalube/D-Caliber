package com.ubalube.scifiaddon.client.gui;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import com.ubalube.scifiaddon.BooleanHelper;
import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(Side.CLIENT)
public class Crosshair extends Gui
{
	
	
	
	@SubscribeEvent
    public static void onRenderGameOverlayEvent(RenderGameOverlayEvent event) {
        EntityPlayer player = main.proxy.getPlayer();
        if (event.getType() == RenderGameOverlayEvent.ElementType.CROSSHAIRS) 
        {
        	event.setCanceled(true);
        	Minecraft MC = Minecraft.getMinecraft();
        	Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/crosshairs/gun.png"));
			Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 0, 0, 256, 256);
            GL11.glColor4f(1, 1, 1, 1);
        }
        
    }
	
}
