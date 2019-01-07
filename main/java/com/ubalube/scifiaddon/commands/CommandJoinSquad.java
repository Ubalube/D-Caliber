package com.ubalube.scifiaddon.commands;

import java.util.List;

import com.google.common.collect.Lists;
import com.ubalube.scifiaddon.commands.util.SquadManager;
import com.ubalube.scifiaddon.squads.SquadMain;
import com.ubalube.scifiaddon.squads.SquadProvider;
import com.ubalube.scifiaddon.util.Reference;
import com.ubalube.scifiaddon.util.packets.SquadStorage;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class CommandJoinSquad extends CommandBase
{
	
	private final List<String> aliases = Lists.newArrayList(Reference.MOD_ID, "jf", "joinf", "jfaction");
	
	@Override
	public String getName() {
		return "joinFaction";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "joinFaction <faction>";
	}
	
	@Override
	public List<String> getAliases() {
		return aliases;
	}
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(args.length < 1) return;
		
		String s = args[0];
		String teamName = s;
		
		if(sender instanceof EntityPlayer)
		{
			SquadManager.joinSquad((EntityPlayer) sender, teamName);
		}
		
	}
	
}
