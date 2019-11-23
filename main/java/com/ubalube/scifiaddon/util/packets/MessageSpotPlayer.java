package com.ubalube.scifiaddon.util.packets;

import com.mojang.realmsclient.gui.ChatFormatting;
import com.ubalube.scifiaddon.items.GunBase;
import com.ubalube.scifiaddon.items.GunHybrid;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSpotPlayer implements IMessage{
	
	//The Item
	private static ItemStack stack;
	//The Player ID
	private int playerId;
	//The method to give the PLAYER the ITEM AMOUNT times.
	public MessageSpotPlayer(EntityPlayer player, Item stack) {
		this.stack = new ItemStack(stack);
		this.playerId = player.getEntityId();
	}
	
	public MessageSpotPlayer() {
	}
	
	//BYTES
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(playerId);
		ByteBufUtils.writeItemStack(buf, stack);
	}
	
	
	@Override
	public void fromBytes(ByteBuf buf) {
		playerId = buf.readInt();
		stack = ByteBufUtils.readItemStack(buf);
	}
	//The message to give the items
	public static class HandleSpotPlayer implements IMessageHandler<MessageSpotPlayer, IMessage>{

		@Override
		public IMessage onMessage(MessageSpotPlayer message, MessageContext ctx) {
			//Multiplayer Player
			EntityPlayerMP mp = ctx.getServerHandler().player;
			World world = mp.getEntityWorld();
			if (world == null) {
				
			} else {
				Entity p = world.getEntityByID(message.playerId);
				if(p != null && p instanceof EntityPlayer)
				{
					RayTraceResult raytraceResultIn = new RayTraceResult(p);
					Entity entity = raytraceResultIn.entityHit;

			        if(raytraceResultIn.typeOfHit == Type.ENTITY)
			        {
			        	if(entity instanceof EntityLiving)
			        	{
			        		EntityPlayer spotter = (EntityPlayer) p;
			        		EntityPlayerMP spotterMP = mp;
			        		for(int i = 0; i < spotterMP.getServer().getPlayerList().getCurrentPlayerCount(); i ++)
			        		{
			        			spotterMP.getServer().getPlayerList().getPlayers().get(i).sendMessage(new TextComponentString("[" + ChatFormatting.GREEN + spotter.getDisplayNameString() + ChatFormatting.RESET + "]" + "Enemy Spotted!"));
			        		}
			        		((EntityPlayer) entity).addPotionEffect(new PotionEffect(MobEffects.GLOWING, 60, 1));
			        	}
			        }
				}
			}
			return null;
		}
	}
	
}
