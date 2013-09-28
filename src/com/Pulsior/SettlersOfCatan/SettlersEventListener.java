package com.Pulsior.SettlersOfCatan;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Multi-purpose EventListener:
 * 	- To intercept chat messages and make them white, for the messages would have the color of their sender otherwise
 *  - To detect whenever bedrock or command blocks are placed, and to turn them into structures
 * @author Pulsior
 *
 */



public class SettlersEventListener implements Listener{
	
	
	SettlerFileIO io = new SettlerFileIO();

	StructureGen gen = new StructureGen();

	SettlersCommandExecutor c = new SettlersCommandExecutor();
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		event.setMessage("§f"+event.getMessage() );		
	}
	@EventHandler
	public void onPlayerBuild(BlockPlaceEvent event){
		Block block = event.getBlock();
		if(block.getType().equals(Material.COMMAND)){ //If the block is a command block, a settlement will be built
			Location loc = block.getLocation();
			gen.buildSettlement(loc, colorCheck( getColor(event.getPlayer().getName() ) )) ;
		}
		if(block.getType().equals(Material.BEDROCK)){ //If the block is bedrock, a city will be built
			Location loc = block.getLocation();
			Location loc2 = loc;
			loc2.setY(loc2.getY()-1);
			if(loc2.getBlock().getType().equals(Material.WOOL)){ //But only if the block beneath is wool
				gen.buildCity(loc, colorCheck( getColor(event.getPlayer().getName() ) ));	
			}
			else{
				event.setCancelled(true);
				event.getPlayer().sendMessage("§cYou need to build a city on top of an existing village");
			}


		}
	}


	public byte colorCheck(String color){
		if(color.equalsIgnoreCase("red")){return 14;}
		if(color.equalsIgnoreCase("blue")){return 11;}
		if(color.equalsIgnoreCase("green")){return 13;}
		if(color.equalsIgnoreCase("yellow")){return 4;}
		return 0;

	}

	public String getColor(String playerName){

		String[] namesAndColors = io.readDataFile();
		for(int x = 0; x < namesAndColors.length; x++){

			if(namesAndColors[x].equalsIgnoreCase(playerName)){
				return namesAndColors[x+1];
			}

		}
		return null;
	}


}
