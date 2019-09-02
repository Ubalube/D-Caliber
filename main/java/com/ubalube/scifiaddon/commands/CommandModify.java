package com.ubalube.scifiaddon.commands;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.realmsclient.gui.ChatFormatting;
import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.commands.util.Loadout;
import com.ubalube.scifiaddon.commands.util.LoadoutSlots;
import com.ubalube.scifiaddon.data.LoadoutData;
import com.ubalube.scifiaddon.items.GunAttachments;
import com.ubalube.scifiaddon.items.GunBase;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemSpectralArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class CommandModify extends CommandBase
{

	@Override
	public String getName() {
		return "modify";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "modify <modification>";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		Potion effect = null;
		
		if(sender instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer) sender;
			
			if(p.getHeldItemMainhand().getItem() instanceof GunBase)
			{

				GunBase gun = (GunBase)p.getHeldItemMainhand().getItem();
				if(args[0].equalsIgnoreCase("PotionEffect"))
				{
					if(p.getHeldItemOffhand().getItem() instanceof ItemPotion)
					{
						List<PotionEffect> peffect = PotionUtils.getEffectsFromStack(p.getHeldItemOffhand());
						
						effect = peffect.get(0).getPotion();

						gun.addModification(GunAttachments.POTIONEFFECT, p.getHeldItemMainhand());
						gun.addPotionEffect(effect, p.getHeldItemMainhand());
					}
					else
					{
						p.sendMessage(new TextComponentString(ChatFormatting.RED + "You need to have a potion in your off hand!"));
					}
				}
				
				if(args[0].equalsIgnoreCase("IncreaseDamage"))
				{
					if(p.getHeldItemOffhand().getItem() instanceof ItemSword)
					{
						gun.addModification(GunAttachments.INCREASEDAMAGE, p.getHeldItemMainhand());
					}
					else
					{
						p.sendMessage(new TextComponentString(ChatFormatting.RED + "You need to have a diamond sword in your off hand!"));
					}
				}
				
				if(args[0].equalsIgnoreCase("LowRecoil"))
				{
					if(p.getHeldItemOffhand().getItem() instanceof ItemSpectralArrow)
					{
						gun.addModification(GunAttachments.LOWRECOIL, p.getHeldItemMainhand());
					}
					else
					{
						p.sendMessage(new TextComponentString(ChatFormatting.RED + "You need to have a spectral arrow in your off hand!"));
					}
				}
				
				if(args[0].equalsIgnoreCase("StatTrack"))
				{
					if(p.getHeldItemOffhand().getItem() instanceof ItemRecord)
					{
						gun.addModification(GunAttachments.STATTRACK, p.getHeldItemMainhand());
						gun.addStatTrack(p.getHeldItemMainhand());
					}
					else
					{
						p.sendMessage(new TextComponentString(ChatFormatting.RED + "You need to have a repeater in your off hand!"));
					}
				}
				
				
			}
			
		}
		
		
		
		
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		List<String> SlotCompletions = new ArrayList<>();
		List<String> LoadoutCompletions = new ArrayList<>();
		if(args.length == 0)
		{
			SlotCompletions.add("PotionEffect");
			SlotCompletions.add("IncreaseDamage");
			SlotCompletions.add("LowRecoil");
			SlotCompletions.add("StatTrack");
			return SlotCompletions;
		}
		
		return super.getTabCompletions(server, sender, args, targetPos);
	}
}
