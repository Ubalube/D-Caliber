package com.ubalube.scifiaddon.tabs;

import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class Armor extends CreativeTabs
{
	public Armor() {
		super("Gear");
	}

	@Override
	public ItemStack getTabIconItem() 
	{
		return new ItemStack(ModItems.RANGER_HELMET);
	}
}
