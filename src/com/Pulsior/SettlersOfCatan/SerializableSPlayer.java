package com.Pulsior.SettlersOfCatan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.Pulsior.SettlersOfCatan.board.SerializableBoardSpace;
import com.Pulsior.SettlersOfCatan.game.Color;

/**
 * Slightly altered version of SPlayer, modified so it can be serialized.
 * @author Pulsior
 *
 */

public class SerializableSPlayer implements Serializable{
	
	private static final long serialVersionUID = -7664805705794397236L;
	
	int playerNumber;
	int score;
	String playerName;
	public Color color;
	List<SerializableBoardSpace> claimedSpaces = new ArrayList<SerializableBoardSpace>();
	
	public SerializableSPlayer(int playerNumber, String playerName, int score){
		this.playerNumber = playerNumber;
		this.playerName = playerName;
		this.score = score;
	}
	
	public void addSpace(SerializableBoardSpace space){
		claimedSpaces.add(space);
	}
	
	public int getNumber(){
		return playerNumber;
	}
	
	public List<SerializableBoardSpace> getClaimed(){
		return claimedSpaces;
	}
	
	public String getPlayerName(){
		return playerName;
	}
	
	public int getScore(){
		return score;
	}
}
