package com.Pulsior.SettlersOfCatan;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class SettlersEventListener implements Listener {

	SettlersCommandExecutor c = new SettlersCommandExecutor();
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		event.setMessage("§f"+event.getMessage() );
		Player p = event.getPlayer();
		String n = p.getName();
		SettlerPlayer s = c.getSettlerByPlayerName(n);
		p.sendMessage( s.getPlayerName() );
		
	}
	
}
