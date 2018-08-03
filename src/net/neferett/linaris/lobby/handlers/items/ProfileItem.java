package net.neferett.linaris.lobby.handlers.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.PlayerData;
import net.neferett.linaris.api.ranks.RankAPI;
import net.neferett.linaris.lobby.minigames.GamesEnum;
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
		final RankAPI rank = pd.getRank();

		lore.add("§f");
		if (pd.contains("rankFinish"))
			lore.add("§7Grade: §" + rank.getColor() + rank.getName() + " " + TimeUtils.minutesToDayHoursMinutes(
					(int) (Math.abs(pd.getRankFinish() - System.currentTimeMillis()) / 1000 / 60)));
		else
			lore.add("§7Grade: §" + rank.getColor() + rank.getName());
		lore.add("§f");
		lore.add("§7Jeu préféré: §e" + GamesEnum.getMostPlayedGame(pd));
		lore.add("§f");

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
