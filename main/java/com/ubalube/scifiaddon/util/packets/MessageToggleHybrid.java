package com.ubalube.scifiaddon.util.packets;

import com.ubalube.scifiaddon.items.GunBase;
import com.ubalube.scifiaddon.items.GunHybrid;

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

public class MessageToggleHybrid implements IMessage{
	
	//The Item
	private static ItemStack stack;
	//The Player ID
	private int playerId;
	//The method to give the PLAYER the ITEM AMOUNT times.
	public MessageToggleHybrid(EntityPlayer player, Item stack) {
		this.stack = new ItemStack(stack);
		this.playerId = player.getEntityId();
	}
	
	public MessageToggleHybrid() {
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
	public static class HandleToggleHybrid implements IMessageHandler<MessageToggleHybrid, IMessage>{

		@Override
		public IMessage onMessage(MessageToggleHybrid message, MessageContext ctx) {
			//Multiplayer Player
			EntityPlayerMP mp = ctx.getServerHandler().player;
			World world = mp.getEntityWorld();
			if (world == null) {
				
			} else {
				Entity p = world.getEntityByID(message.playerId);
				if(p != null && p instanceof EntityPlayer)
				{
					if(((EntityPlayer) p).getHeldItemMainhand().getItem() instanceof GunHybrid)
					{
						NBTTagCompound nbt = ((EntityPlayer) p).inventory.getCurrentItem().getTagCompound();
						boolean hybridval = ((GunHybrid)((EntityPlayer) p).inventory.getCurrentItem().getItem()).getHybrid(stack, nbt);
						
						if(hybridval)
						{
							((GunHybrid)((EntityPlayer) p).inventory.getCurrentItem().getItem()).toggleHybrid(false, stack, nbt);
						}
						else
						{

							((GunHybrid)((EntityPlayer) p).inventory.getCurrentItem().getItem()).toggleHybrid(true, stack, nbt);
						}
						
					}
			        
				}
			}
			return null;
		}
	}
	
}
