package io.github.Anon0x19.zVotebungee.b;

import io.github.Anon0x19.zVotebungee.main.*;
import io.github.Anon0x19.zVotebungee.util.*;
import net.md_5.bungee.api.plugin.*;
import net.md_5.bungee.api.event.*;
import java.util.*;
import net.md_5.bungee.event.*;

public final class a implements Listener
{
    private zVoteBungee a;
    
    public a(final zVoteBungee plugin) {
        this.a = plugin;
        plugin.getProxy().getPluginManager().registerListener((Plugin)plugin, (Listener)this);
    }
    
    @EventHandler(priority = 0)
    public final void a(final LoginEvent e) {
        final io.github.Anon0x19.zVotebungee.util.b mySql = this.a.a();
        final UUID uuid = e.getConnection().getUniqueId();
        final String name = e.getConnection().getName();
        if (!mySql.l().containsValue(uuid)) {
            if (this.a.c()) {
                c.d("Adding uuid to uuid cache");
            }
            mySql.l().put(name, uuid);
        }
    }
}
