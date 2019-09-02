package com.ubalube.scifiaddon.util.Player.util;

import com.ubalube.scifiaddon.commands.util.Loadout;

import net.minecraft.item.ItemStack;

public interface ILoadout {
	public void setPrimary(ItemStack weapon);
	public void setSecondary(ItemStack weapon);
	
	public void setLoadoutID(int ID);
	public int getLoadoutID();
	
	public ItemStack getPrimary();
	public ItemStack getSecondary();
	
}
