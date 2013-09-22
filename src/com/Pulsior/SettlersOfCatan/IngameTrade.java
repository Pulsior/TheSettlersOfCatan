package com.Pulsior.SettlersOfCatan;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

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

			Bukkit.broadcastMessage("§6"+player.getName()+" has bought a road!");
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
		if(inv.contains( 17, 1 ) && inv.contains( 45, 1 ) && inv.contains (296, 1) && inv.contains(35, 1)){ //If the inventory contains one wood, brick, wool and wheat///
			ItemStack is1 = readInventory(inv, Material.LOG, 1) ;
			ItemStack is2 = readInventory(inv, Material.BRICK, 1) ;
			ItemStack is3 = readInventory(inv, Material.WOOL, 1) ;
			ItemStack is4 = readInventory(inv, Material.WHEAT, 1) ;
			inv.remove(is1);
			is1.setAmount(is1.getAmount()-1);
			inv.addItem(is1);
			inv.remove(is2);
			is2.setAmount(is2.getAmount()-1);
			inv.addItem(is2);
			inv.remove(is3);
			is3.setAmount(is3.getAmount()-1);
			inv.addItem(is3);
			inv.remove(is4);
			is4.setAmount(is4.getAmount()-1);
			inv.addItem(is4);
			inv.addItem(new ItemStack(Material.COMMAND, 1));
			Bukkit.broadcastMessage("§6"+player.getName()+" has bought a settlement!");
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
		if(inv.contains(296, 2) && inv.contains(15, 3)){
			ItemStack is1 = readInventory(inv, Material.IRON_ORE, 3) ;
			ItemStack is2 = readInventory(inv, Material.WHEAT, 2) ;
			inv.remove(is1);
			is1.setAmount(is1.getAmount()-3);
			inv.addItem(is1);
			inv.remove(is2);
			is2.setAmount(is2.getAmount()-2);
			inv.addItem(is2);
			inv.addItem(new ItemStack(Material.BEDROCK));
			Bukkit.broadcastMessage("§6"+player.getName()+" has bought a city!");
		}
		else{
			player.sendMessage("§cYou are missing the required materials");
		}
	}
	/**
	 * Util method to get needed ItemStacks out of one's inventory
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
		if(color.equalsIgnoreCase("black")){return 15;}
		return 0;

	}
}
