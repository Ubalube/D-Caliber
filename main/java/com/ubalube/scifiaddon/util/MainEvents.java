package com.ubalube.scifiaddon.util;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.items.GunBase;
import com.ubalube.scifiaddon.util.packets.MessageReloadGun;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MainEvents 
{
	@SubscribeEvent
	public static void doRespawnEvent(PlayerRespawnEvent e)
	{
		EntityPlayer p = (EntityPlayer) e.player;
		main.proxy.OpenLoadoutGUI(p);
	}
	
}