package io.github.Anon0x19.zVotebungee.util;

import io.github.Anon0x19.zVotebungee.main.*;
import java.io.*;
import net.cubespace.Yamler.Config.*;
import net.md_5.bungee.api.*;


public class FileManager extends Config
{
    @Comment("config version, dont touch this as it will break the plugin!")
    private double configVersion;
    @Comment("is mysql enabled")
    private boolean MySQLEnabled;
    @Comment("mysql host")
    private String MySQLHost;
    @Comment("mysql port")
    private String MySQLPort;
    @Comment("mysql database")
    private String MySQLDatabase;
    @Comment("mysql user")
    private String MySQLUser;
    @Comment("mysql password")
    private String MySQLPass;
    @Comment("Should we prevent vote spam prevention by only broadcasting once for each player? (if enabled)")
    private boolean voteSpamPrevention;
    @Comment("The time in minutes that the vote spam prevention clears the players that have already voted, to allow vote broadcasts to be sent for them again.")
    private int voteSpamPreventionTimeout;
    @Comment("Should we broadcast a vote message to all servers on the bungeecord instance when a player votes?")
    private boolean broadcastBungee;
    @Comment("Should we only broadcast when a player is online?")
    private boolean broadcastOnline;
    @Comment("vote broadcast message")
    private String broadcastBungeeMessage;
    @Comment("vote broadcast hover message")
    private String broadcastBungeeHoverMessage;
    @Comment("vote broadcast hover message")
    private String broadcastBungeeClickCommand;
    @Comment("Should we broadcast a vote broadcast command to all servers on all bungeecord instances when a player votes? (requires redis bungee installed and configured)")
    private boolean broadcastRedis;
    @Comment("vote broadcast message")
    private String broadcastRedisCmd;
    @Comment("is debug mode enabled")
    private boolean debugEnabled;
    @Comment("Maintain connection to the database?")
    private boolean maintainConnection;
    @Comment("Should we reward or count votes for offline rewards? If set to false, players that are not online will not be rewarded for voting nor will there votes be counted")
    private boolean rewardOffline;
    
    public FileManager(final zVoteBungee plugin) {
        this.configVersion = 1.6;
        this.MySQLEnabled = false;
        this.MySQLHost = "localhost";
        this.MySQLPort = "3306";
        this.MySQLDatabase = "zVote";
        this.MySQLUser = "root";
        this.MySQLPass = "password";
        this.voteSpamPrevention = true;
        this.voteSpamPreventionTimeout = 5;
        this.broadcastBungee = true;
        this.broadcastOnline = false;
        this.broadcastBungeeMessage = "&b%player% &aVoted for the server! Use /claim!";
        this.broadcastBungeeHoverMessage = "&aVote for the server just like &b%player% &adid!";
        this.broadcastBungeeClickCommand = "vote sites";
        this.broadcastRedis = true;
        this.broadcastRedisCmd = "v broadcast &b%player% &aVoted for the server from &b%service%! &aUse /claim!";
        this.debugEnabled = false;
        this.maintainConnection = true;
        this.rewardOffline = true;
        this.CONFIG_FILE = new File(plugin.getDataFolder(), "config.yml");
        this.CONFIG_HEADER = new String[] { "Configuration file for zVoteBungee", "Copyright (c) Anon0x19 2020 ,All Rights Reserved" };
        try {
            this.init();
        }
        catch (InvalidConfigurationException ex) {
            ex.printStackTrace();
        }
    }
    
    public final double a() {
        return this.configVersion;
    }
    
    public final boolean b() {
        return this.MySQLEnabled;
    }
    
    public final String c() {
        return this.MySQLHost;
    }
    
    public final String d() {
        return this.MySQLPort;
    }
    
    public final String e() {
        return this.MySQLDatabase;
    }
    
    public final String f() {
        return this.MySQLUser;
    }
    
    public final String g() {
        return this.MySQLPass;
    }
    
    public final boolean h() {
        return this.broadcastBungee;
    }
    
    public final String i() {
        return ChatColor.translateAlternateColorCodes('&', this.broadcastBungeeMessage);
    }
    
    public final String j() {
        return ChatColor.translateAlternateColorCodes('&', this.broadcastBungeeHoverMessage);
    }
    
    public final String k() {
        return ChatColor.translateAlternateColorCodes('&', this.broadcastBungeeClickCommand);
    }
    
    public final boolean l() {
        return this.broadcastRedis;
    }
    
    public final String m() {
        return ChatColor.translateAlternateColorCodes('&', this.broadcastRedisCmd);
    }
    
    public final boolean n() {
        return this.debugEnabled;
    }
    
    public final boolean o() {
        return this.voteSpamPrevention;
    }
    
    public final int p() {
        return this.voteSpamPreventionTimeout;
    }
    
    public final boolean q() {
        return this.broadcastOnline;
    }
    
    public final boolean r() {
        return this.maintainConnection;
    }
    
    public final boolean s() {
        return this.rewardOffline;
    }
}
