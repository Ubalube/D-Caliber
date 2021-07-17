package com.ubalube.scifiaddon.world.gen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.ubalube.scifiaddon.init.ModBlocks;
import com.ubalube.scifiaddon.world.biomes.BiomeBadlands;

import net.minecraft.block.Block;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.biome.BiomeMushroomIsland;
import net.minecraft.world.biome.BiomeOcean;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.biome.BiomeSavanna;
import net.minecraft.world.biome.BiomeSnow;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCustomStructures implements IWorldGenerator
{
	//public static final WorldGenStructures RIG = new WorldGenStructures("oilrig");
	//public static final WorldGenStructures RIGSTANDS = new WorldGenStructures("oilrigstands");
	public static final WorldGenStructures CHECKPOINT = new WorldGenStructures("checkpoint");
	public static final WorldGenStructures DUNE = new WorldGenStructures("dune");
	public static final WorldGenStructures LAB = new WorldGenStructures("laboratory");
	public static final WorldGenStructures LEGION = new WorldGenStructures("legion_1");
	public static final WorldGenStructures LEGIONBOSSROOM = new WorldGenStructures("legionbossroom");
	public static final WorldGenStructures UNDERGROUND = new WorldGenStructures("undergroundlab");

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch(world.provider.getDimension())
		{
		
		case 2:
			
			break;
		
		case 1:
			
			break;
			
		case 0:

			//generateStructureOcean(new WorldGenStructures("oilrig"), world, random, chunkX, chunkZ, 500, Blocks.WATER, BiomeOcean.class);
			generateStructure(new WorldGenStructures("checkpoint"), world, random, chunkX, chunkZ, 220, Blocks.GRASS, BiomeSavanna.class);
			generateStructure(new WorldGenStructures("dune"), world, random, chunkX, chunkZ, 470, Blocks.SAND, BiomeDesert.class);
			generateStructure(new WorldGenStructures("laboratory"), world, random, chunkX, chunkZ, 470, Blocks.GRASS, BiomeSnow.class);
			generateStructure(new WorldGenStructures("legion_1"), world, random, chunkX, chunkZ, 470, Blocks.GRASS, BiomePlains.class);
			generateStructure(new WorldGenStructures("legionbossroom"), world, random, chunkX, chunkZ, 200, Blocks.MYCELIUM, BiomeMushroomIsland.class);
			generateStructureUnderground(new WorldGenStructures("undergroundlab"), world, random, chunkX, chunkZ, 400, Blocks.GRASS, BiomeForest.class);
			
			break;
			
		case -1:
			
			break;
		}
		
	}
	
	private void generateStructureUnderground(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes)
	{
		ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));
		
		int x = (chunkX * 16) + random.nextInt(15);
		int z = (chunkZ * 16) + random.nextInt(15);
		int y = calculateGenerationHeight(world, x, z, topBlock);
		
		if(world.getWorldType() != WorldType.FLAT)
		{
			y = y - 25;
		}
		
		
		BlockPos pos = new BlockPos(x,y,z);
		
		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
		
		
		if(classesList.contains(biome))
		{
			if(random.nextInt(chance) == 0)
			{
				generator.generate(world, random, pos);
			}
		}
		
	}
	
	private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes)
	{
		ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));
		
		int x = (chunkX * 16) + random.nextInt(15);
		int z = (chunkZ * 16) + random.nextInt(15);
		int y = calculateGenerationHeight(world, x, z, topBlock);
		
		BlockPos pos = new BlockPos(x,y,z);
		
		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
		
		if(world.getWorldType() == WorldType.FLAT)
		{
			y = y + 1;
		}
		
		if(classesList.contains(biome))
		{
			if(random.nextInt(chance) == 0)
			{
				generator.generate(world, random, pos);
			}
		}
		
	}
	
	private static int calculateGenerationHeight(World world, int x, int z, Block topBlock)
	{
		int y = world.getHeight();
		boolean foundGround = false;
		
		while(!foundGround && y-- >= 0)
		{
			Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
			foundGround = block == topBlock;
		}
		
		return y;
	}
	
	/*private void generateStructureOcean(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes)
	{
		ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));
		
		int x = (chunkX * 16) + random.nextInt(15);
		int z = (chunkZ * 16) + random.nextInt(15);
		int y = calculateOceanGenerationHeight(world, x, z, topBlock);
		
		BlockPos pos = new BlockPos(x,y,z);
		
		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
		
		if(world.getWorldType() != WorldType.FLAT)
		{
			if(classesList.contains(biome))
			{
				if(random.nextInt(chance) == 0)
				{
					generator.generate(world, random, pos);
					generateRigLegs(RIGSTANDS, world, x, y - 3, z, random);
				}
			}
		}
		
	}
	
	private static void generateRigLegs(WorldGenerator legs, World world, int x, int y, int z, Random rand)
	{
		boolean foundGround = false;
		while(!foundGround && y-- >= 0)
		{
			BlockPos pos = new BlockPos(x, y, z);
			Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
			legs.generate(world, null, pos);

			List<Block> blocksAround = new ArrayList<>();
			blocksAround.clear();
			int nonWaterBlockAmount = 0;
			//Get Blocks in X
			
			//Get Blocks in Z
			for(int x1 = 0; x1 >= 32; x1++)
			{
				for(int z1 = 0; z1 >= 32; z1++)
				{
					BlockPos pos1 = new BlockPos(x1, y, z1);
					if(world.getBlockState(pos1).getBlock() == Blocks.WATER)
					{
						blocksAround.add(block);
					}
					else
					{
						nonWaterBlockAmount++;
					}
				}
			}
			
			int waterBlocks = blocksAround.size();
			
			foundGround = waterBlocks <= nonWaterBlockAmount;
		}
	}

	
	private static int calculateOceanGenerationHeight(World world, int x, int z, Block topBlock)
	{
		int y = world.getHeight();
		boolean foundGround = false;
		
		while(!foundGround && y-- >= 0)
		{
			Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
			foundGround = block == topBlock;
			BlockPos pos = new BlockPos(x, y, z);
			BlockPos underpos = new BlockPos(x, y - 1, z);
			if(world.getBlockState(underpos).getBlock() == Blocks.WATER && world.getBlockState(pos).getBlock() == Blocks.AIR)
			{
				y = y - 2;
				foundGround = true;
				break;
			}
		}
		
		return y;
	}*/
	
}
