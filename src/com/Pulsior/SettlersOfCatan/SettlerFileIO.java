package com.Pulsior.SettlersOfCatan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Deprecated class that used to act as a hub for all i/o traffic. Will be removed in the next commit,
 * preserving it for now until the plugin is tested will.
 * @author Pulsior
 *
 */

@Deprecated
public class SettlerFileIO {

	boolean dataFileExists = false;
	boolean cardDataFileExists = false;
	boolean playerFileExists = false;
	Logger logger = Bukkit.getLogger();
	/*
	 * Writes names and team colors to "Players.txt"
	 */
	public boolean writeDataFile(String player, String playerColor){
		try{
			PrintWriter output = new PrintWriter(new FileWriter("plugins/Settlers Of Catan/Players.txt", dataFileExists));
			output.println(player);
			output.println(playerColor);
			output.close();
			dataFileExists = true;
		}
		catch(IOException ex){
			return false;
		}
		return true;
	}

	/**
	 * Write the names of newly joined players to a file
	 * @param player
	 * @return
	 */
	public boolean writePlayerFile(String player){
		try{
			PrintWriter output = new PrintWriter(new FileWriter("plugins/Settlers Of Catan/Players2.txt", playerFileExists));
			output.println(player);
			output.close();
			playerFileExists = true;
		}
		catch(IOException ex){
			return false;
		}
		return true;
	}


	/**
	 * Returns an array of all joined players
	 * @return
	 */
	public Player[] getJoinedPlayers(){
		boolean readLine = true;
		ArrayList<String> playerNames = new ArrayList<String>();
		try{
			BufferedReader input = new BufferedReader( new FileReader("plugins/Settlers Of Catan/Players.txt"));
			String line = input.readLine();
			while(line != null){
				if(readLine){
					playerNames.add(line);
				}							
				readLine = !(readLine);
				line = input.readLine();
			}
			input.close();
			Player[] players = new Player[4];
			for(int x = 0; playerNames.size() > x; x++ ){
				players[x] = Bukkit.getServer().getPlayer(playerNames.get(x));
			}
			return players;


		}
		catch(IOException ex){

		}

		return null;
	}
}
