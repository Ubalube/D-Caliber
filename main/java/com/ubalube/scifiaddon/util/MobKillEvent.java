package com.ubalube.scifiaddon.util;

import javax.swing.text.html.parser.Entity;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.entity.EntitySoldier;
import com.ubalube.scifiaddon.squads.SquadProvider;
import com.ubalube.scifiaddon.util.packets.ISquad;
import com.ubalube.scifiaddon.util.packets.MessageReputation;
import com.ubalube.scifiaddon.util.packets.MessageSquad;

import net.minecraft.block.BlockNote;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MobKillEvent 
{
	@SubscribeEvent
	public void AllianceEnder(LivingDeathEvent e)
	{
		DamageSource source = e.getSource();
		Minecraft mc = Minecraft.getMinecraft();
		
		if(source.getTrueSource() instanceof EntityPlayer)
		{
			//Squad IDS: 0 = None, 1 = Scorpions, 2 = Ghost, 3 = Bandits, 4 = Militia
			
			EntityPlayer p = ((EntityPlayer) source.getTrueSource());
			ISquad squad = p.getCapability(SquadProvider.SQUAD, null);
			//Factions
			if(e.getEntityLiving() instanceof EntitySoldier)
			{
				if(squad.getSquadByID() == 4)
				{
					if(squad.getSquadRep() <= 0)
					{
						squad.leaveSquad();
						if(!p.world.isRemote)
						{
							p.sendMessage(new TextComponentString(TextFormatting.RED + "You have broken your alliance!"));
						}
					}
					else
					{
						squad.setSquadRep(squad.getSquadRep() - 25);
						if(!p.world.isRemote)
						{
							p.sendMessage(new TextComponentString(TextFormatting.RED + "-25 Rep for attacking Allied Soldier"));
						}
					}
				}
			}
		}
	}
}
