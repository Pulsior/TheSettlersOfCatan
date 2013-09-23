package com.Pulsior.SettlersOfCatan;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Settlers of Catan main class, but most of the magic happens in the CommandExecutor class.
 * @author Pulsior
 *
 */

public final class SettlersOfCatan extends JavaPlugin{

	SettlerFileIO io = new SettlerFileIO();

	@Override
	public void onEnable(){
		/*
		 * Register necessary commands and listeners 
		 */
		getCommand("newgame").setExecutor(new SettlersCommandExecutor(this));
		getCommand("join").setExecutor(new SettlersCommandExecutor(this));
		getCommand("whichcoloris").setExecutor(new SettlersCommandExecutor(this));
		getCommand("whoplayswith").setExecutor(new SettlersCommandExecutor(this));
		getCommand("check").setExecutor(new SettlersCommandExecutor(this));
		getCommand("buy").setExecutor(new SettlersCommandExecutor(this));
		getServer().getPluginManager().registerEvents(new SettlersEventListener(), this);
		
		//Create folder to store files
		io.makeDir();
		
		
		
		
	}

	@Override
	public void onDisable() {
		//Nothing yet
	}


	
	
	
	
	


}




