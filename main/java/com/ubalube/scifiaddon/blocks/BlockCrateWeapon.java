package com.ubalube.scifiaddon.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.init.ModBlocks;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCrateWeapon extends BlockHorizontal implements IHasModel
{
	public BlockCrateWeapon(String name, Material material)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(main.decorTab);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
        return i;
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }

    public EnumPushReaction getMobilityFlag(IBlockState state)
    {
        return EnumPushReaction.PUSH_ONLY;
    }
	
	public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		Random r = new Random();
		Item i = ModItems.STEEL;
		int result = r.nextInt(11);
		switch (result) {
		case 0:
			i = ModItems.FAMAS;
			break;
			
		case 1:
			i = ModItems.VECTOR;
			break;
			
		case 2:
			i = ModItems.TEC9;
			break;
			
		case 3:
			i = ModItems.VSS;
			break;

		case 4:
			i = ModItems.SPEC_HELMET;
			break;
			
		case 5:
			i = ModItems.SPEC;
			break;
			
		case 6:
			i = ModItems.SPEC_PANTS;
			break;
			
		case 7:
			i = ModItems.RANGER_CHEST;
			break;
			
		case 8:
			i = ModItems.RANGER_HELMET;
			break;
			
		case 9:
			i = ModItems.RANGER_PANTS;
			break;

		default:
			i = ModItems.SPEC_HELMET;
			break;
		}
		
		return i;
	}
	
    
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.GOLD;
    }
	
	@Override
	public void registerModels()
	{
		main.proxy.registerItemRender(Item.getItemFromBlock(this), 0, "inventory");
	}
	
}
