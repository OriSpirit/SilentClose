package com.spiritlight.silentclose;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketCloseWindow;

public class PacketHandler extends ChannelDuplexHandler {

    @Override
    public void write(ChannelHandlerContext ctx, Object packet, ChannelPromise promise) throws Exception {
        //System.out.println("Picked up write object " + packet + " with name " + packet.getClass().getName());
        if(packet instanceof Packet && packet.getClass().getName().contains("CPacketCloseWindow") && Main.dropChestPacket) {
            //System.out.println("Dropped packet");
            Main.dropChestPacket = false;
            return;
        }
        super.write(ctx, packet, promise);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
        super.channelRead(ctx, obj);
    }
}