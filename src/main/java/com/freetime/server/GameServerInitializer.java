package com.freetime.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class GameServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		
		pipeline.addLast(new PacketFrameDecoder());
		pipeline.addLast(new PacketFrameEncoder());
		
		pipeline.addLast(new PacketHandler());
		
	}
}
