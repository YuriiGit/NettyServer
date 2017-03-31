package com.freetime.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

public class Packet {

	private final int id;
	private final String data;
	// private final Channel channel;
	/*
	 * public Packet(int id, Channel channel) { this.id = id; this.channel =
	 * channel; }
	 * 
	 * public int getId() { return id; }
	 * 
	 * public Channel getChannel() { return channel; }
	 */

	public Packet(int id, String data) {
		this.id = id;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public String getData() {
		return data;
	}

	public static Packet read(ByteBuf buf) {

		int id = buf.readUnsignedShort();
		int length = buf.readShort();
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < length; ++i) {
			builder.append(buf.readChar());
		}
		String msg = builder.toString();
		Packet packet = new Packet(id, msg);

		return packet;
	}

	public static void write(Packet packet, ByteBuf buffer) {
		buffer.writeChar(packet.getId());
		int length = packet.getData().length();
		String data = packet.getData();
		buffer.writeShort(length);
		
		for (int i = 0; i < length; ++i) {
			buffer.writeChar(data.charAt(i));
		}

	}
}
