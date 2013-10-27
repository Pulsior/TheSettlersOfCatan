package com.Pulsior.SettlersOfCatan.game;

import java.io.File;

import org.bukkit.Bukkit;

/**
 * Class instantiated as pregame, where players can join but can't build stuff yet.
 * @author Pulsior
 *
 */

public class PreGame {

	public static boolean preGame = false;
	public PreGame(){

		//Indicate that pregame has been launched
		preGame = true;
		Bukkit.getLogger().info("[Settlers of Catan] Started pregame, players can join the game now");

		//Create folder to store files
		makeDir();
		Bukkit.getLogger().info("[Settlers of Catan] Settlers of Catan data folder created");

	}
	
	public void makeDir(){
		File dataFolder = new File("plugins/Settlers Of Catan");
		if(! (dataFolder.exists() ) ){
			dataFolder.mkdir();
		}
	}


}
