package com.ubalube.scifiaddon.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class CommonProxy 
{
	public void registerItemRender(Item item, int meta, String id) {}
	
	public void registerVariantRenderer(Item item, int meta, String filename, String id) {}
	
	public void OpenLoadoutGUI(EntityPlayer player) {}
	
}
