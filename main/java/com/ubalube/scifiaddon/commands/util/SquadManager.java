package com.ubalube.scifiaddon.commands.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces.Igloo;

public class SquadManager
{
	
	//Create
	public static void create(String name, EntityPlayer p)
	{
		
		if(!p.getEntityData().hasKey("team"))
		{
			p.getEntityData().setString("team", name);
		}
		else
		{
			if(p.getEntityData().getString("team") != null)
			{
				p.sendMessage(new TextComponentString(TextFormatting.RED + "You are already in a squad! Type /leavesquad to leave the squad!"));
				return;
			}
			else
			{
				if(!p.getEntityWorld().isRemote)
				{
					p.sendMessage(new TextComponentString(TextFormatting.AQUA + "Squad " + TextFormatting.YELLOW + p.getEntityData().getString("team") + TextFormatting.AQUA + " has been created!"));
				}
			}
		}
	}
	
	//Leave
	public static void leave(EntityPlayer p)
	{
		
		if(p.getEntityData().getString("team") == null)
		{
			p.sendMessage(new TextComponentString(TextFormatting.RED + "You are not in a squad! Create one with /createsquad <name>!"));
		}
		else
		{
			p.sendMessage(new TextComponentString(TextFormatting.AQUA + "You have left " + TextFormatting.GOLD + p.getEntityData().getString("team")));
			p.getEntityData().setString("team", null);
		}
		
	}
	
	//Info
	public static void info(EntityPlayer p)
	{
		if(p.getEntityData().getString("team") == null)
		{
			p.sendMessage(new TextComponentString(TextFormatting.RED + "You are not in a squad!"));
		}
		else
		{
			p.sendMessage(new TextComponentString(TextFormatting.AQUA + "You are currently in " + TextFormatting.GOLD + p.getEntityData().getString("team")));
		}
	}
	
}
