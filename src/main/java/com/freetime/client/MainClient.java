package com.freetime.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.freetime.server.Packet;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MainClient {

	static final String HOST = System.getProperty("host", "127.0.0.1");
	static final int PORT = Integer.parseInt(System.getProperty("port", "9021"));
	
	public static void main(String[] args) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).handler(new ClientInitializer());
			
			Channel ch = b.connect(HOST, PORT).sync().channel();
			
			ChannelFuture lastWriteFuture = null;
			
			int idPacket = 0;
			
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			for(;;) {
				String line = in.readLine();
				if(line == null) {
					break;
				}
				
				Packet packet = new Packet(idPacket++, line);
				
				lastWriteFuture = ch.writeAndFlush(packet);
				
				if("bye".equals(line.toLowerCase())) {
					ch.closeFuture().sync();
					break;
				}
			}
			if(lastWriteFuture != null) {
				lastWriteFuture.sync();
			}
		} finally {
			group.shutdownGracefully();
		}
	}

}
