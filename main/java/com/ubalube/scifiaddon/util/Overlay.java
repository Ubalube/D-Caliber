package com.ubalube.scifiaddon.util;

import org.jline.utils.PumpReader;

import com.google.common.eventbus.Subscribe;
import com.ubalube.scifiaddon.init.ModItems;

import net.minecraft.block.BlockPumpkin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Reference.MOD_ID)
public class Overlay 
{
    public static final ResourceLocation LOCATION = new ResourceLocation(Reference.MOD_ID + ":textures/overlay/nightvision.png");
    
    @SubscribeEvent
    public static void onRenderOverlay(RenderGameOverlayEvent.Pre event)
    {
        int height = event.getResolution().getScaledHeight();
        int width = event.getResolution().getScaledWidth();
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer p = (EntityPlayer) mc.player;

        
        
        if(p.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ModItems.NVGOGGLES_t2 || p.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ModItems.NVGOGGLES_t1)
        {
            if(event.getType() == ElementType.HELMET)
            {
                Minecraft.getMinecraft().renderEngine.bindTexture(LOCATION);
                GlStateManager.enableAlpha();
                Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect(0, 0, 0, 0, width, height);
            }
        }
    }
}