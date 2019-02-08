package com.ubalube.scifiaddon.util.packets;

import com.ubalube.scifiaddon.bounties.BountyProvider;
import com.ubalube.scifiaddon.squads.SquadProvider;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageBounty implements IMessage
{

	int id;
	int points;
	boolean isComplete;
	boolean isActive;
	
	public MessageBounty() {
	}
	
	public MessageBounty(int bounty_id, int bounty_points, boolean bounty_active, boolean bounty_complete) {
		id = bounty_id;
		points = bounty_points;
		isComplete = bounty_complete;
		isActive = bounty_active;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		id = buf.readInt();
		points = buf.readInt();
		isActive = buf.readBoolean();
		isComplete = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
		buf.writeInt(points);
		buf.writeBoolean(isActive);
		buf.writeBoolean(isComplete);
	}
	
	public static class HandleMessageSquads implements IMessageHandler<MessageBounty, IMessage>{

		@Override
		public IMessage onMessage(MessageBounty message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				//Set the active bounty
				Minecraft.getMinecraft().player.getCapability(BountyProvider.BOUNTY, null).setActiveBounty(message.id);
				//Set the bounty points
				Minecraft.getMinecraft().player.getCapability(BountyProvider.BOUNTY, null).setBountyPoints(message.points);
				//Is the bounty complete
				Minecraft.getMinecraft().player.getCapability(BountyProvider.BOUNTY, null).bountyState(message.isComplete);
				//Is the bounty active
				Minecraft.getMinecraft().player.getCapability(BountyProvider.BOUNTY, null).setActive(message.isActive);
			});
				
			return null;
		}
	}
	
}
