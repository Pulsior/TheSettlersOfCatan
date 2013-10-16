package com.Pulsior.SettlersOfCatan;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.Pulsior.SettlersOfCatan.board.BoardSpace;
import com.Pulsior.SettlersOfCatan.board.Resource;
/**
 * Class to manage the automatic distribution of items. One instance for every player in the game.
 * @author Pulsior
 *
 */
public class SPlayer {

	/**
	 * 
	 */
	
	public int playerNumber;
	String playerName;

	List<BoardSpace> claimedSpaces = new ArrayList<BoardSpace>();

	public SPlayer(int playerNumber, String playerName){
		this.playerNumber = playerNumber;
		this.playerName = playerName;

	}

	public int getPlayerNumber(){
		return this.playerNumber;
	}

	public void addSpace(BoardSpace space){
		claimedSpaces.add(space);
	}
	
	public List<BoardSpace> getClaimed(){
		List<BoardSpace> list = claimedSpaces;
		return list;
	}
	
	public String getPlayerName(){
		return playerName;
	}
	
	public void giveResources(int rolledNumber, Player player){
		PlayerInventory inv = player.getInventory();
		for(BoardSpace space: claimedSpaces){
			if(space.getSpaceNumber() == rolledNumber){
				if(space.getResource().equals(Resource.WOOD)){
					inv.addItem(new ItemStack(Material.LOG));
				}
				if(space.getResource().equals(Resource.WHEAT)){
					inv.addItem(new ItemStack(Material.WHEAT));
				}
				if(space.getResource().equals(Resource.SHEEP)){
					inv.addItem(new ItemStack(Material.WOOL));
				}
				if(space.getResource().equals(Resource.ORE)){
					inv.addItem(new ItemStack(Material.IRON_ORE));
				}
				if(space.getResource().equals(Resource.BRICKS)){
					inv.addItem(new ItemStack(Material.BRICK));
				}
			}
		}
	}
	

	
	public boolean savePlayer(){
		try{  // Catch errors in I/O if necessary.
		    // Open a file to write to, named SavedObj.sav.
		    FileOutputStream saveFile=new FileOutputStream("plugins/Settlers Of Catan/Player.sav");

		    // Create an ObjectOutputStream to put objects into save file.
		    ObjectOutputStream save = new ObjectOutputStream(saveFile);

		    // Now we do the save.
		    save.writeObject(this);

		    // Close the file.
		    save.close(); // This also closes saveFile.
		    }
		    catch(Exception exc){
		    exc.printStackTrace(); // If there was an error, print the info.
		    }
		return false;
	}
	
	public Player getPlayer(){
		return Bukkit.getServer().getPlayer(playerName);
	}
	
}
