package com.Pulsior.SettlersOfCatan.game;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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

		//Set game mode to Adventure
		Bukkit.setDefaultGameMode(GameMode.ADVENTURE);
		//Instantiate the SettlerScoreBoard
		new SettlersScoreboard();
		
	}

	int cn = numberOfPlayers-3; //Don't try to make any sense of this

	public void getJoinedPlayers(){
		players = io.getJoinedPlayers();
	}

	public void endTurn(){
		if(turn != players.length-1){
			turn = turn+1;
		}
		else{
			turn = 0;

		}
	}
	/**
	 * Set the name of the game
	 * @param name
	 */

	public void setName(String name){
		this.gameName = name;
	}

	/**
	 * Get the name of the game
	 * @return
	 */
	public String getName(){
		return this.gameName;
	}

	public Player[] getParticipants(){
		return null;
	}
	
	public void setupScoreboard(){
		
	}

}
