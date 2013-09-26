package com.Pulsior.SettlersOfCatan.game;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import com.Pulsior.SettlersOfCatan.SettlerFileIO;


public class CatanGame {
	int numberOfPlayers;
	String gameName;
	String[] namesAndColors;
	
	SettlerFileIO io = new SettlerFileIO();
	Player[] players = new Player[4];

	public CatanGame(){
		
		//Set game mode to Adventure
		Bukkit.setDefaultGameMode(GameMode.ADVENTURE);
	}

	int cn = numberOfPlayers-3; //Don't try to make any sense of this

	public void storePlayers(){
		for(int x = 1; x > 5 + cn*2  ; x = x+2){
			
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

}
