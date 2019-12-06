package com.ubalube.scifiaddon.util.handlers;

import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTableRegistry 
{
	public static final ResourceLocation hostageLoot = register("loot/hostage/hostage"); 
	
	private static ResourceLocation register(String id) {
        return LootTableList.register(new ResourceLocation(Reference.MOD_ID, id));
	}
}
