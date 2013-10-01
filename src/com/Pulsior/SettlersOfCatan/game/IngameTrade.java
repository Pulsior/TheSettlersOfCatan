package com.Pulsior.SettlersOfCatan.game;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.Pulsior.SettlersOfCatan.SettlersOfCatan;


/**
 * Allows the player to trade in raw materials for useful items
 * @author Pulsior
 *
 */
public class IngameTrade {


	/**
	 * Let a player trade in resources for a road in his/her personal color
	 * @param player
	 */
	public void buyRoad(Player player, String color){
		PlayerInventory inv = player.getInventory();
		ItemStack wood = new ItemStack(Material.LOG, 1);
		ItemStack bricks = new ItemStack(Material.BRICK, 1);
		if(inv.containsAtLeast(wood, 1 )&&inv.containsAtLeast( bricks , 1 ) ){ //If the inventory contains one wood and brick...
			ItemStack is1 = readInventory(inv, Material.LOG, 1) ;
			ItemStack is2 = readInventory(inv, Material.BRICK, 1) ;
			inv.remove(is1);
			is1.setAmount(is1.getAmount()-1);
			inv.addItem(is1);
			inv.remove(is2);
			is2.setAmount(is2.getAmount()-1);
			inv.addItem(is2);

			short dataValue = colorCheck(color);
			ItemStack carpets = new ItemStack(Material.CARPET, 6, dataValue);
			inv.addItem(carpets);

			Bukkit.broadcastMessage("§6"+player.getName()+" has bought a road! ");


		}
		else{
			player.sendMessage("§cYou are missing the required materials");
		}

	}
	/**
	 * Let a player trade in resources for an auto-building settlement
	 * @param player
	 */
	public void buySettlement(Player player){
		PlayerInventory inv = player.getInventory();
		if(inv.contains( Material.BRICK, 1 ) && inv.contains( Material.LOG, 1 ) && inv.contains (Material.WOOL, 1) && inv.contains(Material.WHEAT, 1)){ //If the inventory contains one wood, brick, wool and wheat///
			ItemStack is1 = readInventory(inv, Material.LOG, 1) ;
			ItemStack is2 = readInventory(inv, Material.BRICK, 1) ;
			ItemStack is3 = readInventory(inv, Material.WOOL, 1) ;
			ItemStack is4 = readInventory(inv, Material.WHEAT, 1) ;
			inv.remove(is1); //Remove the stack from the inventory
			is1.setAmount(is1.getAmount()-1); //Subtract one item from the ItemStack
			inv.addItem(is1); //Return the stack to the inventory
			inv.remove(is2);
			is2.setAmount(is2.getAmount()-1);
			inv.addItem(is2);
			inv.remove(is3);
			is3.setAmount(is3.getAmount()-1);
			inv.addItem(is3);
			inv.remove(is4);
			is4.setAmount(is4.getAmount()-1);
			inv.addItem(is4);
			inv.addItem(new ItemStack(Material.COMMAND, 1)); //Give the player a command block
			Bukkit.broadcastMessage("§6"+player.getName()+" has bought a settlement and earned 1 victory point!");
			Scoreboard board = SettlersOfCatan.board;
			Objective o = board.getObjective("victory"); //Add a victory point to the player's score
			Score score = o.getScore(player);
			score.setScore(score.getScore() + 1);
			SettlersOfCatan.board = board;
			for (Player player2 : Bukkit.getServer().getOnlinePlayers()) player2.setScoreboard(SettlersOfCatan.board); //Update the scoreboard

		}
		else{
			player.sendMessage("§cYou are missing the required materials");
		}
	}
	/**
	 * Let a player trade in resources for an auto-building city
	 * @param player
	 */
	public void buyCity(Player player){
		PlayerInventory inv = player.getInventory();
		if(inv.contains(Material.WHEAT, 2) && inv.contains(Material.IRON_ORE, 3)){
			ItemStack is1 = readInventory(inv, Material.IRON_ORE, 3) ;
			ItemStack is2 = readInventory(inv, Material.WHEAT, 2) ;
			inv.remove(is1);
			is1.setAmount(is1.getAmount()-3);
			inv.addItem(is1);
			inv.remove(is2);
			is2.setAmount(is2.getAmount()-2);
			inv.addItem(is2);
			inv.addItem(new ItemStack(Material.BEDROCK)); //Give the player one piece of bedrock
			Bukkit.broadcastMessage("§6"+player.getName()+" has bought a city and earned another victory point!");
			Scoreboard board = SettlersOfCatan.board;
			Objective o = board.getObjective("victory");
			Score score = o.getScore(player);
			score.setScore(score.getScore() + 1);
			SettlersOfCatan.board = board;
			for (Player player2 : Bukkit.getServer().getOnlinePlayers()) player2.setScoreboard(SettlersOfCatan.board);
			SettlersOfCatan.board = board;

		}
		else{
			player.sendMessage("§cYou are missing the required materials");
		}
	}


	/**
	 * Utility method to get needed ItemStacks out of one's inventory
	 * @param inv
	 * @param wishedMaterial
	 * @param wishedSize
	 * @return
	 */
	public ItemStack readInventory(PlayerInventory inv, Material wishedMaterial, int wishedSize){
		ItemStack[] items = inv.getContents();
		for(int x = 0; x < items.length; x++){
			Material itemStackMaterial;
			if(items[x] != null){
				int amountOfItemsInStack = items[x].getAmount();
				itemStackMaterial = items[x].getType();
				if(amountOfItemsInStack >= wishedSize && itemStackMaterial.equals(wishedMaterial)){
					return items[x];
				}
			}

		}
		return null;
	}
	/**
	 * Check one's color and turns it into the adequate short
	 * @param color
	 * @return
	 */
	public short colorCheck(String color){
		if(color.equalsIgnoreCase("red")){return 14;}
		if(color.equalsIgnoreCase("blue")){return 11;}
		if(color.equalsIgnoreCase("green")){return 13;}
		if(color.equalsIgnoreCase("yellow")){return 4;}
		return 0;

	}


}
