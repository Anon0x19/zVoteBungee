package io.github.Anon0x19.zVotebungee.b;

import io.github.Anon0x19.zVotebungee.main.*;
import io.github.Anon0x19.zVotebungee.util.*;
import java.util.*;
import net.md_5.bungee.api.plugin.*;
import com.vexsoftware.votifier.model.*;
import net.md_5.bungee.event.*;

public final class b implements Listener
{
    public zVoteBungee a;
    
    public b(final zVoteBungee plugin) {
        this.a = plugin;
    }
    
    @EventHandler(priority = 64)
    public final void a(final VotifierEvent e) {
        final io.github.Anon0x19.zVotebungee.util.b mySql = this.a.a();
        final FileManager fm = this.a.b();
        final e util = this.a.d();
        final io.github.Anon0x19.zVotebungee.util.a bm = this.a.e();
        final Vote v;
        final String voteUsername = (v = e.getVote()).getUsername();
        final String service = v.getServiceName();
        if (!fm.b()) {
            return;
        }
        if (this.a.c()) {
            c.d("Vote received from username " + voteUsername + " from " + v.getServiceName() + " with IP " + v.getAddress());
        }
        if (v.getUsername().length() == 0 || v.getUsername().equalsIgnoreCase("anonymous") || v.getUsername().contains("test")) {
            return;
        }
        if (!v.getUsername().equals("")) {
            if (this.a.getProxy().getPluginManager().getPlugin("RedisBungee") != null && fm.l() && (!fm.o() || !util.b(voteUsername))) {
                bm.b(voteUsername, service);
            }
            this.a.getProxy().getScheduler().runAsync((Plugin)this.a, (Runnable)new Runnable() {
                @Override
                public final void run() {
                    final UUID playerUUID = mySql.b(voteUsername);
                    boolean isOnline = false;
                    try {
                        if (io.github.Anon0x19.zVotebungee.b.b.this.a.getProxy().getPlayer(playerUUID).getServer() != null) {
                            isOnline = true;
                        }
                    }
                    catch (NullPointerException ex) {}
                    if (!isOnline && !io.github.Anon0x19.zVotebungee.b.b.this.a.b().s()) {
                        return;
                    }
                    Label_0163: {
                        if (fm.h()) {
                            if (fm.o()) {
                                if (util.b(voteUsername)) {
                                    break Label_0163;
                                }
                                if (!fm.q()) {
                                    bm.a(voteUsername, service);
                                    break Label_0163;
                                }
                                if (playerUUID == null) {
                                    break Label_0163;
                                }
                            }
                            else if (fm.q() && playerUUID == null) {
                                break Label_0163;
                            }
                            bm.a(voteUsername, service);
                        }
                    }
                    if (fm.o() && !util.b(voteUsername)) {
                        final e d = util;
                        final String b = voteUsername;
                        final e e = d;
                        if (!d.a().contains(b)) {
                            e.a().add(b);
                        }
                    }
                    if (playerUUID != null) {
                        if (io.github.Anon0x19.zVotebungee.b.b.this.a.c()) {
                            io.github.Anon0x19.zVotebungee.util.c.d("Player UUID for player " + voteUsername + " :" + playerUUID.toString());
                        }
                        mySql.b(playerUUID, voteUsername);
                        mySql.a(playerUUID, voteUsername);
                        io.github.Anon0x19.zVotebungee.b.b.this.a.d().a(playerUUID);
                        return;
                    }
                    if (io.github.Anon0x19.zVotebungee.b.b.this.a.c()) {
                        io.github.Anon0x19.zVotebungee.util.c.d("Player UUID is null for player " + voteUsername + " ignoring.");
                    }
                }
            });
        }
    }
}
