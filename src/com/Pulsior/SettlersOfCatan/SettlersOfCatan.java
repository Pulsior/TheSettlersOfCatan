package com.Pulsior.SettlersOfCatan;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Settlers of Catan main class, but most of the magic happens in the CommandExecutor class.
 * @author Pulsior
 *
 */

public final class SettlersOfCatan extends JavaPlugin{


	@Override
	public void onEnable(){
		/*
		 * Register necessary commands and listeners 
		 */
		getCommand("join").setExecutor(new SettlersCommandExecutor(this));
		getCommand("whichcoloris").setExecutor(new SettlersCommandExecutor(this));
		getCommand("whoplayswith").setExecutor(new SettlersCommandExecutor(this));
		getCommand("check").setExecutor(new SettlersCommandExecutor(this));
		getServer().getPluginManager().registerEvents(new SettlersEventListener(), this);
		
		
	}

	@Override
	public void onDisable() {
		//Nothing yet
	}


	
	
	
	
	


}




