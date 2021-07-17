package com.ubalube.scifiaddon.util;

import com.ubalube.scifiaddon.init.EntityInit;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.items.GunAimable;
import com.ubalube.scifiaddon.items.GunBase;
import com.ubalube.scifiaddon.vehicles.VehicleHumvee;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
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
		
		if(stack.getItem() instanceof GunBase)
		{
			NBTTagCompound nbt = ((GunBase) stack.getItem()).checkNBTTags(stack);
			if (nbt.getBoolean("ADS")) 
			{
				event.setNewfov(0.5F);
            }
		}
		EntityPlayer p = event.getEntity();
		World worldIn = p.world;
		
		if(p.isRiding() && p.getRidingEntity() == new VehicleHumvee(worldIn))
		{
			Entity vehicle = p.getRidingEntity();
			
			if(vehicle.isSprinting())
			{
				event.setNewfov(1.2F);
			}
		}
	}
}
