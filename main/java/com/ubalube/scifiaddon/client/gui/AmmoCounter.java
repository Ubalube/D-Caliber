package com.ubalube.scifiaddon.client.gui;

import com.ubalube.scifiaddon.items.CGunAimingBase;
import com.ubalube.scifiaddon.items.CGunBase;
import com.ubalube.scifiaddon.items.CGunGrenadeLauncher;
import com.ubalube.scifiaddon.items.CGunHelper;
import com.ubalube.scifiaddon.items.CGunPDW;
import com.ubalube.scifiaddon.items.CGunPistol;
import com.ubalube.scifiaddon.items.CGunSkinnable;
import com.ubalube.scifiaddon.items.CGunSniper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class AmmoCounter extends Gui
{
	public AmmoCounter(Minecraft mc)
	{
		ScaledResolution scaled = new ScaledResolution(mc);
		int width = scaled.getScaledWidth();
		int height = scaled.getScaledHeight();
		
		EntityPlayer p = mc.player;
		
		if (p.getHeldItemMainhand().getItem() instanceof CGunBase || p.getHeldItemMainhand().getItem() instanceof CGunAimingBase ||
        		p.getHeldItemMainhand().getItem() instanceof CGunHelper || p.getHeldItemMainhand().getItem() instanceof CGunPDW ||
        		p.getHeldItemMainhand().getItem() instanceof CGunSniper || p.getHeldItemMainhand().getItem() instanceof CGunPistol ||
        		p.getHeldItemMainhand().getItem() instanceof CGunSkinnable || p.getHeldItemMainhand().getItem() instanceof CGunGrenadeLauncher) 
        {
			
			
			
			int currentAmmo = p.getHeldItemMainhand().getMaxDamage() - p.getHeldItemMainhand().getItemDamage();
			int maxAmmo = p.getHeldItemMainhand().getMaxDamage();
			
			int color;
			
			if(currentAmmo == currentAmmo / 2)
			{
				color = 16711680;
			}
			else
			{
				color = 16777215;
			}
			if(p.capabilities.isCreativeMode)
			{
				drawString(mc.fontRenderer, "∞", 10, 10, color);
			}
			else
			{
				drawString(mc.fontRenderer, p.getHeldItemMainhand().getMaxDamage() - p.getHeldItemMainhand().getItemDamage() + "/" + p.getHeldItemMainhand().getMaxDamage(), 10, 10, color);
			}
			
			
			
        }
		
	}
}
