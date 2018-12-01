package com.ubalube.scifiaddon.items;

import java.util.Random;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.IHasModel;
import com.ubalube.scifiaddon.util.handlers.SoundHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class Packages extends Item implements IHasModel
{
	
	public Packages(String name, int StackSize, CreativeTabs tab) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		setMaxStackSize(StackSize);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.MASTER, 1, 1);
		
		if(!worldIn.isRemote)
		{
			if(itemstack == new ItemStack(ModItems.ALPHA))
			{
				Random r = new Random();
				int n = r.nextInt(2);
				switch(n)
				{
				case 1:
					playerIn.inventory.addItemStackToInventory(new ItemStack(ModItems.VECTOR));
					break;
					
				case 2:
					playerIn.inventory.addItemStackToInventory(new ItemStack(ModItems.RIFLECALIBER));
					break;
					
				default:
					playerIn.inventory.addItemStackToInventory(new ItemStack(ModItems.VECTOR));
					break;
				}
			}
			
			if(itemstack == new ItemStack(ModItems.BETA))
			{
				Random r = new Random();
				int n = r.nextInt(2);
				switch(n)
				{
				case 1:
					playerIn.inventory.addItemStackToInventory(new ItemStack(ModItems.TEC9));
					break;
					
				case 2:
					playerIn.inventory.addItemStackToInventory(new ItemStack(ModItems.PISTOLCALIBER));
					break;
					
				default:
					playerIn.inventory.addItemStackToInventory(new ItemStack(ModItems.TEC9));
					break;
				}
			}
			
			if(itemstack == new ItemStack(ModItems.ELITE))
			{
				Random r = new Random();
				int n = r.nextInt(2);
				switch(n)
				{
				case 1:
					playerIn.inventory.addItemStackToInventory(new ItemStack(ModItems.FAMAS));
					break;
					
				case 2:
					playerIn.inventory.addItemStackToInventory(new ItemStack(ModItems.RIFLECALIBERCARBON));
					break;
					
				default:
					playerIn.inventory.addItemStackToInventory(new ItemStack(ModItems.FAMAS));
					break;
				}
			}
			
			if(itemstack == new ItemStack(ModItems.BRAVO))
			{
				Random r = new Random();
				int n = r.nextInt(2);
				switch(n)
				{
				case 1:
					playerIn.inventory.addItemStackToInventory(new ItemStack(ModItems.RIFLECALIBER));
					break;
					
				case 2:
					playerIn.inventory.addItemStackToInventory(new ItemStack(ModItems.SHOTBARREL));
					break;
					
				default:
					playerIn.inventory.addItemStackToInventory(new ItemStack(ModItems.RIFLECALIBER));
					break;
				}
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public void registerModels() 
	{
		main.proxy.registerItemRender(this, 0, "inventory");
		
	}
	

}
