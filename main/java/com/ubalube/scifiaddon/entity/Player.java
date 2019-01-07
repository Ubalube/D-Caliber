package com.ubalube.scifiaddon.entity;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.lwjgl.opengl.GL11;

import com.ubalube.scifiaddon.items.CGunAimingBase;
import com.ubalube.scifiaddon.items.CGunBase;
import com.ubalube.scifiaddon.items.CGunGrenadeLauncher;
import com.ubalube.scifiaddon.items.CGunHelper;
import com.ubalube.scifiaddon.items.CGunPDW;
import com.ubalube.scifiaddon.items.CGunPistol;
import com.ubalube.scifiaddon.items.CGunSkinnable;
import com.ubalube.scifiaddon.items.CGunSniper;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;


public class Player
{
	
	@SubscribeEvent
    public static void EntityRender(RenderLivingEvent.Pre event) {
        
        EntityLivingBase entity = event.getEntity();
        
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            if (player.getHeldItemMainhand().getItem() instanceof CGunBase || player.getHeldItemMainhand().getItem() instanceof CGunAimingBase ||
            		player.getHeldItemMainhand().getItem() instanceof CGunHelper || player.getHeldItemMainhand().getItem() instanceof CGunPDW ||
            		player.getHeldItemMainhand().getItem() instanceof CGunSniper || player.getHeldItemMainhand().getItem() instanceof CGunPistol ||
            		player.getHeldItemMainhand().getItem() instanceof CGunSkinnable || player.getHeldItemMainhand().getItem() instanceof CGunGrenadeLauncher) 
            {
            
                RenderLivingBase renderer = event.getRenderer();
                
                ModelPlayer model = (ModelPlayer) renderer.getMainModel();
                
                model.leftArmPose = ModelBiped.ArmPose.BOW_AND_ARROW;
                model.rightArmPose = ModelBiped.ArmPose.BOW_AND_ARROW;
            }
        }
        
    }
}
