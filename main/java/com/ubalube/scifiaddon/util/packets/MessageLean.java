package com.ubalube.scifiaddon.util.packets;

import com.ubalube.scifiaddon.items.GunBase;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageLean implements IMessage{
	
	//The Item
	private ItemStack stack;
	//The Player ID
	private int playerId;
	
	private LeanDirection leanDir;
	
	//The method to give the PLAYER the ITEM AMOUNT times.
	public MessageLean(EntityPlayer player, Item stack, LeanDirection direction) {
		this.stack = new ItemStack(stack);
		this.playerId = player.getEntityId();
		this.leanDir = direction;
	}
	
	//BYTES
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(playerId);
		
		if(this.leanDir == LeanDirection.LEFT)
		{
			buf.writeFloat(1.0f);
		}
		else
		{
			if(this.leanDir == LeanDirection.RIGHT)
			{
				buf.writeFloat(1.5f);
			}
			else
			{
				if(this.leanDir == LeanDirection.NONE)
				{
					buf.writeFloat(2.0f);
				}
			}
		}
		
		ByteBufUtils.writeItemStack(buf, stack);
	}
	
	
	@Override
	public void fromBytes(ByteBuf buf) {
		playerId = buf.readInt();
		stack = ByteBufUtils.readItemStack(buf);
		if(buf.readFloat() == 1.0f)
		{
			leanDir = LeanDirection.LEFT;
		}
		else
		{
			if(buf.readFloat() == 1.5f)
			{
				leanDir = LeanDirection.RIGHT;
			}
			else
			{
				if(buf.readFloat() == 2.0f)
				{
					leanDir = LeanDirection.NONE;
				}
			}
		}
	}
	//The message to give the items
	public static class HandleLean implements IMessageHandler<MessageLean, IMessage>{

		@Override
		public IMessage onMessage(MessageLean message, MessageContext ctx) {
			//Multiplayer Player
			EntityPlayerMP mp = ctx.getServerHandler().player;
			World world = mp.getEntityWorld();
			if (world == null) {
				
			} else {
				Entity p = world.getEntityByID(message.playerId);
				if(p != null && p instanceof EntityPlayer)
				{
			        if(((EntityPlayer) p).getHeldItemMainhand().getItem() instanceof GunBase)
			        {
			        	EntityPlayer pla = ((EntityPlayer) p);
			        	if(message.leanDir == LeanDirection.LEFT)
			        	{
				        	pla.rotationPitch = -5.0f;
			        	}
			        	else
			        	{
			        		if(message.leanDir == LeanDirection.RIGHT)
			        		{
			        			pla.rotationPitch = 5.0f;
			        		}
			        		else
			        		{
			        			if(message.leanDir == LeanDirection.NONE)
			        			{
			        				pla.rotationPitch = 0.0f;
			        			}
			        		}
			        	}
			        }
				}
			}
			return null;
		}
	}
	
}