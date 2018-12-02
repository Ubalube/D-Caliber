package com.ubalube.scifiaddon.tabs;

import com.ubalube.scifiaddon.init.ModBlocks;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class Decor extends CreativeTabs
{
	public Decor() {
		super("Decoration");
	}

	@Override
	public ItemStack getTabIconItem() 
	{
		return new ItemStack(ModBlocks.BARBEDWIRE);
	}
}
