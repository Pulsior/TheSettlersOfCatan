package com.Pulsior.SettlersOfCatan;

import com.Pulsior.SettlersOfCatan.TeamColor.ColorBlack;
import com.Pulsior.SettlersOfCatan.TeamColor.ColorBlue;
import com.Pulsior.SettlersOfCatan.TeamColor.ColorGreen;
import com.Pulsior.SettlersOfCatan.TeamColor.ColorRed;
import com.Pulsior.SettlersOfCatan.TeamColor.TeamColor;


public class SettlerPlayer {

	public String playerName;
	public TeamColor personalColor;

	public SettlerPlayer(String playerName, String color){
		this.playerName = playerName;
	}

	public void setPlayerColor(String color){
		if(color.equalsIgnoreCase("red")){
			personalColor = new ColorRed();
		}

		if(color.equalsIgnoreCase("green")){
			personalColor = new ColorGreen();
		}
		if(color.equalsIgnoreCase("blue")){
			personalColor = new ColorBlue();
		}
		if(color.equalsIgnoreCase("black")){
			personalColor = new ColorBlack();
		}

	}
	
	public TeamColor getColor(){
		return personalColor;
	}
	
	public String getPlayerName(){
		return "3";
	}

}
