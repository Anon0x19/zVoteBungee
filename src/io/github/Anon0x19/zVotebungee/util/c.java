package io.github.Anon0x19.zVotebungee.util;

import net.md_5.bungee.api.*;

public final class c
{
    private static final String a;
    private static final String b = "[Info] ";
    private static final String c;
    private static final String d;
    private static final String e;
    
    public static void a(final String message) {
        ProxyServer.getInstance().getLogger().info(io.github.Anon0x19.zVotebungee.util.c.a + "[Info] " + message);
    }
    
    public static void b(final String message) {
        ProxyServer.getInstance().getLogger().info(io.github.Anon0x19.zVotebungee.util.c.a + io.github.Anon0x19.zVotebungee.util.c.c + message);
    }
    
    public static void c(final String message) {
        ProxyServer.getInstance().getLogger().info(io.github.Anon0x19.zVotebungee.util.c.a + io.github.Anon0x19.zVotebungee.util.c.d + message);
    }
    
    public static void d(final String message) {
        ProxyServer.getInstance().getLogger().info(io.github.Anon0x19.zVotebungee.util.c.a + io.github.Anon0x19.zVotebungee.util.c.e + message);
    }
    
    static {
        a = ChatColor.GREEN + "[zVoteBungee] ";
        c = ChatColor.YELLOW + "[Severe] ";
        d = ChatColor.RED + "[Warning] ";
        e = ChatColor.AQUA + "[Debug] ";
    }
}
