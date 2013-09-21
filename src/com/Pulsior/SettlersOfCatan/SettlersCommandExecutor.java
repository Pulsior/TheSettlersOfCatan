package com.Pulsior.SettlersOfCatan;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The CommandExecutor class, where much of the magic happens. To prevent class data transition errors,
 * most logic can be found in this class as well.
 * @author DefPdeW
 *
 */
public class SettlersCommandExecutor implements CommandExecutor {

	/*
	 * Declares necessary variables 
	 */
	@SuppressWarnings("unused")
	private SettlersOfCatan main;
	private String[] joinedPlayers = new String[4];
	int amtOfPlayers = 0;
	SettlerPlayer[] registeredPlayers = new SettlerPlayer[4];
	boolean red= false;
	boolean blue = false;
	boolean black = false;
	boolean green = false;
	DataStorage d = new DataStorage();
	SettlerPlayer Player1;
	SettlerPlayer Player2;
	SettlerPlayer Player3;
	SettlerPlayer Player4;

	/**
	 * Constructor required for CommandExecutor functions
	 * @param plugin
	 */
	public SettlersCommandExecutor(SettlersOfCatan plugin) {
		main = plugin;
	}

	/**
	 * Overridden empty constructor method, to instantiate this class and use its methods without trouble
	 */
	public SettlersCommandExecutor(){

	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,	String[] args) {
		//IF the command equals join AND the command contains one argument AND the sender has not joined  the game yet
		if(cmd.getName().equalsIgnoreCase("join") && args.length == 1) {
			if(isJoined(sender.getName()) == false){
				//IF the second argument is either red, green, blue or black

				if(args[0].equalsIgnoreCase("red") ||args[0].equalsIgnoreCase("green") ||args[0].equalsIgnoreCase("blue") ||
						args[0].equalsIgnoreCase("black")){

					if( colorInUse( args[0] ) == true){
						sender.sendMessage("This color is already in use!");
						return true;
					}

					else{
						Player1 = new SettlerPlayer(sender.getName(), args[0]);
						//registeredPlayers[0] = new SettlerPlayer(sender.getName(), args[0]);
						//d.addSettler( new SettlerPlayer( sender.getName(), args[0] ) );
						joinedPlayers[amtOfPlayers] = sender.getName();
						amtOfPlayers = amtOfPlayers + 1;
						colorMessage(sender, args[0]);
						setColorInUse(args[0]);
						setColoredName(args[0], sender);

						return true;
					}

				}
			}
			else{
				sender.sendMessage("§cYou have already joined the game!");
				return true;
			}
		}
		if(cmd.getName().equalsIgnoreCase("check")){
			writePlayerData("args1", "args1");
			return true;
		}
		return false;
	}


	/**
	 * Checks whether a player has joined the Settlers game already
	 * @param playerName
	 * @return
	 */
	public boolean isJoined(String playerName){

		for(int i = 0; i < 4; i++){
			if(joinedPlayers[i] != null){
				if(joinedPlayers[i].equalsIgnoreCase(playerName) ){
					return true;
				}	
			}

		}
		return false;
	}
	/**
	 * Sets a color boolean to true, indicating the specified color is chosen by a player already
	 * @param color
	 */
	public void setTrue(String color){
		if( color.equalsIgnoreCase("green") ){
			green = true;
		}
		if( color.equalsIgnoreCase("red") ){
			red = true;
		}
		if( color.equalsIgnoreCase("blue") ){
			blue = true;
		}
		if( color.equalsIgnoreCase("black") ){
			black = true;
		}
	}
	public boolean colorInUse(String color){
		if(color.equalsIgnoreCase("red")){
			if(red == false){}
			if(red == true)
			{return true;}
		}
		if(color.equalsIgnoreCase("blue")){
			if(blue == false){}
			if(blue == true)
			{return true;}
		}
		if(color.equalsIgnoreCase("green")){
			if(green == false){}
			if(green == true)
			{return true;}
		}
		if(color.equalsIgnoreCase("black")){
			if(black == false){}
			if(black == true)
			{return true;}
		}
		return false;
	}
	/**
	 * Fills the joinedPlayers array with null values, to avoid exceptions when reading the array data.
	 * Deprecated, but preserving it for future use
	 */
	@Deprecated
	public void flushArray(){
		for(int i = 0; i < 4; i++){
			joinedPlayers[i] = null;
		}
		for(int i = 0; i < 4; i++){
			registeredPlayers[i] = null;
		}
	}
	/**
	 * Sends a simple message to a player with their color of choice
	 * @param snd
	 * @param color
	 */
	public void colorMessage(CommandSender snd, String color){
		if(color.equalsIgnoreCase("red")){
			snd.sendMessage("You are now playing with color §4red");
		}
		if(color.equalsIgnoreCase("green")){
			snd.sendMessage("You are now playing with color §2green");
		}
		if(color.equalsIgnoreCase("blue")){
			snd.sendMessage("You are now playing with color §1blue");
		}
		if(color.equalsIgnoreCase("black")){
			snd.sendMessage("You are now playing with color §0black");
		}

	}
	public void setColorInUse(String color){
		if(color.equalsIgnoreCase("red")){red = true;}
		if(color.equalsIgnoreCase("blue")){blue = true;}
		if(color.equalsIgnoreCase("green")){green = true;}
		if(color.equalsIgnoreCase("black")){black = true;}
	}
	/**
	 * Alters the name of a player so his/her team color van be seen in the chat	
	 * @param color
	 * @param snd
	 */
	public void setColoredName(String color, CommandSender snd){
		Player player = Bukkit.getServer().getPlayer(snd.getName());
		if(color.equalsIgnoreCase("red") ){
			player.setDisplayName("§4"+player.getName());	
		}
		if(color.equalsIgnoreCase("green") ){
			player.setDisplayName("§2"+player.getName());	
		}
		if(color.equalsIgnoreCase("blue") ){
			player.setDisplayName("§1"+player.getName());	
		}
		if(color.equalsIgnoreCase("black") ){
			player.setDisplayName("§0"+player.getName());	
		}
	}

	public SettlerPlayer getSettlerByPlayerName(String playerName){
		for(int x = 0; x < 4; x++){
			if(registeredPlayers[x] != null){
				if(registeredPlayers[x].getPlayerName().equalsIgnoreCase(playerName)){
					return registeredPlayers[x];
				}
			}
		}
		return new SettlerPlayer("Dear sir, you failed. Period.", "red");
	}
	public void writePlayerData(String arg1, String arg2){
		
		try {
			PrintWriter writer = new PrintWriter("plugins/data.txt", "UTF-8");
			writer.println("The first line");
			writer.println("The second line");
			writer.close();
		} catch (FileNotFoundException e) {
			Bukkit.getServer().getPlayer("DefPdeW").sendMessage("Shit");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			Bukkit.getServer().getPlayer("DefPdeW").sendMessage("Shit");
			e.printStackTrace();
		}
		
	}

}





