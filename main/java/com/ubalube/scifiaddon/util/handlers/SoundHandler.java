package com.ubalube.scifiaddon.util.handlers;

import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundHandler 
{
	public static SoundEvent GUN_PISTOL, GUN_RIFLE1, GUN_RIFLE2, GUN_SHOTGUN, GUN_SMG1, GUN_SMG2, GUN_SNIPER, GUN_STINGER;
	
	public static void registerSounds()
	{
		/*GUN_PISTOL_SHOOT = registerSound("gun.pistol.shoot");
		GUN_RIFLE_SHOOT = registerSound("gun.rifle.shoot");
		GUN_SNIPER_SHOOT = registerSound("gun.sniper.shoot");
		
		
		//SPECIFIC
		G17_SHOOT = registerSound("gun.glockshoot");
		//Reload
		RELOAD_FIREMODE = registerSound("reload.firemode");
		RELOAD_PISTOLRELOAD = registerSound("reload.pistolreload");
		RELOAD_RIFLERELOAD = registerSound("reload.riflereload");*/
		
		GUN_PISTOL = registerSound("gun.pistol");
		GUN_RIFLE1 = registerSound("gun.rifle1");
		GUN_RIFLE2 = registerSound("gun.rifle2");
		GUN_SHOTGUN = registerSound("gun.shotgun");
		GUN_SMG1 = registerSound("gun.smg1");
		GUN_SMG2 = registerSound("gun.smg2");
		GUN_SNIPER = registerSound("gun.sniper");
		GUN_STINGER = registerSound("gun.stinger");
		
	}
	
public static SoundEvent registerSound(String name) {
		
		ResourceLocation location = new ResourceLocation(Reference.MOD_ID, name);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
	
}
