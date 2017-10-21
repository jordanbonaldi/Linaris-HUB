package net.neferett.linaris.lobby.shop.ranks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.PlayerData;
import net.neferett.linaris.inventory.InstantShopItem;

public class BoosterItem extends InstantShopItem {

	public static BoosterItem	BOOSTER15	= new BoosterItem(15, 500);
	public static BoosterItem	BOOSTER31	= new BoosterItem(31, 800);
	public static BoosterItem	BOOSTER7	= new BoosterItem(7, 300);

	public static List<String> getLore(final int day) {
		final List<String> strings = new ArrayList<>();
		strings.add("§7Durée: §a" + day + " Jours");
		strings.add("");
		strings.add("§a> §dAugmente les gains de §eCoins §a+25%");
		strings.add("§a> §duniquement pour vous !");
		strings.add("§a> §dSe cumule avec les bonus VIP et EpicBonus");
		strings.add("");
		strings.add("§7Chaque achat étend la durée !");
		strings.add("");
		return strings;
	}

	int days;

	public BoosterItem(final int day, final double price) {
		super("booster-" + day, "§6Booster §7x§a" + day + " jours", PriceType.LC, price, getLore(day),
				new ItemStack(Material.EXP_BOTTLE, day));
		this.days = day;
	}

	@Override
	public boolean alreadyHave(final PlayerData arg0) {
		return false;
	}

	public int getDays() {
		return this.days;
	}

	@Override
	public void onBuy(final PlayerData pd) {
		BukkitAPI.get().getTasksManager().addTask(() -> {
			pd.setBoosterTime(this.days);
		});
	}

	@Override
	public void onUse(final PlayerData arg0) {
		// TODO Auto-generated method stub

	}

}
