package com.ubalube.scifiaddon.init;

import com.ubalube.scifiaddon.world.biomes.BiomeBadlands;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BiomeInit 
{
	
	public static final Biome BADLANDS = new BiomeBadlands();
	
	public static void registerBiomes()
	{
		initBiome(BADLANDS, "badlands", BiomeType.DESERT, Type.DEAD, Type.HOT, Type.WASTELAND);
	}
	
	private static Biome initBiome(Biome biome, String name, BiomeType biomeType, Type... types)
	{
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 10));
		BiomeManager.addSpawnBiome(biome);
		
		System.out.println("[DIAMOND CALIBER] Biomes Registered!");
		
		return biome;
	}
	
}
