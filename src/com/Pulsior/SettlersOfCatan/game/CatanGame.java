package com.Pulsior.SettlersOfCatan.game;


import org.bukkit.entity.Player;


public class CatanGame {
	int numberOfPlayers;
	String gameName;
	String[] namesAndColors;
	int turn = 0;
	Player[] players;
	SettlersScoreboard scoreboard;
	public static boolean launched = false;

	public CatanGame(){

		//Instantiate the SettlerScoreBoard
		new SettlersScoreboard();
		launched = true;
		
	}
	
	

}
