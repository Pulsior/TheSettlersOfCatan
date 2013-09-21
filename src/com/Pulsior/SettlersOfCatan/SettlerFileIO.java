package com.Pulsior.SettlersOfCatan;

import java.io.BufferedReader;
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

	boolean fileExists = false;
	/*
	 * Writes names and team colors to "SettlersOfCatanPlayers.txt"
	 */
	public boolean writeDataFile(String player, String playerColor){
		try{
			PrintWriter output = new PrintWriter(new FileWriter("plugins/SettlersOfCatanPlayers.txt", fileExists));
			output.println(player);
			output.println(playerColor);
			output.close();
			fileExists = true;
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
			BufferedReader input = new BufferedReader( new FileReader("plugins/SettlersofCatanPlayers.txt"));
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
}
