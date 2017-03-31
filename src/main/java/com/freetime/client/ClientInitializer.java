package com.freetime.client;

import com.freetime.server.PacketFrameDecoder;
import com.freetime.server.PacketFrameEncoder;
import com.freetime.server.PacketHandler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundInvoker;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ClientInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		
		pipeline.addLast(new PacketFrameDecoder());
		pipeline.addLast(new PacketFrameEncoder());
		
		pipeline.addLast(new ClientPacketHandler());
	}

}
