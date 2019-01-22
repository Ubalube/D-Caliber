package com.ubalube.scifiaddon.blocks;

import java.util.List;
import java.util.Random;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.init.ModBlocks;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockRadius extends Block implements IHasModel
{
	public BlockRadius(String name, Material material, float hardness)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(main.decorTab);
		setHardness(hardness);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	public double par2=5;
	public World world;
	
	public boolean playerInRange(BlockPos pos)
	{
		double xCoord = pos.getX();
		double yCoord = pos.getY();
		double zCoord = pos.getZ();
		World worldObj = this.world;
		AxisAlignedBB box = new AxisAlignedBB(xCoord-25,yCoord-25,zCoord-25,xCoord+25,yCoord+25,zCoord+25);
		List playerList = world.getEntitiesWithinAABB(EntityPlayer.class, box);
		EntityPlayer player = worldObj.getClosestPlayer(xCoord, yCoord, zCoord, 32, false);
		boolean isInRange = playerList.size() == 0;
		return isInRange;
	}
	
	public EntityPlayer getNearestPlayer(BlockPos pos)
	{
		double xCoord = pos.getX();
		double yCoord = pos.getY();
		double zCoord = pos.getZ();
		World worldObj = this.world;
		AxisAlignedBB box = new AxisAlignedBB(xCoord-25,yCoord-25,zCoord-25,xCoord+25,yCoord+25,zCoord+25);
		List playerList = world.getEntitiesWithinAABB(EntityPlayer.class, box);
		EntityPlayer p = worldObj.getClosestPlayer(xCoord, yCoord, zCoord, 32, false);
		return p;
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if(this.playerInRange(pos))
		{
			EntityPlayer p = this.getNearestPlayer(pos);
			p.capabilities.allowEdit = false;
		}
		super.updateTick(worldIn, pos, state, rand);
	}
	
	@Override
	public void registerModels()
	{
		main.proxy.registerItemRender(Item.getItemFromBlock(this), 0, "inventory");
	}
	
}
