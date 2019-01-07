package com.ubalube.scifiaddon.blocks;

import java.time.format.TextStyle;

import org.apache.logging.log4j.message.StructuredDataMessage.Format;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.init.ModBlocks;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.squads.SquadProvider;
import com.ubalube.scifiaddon.util.IHasModel;
import com.ubalube.scifiaddon.util.packets.ISquad;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class BlockSquadTable extends Block implements IHasModel
{
	public BlockSquadTable(String name, Material material)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		
		ISquad squad = playerIn.getCapability(SquadProvider.SQUAD, null);
		
		ItemStack handItem = playerIn.getHeldItem(hand);
		
		if(handItem == new ItemStack(ModItems.SUPPLIES1))
		{
			playerIn.inventory.clearMatchingItems(ModItems.SUPPLIES1, 0, 1, null);
			playerIn.addExperience(12);
			squad.setSquadRep(squad.getSquadRep() + 25);
			playerIn.sendMessage(new TextComponentString(TextFormatting.BLUE + "+ 25 Squad Rep"));
		}
		
		if(handItem == new ItemStack(ModItems.SUPPLIES2))
		{
			playerIn.inventory.clearMatchingItems(ModItems.SUPPLIES2, 0, 1, null);
			playerIn.addExperience(25);
			squad.setSquadRep(squad.getSquadRep() + 50);
			playerIn.sendMessage(new TextComponentString(TextFormatting.BLUE + "+ 50 Squad Rep"));
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
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
	public void registerModels()
	{
		main.proxy.registerItemRender(Item.getItemFromBlock(this), 0, "inventory");
	}
}
