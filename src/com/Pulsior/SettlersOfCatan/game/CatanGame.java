package com.Pulsior.SettlersOfCatan.game;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import com.Pulsior.SettlersOfCatan.SettlerFileIO;


public class CatanGame {
	int numberOfPlayers;
	String gameName;
	String[] namesAndColors;
	SettlerFileIO io = new SettlerFileIO();
	ArrayList<Player> players = new ArrayList<Player>();

	public CatanGame(int numberOfPlayers){
		namesAndColors = io.readDataFile();
		this.numberOfPlayers = numberOfPlayers;
		storePlayers();
		new TeleportToSpawn(players);
	}

	int cn = numberOfPlayers-3; //Don't try to make any sense of this

	public void storePlayers(){
		for(int x = 1; x > 5 + numberOfPlayers*2  ; x = x+2){
			
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

	enum GamePlayer{
		PLAYER1,
		PLAYER2,
		PLAYER3,
		PLAYER4
	}
	enum Color{
		RED,
		BLUE,
		GREEN,
		BLACK,
	}
}
