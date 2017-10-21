package net.neferett.linaris.lobby.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.google.gson.Gson;

public class MinecraftPing
{
  public MinecraftPingReply getPing(String hostname)
    throws IOException
  {
    return getPing(new MinecraftPingOptions().setHostname(hostname));
  }

  public MinecraftPingReply getPing(MinecraftPingOptions options)
    throws IOException
  {
    MinecraftPingUtil.validate(options.getHostname(), "Hostname cannot be null.");
    MinecraftPingUtil.validate(Integer.valueOf(options.getPort()), "Port cannot be null.");

    Socket socket = new Socket();
    socket.connect(new InetSocketAddress(options.getHostname(), options.getPort()), options.getTimeout());

    DataInputStream in = new DataInputStream(socket.getInputStream());
    DataOutputStream out = new DataOutputStream(socket.getOutputStream());

    ByteArrayOutputStream handshake_bytes = new ByteArrayOutputStream();
    DataOutputStream handshake = new DataOutputStream(handshake_bytes);

    handshake.writeByte(MinecraftPingUtil.PACKET_HANDSHAKE);
    MinecraftPingUtil.writeVarInt(handshake, MinecraftPingUtil.PROTOCOL_VERSION);
    MinecraftPingUtil.writeVarInt(handshake, options.getHostname().length());
    handshake.writeBytes(options.getHostname());
    handshake.writeShort(options.getPort());
    MinecraftPingUtil.writeVarInt(handshake, MinecraftPingUtil.STATUS_HANDSHAKE);

    MinecraftPingUtil.writeVarInt(out, handshake_bytes.size());
    out.write(handshake_bytes.toByteArray());

    out.writeByte(1);
    out.writeByte(MinecraftPingUtil.PACKET_STATUSREQUEST);

    MinecraftPingUtil.readVarInt(in);
    int id = MinecraftPingUtil.readVarInt(in);

    MinecraftPingUtil.io(id == -1, "Server prematurely ended stream.");
    MinecraftPingUtil.io(id != MinecraftPingUtil.PACKET_STATUSREQUEST, "Server returned invalid packet.");

    int length = MinecraftPingUtil.readVarInt(in);
    MinecraftPingUtil.io(length == -1, "Server prematurely ended stream.");
    MinecraftPingUtil.io(length == 0, "Server returned unexpected value.");

    byte[] data = new byte[length];
    in.readFully(data);
    String json = new String(data, options.getCharset());

    out.writeByte(9);
    out.writeByte(MinecraftPingUtil.PACKET_PING);
    out.writeLong(System.currentTimeMillis());

    MinecraftPingUtil.readVarInt(in);
    id = MinecraftPingUtil.readVarInt(in);
    MinecraftPingUtil.io(id == -1, "Server prematurely ended stream.");
    MinecraftPingUtil.io(id != MinecraftPingUtil.PACKET_PING, "Server returned invalid packet.");

    handshake.close();
    handshake_bytes.close();
    out.close();
    in.close();
    socket.close();

    return (MinecraftPingReply)new Gson().fromJson(json, MinecraftPingReply.class);
  }
}