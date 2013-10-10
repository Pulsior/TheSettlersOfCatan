package com.Pulsior.SettlersOfCatan;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
/**
 * Class to manage the automatic distribution of items. One instance for every player in the game.
 * @author Pulsior
 *
 */
public class SPlayer {

	public int amountOfWood = 0;
	public int amountOfBricks = 0;
	public int amountOfOre = 0;
	public int amountOfWheat = 0;
	public int amountOfWool = 0;
	public int playerNumber;

	List<BoardSpace> claimedSpaces = new ArrayList<BoardSpace>();

	public SPlayer(int playerNumber){
		this.playerNumber = playerNumber;

	}


	public int getPlayerNumber(){
		return this.playerNumber;
	}

	public void addSpace(BoardSpace space){
		claimedSpaces.add(space);
	}
	
	public void giveResources(int rolledNumber, Player player){
		for(BoardSpace space: claimedSpaces){
			if(space.getSpaceNumber() == rolledNumber){
				if(space.getResource().equals(Resource.WOOD)){
					PlayerInventory inv = player.getInventory();
					inv.addItem(new ItemStack(Material.LOG));
				}
				if(space.getResource().equals(Resource.WHEAT)){
					PlayerInventory inv = player.getInventory();
					inv.addItem(new ItemStack(Material.WHEAT));
				}
				if(space.getResource().equals(Resource.SHEEP)){
				
					PlayerInventory inv = player.getInventory();
					inv.addItem(new ItemStack(Material.WOOL));
				}
				if(space.getResource().equals(Resource.ORE)){
					PlayerInventory inv = player.getInventory();
					inv.addItem(new ItemStack(Material.IRON_ORE));
				}
				if(space.getResource().equals(Resource.BRICKS)){
					PlayerInventory inv = player.getInventory();
					inv.addItem(new ItemStack(Material.BRICK));
				}
			}
		}
	}
}
