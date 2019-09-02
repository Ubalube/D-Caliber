package com.ubalube.scifiaddon.util;

import com.ubalube.scifiaddon.util.Player.LoadoutProvider;
import com.ubalube.scifiaddon.util.Player.util.ILoadout;
import com.ubalube.scifiaddon.util.Player.util.LoadoutStorage;
import com.ubalube.scifiaddon.util.Player.util.Loadouts;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class CapabilityHandler 
{
	public static final ResourceLocation LOADOUTS = new ResourceLocation(Reference.MOD_ID, "loadouts");
	
	public static void register() {
        CapabilityManager.INSTANCE.register(ILoadout.class, new LoadoutStorage(), Loadouts.class);
    }
	
	@SubscribeEvent 
	public static void attachCapability(AttachCapabilitiesEvent event) {
		if (!(event.getObject() instanceof EntityPlayer)) return;
		event.addCapability(LOADOUTS, new LoadoutProvider());
	}
}
