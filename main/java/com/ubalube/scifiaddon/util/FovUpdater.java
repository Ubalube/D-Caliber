package com.ubalube.scifiaddon.util;

import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.items.CGunAimingBase;
import com.ubalube.scifiaddon.items.GunAimable;
import com.ubalube.scifiaddon.items.GunAimableSkin;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.collection.concurrent.Debug;
import net.minecraftforge.fml.relauncher.Side;

/*
 * Made By Smellycock
 */
@EventBusSubscriber(Side.CLIENT)
public class FovUpdater 
{
	@SubscribeEvent
	public static void fovUpdate(FOVUpdateEvent event) {
		ItemStack stack = event.getEntity().getHeldItemMainhand();
		
		
		
		if(stack.getItem() instanceof GunAimableSkin)
		{
			NBTTagCompound nbt = ((GunAimableSkin) stack.getItem()).checkNBTTags(stack);
			if (nbt.getBoolean("ADS")) 
			{
				event.setNewfov(0.5F);
            }
		}
		
		if(stack.getItem() instanceof GunAimable)
		{
			NBTTagCompound nbt2 = ((GunAimable) stack.getItem()).checkNBTTags(stack);
			if (nbt2.getBoolean("ADS")) {
                event.setNewfov(0.3F);
            }
		}
	}
}
