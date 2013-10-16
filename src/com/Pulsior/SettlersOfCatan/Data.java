package com.Pulsior.SettlersOfCatan;

import java.io.Serializable;

import com.Pulsior.SettlersOfCatan.game.Color;

/**
 * Class used to store game data. Is serializable.
 * @author Pulsior
 *
 */

public class Data implements Serializable{
	
	private static final long serialVersionUID = 1104799003874786940L;
	public boolean redUsed = false;
	public boolean yellowUsed = false;
	public boolean greenUsed = false;
	public boolean blueUsed = false;
	public String[] players = new String[4];
	public Color[] colors = new Color[4];
	public int amountOfPlayers = 0;
	public int inTurn = 0;
	
	
	public Color getColor(String name){
		for(int x = 0; x < 4; x++){
			String result = players[x];
			if(result != null){
				if(result.equalsIgnoreCase(name)){
					return colors[x];
				}
			}
		}
		return null;
	}
	
	
	public void setColor(Color color, int position){
		colors[position] = color;
	}
}
