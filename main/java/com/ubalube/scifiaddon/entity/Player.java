package com.ubalube.scifiaddon.entity;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelBiped.ArmPose;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.ubalube.scifiaddon.items.GunAimable;
import com.ubalube.scifiaddon.items.GunAimableSkin;
import com.ubalube.scifiaddon.items.GunBase;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;

@SideOnly(Side.CLIENT)
@EventBusSubscriber(Side.CLIENT)
public class Player
{
	
	
	@SubscribeEvent
    public static void EntityRender(RenderLivingEvent.Pre event) {
        
		
        EntityLivingBase entity = event.getEntity();
        
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            if (player.getHeldItemMainhand().getItem() instanceof GunAimableSkin || player.getHeldItemMainhand().getItem() instanceof GunAimable) {
                
                RenderLivingBase renderer = event.getRenderer();
                
                ItemStack stack = event.getEntity().getHeldItemMainhand();
                
                ModelPlayer model = (ModelPlayer) renderer.getMainModel();
                
                if(stack.getItem() instanceof GunAimableSkin)
        		{
        			NBTTagCompound nbt = ((GunAimableSkin) stack.getItem()).checkNBTTags(stack);
        			if (nbt.getBoolean("ADS")) 
        			{
        				model.leftArmPose = ModelBiped.ArmPose.BOW_AND_ARROW;
                        model.rightArmPose = ModelBiped.ArmPose.BOW_AND_ARROW;
                    }
        			
        			if(nbt.getBoolean("running"))
        			{
        				if(player.getPrimaryHand() == EnumHandSide.LEFT)
        				{
        					model.leftArmPose = ModelBiped.ArmPose.BLOCK;
        				}
        				else
        				{
        					 model.rightArmPose = ModelBiped.ArmPose.BLOCK;
        				}
        			}
        			
        		}
        		
        		if(stack.getItem() instanceof GunAimable)
        		{
        			NBTTagCompound nbt2 = ((GunAimable) stack.getItem()).checkNBTTags(stack);
        			if (nbt2.getBoolean("ADS")) 
        			{
        				model.rightArmPose = ModelBiped.ArmPose.BOW_AND_ARROW;
        				model.leftArmPose = ModelBiped.ArmPose.BOW_AND_ARROW;
                    }
        			
        			if(nbt2.getBoolean("running"))
        			{
        				if(player.getPrimaryHand() == EnumHandSide.LEFT)
        				{
        					model.leftArmPose = ModelBiped.ArmPose.BLOCK;
        				}
        				else
        				{
        					 model.rightArmPose = ModelBiped.ArmPose.BLOCK;
        				}
        			}
        			
        		}
            }
        }
        
    }
	
	
	
	@SubscribeEvent
    public static void onRenderGameOverlayEvent(RenderGameOverlayEvent event) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        if (event.getType() == RenderGameOverlayEvent.ElementType.CROSSHAIRS) {
            if(player.getHeldItemMainhand().getItem() instanceof GunBase)
            {
            	
            	ItemStack stack = player.getHeldItemMainhand();
            	
            	if(stack.getItem() instanceof GunAimableSkin)
        		{
        			NBTTagCompound nbt = ((GunAimableSkin) stack.getItem()).checkNBTTags(stack);
        			if (nbt.getBoolean("ADS")) 
        			{
        				event.setCanceled(true);
                    }
        		}
        		
        		if(stack.getItem() instanceof GunAimable)
        		{
        			NBTTagCompound nbt2 = ((GunAimable) stack.getItem()).checkNBTTags(stack);
        			if (nbt2.getBoolean("ADS")) 
        			{
        				event.setCanceled(true);
                    }
        		}
            }
        	
        	
        }
    }
	
}
