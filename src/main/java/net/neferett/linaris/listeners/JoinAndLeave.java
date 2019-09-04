package net.neferett.linaris.listeners;

import net.neferett.linaris.handlers.games.GamesManager;
import net.neferett.linaris.minigames.GamesEnum;
import net.neferett.linaris.utils.tasksmanager.TaskManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.PlayersHandler.PlayerManager;
import net.neferett.linaris.PlayersHandler.Players;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.api.SettingsManager;
import net.neferett.linaris.events.ReturnToLobbyEvent;
import net.neferett.linaris.handlers.HologramsManager;
import net.neferett.linaris.handlers.items.ItemsManager;
import net.neferett.linaris.handlers.items.ProfileItem;
import net.neferett.linaris.handlers.items.magicbox.MagicboxItem;
import net.neferett.linaris.utils.ConfigDatas;
import net.neferett.linaris.mistery.MysteryItem;
import net.neferett.linaris.mistery.MysteryItemsManager;
import net.neferett.linaris.utils.TitleUtils;

import java.util.Arrays;

public class JoinAndLeave implements Listener {

	static Boolean loaded = false;

	@EventHandler
	public void onPlayerReturnToHUB(final ReturnToLobbyEvent e) {
		e.getTarget().teleport(ConfigDatas.getInstance().getSpawn());
	}

	@EventHandler
	public void PlayerJoin(final PlayerJoinEvent e) {

		final Players p = PlayerManager.get().getPlayer(e.getPlayer());
		BukkitAPI.get().getPlayerDataManager().getPlayerData(p.getName());

		p.teleport(ConfigDatas.getInstance().getSpawn());

		if (p.getPlayerData().getRank().getVipLevel() >= 4) {
			p.setAllowFlight(true);
			p.setFlying(true);
		}
		p.setWalkSpeed(0.3F);
		p.getInventory().clear();

		this.setInventory(p);

		TitleUtils.sendTitle(p.getPlayer(), "§c???...", "§aPvPTraining, PvPFaction, SkyBlock, SkyPvP");

		this.setArmors(p.getPlayer());

		if (!loaded && (loaded = true)) {
			HologramsManager.get().build();
		}

		Arrays.asList(GamesEnum.values()).forEach((a) -> {
			a.getN().getHg().despawn();
			a.getN().createHolo(a.getGm());
		});

	}

	public void setArmors(final Player player) {
		BukkitAPI.get().getTasksManager().addTask(() -> {

			final String helmetID = SettingsManager.getSetting(player.getName(), Games.LOBBY, "armors-helmet");
			if (helmetID != null) {
				final MysteryItem item = MysteryItemsManager.getInstance().getMysteryItem(helmetID);
				if (item != null)
					item.onUse(player, false);
			}

			final String chestplateID = SettingsManager.getSetting(player.getName(), Games.LOBBY, "armors-chestplate");
			if (chestplateID != null) {
				final MysteryItem item = MysteryItemsManager.getInstance().getMysteryItem(chestplateID);
				if (item != null)
					item.onUse(player, false);
			}

			final String leggingsID = SettingsManager.getSetting(player.getName(), Games.LOBBY, "armors-leggings");
			if (leggingsID != null) {
				final MysteryItem item = MysteryItemsManager.getInstance().getMysteryItem(leggingsID);
				if (item != null)
					item.onUse(player, false);
			}

			final String bootsID = SettingsManager.getSetting(player.getName(), Games.LOBBY, "armors-boots");
			if (bootsID != null) {
				final MysteryItem item = MysteryItemsManager.getInstance().getMysteryItem(bootsID);
				if (item != null)
					item.onUse(player, false);
			}

			final String metaID = SettingsManager.getSetting(player.getName(), Games.LOBBY, "metamorphose");

			if (metaID != null) {
				final MysteryItem mitem = MysteryItemsManager.getInstance().getMysteryItem(metaID);
				if (mitem != null) {
					mitem.onUse(player, false);
					player.sendMessage("§f[§9Boutique§f] §7§oMétamorphose active: §b" + mitem.getName());
					if (!SettingsManager.isEnabled(player.getName(), Games.LOBBY, "metamorphose-viewself", true))
						player.sendMessage(
								"§7§oLa métamorphose est visible que par les autres joueurs, pour la voir vous aussi, activez l'oeil de l'ender dans le menu");
				}
			}

		});
	}

	public void setInventory(final Players p) {
		ItemsManager.get().ActionOnEachItems((item, slot) -> p.getInventory().setItem(slot, item.getStaticItem()));
		p.getInventory().setItem(8, MagicboxItem.get().getStaticItem());
		p.getInventory().setItem(6, ProfileItem.getInstance(p.getPlayer()).getStaticItem());
	}

}
