package com.ubalube.scifiaddon.client.gui.slot;

import com.ubalube.scifiaddon.tileentity.TileEntityWorkshop;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotFuel extends Slot
{
	public SlotFuel(IInventory inventory, int index, int x, int y) 
	{
		
		
		
		super(inventory, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) 
	{
		return TileEntityWorkshop.isItemFuel(stack);
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack)
	{
		return super.getItemStackLimit(stack);
	}
}
