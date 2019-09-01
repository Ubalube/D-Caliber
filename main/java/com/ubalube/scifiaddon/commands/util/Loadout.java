package com.ubalube.scifiaddon.commands.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class Loadout 
{
	public String loadoutName; 
	public ItemStack Primary;
	public ItemStack Secondary;
	public List<ItemStack> items = new ArrayList<>();
}
