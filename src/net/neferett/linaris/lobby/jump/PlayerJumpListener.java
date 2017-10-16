package net.neferett.linaris.lobby.jump;


import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.neferett.linaris.lobby.managers.ScoreboardManager;

public class PlayerJumpListener implements Listener {

	JumpsManager manager;
	
	public PlayerJumpListener(JumpsManager manager) {
		this.manager = manager;
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		
		if (e.getFrom().getBlock().equals(e.getTo().getBlock())) return;
		
		Player p = e.getPlayer();
		
		if (!manager.inJump(p)) {
			
			Location to = e.getTo().getBlock().getLocation();
			
			if (manager.getStartLocation().getBlock().getLocation().equals(to)) {
				
				manager.startJump(p);
				
			}
			
		} else {
			
			Location to = e.getTo().getBlock().getLocation();
			int checkpoint = manager.containsBlock(to.getBlock());
			if (checkpoint != -1) {
				manager.checkPoint(p, checkpoint);
			}
			
		}
		
		
	}
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void onPlayerDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (e.getCause() == DamageCause.FALL) {
				if (manager.inJump(p) && p.getFallDistance() >= 6) {
					manager.die(p);
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		if (manager.inJump(e.getPlayer()))
			manager.stopJump(e.getPlayer());;
			ScoreboardManager.jump.put(e.getPlayer(), false);
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent e) {
		if (manager.inJump(e.getPlayer()))
			manager.stopJump(e.getPlayer());;
			ScoreboardManager.jump.put(e.getPlayer(), false);

	}
	
}
