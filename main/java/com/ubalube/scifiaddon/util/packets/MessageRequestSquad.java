package com.ubalube.scifiaddon.util.packets;

import com.ubalube.scifiaddon.squads.SquadProvider;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageRequestSquad implements IMessage
{

	public MessageRequestSquad() {
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}
	
	public static class HandleRequestSquad implements IMessageHandler<MessageRequestSquad, MessageSquad>{

		@Override
		public MessageSquad onMessage(MessageRequestSquad message, MessageContext ctx) {
			
			EntityPlayerMP mp = ctx.getServerHandler().player;
			ISquad squads = mp.getCapability(SquadProvider.SQUAD, null);
			return new MessageSquad(squads.getSquadByID());
		}
	}
}
