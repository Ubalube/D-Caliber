package com.ubalube.scifiaddon.proxy;

import com.ubalube.scifiaddon.client.gui.FactionViewer;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;

public class CommonProxy 
{
	public void registerItemRender(Item item, int meta, String id) {}
	
	public void registerVariantRenderer(Item item, int meta, String filename, String id) {}
	
	public void openFactions()
	{
		Minecraft.getMinecraft().displayGuiScreen(new FactionViewer());
	}
	
}
