package com.Pulsior.SettlersOfCatan;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Pulsior.SettlersOfCatan.game.CatanGame;
import com.Pulsior.SettlersOfCatan.game.PreGame;

/**
 * The CommandExecutor class, where much of the magic happens. To prevent class data transition errors,
 * most logic can be found in this class as well.
 * @author Pulsior
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
	SettlerFileIO io = new SettlerFileIO();
	IngameTrade trade = new IngameTrade();
	boolean red= false;
	boolean blue = false;
	boolean black = false;
	boolean green = false;
	boolean game;
	public static PreGame pregame;
	CatanGame c;
	public static Player inTurn;

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
		/*
		 * Join the Settlers of Catan game with your specified color
		 */
		if(cmd.getName().equalsIgnoreCase("join") && args.length == 1) {
			if(PreGame.preGame){
				if(isJoined(sender.getName()) == false){
					//IF the second argument is either red, green, blue or black

					if(args[0].equalsIgnoreCase("red") ||args[0].equalsIgnoreCase("green") ||args[0].equalsIgnoreCase("blue") ||
							args[0].equalsIgnoreCase("black")){

						if( colorInUse( args[0] ) == true){
							sender.sendMessage("§cThis color is already in use!");
							return true;
						}
						if( !(sender instanceof Player)){
							sender.sendMessage("Only players can use this command");
							return true;
						}

						else{
							joinedPlayers[amtOfPlayers] = sender.getName();
							amtOfPlayers = amtOfPlayers + 1;
							if(io.writeDataFile(sender.getName(), args[0]) == false){
								sender.sendMessage("§cPlayer registration failed, please reload the server and try again");
							};
							colorMessage(sender, args[0]);
							setColorInUse(args[0]);
							setColoredName(args[0], sender);
							Bukkit.broadcastMessage("§6"+sender.getName()+" is now playing with "+args[0]);
							//Bukkit.getServer().getPlayer(sender.getName()).getInventory().setMaxStackSize(1);
							io.writePlayerFile(sender.getName());
							World w = Bukkit.getServer().getWorld("soc");
							Location loc = new Location(w, -872, 60, -954);
							Bukkit.getServer().getPlayer(sender.getName()).teleport(loc);



							return true;
						}

					}
				}
				else{
					sender.sendMessage("§cYou have already joined the game!");
					return true;
				}
			}
			else{
				sender.sendMessage("§cNo pregame is running right now!");
				return true;
			}


		}
		/*
		 * Debug command used sometimes. Not featured in the plugin.yml in releases, thus impossible to use
		 */
		if(cmd.getName().equalsIgnoreCase("check")){

			return true;
		}
		/*
		 * Get the name of a player by specifying a color
		 */
		if(cmd.getName().equalsIgnoreCase("whoplayswith")){
			if(args.length == 1){
				String color = args[0];
				if(getPlayerName(color) != null){
					sender.sendMessage("§a"+getPlayerName(color)+ " is playing with "+color);
				}
				else{
					sender.sendMessage("§cNo one is playing with "+color+" yet");
				}

				return true;
			}
		}
		if(cmd.getName().equalsIgnoreCase("whichcoloris")){
			if(args.length == 1){
				String playerName = args[0];
				if(getColor(playerName) != null){
					sender.sendMessage("§a"+playerName+" is playing with "+getColor(playerName));
				}
				else{
					sender.sendMessage("§c"+playerName+" is not in the game");
				}
				return true;
			}
		}
		if(cmd.getName().equalsIgnoreCase("buy")){
			Player player = Bukkit.getServer().getPlayer(sender.getName());
			if(args.length == 1){
				if( args[0].equalsIgnoreCase("settlement") ){
					trade.buySettlement(player);
				}
				if( args[0].equalsIgnoreCase("city")){
					trade.buyCity(player);
				}
				if( args [0].equalsIgnoreCase("road")){
					String color = getColor(player.getName() );
					trade.buyRoad(player, color);
				}
				return true;
			}
		}
		if(cmd.getName().equalsIgnoreCase("newgame")){
			pregame = new PreGame();
			Bukkit.broadcastMessage("§eA new Settlers of Catan game has been created. Use /join to join the game!");
			return true;
		}

		if(cmd.getName().equalsIgnoreCase("launchgame")){
			c = new CatanGame();
			Bukkit.broadcastMessage("§eThe game has been launched!");
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

		for(int x = 0; x < 4; x++){
			if(joinedPlayers[x] != null){
				if(joinedPlayers[x].equalsIgnoreCase(playerName) ){
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
	 * Alters the name of a player so his/her team color can be seen in the chat	
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
	/**
	 * Returns the color the player chose when joining the game
	 * @param playerName
	 * @return The player color
	 */
	public String getColor(String playerName){

		String[] namesAndColors = io.readDataFile();
		for(int x = 0; x < namesAndColors.length; x++){

			if(namesAndColors[x].equalsIgnoreCase(playerName)){
				return namesAndColors[x+1];
			}

		}
		return null;
	}

	/**
	 * Returns the name of the player using the specified color
	 * @param color
	 * @return
	 */
	public String getPlayerName(String color){
		String[] namesAndColors = io.readDataFile();
		for(int x = 0; x < namesAndColors.length; x++){

			if(namesAndColors[x].equalsIgnoreCase(color)){
				return namesAndColors[x-1];
			}

		}
		return null;
	}







}





