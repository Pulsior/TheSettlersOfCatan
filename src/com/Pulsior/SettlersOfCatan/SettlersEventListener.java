package com.Pulsior.SettlersOfCatan;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

/**
 * Multi-purpose EventListener:
 * 	- To intercept chat messages and make them white, for the messages would have the color of their sender otherwise
 *  - To detect whenever bedrock or command blocks are placed, and to turn them into structures
 * @author Pulsior
 *
 */



public class SettlersEventListener implements Listener{


	SettlerFileIO io = new SettlerFileIO();
	SurfaceRecognition recog = new SurfaceRecognition();
	StructureGen gen = new StructureGen();
	SettlersCommandExecutor c = new SettlersCommandExecutor();

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		event.setMessage("§f"+event.getMessage() );		
	}

	@EventHandler
	public void onPlayerBuild(BlockPlaceEvent event){
		Block block = event.getBlock();
		Player player = event.getPlayer();
		if(block.getType().equals(Material.COMMAND)){ //If the block is a command block, a settlement will be built
			Location loc = block.getLocation();
			gen.buildSettlement(loc, colorCheck( getColor(player.getName() ) )) ;
			BoardSpace[] spaces = recog.recognize(block.getLocation());
			SPlayer p = SettlersOfCatan.sPlayers[ getMetadata(player, "number", SettlersOfCatan.plugin) -1 ]; //TODO Fix 
			for(int x = 0; x < 3; x++){
				BoardSpace space = spaces[x];
				p.addSpace(space);
			}
		}
		if(block.getType().equals(Material.BEDROCK)){ //If the block is bedrock, a city will be built
			Location loc = block.getLocation();
			Location loc2 = loc;
			loc2.setY(loc2.getY()-1);
			if(loc2.getBlock().getType().equals(Material.WOOL)){ //But only if the block beneath is wool
				gen.buildCity(loc, colorCheck( getColor(player.getName() ) ), player);
				Location location = block.getLocation();
				location.setY(location.getY()-2);
				BoardSpace[] spaces = recog.recognize(location);
				SPlayer p = SettlersOfCatan.sPlayers[ getMetadata(player, "number", SettlersOfCatan.plugin) -1 ]; //TODO Fix 
				for(int x = 0; x < 3; x++){
					BoardSpace space = spaces[x];
					p.addSpace(space);
				}
			}
			else{
				event.setCancelled(true);
				player.sendMessage("§cYou need to build a city on top of an existing village");
			}
		}

	}
	/**
	 * If a player clicks a spot with a blaze rod, the resources of surrounding spaces are logged to the console
	 * @param event
	 */
	@EventHandler
	public void onInteract(PlayerInteractEvent event){
		Block block = event.getClickedBlock();
		if(block != null){
			if(event.getItem() != null){
				if(event.getItem().equals(new ItemStack(Material.BLAZE_ROD))){
					recog.recognize(block.getLocation());
				}
				if(event.getItem().equals(new ItemStack(Material.STICK))){
					block.setType(Material.AIR);
				}
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
	
	
	
	
	public int getMetadata(Player player, String key, Plugin plugin){
		List<MetadataValue> values = player.getMetadata(key);  
		for(MetadataValue value : values){
			//if(value.getOwningPlugin().getDescription().getName().equals(plugin.getDescription().getName() ) ){
				return value.asInt();
			//}
		}
		return 0;
	}

}
