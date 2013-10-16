package com.Pulsior.SettlersOfCatan.board;

import org.bukkit.Location;
/**
 * Class used to store spaces on the board in an SPlayer object
 * @author Pulsior
 */

public class BoardSpace{
	
	

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

	public String getResourceAsString(){
		switch(resource){

		case WOOD:
			return "WOOD";
		case ORE:
			return "ORE";

		case SHEEP:
			return "SHEEP";

		case WHEAT:			
			return "WHEAT";

		case BRICKS:
			return "BRICKS";

		case NONE:
			return "NONE";
		}

		return null;
	}

	public Location getLocation(){
		return location;
	}
	public int getSpaceNumber(){
		return number;
	}

}
