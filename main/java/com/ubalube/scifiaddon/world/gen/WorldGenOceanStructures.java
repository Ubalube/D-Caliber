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
import net.minecraft.world.biome.BiomeMushroomIsland;
import net.minecraft.world.biome.BiomeOcean;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.biome.BiomeSavanna;
import net.minecraft.world.biome.BiomeSnow;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenOceanStructures implements IWorldGenerator
{
	public static final WorldGenStructures RIG = new WorldGenStructures("oilrig");
	public static final WorldGenStructures RIGSTANDS = new WorldGenStructures("oilrigstands");

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
			
			generateStructure(new WorldGenStructures("oilrig"), world, random, chunkX, chunkZ, 500, Blocks.WATER, BiomeOcean.class);
			
			break;
			
		case -1:
			
			break;
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
		
		if(world.getWorldType() != WorldType.FLAT)
		{
			if(classesList.contains(biome))
			{
				if(random.nextInt(chance) == 0)
				{
					generator.generate(world, random, pos);
					//generateRigLegs(new WorldGenStructures("oilrigstands"), world, pos.getX(), pos.getY(), pos.getZ(), random);
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

			/*List<Block> blocksAround = new ArrayList<>();
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
			
			int waterBlocks = blocksAround.size();*/
			
			legs.generate(world, rand, pos);
			
			if(block != Blocks.WATER)
			{
				foundGround = true;
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
	}
	
}
