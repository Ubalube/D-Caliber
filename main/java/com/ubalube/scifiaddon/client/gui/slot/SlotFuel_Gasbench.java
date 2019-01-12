package com.ubalube.scifiaddon.client.gui.slot;

import com.ubalube.scifiaddon.tileentity.TileEntityGasbench;
import com.ubalube.scifiaddon.tileentity.TileEntitySkinner;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotFuel_Gasbench extends Slot
{
	public SlotFuel_Gasbench(IInventory inventory, int index, int x, int y) 
	{
		
		
		
		super(inventory, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) 
	{
		return TileEntityGasbench.isItemFuel(stack);
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack)
	{
		return super.getItemStackLimit(stack);
	}
}
