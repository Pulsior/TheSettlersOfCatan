package com.Pulsior.SettlersOfCatan;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import com.Pulsior.SettlersOfCatan.board.BoardSpace;
import com.Pulsior.SettlersOfCatan.board.SerializableBoardSpace;
import com.Pulsior.SettlersOfCatan.game.CatanGame;
import com.Pulsior.SettlersOfCatan.game.Color;
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
	IngameTrade trade = new IngameTrade();
	boolean game;
	public static PreGame pregame;
	CatanGame c;
	int inTurn = 0;
	Logger logger = Bukkit.getLogger();

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
			if(PreGame.preGame && !(CatanGame.launched)){ //If a pregame has been launched and the final game hasn't het...
				if(isJoined(sender.getName()) == false){ //If the sender has not joined the game yet...

					//If the second argument is either red, green, blue or yellow
					if(args[0].equalsIgnoreCase("red") ||args[0].equalsIgnoreCase("green") ||args[0].equalsIgnoreCase("blue") ||
							args[0].equalsIgnoreCase("yellow")){
						Color color = getColorByString(args[0]);
						//If the color chosen by the player is already in use...
						if( colorInUse( color ) == true){
							sender.sendMessage("§cThis color is already in use!");
							return true;
						}
						//If the command is executed from the console...
						if( !(sender instanceof Player)){
							sender.sendMessage("Only players can use this command");
							return true;
						}

						else{
							SettlersOfCatan.data.players[SettlersOfCatan.data.amountOfPlayers] = sender.getName(); //Add the player's name to the joinedPlayers array
							colorMessage(sender, color); //Set color properties
							setColorInUse(color);
							setColoredName(color, sender.getName() );
							SettlersOfCatan.data.setColor(color, SettlersOfCatan.data.amountOfPlayers);
							Bukkit.broadcastMessage("§6"+sender.getName()+" is now playing with "+args[0]);	
							World w = Bukkit.getServer().getWorld("world"); //Teleport the player to the spawn of the map
							Location loc = new Location(w, -872, 60, -954);
							Player player = Bukkit.getServer().getPlayer(sender.getName());
							player.teleport(loc);
							player.setGameMode(GameMode.ADVENTURE);
							player.setAllowFlight(true);
							player.sendMessage("You can fly now!");
							player.setMetadata("number", new FixedMetadataValue(main, SettlersOfCatan.data.amountOfPlayers+1));
							SettlersOfCatan.sPlayers[SettlersOfCatan.data.amountOfPlayers] = new SPlayer(SettlersOfCatan.data.amountOfPlayers+1, sender.getName() );
							SettlersOfCatan.data.amountOfPlayers++;
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
		 * Debug command used for testing. Not featured in the plugin.yml and main class in releases, 
		 * thus impossible to use for a normal user
		 */
		if(cmd.getName().equalsIgnoreCase("check")){
			Bukkit.getServer().getPlayer(sender.getName()).setPlayerWeather(WeatherType.DOWNFALL);
			return true;
		}		

		/*
		 * Trade resources for settlements, roads or cities
		 */
		if(cmd.getName().equalsIgnoreCase("buy")){
			if(sender.getName().equalsIgnoreCase(SettlersOfCatan.data.players[SettlersOfCatan.data.inTurn])){
				Player player = Bukkit.getServer().getPlayer(sender.getName());
				if(args.length == 1){
					if( args[0].equalsIgnoreCase("settlement") ){
						trade.buySettlement(player);
					}
					if( args[0].equalsIgnoreCase("city")){
						trade.buyCity(player);
					}
					if( args [0].equalsIgnoreCase("road")){
						Color color = SettlersOfCatan.data.getColor(player.getName() );
						trade.buyRoad(player, color);
					}
					return true;
				}
				return true;
			}
			else{
				sender.sendMessage("§cIt is not your turn!");
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
			logger.info("[Settlers of Catan] "+sender.getName()+" created a new game"); //Log who created a new game
			Bukkit.broadcastMessage("§eA new Settlers of Catan game has been created. Use /join to join the game!");
			return true;
		}

		/*
		 * Launches the game by instantiating game.CatanGame
		 */
		if(cmd.getName().equalsIgnoreCase("launchgame")){
			if(SettlersOfCatan.data.amountOfPlayers != 0){
				c = new CatanGame();
				logger.info("[Settlers of Catan] Starting a new game with "+Integer.toString(SettlersOfCatan.data.amountOfPlayers)+ " players"); //Log that a game has been started
				Bukkit.broadcastMessage("§eThe game has been launched!");
				dice.roll();
				Bukkit.broadcastMessage(Bukkit.getServer().getPlayer(SettlersOfCatan.data.players[0]).getDisplayName() + " §frolled §c"+ Integer.toString( Dice.lastValue )+"§f!" );

				return true;
			}
			else{
				sender.sendMessage("§cNo players are in the game yet!");
				return true;
			}
		}

		/*
		 * Let a player end his/her turn, allowing the next player to make a move
		 */
		if(cmd.getName().equalsIgnoreCase("endturn")){
			if( ! (sender instanceof Player)){
				if(args.length == 1){
					Dice.lastValue = Integer.parseInt(args[0]);
					endTurn();
				}
				if(args.length == 0){
					dice.roll();
					endTurn();
				}
				return true;
			}
			else if(SettlersOfCatan.data.players [SettlersOfCatan.data.inTurn].equals( sender.getName() ) ){
				dice.roll();
				endTurn();
			}
			else{
				sender.sendMessage("§cIt is not your turn!");
			}
			return true;
		}
		/*
		 * Save the game, so the server can be shut down safely
		 */
		if(cmd.getName().equalsIgnoreCase("save")){
			for(int x = 0; x < 4; x++){
				if(SettlersOfCatan.sPlayers[x] != null){
					savePlayerData(x+1);
				}
			}
			saveGlobalData();
			logger.info("[Settlers of Catan] Game saved");
			Bukkit.broadcastMessage("§aThe game was saved");
			return true;
		}
		

		if(cmd.getName().equalsIgnoreCase("load")){
			loadGlobalData();
			for(String name : SettlersOfCatan.data.players){
				if(name != null){
					if(Bukkit.getServer().getPlayer(name) == null){
						return false;
					}
				}
			}
			SerializableSPlayer[] players = new SerializableSPlayer[SettlersOfCatan.data.amountOfPlayers];
			for(int x = 0; x < SettlersOfCatan.data.amountOfPlayers; x++){
				players[x] = loadPlayerData(x+1);
			}
			for(SerializableSPlayer player : players){
				if(player != null){
					registerSPlayer(player);
				}
			}
			Bukkit.broadcastMessage("§aLoaded existing game");
			logger.info("[Settlers of Catan] Loaded!");
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
	 * Method to verify whether a color is used by someone else
	 * @param color
	 * @return
	 */
	public boolean colorInUse(Color color){
		switch(color){
		case RED:
			if(SettlersOfCatan.data.redUsed == false){}
			if(SettlersOfCatan.data.redUsed == true)
			{return true;}
			break;
		case BLUE:
			if(SettlersOfCatan.data.blueUsed == false){}
			if(SettlersOfCatan.data.blueUsed == true)
			{return true;}
			break;
		case GREEN:
			if(SettlersOfCatan.data.greenUsed == false){}
			if(SettlersOfCatan.data.greenUsed == true){return true;}
			break;
		case YELLOW:
			if(SettlersOfCatan.data.yellowUsed == false){}
			if(SettlersOfCatan.data.yellowUsed == true)
			{return true;}
			break;
		}
		return false;
	}


	/**
	 * Sends a simple message to a player with their color of choice
	 * @param snd
	 * @param color
	 */
	public void colorMessage(CommandSender snd, Color color){
		switch(color){
		case RED:
			snd.sendMessage("You are now playing with color §4red");
			break;
		case GREEN:
			snd.sendMessage("You are now playing with color §2green");
			break;
		case BLUE:
			snd.sendMessage("You are now playing with color §9blue");
			break;
		case YELLOW:
			snd.sendMessage("You are now playing with color §eyellow");
			break;
		}
	}
	
	/**
	 * Sets a color boolean to true;
	 * @param color
	 */
	public void setColorInUse(Color color){
		switch(color){
		case RED:
			SettlersOfCatan.data.redUsed = true;
			break;
		case BLUE:
			SettlersOfCatan.data.blueUsed = true;
			break;
		case GREEN:
			SettlersOfCatan.data.greenUsed = true;
			break;
		case YELLOW:
			SettlersOfCatan.data.yellowUsed = true;
			break;
		}
	}
	/**
	 * Alters the name of a player so his/her team color can be seen in the chat	
	 * @param color
	 * @param snd
	 */
	public void setColoredName(Color color, String snd){
		Player player = Bukkit.getServer().getPlayer(snd);
		switch(color){
		case RED:
			player.setDisplayName("§4"+player.getName());
			break;
		case GREEN:
			player.setDisplayName("§2"+player.getName());
			break;
		case BLUE:
			player.setDisplayName("§9"+player.getName());
			break;
		case YELLOW:
			player.setDisplayName("§e"+player.getName());
			break;
		}
	}
	
	/**
	 * Get a Color enum with a string
	 * @param color
	 * @return
	 */
	
	public Color getColorByString(String color){
		if(color.equalsIgnoreCase("red")){
			return Color.RED;
		}
		if(color.equalsIgnoreCase("blue")){
			return Color.BLUE;
		}
		if(color.equalsIgnoreCase("green")){
			return Color.GREEN;
		}
		if(color.equalsIgnoreCase("yellow")){
			return Color.YELLOW;
		}
		return null;
	}


	/**
	 * Method to end a player's turn. Also give the appropriate resources to a player
	 */
	public void endTurn(){
		Bukkit.broadcastMessage(Bukkit.getServer().getPlayer(SettlersOfCatan.data.players[SettlersOfCatan.data.inTurn]).getDisplayName() +"§f ended his turn!");
		if(SettlersOfCatan.data.inTurn < SettlersOfCatan.data.amountOfPlayers-1){
			SettlersOfCatan.data.inTurn = SettlersOfCatan.data.inTurn+1;
		}
		else{
			SettlersOfCatan.data.inTurn = 0;
		}
		int result = Dice.lastValue;
		String playerName = Bukkit.getServer().getPlayer(SettlersOfCatan.data.players[SettlersOfCatan.data.inTurn]).getDisplayName();
		Bukkit.broadcastMessage("It is now "+playerName+ "'s §fturn!");
		Bukkit.broadcastMessage(playerName + " §frolled §c"+ Integer.toString( result )+"§f!" );

		if(SettlersOfCatan.data.players[0] != null){
			Player player = Bukkit.getServer().getPlayer(SettlersOfCatan.data.players[0]);
			int number = getMetadata(Bukkit.getServer().getPlayer(SettlersOfCatan.data.players[0]), "number", SettlersOfCatan.plugin);
			SPlayer sp = SettlersOfCatan.sPlayers[number-1];
			sp.giveResources(Dice.lastValue, player );
		}

		if(SettlersOfCatan.data.players[1] != null){
			Player player = Bukkit.getServer().getPlayer(SettlersOfCatan.data.players[1]);
			int number = getMetadata(Bukkit.getServer().getPlayer(SettlersOfCatan.data.players[1]), "number", SettlersOfCatan.plugin);
			logger.info("[Settlers of Catan] The number is " + Integer.toString(number) );
			SPlayer sp = SettlersOfCatan.sPlayers[number-1];
			sp.giveResources(Dice.lastValue, player);

		}
		if(SettlersOfCatan.data.players[2] != null){
			Player player = Bukkit.getServer().getPlayer(SettlersOfCatan.data.players[2]);
			int number = getMetadata(Bukkit.getServer().getPlayer(SettlersOfCatan.data.players[2]), "number", SettlersOfCatan.plugin);
			SPlayer sp = SettlersOfCatan.sPlayers[number-1];
			sp.giveResources(Dice.lastValue, player);

		}
		if(SettlersOfCatan.data.players[3] != null){
			Player player = Bukkit.getServer().getPlayer(SettlersOfCatan.data.players[3]);
			int number = getMetadata(Bukkit.getServer().getPlayer(SettlersOfCatan.data.players[3]), "number", SettlersOfCatan.plugin);
			SPlayer sp = SettlersOfCatan.sPlayers[number-1];
			sp.giveResources(Dice.lastValue, player);

		}
	}


	
	/**
	 * Get the metadata of a player
	 * @param player
	 * @param key
	 * @param plugin
	 * @return
	 */
	
	public int getMetadata(Player player, String key, Plugin plugin){
		List<MetadataValue> values = player.getMetadata(key);  
		for(MetadataValue value : values){
			return value.asInt();

		}
		return 0;
	}

	/**
	 * Save individual player data (acquired resources)
	 * @param number
	 */
	public void savePlayerData(int number){
		SPlayer player = SettlersOfCatan.sPlayers[number-1];
		SerializableSPlayer ssp = new SerializableSPlayer(player.getPlayerNumber(), player.getPlayerName());
		for( BoardSpace space : player.getClaimed()){
			ssp.addSpace(new SerializableBoardSpace(space.getResource(), space.getLocation().getX(),
					space.getLocation().getY(), space.getLocation().getZ(), space.getSpaceNumber()));
		}
		try{
			FileOutputStream fout = new FileOutputStream("plugins/Settlers Of Catan/player"+Integer.toString(number)+".sav");
			ObjectOutputStream oos = new ObjectOutputStream(fout);   
			oos.writeObject(ssp);
			oos.close();

		}
		catch(IOException ex){
			logger.info("[Settlers of Catan] An exception occured while saving the player data");
			logger.info(ex.getMessage());
		}
	}

	/**
	 * Save data about the game
	 */
	
	public void saveGlobalData(){
		try{
			FileOutputStream fout = new FileOutputStream("plugins/Settlers Of Catan/gameData.sav");
			ObjectOutputStream oos = new ObjectOutputStream(fout);   
			oos.writeObject( SettlersOfCatan.data );
			oos.close();

		}
		catch(IOException ex){
			logger.info("[Settlers of Catan] An exception occured while saving the global data");
			logger.info(ex.getMessage() );
			StackTraceElement[] st = ex.getStackTrace();
			for(StackTraceElement element : st){
				logger.info(element.toString());
			}
		}
	}
	
	/**
	 * Load individual player data
	 * @param number
	 * @return
	 */
	
	public SerializableSPlayer loadPlayerData(int number){
		SerializableSPlayer ssp;
		try{
			FileInputStream fileInput = new FileInputStream("plugins/Settlers Of Catan/player"+Integer.toString(number)+".sav");
			ObjectInputStream objInput = new ObjectInputStream(fileInput);
			ssp = (SerializableSPlayer) objInput.readObject();
			objInput.close();
			if(ssp == null){
				logger.info("[Settlers of Catan] Player "+Integer.toString(number)+" is equal to null");
			}

			return ssp;
		}
		catch(Exception ex){
			logger.info("[Settlers of Catan] An exception occured while loading the data of player "+Integer.toString(number));
			return null;
		} 
	}
	/**
	 * Load data about the game
	 */
	public void loadGlobalData(){
		try{
			FileInputStream fileInput = new FileInputStream("plugins/Settlers Of Catan/gameData.sav");
			ObjectInputStream objInput = new ObjectInputStream(fileInput);
			Data data = (Data) objInput.readObject();
			objInput.close();
			if(data == null){
				logger.info("[Settlers of Catan] The global data is equal to null.");
			}
			else{
				SettlersOfCatan.data = data;
			}
		}
		catch(IOException ex){
			logger.info("[Settlers of Catan] An IOException occured while loading the global data");
			StackTraceElement[] st = ex.getStackTrace();
			for(StackTraceElement element : st){
				logger.info(element.toString());
			}
		} 
		catch(ClassNotFoundException ex){
			logger.info("[Settlers of Catan] A ClassNotFoundExpeption occured while loading the global data");
			StackTraceElement[] st = ex.getStackTrace();
			for(StackTraceElement element : st){
				logger.info(element.toString());
			}

		}
	}

	/**
	 * Register the loaded player in the game and add his claimed resources
	 * @param ssp
	 */

	public void registerSPlayer(SerializableSPlayer ssp){
		if(ssp != null){
			logger.info(ssp.getPlayerName() +", "+Integer.toString(ssp.getNumber()));
			SPlayer player = new SPlayer(ssp.getNumber(), ssp.getPlayerName() );
			for(SerializableBoardSpace space : ssp.getClaimed()){
				Location location = new Location(Bukkit.getWorld("world"), space.getX(), space.getY(), space.getZ() );
				player.addSpace(new BoardSpace(space.getResource(), location, space.getNumber() ));
			}
			SettlersOfCatan.sPlayers[ssp.playerNumber-1] = player;
			Player player2 = Bukkit.getServer().getPlayer( player.getPlayerName() );
			player2.setMetadata("number", new FixedMetadataValue(main, player.getPlayerNumber() ) );
			Color color = SettlersOfCatan.data.getColor( player2.getName() );
			setColoredName(color, player2.getName());
			player2.setAllowFlight(true);
			player2.setGameMode(GameMode.ADVENTURE);
		}
		else{
			logger.info("[Settlers of Catan] Executed with ssp null!");
		}

	}


}





