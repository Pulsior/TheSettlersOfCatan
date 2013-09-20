package com.Pulsior.SettlersOfCatan;

import org.bukkit.plugin.java.JavaPlugin;

public final class SettlersOfCatan extends JavaPlugin{


	@Override
	public void onEnable(){
		getCommand("join").setExecutor(new SettlersCommandExecutor(this));
		getServer().getPluginManager().registerEvents(new SettlersEventListener(), this);
		
	}

	@Override
	public void onDisable() {

	}


	
	
	
	
	


}




