package com.ubalube.scifiaddon.proxy;

import com.ubalube.scifiaddon.BooleanHelper;
import com.ubalube.scifiaddon.client.gui.Crosshair;
import com.ubalube.scifiaddon.client.gui.FactionViewer;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy 
{
	public void registerItemRender(Item item, int meta, String id) {}
	
	public void registerVariantRenderer(Item item, int meta, String filename, String id) {}
	
	public void openFactions()
	{
		Minecraft.getMinecraft().displayGuiScreen(new FactionViewer());
	}
	
	public EntityPlayer getPlayer() {
        return null;    
    }
	
}
