package com.ubalube.scifiaddon.tabs;

import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class Objects extends CreativeTabs
{
	public Objects() {
		super("Object");
	}

	@Override
	public ItemStack getTabIconItem() 
	{
		return new ItemStack(ModItems.ICON);
	}
}
