package com.ubalube.scifiaddon.util;

import javax.swing.text.html.parser.Entity;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.entity.EntitySoldier;
import com.ubalube.scifiaddon.items.GunBase;
import net.minecraft.block.BlockNote;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemPickupEvent;

public class GunNBTEvent 
{
	
	@SubscribeEvent
	public void GunNBTFix(ItemPickupEvent e)
	{
		ItemStack stack = e.getStack();
		NBTTagCompound nbt = stack.getTagCompound();
        if (nbt == null) {
            nbt = new NBTTagCompound();
            stack.setTagCompound(nbt);
        }
		
		if(e.getStack().getItem() instanceof GunBase)
		{
			
			if(stack.getItemDamage() == stack.getMaxDamage())
			{
		        nbt.setBoolean("reload", true);
			}
		}
		else
		{
			nbt.setBoolean("reload", false);
		}
	}
}
