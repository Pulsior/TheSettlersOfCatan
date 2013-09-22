package com.Pulsior.SettlersOfCatan;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
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

	private SettlersOfCatan main;
	SettlerFileIO io = new SettlerFileIO();

	boolean shouldBuild = false;

	SettlersCommandExecutor c = new SettlersCommandExecutor();
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		event.setMessage("§f"+event.getMessage() );		
	}
	@EventHandler
	public void onPlayerBuild(BlockPlaceEvent event){
		Block block = event.getBlock();
		if(block.getType().equals(Material.OBSIDIAN)){ //If the block is bedrock, a settlement will be built
			block.setType(Material.WOOD);
			Location loc = block.getLocation();
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


	public short colorCheck(String color){
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
