package com.ubalube.scifiaddon.util;

import com.google.common.eventbus.Subscribe;
import com.ubalube.scifiaddon.main;

import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LoadoutEvent 
{
	@SubscribeEvent
	public void showLoadout(PlayerRespawnEvent e)
	{
		World worldIn = e.player.getEntityWorld();
		WorldServer sworldIn = e.player.getServer().getWorld(e.player.getSpawnDimension());
		
		if(e.player.isServerWorld())
		{
			if(sworldIn.getGameRules().getBoolean("classes") == true)
			{
				main.proxy.openLoadoutGUI(e.player);
			}
		}
		else
		{
			if(worldIn.getGameRules().getBoolean("classes") == true)
			{
				main.proxy.openLoadoutGUI(e.player);
			}
		}
	}
}
