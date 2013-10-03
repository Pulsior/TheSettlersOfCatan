package com.Pulsior.SettlersOfCatan;

import org.bukkit.Location;

public class BoardSpace {

	Resource resource;
	Location location;
	int number;
	
	public BoardSpace(Resource resource, Location location, int number){
		this.resource = resource;
		this.location = location;
		this.number = number;
	}
	
	public Resource getResource(){
		return resource;
	}
	public Location getLocation(){
		return location;
	}
	public int getSpaceNumber(){
		return number;
	}
	
}
