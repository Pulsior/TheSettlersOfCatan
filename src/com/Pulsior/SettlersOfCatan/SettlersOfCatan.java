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

	SettlerFileIO io = new SettlerFileIO();
	public static Scoreboard board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
	
	
	@Override
	public void onEnable(){
		/*
		 * Register necessary commands and listeners 
		 */
		getCommand("launchgame").setExecutor(new SettlersCommandExecutor(this));
		getCommand("newgame").setExecutor(new SettlersCommandExecutor(this));
		getCommand("join").setExecutor(new SettlersCommandExecutor(this));
		getCommand("whichcoloris").setExecutor(new SettlersCommandExecutor(this));
		getCommand("whoplayswith").setExecutor(new SettlersCommandExecutor(this));
		getCommand("check").setExecutor(new SettlersCommandExecutor(this));
		getCommand("buy").setExecutor(new SettlersCommandExecutor(this));
		getServer().getPluginManager().registerEvents(new SettlersEventListener(), this);
		

		
		
		
		
		
	}

	@Override
	public void onDisable() {
		//Nothing yet
	}
	


	
	
	
	
	


}




