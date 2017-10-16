package net.neferett.linaris.lobby.shop.ranks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.PlayerData;
import net.neferett.linaris.lobby.shop.InstantShopItem;

public class BoosterItem extends InstantShopItem{

	public static BoosterItem BOOSTER7 = new BoosterItem(7, 300);
	public static BoosterItem BOOSTER15 = new BoosterItem(15, 500);
	public static BoosterItem BOOSTER31 = new BoosterItem(31, 800);
	
	int days;
	
	public BoosterItem(int day, double price) {
		super("booster-" + day, "§6Booster §7x§a" + day + " jours", PriceType.LC, price, getLore(day), new ItemStack(Material.EXP_BOTTLE,day));
		this.days = day;
	}
	
	public static List<String> getLore(int day) {
		List<String> strings = new ArrayList<String>();
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

	public int getDays() {
		return days;
	}

	@Override
	public void onBuy(Player p) {
		BukkitAPI api = BukkitAPI.get();
		api.getTasksManager().addTask(() -> {
			PlayerData pd = BukkitAPI.get().getPlayerDataManager().getPlayerData(p.getName());
			pd.setBoosterTime(days);
		});
	}

	@Override
	public boolean alreadyHave(Player p) {
		return false;
	}

	@Override
	public double getPrice(Player p) {
		return getBasePrice();
	}

}
