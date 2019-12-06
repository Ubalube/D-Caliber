package com.ubalube.scifiaddon.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.util.WorldData;
import com.ubalube.scifiaddon.util.packets.MessageSaveData;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class CommandTeam extends CommandBase 
{

	@Override
	public String getName() {
		return "squad";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "squad <create,leave,help,invite,join,kick> <args>";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(sender instanceof EntityPlayer)
		{
			EntityPlayer theSender = (EntityPlayer) sender;
			WorldData data = WorldData.get(theSender.getEntityWorld());
			
			switch(args[0])
			{
				case "create":
					String name = args[1];
					if(data.teams.containsKey(name))
					{
						theSender.sendMessage(new TextComponentString(TextFormatting.RED + "The squad " + name + " already exists!"));
						return;
					}
					else
					{
						if(name.contains("!") || name.contains("?") || name.contains(">") || name.contains("<"))
						{
							theSender.sendMessage(new TextComponentString(TextFormatting.RED + "You can not use the following symbols in a squad name: !, ?, >, <"));
							return;
						}
					}
					
					if(data.team.containsKey(theSender.getUniqueID()))
					{
						theSender.sendMessage(new TextComponentString(TextFormatting.RED + "You are already in a squad: " + TextFormatting.GREEN + data.team.get(theSender.getUniqueID())));
						theSender.sendMessage(new TextComponentString(TextFormatting.RED + "You must leave that squad to make your own!"));
						return;
					}
					
					data.addNewTeam(name, theSender);
					theSender.sendMessage(new TextComponentString(TextFormatting.GREEN + "You have created a squad named: " + name));
					break;
				case "kick":
					try {
                        String playerName= args[1];
                        UUID uid = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerProfileCache().getGameProfileForUsername(playerName).getId();
                        if(data.team.containsKey(uid)) {
                            sender.sendMessage(new TextComponentString(TextFormatting.RED + playerName + " has been kicked from your squad!"));
                            data.removePlayer((EntityPlayer)sender,uid);
                        }
                    } catch (Exception ex) {
                        sender.sendMessage(new TextComponentString(TextFormatting.RED + "That player is offline or does not exist!"));
                    }
                    break;
				case "invite":
					try {
                        String playerName = args[1];
                        EntityPlayer newp = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(playerName);
                        EntityPlayer oldp = (EntityPlayer)sender;
                        UUID uuid = newp.getUniqueID();
                        String theteam = data.team.get(oldp.getUniqueID());
                        if(data.teams.get(theteam).contains(uuid)) {
                            sender.sendMessage(new TextComponentString(TextFormatting.RED + "That player is already in your squad!"));
                            return;
                        } else if (newp.getUniqueID().equals(oldp.getUniqueID())) {
                            sender.sendMessage(new TextComponentString(TextFormatting.RED + "You can't invite yourself to your own squad!"));
                            return;
                        }
                        newp.getEntityData().setString("invitedby",oldp.getUniqueID().toString());
                        oldp.sendMessage(new TextComponentString(TextFormatting.GREEN + "You have invited " + newp.getDisplayNameString() + " to your squad"));
                        newp.sendMessage(new TextComponentString(TextFormatting.GREEN + "You have been invited by " + oldp.getDisplayNameString() + " to join their squad. Type /squad join to join them!"));
                    } catch (Exception ex) {
                        sender.sendMessage(new TextComponentString(TextFormatting.RED + "Must enter an online player's username to invite"));
                    }
                    break;
				case "leave":
					try {
                        EntityPlayer p = (EntityPlayer)sender;
                        String toLeave = data.team.get(p.getUniqueID());
                        data.removePlayer(p,p.getUniqueID());
                        p.sendMessage(new TextComponentString(TextFormatting.GREEN + "Successfully left the squad!"));
                        for(UUID pla : data.teams.get(toLeave))
                        {
                        	p.getServer().getPlayerList().getPlayerByUUID(pla).sendMessage(new TextComponentString(TextFormatting.RED + p.getDisplayNameString() + " has left your clan!"));
                        }
                        if(data.teams.get(toLeave).isEmpty()) {
                            data.removeTeam(toLeave);
                        }
                    } catch (Exception ex) {
                        sender.sendMessage(new TextComponentString(TextFormatting.RED + "You're not in a squad"));
                    }
                    break;
				case "join":
                    EntityPlayer invitee = (EntityPlayer)sender;
                    EntityPlayerMP inviter = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(UUID.fromString(invitee.getEntityData().getString("invitedby")));
                    UUID uid = invitee.getUniqueID();
                    if(inviter==null) {
                        sender.sendMessage(new TextComponentString(TextFormatting.RED + "You have not been invited to a squad!"));
                        break;
                    }
                    else if (data.team.containsKey(uid)){
                        sender.sendMessage(new TextComponentString(TextFormatting.RED + "You were removed from your old squad!"));
                        data.removePlayer(invitee,uid);
                    }
                    if (data.team.containsKey(inviter.getUniqueID()) && inviter != null) {
                        String name1 = data.team.get(inviter.getUniqueID());
                    }
                    data.addPlayerToTeam(inviter, uid);
                    main.NETWORK.sendTo(new MessageSaveData(data.teams),(EntityPlayerMP)invitee);
                    sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Joined " + inviter.getDisplayNameString() + "'s squad!"));
                    inviter.sendMessage(new TextComponentString(TextFormatting.GREEN + invitee.getDisplayNameString() + " has joined your squad!"));
                    break;
				case "help":
					sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Diamond Caliber squad"));
					sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "/squad create <name> - creates a squad"));
					sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "/squad invite <playername> - invites a player to your squad"));
					sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "/squad kick <playername> - kicks a player from your squad"));
					sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "/squad join - joins the current squad invite"));
					sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "/squad leave - leaves the current squad"));
					sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "/squad help - shows this menu"));
					
					
			}
			
		}
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		List<String> tabCompletions = new ArrayList<>();
		
		if(args.length == 0)
		{
			tabCompletions.clear();
			tabCompletions.add("create");
			tabCompletions.add("invite");
			tabCompletions.add("kick");
			tabCompletions.add("join");
			tabCompletions.add("leave");
			tabCompletions.add("help");
		}
		else
		{
			tabCompletions.clear();
			if(args.length > 0)
			{
				for(String p : server.getOnlinePlayerNames())
				{
					tabCompletions.add(p);
				}
			}
		}
		
		return tabCompletions;
	}
	
}
