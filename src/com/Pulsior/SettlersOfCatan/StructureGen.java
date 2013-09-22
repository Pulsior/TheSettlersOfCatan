package com.Pulsior.SettlersOfCatan;

import org.bukkit.Location;
import org.bukkit.Material;

/**
 * Generates cities and settlements. This code is really long and cumbersome
 * since Bukkit seems to hate for loops
 * @author Pulsior
 *
 */

public class StructureGen {
	
	public void buildCity(Location loc){
		
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
		loc.setX(loc.getX()+1);
		loc.getBlock().setType(Material.WOOL);
		loc.setX(loc.getX()-2);
		loc.getBlock().setType(Material.WOOL);
		loc.setX(loc.getX()+1);
		loc.setZ(loc.getZ()+1);
		loc.getBlock().setType(Material.WOOL);
		loc.setX(loc.getX()+1);
		loc.getBlock().setType(Material.WOOL);
		loc.setX(loc.getX()-2);
		loc.getBlock().setType(Material.WOOL);
		loc.setX(loc.getX()+1);
		loc.setZ(loc.getZ()-2);
		loc.getBlock().setType(Material.WOOL);
		loc.setX(loc.getX()+1);
		loc.getBlock().setType(Material.WOOL);
		loc.setX(loc.getX()-2);
		loc.getBlock().setType(Material.WOOL);
		loc.setX(loc.getX()+1);
	}
	public void buildSettlement(Location loc){
		loc.getBlock().setType(Material.WOOD);
		loc.setX(loc.getX()+1);
		loc.getBlock().setType(Material.WOOD);
		loc.setZ(loc.getZ()-1);
		loc.getBlock().setType(Material.WOOD);
		loc.setX(loc.getX()-1);
		loc.getBlock().setType(Material.WOOD);
		loc.setY(loc.getY()+1);
		loc.getBlock().setType(Material.WOOL);
		loc.setX(loc.getX()+1);
		loc.getBlock().setType(Material.WOOL);
		loc.setZ(loc.getZ()+1);
		loc.getBlock().setType(Material.WOOL);
		loc.setX(loc.getX()-1);
		loc.getBlock().setType(Material.WOOL);
	}
}
