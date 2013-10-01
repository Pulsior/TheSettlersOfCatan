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
	
	public void recognize(Location loc){
		Location nLoc = newLoc(loc);
		nLoc.setZ(nLoc.getZ()-7);
		log.info("North: "+getString(nLoc));
		Location eLoc = newLoc(loc);
		eLoc.setX(eLoc.getX()+7);
		log.info("East: "+getString(eLoc));
		Location sLoc = newLoc(loc);
		sLoc.setZ(sLoc.getZ()+7);
		log.info("South: "+getString(sLoc));
		Location wLoc = newLoc(loc);
		wLoc.setX(wLoc.getX()-7);
		log.info("West: "+ getString(wLoc));
				
	}
	
	public String getString(Location loc){
		if(! (loc.getBlock().getType().equals(Material.SOIL) ) ){
			loc.setX(loc.getX()+1);
			if(loc.getBlock().getType().equals(Material.SOIL)){
				return "WHEAT " + sv.getNumber(Resource.WHEAT, loc);
			}
			else{
				loc.setX(loc.getX()-1);
			}
		}
		
		if(loc.getBlock().getType().equals(Material.STONE) || loc.getBlock().getType().equals(Material.IRON_ORE)){
			return "ORE " + sv.getNumber(Resource.ORE, loc);
		}
		if(loc.getBlock().getType().equals(Material.STAINED_CLAY) || loc.getBlock().getType().equals(Material.COBBLESTONE)){
			return "BRICK " + sv.getNumber(Resource.BRICKS, loc);
		}
		if(loc.getBlock().getType().equals(Material.WATER)){
			loc.setX(loc.getX()+1);
			if(loc.getBlock().getType().equals(Material.WATER)){
				loc.setZ(loc.getZ()+1);
				if(loc.getBlock().getType().equals(Material.WATER)){
					return "SEA";
				}
				else{
					if(loc.getBlock().getType().equals(Material.SOIL)){
						return "WHEAT " + sv.getNumber(Resource.WHEAT, loc);
					}
				}
			}
			else{
				if(loc.getBlock().getType().equals(Material.SOIL)){
					return "WHEAT " + sv.getNumber(Resource.WHEAT, loc);
				}
			}
		}
		if(loc.getBlock().getType().equals(Material.SOIL)){
			return "WHEAT " + sv.getNumber(Resource.WHEAT, loc);
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
				return "WOOD "+ sv.getNumber(Resource.WOOD, loc);
			}
			if(loc.getBlock().getType().equals(Material.AIR)){
				return "SHEEP " + sv.getNumber(Resource.SHEEP, loc);
			}
		}
		if(loc.getBlock().getType().equals(Material.DIRT)){
			loc.setY(loc.getY()+1);
			if(loc.getBlock().getType().equals(Material.LOG)){
				return "WOOD "+ sv.getNumber(Resource.WOOD, loc);
			}
			return "SHEEP " + sv.getNumber(Resource.SHEEP, loc);
			
			
		}
		if(loc.getBlock().getType().equals(Material.SANDSTONE)){
			return "DESERT";
		}
		
		return "NOTHING FOUND";
	}
}
