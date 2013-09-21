package com.Pulsior.SettlersOfCatan;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Simple EventListener to make all chat messages white.
 * Chat messages will have the team color of their sender otherwise
 * @author Pulsior
 *
 */

public class SettlersEventListener implements Listener {

	SettlersCommandExecutor c = new SettlersCommandExecutor();
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		event.setMessage("§f"+event.getMessage() );
		
		
	}
	
}
