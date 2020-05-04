package io.github.Anon0x19.zVotebungee.a;

import net.md_5.bungee.api.plugin.*;
import io.github.Anon0x19.zVotebungee.main.*;
import net.md_5.bungee.api.*;
import java.util.*;
import io.github.Anon0x19.zVotebungee.util.*;

public final class a extends Command
{
    private zVoteBungee a;
    
    public a(final zVoteBungee plugin) {
        super("vb");
        this.a = plugin;
    }
    
    public final void execute(CommandSender sender, UUID uuid) {
        if (!sender.hasPermission("zVotebungee.admin")) {
            e.a(sender, ChatColor.RED + "You do not have permission for this command!");
            return;
        }
        if (args.length <= 0) {
            sender.sendMessage(ChatColor.AQUA + "zVoteBungee Commands.");
            sender.sendMessage("");
            sender.sendMessage(ChatColor.AQUA + "/vb addtestvote <player> - " + ChatColor.GOLD + " attempt add a test vote for a player");
            sender.sendMessage(ChatColor.AQUA + "/vb reward <player> - " + ChatColor.GOLD + " attempt to reward an online player using bungeecord communication (they need to have unclaimed votes)");
            return;
        }
        if (args.length == 2 && args[0].equals("reward")) {
            final String playerName = args[1];
            final UUID uuid2 = this.a.a().b(playerName);
            this.a.d().a(uuid2);
            e.a(sender, ChatColor.GREEN + "Rewarding player " + playerName);
            return;
        }
        if (args.length == 2 && args[0].equals("convert")) {
            final String playerName = args[1];
            final d uuidFetcher = new d(playerName);
            try {
                uuid = uuidFetcher.a();
                e.a(sender, ChatColor.GREEN + "UUID: " + uuid);
                return;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
        }
        if (args.length == 2 && args[0].equals("addtestvote")) {
            final String playerName = args[1];
            final b mySql = this.a.a();
            try {
                if ((uuid = mySql.b(playerName)) == null) {
                    e.a(sender, ChatColor.GREEN + "UUID retrieval failed!: " + playerName);
                    return;
                }
                this.a.a().b(uuid, playerName);
                this.a.a().a(uuid, playerName);
                e.a(sender, ChatColor.GREEN + "Successfully added vote for player: " + playerName);
                return;
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
                return;
            }
        }
        if (args.length >= 2 && args[0].equalsIgnoreCase("broadcast")) {
            final StringBuilder messages = new StringBuilder();
            for (int x = 1; x < args.length; ++x) {
                messages.append(args[x]).append(" ");
            }
            final String message = messages.toString();
            sender = (CommandSender)ChatColor.translateAlternateColorCodes('&', message);
            ProxyServer.getInstance().broadcast((String)sender);
            return;
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("broadcastvote")) {
            final String playerName = args[1];
            this.a.e().a(playerName, "testService");
            return;
        }
        e.a(sender, "Unknown command!");
    }
}