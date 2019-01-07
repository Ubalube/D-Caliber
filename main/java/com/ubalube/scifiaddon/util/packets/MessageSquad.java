package com.ubalube.scifiaddon.util.packets;

import com.ubalube.scifiaddon.squads.SquadProvider;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSquad implements IMessage
{

	int id;
	public MessageSquad() {
	}
	
	public MessageSquad(int id_2) {
		id = id_2;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		id = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
	}
	
	public static class HandleMessageSquads implements IMessageHandler<MessageSquad, IMessage>{

		@Override
		public IMessage onMessage(MessageSquad message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				Minecraft.getMinecraft().player.getCapability(SquadProvider.SQUAD, null).setSquadByID(message.id);
				
			});
				
			return null;
		}
	}
	
}
