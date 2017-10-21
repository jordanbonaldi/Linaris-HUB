package net.neferett.linaris.lobby.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.world.StructureGrowEvent;

public class NaturalListener implements Listener {

	@EventHandler
	public void onBlockFadeEvent(final BlockFadeEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onBlockFormEvent(final BlockFormEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onBlockGrowEvent(final BlockGrowEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onBlockPhysicsEvent(final BlockPhysicsEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onBlockRedstoneEvent(final BlockRedstoneEvent event) {
		event.setNewCurrent(event.getOldCurrent());
	}

	@EventHandler
	public void onBlockSpreadEvent(final BlockSpreadEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onLeavesDecayEvent(final LeavesDecayEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onStructureGrowEvent(final StructureGrowEvent event) {
		event.setCancelled(true);
	}

}
