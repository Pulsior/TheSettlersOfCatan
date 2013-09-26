package com.Pulsior.SettlersOfCatan;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Simple EventListener to make all chat messages white.
 * Chat messages will have the team color of their sender otherwise
 * @author Pulsior
 *
 */



public class SettlersEventListener implements Listener{
	
	
	static boolean workAroundBug = false;

	SettlerFileIO io = new SettlerFileIO();

	public static boolean shouldBuild = false;
	StructureGen gen = new StructureGen();

	SettlersCommandExecutor c = new SettlersCommandExecutor();
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		event.setMessage("�f"+event.getMessage() );		
	}
	@EventHandler
	public void onPlayerBuild(BlockPlaceEvent event){
		Block block = event.getBlock();
		if(block.getType().equals(Material.COMMAND)){ //If the block is a command block, a settlement will be built
			Location loc = block.getLocation();
			gen.buildSettlement(loc, colorCheck( getColor(event.getPlayer().getName() ) )) ;
		}
		if(block.getType().equals(Material.BEDROCK)){
			Location loc = block.getLocation();
			Location loc2 = loc;
			loc2.setY(loc2.getY()-1);
			if(loc2.getBlock().getType().equals(Material.WOOL)){
				gen.buildCity(loc, colorCheck( getColor(event.getPlayer().getName() ) ));	
			}
			else{
				event.setCancelled(true);
				event.getPlayer().sendMessage("�cYou need to build a city on top of an existing village");
			}


		}
	}


	public byte colorCheck(String color){
		if(color.equalsIgnoreCase("red")){return 14;}
		if(color.equalsIgnoreCase("blue")){return 11;}
		if(color.equalsIgnoreCase("green")){return 13;}
		if(color.equalsIgnoreCase("black")){return 15;}
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

	public void setBuild(){
		shouldBuild = true;
	}


}
