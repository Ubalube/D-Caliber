package com.ubalube.scifiaddon.world.biomes;

import com.ubalube.scifiaddon.entity.EntitySoldier;
import com.ubalube.scifiaddon.init.ModBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class BiomeBadlands extends Biome
{
	public BiomeBadlands()
	{
		super(new BiomeProperties("Badlands").setBaseHeight(-0.1F).setHeightVariation(0.0F).setRainDisabled().setTemperature(8.0F));
		
		topBlock = Blocks.SANDSTONE.getDefaultState();
		fillerBlock = Blocks.STONE.getDefaultState();
		
		this.decorator.ironGen = new WorldGenMinable(ModBlocks.STEELORE.getDefaultState(), 5);
		
		this.decorator.treesPerChunk = 0;
		this.getSkyColorByTemp(8.0F);
		
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		
		this.spawnableCreatureList.add(new SpawnListEntry(EntitySoldier.class, 10, 2, 4));
		
	}
}
