package com.ubalube.scifiaddon.commands;

import java.util.List;

import com.google.common.collect.Lists;
import com.ubalube.scifiaddon.commands.util.SquadManager;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class CommandSquadInfo extends CommandBase
{
	
	private final List<String> aliases = Lists.newArrayList(Reference.MOD_ID, "si", "sinfo", "squadi");
	
	@Override
	public String getName() {
		return "squadinfo";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "squadinfo";
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

		
		if(sender instanceof EntityPlayer)
		{
			SquadManager.info((EntityPlayer)sender);
		}
		
	}
	
}
