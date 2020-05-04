package io.github.Anon0x19.zVotebungee.util;

import io.github.Anon0x19.zVotebungee.main.*;
import net.md_5.bungee.api.*;
import net.md_5.bungee.api.chat.*;
import com.imaginarycode.minecraft.redisbungee.*;

public final class a
{
    private zVoteBungee a;
    
    public a(final zVoteBungee plugin) {
        this.a = plugin;
    }
    
    public final void a(final String voteUsername, final String service) {
        final FileManager fm;
        String voteBroadcast = (fm = this.a.b()).i();
        String voteBroadcastHover = fm.j();
        final String voteBroadcastClick = fm.k();
        voteBroadcast = (voteBroadcast = voteBroadcast.replaceAll("%player%", voteUsername)).replaceAll("%service%", service);
        voteBroadcastHover = voteBroadcastHover.replaceAll("%player%", voteUsername);
        final TextComponent message;
        (message = new TextComponent(voteBroadcast)).setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(voteBroadcastHover).create()));
        if (!voteBroadcastClick.equals("")) {
            message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + voteBroadcastClick));
        }
        ProxyServer.getInstance().broadcast((BaseComponent)message);
        if (this.a.c()) {
            c.d("Broadcasting vote message.");
        }
    }
    
    public final void b(final String voteUsername, final String service) {
        final String broadcastVoteCmd = this.a.b().m().replaceAll("%player%", voteUsername).replaceAll("%service%", service);
        RedisBungee.getApi().sendProxyCommand(broadcastVoteCmd);
        if (this.a.c()) {
            this.a.getLogger().info("regis proxy command: " + broadcastVoteCmd);
        }
    }
}
