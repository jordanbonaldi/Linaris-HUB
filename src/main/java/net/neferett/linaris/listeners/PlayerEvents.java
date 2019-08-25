package net.neferett.linaris.listeners;

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

public class PlayerEvents implements Listener {

	@EventHandler
	public void onBreakBlock(final BlockBreakEvent e) {
		if (!e.getPlayer().isOp())
			e.setCancelled(true);
	}

	@EventHandler
	public void onCraft(final PrepareItemCraftEvent e) {
		e.getInventory().setResult(null);
	}

	@EventHandler
	public void onDamage(final EntityDamageEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onDamageEntity(final EntityDamageByEntityEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onEntityTarget(final EntityTargetLivingEntityEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onFoodChange(final FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onPickupItem(final PlayerPickupItemEvent e) {
		e.setCancelled(false);
	}

	@EventHandler
	public void onPlaceBlock(final BlockPlaceEvent e) {
		if (!e.getPlayer().isOp())
			e.setCancelled(true);
	}

	@EventHandler
	public void onPlayerDead(final PlayerDeathEvent e) {
		e.getDrops().clear();
		PlayerUtils.sendForceRespawn(e.getEntity(), 1);
	}

}
