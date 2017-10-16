package net.neferett.linaris.lobby.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.PlayerData;
import net.neferett.linaris.api.Rank;
import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.utils.ItemStackUtils;
import net.neferett.linaris.utils.TimeUtils;

public class ProfileItem extends MenuItem {

	public static SpecialItem getInstance(final Player p) {
		return get(registerItem(new ProfileItem(p)));
	}

	public static String[] getLore(final Player p) {
		final List<String> lore = new ArrayList<>();

		final PlayerData pd = BukkitAPI.get().getPlayerDataManager().getPlayerData(p.getName());
		final Rank rank = pd.getPRank();

		if (pd.contains("rankFinish"))
			lore.add("§7Grade: §" + rank.getColor() + rank.getName() + " " + TimeUtils.minutesToDayHoursMinutes(
					(int) (Math.abs(pd.getRankFinish() - System.currentTimeMillis()) / 1000 / 60)));
		else
			lore.add("§7Grade: §" + rank.getColor() + rank.getName());
		lore.add("§7Coins: §e" + String.format("%.2f", pd.getEC()));
		lore.add("§7Crédits: §b" + String.format("%.2f", pd.getLC()));
		if (pd.contains("booster"))
			lore.add("§7Booster: " + TimeUtils.minutesToDayHoursMinutes(
					(int) (Math.abs(pd.getBoosterFinish() - System.currentTimeMillis()) / 1000 / 60)));
		else
			lore.add("§7Booster: §cDésactivé");
		lore.add("§7Gains de Coins: §e" + (100 + rank.getECMultiplier() * 100) + "%");
		lore.add("§7Gains de Crédits: §e" + (100 + rank.getLCMultiplier() * 100) + "%");

		return lore.toArray(new String[lore.size()]);
	}

	public int id = -1;

	public ProfileItem(final Player p) {
		super("§6§n" + p.getName(), ItemStackUtils.getPlayerHead(p.getName()), getLore(p));

	}

	@Override
	public void inventoryClickEvent(final Player arg0) {
		this.setCancelInteractEvent(true);
	}

}
