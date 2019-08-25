package net.neferett.linaris.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryListener implements Listener {

	@EventHandler
	public void closeinv(final InventoryCloseEvent e) {
		final Player p = (Player) e.getPlayer();

		if (MoveListener.portalRegion.isInside(p.getLocation())
				&& e.getInventory().getTitle().equalsIgnoreCase("Menu Principal & Jeux"))
			MoveListener.portail.put(p, System.currentTimeMillis());
	}

	@EventHandler
	public void onInvOpen(final InventoryOpenEvent e) {
		if (e.getInventory().getType() == InventoryType.MERCHANT)
			e.setCancelled(true);
	}

}
