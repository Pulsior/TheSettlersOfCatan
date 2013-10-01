package com.Pulsior.SettlersOfCatan;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * Generates cities and settlements. This code is really long and cumbersome
 * since Bukkit seems to hate for-loops
 * @author Pulsior
 *
 */

public class StructureGen {
	
	
	public void buildCity(Location loc, byte data, Player player){
		loc.setY(loc.getY()-1);
		loc.getBlock().setType(Material.STONE);
		loc.setX(loc.getX()+1);
		loc.getBlock().setType(Material.STONE);
		loc.setX(loc.getX()-2);
		loc.getBlock().setType(Material.STONE);
		loc.setX(loc.getX()+1);
		loc.setZ(loc.getZ()+1);
		loc.getBlock().setType(Material.STONE);
		loc.setX(loc.getX()+1);
		loc.getBlock().setType(Material.STONE);
		loc.setX(loc.getX()-2);
		loc.getBlock().setType(Material.STONE);
		loc.setX(loc.getX()+1);
		loc.setZ(loc.getZ()-2);
		loc.getBlock().setType(Material.STONE);
		loc.setX(loc.getX()+1);
		loc.getBlock().setType(Material.STONE);
		loc.setX(loc.getX()-2);
		loc.getBlock().setType(Material.STONE);
		loc.setX(loc.getX()+1);
		loc.setY(loc.getY()+1);
		loc.setZ(loc.getZ()+1);
		loc.getBlock().setType(Material.STONE);
		loc.setX(loc.getX()+1);
		loc.getBlock().setType(Material.STONE);
		loc.setX(loc.getX()-2);
		loc.getBlock().setType(Material.STONE);
		loc.setX(loc.getX()+1);
		loc.setZ(loc.getZ()+1);
		loc.getBlock().setType(Material.STONE);
		loc.setX(loc.getX()+1);
		loc.getBlock().setType(Material.STONE);
		loc.setX(loc.getX()-2);
		loc.getBlock().setType(Material.STONE);
		loc.setX(loc.getX()+1);
		loc.setZ(loc.getZ()-2);
		loc.getBlock().setType(Material.STONE);
		loc.setX(loc.getX()+1);
		loc.getBlock().setType(Material.STONE);
		loc.setX(loc.getX()-2);
		loc.getBlock().setType(Material.STONE);
		loc.setX(loc.getX()+1);
		loc.setY(loc.getY()+1);
		loc.setZ(loc.getZ()+1);
		loc.getBlock().setType(Material.WOOL);
		loc.getBlock().setData(data);
		loc.setX(loc.getX()+1);
		loc.getBlock().setType(Material.WOOL);
		loc.getBlock().setData(data);
		loc.setX(loc.getX()-2);
		loc.getBlock().setType(Material.WOOL);
		loc.getBlock().setData(data);
		loc.setX(loc.getX()+1);
		loc.setZ(loc.getZ()+1);
		loc.getBlock().setType(Material.WOOL);
		loc.getBlock().setData(data);
		loc.setX(loc.getX()+1);
		loc.getBlock().setType(Material.WOOL);
		loc.getBlock().setData(data);
		loc.setX(loc.getX()-2);
		loc.getBlock().setType(Material.WOOL);
		loc.getBlock().setData(data);
		loc.setX(loc.getX()+1);
		loc.setZ(loc.getZ()-2);
		loc.getBlock().setType(Material.WOOL);
		loc.getBlock().setData(data);
		loc.setX(loc.getX()+1);
		loc.getBlock().setType(Material.WOOL);
		loc.getBlock().setData(data);
		loc.setX(loc.getX()-2);
		loc.getBlock().setType(Material.WOOL);
		loc.getBlock().setData(data);
		loc.setX(loc.getX()+1);
	}
	public void buildSettlement(Location loc, byte data){
		loc.getBlock().setType(Material.WOOD);
		loc.setX(loc.getX()+1);
		loc.getBlock().setType(Material.WOOD);
		loc.setZ(loc.getZ()-1);
		loc.getBlock().setType(Material.WOOD);
		loc.setX(loc.getX()-1);
		loc.getBlock().setType(Material.WOOD);
		loc.setY(loc.getY()+1);
		loc.getBlock().setType(Material.WOOL);
		loc.getBlock().setData(data);
		loc.setX(loc.getX()+1);
		loc.getBlock().setType(Material.WOOL);
		loc.getBlock().setData(data);
		loc.setZ(loc.getZ()+1);
		loc.getBlock().setType(Material.WOOL);
		loc.getBlock().setData(data);
		loc.setX(loc.getX()-1);
		loc.getBlock().setType(Material.WOOL);
		loc.getBlock().setData(data);
	}
	
	public byte colorCheck(String color){
		if(color.equalsIgnoreCase("red")){return 14;}
		if(color.equalsIgnoreCase("blue")){return 11;}
		if(color.equalsIgnoreCase("green")){return 13;}
		if(color.equalsIgnoreCase("black")){return 15;}
		return 0;

	}
}
