package com.Pulsior.SettlersOfCatan;

import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import com.Pulsior.SettlersOfCatan.game.CatanGame;
import com.Pulsior.SettlersOfCatan.game.Dice;
import com.Pulsior.SettlersOfCatan.game.IngameTrade;
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
	Dice dice = new Dice();
	private SettlersOfCatan main;
	private String[] joinedPlayers = new String[4];
	int amtOfPlayers = 0;
	SettlerFileIO io = new SettlerFileIO();
	IngameTrade trade = new IngameTrade();
	boolean red= false;
	boolean blue = false;
	boolean yellow = false;
	boolean green = false;
	boolean game;
	public static PreGame pregame;
	CatanGame c;
	int inTurn = 0;
	Logger l = Bukkit.getLogger();

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

		/*
		 * Join the Settlers of Catan game with your specified color
		 */

		//If the command equals join AND the command contains one argument AND the sender has not joined  the game yet
		if(cmd.getName().equalsIgnoreCase("join") && args.length == 1) {
			if(PreGame.preGame){ //If a pregame has been launched...
				if(isJoined(sender.getName()) == false){ //If the sender has not joined the game yet...

					//If the second argument is either red, green, blue or yellow
					if(args[0].equalsIgnoreCase("red") ||args[0].equalsIgnoreCase("green") ||args[0].equalsIgnoreCase("blue") ||
							args[0].equalsIgnoreCase("yellow")){

						//If the color chosen by the player is already in use...
						if( colorInUse( args[0] ) == true){
							sender.sendMessage("§cThis color is already in use!");
							return true;
						}
						//If the command is executed from the console...
						if( !(sender instanceof Player)){
							sender.sendMessage("Only players can use this command");
							return true;
						}

						else{
							joinedPlayers[amtOfPlayers] = sender.getName(); //Add the player's name to the joinedPlayers array
							ArrayStorage.players[amtOfPlayers] = sender.getName(); //Add the player's name to the joinedPlayers array
							amtOfPlayers++; //Increase both values by one
							ArrayStorage.amountOfPlayers++;
							if(io.writeDataFile(sender.getName(), args[0]) == false){ //Write name and color to a file
								sender.sendMessage("§cPlayer registration failed, please reload the server and try again");
							};
							colorMessage(sender, args[0]); //Set color properties
							setColorInUse(args[0]);
							setColoredName(args[0], sender);
							Bukkit.broadcastMessage("§6"+sender.getName()+" is now playing with "+args[0]);	
							io.writePlayerFile(sender.getName()); //Write another player file
							World w = Bukkit.getServer().getWorld("soc"); //Teleport the player to the spawn of the map
							Location loc = new Location(w, -872, 60, -954);
							Player player = Bukkit.getServer().getPlayer(sender.getName());
							player.teleport(loc);
							player.setGameMode(GameMode.ADVENTURE);
							player.setAllowFlight(true);
							player.sendMessage("You can fly now!");
							player.setMetadata("number", new FixedMetadataValue(main, ArrayStorage.amountOfPlayers+1));
							SettlersOfCatan.sPlayers[ArrayStorage.amountOfPlayers] = new SPlayer(ArrayStorage.amountOfPlayers+1);
							return true;
						}

					}
				}

				//Message sent when a player has already joined
				else{
					sender.sendMessage("§cYou have already joined the game!");
					return true;
				}
			}
			//Message sent when PreGame has not yet been instantiated
			else{
				sender.sendMessage("§cNo pregame is running right now!");
				return true;
			}


		}
		/*
		 * Debug command used sometimes. Not featured in the plugin.yml and main class in releases, 
		 * thus impossible to use for a normal user
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
		/*
		 * Let the player get the color of another player
		 */
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

		/*
		 * Trade resources for settlements, roads or cities
		 */
		if(cmd.getName().equalsIgnoreCase("buy")){
			if(sender.getName().equalsIgnoreCase(ArrayStorage.players[ArrayStorage.inTurn])){
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
				return true;
			}
			else{
				sender.sendMessage("It is not your turn!");
				if(args.length != 0){
					return true;
				}
			}
		}
		/*
		 * Creates a pregame in which players can join,
		 * by instantiating game.PreGame
		 */
		if(cmd.getName().equalsIgnoreCase("newgame")){
			pregame = new PreGame();
			Bukkit.getLogger().info(sender.getName()+" created a new game"); //Log who created a new game
			Bukkit.broadcastMessage("§eA new Settlers of Catan game has been created. Use /join to join the game!");
			return true;
		}

		/*
		 * Launches the game by instantiating game.CatanGame
		 */
		if(cmd.getName().equalsIgnoreCase("launchgame")){
			c = new CatanGame();
			Bukkit.getLogger().info("Starting a new game with "+Integer.toString(ArrayStorage.amountOfPlayers)+ " players"); //Log that a game has been started
			Bukkit.broadcastMessage("§eThe game has been launched!");
			dice.roll();
			Bukkit.broadcastMessage(Bukkit.getServer().getPlayer(ArrayStorage.players[0]).getDisplayName() + " §frolled §c"+ Integer.toString( Dice.lastValue )+"§f!" );

			return true;
		}

		/*
		 * Let a player end his/her turn, allowing the next player to make a move
		 */
		if(cmd.getName().equalsIgnoreCase("endturn")){
			endTurn();
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
		if( color.equalsIgnoreCase("yellow") ){
			yellow = true;
		}
	}
	/**
	 * Method to verify whether a color is used by someone else
	 * @param color
	 * @return
	 */
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
		if(color.equalsIgnoreCase("yellow")){
			if(yellow == false){}
			if(yellow == true)
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
			snd.sendMessage("You are now playing with color §9blue");
		}
		if(color.equalsIgnoreCase("yellow")){
			snd.sendMessage("You are now playing with color §eyellow");
		}


	}
	public void setColorInUse(String color){
		if(color.equalsIgnoreCase("red")){red = true;}
		if(color.equalsIgnoreCase("blue")){blue = true;}
		if(color.equalsIgnoreCase("green")){green = true;}
		if(color.equalsIgnoreCase("yellow")){yellow = true;}
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
			player.setPlayerListName("§4"+player.getName());
		}
		if(color.equalsIgnoreCase("green") ){
			player.setDisplayName("§2"+player.getName());
			player.setPlayerListName("§2"+player.getName());
		}
		if(color.equalsIgnoreCase("blue") ){
			player.setDisplayName("§9"+player.getName());
			player.setPlayerListName("§9"+player.getName());
		}
		if(color.equalsIgnoreCase("yellow") ){
			player.setDisplayName("§e"+player.getName());
			player.setPlayerListName("§e"+player.getName());
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

	public void endTurn(){
		Bukkit.broadcastMessage(Bukkit.getServer().getPlayer(ArrayStorage.players[ArrayStorage.inTurn]).getDisplayName() +"§f ended his turn!");
		if(ArrayStorage.inTurn < ArrayStorage.amountOfPlayers-1){
			ArrayStorage.inTurn = ArrayStorage.inTurn+1;
		}
		else{
			ArrayStorage.inTurn = 0;
		}
		dice.roll();
		l.info("Dice rolled");
		int result = Dice.lastValue;
		String playerName = Bukkit.getServer().getPlayer(ArrayStorage.players[ArrayStorage.inTurn]).getDisplayName();
		Bukkit.broadcastMessage("It is now "+playerName+ "'s §fturn!");
		Bukkit.broadcastMessage(playerName + " §frolled §c"+ Integer.toString( result )+"§f!" );
		
		if(ArrayStorage.players[0] != null){
			Player player = Bukkit.getServer().getPlayer(ArrayStorage.players[0]);
			int number = getMetadata(Bukkit.getServer().getPlayer(ArrayStorage.players[0]), "number", SettlersOfCatan.plugin);
			SPlayer sp = SettlersOfCatan.sPlayers[number-1];
			sp.giveResources(Dice.lastValue, player );
			
		}
		if(ArrayStorage.players[1] != null){
			Player player = Bukkit.getServer().getPlayer(ArrayStorage.players[1]);
			int number = getMetadata(Bukkit.getServer().getPlayer(ArrayStorage.players[1]), "number", SettlersOfCatan.plugin);
			SPlayer sp = SettlersOfCatan.sPlayers[number];
			sp.giveResources(Dice.lastValue, player);
			
		}
		if(ArrayStorage.players[2] != null){
			Player player = Bukkit.getServer().getPlayer(ArrayStorage.players[2]);
			int number = getMetadata(Bukkit.getServer().getPlayer(ArrayStorage.players[2]), "number", SettlersOfCatan.plugin);
			SPlayer sp = SettlersOfCatan.sPlayers[number];
			sp.giveResources(Dice.lastValue, player);
			
		}
		if(ArrayStorage.players[3] != null){
			Player player = Bukkit.getServer().getPlayer(ArrayStorage.players[3]);
			int number = getMetadata(Bukkit.getServer().getPlayer(ArrayStorage.players[3]), "number", SettlersOfCatan.plugin);
			SPlayer sp = SettlersOfCatan.sPlayers[number];
			sp.giveResources(Dice.lastValue, player);
			
		}
	}





	public int getMetadata(Player player, String key, Plugin plugin){
		List<MetadataValue> values = player.getMetadata(key);  
		for(MetadataValue value : values){
			return value.asInt();

		}
		return 0;
	}

}





