package com.Pulsior.SettlersOfCatan.game;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;

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
		
		//Generate new world
		Bukkit.getServer().createWorld(new WorldCreator("soc"));
		Bukkit.getLogger().info("The Settlers of Catan world has been generated");
		
		


	}


}
