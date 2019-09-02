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

public class MessageReloadGun implements IMessage{
	
	//The Item
	private ItemStack stack;
	//The Player ID
	private int playerId;
	//The method to give the PLAYER the ITEM AMOUNT times.
	public MessageReloadGun(EntityPlayer player, Item stack) {
		this.stack = new ItemStack(stack);
		this.playerId = player.getEntityId();
	}
	
	public MessageReloadGun() {
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
	public static class HandleReloadGun implements IMessageHandler<MessageReloadGun, IMessage>{

		@Override
		public IMessage onMessage(MessageReloadGun message, MessageContext ctx) {
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
						NBTTagCompound nbt = ((EntityPlayer) p).inventory.getCurrentItem().getTagCompound();
						((GunBase)((EntityPlayer) p).inventory.getCurrentItem().getItem()).Reload(((EntityPlayer) p), ((EntityPlayer) p).inventory.getCurrentItem(), nbt);
					}
			        
				}
			}
			return null;
		}
	}
	
}
