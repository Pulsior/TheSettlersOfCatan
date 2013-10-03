package com.Pulsior.SettlersOfCatan;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;


public class SurfaceRecognition {
	
	SpaceValues sv = new SpaceValues();
	Logger log = Bukkit.getLogger();

	public Location newLoc(Location loc){
		return new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
	}
	
	/**
	 * Return an array of spaces which can be claimed by the player
	 * @param loc
	 * @return
	 */
	public BoardSpace[] recognize(Location loc){
		loc.setY(loc.getY()-1);
		BoardSpace[] spaces = new BoardSpace[4];
		
		Location nLoc = newLoc(loc);
		nLoc.setZ(nLoc.getZ()-7);
		Resource northResource = getResource(nLoc);
		log.info("North: "+northResource);
		spaces[0] = new BoardSpace(northResource, nLoc, sv.getNumber(northResource, nLoc));
		
		Location eLoc = newLoc(loc);
		eLoc.setX(eLoc.getX()+7);
		Resource eastResource = getResource(eLoc);
		log.info("East: "+eastResource);
		spaces[1] = new BoardSpace(eastResource, eLoc, sv.getNumber(eastResource, eLoc));
		
		Location sLoc = newLoc(loc);
		sLoc.setZ(sLoc.getZ()+7);
		Resource southResource = getResource(sLoc);
		log.info("South: "+southResource);
		spaces[2] = new BoardSpace(southResource, sLoc, sv.getNumber(southResource, sLoc));
		
		Location wLoc = newLoc(loc);
		wLoc.setX(wLoc.getX()-7);
		Resource westResource  = getResource(wLoc);
		log.info("West: "+ westResource);
		spaces[3] = new BoardSpace(westResource, sLoc, sv.getNumber(westResource, wLoc));
		return spaces;
	}
	
	public Resource getResource(Location loc){
		if(! (loc.getBlock().getType().equals(Material.SOIL) ) ){
			loc.setX(loc.getX()+1);
			if(loc.getBlock().getType().equals(Material.SOIL)){
				return Resource.WHEAT;
			}
			else{
				loc.setX(loc.getX()-1);
			}
		}
		
		if(loc.getBlock().getType().equals(Material.STONE) || loc.getBlock().getType().equals(Material.IRON_ORE)){
			return Resource.ORE;
		}
		if(loc.getBlock().getType().equals(Material.STAINED_CLAY) || loc.getBlock().getType().equals(Material.COBBLESTONE)){
			return Resource.BRICKS;
		}
		if(loc.getBlock().getType().equals(Material.WATER)){
			loc.setX(loc.getX()+1);
			if(loc.getBlock().getType().equals(Material.WATER)){
				loc.setZ(loc.getZ()+1);
				if(loc.getBlock().getType().equals(Material.WATER)){
					return null;
				}
				else{
					if(loc.getBlock().getType().equals(Material.SOIL)){
						return Resource.WHEAT;
					}
				}
			}
			else{
				if(loc.getBlock().getType().equals(Material.SOIL)){
					return Resource.WHEAT;
				}
			}
		}
		if(loc.getBlock().getType().equals(Material.SOIL)){
			return Resource.WHEAT;
		}
		if(loc.getBlock().getType().equals(Material.GRASS)){
			loc.setY(loc.getY()+4);
			Location loc2 = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
			Location loc3 = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
			Location loc4 = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
			loc2.setY(loc2.getY()-1);
			loc3.setY(loc3.getY()-2);
			loc4.setY(loc4.getY()-3);
			if(loc.getBlock().getType().equals(Material.LEAVES) || loc.getBlock().getType().equals(Material.LOG) || loc2.getBlock().getType().equals(Material.LEAVES) || loc2.getBlock().getType().equals(Material.LOG) || loc3.getBlock().getType().equals(Material.LEAVES) || loc3.getBlock().getType().equals(Material.LOG) || loc4.getBlock().getType().equals(Material.LEAVES) || loc4.getBlock().getType().equals(Material.LOG)  ){
				return Resource.WOOD;
			}
			if(loc.getBlock().getType().equals(Material.AIR)){
				return Resource.SHEEP;
			}
		}
		if(loc.getBlock().getType().equals(Material.DIRT)){
			loc.setY(loc.getY()+1);
			if(loc.getBlock().getType().equals(Material.LOG)){
				return Resource.WOOD;
			}
			return Resource.SHEEP;
			
			
		}
		
		return Resource.NONE;
	}
}
