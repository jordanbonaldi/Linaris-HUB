package net.neferett.linaris.lobby.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.PlayerData;
import net.neferett.linaris.api.PlayerLocal;
import net.neferett.linaris.api.PlayerLocalManager;

public class PlayerChatListener implements Listener {

	public String getCooldown(final PlayerLocal player) {
		final long millis = player.getLong("chatcl") - System.currentTimeMillis();
		final int seconds = (int) (millis / 1000) % 60;
		final int minutes = (int) (millis / (1000 * 60) % 60);
		final int hours = (int) (millis / (1000 * 60 * 60) % 24);
		String date = "";
		if (hours >= 1) date += hours + " heure" + (hours > 1 ? "s " : " ");
		if (minutes >= 1) date += minutes + " minute" + (minutes > 1 ? "s " : " ");
		date += seconds + " seconde" + (seconds > 1 ? "s" : "");
		return date;
	}

	public boolean hasCoolDown(final PlayerLocal player) {
		if (!player.contains("chatcl")) return false;
		final long millis = player.getLong("chatcl");
		if (millis - System.currentTimeMillis() <= 0) return false;

		return true;

	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onPlayerChat(final AsyncPlayerChatEvent event) {

		final Player player = event.getPlayer();
		event.setCancelled(true);
		BukkitAPI.get().getTasksManager().addTask(() -> {

			final PlayerLocal pl = PlayerLocalManager.get().getPlayerLocal(player.getName());
			if (this.hasCoolDown(pl)) {

				player.sendMessage("§cVous devez attendre §e" + this.getCooldown(pl) + "§c.");

				return;
			}

			final PlayerData pd = BukkitAPI.get().getPlayerDataManager().getPlayerData(player.getName());
			if (pd.getRank().getModerationLevel() <= 1) pd.setLong("chatcl", System.currentTimeMillis() + 3 * 1000);

			if (pd.getRank().getModerationLevel() < 1) for (final Player target : Bukkit.getOnlinePlayers())
				target.sendMessage(pd.getRank().getPrefix(pd) + player.getName() + "§" + pd.getRank().getColor() + " : "
						+ event.getMessage().trim());
			else for (final Player target : Bukkit.getOnlinePlayers())
				target.sendMessage(pd.getRank().getPrefix(pd) + player.getName() + "§" + pd.getRank().getColor() + " : "
						+ event.getMessage().trim().replace("&", "§"));

		});

	}

}
