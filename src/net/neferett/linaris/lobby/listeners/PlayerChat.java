package net.neferett.linaris.lobby.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.PlayerData;

public class PlayerChat implements Listener {

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onPlayerChat(final AsyncPlayerChatEvent event) {
		final Player player = event.getPlayer();

		event.setCancelled(true);
		final PlayerData pd = BukkitAPI.get().getPlayerDataManager().getPlayerData(player.getName());

		player.sendMessage("§" + pd.getRank().getColor() + pd.getRank().getPrefix(pd) + player.getName() + "§"
				+ pd.getRank().getColor() + " : " + event.getMessage().trim().replace("&", "§"));

	}

}
