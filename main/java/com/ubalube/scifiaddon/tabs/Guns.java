package com.ubalube.scifiaddon.tabs;

import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class Guns extends CreativeTabs
{
	public Guns() {
		super("Weapons");
	}

	@Override
	public ItemStack getTabIconItem() 
	{
		return new ItemStack(ModItems.VECTOR);
	}
}
