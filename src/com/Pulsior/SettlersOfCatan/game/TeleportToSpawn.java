package com.Pulsior.SettlersOfCatan.game;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class TeleportToSpawn {
	
	public TeleportToSpawn(ArrayList<Player> players){
		World w = Bukkit.getServer().getWorld("soc");
		Location l = new Location(w, -865, 4, -951);
		for(int x = 0; x > players.size(); x++){
			players.get(x).teleport(l);
		}
	}
	public TeleportToSpawn(Player p){
		World w = Bukkit.getServer().getWorld("soc");
		Location l = new Location(w, -872, 60, -954);
		p.teleport(l);
		
	}
}
