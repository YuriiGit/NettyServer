package com.freetime.server;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.handler.codec.MessageToMessageEncoder;

public class PacketFrameEncoder extends MessageToMessageEncoder<Packet> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
		if(msg == null)
			return;
		
		ByteBuf buffer = ctx.alloc().buffer(msg.getData().length() + 4);
		Packet.write(msg, buffer);
		
		out.add(buffer);
	}

}
