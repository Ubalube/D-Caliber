package com.ubalube.scifiaddon.util.handlers;

import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundHandler 
{
	public static SoundEvent GUN_PISTOL_SHOOT, GUN_RIFLE_SHOOT, GUN_SNIPER_SHOOT, RELOAD_FIREMODE, RELOAD_PISTOLRELOAD, RELOAD_RIFLERELOAD, G17_SHOOT, ELCOVERT_SHOOT;
	
	public static void registerSounds()
	{
		GUN_PISTOL_SHOOT = registerSound("gun.pistol.shoot");
		GUN_RIFLE_SHOOT = registerSound("gun.rifle.shoot");
		GUN_SNIPER_SHOOT = registerSound("gun.sniper.shoot");
		
		//SPECIFIC
		G17_SHOOT = registerSound("gun.g17shoot");
		ELCOVERT_SHOOT = registerSound("gun.elcovertshoot");
		
		//Reload
		RELOAD_FIREMODE = registerSound("reload.firemode");
		RELOAD_PISTOLRELOAD = registerSound("reload.pistolreload");
		RELOAD_RIFLERELOAD = registerSound("reload.riflereload");
	}
	
public static SoundEvent registerSound(String name) {
		
		ResourceLocation location = new ResourceLocation(Reference.MOD_ID, name);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
	
}
