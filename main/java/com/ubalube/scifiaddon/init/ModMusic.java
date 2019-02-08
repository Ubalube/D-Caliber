package com.ubalube.scifiaddon.init;

import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(Reference.MOD_ID)
public class ModMusic {
	
	public static final SoundEvent HOSTILE_LANDS = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "hostile_lands")).setRegistryName(new ResourceLocation(Reference.MOD_ID, "hostile_lands"));
	public static final SoundEvent CRYSIS = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "crysis")).setRegistryName(new ResourceLocation(Reference.MOD_ID, "crysis"));
	
	
	@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
	public static class SoundEventRegistration {
		@SubscribeEvent
		public static void registerSoundEvents(final RegistryEvent.Register<SoundEvent> event) {
			final SoundEvent[] sounds = {
					HOSTILE_LANDS,
					CRYSIS
			};
			
			event.getRegistry().registerAll(sounds);
		}
	}
}
