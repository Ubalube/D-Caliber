package com.ubalube.scifiaddon.util.packets;

import com.ubalube.scifiaddon.squads.SquadProvider;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageReputation implements IMessage
{

	int rep;
	public MessageReputation() {
	}
	
	public MessageReputation(int id_2) {
		rep = id_2;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		rep = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(rep);
	}
	
	public static class HandleMessageSquads implements IMessageHandler<MessageReputation, IMessage>{

		@Override
		public IMessage onMessage(MessageReputation message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				Minecraft.getMinecraft().player.getCapability(SquadProvider.SQUAD, null).setSquadRep(message.rep);
				
			});
				
			return null;
		}
	}
	
}
