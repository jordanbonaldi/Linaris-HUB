package net.neferett.linaris.lobby.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import net.neferett.linaris.utils.PlayerUtils;
import net.neferett.linaris.lobby.Lobby;

public class PermissionsModuleListener implements Listener {

	Lobby game;
	public Lobby getGame() {
		return game;
	}
	
	
	public PermissionsModuleListener(Lobby lobby) {
		this.game = lobby;
	}

	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent e) {
		if (!getGame().getPermissionsManager().food) e.setCancelled(true);
	}
	@EventHandler
	public void onBreakBlock(BlockBreakEvent e) {
		if (!e.getPlayer().isOp())
			e.setCancelled(true);
	}
	
	@EventHandler
	public void onPickupItem(PlayerPickupItemEvent e) {
		e.setCancelled(false);
	}

	@EventHandler
	public void onEntityTarget(EntityTargetLivingEntityEvent e) {
		if (!getGame().getPermissionsManager().entityTarget) e.setCancelled(true);
	}
	@EventHandler
	public void onPlaceBlock(BlockPlaceEvent e) {
		if (!e.getPlayer().isOp())
		 e.setCancelled(true);
	}

	@EventHandler
	public void onDamageEntity(EntityDamageByEntityEvent e) {		
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player && !getGame().getPermissionsManager().pvp) e.setCancelled(true);
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {	
		if ((!getGame().getPermissionsManager().damagePlayer && e.getEntity() instanceof Player))	e.setCancelled(true);
		if (!getGame().getPermissionsManager().damageMob && !(e.getEntity() instanceof Player))	e.setCancelled(true);
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (p.getGameMode() == GameMode.SPECTATOR) e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerDead(final PlayerDeathEvent e) {
		
		if (!getGame().getPermissionsManager().deadDrop) e.getDrops().clear();
		
		if (!getGame().getPermissionsManager().autoRespawn) return;
		
		PlayerUtils.sendForceRespawn(e.getEntity(), 1);
	}

	@EventHandler
	public void onCraft(PrepareItemCraftEvent e) {
		if (!getGame().getPermissionsManager().craft)
		e.getInventory().setResult(null);
	}
	

}
