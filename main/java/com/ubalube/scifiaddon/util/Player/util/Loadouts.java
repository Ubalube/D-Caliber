package com.ubalube.scifiaddon.util.Player.util;

import net.minecraft.item.ItemStack;

public class Loadouts implements ILoadout {

	
	private ItemStack Primary, Secondary;
	private int loadoutID;
	@Override
	public void setPrimary(ItemStack weapon) {
		this.Secondary = weapon;
	}
	@Override
	public void setSecondary(ItemStack weapon) {
		this.Primary = weapon;
	}
	@Override
	public void setLoadoutID(int ID) {
		this.loadoutID = ID;
	}
	@Override
	public int getLoadoutID() {
		return this.loadoutID;
	}
	@Override
	public ItemStack getPrimary() {
		return this.Primary;
	}
	@Override
	public ItemStack getSecondary() {
		return this.Secondary;
	}
}
