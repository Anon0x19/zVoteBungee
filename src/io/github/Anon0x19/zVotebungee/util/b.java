package io.github.Anon0x19.zVotebungee.util;

import io.github.Anon0x19.zVotebungee.main.*;
import net.md_5.bungee.api.plugin.*;
import net.md_5.bungee.api.*;
import java.sql.*;
import com.google.common.base.*;
import net.md_5.bungee.api.connection.*;
import net.md_5.bungee.api.connection.Connection;
import java.io.*;
import java.util.*;

public final class b
{
    private zVoteBungee a;
    private HashMap<String, UUID> c;
    private Connection b;
    private List<UUID> d;
    private Map<UUID, String> e;
    
    public b(final zVoteBungee plugin) {
        this.a = plugin;
        if (plugin.b().b()) {
            plugin.getProxy().getScheduler().runAsync((Plugin)plugin, (Runnable)new Runnable() {
                @Override
                public final void run() {
                    io.github.Anon0x19.zVotebungee.util.b.this.b();
                    io.github.Anon0x19.zVotebungee.util.b.this.d();
                    io.github.Anon0x19.zVotebungee.util.b.this.e();
                    io.github.Anon0x19.zVotebungee.util.b.this.f();
                    io.github.Anon0x19.zVotebungee.util.b.this.a();
                }
            });
        }
        else {
            io.github.Anon0x19.zVotebungee.util.c.a("DatabaseManager is " + ChatColor.RED + "DISABLED " + ChatColor.GREEN + "Please enable once you have setup the database details.");
        }
        this.c = new HashMap<String, UUID>();
        this.d = new ArrayList<UUID>();
        this.e = new HashMap<UUID, String>();
    }
    
    public final void a() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            final String query = "SHOW TABLES LIKE 'TOPVOTERS'";
            if (this.a.c()) {
                io.github.Anon0x19.zVotebungee.util.c.d("Preparing initial sql statement for TopVoters.");
            }
            final PreparedStatement prepareStatement;
            final ResultSet result = (prepareStatement = this.c().prepareStatement(query)).executeQuery();
            int results = 0;
            while (result.next()) {
                ++results;
            }
            result.close();
            prepareStatement.close();
            if (!this.b.isClosed() && !this.a.b().r()) {
                if (this.a.c()) {
                    io.github.Anon0x19.zVotebungee.util.c.d("Closing connection, connection is not being maintained.");
                }
                this.b.close();
            }
            if (results > 0) {
                if (this.a.c()) {
                    io.github.Anon0x19.zVotebungee.util.c.d("Table TopVoters exists, ignoring!");
                }
                return;
            }
            io.github.Anon0x19.zVotebungee.util.c.a("Table TopVoters does not exist creating it for you!");
            final String sql = "CREATE TABLE TOPVOTERS (ID MEDIUMINT NOT NULL AUTO_INCREMENT UNIQUE, MONTHYEAR VARCHAR(7),  POSITION INT,  UUID varchar(40),  PLAYERNAME VARCHAR(50),  VOTES MEDIUMINT,  PRIMARY KEY ( ID ))";
            final PreparedStatement prepareStatement2;
            (prepareStatement2 = this.c().prepareStatement(sql)).executeUpdate();
            prepareStatement2.close();
            if (!this.b.isClosed() && !this.a.b().r()) {
                if (this.a.c()) {
                    io.github.Anon0x19.zVotebungee.util.c.d("Closing connection, connection is not being maintained.");
                }
                this.b.close();
            }
        }
        catch (ClassNotFoundException ex2) {
            io.github.Anon0x19.zVotebungee.util.c.c("DatabaseManager driver wasn't found!");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void a(String monthYear, final int position, final String uuid, final String playerName, final int voteCount) {
        monthYear = "INSERT INTO TOPVOTERS VALUES (NULL, '" + monthYear + "', " + position + ", '" + uuid + "', '" + playerName + "', " + voteCount + ");";
        if (this.a.c()) {
            io.github.Anon0x19.zVotebungee.util.c.d("SQL: " + monthYear);
        }
        final boolean success = this.a(monthYear);
        if (this.a.c() && success) {
            io.github.Anon0x19.zVotebungee.util.c.d("Top voter insert successful");
        }
    }
    
    public final void b() {
        try {
            final String c = this.a.b().c();
            final String MySqlPort = this.a.b().d();
            final String MySqlDatabase = this.a.b().e();
            final String MySqlUsername = this.a.b().f();
            final String MySqlPassword = this.a.b().g();
            io.github.Anon0x19.zVotebungee.util.c.a("Attempting to connect to MySQL database.");
            if (!this.a.b().r()) {
                final Connection sqlDatabaseConnection = DriverManager.getConnection("jdbc:mysql://" + c + ":" + MySqlPort + "/" + MySqlDatabase + "?useSSL=false", MySqlUsername, MySqlPassword);
                io.github.Anon0x19.zVotebungee.util.c.a("Connection to MySQL database successful.");
                this.b = sqlDatabaseConnection;
                return;
            }
            final Connection sqlDatabaseConnection = DriverManager.getConnection("jdbc:mysql://" + c + ":" + MySqlPort + "/" + MySqlDatabase + "?autoReconnect=true&useSSL=false", MySqlUsername, MySqlPassword);
            io.github.Anon0x19.zVotebungee.util.c.a("Connection to MySQL database successful.");
            this.b = sqlDatabaseConnection;
        }
        catch (SQLException e) {
            io.github.Anon0x19.zVotebungee.util.c.c("DatabaseManager could not establish a connection when trying to setup the connection!" + e);
        }
    }
    
    public final Connection c() {
        try {
            if (!this.b.isClosed()) {
                if (this.a.c()) {
                    io.github.Anon0x19.zVotebungee.util.c.d("SQL Connection is still active, using it!.");
                }
                return this.b;
            }
        }
        catch (SQLException e) {
            io.github.Anon0x19.zVotebungee.util.c.b("SQL Error! " + e.getMessage());
        }
        catch (NullPointerException ex) {}
        if (this.a.c()) {
            io.github.Anon0x19.zVotebungee.util.c.d("SQL Connection is null creating new connection.");
        }
        this.b();
        return this.b;
    }
    
    public final void d() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            final String query = "SHOW TABLES LIKE 'ALLVOTES'";
            if (this.a.c()) {
                io.github.Anon0x19.zVotebungee.util.c.d("Preparing initial sql statement for AllVotes.");
            }
            final PreparedStatement prepareStatement;
            final ResultSet result = (prepareStatement = this.c().prepareStatement(query)).executeQuery();
            int i = 0;
            while (result.next()) {
                ++i;
            }
            result.close();
            prepareStatement.close();
            if (!this.b.isClosed() && !this.a.b().r()) {
                if (this.a.c()) {
                    io.github.Anon0x19.zVotebungee.util.c.d("Closing connection, connection is not being maintained.");
                }
                this.b.close();
            }
            if (i > 0) {
                if (this.a.c()) {
                    io.github.Anon0x19.zVotebungee.util.c.d("Table ALLVOTES exists, ignoring!");
                }
                return;
            }
            io.github.Anon0x19.zVotebungee.util.c.a("Table ALLVOTES does not exist creating it for you!");
            final String sql = "CREATE TABLE ALLVOTES (ID MEDIUMINT NOT NULL AUTO_INCREMENT UNIQUE, UUID VARCHAR(40) UNIQUE,  PLAYER VARCHAR(50) UNIQUE,  VOTES MEDIUMINT , LASTVOTE DATE, PRIMARY KEY ( ID ))";
            final PreparedStatement prepareStatement2;
            (prepareStatement2 = this.c().prepareStatement(sql)).executeUpdate();
            prepareStatement2.close();
            if (!this.b.isClosed() && !this.a.b().r()) {
                if (this.a.c()) {
                    io.github.Anon0x19.zVotebungee.util.c.d("Closing connection, connection is not being maintained.");
                }
                this.b.close();
            }
        }
        catch (ClassNotFoundException ex2) {
            io.github.Anon0x19.zVotebungee.util.c.c("DatabaseManager driver wasn't found!");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void e() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            final String query = "SHOW TABLES LIKE 'MONTHLYVOTES'";
            if (this.a.c()) {
                io.github.Anon0x19.zVotebungee.util.c.d("Preparing initial sql statement for MonthlyVotes.");
            }
            final PreparedStatement prepareStatement;
            final ResultSet result = (prepareStatement = this.c().prepareStatement(query)).executeQuery();
            int i = 0;
            while (result.next()) {
                ++i;
            }
            result.close();
            prepareStatement.close();
            if (!this.b.isClosed() && !this.a.b().r()) {
                if (this.a.c()) {
                    io.github.Anon0x19.zVotebungee.util.c.d("Closing connection, connection is not being maintained.");
                }
                this.b.close();
            }
            if (i > 0) {
                if (this.a.c()) {
                    io.github.Anon0x19.zVotebungee.util.c.d("Table MONTHLYVOTES exists, ignoring!");
                }
                return;
            }
            io.github.Anon0x19.zVotebungee.util.c.a("Table MONTHLYVOTES does not exist creating it for you!");
            final String sql = "CREATE TABLE MONTHLYVOTES (ID MEDIUMINT NOT NULL AUTO_INCREMENT UNIQUE, UUID VARCHAR(40) UNIQUE,  PLAYER VARCHAR(50) UNIQUE,  VOTES MEDIUMINT ,UNCLAIMEDVOTES MEDIUMINT, LASTVOTE DATE, PRIMARY KEY ( ID ))";
            final PreparedStatement prepareStatement2;
            (prepareStatement2 = this.c().prepareStatement(sql)).executeUpdate();
            prepareStatement2.close();
            if (!this.b.isClosed() && !this.a.b().r()) {
                if (this.a.c()) {
                    io.github.Anon0x19.zVotebungee.util.c.d("Closing connection, connection is not being maintained.");
                }
                this.b.close();
            }
        }
        catch (ClassNotFoundException ex) {
            io.github.Anon0x19.zVotebungee.util.c.c("DatabaseManager driver wasn't found!");
        }
        catch (Exception e) {
            io.github.Anon0x19.zVotebungee.util.c.c("DatabaseManager could not establish a connection when trying to setup monthly votes: " + e);
        }
    }
    
    public final void f() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            final String query = "SHOW TABLES LIKE 'LASTRESET'";
            if (this.a.c()) {
                io.github.Anon0x19.zVotebungee.util.c.d("Preparing initial sql statement for LastReset.");
            }
            final PreparedStatement prepareStatement;
            final ResultSet result = (prepareStatement = this.c().prepareStatement(query)).executeQuery();
            int i = 0;
            while (result.next()) {
                ++i;
            }
            result.close();
            prepareStatement.close();
            if (!this.b.isClosed() && !this.a.b().r()) {
                if (this.a.c()) {
                    io.github.Anon0x19.zVotebungee.util.c.d("Closing connection, connection is not being maintained.");
                }
                this.b.close();
            }
            if (i <= 0) {
                io.github.Anon0x19.zVotebungee.util.c.a("Table LASTRESET does not exist creating it for you!");
                final String sql = "CREATE TABLE LASTRESET (ID MEDIUMINT NOT NULL AUTO_INCREMENT, TABLENAME VARCHAR(30),  LASTRESET DATE, PRIMARY KEY ( ID ))";
                final PreparedStatement prepareStatement2;
                (prepareStatement2 = this.c().prepareStatement(sql)).executeUpdate();
                prepareStatement2.close();
                if (!this.b.isClosed() && !this.a.b().r()) {
                    if (this.a.c()) {
                        io.github.Anon0x19.zVotebungee.util.c.d("Closing connection, connection is not being maintained.");
                    }
                    this.b.close();
                }
                final String string = "INSERT INTO LASTRESET VALUES (null,'" + "MONTHLYVOTES" + "','" + new Date(System.currentTimeMillis()) + "')";
                if (this.a.c()) {
                    this.a.getLogger().info("Add initial last reset: " + string);
                }
                try {
                    final Statement statement;
                    (statement = this.c().createStatement()).executeUpdate(string);
                    statement.close();
                    if (!this.a.b().r()) {
                        this.b.close();
                    }
                }
                catch (SQLException ex) {
                    this.a.getLogger().severe("ERROR inserting new initial last reset!" + ex);
                }
            }
        }
        catch (ClassNotFoundException ex2) {
            io.github.Anon0x19.zVotebungee.util.c.c("DatabaseManager driver wasn't found!");
        }
        catch (Exception e) {
            io.github.Anon0x19.zVotebungee.util.c.c("DatabaseManager could not establish a connection when trying to setup last reset table: " + e);
        }
    }
    
    public final void g() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            io.github.Anon0x19.zVotebungee.util.c.a("Attempting to connect to DatabaseManager.");
            final String query = "SHOW TABLES LIKE 'DAILYTARGET'";
            final PreparedStatement prepareStatement;
            final ResultSet result = (prepareStatement = this.c().prepareStatement(query)).executeQuery();
            int i = 0;
            while (result.next()) {
                ++i;
            }
            result.close();
            prepareStatement.close();
            if (i <= 0) {
                io.github.Anon0x19.zVotebungee.util.c.a("Table DAILYTARGET does not exist creating it for you!");
                final String sql = "CREATE TABLE DAILYTARGET (ID MEDIUMINT NOT NULL AUTO_INCREMENT, VOTES MEDIUMINT , LASTREACHED DATE, LASTRESET DATE, PRIMARY KEY ( ID ))";
                final PreparedStatement prepareStatement2;
                if ((prepareStatement2 = this.c().prepareStatement(sql)).executeUpdate() == 1) {
                    io.github.Anon0x19.zVotebungee.util.c.a("Daily target table creation successful.");
                }
                prepareStatement2.close();
                final Date date = new Date(System.currentTimeMillis());
                final String sql2 = "INSERT INTO DAILYTARGET VALUES (NULL, '0', NULL, '" + date + "')";
                if (this.a.c()) {
                    io.github.Anon0x19.zVotebungee.util.c.d("inserting default daily target value: " + sql2);
                }
                try {
                    final Statement statement2;
                    (statement2 = this.c().createStatement()).executeUpdate(sql2);
                    statement2.close();
                }
                catch (SQLException e) {
                    this.a.getLogger().severe("Error inserting default daily target values!" + e);
                }
                if (!this.a.b().r()) {
                    this.b.close();
                }
            }
        }
        catch (ClassNotFoundException ex) {
            io.github.Anon0x19.zVotebungee.util.c.c("DatabaseManager driver wasn't found!");
        }
        catch (Exception e2) {
            io.github.Anon0x19.zVotebungee.util.c.c("DatabaseManager could not establish a connection: " + e2);
        }
    }
    
    public final int h() {
        final String query = "SELECT VOTES FROM DAILYTARGET WHERE ID ='1'";
        if (this.a.c()) {
            this.a.getLogger().info("getting daily target vote count: " + query);
        }
        int voteCount = 0;
        int rows = 0;
        try {
            final Statement statement;
            final ResultSet result = (statement = this.c().createStatement()).executeQuery(query);
            while (result.next()) {
                voteCount = result.getInt("VOTES");
                ++rows;
            }
            result.close();
            statement.close();
            if (rows <= 1) {
                return voteCount;
            }
            if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException e) {
            this.a.getLogger().severe("SQL Error!" + e);
        }
        return -1;
    }
    
    public final Date i() {
        final String query = "SELECT LASTRESET FROM DAILYTARGET WHERE ID ='1'";
        if (this.a.c()) {
            this.a.getLogger().info("getting vote target last reset date: " + query);
        }
        Date lastReset = null;
        int rows = 0;
        try {
            final Statement statement;
            final ResultSet result = (statement = this.c().createStatement()).executeQuery(query);
            while (result.next()) {
                lastReset = result.getDate("LASTRESET");
                ++rows;
            }
            result.close();
            statement.close();
            if (rows <= 1) {
                return lastReset;
            }
            if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException e) {
            this.a.getLogger().severe("SQL Error while getting last reset date!" + e);
        }
        return null;
    }
    
    public final Date j() {
        final String query = "SELECT LASTREACHED FROM DAILYTARGET WHERE ID ='1'";
        if (this.a.c()) {
            this.a.getLogger().info("getting vote target last reset date: " + query);
        }
        Date lastReset = null;
        int rows = 0;
        try {
            final Statement statement;
            final ResultSet result = (statement = this.c().createStatement()).executeQuery(query);
            while (result.next()) {
                lastReset = result.getDate("LASTREACHED");
                ++rows;
            }
            result.close();
            statement.close();
            if (rows <= 1) {
                if (lastReset == null) {
                    return null;
                }
                return lastReset;
            }
            else if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException e) {
            this.a.getLogger().severe("SQL Error while retrieving last reached target!" + e);
        }
        return null;
    }
    
    public final void k() {
        new Date(System.currentTimeMillis());
        final String query = "SELECT VOTES FROM DAILYTARGET WHERE ID ='1'";
        if (this.a.c()) {
            this.a.getLogger().info("Adding vote to daily target: " + query);
        }
        int voteCount = 0;
        int rows = 0;
        try {
            final Statement statement;
            final ResultSet result = (statement = this.c().createStatement()).executeQuery(query);
            while (result.next()) {
                voteCount = result.getInt("VOTES");
                ++rows;
            }
            result.close();
            statement.close();
            if (rows <= 1) {
                final int newVoteValue = voteCount + 1;
                final String sql = "UPDATE DAILYTARGET SET VOTES ='" + newVoteValue + "' WHERE ID='1'";
                if (this.a.c()) {
                    this.a.getLogger().info(sql);
                }
                final Statement statement2;
                (statement2 = this.c().createStatement()).executeUpdate(sql);
                statement2.close();
            }
            if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException e) {
            this.a.getLogger().severe("SQL Error while adding daily vote! " + e);
        }
    }
    
    public final void a(final boolean targetReached) {
        final Date date = new Date(System.currentTimeMillis());
        String sql;
        if (targetReached) {
            sql = "UPDATE DAILYTARGET SET VOTES ='0', LASTREACHED ='" + date + "', LASTRESET = '" + date + "'";
        }
        else {
            sql = "UPDATE DAILYTARGET SET VOTES ='0', LASTRESET = '" + date + "'";
        }
        try {
            final Statement statement;
            (statement = this.c().createStatement()).executeUpdate(sql);
            statement.close();
            if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException e) {
            io.github.Anon0x19.zVotebungee.util.c.b("SQL ERROR while resetting daily vote target! " + e);
        }
    }
    
    private int a(final Map<String, UUID> playerData, final String tableName) {
        int failedUpdates = 0;
        for (final Map.Entry<String, UUID> entry : playerData.entrySet()) {
            final String sql = "UPDATE " + tableName + " SET `UUID`='" + entry.getValue() + "' WHERE `PLAYER`='" + entry.getKey() + "'";
            if (this.a.c()) {
                io.github.Anon0x19.zVotebungee.util.c.d(sql);
            }
            if (!this.a(sql)) {
                io.github.Anon0x19.zVotebungee.util.c.b("UUID update failed for player " + entry.getKey());
                ++failedUpdates;
            }
            if (!this.d.contains(entry.getValue())) {
                this.d.add(entry.getValue());
            }
        }
        return failedUpdates;
    }
    
    public final boolean a(final String sql) {
        try {
            final Statement statement;
            (statement = this.c().createStatement()).executeUpdate(sql);
            statement.close();
            if (!this.a.b().r()) {
                this.b.close();
            }
            return true;
        }
        catch (SQLException e) {
            io.github.Anon0x19.zVotebungee.util.c.b("Error executing query: " + sql + " Error: " + e.getMessage());
            return false;
        }
    }
    
    public final HashMap<String, UUID> l() {
        return this.c;
    }
    
    public final UUID b(String username) {
        if (this.a.c()) {
            io.github.Anon0x19.zVotebungee.util.c.d("Get uuid from username: " + username);
        }
        UUID uuid = null;
        if (this.a.c()) {
            io.github.Anon0x19.zVotebungee.util.c.d("Trying cache to retrieve uuid.");
        }
        if (this.c.containsKey(username)) {
            if (this.a.c()) {
                io.github.Anon0x19.zVotebungee.util.c.d("Player uuid is cached using: " + this.c.get(username));
            }
            return this.c.get(username);
        }
        final ProxiedPlayer player = this.a.getProxy().getPlayer(username);
        if (this.a.c()) {
            io.github.Anon0x19.zVotebungee.util.c.d("Trying to check if player is online to retrieve uuid.");
        }
        if (player != null) {
            if (this.a.c()) {
                io.github.Anon0x19.zVotebungee.util.c.d("Player is online retrieving UUID.");
            }
            final UUID playerUUID = player.getUniqueId();
            if (!this.c.containsKey(username)) {
                this.c.put(username, playerUUID);
            }
            if (this.a.c()) {
                io.github.Anon0x19.zVotebungee.util.c.d("Retrieved cached UUID is: " + playerUUID);
            }
            return playerUUID;
        }
        final String sql = "SELECT UUID FROM ALLVOTES WHERE PLAYER='" + username + "'";
        int results = 0;
        if (this.a.c()) {
            io.github.Anon0x19.zVotebungee.util.c.d("Trying to check allvotes database table to see if player has voted before.");
        }
        try {
            final ResultSet result = this.c().createStatement().executeQuery(sql);
            while (result.next()) {
                uuid = UUID.fromString(result.getString("UUID"));
                if (!this.c.containsKey(username)) {
                    this.c.put(username, uuid);
                }
                ++results;
            }
            if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException e) {
            io.github.Anon0x19.zVotebungee.util.c.b("SQL Error getting uuid from username! " + e.getMessage());
        }
        if (results == 0) {
            if (this.a.getProxy().getConfig().isOnlineMode()) {
                if (this.a.c()) {
                    io.github.Anon0x19.zVotebungee.util.c.d("UUID is not in database and player is offline, contacting Mojang.");
                }
                final d uuidFetcher = new d(username);
                try {
                    uuid = uuidFetcher.a();
                    if (!this.c.containsKey(username)) {
                        this.c.put(username, uuid);
                    }
                    return uuid;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    if (this.a.c()) {
                        io.github.Anon0x19.zVotebungee.util.c.d("UUID resolved from Mojang: " + uuid);
                    }
                    return uuid;
                }
            }
            if (!this.c.containsKey(username)) {
                this.c.put(username, uuid);
            }
            if (this.a.c()) {
                io.github.Anon0x19.zVotebungee.util.c.d("Using offline uuid.");
            }
            username = username;
            return UUID.nameUUIDFromBytes(("OfflinePlayer:" + username).getBytes(Charsets.UTF_8));
        }
        return uuid;
    }
    
    public final void a(UUID playerUUID, String playerName) {
        if (playerUUID == null) {
            if (this.a.c()) {
                io.github.Anon0x19.zVotebungee.util.c.d("UUID is null ignoring for vote username " + playerName);
            }
            return;
        }
        final String query = "SELECT VOTES FROM MONTHLYVOTES WHERE UUID='" + playerUUID + "'";
        if (this.a.c()) {
            io.github.Anon0x19.zVotebungee.util.c.d("Add Player Monthly Vote check uuid: " + query);
        }
        int n = 0;
        try {
            final Statement statement;
            final ResultSet result = (statement = this.c().createStatement()).executeQuery(query);
            while (result.next()) {
                ++n;
            }
            if (n == 0) {
                String uuid = "";
                if (this.a.c()) {
                    io.github.Anon0x19.zVotebungee.util.c.d("No results found by uuid!");
                }
                int result2Count = 0;
                final String queryCheckPlayerName = "SELECT VOTES,UUID FROM MONTHLYVOTES WHERE PLAYER='" + playerName + "'";
                final ResultSet result2 = this.c().createStatement().executeQuery(queryCheckPlayerName);
                if (this.a.c()) {
                    io.github.Anon0x19.zVotebungee.util.c.d("Add Player Monthly Vote check username: " + queryCheckPlayerName);
                }
                while (result2.next()) {
                    uuid = result2.getString("UUID");
                    ++result2Count;
                }
                if (result2Count == 1) {
                    if (this.a.c()) {
                        io.github.Anon0x19.zVotebungee.util.c.d("UUID of player: " + playerUUID);
                        io.github.Anon0x19.zVotebungee.util.c.d("Found result by username with uuid: " + uuid);
                    }
                    if (!playerUUID.equals(uuid)) {
                        if (this.a.c()) {
                            io.github.Anon0x19.zVotebungee.util.c.d("UUID mismatch of MonthlyVotes, updating uuid of player to: " + playerUUID);
                        }
                        final String sql = "UPDATE MONTHLYVOTES SET UUID = '" + playerUUID + "' WHERE UUID='" + uuid + "'";
                        if (this.a.c()) {
                            io.github.Anon0x19.zVotebungee.util.c.d("Update uuid query: " + sql);
                        }
                        if (this.a(sql)) {
                            if (this.a.c()) {
                                io.github.Anon0x19.zVotebungee.util.c.d("UUID successfully updated.");
                                this.e(playerUUID, playerName);
                            }
                            else {
                                io.github.Anon0x19.zVotebungee.util.c.b("UUID was not updated successfully please check logs!!");
                            }
                        }
                    }
                }
                else {
                    if (this.a.c()) {
                        io.github.Anon0x19.zVotebungee.util.c.d("No result by username creating new monthly vote record!");
                    }
                    final Serializable s = playerUUID;
                    final String s2 = playerName;
                    playerName = (String)s;
                    playerUUID = (UUID)this;
                    playerName = "INSERT INTO MONTHLYVOTES VALUES (null, '" + (Object)playerName + "', '" + s2 + "','1" + "','1" + "','" + new Date(System.currentTimeMillis()) + "')";
                    if (((b)playerUUID).a.c()) {
                        ((b)playerUUID).a.getLogger().info("Create New Monthly Vote Record: " + playerName);
                    }
                    ((b)playerUUID).a(playerName);
                }
            }
            else if (n == 1) {
                this.e(playerUUID, playerName);
            }
            else {
                this.a.getLogger().severe("more than one record was found for a player!! failed to register vote!");
            }
            result.close();
            statement.close();
            if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException e) {
            this.a.getLogger().severe("error adding all time player vote!!" + e);
        }
    }
    
    public final void b(UUID playerUUID, String playerName) {
        if (playerUUID == null) {
            if (this.a.c()) {
                io.github.Anon0x19.zVotebungee.util.c.d("UUID is null ignoring for vote username " + playerName);
            }
            return;
        }
        final String query = "SELECT VOTES FROM ALLVOTES WHERE UUID='" + playerUUID + "'";
        if (this.a.c()) {
            this.a.getLogger().info("Add Player AllTime Vote: " + query);
        }
        int n = 0;
        try {
            final Statement statement;
            final ResultSet result = (statement = this.c().createStatement()).executeQuery(query);
            while (result.next()) {
                ++n;
            }
            if (n == 0) {
                String uuid = "";
                if (this.a.c()) {
                    io.github.Anon0x19.zVotebungee.util.c.d("No results found by uuid!");
                }
                int result2Count = 0;
                final String queryCheckPlayerName = "SELECT VOTES,UUID FROM ALLVOTES WHERE PLAYER='" + playerName + "'";
                final ResultSet result2 = this.c().createStatement().executeQuery(queryCheckPlayerName);
                if (this.a.c()) {
                    io.github.Anon0x19.zVotebungee.util.c.d("Add Player Monthly Vote check username: " + queryCheckPlayerName);
                }
                while (result2.next()) {
                    uuid = result2.getString("UUID");
                    ++result2Count;
                }
                if (result2Count == 1) {
                    if (this.a.c()) {
                        io.github.Anon0x19.zVotebungee.util.c.d("UUID of player: " + playerUUID);
                        io.github.Anon0x19.zVotebungee.util.c.d("Found result by username with uuid: " + uuid);
                    }
                    if (!playerUUID.equals(uuid)) {
                        if (this.a.c()) {
                            io.github.Anon0x19.zVotebungee.util.c.d("UUID mismatch of AllVotes, updating uuid of player to: " + playerUUID);
                        }
                        final String sql = "UPDATE ALLVOTES SET UUID = '" + playerUUID + "' WHERE UUID='" + uuid + "'";
                        if (this.a.c()) {
                            io.github.Anon0x19.zVotebungee.util.c.d("Update uuid query: " + sql);
                        }
                        if (this.a(sql)) {
                            if (this.a.c()) {
                                io.github.Anon0x19.zVotebungee.util.c.d("UUID successfully updated.");
                                this.f(playerUUID, playerName);
                            }
                            else {
                                io.github.Anon0x19.zVotebungee.util.c.b("UUID was not updated successfully please check logs!!");
                            }
                        }
                    }
                }
                else {
                    if (this.a.c()) {
                        io.github.Anon0x19.zVotebungee.util.c.d("No result by username creating new monthly vote record!");
                    }
                    final Serializable s = playerUUID;
                    final String s2 = playerName;
                    playerName = (String)s;
                    playerUUID = (UUID)this;
                    playerName = "INSERT INTO ALLVOTES VALUES (null, '" + (Object)playerName + "', '" + s2 + "','1" + "','" + new Date(System.currentTimeMillis()) + "')";
                    if (((b)playerUUID).a.c()) {
                        ((b)playerUUID).a.getLogger().info("Create New All Time Vote Record: " + playerName);
                    }
                    ((b)playerUUID).a(playerName);
                }
            }
            else if (n == 1) {
                this.f(playerUUID, playerName);
            }
            else {
                this.a.getLogger().severe("more than one record was found for a player!! failed to register vote!");
            }
            result.close();
            statement.close();
            if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException e) {
            this.a.getLogger().severe("error adding all time player vote!!" + e);
        }
    }
    
    public final void c(final UUID playerUUID, final String playerName) {
        final Date date = new Date(System.currentTimeMillis());
        final String sql = "INSERT INTO MONTHLYVOTES VALUES (null, '" + playerUUID + "', '" + playerName + "','1" + "','1" + "','" + date + "')";
        if (this.a.c()) {
            this.a.getLogger().info("Create New Monthly Vote Record: " + sql);
        }
        this.a(sql);
    }
    
    public final void d(final UUID playerUUID, final String playerName) {
        final Date date = new Date(System.currentTimeMillis());
        final String sql = "INSERT INTO ALLVOTES VALUES (null, '" + playerUUID + "', '" + playerName + "','1" + "','" + date + "')";
        if (this.a.c()) {
            this.a.getLogger().info("Create New All Time Vote Record: " + sql);
        }
        this.a(sql);
    }
    
    public final void e(final UUID playerUUID, final String playerName) {
        final String query = "SELECT VOTES,UNCLAIMEDVOTES,PLAYER FROM MONTHLYVOTES WHERE UUID ='" + playerUUID + "'";
        if (this.a.c()) {
            this.a.getLogger().info("Update Existing Vote Record: " + query);
        }
        int voteCount = 0;
        int unclaimedCount = 0;
        int rows = 0;
        String playerNameSQL = null;
        try {
            final Statement statement;
            final ResultSet result = (statement = this.c().createStatement()).executeQuery(query);
            while (result.next()) {
                voteCount = result.getInt("VOTES");
                unclaimedCount = result.getInt("UNCLAIMEDVOTES");
                playerNameSQL = result.getString("PLAYER");
                ++rows;
            }
            result.close();
            statement.close();
            if (rows <= 1) {
                final int newVoteValue = voteCount + 1;
                final int newUnclaimedCount = unclaimedCount + 1;
                final Date date = new Date(System.currentTimeMillis());
                final String sql = "UPDATE MONTHLYVOTES SET VOTES ='" + newVoteValue + "',LASTVOTE ='" + date + "', UNCLAIMEDVOTES='" + newUnclaimedCount + "' WHERE `UUID`='" + playerUUID + "'";
                if (this.a.c()) {
                    this.a.getLogger().info(sql);
                    io.github.Anon0x19.zVotebungee.util.c.d("RowCount: " + rows);
                    io.github.Anon0x19.zVotebungee.util.c.d("VoteCount: " + voteCount);
                    io.github.Anon0x19.zVotebungee.util.c.d("UnclaimedCount: " + unclaimedCount);
                    io.github.Anon0x19.zVotebungee.util.c.d("NewVoteCount: " + newVoteValue);
                    io.github.Anon0x19.zVotebungee.util.c.d("NewUnclaimedCount: " + newUnclaimedCount);
                }
                this.a(sql);
            }
            else {
                this.a.getLogger().severe("Duplicate player names please check database!");
            }
            if (!playerNameSQL.equals(playerName)) {
                if (this.a.c()) {
                    io.github.Anon0x19.zVotebungee.util.c.d("Player name has changed, updating.");
                }
                this.a("UPDATE MONTHLYVOTES SET PLAYER='" + playerName + "' WHERE UUID='" + playerUUID + "'");
            }
            if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException e) {
            this.a.getLogger().severe("SQL Error!" + e);
        }
    }
    
    public final void f(final UUID playerUUID, final String playerName) {
        final String query = "SELECT VOTES,PLAYER FROM ALLVOTES WHERE UUID ='" + playerUUID + "'";
        if (this.a.c()) {
            this.a.getLogger().info("Update Existing Vote Record: " + query);
        }
        int voteCount = 0;
        int rows = 0;
        String playerNameSQL = null;
        try {
            final Statement statement;
            final ResultSet result = (statement = this.c().createStatement()).executeQuery(query);
            while (result.next()) {
                voteCount = result.getInt("VOTES");
                playerNameSQL = result.getString("PLAYER");
                ++rows;
            }
            result.close();
            statement.close();
            if (rows <= 1) {
                final int newVoteValue = voteCount + 1;
                final Date date = new Date(System.currentTimeMillis());
                final String sql = "UPDATE ALLVOTES SET VOTES ='" + newVoteValue + "',LASTVOTE ='" + date + "' WHERE `UUID`='" + playerUUID + "'";
                if (this.a.c()) {
                    this.a.getLogger().info(sql);
                }
                final Statement statement2;
                (statement2 = this.c().createStatement()).executeUpdate(sql);
                statement2.close();
            }
            else {
                this.a.getLogger().severe("Duplicate player names please check database!");
            }
            if (!playerNameSQL.equals(playerName)) {
                if (this.a.c()) {
                    io.github.Anon0x19.zVotebungee.util.c.d("Player name has changed, updating.");
                }
                this.a("UPDATE ALLVOTES SET PLAYER='" + playerName + "' WHERE UUID='" + playerUUID + "'");
            }
            if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException e) {
            this.a.getLogger().severe("SQL Error!" + e);
        }
    }
    
    public final void a(final UUID playerUUID) {
        if (this.a.c()) {
            io.github.Anon0x19.zVotebungee.util.c.d("reset unclaimed votes.");
        }
        final String sql = "UPDATE MONTHLYVOTES SET UNCLAIMEDVOTES ='0' WHERE `UUID`='" + playerUUID + "'";
        this.a(sql);
    }
    
    public final LinkedHashMap<String, Integer> m() {
        final LinkedHashMap<String, Integer> votesAllTime2 = new LinkedHashMap<String, Integer>();
        final String query = "SELECT PLAYER, VOTES FROM ALLVOTES ORDER BY VOTES DESC LIMIT 0, 10";
        int votes = 0;
        try {
            final Statement statement;
            final ResultSet result = (statement = this.c().createStatement()).executeQuery(query);
            while (result.next()) {
                votesAllTime2.put(result.getString("PLAYER"), result.getInt("VOTES"));
                ++votes;
            }
            if (this.a.c()) {
                io.github.Anon0x19.zVotebungee.util.c.d("Loaded " + ChatColor.AQUA + votes + ChatColor.GREEN + " all vote records!");
            }
            result.close();
            statement.close();
            if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException e) {
            io.github.Anon0x19.zVotebungee.util.c.b("Could not load all time votes!" + e);
        }
        return votesAllTime2;
    }
    
    public final LinkedHashMap<String, Integer> a(final int limit) {
        final LinkedHashMap<String, Integer> votesAllTime2 = new LinkedHashMap<String, Integer>();
        final String query = "SELECT PLAYER, VOTES FROM ALLVOTES ORDER BY VOTES DESC LIMIT 0, " + limit;
        int votes = 0;
        try {
            final Statement statement;
            final ResultSet result = (statement = this.c().createStatement()).executeQuery(query);
            while (result.next()) {
                votesAllTime2.put(result.getString("PLAYER"), result.getInt("VOTES"));
                ++votes;
            }
            if (this.a.c()) {
                io.github.Anon0x19.zVotebungee.util.c.d("Loaded " + ChatColor.AQUA + votes + ChatColor.GREEN + " all vote records!");
            }
            result.close();
            statement.close();
            if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException e) {
            io.github.Anon0x19.zVotebungee.util.c.b("Could not load all time votes!" + e);
        }
        return votesAllTime2;
    }
    
    public final LinkedHashMap<String, Integer> n() {
        final LinkedHashMap<String, Integer> votesAllTime = new LinkedHashMap<String, Integer>();
        final String query = "SELECT PLAYER, VOTES FROM MONTHLYVOTES ORDER BY VOTES DESC LIMIT 0, 10";
        int votes = 0;
        try {
            final Statement statement;
            final ResultSet result = (statement = this.c().createStatement()).executeQuery(query);
            while (result.next()) {
                votesAllTime.put(result.getString("PLAYER"), result.getInt("VOTES"));
                ++votes;
            }
            if (this.a.c()) {
                io.github.Anon0x19.zVotebungee.util.c.d("Loaded " + ChatColor.AQUA + votes + ChatColor.GREEN + " monthly vote records!");
            }
            result.close();
            statement.close();
            if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException e) {
            io.github.Anon0x19.zVotebungee.util.c.b("Could not retrieve player votes!" + e);
        }
        return votesAllTime;
    }
    
    public final LinkedHashMap<String, Integer> b(final int limit) {
        final LinkedHashMap<String, Integer> votesAllTime = new LinkedHashMap<String, Integer>();
        final String query = "SELECT PLAYER, VOTES FROM MONTHLYVOTES ORDER BY VOTES DESC LIMIT 0, " + limit;
        int votes = 0;
        try {
            final Statement statement;
            final ResultSet result = (statement = this.c().createStatement()).executeQuery(query);
            while (result.next()) {
                votesAllTime.put(result.getString("PLAYER"), result.getInt("VOTES"));
                ++votes;
            }
            if (this.a.c()) {
                io.github.Anon0x19.zVotebungee.util.c.d("Loaded " + ChatColor.AQUA + votes + ChatColor.GREEN + " monthly vote records!");
            }
            result.close();
            statement.close();
            if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException e) {
            io.github.Anon0x19.zVotebungee.util.c.b("Could not retrieve player votes!" + e);
        }
        return votesAllTime;
    }
    
    public final int b(final UUID playerUUID) {
        int unclaimedVotes = 0;
        final String query = "SELECT UNCLAIMEDVOTES FROM MONTHLYVOTES WHERE UUID='" + playerUUID + "'";
        int results = 0;
        try {
            final Statement statement;
            final ResultSet result = (statement = this.c().createStatement()).executeQuery(query);
            while (result.next()) {
                unclaimedVotes = result.getInt("UNCLAIMEDVOTES");
                ++results;
            }
            result.close();
            statement.close();
            if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException e) {
            io.github.Anon0x19.zVotebungee.util.c.b("Could not load unclaimed votes!" + e);
        }
        if (results == 0) {
            return 0;
        }
        if (results == 1) {
            return unclaimedVotes;
        }
        return -1;
    }
    
    public final boolean a(final UUID playerUUID, final int amount) {
        int unclaimedVotes = 0;
        final String query = "SELECT UNCLAIMEDVOTES FROM MONTHLYVOTES WHERE UUID='" + playerUUID + "'";
        try {
            final Statement statement;
            final ResultSet result = (statement = this.c().createStatement()).executeQuery(query);
            while (result.next()) {
                unclaimedVotes = result.getInt("UNCLAIMEDVOTES");
            }
            result.close();
            statement.close();
            if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException e) {
            io.github.Anon0x19.zVotebungee.util.c.b("Could not load unclaimed votes!" + e);
        }
        if (amount == 0) {
            return false;
        }
        if (unclaimedVotes >= amount) {
            final int finalAmount = unclaimedVotes - amount;
            final String sql = "UPDATE MONTHLYVOTES SET UNCLAIMEDVOTES ='" + finalAmount + "' WHERE `UUID`='" + playerUUID + "'";
            try {
                if (this.a.c()) {
                    io.github.Anon0x19.zVotebungee.util.c.d(sql);
                }
                final Statement statement2;
                (statement2 = this.c().createStatement()).executeUpdate(sql);
                statement2.close();
                if (!this.a.b().r()) {
                    this.b.close();
                }
                return true;
            }
            catch (SQLException e2) {
                io.github.Anon0x19.zVotebungee.util.c.b("SQL Error!" + e2);
            }
        }
        return false;
    }
    
    public final int g(final UUID playerUUID, String results) {
        int playerVotes = 0;
        final String query = "SELECT VOTES FROM " + table + " WHERE UUID='" + playerUUID + "'";
        if (this.a.c()) {
            io.github.Anon0x19.zVotebungee.util.c.d("SQL Query: " + query);
        }
        results = 0;
        try {
            final Statement statement;
            final ResultSet result = (statement = this.c().createStatement()).executeQuery(query);
            while (result.next()) {
                playerVotes = result.getInt("VOTES");
                ++results;
            }
            result.close();
            statement.close();
            if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException e) {
            io.github.Anon0x19.zVotebungee.util.c.b("Could not check players votes by UUID!" + e);
        }
        if (results == 0) {
            return 0;
        }
        if (results == 1) {
            if (this.a.c()) {
                io.github.Anon0x19.zVotebungee.util.c.d("Vote count: " + playerVotes);
            }
            return playerVotes;
        }
        return -1;
    }
    
    public final int a(String playerName, String results) {
        int playerVotes = 0;
        playerName = "SELECT VOTES FROM " + table + " WHERE PLAYER='" + playerName + "'";
        if (this.a.c()) {
            io.github.Anon0x19.zVotebungee.util.c.d("SQL Query: " + playerName);
        }
        results = 0;
        try {
            final Statement statement;
            final ResultSet result = (statement = this.c().createStatement()).executeQuery(playerName);
            while (result.next()) {
                playerVotes = result.getInt("VOTES");
                ++results;
            }
            result.close();
            statement.close();
            if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException e) {
            io.github.Anon0x19.zVotebungee.util.c.b("Could check players votes by username!" + e);
        }
        if (results == 0) {
            return 0;
        }
        if (results == 1) {
            return playerVotes;
        }
        return -1;
    }
    
    public final boolean c(final UUID playerUUID) {
        final Date todaysDate = new Date(System.currentTimeMillis());
        final String query = "SELECT ID FROM MONTHLYVOTES WHERE UUID ='" + playerUUID + "' AND LASTVOTE ='" + todaysDate + "'";
        int results = 0;
        try {
            final Statement statement;
            final ResultSet result = (statement = this.c().createStatement()).executeQuery(query);
            while (result.next()) {
                ++results;
            }
            result.close();
            statement.close();
            if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException ex) {
            io.github.Anon0x19.zVotebungee.util.c.b("could not check if player voted today!");
        }
        return results == 1;
    }
    
    public final void c(String tablename) {
        final String sql = "UPDATE " + tablename + " SET VOTES = 0";
        try {
            final Statement statement;
            (statement = this.c().createStatement()).executeUpdate(sql);
            statement.close();
        }
        catch (SQLException ex) {
            this.a.getLogger().severe("could not reset votes for table " + tablename);
            return;
        }
        final Date date = new Date(System.currentTimeMillis());
        tablename = "UPDATE LASTRESET SET LASTRESET ='" + date + "' WHERE TABLENAME='" + tablename + "'";
        if (this.a.c()) {
            this.a.getLogger().info(sql);
            this.a.getLogger().info(tablename);
        }
        try {
            final Statement statement2;
            (statement2 = this.c().createStatement()).executeUpdate(tablename);
            statement2.close();
            if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException e) {
            this.a.getLogger().severe("could not set last reset date." + e);
        }
    }
    
    public final void d(String tableName) {
        final Date date = new Date(System.currentTimeMillis());
        tableName = "INSERT INTO LASTRESET VALUES (null,'" + tableName + "','" + date + "')";
        if (this.a.c()) {
            this.a.getLogger().info("Add initial last reset: " + tableName);
        }
        try {
            final Statement statement;
            (statement = this.c().createStatement()).executeUpdate(tableName);
            statement.close();
            if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException e) {
            this.a.getLogger().severe("ERROR inserting new initial last reset!" + e);
        }
    }
    
    public final Date e(String tableName) {
        Date lastReset = null;
        tableName = "SELECT LASTRESET FROM LASTRESET WHERE TABLENAME='" + tableName + "'";
        int results = 0;
        try {
            final Statement statement;
            final ResultSet result = (statement = this.c().createStatement()).executeQuery(tableName);
            while (result.next()) {
                lastReset = result.getDate("LASTRESET");
                ++results;
            }
            result.close();
            statement.close();
            if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException e) {
            this.a.getLogger().severe("Could not check last reset date!" + e);
        }
        if (results == 0) {
            return null;
        }
        if (results == 1) {
            return lastReset;
        }
        return null;
    }
    
    public final LinkedHashMap<String, Integer> o() {
        final Date todaysDate = new Date(System.currentTimeMillis());
        final LinkedHashMap<String, Integer> topFiveDailyVotes = new LinkedHashMap<String, Integer>();
        final String query = "SELECT PLAYER, VOTES FROM MONTHLYVOTES WHERE DATE = '" + todaysDate + "' ORDER BY VOTES DESC LIMIT 0, 5";
        if (this.a.c()) {
            io.github.Anon0x19.zVotebungee.util.c.d(query);
        }
        int votes = 0;
        try {
            final Statement statement;
            final ResultSet result = (statement = this.c().createStatement()).executeQuery(query);
            while (result.next()) {
                topFiveDailyVotes.put(result.getString("PLAYER"), result.getInt("VOTES"));
                ++votes;
            }
            result.close();
            statement.close();
            io.github.Anon0x19.zVotebungee.util.c.a("Loaded " + ChatColor.AQUA + votes + ChatColor.GREEN + " daily vote records!");
            if (!this.a.b().r()) {
                this.b.close();
            }
        }
        catch (SQLException e) {
            io.github.Anon0x19.zVotebungee.util.c.b("Could not load top 5 daily votes votes!" + e);
        }
        return topFiveDailyVotes;
    }
}
