package net.neferett.linaris.lobby.listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import net.neferett.linaris.lobby.handlers.inventories.MainMenuInventory;
import net.neferett.linaris.lobby.utils.ConfigDatas;
import net.neferett.linaris.utils.CuboidRegion;
import net.neferett.linaris.utils.gui.GuiManager;

public class MoveListener implements Listener {

	public static HashMap<Player, Long>	portail			= new HashMap<>();

	public static CuboidRegion			portalRegion	= new CuboidRegion(
			new Location(Bukkit.getWorld("world"), 7, 104, 30), new Location(Bukkit.getWorld("world"), -6, 132, 30));

	@SuppressWarnings("deprecation")
	@EventHandler
	public void move(final PlayerMoveEvent e) {
		final Player p = e.getPlayer();

		if (portalRegion.isInside(p.getLocation()) && portail.containsKey(e.getPlayer())
				&& (portail.get(e.getPlayer()) / 1000 + 3.5 - System.currentTimeMillis() / 1000 <= 0
						|| !portail.containsKey(e.getPlayer()))) {
			GuiManager.openGui(new MainMenuInventory(p));
			return;
		}

		if (p.getLocation().getY() < 50)
			p.teleport(ConfigDatas.getInstance().getSpawn());

		if (e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SEA_LANTERN
				&& !p.isOnGround() && e.getPlayer().getVelocity().getY() > 0)
			e.getPlayer()
					.setVelocity(e.getPlayer().getLocation().getDirection().multiply(4.0).add(new Vector(0, 8, 0)));
	}

}
