package com.ubalube.scifiaddon.tabs;

import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class Parts extends CreativeTabs
{
	public Parts() {
		super("Parts");
	}

	@Override
	public ItemStack getTabIconItem() 
	{
		return new ItemStack(ModItems.RIFLECALIBERCARBON);
	}
}
