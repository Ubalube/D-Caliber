package com.ubalube.scifiaddon;

import com.ubalube.scifiaddon.items.CGunAimingBase;
import com.ubalube.scifiaddon.items.CGunBase;
import com.ubalube.scifiaddon.items.CGunHelper;
import com.ubalube.scifiaddon.items.CGunPDW;
import com.ubalube.scifiaddon.items.CGunPistol;
import com.ubalube.scifiaddon.items.CGunSkinnable;
import com.ubalube.scifiaddon.items.CGunSniper;

import net.minecraft.entity.player.EntityPlayer;

public class BooleanHelper 
{
	public static boolean isHoldingGun(EntityPlayer player)
	{
		
        if (player.getHeldItemMainhand().getItem() instanceof CGunBase || player.getHeldItemMainhand().getItem() instanceof CGunAimingBase ||
        		player.getHeldItemMainhand().getItem() instanceof CGunHelper || player.getHeldItemMainhand().getItem() instanceof CGunPDW ||
        		player.getHeldItemMainhand().getItem() instanceof CGunSniper || player.getHeldItemMainhand().getItem() instanceof CGunPistol || 
        		player.getHeldItemMainhand().getItem() instanceof CGunSniper || player.getHeldItemMainhand().getItem() instanceof CGunPistol ||
        		player.getHeldItemMainhand().getItem() instanceof CGunSkinnable)
        {
        	return true;
        }
        else
        {

            return false;
        }
		
	}
}
