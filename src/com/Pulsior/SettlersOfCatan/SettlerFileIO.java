package com.Pulsior.SettlersOfCatan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**Class designed for interacting with files, all I/O traffic goes through the SettlerFileIO class
 * 
 * @author Pulsior
 *
 */
public class SettlerFileIO {

	boolean dataFileExists = false;
	boolean cardDataFileExists = false;
	/*
	 * Writes names and team colors to "SettlersOfCatanPlayers.txt"
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

	/*
	 * Reads out the "SetlersOfCatanPlayers.txt" file and stores the acquired data in an String[] array.
	 */
	public String[] readDataFile(){
		ArrayList<String> ar = new ArrayList<String>(); //Starting with an ArrayList, for we don't know how many names will be in the file
		String[] namesAndColors; //Switch to String[] later, since arrays are far simpler to handle than ArrayLists
		try{
			BufferedReader input = new BufferedReader( new FileReader("plugins/Settlers Of Catan/Players.txt"));
			String line = input.readLine();
			while(line != null){
				ar.add(line);
				line = input.readLine();
			}
			namesAndColors = new String[ar.size()];
			for(int x = 0; x < ar.size(); x++){
				namesAndColors[x] = ar.get(x);
			}
			input.close();
			return namesAndColors;
		}
		catch(IOException ex){

		}
		return null; //Return null if everything failed...
	}


	public void makeDir(){
		File dataFolder = new File("plugins/Settlers Of Catan");
		if(! (dataFolder.exists() ) ){
			dataFolder.mkdir();
		}
	}

	public boolean writeCurrentTurn(String p){
		try{
			PrintWriter output = new PrintWriter(new FileWriter("plugins/Settlers Of Catan/PlayerInTurn"));
			output.println(p);
			output.close();

		}
		catch(IOException ex){
			return false;
		}
		return true;
	}

	public String getCurrentTurn(){
		try{
			BufferedReader input = new BufferedReader( new FileReader("plugins/Settlers Of Catan/PlayerInTurn"));
			String ip = input.readLine();
			input.close();
			return ip;
		}
		catch(IOException ex){

		}
		return null;
	}

	public ArrayList<String> getJoinedPlayers(){
		boolean readLine = true;
		ArrayList<String> players = new ArrayList<String>();
		try{
			BufferedReader input = new BufferedReader( new FileReader("plugins/Settlers Of Catan/Players.txt"));
			while(!(input.readLine().equals(null))){
				String line = input.readLine();
				if(readLine){
					players.add(line);
				}
				readLine = !(readLine);
			}
		input.close();
		return players;

		}
		catch(IOException ex){

		}

		return null;
	}


}
