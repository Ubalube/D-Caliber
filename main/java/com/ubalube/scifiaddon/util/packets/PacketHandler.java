package com.ubalube.scifiaddon.util.packets;

import net.minecraft.network.INetHandler;
import net.minecraft.network.play.client.CPacketSteerBoat;

public interface PacketHandler extends INetHandler
{
	void processSteerVehicle(CPacketSteerVehicle packetIn);
}
