package com.Pulsior.SettlersOfCatan.game;


import org.bukkit.entity.Player;

import com.Pulsior.SettlersOfCatan.SettlerFileIO;


public class CatanGame {
	int numberOfPlayers;
	String gameName;
	String[] namesAndColors;
	int turn = 0;
	SettlerFileIO io = new SettlerFileIO();
	Player[] players;
	SettlersScoreboard scoreboard;
	public static boolean launched = false;

	public CatanGame(){

		//Instantiate the SettlerScoreBoard
		new SettlersScoreboard();
		launched = true;
		
	}
	
	

}
