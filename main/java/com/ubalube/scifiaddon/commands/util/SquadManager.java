package com.ubalube.scifiaddon.commands.util;

import com.ubalube.scifiaddon.squads.SquadProvider;
import com.ubalube.scifiaddon.util.packets.ISquad;

import net.minecraft.client.model.ModelShield;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces.Igloo;
import net.minecraftforge.common.capabilities.Capability;

public class SquadManager
{
	
	//Info
	public static void info(EntityPlayer p)
	{
		ISquad squad = p.getCapability(SquadProvider.SQUAD, null);
		int ID = squad.getSquadByID();
		String squadName = null;
		switch(ID)
		{
		case 0:
			squadName = null;
			break;
		case 1:
			squadName = "Ghost";
			break;
		case 2:
			squadName = "Bandit";
			break;
		case 3:
			squadName = "Milita";
			break;
		case 4:
			squadName = "Scorpions";
			break;
		default:
			squadName = null;
			break;
		}
		
		if(!p.world.isRemote)
		{
			p.sendMessage(new TextComponentString(TextFormatting.GREEN + "You are currently in the " + squadName + " with a reputation of " + squad.getSquadRep()));
		}
		
	}
	
	public static void joinSquad(EntityPlayer p, String faction)
	{
		ISquad squad = p.getCapability(SquadProvider.SQUAD, null);
		
		int ID = 0;
		boolean correctName;
		
		switch(faction)
		{
		case "ghost":
			ID = 1;
			correctName = true;
			break;
		case "bandit":
			ID = 2;
			correctName = true;
			break;
		case "milita":
			ID = 3;
			correctName = true;
			break;
		case "scorpion":
			ID = 4;
			correctName = true;
			break;
		default:
			correctName = false;
			break;
		}
		
		if(correctName != false)
		{
			if(squad.isInSquad() == false)
			{
				squad.joinSquad(ID);
				if(!p.world.isRemote)
				{
					p.sendMessage(new TextComponentString(TextFormatting.GREEN + "You have joined " + faction + "!"));
				}
			}
			else
			{
				if(p.experienceLevel >= 10)
				{
					squad.leaveSquad();
					squad.joinSquad(ID);
					if(!p.world.isRemote)
					{
						p.sendMessage(new TextComponentString(TextFormatting.GREEN + "You have left your current faction and joined " + faction + "!"));
						p.addExperienceLevel(-10);
					}
				}
				else
				{
					if(!p.world.isRemote)
					{
						p.sendMessage(new TextComponentString(TextFormatting.RED + "You need to atleast be level 10 in experience!"));
					}
				}
			}
		}
		else
		{
			if(!p.world.isRemote)
			{
				p.sendMessage(new TextComponentString(TextFormatting.RED + "[ERROR] " + faction + " is not a valid faction!"));
			}
		}
		
		
	}
	
}


