package com.ubalube.scifiaddon.world.types;

import com.ubalube.scifiaddon.init.BiomeInit;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;

public class WorldTypeBadlands extends WorldType
{
	public WorldTypeBadlands()
	{
		super("Badlands");
	}
	
	@Override
	public BiomeProvider getBiomeProvider(World world) {
		return new BiomeProviderSingle(BiomeInit.BADLANDS);
	}
	
}
