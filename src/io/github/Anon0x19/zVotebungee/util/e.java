package io.github.Anon0x19.zVotebungee.util;

import io.github.Anon0x19.zVotebungee.main.*;
import com.imaginarycode.minecraft.redisbungee.*;
import net.md_5.bungee.api.config.*;
import net.md_5.bungee.api.connection.*;
import java.util.*;
import java.io.*;
import com.google.common.base.*;
import net.md_5.bungee.api.*;
import net.md_5.bungee.api.chat.*;

public final class e
{
    private zVoteBungee a;
    private List<String> b;
    
    public e(final zVoteBungee plugin) {
        this.a = plugin;
        this.b = new ArrayList<String>();
    }
    
    public final void a(final String voterName) {
        if (!this.b.contains(voterName)) {
            this.b.add(voterName);
        }
    }
    
    public final boolean b(final String voterName) {
        return this.b.contains(voterName);
    }
    
    public final List<String> a() {
        return this.b;
    }
    
    public final void b() {
        this.b.clear();
    }
    
    public static void a(final CommandSender sender, final String messageIn) {
        sender.sendMessage(ChatColor.AQUA + "[zVoteBungee] " + messageIn);
    }
    
    public static void b(final CommandSender sender, final String messageIn) {
        sender.sendMessage(messageIn);
    }
    
    public final void a(final UUID playerUUID) {
        if (playerUUID == null) {
            if (this.a.c()) {
                c.d("Player uuid is null, not sending server message.");
            }
            return;
        }
        if (this.a.getProxy().getPluginManager().getPlugin("RedisBungee") != null) {
            try {
                final ServerInfo playersServerInfo = RedisBungee.getApi().getServerFor(playerUUID);
                final Iterator<ProxiedPlayer> iterator = (Iterator<ProxiedPlayer>)playersServerInfo.getPlayers().iterator();
                while (true) {
                    while (iterator.hasNext()) {
                        final ProxiedPlayer proxiedPlayer;
                        if ((proxiedPlayer = iterator.next()).getUniqueId().equals(playerUUID)) {
                            final Server server = proxiedPlayer.getServer();
                            final Server playersServer = server;
                            this.a(playerUUID, playersServer);
                            return;
                        }
                    }
                    final Server server = null;
                    continue;
                }
            }
            catch (NullPointerException ex) {
                c.b(ex.getMessage());
                return;
            }
        }
        try {
            final Server playersServer = this.a.getProxy().getPlayer(playerUUID).getServer();
            this.a(playerUUID, playersServer);
        }
        catch (NullPointerException ex2) {
            c.b(ex2.getMessage());
        }
    }
    
    public static Server a(final UUID playerUUID, final Collection<ProxiedPlayer> playerCollection) {
        final Iterator<ProxiedPlayer> iterator = playerCollection.iterator();
        while (iterator.hasNext()) {
            final ProxiedPlayer player;
            if ((player = iterator.next()).getUniqueId().equals(playerUUID)) {
                return player.getServer();
            }
        }
        return null;
    }
    
    public final void a(final UUID playerUUID, final Server playersServer) {
        if (this.a.c()) {
            c.d("Players server for " + playerUUID + " is: " + playersServer);
        }
        playersServer.getInfo().getName();
        final ByteArrayOutputStream b = new ByteArrayOutputStream();
        final DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("v:rewards");
            out.writeUTF(playerUUID.toString());
            playersServer.sendData("v:rewards", b.toByteArray());
            if (this.a.c()) {
                c.d("Sending UUID: " + playerUUID);
            }
        }
        catch (IOException ex) {
            c.b(ex.getMessage());
        }
    }
    
    public static UUID c(final String username) {
        return UUID.nameUUIDFromBytes(("OfflinePlayer:" + username).getBytes(Charsets.UTF_8));
    }
    
    public static void d(final String message) {
        ProxyServer.getInstance().broadcast(message);
    }
    
    public static void a(final TextComponent message) {
        ProxyServer.getInstance().broadcast((BaseComponent)message);
    }
}