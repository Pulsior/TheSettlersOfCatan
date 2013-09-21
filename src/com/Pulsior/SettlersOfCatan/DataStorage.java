package com.Pulsior.SettlersOfCatan;

public class DataStorage {

	SettlerPlayer[] settlers = new SettlerPlayer[4];
	
	public SettlerPlayer getSettler(int arrayPosition){
		return settlers[arrayPosition];
	}
	
	public void addSettler(SettlerPlayer s){
		settlers[0] = s;
	}
}
