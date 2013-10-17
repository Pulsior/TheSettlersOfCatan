package com.Pulsior.SettlersOfCatan;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

/**
 * Settlers of Catan main class. It's a bit quiet here...
 * @author Pulsior
 *
 */

public final class SettlersOfCatan extends JavaPlugin{

	public static SPlayer[] sPlayers = new SPlayer[4];
	public static Data data = new Data();
	public static Scoreboard board;
	public static SettlersOfCatan plugin = new SettlersOfCatan();


	@Override
	public void onEnable(){
		board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
		/*
		 * Register necessary commands and listeners 
		 */
		getCommand("launchgame").setExecutor(new SettlersCommandExecutor(this));
		getCommand("endturn").setExecutor(new SettlersCommandExecutor(this));
		getCommand("newgame").setExecutor(new SettlersCommandExecutor(this));
		getCommand("join").setExecutor(new SettlersCommandExecutor(this));
		getCommand("check").setExecutor(new SettlersCommandExecutor(this));
		getCommand("buy").setExecutor(new SettlersCommandExecutor(this));
		getCommand("save").setExecutor(new SettlersCommandExecutor(this));
		getCommand("load").setExecutor(new SettlersCommandExecutor(this));
		getServer().getPluginManager().registerEvents(new SettlersEventListener(), this);


	}

	@Override
	public void onDisable() {

	}




}




