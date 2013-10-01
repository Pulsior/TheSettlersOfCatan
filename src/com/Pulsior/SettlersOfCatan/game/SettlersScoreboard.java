package com.Pulsior.SettlersOfCatan.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Team;

import com.Pulsior.SettlersOfCatan.SettlerFileIO;
import com.Pulsior.SettlersOfCatan.SettlersOfCatan;



/**
 * Class to manage a scoreboard on which the victory points of all
 * players are shown. Called SettlersScoreBoard to avoid confusion
 * with the Scoreboard class in Bukkit.
 * Also manages victory points
 * @author Pulsior
 *
 */
public class SettlersScoreboard {


	public SettlersScoreboard(){
		Team settlers = SettlersOfCatan.board.registerNewTeam("Victory points");
		SettlerFileIO io = new SettlerFileIO();
		Objective objective = SettlersOfCatan.board.registerNewObjective("victory", "dummy");
		Player[] players = io.getJoinedPlayers();
		settlers.addPlayer(players[0]);
		if(players[1] != null){settlers.addPlayer(players[1]);}
		if(players[2] != null){settlers.addPlayer(players[2]);}
		if(players[3] != null){settlers.addPlayer(players[3]);}
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName("Victory points");
		Score score = objective.getScore(players[0]);
		score.setScore(0);
		if(players[1] != null){score = objective.getScore(players[1]);}
		score.setScore(0);
		if(players[2] != null){score = objective.getScore(players[2]);}
		score.setScore(0);
		if(players[3] != null){score = objective.getScore(players[3]);}
		score.setScore(0);
		
		
		players[0].setScoreboard(SettlersOfCatan.board);
		if(players[1] != null){players[1].setScoreboard(SettlersOfCatan.board);}
		if(players[2] != null){players[2].setScoreboard(SettlersOfCatan.board);}
		if(players[3] != null){players[3].setScoreboard(SettlersOfCatan.board);}
		Bukkit.getLogger().info("Done setting up the scoreboard!");
		

	}
}
