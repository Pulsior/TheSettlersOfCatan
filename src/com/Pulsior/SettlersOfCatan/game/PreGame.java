package com.Pulsior.SettlersOfCatan.game;

import org.bukkit.Bukkit;

import com.Pulsior.SettlersOfCatan.SettlerFileIO;

/**
 * Class instantiated as pregame, where players can join but can't build stuff yet.
 * @author Pulsior
 *
 */

public class PreGame {

	public static boolean preGame = false;
	SettlerFileIO io = new SettlerFileIO();

	public PreGame(){

		//Indicate that pregame has been launched
		preGame = true;
		Bukkit.getLogger().info("[Settlers of Catan] Started pregame, players can join the game now");

		//Create folder to store files
		io.makeDir();
		Bukkit.getLogger().info("[Settlers of Catan] Settlers of Catan data folder created");

	}


}
