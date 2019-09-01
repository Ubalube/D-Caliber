package com.ubalube.scifiaddon.commands;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.commands.util.Loadout;
import com.ubalube.scifiaddon.commands.util.LoadoutSlots;
import com.ubalube.scifiaddon.data.LoadoutData;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class CommandLoadout extends CommandBase
{

	@Override
	public String getName() {
		return "loadout";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "loadout primary <loadout>";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(args.length < 2)
		{
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "You must define either primary or secondary and define a loadout!"));
			return;
		}
		
		String loadout = args[0];
		String loadoutselected = args[1];
		
		if(!loadout.equalsIgnoreCase("primary") || !loadout.equalsIgnoreCase("secondary"))
		{
			sender.sendMessage(new TextComponentString(TextFormatting.RED + args[0] + " is not a valid slot! Options: Primary/Secondary"));
			return;
		}
		EntityPlayer p = (EntityPlayer)sender;
		Loadout l = new Loadout();
		if(loadout.equalsIgnoreCase("primary"))
		{
			l.Primary = p.getHeldItemMainhand();
		}

		if(loadout.equalsIgnoreCase("secondary"))
		{
			l.Secondary = p.getHeldItemMainhand();
		}
		
		LoadoutSlots slot = main.loadoutData.getLoadoutSlotByName(args[0]);
		main.loadoutData.setLoadout(slot, l);
		sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Successfully updated loadout " + args[0]));
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		List<String> SlotCompletions = new ArrayList<>();
		List<String> LoadoutCompletions = new ArrayList<>();
		if(args.length == 1)
		{
			SlotCompletions.add("Primary");
			SlotCompletions.add("Secondary");
			return SlotCompletions;
		}
		
		if(args.length == 2)
		{
			for(Loadout l : main.loadoutData.getLoadouts())
			{
				LoadoutCompletions.add(l.loadoutName);
			}
			return LoadoutCompletions;
		}
		
		return super.getTabCompletions(server, sender, args, targetPos);
	}
}
