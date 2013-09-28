package com.Pulsior.SettlersOfCatan.game;


import org.bukkit.entity.Player;

import com.Pulsior.SettlersOfCatan.SettlerFileIO;
import com.Pulsior.SettlersOfCatan.SettlersScoreboard;


public class CatanGame {
	int numberOfPlayers;
	String gameName;
	String[] namesAndColors;
	int turn = 0;
	SettlerFileIO io = new SettlerFileIO();
	Player[] players;
	SettlersScoreboard scoreboard;

	public CatanGame(){

		//Instantiate the SettlerScoreBoard
		new SettlersScoreboard();
		
	}

}
