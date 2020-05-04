package io.github.Anon0x19.zVotebungee.util;

import java.util.concurrent.*;
import java.net.*;
import com.google.gson.*;
import java.io.*;
import java.util.*;
import java.nio.*;

public final class d implements Callable<UUID>
{
    private static final String a = "https://api.mojang.com/profiles/minecraft";
    private final String b;
    private JsonParser c;
    
    public d(final String name) {
        this.c = new JsonParser();
        this.b = name;
    }
    
    public final UUID a() throws Exception {
        final HttpURLConnection httpURLConnection;
        (httpURLConnection = (HttpURLConnection)new URL("https://api.mojang.com/profiles/minecraft").openConnection()).setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        final HttpURLConnection connection = httpURLConnection;
        final String json = new Gson().toJson((Object)this.b);
        final HttpURLConnection httpURLConnection2 = connection;
        final String s = json;
        final OutputStream outputStream = httpURLConnection2.getOutputStream();
        io.github.Anon0x19.zVotebungee.util.c.d("UUID body: " + s);
        outputStream.write(s.getBytes());
        outputStream.flush();
        outputStream.close();
        final Iterator iterator;
        if ((iterator = ((JsonArray)this.c.parse((Reader)new InputStreamReader(connection.getInputStream()))).iterator()).hasNext()) {
            final JsonObject jsonProfile;
            final String id = (jsonProfile = (JsonObject) iterator.next()).get("id").toString().replaceAll("\"", "");
            jsonProfile.get("name");
            io.github.Anon0x19.zVotebungee.util.c.d(id);
            final String s2 = id;
            return UUID.fromString(s2.substring(0, 8) + "-" + s2.substring(8, 12) + "-" + s2.substring(12, 16) + "-" + s2.substring(16, 20) + "-" + s2.substring(20, 32));
        }
        return null;
    }
    
    private static void a(final HttpURLConnection connection, final String body) throws Exception {
        final OutputStream stream = connection.getOutputStream();
        c.d("UUID body: " + body);
        stream.write(body.getBytes());
        stream.flush();
        stream.close();
    }
    
    private static HttpURLConnection b() throws Exception {
        final HttpURLConnection connection;
        (connection = (HttpURLConnection)new URL("https://api.mojang.com/profiles/minecraft").openConnection()).setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        return connection;
    }
    
    private static UUID a(final String id) {
        return UUID.fromString(id.substring(0, 8) + "-" + id.substring(8, 12) + "-" + id.substring(12, 16) + "-" + id.substring(16, 20) + "-" + id.substring(20, 32));
    }
    
    public static byte[] a(final UUID uuid) {
        final ByteBuffer byteBuffer;
        (byteBuffer = ByteBuffer.wrap(new byte[16])).putLong(uuid.getMostSignificantBits());
        byteBuffer.putLong(uuid.getLeastSignificantBits());
        return byteBuffer.array();
    }
    
    public static UUID a(final byte[] array) {
        if (array.length != 16) {
            throw new IllegalArgumentException("Illegal byte array length: " + array.length);
        }
        final ByteBuffer byteBuffer;
        final long mostSignificant = (byteBuffer = ByteBuffer.wrap(array)).getLong();
        final long leastSignificant = byteBuffer.getLong();
        return new UUID(mostSignificant, leastSignificant);
    }

	@Override
	public UUID call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
