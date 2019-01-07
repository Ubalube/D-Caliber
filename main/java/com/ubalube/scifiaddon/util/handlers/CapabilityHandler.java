package com.ubalube.scifiaddon.util.handlers;

import com.ubalube.scifiaddon.squads.SquadMain;
import com.ubalube.scifiaddon.squads.SquadProvider;
import com.ubalube.scifiaddon.util.Reference;
import com.ubalube.scifiaddon.util.packets.ISquad;
import com.ubalube.scifiaddon.util.packets.SquadStorage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler 
{
	public static final ResourceLocation TEAM = new ResourceLocation(Reference.MOD_ID, "teams");
	
	public static void register()
    {
        CapabilityManager.INSTANCE.register(ISquad.class, new SquadStorage(), SquadMain.class);
    }
	
	@SubscribeEvent 
	public void attachCapability(AttachCapabilitiesEvent event) 
	{
		if (!(event.getObject() instanceof EntityPlayer)) return; 

		event.addCapability(TEAM, new SquadProvider()); 
	}
}
