package com.Pulsior.SettlersOfCatan.game;

import org.bukkit.Bukkit;

import com.Pulsior.SettlersOfCatan.SettlerFileIO;

public class PreGame {

	public static boolean preGame = false;
	

	SettlerFileIO io = new SettlerFileIO();

	public PreGame(){

		//Indicate that pregame has been launched
		preGame = true;
		Bukkit.getLogger().info("Started pregame, players can join the game now");

		//Create folder to store files
		io.makeDir();
		Bukkit.getLogger().info("Settlers of Catan data folder created");

	}


}
