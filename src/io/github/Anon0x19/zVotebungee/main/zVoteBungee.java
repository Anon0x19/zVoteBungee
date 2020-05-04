package io.github.Anon0x19.zVotebungee.main;

import io.github.Anon0x19.zVotebungee.util.*;
import net.md_5.bungee.api.*;
import java.util.concurrent.*;
import net.md_5.bungee.api.plugin.*;

public class zVoteBungee extends Plugin
{
    private b a;
    private FileManager b;
    private a c;
    private e d;
    
    public void onEnable() {
        this.b = new FileManager(this);
        this.a = new b(this);
        this.d = new e(this);
        this.c = new a(this);
        if (this.getProxy().getPluginManager().getPlugin("NuVotifier") != null || this.getProxy().getPluginManager().getPlugin("BungeeVote") != null) {
            this.getProxy().getPluginManager().registerListener((Plugin)this, (Listener)new io.github.Anon0x19.zVotebungee.b.b(this));
            this.getProxy().getPluginManager().registerListener((Plugin)this, (Listener)new io.github.Anon0x19.zVotebungee.b.a(this));
        }
        if (this.b.a() != 1.6) {
            this.onDisable();
            this.getProxy().getPluginManager().unregisterListener((Listener)new io.github.Anon0x19.zVotebungee.b.b(this));
            this.getLogger().severe(ChatColor.RED + "Your config.yml is out of date! Please stop the proxy, backup your config then delete it and restart! Listening for votes has been disabled until this is rectified!");
            return;
        }
        if (this.b.o()) {
            this.getLogger().info("Starting clear voter task.");
            this.getProxy().getScheduler().schedule((Plugin)this, (Runnable)new Runnable() {
                @Override
                public final void run() {
                    if (zVoteBungee.this.b().n()) {
                        zVoteBungee.this.getLogger().info("Clearing vote spam prevention cache.");
                    }
                    zVoteBungee.this.d().a().clear();
                }
            }, (long)this.b.p(), (long)this.b.p(), TimeUnit.MINUTES);
        }
        this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new io.github.Anon0x19.zVotebungee.a.a(this));
    }
    
    private void f() {
        this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new io.github.Anon0x19.zVotebungee.a.a(this));
    }
    
    public final b a() {
        return this.a;
    }
    
    public final FileManager b() {
        return this.b;
    }
    
    public final boolean c() {
        return this.b.n();
    }
    
    public final e d() {
        return this.d;
    }
    
    public final a e() {
        return this.c;
    }
}
