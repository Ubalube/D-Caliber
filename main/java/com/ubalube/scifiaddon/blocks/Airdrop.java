package com.ubalube.scifiaddon.blocks;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.init.ModBlocks;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Airdrop extends BlockFalling implements IHasModel
{
	public Airdrop(String name, Material material)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public void registerModels()
	{
		main.proxy.registerItemRender(Item.getItemFromBlock(this), 0, "inventory");
	}
	
}
