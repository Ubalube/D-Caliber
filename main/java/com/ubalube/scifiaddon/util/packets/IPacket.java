package com.ubalube.scifiaddon.util.packets;

import java.io.IOException;

import com.jcraft.jogg.Packet;

import net.minecraft.network.INetHandler;
import net.minecraft.network.PacketBuffer;

public interface IPacket<T extends PacketHandler>
{
    /**
     * Reads the raw packet data from the data stream.
     */
    void readPacketData(PacketBuffer buf) throws IOException;

    /**
     * Writes the raw packet data to the data stream.
     */
    void writePacketData(PacketBuffer buf) throws IOException;

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    void processPacket(T handler);
}
